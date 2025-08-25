package com.uade.tpo.gimnasio.config;

import com.uade.tpo.gimnasio.services.AuthService;
import com.uade.tpo.gimnasio.services.EmailService;
import com.uade.tpo.gimnasio.services.OtpService;
import com.uade.tpo.gimnasio.services.impl.AuthServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.time.Clock;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    @Qualifier("otpTemplate")
    SimpleMailMessage otpTemplate(@Value("${spring.mail.username}") String username) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        return message;
    }

    @Bean
    AuthService authService(OtpService otpService,
                            EmailService emailService,
                            @Qualifier("otpTemplate") SimpleMailMessage templateMessage) {
        return new AuthServiceImpl(otpService, emailService, templateMessage);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

