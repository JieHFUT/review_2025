package thread.threadtongbu_3.threadsafe;

/**
 * ClassName: Window1Test
 * Package: thread.threadtongbu.threadsafe
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/10/14 22:52
 * @Version 1.0
 */
public class Window1Test {

    public static void main(String[] args) {
        Window1 w1 = new Window1("窗口一");
        Window1 w2 = new Window1("窗口二");
        Window1 w3 = new Window1("窗口三");

        w1.start();
        w2.start();
        w3.start();

    }
}
