package thread.waitandnotify_5;

public class PrintNumber implements Runnable {


    private int number = 1;
    Object lock = new Object();

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                lock.notify();

                if (number <= 100) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + number);
                    number++;

                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                } else {
                    break;
                }
            }
        }
    }

}
