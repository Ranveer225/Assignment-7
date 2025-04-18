import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    static Lock fork0 = new ReentrantLock();
    static Lock fork1 = new ReentrantLock();

    public static void main(String[] args) {
        Thread philosopher0 = new Thread(() -> {
            while (true) {
                think(0);
                fork0.lock();
                fork1.lock();
                eat(0);
                fork1.unlock();
                fork0.unlock();
            }
        });

        Thread philosopher1 = new Thread(() -> {
            while (true) {
                think(1);
                fork1.lock();
                fork0.lock();
                eat(1);
                fork0.unlock();
                fork1.unlock();
            }
        });

        philosopher0.start();
        philosopher1.start();
    }

    static void think(int id) {
        System.out.println("Philosopher " + id + " is thinking.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void eat(int id) {
        System.out.println("Philosopher " + id + " is eating.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
