package collection.f_sort.sort4_shell;

/**
 * ClassName: Shell
 * Package: collection.f_sort.sort4_shell
 * Description:
 * 希尔排序（是对简单插入排序的优化）也称之为缩小增量排序
 * 1.希尔排序本质上是进行分组后再排序
 * 2.分组后对每一组的排序方法有两种
 *     (1).对每一组使用插入法进行排序
 *     (2).对每一组使用交换法进行排序
 *
 * @Author jieHFUT
 * @Create 2025/7/28 16:59
 * @Version 1.0
 */
public class Shell {


    // 将数组中两个位置的元素进行交换
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }



    /**
     * 希尔排序 => 使用交换法
     * 1.先将数组分为 array.length / 2 组，排序后
     * 2.再将数组分为 array.length / 2 / 2 组
     * 3.,......
     *
     * [0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16]
     * 相当于对两个数组进行交换排序，不过下标是依次减二
     * [0 2 4 6 8 10 12 14 16]
     * [1 3 5 7 9 11 13 15]
     * @param array
     */
    public static void shell(int[] array) {

        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            // 对每一组进行排序
            for (int i = gap; i < array.length; i++) {
                // 此时需要判断下标为 i 的元素和前面有序列表的大小
                // 遍历前面有序列表，前面有序列表需要从后面和 i 比
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (array[j] > array[j + gap]) {
                        swap(array, j, j + gap);
                    }
                }
            }
        }
    }


    /**
     * 希尔排序 => 使用插入法
     * 1.先将数组分为 array.length / 2 组，排序后
     * 2.再将数组分为 array.length / 2 / 2 组
     * 3.,......
     *
     * [0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16]
     * 相当于对两个数组进行插入排序，不过下标是依次减二
     * [0 2 4 6 8 10 12 14 16]
     * [1 3 5 7 9 11 13 15]
     * @param array
     */
    public static void shell1(int[] array) {

        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            // 对每一组进行排序
            for (int i = gap; i < array.length; i++) {
                // 记录待插入值
                int toInsert = array[i];
                // 对前面的有序数组进行从后往前的遍历，如果大于待插入值就后移
                int j = 0;
                for (j = i - gap; j >= 0; j -= gap) {
                    if (array[j] > toInsert) {
                        array[j + gap] = array[j];
                    } else {
                        // 找到了待插入值的位置
                        array[j + gap] = toInsert;
                        break;
                    }
                }
                // 如果从后往前遍历完，有序数组全部大于 toInsert
                if (j < 0) {
                    array[j + gap] = toInsert;
                }
            }
        }
    }





}
