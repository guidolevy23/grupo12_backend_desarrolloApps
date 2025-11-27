package com.uade.tpo.gimnasio.services;

import com.uade.tpo.gimnasio.dto.qr.QRCodeDataDTO;

public interface QRCodeService {
    /**
     * Generate QR code data for a specific class session
     */
    QRCodeDataDTO generateQRCodeData(Long classId, String sessionId);
    
    /**
     * Validate QR code data
     */
    boolean validateQRCode(String qrCodeData, Long expectedClassId);
    
    /**
     * Generate QR code image as byte array
     */
    byte[] generateQRCodeImage(QRCodeDataDTO qrData) throws Exception;
}
