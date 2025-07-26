package collection.c_linkedlist.singlelinkedlist;

import java.text.BreakIterator;

/**
 * ClassName: SingleLinkedList
 * Package: collection.c_linkedlist.singlelinkedlist
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/7/26 18:50
 * @Version 1.0
 */
public class SingleLinkedList {

    // 初始化一个头节点，不存放任何的数据
    private Node head = new Node(0, "", "");

    // 返回头节点
    public Node getHead() {
        return head;
    }

    // 添加节点到单向链表
    public void addInTail(Node node) {
        Node current = head;
        while(true) {
            // 寻找到链表尾部
            if(current.next == null) {
                current.next = node;
                return;
            }
            current = current.next;
        }
    }

    // 根据排序将节点加入到链表中（默认前面的链表已经根据 no 排序好了），如果已经有该排名就声明已经存在该节点
    public void addOrderByNo(Node node) {
        Node current = head;
        boolean flag = false; // 默认原来链表中不存在该排名
        // 遍历链表
        while(true) {
            // 如果到最后都没有找到其位置就直接链在最后面
            if(current.next == null) {
                break;
            }
            // 如果中间遇到了排名一样的就声明
            if(current.next.no == node.no) {
                flag = true;
                break;
            } else if (current.next.no > node.no) {
                break;
            }
            current = current.next;
        }
        if(flag) {
            System.out.println("该节点的排序已经存在！" + node.no);
        } else {
            node.next = current.next;
            current.next = node;
        }
    }


    // 判断链表是否为空
    public boolean isEmpty() {
        return head.next == null;
    }

    // 根据排名 no 来修改节点信息
    public void update(Node node) {
        // 判断链表是否为空
        if (isEmpty()) {
            System.out.println("链表为空，不能进行数据修改！");
            return;
        }
        // 遍历链表来修改信息
        Node current = head;
        boolean flag = false; // 默认匹配不到要修改的节点

        while(true) {
            if (current.next != null && current.next.no == node.no) {
                // 匹配成功
                flag = true;
                break;
            }
            if(current.next == null) {
                // 结束了也没有匹配到
                break;
            }
            current = current.next;
        }
        if (flag) {
            current.next.name = node.name;
            current.next.nickname = node.nickname;
        } else {
            System.out.println("没有匹配到要修改的节点：" + node.no);
        }
    }


    // 根据排序删除某一个节点
    public void remove(int no) {
        if (isEmpty()) throw new RuntimeException("链表为空，无法删除节点");
        // 遍历链表，匹配到就进行删除，匹配不到就说明
        Node current = head;
        boolean flag = false; // 默认匹配不到
        while(true) {
            if (current.next != null && current.next.no == no) {
                flag = true;
                break;
            } else if (current.next == null) {
                // 该链表没有该节点
                break;
            }
            current = current.next;
        }
        if (flag) {
            // 删除 current.next
            current.next = current.next.next;
        } else {
            System.out.println("无法删除该节点，链表中没有该节点：" + no);
        }
    }


    // 遍历链表，打印节点信息
    public void list() {
        if (isEmpty()) throw new RuntimeException("链表为空，无法遍历！");
        Node current = head;
        while(current.next != null) {
            System.out.println(current.next);
            current = current.next;
        }
        System.out.println();
    }

    // 获取单链表的有效节点的个数
    public int getLength(Node head) {
        if (head.next == null) return 0;
        // 遍历链表获取数量
        Node current = head;
        int count = 0;
        while (current.next != null) {
            count++;
            current = current.next;
        }
        return count;
    }


    // 查找单链表中的倒数第 K 个节点

    /**
     * 方式1：先遍历一遍，再遍历到 length - k 个
     * 方式2：使用快慢指针方法
     * @param head
     * @param k
     * @return
     */
    public Node findLastIndexNode1(Node head, int k) {

    }




















}
