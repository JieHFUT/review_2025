package jdk17.lambda;

import org.junit.Test;

import java.util.Comparator;

/**
 * ClassName: ATest
 * Package: jdk17.lambda
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/9/25 17:40
 * @Version 1.0
 */
public class ATest {

    public static void main(String[] args) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("重写方法");
            }
        };
        runnable.run();


        // 对上面的进行 lambda 写法
        // Lambda 是一个**匿名函数**，我们可以把 Lambda 表达式理解为是一段可以传递的代码（将代码像数据一样进行传递）
        // 使用它可以写出更简洁、更灵活的代码。作为一种更紧凑的代码风格，使Java的语言表达能力得到了提升。

        // 也就是匿名中对于确定要写的内容就将其省略
        Runnable runnable1 = () -> {
            System.out.println("重写方法");
        };
        runnable1.run();

    }


    @Test
    public void testComparator(){
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
        System.out.println(comparator.compare(1, 2));

        // 下面是 lambda 写法
        Comparator<Integer> comparator1 = (Integer o1, Integer o2) -> {
            return o1.compareTo(o2);
        };
        System.out.println(comparator1.compare(1, 2));
        // 更加简写
        Comparator<Integer> comparator2 = (o1, o2) -> Integer.compare(o1, o2);
    }


    @Test
    public void testFuncation(){
        // 方法引用
        Comparator<Integer> comparator = Integer::compare;
        System.out.println(comparator.compare(1, 2));
    }




}
