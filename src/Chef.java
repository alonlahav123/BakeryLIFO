import java.util.ArrayList;

public class Chef extends Thread {
    private Stock s;
    private ArrayList<String> queue;

    public Chef(Stock s, ArrayList<String> queue) {
        this.s = s;
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true) {
            if(queue.size() > s.stock) {
                System.out.println(this.getName() + " [CHEF]: making cake");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (s) {
                    s.stock++;
                    System.out.println(this.getName() + " [CHEF]: finished cake, there are " + s.stock + " cakes");
                    s.notifyAll();
                }
            } else {
                System.out.println(this.getName() + " [CHEF]: No more cakes needed");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

