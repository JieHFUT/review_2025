package thread.waitandnotify_5.printnumber;

/**
 * ClassName: printNumberTest
 * Package: thread.waitandnotify_5.printnumber
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/7/24 15:32
 * @Version 1.0
 */
public class printNumberTest {
    public static void main(String[] args) {


        PrintNumber printNumber = new PrintNumber();

        Thread t1 = new Thread(printNumber, "first");
        Thread t2 = new Thread(printNumber, "second");

        t1.start();
        t2.start();



    }
}
