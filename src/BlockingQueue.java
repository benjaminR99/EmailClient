import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue  {
    private int size;
    LinkedList<Email> emailQueue ;

    public BlockingQueue(int size) {
        this.size = size;
        emailQueue  = new LinkedList<>();
    }
    public synchronized void enqueue(Email email)  {
        try{
            while (emailQueue.size()>=size){
                wait();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        emailQueue.add(email) ;
        notifyAll();
    }
    public synchronized Email dequeue() {
        try{
            while (emailQueue.isEmpty()){
                wait();
            }
        }catch (InterruptedException e2){
            e2.printStackTrace();
        }
        Email temp = emailQueue.pop();
        notifyAll();
        return temp;
    }



}
