package collection.f_sort.sort7_radix;

/**
 * ClassName: Radix
 * Package: collection.f_sort.sort7_radix
 * Description:
 * 桶排序 (bucket sort)，也叫做基数排序
 *
 * @Author jieHFUT
 * @Create 2025/7/28 19:18
 * @Version 1.0
 */
public class Radix {


    /**
     * 原理是按照位数排序
     *
     * 10个桶 0-9
     * 1.先排序个位数，按照个位数大小将所有数字放在对应的桶里
     * 2.再排序十位数，按照十位数大小将所有数字放在对应的桶里（注意是稳定排序）
     * 3.......
     *
     * 数组中最大的数有多少位数，就需要进行排序多少轮，每一轮位数变大一位
     * @param arr
     */
    public static void radixSort(int[] arr) {

        int[][] bucket = new int[10][arr.length];
        int[] bucketIndexNumber = new int[bucket.length];

        // 计算该数组的最大数字的位数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int enlarged = 1;
        int maxNum = (max + "").length(); // 最大的位数

        for (int k = 0; k < maxNum; k++, enlarged *= 10) {
            // 依次排序个位、十位、百位
            // 个、十、百... 位数的数字
            for (int i = 0; i < arr.length; i++) {
                // 获得该数组的第 i 个元素的个位数
                int digit = (arr[i] / enlarged) % 10;
                // 将该数字放进对应的桶中
                bucket[digit][bucketIndexNumber[digit]] = arr[i];
                bucketIndexNumber[digit]++;
            }

            // 此时数组中的数字已经全部入桶，将桶中的数字全部拿出放回数组中
            int recodeArr = 0;
            for (int i = 0; i < bucket.length; i++) {
                if (bucketIndexNumber[i] > 0) {
                    // 说明第 i 个桶中有数据
                    for (int j = 0; j < bucketIndexNumber[i]; j++) {
                        // 从下标为 0 的位置拿数据，拿 bucketIndexNumber[i] 个
                        arr[recodeArr++] = bucket[i][j];
                    }
                }
                bucketIndexNumber[i] = 0;
            }
        }

    }

}
