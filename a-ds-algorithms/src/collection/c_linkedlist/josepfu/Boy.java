package collection.c_linkedlist.josepfu;

/**
 * ClassName: Boy
 * Package: collection.c_linkedlist.josepfu
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/7/27 0:12
 * @Version 1.0
 */
// 表示节点数据
// 表示节点类
public class Boy {

    private int no; // 编号
    private Boy next; // 指向下一个节点

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
