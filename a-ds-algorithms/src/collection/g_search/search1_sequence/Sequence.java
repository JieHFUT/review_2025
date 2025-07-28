package collection.g_search.search1_sequence;

/**
 * ClassName: Sequence
 * Package: collection.g_search.search1_sequence
 * Description:
 * 按序搜索：时间复杂度 n
 * 顺序查找：找到一个满足条件的值即可（返回其下标）
 *
 * @Author jieHFUT
 * @Create 2025/7/28 21:39
 * @Version 1.0
 */
public class Sequence {


    /**
     *
     * @param array 在数组中寻找目标值，找不到就返回 -1
     * @param target 目标
     * @return 目标值的下标
     */
    public static int sequence(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
