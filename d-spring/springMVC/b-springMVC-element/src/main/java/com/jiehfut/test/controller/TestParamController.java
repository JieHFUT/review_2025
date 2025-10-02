package com.jiehfut.test.controller;

import com.jiehfut.test.bean.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

// 测试获取请求参数的功能
@Controller
public class TestParamController {

    // 1.通过 servlet 的原生方法进行调用
    @RequestMapping("/testServletAPI")
    public String testServletAPI(HttpServletRequest request) {
        // 形参位置的 request 表示当前请求
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username = " + username + ", password = " + password);
        return "success";
    }



    // 2.通过使用控制器的参数获取请求中的参数，对于复选框可以使用一个字符串获取，也可以使用一个数组获取
    @RequestMapping("/testParam")
    public String testParams(String username, String password) {
        // 保证形式参数名称和请求中的参数名称保持一致即可
        System.out.println("username = " + username + ", password = " + password);
        return "success";
    }

    // 2.通过使用控制器的参数获取请求中的参数，对于复选框可以使用一个字符串获取，也可以使用一个数组获取
    @RequestMapping("/testParamsString")
    public String testParams(@RequestParam("username") String username, String password, String hobby) {
        // 保证形式参数名称和请求中的参数名称保持一致即可，如果参数名不一致，则不同的参数默认被置为空值
        // 如果参数名字不一致，也可以通过注解 @RequestParam 来声明一致
        System.out.println("username = " + username + ", password = " + password + ", hobby = " + hobby);
        // username = zhangsan, password = 43523451, hobby = b,c
        return "success";
    }



    /**
     * 2.通过使用控制器的参数获取请求中的参数，对于复选框可以使用一个字符串获取，也可以使用一个数组获取
     * @RequestParam 请求参数和控制器中的形参创建映射关系
     * @RequestHeader   请求头信息和控制器中的形参创建映射关系
     * @CookieValue     将 cookie 数据和控制器方法的形参创建映射关系
     * @param username
     * @param pwd
     * @param hobby
     * @param host
     * @param JSESSIONID
     * @return
     */
    @RequestMapping("/testParamsArray")
    public String testParams(String username,
                             @RequestParam(value = "password", required = false, defaultValue = "default password") String pwd,
                             String[] hobby,
                             @RequestHeader(value = "Host", required = true, defaultValue = "default:::localhost:8080") String host,
                             @CookieValue(value = "JSESSIONID", required = false) String JSESSIONID) {
        // 保证形式参数名称和请求中的参数名称保持一致即可
        // GET /mvc/testParams?username=zhangsan&password=4365&hobby=a&hobby=b&hobby=c HTTP/1.1

        System.out.println("username = " + username + ", password = " + pwd + ", hobby = " + Arrays.toString(hobby));
        // username = zhangsan, password = 2345123, hobby = [a, b]

        // 请求头中的信息可以和控制器中的形参进行映射
        System.out.println("host = " + host); // host = localhost:8080
        // 获取 JSESSIONID
        System.out.println("JSESSIONID = " + JSESSIONID);
        return "success";
    }


    /**
     * @RequestParam 一共有 3 个参数（ value && name 互为别名）
     *
     * @Target({ElementType.PARAMETER})
     * @Retention(RetentionPolicy.RUNTIME)
     * @Documented
     * public @interface RequestParam {
     *     1.@AliasFor("name")
     *       String value() default "";
     *     1.@AliasFor("value")
     *       String name() default "";
     *     2.boolean required() default true; 表示注解的参数在请求中必须有，如果没有且不设置默认值就报错 400
     *                                        如果将其设置为 false, 则请求中可传可不传（该属性表示设置是否必须传递）
     *     3.String defaultValue() default "\n\t\t\n\t\t\n\ue000\ue001\ue002\n\t\t\t\t\n";
     *     该值表示请求中如果不传递该参数，其默认值是什么？ 如：http://localhost:8080/mvc/testParamsArray?username=zhangsan&password=&hobby=b
     * }
     *
     *
     *
     *
     * @RequestParam    请求参数和控制器中的形参创建映射关系
     * @RequestHeader   请求头信息和控制器中的形参创建映射关系
     * @CookieValue     将cookie数据和控制器方法的形参创建映射关系
     * @PathVariable    请求路径中支持路径中的占位符 {} 为占位符
     * 这四个注解的参数都是一样的，三个参数
     * value  required  defaultValue
     *
     */


    /**
     * 直接将请求中的参数用实体类对象来接收
     * @return
     */
    @RequestMapping("/testbean")
    public String testbean(User user,
                           @RequestHeader(value = "Content-Type") String contentType,
                           @RequestHeader(value = "Content-Length") Integer contentLength
                           ) {
        System.out.println("user = " + user);
        // user = User{id=null, username='李四', password='32413', age=12, sex='女', email='3492779706@qq.com'}


        System.out.println("contentType = " + contentType);
        System.out.println("contentLength = " + contentLength);
        return "success";
    }


    /**
     * 乱码问题：
     * get 请求的乱码是由于 Tomcat 造成的，在 server.xml 中，在设置端口的地方添加一个 URIEcoding="UTF-8"
     * post 请求的乱码问题需要在获取参数之前指定请求使用的字符集（但是在我们设置编码之前 DispatcherServlet 已经获取了参数）
     * 所以在 controller 层里设置编码已经没有用了，所以要找到比 DispatcherServlet 启动更早的组件
     * 在 Tomcat 中组件的初始化顺序：servletContext 监听器 => 过滤器 filter => servlet
     * 所以在 过滤器 中设置统一编码字符集 => web.xml
     */


























}
