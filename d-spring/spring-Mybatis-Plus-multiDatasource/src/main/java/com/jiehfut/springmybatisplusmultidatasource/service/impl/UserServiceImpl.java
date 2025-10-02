package com.jiehfut.springmybatisplusmultidatasource.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiehfut.springmybatisplusmultidatasource.mapper.UserMapper;
import com.jiehfut.springmybatisplusmultidatasource.pojo.User;
import com.jiehfut.springmybatisplusmultidatasource.service.UserService;
import org.springframework.stereotype.Service;


@Service
@DS("master") // 声明该服务层使用的组件
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


}
