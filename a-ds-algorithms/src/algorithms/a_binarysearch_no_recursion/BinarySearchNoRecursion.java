package algorithms.a_binarysearch_no_recursion;

/**
 * ClassName: BinarySearchNoRecursion
 * Package: algorithms.a_binarysearch_no_recursion
 * Description:
 *
 * 二分查找不使用递归
 *
 *
 * @Author jieHFUT
 * @Create 2025/7/31 23:35
 * @Version 1.0
 */
public class BinarySearchNoRecursion {

    /**
     * 如果找到了就返回下标，否则返回 -1
     * @param arr
     * @param target
     * @return
     */
    public int binarySearchNoRecursion(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int mid;

        while (left <= right) {
            mid = (left + right) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

}
