package com.uade.tpo.gimnasio.services.impl;

import com.uade.tpo.gimnasio.dto.OtpDto;
import com.uade.tpo.gimnasio.models.entity.Otp;
import com.uade.tpo.gimnasio.repositories.OtpRepository;
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

    public OtpServiceImpl(Clock clock, OtpRepository otpRepository) {
        this.clock = clock;
        this.otpRepository = otpRepository;
    }

    private LocalDateTime generateExpiryTime() {
        return LocalDateTime.now(clock).plusMinutes(OTP_EXPIRY_MINUTES);
    }

    @Override
    public Otp create(OtpDto dto) {
        Otp otp = otpRepository
                .findByIdentifier(dto.getIdentifier())
                .orElse(new Otp());
        otp.setIdentifier(dto.getIdentifier());
        otp.setOtp(generateCode());
        otp.setExpiresAt(generateExpiryTime());
        return otpRepository.saveAndFlush(otp);
    }

    @Override
    public boolean validate(OtpDto dto) {
        return otpRepository.findByIdentifier(dto.getIdentifier())
                .filter(otp -> otp.getOtp().equals(dto.getOtp()))
                .filter(otp -> otp.getExpiresAt().isAfter(LocalDateTime.now(clock)))
                .isPresent();
    }
}
