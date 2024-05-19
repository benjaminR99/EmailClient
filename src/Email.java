import java.util.Date;
public class Email {
    private String senderID;
    private String subject;
    private String content;
    private Date receivedDate;
    public Email(String senderID,String subject,String content,Date receivedDate) {
        this.senderID=senderID;
        this.subject=subject;
        this.content=content;
        this.receivedDate=receivedDate;
    }
    public String getSenderID() {
        return senderID;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }
}
