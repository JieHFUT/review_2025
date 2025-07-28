package collection.f_sort.sort6_merge;

/**
 * ClassName: Merge
 * Package: collection.f_sort.sort6_merge
 * Description:
 *
 *
 * @Author jieHFUT
 * @Create 2025/7/28 18:48
 * @Version 1.0
 */
public class Merge {


    /**
     * 归并排序进行 "分" 的过程
     *
     * @param arr 需要被排序的数组
     * @param left
     * @param right
     * @param temp new int[arr.length];
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left == right) return;
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid, temp);
        mergeSort(arr, mid + 1, right, temp);
        // 合并
        merge(arr, left, mid, right, temp);

    }

    /**
     * 归并排序的合并方法
     * 虽然传递过来是整个需要被排序的数组，但是在一轮排序过程中只需要对从 left - right 下标的数据进行排序
     * [left ... mid][mid+1 ... right]
     * left指针和 mid+1指针相互比较，小的移动到数组 ret 中去
     * 两个指针全部移动到末尾的时候，在 ret 数组中就保存了有序的合并
     * 再将 ret 数组中有序的合并拷贝到 arr(从下标 left开始)中
     *
     * 排序的时候将排序内容写在临时数组 ret 中，最后再拷贝回去
     * @param arr
     * @param left
     * @param mid
     * @param right
     * @param ret
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] ret) {

        int i = left;
        int count = 0;
        int j = mid + 1;
        // 可以考虑现在是两个有序数组 一个是arr[0 => mid] 一个是arr[mid+1 => right]
        while(i <= mid && j <= right) {
            // 两个有序数组都还没有到头
            if(arr[i] < arr[j]) {
                // 将较小的加入到零时数组中
                ret[count] = arr[i];
                count++;
                i++;
            } else {
                ret[count] = arr[j];
                count++;
                j++;
            }
        }
        // 已经有某一个数组到头了
        if(i == mid) {
            while(j <= right) {
                ret[count] = arr[j];
                count++;
                j++;
            }
        }
        if(j == right) {
            while(i <= mid) {
                ret[count] = arr[i];
                count++;
                i++;
            }
        }
        // 已经将 arr 中数据在 ret 中有序放置
        // 开始拷贝回去
        count = 0;
        int tempLeft = left;
        while(tempLeft <= right) {
            arr[tempLeft] = ret[count];
            count++;
            tempLeft++;
        }
    }

}
