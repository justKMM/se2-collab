package entity_test;

import hbrs.se2.collhbrs.model.entity.Email;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void testEmailConstructorAndGetters() {
        String recipient = "test@example.com";
        String msgBody = "This is a test message";
        String subject = "Test Subject";
        String attachment = "test_attachment.pdf";

        Email email = new Email(recipient, msgBody, subject, attachment);

        assertEquals(recipient, email.getRecipient());
        assertEquals(msgBody, email.getMsgBody());
        assertEquals(subject, email.getSubject());
        assertEquals(attachment, email.getAttachment());
    }

    @Test
    void testSetters() {
        Email email = new Email("test@example.com", "This is a test message", "Test Subject", "test_attachment.pdf");

        String newRecipient = "new@example.com";
        String newMsgBody = "This is a new test message";
        String newSubject = "New Test Subject";
        String newAttachment = "new_test_attachment.pdf";

        email.setRecipient(newRecipient);
        email.setMsgBody(newMsgBody);
        email.setSubject(newSubject);
        email.setAttachment(newAttachment);

        assertEquals(newRecipient, email.getRecipient());
        assertEquals(newMsgBody, email.getMsgBody());
        assertEquals(newSubject, email.getSubject());
        assertEquals(newAttachment, email.getAttachment());
    }

    @Test
    void testToString() {
        String recipient = "test@example.com";
        String msgBody = "This is a test message";
        String subject = "Test Subject";
        String attachment = "test_attachment.pdf";

        Email email = new Email(recipient, msgBody, subject, attachment);
        String expectedString = "Email(recipient=" + recipient +
                ", msgBody=" + msgBody +
                ", subject=" + subject +
                ", attachment=" + attachment +
                ")";

        assertEquals(expectedString, email.toString());
    }
}