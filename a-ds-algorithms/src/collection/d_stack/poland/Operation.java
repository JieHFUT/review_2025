package collection.d_stack.poland;

/**
 * ClassName: Operation
 * Package: collection.d_stack.poland
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/7/27 18:59
 * @Version 1.0
 */
public class Operation {

    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;


    // 返回这些符号对应的优先级
    public static int getPriority(String operation) {
        int ret = 0;
        switch (operation) {
            case "+":
                ret = ADD;
                break;
            case "-":
                ret = SUB;
                break;
            case "*":
                ret = MUL;
                break;
            case "/":
                ret = DIV;
                break;
            default:
                System.out.println("不存在该运算符：" + operation);
                break;
        }
        return ret;
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
