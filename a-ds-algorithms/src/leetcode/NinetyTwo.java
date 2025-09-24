package leetcode;

/**
 * ClassName: NinetyTwo
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/9/10 21:50
 * @Version 1.0
 */
public class NinetyTwo {


    public static void main(String[] args) {
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        reverseBetween(node, 2, 4);

    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        // 思路：插入法
        int record = 1; // 记录目前处理的是第一个节点

        ListNode cur = head;

        ListNode newHead = new ListNode();
        ListNode remove = newHead;

        // 如果 record 的位置在 left - right 之间就插入法，否则就直接后缀在其后面
        while (cur != null && record++ < left) {
            // 直接后缀
            ListNode curNext = cur.next;
            cur.next = remove.next;
            remove.next = cur;
            remove = remove.next;
            cur = curNext;
        }

        while (cur != null && record++ <= right) {
            // 进行插入
            ListNode curNext = cur.next;
            cur.next = remove.next;
            remove.next = cur;
            cur = curNext;
        }

        // 先将 remove 移动到最后一个借点
        while (remove != null && remove.next != null) {
            remove = remove.next;
        }

        while (cur != null) {
            // 直接后缀
            ListNode curNext = cur.next;
            cur.next = remove.next;
            remove.next = cur;
            remove = remove.next;
            cur = curNext;
        }

        return newHead.next;
    }
}
