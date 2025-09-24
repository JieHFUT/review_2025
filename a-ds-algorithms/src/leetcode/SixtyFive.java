package leetcode;

/**
 * ClassName: SixtyFive
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/8/27 16:01
 * @Version 1.0
 */
public class SixtyFive {
    public static void main(String[] args) {
        System.out.println(isNumber("0"));
    }


    public static boolean isNumber(String s) {
        if (s == null || s.length() == 0) return false;
        // 数字的定义有两个类型（整数加可选指数 || 十进制数加可选指数）

        boolean isContain = false;
        // 先遍历一遍看字符串是否包含可选指数 (如果包含的话记录 e/E 的下标)
        int i = 0;
        for (i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'e' || s.charAt(i) == 'E') {
                // 包含指数
                isContain = true;
                break;
            }
        }


        if (isContain) {
            // 包含指数，e 的下标是 i
            String prev = s.substring(0, i);
            String next = s.substring(i);

            return (isInteger(prev) || isDicimal(prev)) && isIndex(next);
        } else {
            // 不包含指数（必须是一个整数或者十进制数）
            return isInteger(s) || isDicimal(s);
        }

    }


    // 判断是不是一个整数（可选符号 '-' 或 '+' 后面跟着 数字）
    public static boolean isInteger(String str) {
        if (str == null || str.length() == 0) return false;
        if (str.length() == 1 && (str.charAt(0) == '+' || str.charAt(0) == '-')) return false;
        // 判断第一个是加减号或者数字就后移，否则就为假
        char ch = str.charAt(0);
        if (ch == '+' || ch == '-' || Character.isDigit(ch)) {
            // 第一个符号满足条件
            for (int i = 1; i < str.length(); i++) {
                // 剩下的需要全部是数字才可以
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }


    // 判断是不是一个十进制数（可选符号 '-' 或 '+' 后面跟着  354.||.231||143.21）
    public static boolean isDicimal(String str) {
        if (str == null || str.length() == 0) return false;
        if (str.charAt(0) == '-' || str.charAt(0) == '+') {
            return isDicimal2(str.substring(1));
        } else {
            return isDicimal2(str);
        }
    }

    public static boolean isDicimal2(String str) {
        if (str == null || str.length() == 0) return false;
        // 判断字符串是不是纯纯 354.||.231||143.21
        if (str.length() == 1 && str.charAt(0) == '.') return false;
        int count = 0; // 记录点的数量
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isDigit(ch) || ch == '.') {
                // 如果有两个及其以上的点也是 false，或者没有点也是 false
                if (ch == '.') count++;
                if (count >= 2) return false;
            } else {
                return false;
            }
        }
        if (count == 1) {
            return true;
        }
        return false;
    }


    // 判断是不是一个指数（e/E 后面跟着一个整数）
    public static boolean isIndex(String str) {
        if (str == null || str.length() == 0) return false;
        char ch = str.charAt(0);
        if (ch == 'e' || ch == 'E') {
            if (!isInteger(str.substring(1))) {
                // 后面不是整数
                return false;
            } else {
                return true;
            }
        } else {
            // 开头不是 E/e
            return false;
        }
    }



}
