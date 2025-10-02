package com.jiehfut.springmybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiehfut.springmybatisplus.mapper.UserMapper;
import com.jiehfut.springmybatisplus.pojo.User;
import com.jiehfut.springmybatisplus.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


}
