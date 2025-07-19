package thread.createthread_1.callableandpool;

import java.util.concurrent.FutureTask;

public class ImplementCallableTest {
    public static void main(String[] args) {

        // 1. 创建实现 Callable 接口的对象
        ImplementCallable implementCallable = new ImplementCallable();


        // 2. 将此 Callable 接口实现类的对象作为传递到 FutureTask 构造器中，创建 FutureTask 的对象
        FutureTask futureTask = new FutureTask(implementCallable);

        // 3. 将FutureTask的对象作为参数传递到Thread类的构造器中，创建Thread对象，并调用start()
        Thread t1 = new Thread(futureTask);
        t1.start();

        System.out.println("main()线程");

        try {
            // 4. 获取Callable中call方法的返回值
            //get()返回值即为FutureTask构造器参数Callable实现类重写的call()的返回值。
            Object sum = futureTask.get();
            System.out.println("总和为：" + sum);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
