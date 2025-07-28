package collection.d_stack.poland;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * ClassName: Polandotation
 * Package: collection.d_stack.poland
 * Description:
 * 完成波兰表达式的各种操作
 * infixExpression：中缀表达式
 * suffixExpression：后缀表达式
 *
 *
 * 1.将中缀表达式转化为后缀表达式
 *     (1).中缀表达式转换为对应的 List 集合
 *     (2).中缀 List 集合转化为后缀 List 集合
 *     (3).后缀 List 集合转换为后缀表达式
 * 0.将中缀表达式转化为对应的集合
 * 1.将中缀表达式的集合转换为后缀表达式的集合
 * 2.将一个逆波兰表达式，依次把数据和运算符放入到 ArrayList 中
 * 3.完成对逆波兰表达式的运算
 *
 * @Author jieHFUT
 * @Create 2025/7/27 18:59
 * @Version 1.0
 */
public class PolandNotation {



    /**
     * 将中缀表达式转化为后缀表达式
     *
     * @param infixExpression 中缀表达式 "10+((2+3)×4)-5"
     * @return 后缀表达式的集合 List
     */
    public static List<String> midToBehind(String infixExpression) {

        return null;
    }

    /**
     * 将字符串（中缀或者后缀）转换为一个 List 集合
     * "10+((2+3)×4)-5" => List[10, +, (, (, 2, +, 3, ), *, 4, ), -, 5]
     * @param expression 字符串 "10+((2+3)×4)-5"
     * @return List[10, +, (, (, 2, +, 3, ), *, 4, ), -, 5]
     */
    public static List<String> expressionToList(String expression) {
        // 定义一个 List 对象，作为存储数据的返回值
        List<String> list = new ArrayList<>();
        int index = 0;
        while (true) {
            // 遍历字符串
            char ch = expression.charAt(index);
            if (ch < '0' || ch > '9') {
                // 该字符不是数字，直接添加到集合中
                list.add(String.valueOf(ch));
            } else {
                StringBuilder str = new StringBuilder();
                str.append(ch);
                // 该字符是数字，需要判断下一个字符是不是数字
                while (index < expression.length() - 1 && expression.charAt(index + 1) >= '0' && expression.charAt(index + 1) <= '9') {
                    str.append(expression.charAt(++index));
                }
                list.add(str.toString());
            }
            index++;
            if (index >= expression.length()) break;
        }
        return list;
    }


    /**
     * "10+((2+3)×4)-5"
     * 将一个中缀的 List 集合转化为一个后缀的 List 集合
     * @param infixExpression List[10, +, (, (, 2, +, 3, ), *, 4, ), -, 5]
     * @return suffixExpression List[10, 2, 3, +, 4, *, +, 5, -]
     *
     * 1.初始化两个栈：运算符栈s1和储存中间结果的栈s2；
     * 2.从左至右扫描中缀表达式；
     * 3.遇到数时，将其压s2；
     * 4.遇到运算符时，比较其与s1栈顶运算符的优先级：
     *     （1）如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
     *     （2）如果，若优先级比栈顶运算符的高，也将运算符压入s1；
     *     （3）否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4-1)与s1中新的栈顶运算符相比较；
     * 5.遇到括号时：
     *     （1）如果是左括号“(”，则直接压入s1
     *     （2）如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
     * 6.重复步骤2至5，直到表达式的最右边
     * 7.将s1中剩余的运算符依次弹出并压入s2
     * 8.依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
     */
    public static List<String> infixListToSuffixList(List<String> infixExpression) {
        Stack<String> numStack = new Stack<>(); // 存储数栈 s2
        Stack<String> operStack = new Stack<>();// 运算符栈 s1
        // 遍历前缀集合
        Iterator<String> iterator = infixExpression.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            // 如果是数字就直接入数栈
            if (str.matches("[0-9]*")) {
                numStack.push(str);
            } else if (str.equals("(")) {
                operStack.push(str);
            } else if (str.equals(")")) {
                // 右括号：一直弹出运算符压入数栈，扔掉括号 "(", ")"
                while (!operStack.peek().equals("(")) {
                    numStack.push(operStack.pop());
                }
                operStack.pop();
            } else {
                // 普通运算符（如果此时运算符的优先级小于等于栈顶的运算符，弹出运算符压入另外一个栈，直到栈顶的运算符优先级高于 str，然后str入栈）
                //           否则直接入栈
                while (!operStack.isEmpty() && !operStack.peek().equals("(") && Operation.getPriority(str) <= Operation.getPriority(operStack.peek())) {
                    numStack.push(operStack.pop());
                }
                operStack.push(str);
            }
        }
        while(!operStack.isEmpty()) {
            numStack.push(operStack.pop());
        }
        Stack<String> retStack = new Stack<>();
        while (!numStack.isEmpty()) {
            retStack.push(numStack.pop());
        }

        List<String> ret = new ArrayList<>();
        while (!retStack.isEmpty()) {
            ret.add(retStack.pop());
        }

        return ret;
    }

    /**
     * 将一个后缀表达式字符串转换为后缀的集合
     * @param suffixString "10 2 3 + 4 * + 5 -"
     * @return List[10, 2, 3, +, 4, *, +, 5, -]
     */
    public static List<String> suffixExpression(String suffixString) {
        String[] suffixArray = suffixString.split(" ");
        List<String> ret = new ArrayList<>();
        for (String s : suffixArray) {
            ret.add(s);
        }
        return ret;
    }

    /**
     * 完成对逆波兰表达式的运算
     * "10+((2+3)×4)-5" = 25
     * 方法：按序入栈，遇到运算符就弹出两个计算
     *
     * @param suffixExpression List[10, 2, 3, +, 4, ×, +, 5, –]
     * @return 25
     */
    public static int calculate(List<String> suffixExpression) {
        Stack<String> retStack = new Stack<>();
        // 遍历集合
        Iterator<String> iterator = suffixExpression.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            if (str.matches("[0-9]*")) {
                // 如果是数字就入栈
                retStack.push(str);
            } else {
                // 是符号就弹出计算
                retStack.push(String.valueOf(
                        Operation.cal(Integer.parseInt(retStack.pop()), Integer.parseInt(retStack.pop()), str.charAt(0))
                ));
            }
        }
        return Integer.parseInt(retStack.pop());
    }


}
