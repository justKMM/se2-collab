package entity_test;

import hbrs.se2.collhbrs.model.entity.Email;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    private static final String RECIPIENT = "test@example.com";
    private static final String MSG_BODY = "This is a test message";
    private static final String SUBJECT = "Test Subject";
    private static final String ATTACHMENT = "test_attachment.pdf";

    private static final String NEW_RECIPIENT = "new@example.com";
    private static final String NEW_MSG_BODY = "This is a new test message";
    private static final String NEW_SUBJECT = "New Test Subject";
    private static final String NEW_ATTACHMENT = "new_test_attachment.pdf";

    @Test
    void testEmailConstructorAndGetters() {
        Email email = new Email(RECIPIENT, MSG_BODY, SUBJECT, ATTACHMENT);

        assertEquals(RECIPIENT, email.getRecipient());
        assertEquals(MSG_BODY, email.getMsgBody());
        assertEquals(SUBJECT, email.getSubject());
        assertEquals(ATTACHMENT, email.getAttachment());
    }

    @Test
    void testSetters() {
        Email email = new Email(RECIPIENT, MSG_BODY, SUBJECT, ATTACHMENT);

        email.setRecipient(NEW_RECIPIENT);
        email.setMsgBody(NEW_MSG_BODY);
        email.setSubject(NEW_SUBJECT);
        email.setAttachment(NEW_ATTACHMENT);

        assertEquals(NEW_RECIPIENT, email.getRecipient());
        assertEquals(NEW_MSG_BODY, email.getMsgBody());
        assertEquals(NEW_SUBJECT, email.getSubject());
        assertEquals(NEW_ATTACHMENT, email.getAttachment());
    }

    @Test
    void testToString() {
        Email email = new Email(RECIPIENT, MSG_BODY, SUBJECT, ATTACHMENT);
        String expectedString = "Email(recipient=" + RECIPIENT +
                ", msgBody=" + MSG_BODY +
                ", subject=" + SUBJECT +
                ", attachment=" + ATTACHMENT +
                ")";

        assertEquals(expectedString, email.toString());
    }
}