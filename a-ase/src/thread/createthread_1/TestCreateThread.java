package thread.createthread_1;

/**
 * ClassName: TestCreateThread
 * Package: thread.createthread
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/10/13 15:24
 * @Version 1.0
 */
public class TestCreateThread {
    public static void main(String[] args) {



        // 创建继承 thread 类的线程1的实例
        ExtendThread thread1 = new ExtendThread();
        // 通过对象调用 start() 启动线程
        thread1.start();

        //
        ExtendThread thread2 = new ExtendThread();
        thread2.setName("this is the thread's name");
        thread2.start();

        /**
         * 注意不能使用 run() 方法来启动线程，对线程什么时候开始调度，是计算机操作系统的问题
         * 使用 run() 方法不会启动线程，只是普通的在 main 线程里调用其他类的方法
         *
         * 已经启动的线程不能再次执行 start() 方法，否则会报 IllegalThreadStateException 错
         * 可以重新创建一个 线程 的实例对象，再次通过 start() 方法启动
         */
        // 在 main 线程里创建一个打印 1-100 奇数的操作
        for (int i = 1; i <= 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }







        // 第二种创建方法
        ImplementsRunnable runnable = new ImplementsRunnable();
        // 将此对象当成参数传入 Thread 类的构造器中
        Thread thread3 = new Thread(runnable);
        Thread thread4 = new Thread(runnable);
        // 启动线程，这种通过实现接口的线程就可以创建一个实例，向多个 thread 的构造器里放，然后同时执行
        thread3.start();
        thread4.start();
        // 在 main 线程里创建一个打印 1-100 10的倍数的操作
        for (int i = 1; i <= 100; i++) {
            if (i % 10 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }



        // 更改线程的名字
        Thread thread5 = new Thread(runnable, "this is the thread's name");



        // 通过匿名内部类来实现线程的创建和启动
        new Thread("this is the thread's name"){
            @Override
            public void run() {
                System.out.println(" 这是通过匿名内部类创建并且启动的线程! ");
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(" 这是通过匿名内部类创建并且启动的线程! ");
            }
        }, "this is the thread's name").start();


    }
}
