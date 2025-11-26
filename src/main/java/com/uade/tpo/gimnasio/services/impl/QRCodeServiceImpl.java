package com.uade.tpo.gimnasio.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uade.tpo.gimnasio.dto.qr.QRCodeDataDTO;
import com.uade.tpo.gimnasio.qrcodegen.QrCode;
import com.uade.tpo.gimnasio.services.QRCodeService;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;

@Service
public class QRCodeServiceImpl implements QRCodeService {

    private static final String SECRET_KEY = "qr-code-secret-key-change-in-production";
    private static final String ALGORITHM = "HmacSHA256";
    private static final int QR_CODE_SIZE = 300;

    private final ObjectMapper objectMapper;

    public QRCodeServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public QRCodeDataDTO generateQRCodeData(Long classId, String sessionId) {
        Instant timestamp = Instant.now();
        String dataToSign = classId + ":" + sessionId + ":" + timestamp.toString();
        String signature = generateSignature(dataToSign);

        return new QRCodeDataDTO(
                classId,
                sessionId,
                timestamp,
                "checkin",
                signature);
    }

    @Override
    public boolean validateQRCode(String qrCodeData, Long expectedClassId) {
        try {
            QRCodeDataDTO qrData = objectMapper.readValue(qrCodeData, QRCodeDataDTO.class);

            // Check if it's a check-in type QR code
            if (!"checkin".equals(qrData.type())) {
                return false;
            }

            // Check if class ID matches
            if (!expectedClassId.equals(qrData.classId())) {
                return false;
            }

            // Check if QR code is not expired (valid for 24 hours)
            Instant now = Instant.now();
            Instant qrTimestamp = qrData.timestamp();
            if (now.isAfter(qrTimestamp.plusSeconds(86400))) { // 24 hours
                return false;
            }

            // Verify signature
            String dataToVerify = qrData.classId() + ":" + qrData.sessionId() + ":" + qrData.timestamp().toString();
            String expectedSignature = generateSignature(dataToVerify);

            return expectedSignature.equals(qrData.signature());

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public byte[] generateQRCodeImage(QRCodeDataDTO qrData) throws Exception {
        try {
            String qrContent = objectMapper.writeValueAsString(qrData);

            // Generate QR Code using Nayuki library
            QrCode qrCode = QrCode.encodeText(qrContent, QrCode.Ecc.MEDIUM);

            // Convert QR Code to BufferedImage
            int scale = QR_CODE_SIZE / qrCode.size;
            int border = 1;
            BufferedImage image = toBufferedImage(qrCode, scale, border);

            // Convert BufferedImage to byte array
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "PNG", outputStream);

            return outputStream.toByteArray();

        } catch (Exception e) {
            throw new Exception("Error generating QR code image", e);
        }
    }

    /**
     * Converts a QrCode object to a BufferedImage.
     * 
     * @param qr     The QR code to convert
     * @param scale  The scale factor (pixels per module)
     * @param border The border size in modules
     * @return BufferedImage representation of the QR code
     */
    private BufferedImage toBufferedImage(QrCode qr, int scale, int border) {
        int size = (qr.size + border * 2) * scale;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                int moduleX = x / scale - border;
                int moduleY = y / scale - border;
                boolean isDark = qr.getModule(moduleX, moduleY);
                int color = isDark ? 0x000000 : 0xFFFFFF; // Black or white
                image.setRGB(x, y, color);
            }
        }

        return image;
    }

    private String generateSignature(String data) {
        try {
            Mac mac = Mac.getInstance(ALGORITHM);
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
            mac.init(secretKeySpec);

            byte[] hash = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Error generating signature", e);
        }
    }
}
