package collection.f_sort.sort2_select;

import java.text.Format;

/**
 * ClassName: Select
 * Package: collection.f_sort.sort2_select
 * Description:
 * 选择排序（下标 0 -> n-1）
 * 第一次从 0 -> n-1 中选取最小值与 0 交换
 * 第二次从 1 -> n-1 中选取最小值与 1 交换
 * 第三次从 2 -> n-1 中选取最小值与 2 交换
 * @Author jieHFUT
 * @Create 2025/7/28 15:27
 * @Version 1.0
 */
public class Select {

    // 将数组中两个位置的元素进行交换
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 选择排序
     * 遍历选择最小的后，和最前面的进行交换
     */
    public static void select(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            // 每一次都和下标为 i 的元素进行交换
            int minIndex = i;
            for (int j = i; j < array.length - 1; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }
    }
}
