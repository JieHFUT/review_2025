package thread.threadtongbu_3.threadsafe;

/**
 * ClassName: WindowTest
 * Package: thread.threadtongbu.threadsafe
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/10/14 22:14
 * @Version 1.0
 */
public class WindowTest {

    public static void main(String[] args) {

        Window window1 = new Window("窗口一");
        Window window2 = new Window("窗口二");
        Window window3 = new Window("窗口三");

        window1.start();
        window2.start();
        window3.start();




    }
}
