package hbrs.se2.collhbrs.service;

import hbrs.se2.collhbrs.entity.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(@Qualifier("javaMailSender") JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /*@Value("${spring.mail.username}") private String sender;*/
    private final String sender = "Sender";
    // Send a simple email
    public String sendSimpleMail(Email email) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            // Filling in the email's details
            message.setFrom(sender);
            message.setTo(email.getRecipient());
            message.setSubject(email.getSubject());
            message.setText(email.getMsgBody());
            // Sending the email
            javaMailSender.send(message);
            return "Email sent successfully";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Email sent failed - Email type: Simple Email. Message: " + e.getMessage();
        }
    }

    // Send an email with attachment
    public String sendMailWithAttachment(Email email) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            // Init the helper (has to be put in try-catch cause the constructor throws exception)
            helper = new MimeMessageHelper(message, true);
            // Filling in the email's details
            helper.setFrom(sender);
            helper.setTo(email.getRecipient());
            helper.setText(email.getMsgBody());
            helper.setSubject(email.getSubject());
            // Adding the attachments
            FileSystemResource file = new FileSystemResource(new File(email.getMsgBody()));
            helper.addAttachment(Objects.requireNonNull(file.getFilename()), file);
            // Sending the email
            javaMailSender.send(message);
            return "Email sent successfully";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Email sent failed - Email type: Email with Attachment(s). Message: " + e.getMessage();
        }
    }
}
