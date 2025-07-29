package collection.f_sort.sort8_heap;

/**
 * ClassName: Heap
 * Package: collection.f_sort.sort8_heap
 * Description:
 * 堆排序
 * 堆也叫做优先级队列，是一种完全二叉树（分为大顶堆和小顶堆）
 *
 * 完美二叉树（满二叉树）：若树高度为h，则节点总数为 2的 h+1次方 - 1，呈现标准的指数级关系
 * 完全二叉树：只有最底层的节点未被填满，且最底层节点尽量靠左填充
 * 完满二叉树：除了叶节点之外，其余所有节点都有两个子节点
 * 平衡二叉树：任意节点的左子树和右子树的高度之差的绝对值不超过 1
 *
 *
 * 大顶堆特点：在数组中 arr[i] >= arr[2*i+1] && arr[i] >= arr[2*i+2]
 * 小顶堆特点：在数组中 arr[i] <= arr[2*i+1] && arr[i] <= arr[2*i+2]
 * 大顶堆：升序
 * 小顶堆：降序
 *
 * @Author jieHFUT
 * @Create 2025/7/28 19:19
 * @Version 1.0
 */
public class Heap {

    /**
     * 大顶堆的排序办法
     * 1.将待排序序列构造成一个大顶堆
     * 2.此时，整个序列的最大值就是堆顶的根节点。
     * 3.将其与末尾元素进行交换，此时末尾就为最大值。
     * 4.然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了。
     *
     */
    // 编写一个堆排序的方法，将传递进来的数据 arr 排序为从小到大
    public static void heapSort(int[] arr) {
        // 将数组 arr 先变成一个大顶堆（数组摆成树后，非叶子节点的下标是 0 - arr.length/2-1）
        // 对每一个非叶子节点进行调整，调整完毕就第一次变成了一个大顶堆
        for (int i = arr.length/2-1; i >= 0; i--) {
            adjust(arr, i, arr.length);
        }
        // 现在变成大顶堆之后，要将堆顶的最大值（也就是下标为0的位置）与堆的最后一个交换
        for (int i = arr.length-1; i > 0; i--) {
            // 一次次的将堆顶的最大值（下标为0）的值放到最后，次后...
            swap(arr, 0, i);
            // 每放置一次，就调整一次根节点，让其重新变成大顶堆
            adjust(arr, 0, i);
        }

    }
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 调整 tree, 从左至右，从下往上
     * @param arr 需要被调整的数据
     * @param i 表示非叶子节点在数组中的索引
     * @param length 表示要对多少个元素进行调整，调整一次就确定一个最大的数（下一层调整就少调整一个）
     *
     * adjust 功能详解
     * 1.传入的下标 i,意味着 i 为根的这个树变成了大顶堆
     * 2.想要 1 实现要满足这个 i 下标的左右子树已经是大顶堆
     */
    public static void adjust(int[] arr, int i, int length) {
        // 前提：i 的左右子树已经是大顶堆 => 结果：将以 i 为根的树也变成大顶堆

        // 左右节点中，大的和根进行交换
        // 如果发生交换了，左右哪边进行的交换，哪一边就要进行重新调整
        int temp = arr[i];
        // 左子节点下标 k=i*2+1  右子节点下标：k+1
        for (int k = i*2+1; k < length; k = k*2+1) {
            // 存在右子节点，并且右子节点大于左子节点
            if (k+1 < length && arr[k] < arr[k+1]) {
                // 决定哪一边有可能与 i 进行交换
                k++;
                // k++后，k 就变成了左右中较大的那个的下标
            }

            if (arr[k] > temp) {
                // 真发生交换
                arr[i] = arr[k];
                i = k;
            } else {
                //
                break;
            }
        }
        arr[i] = temp;
    }


}
