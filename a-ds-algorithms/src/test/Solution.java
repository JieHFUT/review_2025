package test;

import java.util.*;

/**
 * ClassName: test.Solution
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/8/6 3:30
 * @Version 1.0
 */
class Solution {
    public static void main(String[] args) {
        // System.out.println(isMatch("aab", "c*a*b"));
        // System.out.println(maxArea(new int[]{2,3,4,5,18,17,6}));
        // jisuan(21);
//        List<String> strings = letterCombinations("23");
//        for (String s : strings) {
//            System.out.println(s);
//        }
//        mergeKLists1(new ListNode[]{
//                new ListNode(1, new ListNode(4, new ListNode(5, null))),
//                new ListNode(1, new ListNode(3, new ListNode(4, null))),
//                new ListNode(2, new ListNode(6, null))
//        });

//        ListNode ret =  reverseKGroup(new ListNode(1,
//                                            new ListNode(2,
//                                                    new ListNode(3,
//                                                            new ListNode(4,
//                                                                    new ListNode(5, null))))), 3);
//        int i = removeElement(new int[]{2}, 3);
//        System.out.println(i);

//        int[] next = getNext("abcdadca");
//        System.out.println(Arrays.toString(next));

        int[] next = getNext("abaababab");
        System.out.println(Arrays.toString(next));

        int index = strStr("mississippi", "issip");
        System.out.println(index);


    }


    public static int strStr(String haystack, String needle) {
        // 手撕 KMP 算法
        int[] next = getNext(needle);
        // 每次遇到匹配不成功的需要前进的程度为：前面已经匹配的数量 - 其匹配值
        for (int i = 0; i < haystack.length(); ) {
            int j = 0;
            for (j = 0; j < needle.length(); j++) {
                // 如果越界了但是 needle 没有到尽头
                if (i + j == haystack.length() && j != needle.length()) {
                    return -1;
                }

                if (haystack.charAt(i) != needle.charAt(0)) {
                    i++;
                    break;
                }
                if (i+j < haystack.length() && haystack.charAt(i+j) != needle.charAt(j)) {
                    i += j-next[j-1];
                    break;
                }
            }
            if (j == needle.length()) {
                // 表示这一次完全匹配成功
                return i;
            }
        }
        return -1;
    }

    // 获取字符串 needle 的 next[] 数组
    public static int[] getNext(String needle) {
        //
        int[] ret = new int[needle.length()];
        int index = 0;

        for (int i = 0; i < needle.length(); i++) {
            if (i == 0) {
                ret[i] = 0;
            } else {
                if (needle.charAt(i) == needle.charAt(index)) {
                    ret[i] = ret[i-1] + 1;
                    index++;
                } else if(needle.charAt(i) == needle.charAt(0)) {
                    ret[i] = 1;
                    index = 1;
                }
            }
        }
        return ret;
    }

    public static int removeElement(int[] nums, int val) {
        // 思路：前后指针，前面找 val，后面找非 val，进行交换
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int left = 0;
        int right = len - 1;


        while(left < right) {

            while(left < len && nums[left] != val) {
                left++;
            }
            if (left == len) {
                return len;
            }
            while(right > -1 && nums[right] == val) {
                right--;
            }
            if (left > right) {
                break;
            }
            // 进行交换
            swap(nums, left, right);
        }
        // 交换完成了

        int count = 0;
        int index = len - 1;
        int record = nums[index];
        while(index > -1 && nums[index] == record) {
            count++;
            index--;
        }
        return len - count;

    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) return head;
        // 思路：持续翻转，每次翻转 K 个以后，就以最后一个节点为新的开始节点再翻转 K 个

        // 先创建一个指针先走 K-1 个，如果走不到就直接返回，走到了就与遍历同时走，
        // 如果这一组翻转完成期间，先走的指针.next 指到空的了就不要再继续了
        ListNode advance = head;
        for (int i = 1; i < k; i++) {
            advance = advance.next;
            if (advance == null) {
                return head;
            }
        }

        ListNode newHead = new ListNode();
        ListNode ret = newHead;

        int count = 0; // 记录翻转了几个
        boolean flag = true; // 记录下一轮 K 个要不要翻转
        while (flag) {

            for (int j = 0; j < k; j++) {
                // 应该再翻转之前进行判断
                if (advance.next == null) {
                    flag = false;
                } else {
                    advance = advance.next;
                }

                // 一轮翻转 K 个
                ListNode temp = head.next;
                head.next = newHead.next;
                newHead.next = head;
                head = temp;
            }
            // 移动 newHead 到最后一个节点再来一遍
            for (int m = 0; m < k; m++) {
                newHead = newHead.next;
            }
        }

        // 到这里还有最后一个不足 K 的需要添加到末尾
        newHead.next = head;

        return ret.next;
    }

    public static ListNode mergeKLists1(ListNode[] lists) {
        // 使用小顶堆来实现
        Queue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        // 把各个链表的第一个元素，也就是头节点放进去了，这些头节点组成了一个小顶堆
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                queue.add(lists[i]);
            }
        }

        ListNode head = new ListNode();
        ListNode r = head; // 记录
        while(!queue.isEmpty()){
            // 将最小的出优先级队列
            ListNode temp = queue.poll();
            // 如果这个最小节点还有后续节点，就将其加入最小堆
            if(temp.next != null)
                queue.offer(temp.next);
            r.next = temp;
            r = r.next;
            r.next = null;
        }
        return head.next;


    }

    public static ListNode mergeKLists(ListNode[] lists) {
        // 取 n 个指针，然后遍历到头的令其值为 Integer.MAX_VALUE，如果某一次最小值为 Integer.MAX_VALUE
        ListNode[] point = new ListNode[lists.length];
        // 赋值所有的指针
        for (int i = 0; i < lists.length; i++) {
            point[i] = lists[i];
        }
        ListNode ret = new ListNode();
        while (true) {
            // 记录最小值的指针下标
            int index = -1;
            int minValue = Integer.MAX_VALUE;
            // 遍历各个指针，如果不是 null 就参与比较
            for (int j = 0; j < point.length; j++) {
                if (point[j] != null && point[j].val < minValue) {
                    minValue = point[j].val;
                    index = j;
                }
            }

            // 判断是否结束
            if (index == -1) {
                break;
            } else {
                // 进行返回集合的拼接和最小指针的移动
                ListNode minNext = point[index].next;
                point[index].next = ret.next;
                ret.next = point[index];
                point[index] = minNext;
            }
        }
        // 结束了，需要对集合 ret 进行反转
        return reverse(ret.next);
    }

    // 对单链表进行反转
    public static ListNode reverse(ListNode node) {
        // 思路：使用双指针
        if (node == null || node.next == null) return node;
        ListNode advance = node.next;
        ListNode after = node;
        after.next = null;

        while (advance != null) {
            // 记录下一个
            ListNode temp = advance.next;
            advance.next = after;
            after = advance;
            advance = temp;
        }
        return after;
    }

    static List<String> ret = new ArrayList();

    static HashMap<Integer, String[]> values = new HashMap(){
        {
            put(2, new String[]{"a","b","c"});
            put(3, new String[]{"d","e","f"});
            put(4, new String[]{"g","h","i"});
            put(5, new String[]{"j","k","l"});
            put(6, new String[]{"m","n","o"});
            put(7, new String[]{"p","q","r","s"});
            put(8, new String[]{"t","u","v"});
            put(9, new String[]{"w","x","y","z"});
        }
    };

    // 方法三（DFS-深度优先遍历）
    public static List<String> letterCombinations(String digits) {
        dfs(digits, new StringBuilder(), 0);
        return ret;
    }
    /**
     * 深度优先遍历：
     * String digits 原始字符串数字
     * StringBuilder sb
     * int index 遍历的第几个数字
     */
    public static void dfs(String digits, StringBuilder sb, int index) {
        if (digits == null || digits.length() == 0) {
            return;
        }
        if (index == digits.length()) {
            // 上一轮遍历到最后一个数字了
            ret.add(sb.toString());
            return;
        }
        String[] cur = values.get(digits.charAt(index) - '0');
        for (int i = 0; i < cur.length; i ++) {
            sb.append(cur[i]);
            dfs(digits, sb, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static List<String> letterCombinations2(String digits) {
        List<String> ret = new ArrayList();
        if (digits.length() == 0) return ret;

        HashMap<Integer, String[]> values = new HashMap();
        values.put(2, new String[]{"a","b","c"});
        values.put(3, new String[]{"d","e","f"});
        values.put(4, new String[]{"g","h","i"});
        values.put(5, new String[]{"j","k","l"});
        values.put(6, new String[]{"m","n","o"});
        values.put(7, new String[]{"p","q","r","s"});
        values.put(8, new String[]{"t","u","v"});
        values.put(9, new String[]{"w","x","y","z"});
        for (String str : values.get(digits.charAt(0) - '0')) {
            ret.add(str);
        }
        if (digits.length() == 1) return ret;

        // 在这里最少有一个数字
        // 记录目前要添加的是第几个数字的下标
        int index = 1;
        backtrack(ret, values, digits, index);
        return ret;
    }
    /**
     * List<String> prev 前面所有的数字组成的字符串集合
     * HashMap<Integer, String[]> values 数字与字符之间的对应关系
     * String digits 原始的字符串数字
     * int index 当前是第几个数字
     * 教训：只有回溯的终结条件可以开始直接返回，初始就成立的不能直接返回
     */
    public static void backtrack(List<String> prev, HashMap<Integer, String[]> values, String digits, int index) {
        if (index == digits.length()) {
            return;
        }
        // 添加了这个数字之后的集合
        List<String> nowList = new ArrayList();

        for (String prevStr : prev) {
            for (String nowStr : values.get(digits.charAt(index) - '0')) {
                nowList.add(prevStr + nowStr);
            }
        }
        backtrack (nowList, values, digits, index + 1);

        // 不能重新赋值，需要添加值
        prev.clear();
        for (String nowStr : nowList) {
            prev.add(nowStr);
        }

    }

    public static List<String> letterCombinations1(String digits) {
        // 返回的集合
        int len = digits.length();
        List<String> ret = new ArrayList();

        if (len == 0) return ret;

        HashMap<Integer, String[]> values = new HashMap();
        values.put(2, new String[]{"a","b","c"});
        values.put(3, new String[]{"d","e","f"});
        values.put(4, new String[]{"g","h","i"});
        values.put(5, new String[]{"j","k","l"});
        values.put(6, new String[]{"m","n","o"});
        values.put(7, new String[]{"p","q","r","s"});
        values.put(8, new String[]{"t","u","v"});
        values.put(9, new String[]{"w","x","y","z"});
        System.out.println(digits.charAt(0));
        String[] strs = values.get(digits.charAt(0));
        for (String str : strs) {
            System.out.println(str);
        }

        ret = Arrays.asList(values.get(digits.charAt(0)));

        if (len == 1) return ret;
        int i = 1; // 记录遍历到哪一个数字的下标了
        while(i < len) {
            List<String> temp = new ArrayList();
            // 如果是两个或者两个以上的数字，从第二个数字开始整理（开始的时候 ret 中已经有第一个数字的字符串集合了）
            for(String prev : ret) {
                // 前面数字的所有组成的字符串，考虑每一个字符串都加上一遍这个数字的
                for(String now : values.get(digits.charAt(i))) {
                    temp.add(prev + now);
                }
            }
            ret = temp;
            i++;
        }
        return ret;
    }


    public static void jisuan(int num) {
        int ge = num % 10;
        int shi = num % 100 / 10;
        int bai = num % 1000 / 100;
        int qian = num / 10000;
        System.out.println(ge);
        System.out.println(shi);
        System.out.println(bai);
        System.out.println(qian);
    }
    public static int maxArea(int[] height) {
        // 方法二（算法思路有问题，部分最后不是最大值）：先计算最边上的两个，然后依次向里面
        // （如果高度比两头都低就不看，高度大于较矮的那个就计算比较一下三者围成的三个面积）
        int left = 0;
        int right = height.length - 1;
        int k = 0; // left && right 是用来记录最大图形的两端下标，k 是来遍历的

        int maxArea = Math.min(height[left], height[right]) * (right - left);

        for(k = 1; k < height.length && k != left && k != right; k++) {
            // 比较三者值
            int area2 = Math.min(height[left], height[k]) * Math.abs(k - left);
            int area3 = Math.min(height[k], height[right]) * Math.abs(right - k);
            if (maxArea >= area2 && maxArea > area3) {
                continue;
            } else if (area2 > maxArea && area2 > area3) {
                right = k;
                maxArea = area2;
            } else {
                left = k;
                maxArea = area3;
            }
        }
        return maxArea;
    }
    public static boolean isMatch(String s, String p) {

        // 思路：双指针，依次移动
        int points = 0;
        int pointp = 0;
        char prev;

        // 第一个字符不可能是 '*'
        if (p.charAt(pointp) == '.') {
            pointp++;
            points++;
            prev = '.';
        } else {
            if (s.charAt(points) != p.charAt(pointp))
                return false;
            prev = s.charAt(points);
            pointp++;
            points++;
        }

        while (points < s.length() && pointp < p.length()) {
            // 先从 p 开始，如果 p 的字符是 '.'，两个指针都加一
            // 如果 p 的字符是 '*', 就判断 s 是否和 p 的前一个字符相同
            // （如果不相同就返回 false，否则就比较到不相同后，p 指针加一后再重新比较）
            if (p.charAt(pointp) == '.') {
                pointp++;
                points++;
                prev = '.';
            } else if (p.charAt(pointp) == '*') {
                if (prev == '.')
                    return true;
                if (s.charAt(points) != prev)
                    return false;
                // 一致比较，知道 s 不为 prev
                while (points < s.length() && s.charAt(points) == prev) {
                    points++;
                }
                pointp++;
            } else {
                // p 就是普通字符
                if (s.charAt(points) != p.charAt(pointp))
                    return false;
                prev = s.charAt(points);
                pointp++;
                points++;
            }
        }
        if (points == s.length() && pointp == p.length())
            return true;
        return false;

    }
}