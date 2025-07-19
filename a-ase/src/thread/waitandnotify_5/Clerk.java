package thread.waitandnotify_5;


// 店员
public class Clerk {
    // 产品的数量
    private int productNum = 0;

    // 增加产品数量的方法
    public synchronized void addProduct() {
        if (productNum >= 20) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            productNum++;
            System.out.println(Thread.currentThread().getName() + "生产了第 " + productNum + " 个产品.");

            // 唤醒
            notifyAll();
        }
    }

    // 减少产品数量的办法
    public synchronized void removeProduct() {

        if (productNum <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + "消费了第 " + productNum + " 个产品.");
            productNum--;
            notifyAll();
        }
    }

}
