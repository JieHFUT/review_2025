package thread.lockanddead_4.lazyandhungry;


// 饿汉式
public class Lazy {
    // 1. 类的构造器私有化
    private Lazy() {}

    // 2. 声明当前类的实例, 此属性也必须声明为static的
    private static Lazy instance = null;

    // 3. 通过getXxx()获取当前类的实例，如果未创建对象，则在方法内部进行创建
    public static Lazy getInstance() {
        if (instance == null) {
            instance = new Lazy();
        }
        return instance;
    }

}
