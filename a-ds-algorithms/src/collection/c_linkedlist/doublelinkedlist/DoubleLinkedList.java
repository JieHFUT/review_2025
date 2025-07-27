package collection.c_linkedlist.doublelinkedlist;

/**
 * ClassName: DoubleLinkedList
 * Package: collection.c_linkedlist.doublelinkedlist
 * Description:
 * 双向链表的实现 no, prev, next, name, nickname
 * @Author jieHFUT
 * @Create 2025/7/26 22:46
 * @Version 1.0
 */
public class DoubleLinkedList {

    private Node head = new Node(0, "", "");

    public Node getHead() {
        return head;
    }

    // 遍历双向链表的方法
    public void list() {
        if (head.next == null) {
            System.out.println("双链表为空，无法进行遍历");
            return;
        }
        Node cur = head.next;
        while (cur != null) {
            System.out.println(cur);
        }
    }

    // 添加节点到链表尾部
    public void addInTail(Node node) {
        Node cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = node;
        node.prev = cur;
    }

    // 修改节点的信息，根据 no 来进行修改
    public void update(Node node) {
        if (head.next == null) {
            System.out.println("双链表为空，无法进行修改");
            return;
        }
        Node cur = head.next;
        boolean flag = false; // 默认匹配不到该节点
        while(true) {
            if(cur == null) break;
            if (cur.no == node.no) {
                flag = true;
                break;
            }
            cur = cur.next;
        }
        if (flag) {
            // 匹配到了
            cur.name = node.name;
            cur.nickname = node.nickname;
        } else {
            System.out.println("没有匹配到对应的节点信息：" + node.no);
        }
    }


    // 删除某一个节点
    public void remove(int no) {
        // 遍历链表，遇到直接删除即可
        if (head.next == null) {
            System.out.println("链表为空，无法进行删除：" + no);
        }
        Node cur = head.next;
        boolean flag = false;
        while (cur != null) {
            if (cur.no == no) {
                // 找到需要删除的节点
                flag = true;
                break;
            }
            cur = cur.next;
        }
        if (flag) {
            if (cur.next == null) {
                // 匹配到最后一个节点
                cur.prev.next = null;
                cur.prev = null;
            }
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;
        } else {
            System.out.println("没有找到该节点：" + no);
        }
    }









}







