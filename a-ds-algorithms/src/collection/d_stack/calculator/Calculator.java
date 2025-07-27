package collection.d_stack.calculator;

import collection.d_stack.arraystack.ArrayStack;

import java.util.Scanner;

/**
 * ClassName: Calculator
 * Package: collection.d_stack
 * Description:
 * 这是使用栈来实现一个计算器的过程
 * 只包含 + - * /
 * 计算表达式形如：“7-3+2*3-6” = ?
 *
 * 升级版：如果出现多位数怎么考虑？
 *
 * @Author jieHFUT
 * @Create 2025/7/27 16:47
 * @Version 1.0
 */
public class Calculator {

    public static void main(String[] args) {
        System.out.println("请输入计算表达式：");
        Scanner sc = new Scanner(System.in);
        // 数值栈 符号栈
        /**
         * 1.遍历用户输入的字符串
         * 2.如果是数字就直接入数字栈
         * 3.如果是符号：
         *      (1) 如果符号栈为空就直接入栈
         *      (2) 如果符号栈不为空
         *             -> 如果当前的符号优先级小于等于栈顶的优先级，pop 出两个数字一个符号计算
         *             -> 如果当前的符号的优先级大于栈顶的优先级，接着入栈
         * 4.遍历完成后，就按序 pop出两个数字一个符号计算，直到数字栈中只剩下一个数字
         */
        String input = sc.nextLine(); // "7-3+2*3-6"
        ArrayStack numStack = new ArrayStack(10); // 数栈
        ArrayStack opStack = new ArrayStack(10);  // 符号栈
        int index = 0;
        while (true) {
            // 获取字符串的最前面的字符
            char ch = input.charAt(index);
            // 判断是数字还是运算符
            if (isOper(ch)) {
                // 是运算符
                if (!opStack.isEmpty() && priority(ch) <= priority(opStack.peek())) {
                    // 开始计算
                    numStack.push(cal(numStack.pop(), numStack.pop(), opStack.pop()));
                } else {
                    opStack.push(ch);
                }
            } else {
                // 是个位数 => 直接入栈
                numStack.push(ch);
            }
            index++;
            // 结束标志：如果下标等于字符串长度，遍历完成
            if (index == input.length()) break;
        }
        // 此时按序计算
        while (true) {

        }





    }

    //////////////////////////////////////////////////////////////////
    // 返回运算符的优先级别，优先级别是由程序员来确定，优先级使用数字来表示
    public static int priority(int oper) {
        if (oper == '+' || oper == '-') return 0;
        if (oper == '*' || oper == '/') return 1;
        return -1;
    }

    // 判断是不是一个运算符号
    public static boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    // 计算
    public static int cal(int num1, int num2, int oper) {
        if (oper == '+') return num2 + num1;
        if (oper == '*') return num2 * num1;
        if (oper == '/') return num2 / num1;
        if (oper == '-') return num2 - num1;
        return 0;

    }
}
