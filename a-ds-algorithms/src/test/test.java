package test;

import java.util.*;

/**
 * ClassName: test.test
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/8/4 8:09
 * @Version 1.0
 */
public class test {

    public static void main(String[] args) {

//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list.add(5);
//        list.add(6);
//        System.out.println(list);
//
//        System.out.println("0123456789".substring(1)); // 123456789
//        System.out.println("0123456789".substring(1, 9)); // 12345678
//        "weiohf".length();
//        StringBuilder ret = new StringBuilder();
//        System.out.println(" +24353".trim());
//        Character.isDigit('t');
//        char ch = '8' - '0';
//        System.out.println("ch = " + ch);
//
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Integer.MIN_VALUE);
//
//        int digit = '9' - '0';
//        System.out.println("digit = " + digit);
//        System.out.println((char) digit);
//        System.out.println(Math.abs(-5));
//
//        String str;
//        str = "swqd";
//        System.out.println(str);
//
//
//        List<String> strs1 = new ArrayList<>();
//        List<String> strs2 = new ArrayList<>();
//        strs2.add("1");
//        strs2.add("2");
//        strs2.add("3");
//        System.out.println("strs1.size() = " + strs1.size());
//        List<String> strs3 = new ArrayList<>();
//        for (String str1 : strs1) {
//            for (String str2 : strs2) {
//                strs3.add(str1 + str2);
//            }
//        }
//        System.out.println("strs3.size() = " + strs3.size());

//        String[] strss = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
//        for (String s : strss) {
//            System.out.println(s);
//        }

//        Stack<Integer> stack = new Stack<>();
//        int[] point = new int[2];
//        Arrays.fill(point, 0);
//
//        int[] nums = {1,2,4,2315,4,36,457,65,87};
//        Arrays.sort(nums);

        System.out.println(Integer.MIN_VALUE);
        Stack stack = new Stack();

        Queue<Character> queue = new LinkedList<>();

    }

    public int romanToInt(String s) {
        // 给定一个罗马数字，将其转换为整数 (1<=s.length<=15)
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        // 遍历字符串 s，优先判断前两个字符是否符合，然后再判断一个字符是否符合
        int compareIndex = 0; // 比较的下标走到哪里了（上一次哪一个符合成功了，就遍历到哪个位置）
        int ret = 0;
        String str;
        while (s.length() >= 2) {
            str = new String(new char[]{s.charAt(0), s.charAt(1)});
            str = new StringBuilder().append(s.charAt(0)).toString();

        }
        return 0;
    }



}
