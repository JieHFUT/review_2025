package com.jiehfut.listener.request;

import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;

/**
 * ClassName: RequestListener
 * Package: com.jiehfut.listener.request
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/9/26 3:31
 * @Version 1.0
 */

@WebListener
public class RequestListener implements ServletRequestListener, ServletRequestAttributeListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        // 任何一个请求域对象的建立都会触发该监听器方法的执行
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        // 任何一个请求域对象的销毁都会触发该监听器方法的执行
    }

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        // 任何一个请求域对象的数据建立都会触发该监听器方法的执行
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        // 任何一个请求域对象的数据修改都会触发该监听器方法的执行
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        // 任何一个请求域对象的数据删除都会触发该监听器方法的执行
    }
}
