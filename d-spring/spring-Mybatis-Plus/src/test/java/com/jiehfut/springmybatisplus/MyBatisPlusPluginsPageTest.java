package com.jiehfut.springmybatisplus;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiehfut.springmybatisplus.mapper.UserMapper;
import com.jiehfut.springmybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisPlusPluginsPageTest {

    @Autowired
    private UserMapper userMapper;

    @Test // 测试分页插件的使用
    public void testPagePlugins(){
        Page<User> page = new Page<>(2, 3); // 当前页码，每一页条数
        userMapper.selectPage(page, null); // 查询所有记录
        System.out.println(page);
        // SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user
        // WHERE is_deleted=0 LIMIT ?,?

        System.out.println("page.getRecords = " + page.getRecords());
        System.out.println("page.getTotal = " + page.getTotal());
        System.out.println("page.getPages = " + page.getPages());
        System.out.println("page.hasNext = " + page.hasNext());
        System.out.println("page.hasPrevious = " + page.hasPrevious());
        // page.getRecords = [User(id=4, name=zhangsan, age=18, email=346122@163.com, isDeleted=0),
        //                    User(id=5, name=lisi, age=23, email=43652342@163.com, isDeleted=0),
        //                    User(id=6, name=dahuang, age=63, email=dahuang@163.com, isDeleted=0)]
        // page.getTotal = 7
        // page.getPages = 3
        // page.hasNext = true
        // age.hasPrevious = true
    }


    @Test
    public void testSelfPageByAge(){
        Page<User> page = new Page<>(2, 3);
        userMapper.selectPageVo(page, 20);
        System.out.println(page.getRecords());
        // select uid, user_name, age, email from t_user where age > ? LIMIT ?,?
    }







}
