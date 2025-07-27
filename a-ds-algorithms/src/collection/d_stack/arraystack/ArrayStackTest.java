package collection.d_stack.arraystack;

/**
 * ClassName: ArrayStackTest
 * Package: collection.d_stack.arraystack
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/7/27 17:07
 * @Version 1.0
 */
public class ArrayStackTest {
    public static void main(String[] args) {

        ArrayStack stack = new ArrayStack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);

        stack.list();
        System.out.println(stack.isFull());
        int peek = stack.peek();
        System.out.println(peek);
        stack.pop();
        System.out.println(stack.peek());
    }
}
