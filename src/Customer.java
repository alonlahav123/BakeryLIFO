import java.util.ArrayList;

public class Customer extends Thread {
    private Stock s;
    private ArrayList<String> queue;
    private boolean run;

    public Customer(Stock s, ArrayList<String> queue) {
        this.s = s;
        this.queue = queue;
        this.run = true;
    }

    @Override
    public void run() {
        System.out.println(this.getName() + " is here!");
        queue.add(this.getName());
        while(true) {
            if(s.stock >= 1 && queue.get(queue.size()-1).equals(this.getName())) {

                synchronized (s) {
                    s.stock--;
                    System.out.println(this.getName() + " [CUST]: mmmmm... cake");
                    queue.remove(queue.size()-1);
                }
                try {
                    Thread.sleep(5000 + (int)(Math.random()*10000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.add(this.getName());
            } else {
                try {
                    synchronized (s) {
                        s.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}


// customer gets cake then either goes away and does not return or return but later