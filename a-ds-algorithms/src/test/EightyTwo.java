package test;

/**
 * ClassName: EightyTwo
 * Package: test
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/8/29 12:23
 * @Version 1.0
 */
public class EightyTwo {

    public static void main(String[] args) {

        ListNode node = deleteDuplicates(new ListNode(1,
                                                new ListNode(2,
                                                        new ListNode(3,
                                                                new ListNode(3,
                                                                        new ListNode(4,
                                                                                new ListNode(4,
                                                                                        new ListNode(4,
                                                                                                new ListNode(5)))))))));


    }



    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;

        // 思路：现在至少有两个节点 => 双指针
        ListNode headprev = new ListNode(0, head);

        ListNode prev = headprev;
        ListNode rear = head;

        while (rear.next != null) {
            // 判读下一个是否是重复的
            if (rear.next != null && rear.val == rear.next.val) {
                while (rear.next != null && rear.val == rear.next.val) {
                    rear = rear.next;
                }
                rear = rear.next;
                prev.next = rear;
            } else {
                prev = rear;
                rear = rear.next;
            }
        }

        return headprev.next;

    }
}
