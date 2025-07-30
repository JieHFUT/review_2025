package collection.l_binarysort_tree;

import org.junit.Test;

/**
 * ClassName: BinarySortTreeTest
 * Package: binarysorttree
 * Description:
 *
 * @Author jieHFUT
 * @Create 2024/11/2 20:16
 * @Version 1.0
 */
public class BinarySortTreeTest {
    public static void main(String[] args) {

        BinarySortTree tree = new BinarySortTree();
        int[] content = new int[100_000];
        for (int i = 0; i < content.length; i++)
            content[i] = (int) (Math.random() * 100_000);
        // 将这些随机元素放置在排序树中
        for (int i = 0; i < content.length; i++)
            tree.add(new Node(content[i]));
        System.out.println("输出排序树的前序遍历结果为：");
        tree.preOrder();
        System.out.println("输出排序树的层序遍历结果为：");
        tree.levelOrder();
    }


    @Test
    public void test(){
        BinarySortTree tree = new BinarySortTree();
        int[] toSort = {15,7,25,18,30,17,20};
        for (int i = 0; i < toSort.length; i++) {
            tree.add(new Node(toSort[i]));
        }
        System.out.println("输出排序树的前序遍历结果为：");
        tree.preOrder();
        System.out.println("输出排序树的层序遍历结果为：");
        tree.levelOrder();
        tree.delete(18);
    }


}
