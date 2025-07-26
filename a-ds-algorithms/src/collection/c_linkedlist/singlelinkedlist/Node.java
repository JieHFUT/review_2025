package collection.c_linkedlist.singlelinkedlist;

/**
 * ClassName: Node
 * Package: collection.c_linkedlist.singlelinkedlist
 * Description:
 *  单链表的节点对象
 * @Author jieHFUT
 * @Create 2025/7/26 18:49
 * @Version 1.0
 */
public class Node {
    public int no; // 节点排名
    public String name;
    public String nickname;
    public Node next;

    // 构造器
    public Node(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    // 输出

    @Override
    public String toString() {
        return "Node [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }
}
