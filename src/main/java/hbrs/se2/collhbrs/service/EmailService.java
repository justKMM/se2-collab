package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.model.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final String emailSender = "Aldaringhausen-Klangkreationen-GmbH";

    @Autowired
    private JavaMailSender javaMailSender;

    public String sendSimpleMail(Email email) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailSender);
            message.setTo(email.getRecipient());
            message.setSubject(email.getSubject());
            message.setText(email.getMsgBody());
            // Sending the email
            javaMailSender.send(message);
            return "Email sent successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Email sent failed - Email type: Simple Email. Message: " + e.getMessage();
        }
    }
}
