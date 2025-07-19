package thread.createthread_1;

/**
 * ClassName: ImplementsRunnable
 * Package: thread.createthread
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/10/13 15:50
 * @Version 1.0
 */

// 1. 创建一个实现了 Runnable 接口的类
public class ImplementsRunnable implements Runnable {

    // 2. 实现 Thread 类的 run() 方法, 将要执行的操作声明在该方法体中
    @Override
    public void run() {
        // 3. 实现打印 1-100 内5的倍数
        for (int i = 1; i <= 100; i++) {
            if (i % 5 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}
