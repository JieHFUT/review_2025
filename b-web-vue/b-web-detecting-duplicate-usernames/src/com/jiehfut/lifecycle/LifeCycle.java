package com.jiehfut.lifecycle;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Tomcat 创建并且管理 Servlet 的过程
 *
 * 1.Servlet 实例化   =============>  构造器   =======>  第一次请求的时候执行  ========   1次
 * 2.Servlet 初始化   =============>  init    =======>  构造完毕就执行     =========   1次
 * 3.Servlet 接收请求，处理请求 服务  => service() =====>  每次请求都会执行   ==========   多次
 * 4.Servlet 销毁     =============> destory()  ====>  关闭 Tomcat 服务的时候执行 ==    1次
 *
 * ==> 得到一个结论：Servlet 在 Tomcat 是单例的
 *     所以 servlet 中的成员变量在多个访问线程中是共享的，不建议在 service 方法中去修改成员变量
 *
 *
 * 同时有很多客户端给 tomcat 客户端发送请求
 * 对于 lifeCycle 资源
 * Tomcat 每个请求都有一个线程栈，至于 lifeCycle 对象，是在堆中存储
 * 每一个线程栈里面会执行一个 service 方法
 * service 方法中的变量存储在 lifeCycle 对象中，每个 service 方法执行的时候会调用 lifeCycle 对象中的变量
 *
 *
 *
 * default servlet => The mapping for the default servlet
 *     <servlet-mapping>
 *         <servlet-name>default</servlet-name>
 *         <url-pattern>/</url-pattern>
 *     </servlet-mapping>
 * 如果一个静态资源请求路径没有和任何一个 servlet 匹配上，就会将这个请求交给 default servlet 处理
 *
 *     <servlet>
 *         <servlet-name>default</servlet-name>
 *         <servlet-class>org.apache.catalina.servlets.DefaultServlet</servlet-class>
 *         <init-param>
 *             <param-name>debug</param-name>
 *             <param-value>0</param-value>
 *         </init-param>
 *         <init-param>
 *             <param-name>listings</param-name>
 *             <param-value>false</param-value>
 *         </init-param>
 *         <load-on-startup>1</load-on-startup>
 *     </servlet>
 *
 *     <servlet>
 *         <servlet-name>jsp</servlet-name>
 *         <servlet-class>org.apache.jasper.servlet.JspServlet</servlet-class>
 *         <init-param>
 *             <param-name>fork</param-name>
 *             <param-value>false</param-value>
 *         </init-param>
 *         <init-param>
 *             <param-name>xpoweredBy</param-name>
 *             <param-value>false</param-value>
 *         </init-param>
 *         <load-on-startup>3</load-on-startup>
 *     </servlet>
 *
 *     <servlet-mapping>
 *         <servlet-name>default</servlet-name>
 *         <url-pattern>/</url-pattern>
 *     </servlet-mapping>
 *
 *
 *
 * default servlet 会根据你请求的资源路径，找到对应的文件，通过 IO 流读取这个文件，
 * 然后将这个文件放到 response 中去，并且判断文件拓展名，选择合适的类型设置响应头：Content-Type
 * 并且读取文件大小设置响应头：Content-length
 *
 */

// @WebServlet(value = "/lifeCycle")  ===> xml 文件中的启动 servlet 顺序
public class LifeCycle extends HttpServlet {

    public LifeCycle() {
        System.out.println("构造器");
    }


    @Override
    public void init() throws ServletException {
        System.out.println("初始化");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("服务");
    }

    @Override
    public void destroy() {
        System.out.println("销毁");
    }


}
