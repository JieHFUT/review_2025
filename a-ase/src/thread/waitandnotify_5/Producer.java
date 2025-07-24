package thread.waitandnotify_5;


/**
 * 使用两个线程打印 1 - 100，交替打印
 */

public class Producer extends Thread {

    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("生产者开始生产商品...");

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.addProduct();

        }
    }
}
