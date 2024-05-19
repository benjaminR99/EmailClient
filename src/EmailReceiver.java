

import java.util.Properties;
import javax.mail.*;
import javax.mail.search.*;


public class EmailReceiver implements Runnable {
    private BlockingQueue myBlockingQueue;
    private Thread EmailReceiverthread ;
    public EmailReceiver(BlockingQueue myBlockingQueue){
        this.myBlockingQueue=myBlockingQueue;
        EmailReceiverthread = new Thread(this) ;
        EmailReceiverthread.start();		//starting this thread
    }

    @Override
    public void run() {
        while (Thread.currentThread().isAlive()) {
            try{
                emailReceive();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }

    }

    public void emailReceive() throws Exception {

        //Email properties
        String host = "imap.gmail.com";
        String user = "remisiusbenjamin@gmail.com";
        String password = "Rbenj@19";

        Properties properties = System.getProperties();
        Session session = Session.getDefaultInstance(properties);
        Store store = session.getStore("imaps");
        store.connect(host, user, password);
        Folder inbox = store.getFolder("inbox");
        inbox.open(Folder.READ_WRITE);

        // search for all "unseen" messages
        Flags seen = new Flags(Flags.Flag.SEEN);
        FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
        Message messages[] = inbox.search(unseenFlagTerm);

        if (inbox.getUnreadMessageCount()> 0) {
            for (int i = 0; i < messages.length; i++) {
                Email email = new Email((messages[i].getFrom()[0]).toString(), messages[i].getSubject(),(messages[i].getContent()).toString(),messages[i].getSentDate());
                myBlockingQueue.enqueue(email);
                messages[i].setFlag(Flags.Flag.SEEN, true);			//set flag as seen
            }
        }
        inbox.close(true);
        store.close();
    }







}
