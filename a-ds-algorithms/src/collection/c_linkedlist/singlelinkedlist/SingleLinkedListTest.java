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

        System.out.println("=============================");



        SingleLinkedList linkedList1 = new SingleLinkedList();
        Node hero11 = new Node(11, "宋江", "及时雨");
        Node hero21 = new Node(3, "卢俊义", "玉麒麟");
        Node hero31 = new Node(51, "吴用", "智多星");
        Node hero41 = new Node(71, "林冲", "豹子头");
        Node hero51 = new Node(2, "宋江", "及时雨");
        Node hero61 = new Node(6, "卢俊义", "玉麒麟");
        Node hero71 = new Node(8, "吴用", "智多星");
        Node hero81 = new Node(9, "林冲", "豹子头");
        linkedList1.addOrderByNo(hero11);
        linkedList1.addOrderByNo(hero21);
        linkedList1.addOrderByNo(hero31);
        linkedList1.addOrderByNo(hero41);
        linkedList1.addOrderByNo(hero51);
        linkedList1.addOrderByNo(hero61);
        linkedList1.addOrderByNo(hero71);
        linkedList1.addOrderByNo(hero81);
        linkedList1.addOrderByNo(hero51);
        linkedList1.list();
        SingleLinkedList linkedList2 = new SingleLinkedList();
        Node hero111 = new Node(11, "宋江", "及时雨");
        Node hero211 = new Node(34, "卢俊义", "玉麒麟");
        Node hero311 = new Node(51, "吴用", "智多星");
        Node hero411 = new Node(7, "林冲", "豹子头");
        Node hero511 = new Node(2, "宋江", "及时雨");
        Node hero611 = new Node(64, "卢俊义", "玉麒麟");
        Node hero711 = new Node(82, "吴用", "智多星");
        Node hero811 = new Node(5, "林冲", "豹子头");
        linkedList2.addOrderByNo(hero111);
        linkedList2.addOrderByNo(hero211);
        linkedList2.addOrderByNo(hero311);
        linkedList2.addOrderByNo(hero411);
        linkedList2.addOrderByNo(hero511);
        linkedList2.addOrderByNo(hero611);
        linkedList2.addOrderByNo(hero711);
        linkedList2.addOrderByNo(hero811);
        linkedList2.addOrderByNo(hero511);
        linkedList2.list();
        Node combine = SingleLinkedList.combine(linkedList1.getHead(), linkedList2.getHead());
        SingleLinkedList linkedList3 = new SingleLinkedList();
        linkedList3.addInTail(combine.next);
        linkedList3.list();
    }
}
