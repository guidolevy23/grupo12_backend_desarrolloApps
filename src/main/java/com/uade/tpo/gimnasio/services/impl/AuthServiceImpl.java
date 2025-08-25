package com.uade.tpo.gimnasio.services.impl;

import com.uade.tpo.gimnasio.dto.OtpDto;
import com.uade.tpo.gimnasio.models.entity.Otp;
import com.uade.tpo.gimnasio.services.AuthService;
import com.uade.tpo.gimnasio.services.OtpService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    OtpService otpService;

    public AuthServiceImpl(OtpService otpService) {
        this.otpService = otpService;
    }

    @Override
    public boolean createAndSendOtp(OtpDto dto) {
        Otp otp = otpService.create(dto);
        return true;
    }
}
