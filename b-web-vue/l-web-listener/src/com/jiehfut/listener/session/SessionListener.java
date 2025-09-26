package com.jiehfut.listener.session;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * ClassName: SessionListener
 * Package: com.jiehfut.listener.request
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/9/26 3:28
 * @Version 1.0
 */


@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // 任何一个 session 域对象的建立都会触发该监听器方法的执行

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // 任何一个 session 域对象的销毁都会触发该监听器方法的执行

    }



    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        // 任何一个 session 域对象的键值对创建都会触发该方法

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        // 任何一个 session 域对象的键值对移除都会触发该方法

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        // 任何一个 session 域对象的键值对修改都会触发该方法

    }


}
