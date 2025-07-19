package thread.lockanddead_4.singleton;

public class Bank {

    private Bank() {}
    private static volatile Bank instance = null;

    // 懒汉式存在线程安全问题

    // 实现线程安全的方式一(使用同步方法)
    public static synchronized Bank getInstance1() {
        // 同步监视器默认为：Bank.class
        if (instance == null) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new Bank();
        }
        return instance;
    }

    // 实现线程安全的方式二(使用同步代码块)
    public static Bank getInstance2() {
        synchronized (Bank.class) {
            if (instance == null) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                instance = new Bank();
            }
        }
        return instance;
    }

    //实现线程安全的方式3:相较于方式1和方式2来讲，效率更高。为了避免出现指令重排，需要将instance声明为volatile
    public static Bank getInstance3(){
        if(instance == null) {
            synchronized (Bank.class) {
                if (instance == null) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new Bank();
                }

            }
        }
        return instance;
    }

}
