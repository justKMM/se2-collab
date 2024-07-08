package hbrs.se2.collhbrs.model.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Email {
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;

    public Email(String recipient, String msgBody, String subject, String attachment) {
        this.recipient = recipient;
        this.msgBody = msgBody;
        this.subject = subject;
        this.attachment = attachment;
    }
    public String toString() {
        return "Email(recipient=" + this.getRecipient() +
                ", msgBody=" + this.getMsgBody() +
                ", subject=" + this.getSubject() +
                ", attachment=" + this.getAttachment() +
                ")";
    }

}
