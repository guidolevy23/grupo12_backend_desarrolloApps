package com.uade.tpo.gimnasio.services.impl;

import com.uade.tpo.gimnasio.dto.OtpDto;
import com.uade.tpo.gimnasio.models.entity.Otp;
import com.uade.tpo.gimnasio.services.AuthService;
import com.uade.tpo.gimnasio.services.EmailService;
import com.uade.tpo.gimnasio.services.OtpService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private OtpService otpService;
    private EmailService emailService;
    private SimpleMailMessage templateMessage;

    public AuthServiceImpl(OtpService otpService, EmailService emailService, SimpleMailMessage templateMessage) {
        this.otpService = otpService;
        this.emailService = emailService;
        this.templateMessage = templateMessage;
    }

    @Override
    public boolean createAndSendOtp(OtpDto dto) {
        Otp otp = otpService.create(dto);
        SimpleMailMessage msg = createOptMessage(otp);
        emailService.send(msg);
        return true;
    }

    private SimpleMailMessage createOptMessage(Otp otp) {
        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setTo(otp.getIdentifier());
        msg.setSubject("OTP Verification Code: " + otp.getOtp());
        msg.setText("Codigo de verificacion: " + otp.getOtp());
        return msg;
    }

    @Override
    public boolean validateOtp(OtpDto dto) {
        return otpService.validate(dto);
    }
}
