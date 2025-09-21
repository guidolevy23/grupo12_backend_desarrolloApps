package com.uade.tpo.gimnasio.services.impl;

import com.uade.tpo.gimnasio.dto.OtpDto;
import com.uade.tpo.gimnasio.dto.auth.LoginRequest;
import com.uade.tpo.gimnasio.dto.auth.LoginResponse;
import com.uade.tpo.gimnasio.models.entity.Otp;
import com.uade.tpo.gimnasio.security.JwtUtil;
import com.uade.tpo.gimnasio.services.AuthService;
import com.uade.tpo.gimnasio.services.EmailService;
import com.uade.tpo.gimnasio.services.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        return new LoginResponse(jwtUtil.generateToken(authentication.getName()));
    }

//    @Override
//    public boolean createAndSendOtp(OtpDto dto) {
//        Otp otp = otpService.create(dto);
//        SimpleMailMessage msg = createOptMessage(otp);
//        emailService.send(msg);
//        return true;
//    }
//
//    private SimpleMailMessage createOptMessage(Otp otp) {
//        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
//        msg.setTo(otp.getIdentifier());
//        msg.setSubject("OTP Verification Code: " + otp.getOtp());
//        msg.setText("Codigo de verificacion: " + otp.getOtp());
//        return msg;
//    }
//
//    @Override
//    public boolean validateOtp(OtpDto dto) {
//        return otpService.validate(dto);
//    }
}
