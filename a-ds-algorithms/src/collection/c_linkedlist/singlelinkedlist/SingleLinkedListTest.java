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
        Node hero2 = new Node(3, "卢俊义", "玉麒麟");
        Node hero3 = new Node(5, "吴用", "智多星");
        Node hero4 = new Node(7, "林冲", "豹子头");
        Node hero5 = new Node(2, "宋江", "及时雨");
        Node hero6 = new Node(4, "卢俊义", "玉麒麟");
        Node hero7 = new Node(8, "吴用", "智多星");
        Node hero8 = new Node(9, "林冲", "豹子头");

        linkedList.addOrderByNo(hero1);
        linkedList.addOrderByNo(hero2);
        linkedList.addOrderByNo(hero3);
        linkedList.addOrderByNo(hero4);
        linkedList.addOrderByNo(hero5);
        linkedList.addOrderByNo(hero6);
        linkedList.addOrderByNo(hero7);
        linkedList.addOrderByNo(hero8);
        linkedList.addOrderByNo(hero5);
        linkedList.list();

        linkedList.addOrderByNo(hero3);

        linkedList.update(new Node(5, "无用", "智多星"));
        linkedList.list();
        System.out.println("该链表的有效节点个数为：" + linkedList.getLength(linkedList.getHead()));

        System.out.println("倒数第四个节点是：" + linkedList.findLastIndexNode1(linkedList.getHead(), 4));
        System.out.println("倒数第四个节点是：" + linkedList.findLastIndexNode2(linkedList.getHead(), 4));

        // 反转单链表
        System.out.println("反转单链表：");
        linkedList.reverse1(linkedList.getHead());
        linkedList.list();
        linkedList.reverse2(linkedList.getHead());
        linkedList.list();

        System.out.println("反向打印单链表：");
        linkedList.printFromLast(linkedList.getHead());



        System.out.println("ss");



    }
}
