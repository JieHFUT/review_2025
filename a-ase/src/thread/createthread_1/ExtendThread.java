package thread.createthread_1;

/**
 * ClassName: ExtendThread
 * Package: thread.createthread
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/10/13 15:09
 * @Version 1.0
 */

// 创建一个分线程1，遍历100以内的整数

// 1. 继承 Thread 类
public class ExtendThread extends Thread {
    // 2. 重写 Thread 类的 run() 方法, 将要执行的操作声明在该方法体中
    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}
