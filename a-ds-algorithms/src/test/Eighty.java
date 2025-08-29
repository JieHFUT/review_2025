package test;

/**
 * ClassName: Eighty
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/8/29 11:15
 * @Version 1.0
 */
public class Eighty {

    public static void main(String[] args) {

        int[] nums = new int[]{0,0,1,1,2,2,2,3,4,5,5,6,6,6,6,7,8,8};
        System.out.println(removeDuplicates(nums));

    }


    public static int removeDuplicates(int[] nums) {
        // 有序 0 0 0 1 1 2 3 4 5 5 5 6 6 7 8 8 8 8
        // 思路：先遍历一遍将多余的数字置为 Integer.MIN_VALUE
        // 思路：然后再次遍历并且记录 Integer.MIN_VALUE 的数量 n，后面的元素依次向左移动 n
        int repeat = 0;
        for (int i = 0; i < nums.length; i++) {
            int key = nums[i];
            int count = 1;
            while (i + 1 < nums.length && nums[i + 1] == key) {
                if (count >= 2) {
                    nums[i + 1] = Integer.MIN_VALUE;
                    repeat++;
                }
                i++;
                count++;
            }
        }

        // 每一个元素要向前移动的距离
        int tomove = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == Integer.MIN_VALUE) {
                tomove++;
            } else {
                nums[i - tomove] = nums[i];
            }
        }


        return nums.length - repeat;
    }
}
