package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: LocalVar
 * Package: jvm
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/10/26 15:55
 * @Version 1.0
 */
public class LocalVar {

    public static String name = "zhansgan";

    public int age = 10;

    static {
        gender = "male";
    }

    public static String gender = "female";


    public LocalVar() {
        System.out.println("这是无参构造器");
    }

    public static List<String> hobbies() {
        List<String> ret = new ArrayList<>();
        ret.add("dance");
        return ret;
    }

    public int studyAge(String name) {
        int defaultAge = 2;
        List<String> hobbies = hobbies();
        return who(defaultAge);
    }

    public int who(int defaultAge) {
        return defaultAge + 3;
    }


    public static void main(String[] args) {
        LocalVar localVar = new LocalVar();
        System.out.println(localVar.studyAge("zhangsan"));
        System.out.println("hahahahha");
    }



}
