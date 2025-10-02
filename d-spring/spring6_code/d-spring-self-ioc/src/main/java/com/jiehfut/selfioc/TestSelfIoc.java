package com.jiehfut.selfioc;

import com.jiehfut.selfioc.bean.AnnotationApplicationContext;
import com.jiehfut.selfioc.bean.ApplicationContext;
import com.jiehfut.selfioc.service.UserService;
import com.jiehfut.selfioc.service.impl.UserServiceImpl;

public class TestSelfIoc {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationApplicationContext("com.jiehfut.selfioc");
        UserServiceImpl userServiceImpl = (UserServiceImpl) context.getBean(UserService.class);
        System.out.println("userServiceImpl = " + userServiceImpl);
        userServiceImpl.add();
    }
}
