package com.jiehfut.bssmaop.service.impl;

import com.jiehfut.bssmaop.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Override
    public void saveUser() {
        System.out.println("save user....");
    }
}
