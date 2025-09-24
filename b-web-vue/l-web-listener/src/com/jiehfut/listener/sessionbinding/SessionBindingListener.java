package com.jiehfut.listener.sessionbinding;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;


@WebListener
public class SessionBindingListener implements HttpSessionBindingListener {

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        // 当前监听器实例放入某一个 session 对象中作为数据，绑定
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        // 当前监听器实例从某一个 session 对象中移除，解除绑定

    }
}

