package com.jiehfut.listener.application;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;


@WebListener // 配置监听器的方法二
public class ApplicationListener implements ServletContextListener, ServletContextAttributeListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 监听应用域初始化（ServletContextEvent：应用域事件对象）

        // 获取应用域
        ServletContext application = sce.getServletContext();
        System.out.println("应用域 " + application.hashCode() + " 被初始化了");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 监听应用域销毁

        ServletContext application = sce.getServletContext();
        System.out.println("应用域 " + application.hashCode() + " 被销毁了");
    }







    @Override
    public void attributeAdded(ServletContextAttributeEvent scae) {
        // 监听应用域添加键值对
        ServletContext application = scae.getServletContext();
        String key = scae.getName();
        Object value = scae.getValue();
        System.out.println("应用域 " + application.hashCode() + " 添加了一个键值对：" + "key=" + key + ", value=" + value);
    }


    @Override
    public void attributeRemoved(ServletContextAttributeEvent scae) {
        // 监听应用域删除键值对
        ServletContext application = scae.getServletContext();
        String key = scae.getName();
        Object value = scae.getValue();
        System.out.println("应用域 " + application.hashCode() + " 删除了一个键值对：" + "key=" + key + ", value=" + value);
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent scae) {
        // 监听应用域修改键值对
        ServletContext application = scae.getServletContext();
        String key = scae.getName();
        Object oldValue = scae.getValue(); // 获取旧的值
        Object newValue = application.getAttribute(key); // 获取新的值
        System.out.println("应用域 " + application.hashCode() + " 修改了一个键值对" +
                "\nKey=" + key + ", oldValue=" + oldValue +
                "\nKey=" + key + ", value=" + newValue);
    }
}
