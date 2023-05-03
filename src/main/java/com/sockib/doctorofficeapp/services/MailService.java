package com.sockib.doctorofficeapp.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Profile("prod")
@Service
public class MailService {

    private final JavaMailSender mailSender;
    // TODO remove that line
    private final Environment env;
    private final String from;

    public MailService(JavaMailSender mailSender, Environment env) {
        this.mailSender = mailSender;
        this.env = env;
        this.from = env.getProperty("spring.mail.username");
    }

    public void sendMail(String to, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }

}
