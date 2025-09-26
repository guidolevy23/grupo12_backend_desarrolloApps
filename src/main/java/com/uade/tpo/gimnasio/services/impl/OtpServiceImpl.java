package com.uade.tpo.gimnasio.services.impl;

import com.uade.tpo.gimnasio.models.entity.Otp;
import com.uade.tpo.gimnasio.models.entity.User;
import com.uade.tpo.gimnasio.repositories.OtpRepository;
import com.uade.tpo.gimnasio.repositories.UserRepository;
import com.uade.tpo.gimnasio.services.EmailService;
import com.uade.tpo.gimnasio.services.OtpService;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpServiceImpl implements OtpService {

    private final static int OTP_EXPIRY_MINUTES = 90;

    private static String generateCode() {
        return String.valueOf(new Random().nextInt(100_000, 900_000));
    }

    private final Clock clock;
    private final OtpRepository otpRepository;
    private final EmailService emailService;
    private final UserRepository userRepository;

    public OtpServiceImpl(Clock clock,
                          OtpRepository otpRepository,
                          EmailService emailService,
                          UserRepository userRepository) {
        this.clock = clock;
        this.otpRepository = otpRepository;
        this.emailService = emailService;
        this.userRepository = userRepository;
    }

    private LocalDateTime generateExpiryTime() {
        return LocalDateTime.now(clock).plusMinutes(OTP_EXPIRY_MINUTES);
    }

    @Override
    public void createAndSend(User user) {
        Otp otp = otpRepository
                .findByUser(user)
                .orElse(new Otp());
        otp.setUser(user);
        otp.setOtp(generateCode());
        otp.setExpiresAt(generateExpiryTime());
        Otp savedOtp = otpRepository.saveAndFlush(otp);
        sendOtpEmail(user, savedOtp.getOtp());
    }

    private void sendOtpEmail(User user, String code) {
        var msg = new org.springframework.mail.SimpleMailMessage();
        msg.setTo(user.getEmail());
        msg.setSubject("RitmoFit - Tu codigo de verificacion");
        msg.setText("Tu codigo de verifcacion: " + code + ". Se vence en " + OTP_EXPIRY_MINUTES + " minutos.");
        emailService.send(msg);
    }

    @Override
    public boolean validate(User user, String code) {
        return otpRepository.findByUser(user)
                .filter(otp -> otp.getOtp().equals(code))
                .filter(otp -> otp.getExpiresAt().isAfter(LocalDateTime.now(clock)))
                .isPresent();
    }
}
