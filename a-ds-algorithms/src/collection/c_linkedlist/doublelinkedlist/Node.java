package collection.c_linkedlist.doublelinkedlist;

/**
 * ClassName: Node
 * Package: collection.c_linkedlist.doublelinkedlist
 * Description:
 * 双向链表的节点类
 * @Author jieHFUT
 * @Create 2025/7/26 22:44
 * @Version 1.0
 */
public class Node {

    public int no;
    public String name;
    public String nickname;

    public Node next;
    public Node prev;

    public Node(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Node [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }
}
