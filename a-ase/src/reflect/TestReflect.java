package reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ClassName: TestReflect
 * Package: reflect
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/9/25 16:34
 * @Version 1.0
 */
public class TestReflect {
    // 1.获取 Class 对象的多种方式
    @Test
    public void testGetClass() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 1.类名.class获取
        Class<Car> clazz1 = Car.class;
        // 2.对象.getClass()
        Class clazz2 = new Car().getClass();
        // 3.Class.forName("path")
        Class clazz3 = Class.forName("reflect.Car");

        // 实例化 - 无参构造方法进行实例化
        Car car1 = clazz1.newInstance(); // 已经过时
        Car car = (Car) clazz3.getDeclaredConstructor().newInstance();
        System.out.println(car);

    }

    // 2.获取构造方法
    @Test
    public void testConstructor() throws Exception {
        Class clazz1 = Car.class;
        // 获取所有构造方法（不包括非 public 的）
        Constructor[] constructors = clazz1.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println("构造方法的名称：" + constructor.getName() + " 参数个数：" + constructor.getParameterCount());
        }
        /**
         构造方法的名称：com.jiehfut.testreflect.Car 参数个数：0
         构造方法的名称：com.jiehfut.testreflect.Car 参数个数：3

         getConstructors 只会获取声明是 public 的构造方法
         getDeclaredConstructors 会获取所有的构造方法
         */

        // 指定有参数的构造方法创建对象
        Constructor constructor1 = clazz1.getConstructor(String.class, int.class, String.class);
        // constructor1.setAccessible(true); 若是私有的构造器就需要设置 setAccessible 为 true
        Car car1 = (Car) constructor1.newInstance("xiaomi", 2, "red");
        System.out.println(car1.toString());

    }

    // 3.获取属性
    @Test
    public void testGetFiled() throws Exception {
        Class<Car> clazz1 = Car.class;
        // 获取一个对象（无参构造）
        Car car = (Car) clazz1.getDeclaredConstructor().newInstance();
        // 获取所有的属性（包含私有的属性）
        Field[] fields = clazz1.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            if(field.getName().equals("brand")){
                // 设置允许访问
                field.setAccessible(true);
                field.set(car, "huawei");
            }
        }
        System.out.println(car.toString());
    }

    // 4.获取方法
    @Test
    public void testGetFunction() throws InvocationTargetException, IllegalAccessException {
        Car car = new Car("xiaomi", 2, "red");
        Class clazz = car.getClass();

        // 获得所有的 public 的方法
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
            // 执行某一个方法，如 toString
            if (method.getName().equals("toString")) {
                // invoke 执行该方法
                String string = (String) method.invoke(car);
                System.out.println("toString function: " + string);
            }
        }
        System.out.println("\n获得所有的方法，包括私有的方法：");
        // 获得所有的方法，包括私有的方法
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println(method.getName());
            if (method.getName().equals("run")) {
                method.setAccessible(true);
                method.invoke(car);
            }
        }
    }
}
