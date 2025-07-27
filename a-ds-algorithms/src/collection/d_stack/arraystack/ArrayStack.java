package collection.d_stack.arraystack;

/**
 * ClassName: ArrayStack
 * Package: collection.d_stack
 * Description:
 * 使用 数组 来模拟实现一个栈
 * @Author jieHFUT
 * @Create 2025/7/27 16:20
 * @Version 1.0
 */
public class ArrayStack {

    private int maxSize;  // 用于记录栈的大小
    private int top = -1; // 用于记录栈顶元素的索引位置
    private int[] stack;  // 底层数组

    // 构造方法
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }


    // 判断栈是否已经满了
    public boolean isFull() {
        return top == maxSize - 1;
    }
    // 判断栈是否是空的
    public boolean isEmpty() {
        return top == -1;
    }

    // 入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("Stack is full");
            return;
        }
        stack[++top] = value;
    }

    // 出栈
    public int pop() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return stack[top--];
    }

    // 显示栈的情况（遍历栈），遍历的时候需要从栈顶开始显示数据
    public void list() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        int count = top;
        while (count >= 0) {
            System.out.printf("stack[%d] = %d\n", count, stack[count--]);
        }
        System.out.println();
    }

    // 返回当前栈顶的值，但是不出栈
    public int peek() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return stack[top];
    }

}
