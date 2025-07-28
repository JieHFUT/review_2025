package collection.g_search.search3_differ;

/**
 * ClassName: DifferAlloc
 * Package: collection.g_search.search3_differ
 * Description:
 * 差值查找，按照比重分配进行查询（也要要求数组已经是有序的）
 * 和二分查找的区别在于不再是简简单单从中间分开
 * 而是对 mid 这个值进行处理
 *                                    target - array[left]
 * mid = left + (right - left) * ——————————————————————————————
 *                                 array[right] - array[left]
 *
 * @Author jieHFUT
 * @Create 2025/7/28 21:58
 * @Version 1.0
 */
public class DifferAlloc {

    /**
     * 假设数组 array 已经按照从小到大的顺序进行排序
     * @param array
     * @param target
     * @return
     */
    public static int differAlloc(int[] array, int target) {

        if (target > array[array.length - 1] || target < array[0])
            return -1;

        return differAlloc(array, 0, array.length - 1, target);
    }

    /**
     *
     * @param array
     * @param start
     * @param end
     * @param target
     * @return 找到了返回下标值
     */
    public static int differAlloc(int[] array, int start, int end, int target) {

        if (start > end) return -1;

        int mid = start + (end - start) * (target - array[start]) / (array[end] - array[start]);
        // 比较目标值和中间的数据大小
        if (array[mid] == target) {
            // 找到了
            return mid;
        } else if (array[mid] > target) {
            return differAlloc(array, start, mid - 1, target);
        } else {
            return differAlloc(array, mid + 1, end, target);
        }

    }


}
