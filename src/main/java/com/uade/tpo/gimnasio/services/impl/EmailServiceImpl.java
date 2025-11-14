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
        String host = System.getenv("EMAIL_HOST");
        String username = System.getenv("EMAIL_USERNAME");
        String password = System.getenv("EMAIL_PASSWORD");

        // Si no hay configuración SMTP, simulamos el envío
        if (host == null || username == null || password == null || host.equals("localhost")) {
            System.out.println("📩 [SIMULADO] Email enviado a: " + String.join(", ", msg.getTo()));
            System.out.println("Asunto: " + msg.getSubject());
            System.out.println("Cuerpo: " + msg.getText());
            System.out.println("───────────────────────────────────────");
            System.out.println("👉 Copiá el código OTP de arriba para usarlo en el login");
            System.out.println("───────────────────────────────────────");
            return;
        }

        // Envío real si hay credenciales
        try {
            mailSender.send(msg);
            log.info("📬 Email enviado correctamente a {}", String.join(", ", msg.getTo()));
        } catch (MailException ex) {
            log.error("❌ Error enviando email: {}", ex.getMessage(), ex);
        }
    }
}
