package collection.c_linkedlist.josepfu;

/**
 * ClassName: CircleSingleLinkedList
 * Package: collection.c_linkedlist.josepfu
 * Description:
 * Boy(int no, Boy next)
 *
 * @Author jieHFUT
 * @Create 2025/7/27 0:13
 * @Version 1.0
 */
// 创建一个环形单链表
public class CircleSingleLinkedList {

    // 创建一个 first 节点
    private Boy first;

    // 添加节点的时候，需要构建环形链表，链表的指向需要形成一个环

    /**
     * 用户指定的环形链表中有多少个节点：nums
     * @param nums
     */
    public void add(int nums) {
        if (nums <= 0) return;

        // 创建一个节点用于记录最新的节点
        Boy cur = null;

        for (int i = 1; i <= nums; i++) {
            if (i == 1) {
                // 第一个节点
                first = new Boy(i);
                first.setNext(first);
                cur = first;
            } else {
                // 环形链表
                Boy boy = new Boy(i);
                cur.setNext(boy);
                boy.setNext(first);
                cur = boy;
            }
        }
    }


    // 遍历当前环形列表所有的节点
    public void showBoy() {
        if (first == null) return;

        // first != null 说明至少有一个节点
        System.out.print(first.getNo() + "\t");
        Boy cur = first.getNext();
        while (cur != first) {
            System.out.print(cur.getNo() + "\t");
            cur = cur.getNext();
        }
        System.out.println();
    }


    /**
     * 根据用户的输入，输出一个环形链表的出圈顺序
     * @param startNo 从第几个节点开始输出
     * @param countNo 每隔多少个节点输出一次
     * @param nums 一共有多少个节点
     */
    public void countBoyList(int startNo, int countNo, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("Invalid input");
            return;
        }

        // 先指定 helper 指针指向最后一个节点
        Boy helper = first;
        while (helper.getNext() != first) {
            helper = helper.getNext();
        }

        // helper 和 first 同时移动 startNo - 1 次
        for (int i = 1; i < startNo; i++) {
            helper = helper.getNext();


            first = first.getNext();
        }

        /**
         * 此时 first 指向的是需要被删除的第一个节点，helper 指向的是前一个节点
         * 从现在开始，first和 helper需要先移动 countNo 次，然后再删除之前记录的 first
         * 如果 helper 移动完成以后追上了 first, 说明只剩下了一个元素
         */
        while (true) {
            if(first == helper) break;
            // 记录需要被删除的节点
            Boy prevHelper = helper;
            Boy needDel = first;
            for (int i = 0; i < countNo; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("需要被出圈的是 %d\n", needDel.getNo());
            // 删除该节点
            prevHelper.setNext(needDel.getNext());
            // needDel.setNext(null);
        }
        System.out.printf("最后一个被出圈的是 %d\n", first.getNo());
        first = null;
    }

}
