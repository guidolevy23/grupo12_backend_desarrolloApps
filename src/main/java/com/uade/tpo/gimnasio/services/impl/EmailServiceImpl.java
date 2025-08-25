package com.uade.tpo.gimnasio.services.impl;

import com.uade.tpo.gimnasio.services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private static Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

    private MailSender mailSender;

    public EmailServiceImpl(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void send(SimpleMailMessage msg) {
        try {
            this.mailSender.send(msg);
        }
        catch (MailException ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
