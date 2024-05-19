import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Mail {
    private String date;
    private String email;
    private String subject;
    private String content;


    public Mail(String date,String email, String subject, String content) {
        this.date=date;
        this.email=email;
        this.subject=subject;
        this.content=content;
    }

    public String getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public  void sendMail()  {
        System.out.println("Preparing to send email");

        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myaccountEmail = "huma32875@gmail.com";
        String password = "Rbenj@19";

        Session session = Session.getInstance(properties,new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myaccountEmail, password);
            }
        });
        Message message = prepareMessage(session , myaccountEmail,email,subject,content);
        try{
            Transport.send(message);
            System.out.println("Message send successfully");
            System.out.println("");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private static Message prepareMessage(Session session, String myaccountEmail, String email, String subject, String content) {
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myaccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject(subject);
            message.setText(content);
            return message;
        } catch (Exception e) {

        }
        return null;
    }
}
