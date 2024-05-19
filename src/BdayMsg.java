import java.text.SimpleDateFormat;

import java.util.Date;
public class BdayMsg {
    String todayDate= new SimpleDateFormat("yyyy/MM/dd").format(new Date());
    public void sendBdayMsg(Sendable obj) throws Exception {
            System.out.println("We are sending Birthday Wishes to " + obj.getName());
            Mail mail = new Mail(todayDate,obj.getEmail(), "Birthday Wish", obj.getBdayMsg());	//sending birthday mail
            mail.sendMail();
    }
}
