package com.jiehfut.listener.sessionactivationlistener;

import jakarta.servlet.http.HttpSessionActivationListener;
import jakarta.servlet.http.HttpSessionEvent;


public class SessionActivationListener implements HttpSessionActivationListener {
    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        // session 对象即将进行钝化之前执行

    }

    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        // session 活化完毕之后执行

    }
}
