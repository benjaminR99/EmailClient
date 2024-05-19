import java.io.*;

public class EmailTxtNotification implements Runnable {
    private BlockingQueue myBlockingQueue;
    private boolean running = true;

    public EmailTxtNotification(BlockingQueue myBlockingQueue) {
        this.myBlockingQueue = myBlockingQueue;
        (new Thread(this)).start();
    }

    @Override
    public void run() {
        while (Thread.currentThread().isAlive()) {
            try {
                emailWriteToNotificationFile();
            } catch (Exception e) {
                e.printStackTrace(); // Properly handle exception
            }
        }
    }


    public void emailWriteToNotificationFile() throws Exception {
        File file = new File("C:\\Users\\a\\Desktop\\JAVA\\Email_Client_Ben\\notification.txt");
        boolean append = file.exists();

        try (FileOutputStream fo = new FileOutputStream(file, true);
             ObjectOutputStream outObj = append ? new AppendableObjectOutputStream(fo) : new ObjectOutputStream(fo)) {

            outObj.writeObject(myBlockingQueue.dequeue());
        }
    }
}

 class AppendableObjectOutputStream extends ObjectOutputStream {

    public AppendableObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        reset();
    }
}
