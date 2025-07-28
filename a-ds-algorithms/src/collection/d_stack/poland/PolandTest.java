package collection.d_stack.poland;

import org.junit.Test;

import java.util.List;
import java.util.Scanner;

/**
 * ClassName: Test
 * Package: collection.d_stack.poland
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/7/27 19:09
 * @Version 1.0
 */
public class PolandTest {
    public static void main(String[] args) {

        // 将字符串（中缀或者后缀）转换为一个 List 集合
        List<String> expressionToList = PolandNotation.expressionToList("10+((2+3)*4)-5");
        System.out.println(expressionToList); // [10, +, (, (, 2, +, 3, ), *, 4, ), -, 5]

        // 将一个中缀的 List 集合转化为一个后缀的 List 集合
        List<String> suffixList = PolandNotation.infixListToSuffixList(expressionToList);
        System.out.println(suffixList); // [10, 2, 3, +, 4, *, +, 5, -]

        // 完成对逆波兰表达式的运算
        int calculate = PolandNotation.calculate(suffixList);
        System.out.println(calculate);

    }

}
