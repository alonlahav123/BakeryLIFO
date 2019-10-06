import java.util.ArrayList;

public class Bakery {
    public static void main(String[] args) {
        ArrayList<Chef> chefs = new ArrayList<>();
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<String> queue = new ArrayList<>();
        Stock s = new Stock();
        s.stock = 0;

        for(int i = 0; i < 10; i++) {
            Customer c = new Customer(s, queue);
            c.start();
            customers.add(c);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Chef chef = new Chef(s, queue);
        chef.start();

        try {
            chef.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
