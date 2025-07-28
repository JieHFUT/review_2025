package collection.g_search.search2_binary;

/**
 * ClassName: Binary
 * Package: collection.g_search.search2_binary
 * Description:
 * 二分搜索：时间复杂度 log(n)
 * 二分查找需要使用已经排序完成的数组
 *
 * @Author jieHFUT
 * @Create 2025/7/28 21:42
 * @Version 1.0
 */
public class Binary {


    /**
     * 假设数组 array 已经按照从小到大的顺序进行排序
     * @param array
     * @param target
     * @return
     */
    public static int binary(int[] array, int target) {

        if (target > array[array.length - 1] || target < array[0])
            return -1;

        return binary(array, 0, array.length - 1, target);
    }

    /**
     *
     * @param array
     * @param start
     * @param end
     * @param target
     * @return 找到了返回下标值
     */
    public static int binary(int[] array, int start, int end, int target) {

        if (start > end) return -1;

        int mid = start + (end - start) / 2;
        // 比较目标值和中间的数据大小
        if (array[mid] == target) {
            // 找到了
            return mid;
        } else if (array[mid] > target) {
            return binary(array, start, mid - 1, target);
        } else {
            return binary(array, mid + 1, end, target);
        }

    }

}
