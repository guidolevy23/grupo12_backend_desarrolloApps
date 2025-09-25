package com.uade.tpo.gimnasio.services.impl;

import com.uade.tpo.gimnasio.services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void send(SimpleMailMessage msg) {
        try {
            mailSender.send(msg);
        } catch (MailException ex) {
            log.error("Error enviando email: {}", ex.getMessage(), ex);
        }
    }
}
