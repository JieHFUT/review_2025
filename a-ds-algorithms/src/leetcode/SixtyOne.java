package leetcode;

/**
 * ClassName: SixtyOne
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/8/26 22:52
 * @Version 1.0
 */
public class SixtyOne {
    public static void main(String[] args) {

        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        ListNode right = rotateRight(node, 2);
        while (right != null) {
            System.out.println(right.val);
            right = right.next;
        }
    }

    public static ListNode rotateRight(ListNode head, int k) {
        // 思路：先遍历一遍将链表形成环形链表，然后移动头节点，最后在正确的位置断开
        // 移动 k 个位置，就是将头借点移动 length - k % length 作为新的头借点
        // 移动 k - 1 个位置，将其指向新的头借点的指向给断开
        if (head == null || head.next == null) return head;

        // 1.遍历一遍形成环
        int size = 1;
        ListNode cur = head;
        while (cur.next != null) {
            size++;
            cur = cur.next;
        }

        // 首位相连
        cur.next = head;
        // 开始移动
        ListNode newHead = head;
        int step = size - k % size;
        while (step-- > 0) {
            newHead = newHead.next;
        }

        ListNode prev = head;
        int step2 = size - (k + 1) % size;
        while (step2-- > 0) {
            prev = prev.next;
        }
        // 断开
        prev.next = null;

        return newHead;
    }

}



