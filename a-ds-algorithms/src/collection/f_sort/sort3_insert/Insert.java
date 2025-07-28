package collection.f_sort.sort3_insert;

/**
 * ClassName: Insert
 * Package: collection.f_sort.sort3_insert
 * Description:
 * 插入排序
 * 遍历数组，每遇到一个元素，按照顺序将其插入到前面的数据中
 *
 *
 * @Author jieHFUT
 * @Create 2025/7/28 15:38
 * @Version 1.0
 */
public class Insert {


    /**
     * 1.第二个元素开始遍历数组
     * 2.前面的位置已经是有序的列表
     * 3.将要插入的这个数据和有序列表中最后一个进行对比，如果要插入的数据比他小，有序列表最后一个数据后移
     * 4.否则就找到了要插入的位置
     * 5.如果有序列表全部后移，要插入的数据是最小的，直接插入到头部
     * @param array
     */
    public static void insert(int[] array) {
        for (int i = 1; i < array.length; i++) {
            // 遍历第二个元素到最后一个元素
            // 将后面的 n-1 个元素依次插入到前面的排序中
            int toInsert = array[i]; // 待插入数据
            // 前面的 i 个数据已经是有序的了
            int j = 0;

            for (j = i - 1; j > 0; j--) {
                // 从有序的最后一个数字开始判断，如果有序中这个数字大于 toInsert，有序数字后移
                if (array[j] > toInsert) {
                    array[j + 1] = array[j];
                } else {
                    // 否则就找到了要插入的位置
                    array[j + 1] = toInsert;
                    break;
                }
            }
            // 如果遍历结束，有序数列中所有的元素都比 toInsert 大，那就插入到第一个位置
            if (i <= 0) array[j] = toInsert;
        }
    }






}
