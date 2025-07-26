package collection.c_linkedlist.singlelinkedlist;

/**
 * ClassName: SingleLinkedListTest
 * Package: collection.c_linkedlist.singlelinkedlist
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/7/26 18:50
 * @Version 1.0
 */
public class SingleLinkedListTest {

    public static void main(String[] args) {

        SingleLinkedList linkedList = new SingleLinkedList();

        //进行测试
        //先创建节点
        Node hero1 = new Node(1, "宋江", "及时雨");
        Node hero2 = new Node(2, "卢俊义", "玉麒麟");
        Node hero3 = new Node(3, "吴用", "智多星");
        Node hero4 = new Node(4, "林冲", "豹子头");

        linkedList.addOrderByNo(hero3);
        linkedList.addOrderByNo(hero4);
        linkedList.addOrderByNo(hero2);
        linkedList.addOrderByNo(hero1);
        linkedList.list();

        linkedList.addOrderByNo(hero3);

        linkedList.update(new Node(3, "无用", "智多星"));
        linkedList.list();
        System.out.println("该链表的有效节点个数为：" + linkedList.getLength(linkedList.getHead()));



        System.out.println("ss");



    }
}
