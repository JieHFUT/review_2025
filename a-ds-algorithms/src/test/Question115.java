package test;

/**
 * ClassName: Question115
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/9/18 15:35
 * @Version 1.0
 */
public class Question115 {

    public static void main(String[] args) {
        System.out.println(numDistinct(
                "adbdadeecadeadeccaeaabdabdbcdabddddabcaaadbabaaedeeddeaeebcdeabcaaaeeaeeabcddcebddebeebedaecccbdcbcedbdaeaedcdebeecdaaedaacadbdccabddaddacdddc",
                "bcddceeeebecbc"));
    }


    public static int numDistinct(String s, String t) {
        // 统计次数
        if (s.length() == 0 && t.length() == 0) return 1;
        if (t.length() == 0) return 1;
        if (s.length() == 0) return 0;

        char ch = t.charAt(0);

        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ch) {
                int onetype = numDistinct(s.substring(i + 1), t.substring(1));
                sum += onetype;
            }
        }

        return sum;
    }
}
