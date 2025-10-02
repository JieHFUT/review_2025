package com.jiehfut.factorybean;

import org.springframework.beans.factory.FactoryBean;

public class MFactoryBean implements FactoryBean<User> {

    // 在 xml 文件中配置好该类的信息后，返回的对象类型是此方法返回的对象
    @Override
    public User getObject() throws Exception {
        return new User();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
