package com.uade.tpo.gimnasio.services.impl;

import com.uade.tpo.gimnasio.dto.OtpDto;
import com.uade.tpo.gimnasio.dto.auth.LoginRequest;
import com.uade.tpo.gimnasio.dto.auth.LoginResponse;
import com.uade.tpo.gimnasio.dto.auth.RegisterRequest;
import com.uade.tpo.gimnasio.dto.auth.RegisterResponse;
import com.uade.tpo.gimnasio.models.entity.Otp;
import com.uade.tpo.gimnasio.models.entity.User;
import com.uade.tpo.gimnasio.repositories.UserRepository;
import com.uade.tpo.gimnasio.security.JwtUtil;
import com.uade.tpo.gimnasio.services.AuthService;
import com.uade.tpo.gimnasio.services.EmailService;
import com.uade.tpo.gimnasio.services.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.security.Key;
import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           JwtUtil jwtUtil,
                           PasswordEncoder passwordEncoder,
                           UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        return new LoginResponse(jwtUtil.generateToken(authentication.getName()));
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already taken");
        }
        User user = new User();
        user.setEmail(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(User.Role.USER);
        userRepository.save(user);

        return new RegisterResponse("User registered successfully!");
    }
    // $2a$10$m8NU7CI6FH4oi64sY8p2TeRGlX0Cf4Cf7mlKx/e/RhvosxMvUSHVG

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
