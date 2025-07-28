package collection.f_sort.sort1_bubble;

/**
 * ClassName: Bubble
 * Package: collection.f_sort.sort1_bubble
 * Description:
 * 冒泡排序算法
 * 1.双循环进行排序
 * 2.改进
 * @Author jieHFUT
 * @Create 2025/7/28 14:56
 * @Version 1.0
 */
public class Bubble {

    // 将数组中两个位置的元素进行交换
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 1.双循环对数组进行排序
     *   每一个数字都遍历一遍先把最大的数字排到最后
     *   再遍历一遍将第二大的数字排到后面
     *
     *   n 个数字一共要遍历 n-1 遍
     * @param array
     */
    public static void bubble1(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            // 下面一次循环就将一个最大的数字放到最后
            for (int j = 0; j < array.length - i - 1 ; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }


    /**
     * 第一次改进：如果在内层循环中，某一次循环一次交换都没有发生，说明数组已经有序 => 直接结束
     * @param array
     */
    public static void bubble2(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            // 下面一次循环就将一个最大的数字放到最后
            boolean flag = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    flag = false;
                    swap(array, j, j + 1);
                }
            }
            if (flag) {
                break;
            } else {
                flag = true;
            }
        }
    }


    /**
     * 第二次改进：
     * @param array
     */
    public static void bubble3(int[] array) {

    }



}
