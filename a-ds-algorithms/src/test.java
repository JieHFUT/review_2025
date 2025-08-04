import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: test
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/8/4 8:09
 * @Version 1.0
 */
public class test {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        System.out.println(list);

        System.out.println("0123456789".substring(1)); // 123456789
        System.out.println("0123456789".substring(1, 9)); // 12345678
        "weiohf".length();
        StringBuilder ret = new StringBuilder();
        System.out.println(" +24353".trim());
        Character.isDigit('t');
        char ch = '8' - '0';
        System.out.println("ch = " + ch);

    }
}
