package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.entity.Email;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class EmailService implements Serializable {

    private final transient JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public String sendSimpleMail(Email email) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("Aldaringhausen-Klangkreationen-GmbH");
            message.setTo(email.getRecipient());
            message.setSubject(email.getSubject());
            message.setText(email.getMsgBody());
            // Sending the email
            javaMailSender.send(message);
            return "Email sent successfully";
        } catch (Exception e) {
            return "Email sent failed - Email type: Simple Email. Message: " + e.getMessage();
        }
    }
}
