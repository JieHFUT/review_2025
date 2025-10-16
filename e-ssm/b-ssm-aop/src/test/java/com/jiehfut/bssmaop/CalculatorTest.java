package com.jiehfut.bssmaop;

import com.jiehfut.bssmaop.calculator.Calculator;
import com.jiehfut.bssmaop.calculator.impl.*;
import com.jiehfut.bssmaop.service.UserService;
import com.jiehfut.bssmaop.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculatorTest {

    @Test
    public void testCalculator(){
        Calculator calculator1 = new CalculatorImpl();
        calculator1.add(2, 3);

        /**
         * 硬编码
         * 实现类在每一个接口上都进行日志的添加
         */
        Calculator calculator2 = new CalculatorImplHardCoding();
        calculator2.add(2, 3);

        /**
         * 静态代理
         * 在编码时期就确定了代理对象（关系）
         * 功能完善，但是范围太小了，只能负责部分接口代理功能
         */
        Calculator calculator3 = new CalculatorImplStaticProxy((CalculatorImpl) calculator1);
        calculator3.add(2, 3);

        /**
         * 动态代理
         * 在运行期间才确定代理对象（关系）
         * 拦截器思想，目标对象在执行期间会被动态拦截，进行动态代理
         * 强制要求目标对象必有接口，代理的也只是接口规定的方法
         */
        DynamicProxy dynamicProxy = new DynamicProxy(new CalculatorImpl());
        Calculator calculator4 = (Calculator) dynamicProxy.getProxy();
        calculator4.add(2, 3);


        /**
         *
         */
        CalculatorImpl calculator = (CalculatorImpl) DynamicProxyStatic.getProxyInstance(new CalculatorImpl());
        calculator.add(2, 3);
    }

    
    @Test // 动态代理
    public void testUserService(){
        DynamicProxy dynamicProxy = new DynamicProxy(new UserServiceImpl());
        UserService service = (UserService) dynamicProxy.getProxy();
        service.saveUser();
    }

    

    @Autowired
    Calculator calculator; // 容器中是它的代理对象

    @Autowired
    UserService userService;

    @Test
    public void testAopAspect(){
        System.out.println("实现类：" + calculator);
        System.out.println("实现类类型：" + calculator.getClass());

        calculator.add(15, 85);
        // 实现类：com.jiehfut.bssmaop.calculator.impl.CalculatorImpl@6436e181
        // 实现类类型：class com.jiehfut.bssmaop.calculator.impl.CalculatorImpl$$SpringCGLIB$$0
        // 环绕通知 => 目标方法之前执行
        // Logger-->前置通知，方法名称是：add，参数是：[15, 85]
        // 方法内部 result = 100
        // Logger-->返回通知，方法名称是：add，目标方法的返回结果是：100
        // Logger-->后置通知，方法名称是：add
        // 环绕通知 => 目标方法返回值之后执行
        // 环绕通知 => 目标方法执行完毕后执行

        /**
         * 很明显有了切面类后，容器中组件就不是原生的该类了
         * 容器中的组件将会是 spring 中的 CGLIB 为其产生的一个代理对象
         * CGLIB：优点
         *     jdk 做代理对象，必须是有接口
         *     CGLIB 可以为万物产生代理，没有接口也行
         *
         * 目标方法执行之前会进行很多回调 => 增强器（类 - advised - advisors - 集合里嵌入了增强器链）
         * 切面中的所有通知方法就为增强器，这些增强器会被组织成为一个链路放到集合中
         * 目标方法真正执行前后会去增强器链中执行需要提前执行的方法
         *
         * AOP 的底层原理：
         *     spring 会为每个被切面切入的组件创建代理对象（CGLIB 创建的代理对象，无视接口）
         *     代理对象中保存了切面类中的所有通知方法的增强器链
         *     目标方法执行的时候，会去先执行增强器链中需要提前执行的通知方法
         *
         * 通知方法的执行顺序
         *     没有异常：前置通知、目标方法、返回通知、后置通知
         *     发生异常：前置通知、目标方法、异常通知、后置通知
         */

        /**
         * 多切面类执行顺序
         * 不设置就是安装类名首字母顺序

         实现类：com.jiehfut.bssmaop.calculator.impl.CalculatorImpl@67bb4dcd
         实现类类型：class com.jiehfut.bssmaop.calculator.impl.CalculatorImpl$$SpringCGLIB$$0

         环绕通知（Logger） => 目标方法之前执行
         Logger-->前置通知，方法名称是：add，参数是：[15, 85]
         环绕通知（Permission） => 目标方法之前执行
         Permission-->前置通知，方法名称是：add，参数是：[15, 85]
         方法内部 result = 100
         Permission-->返回通知，方法名称是：add，目标方法的返回结果是：100
         Permission-->后置通知，方法名称是：add
         环绕通知（Permission） => 目标方法返回值之后执行
         环绕通知（Permission） => 目标方法执行完毕后执行
         Logger-->返回通知，方法名称是：add，目标方法的返回结果是：100
         Logger-->后置通知，方法名称是：add
         环绕通知（Logger） => 目标方法返回值之后执行
         环绕通知（Logger） => 目标方法执行完毕后执行

         */
    }

    @Test
    public void testUserServiceAspect(){
        System.out.println("实现类：" + userService);
        System.out.println("实现类类型；" + userService.getClass());
        userService.saveUser();
        // 实现类：com.jiehfut.bssmaop.service.impl.UserServiceImpl@7364eed1
        // 实现类类型；class com.jiehfut.bssmaop.service.impl.UserServiceImpl
        // save user....
    }





}
