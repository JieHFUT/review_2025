package thread.createthread_1.callableandpool;

/*
 * 创建多线程的方式三：实现Callable （jdk5.0新增的）
 */


import java.util.concurrent.Callable;

//1.创建一个实现Callable的实现类
public class ImplementCallable implements Callable {

    // 2.实现call方法，将此线程需要执行的操作声明在call()中
    @Override
    public Object call() throws Exception {
        // 打印100以内的偶数 并且求100以内的偶数和
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
            Thread.sleep(1000);
        }
        return sum;
    }
}
