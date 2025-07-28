package collection.f_sort.sort5_quick;

/**
 * ClassName: Quick
 * Package: collection.f_sort.sort5_quick
 * Description:
 * 快速排序：属于对冒泡排序的优化升级版本
 *
 * 1.选取一个基准 pivot
 * 2.将数据分为两部分（小于基准的在左侧，大于基准的在右测）
 * 3.接着对左右递归
 *
 * @Author jieHFUT
 * @Create 2025/7/28 17:43
 * @Version 1.0
 */
public class Quick {


    // 将数组中两个位置的元素进行交换
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    /**
     *
     * @param arr
     * @param leftKey 该数组左侧下标
     * @param rightKey 该数组右侧下标
     */
    public static void quickSort(int[] arr, int leftKey, int rightKey) {
        // 选取基准
        int left = leftKey;
        int right = rightKey;

        int pivot = (left + right) / 2;
        int povitNum = arr[pivot];
        // 遍历数组，比基准小的放在左边，比基准大的放在右边
        // 也就是 left 向右移动，right 向左移动 （左边大于基准，右边小于基准 => 交换）
        while (left < right) {
            while (arr[left] < povitNum)
                left++;
            while (arr[right] > povitNum)
                right--;

            if (right <= left) break; // 一轮排序结束
            // 在这里需要对左右进行数据交换
            swap(arr, left, right);

            if (arr[left] == povitNum) right--;
            if (arr[right] == povitNum) left++;
        }

        // 考虑到如果 left == right, 下一轮无法分开
        if (left == right) {
            left++;
            right--;
        }
        // 对左边比基准小的再次排序
        if (leftKey < right)
            quickSort(arr, leftKey, right);
        // 对右边比基准大的再次排序
        if (left < rightKey)
            quickSort(arr, left, rightKey);


    }





}
