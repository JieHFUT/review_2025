package thread.lockanddead_4.lazyandhungry;


// 饿汉式
public class Hungry {

    // 1. 类的构造器私有化
    private Hungry() {}
    // 2. 在类的内部创建当前类的实例, 此属性也必须声明为static的
    private static Hungry instance = new Hungry();
    // 3. 使用getXxx()方法获取当前类的实例，必须声明为static的
    public static Hungry getInstance() {
        return instance;
    }

}
