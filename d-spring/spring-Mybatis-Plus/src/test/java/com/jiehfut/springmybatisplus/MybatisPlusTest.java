package com.jiehfut.springmybatisplus;


import com.jiehfut.springmybatisplus.mapper.UserMapper;
import com.jiehfut.springmybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MybatisPlusTest {

    @Autowired
    UserMapper userMapper;

    @Test // todo:测试获取全部记录
    public void testMybatisPlusTest(){
        // selectList()根据 MP 内置的条件构造器查询一个list集合，null表示没有条件（Wrapper<T>），即查询所有
        userMapper.selectList(null).forEach(System.out::println);
    }


    @Test // todo:测试向数据库中添加一条记录
    public void testInsert(){
        User user = new User();
        user.setName("zhangsan");
        user.setAge(18);
        user.setEmail("3492779706@qq.com");
        int insert = userMapper.insert(user);
        System.out.println("insert = " + insert);
        System.out.println("id = " + user.getId());
        // insert = 1
        // id = 1872219133313503233
        /**
         * 在 mybatis-plus 中，是使用雪花算法得出 id
         * INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
         */
    }


    @Test // todo:测试删除记录
    public void testDeleteById(){
        // 有多种删除方式
        int delete = userMapper.deleteById(1872221496573132802L);
        System.out.println("delete = " + delete);
        // DELETE FROM user WHERE id=?
    }
    @Test
    public void testDeleteByMap(){
        Map<String, Object> map = new HashMap<>();
        // 删除条件是 AND 的关系
        map.put("name", "zhangsan");
        map.put("id", 3);
        int delete = userMapper.deleteByMap(map);
        System.out.println("delete = " + delete);

        // DELETE FROM user WHERE (name = ? AND id = ?)
    }
    @Test // 多个 ID 实现批量删除
    public void testDeleteByIds(){
        List<Long> list = Arrays.asList(2L, 3L);
        int batchIds = userMapper.deleteBatchIds(list);
        System.out.println("batchIds = " + batchIds);
        // DELETE FROM user WHERE id IN ( ? , ? )
        // 添加逻辑删除字段后 ：UPDATE t_user SET is_deleted=1 WHERE uid IN ( ? , ? ) AND is_deleted=0
    }



    @Test // 测试修改记录的方法
    public void testUpdate(){
        User user = new User();
        user.setId(1872219626836271105L);
        user.setName("lisi");
        user.setAge(28);
        user.setEmail("34325706@qq.com");
        int i = userMapper.updateById(user);
        System.out.println("i = " + i);
        // UPDATE user SET name=?, age=?, email=? WHERE id=?
    }


    @Test // 查询数据
    public void testSelectById(){
        User user = userMapper.selectById(1872219626836271105L);
        System.out.println("user = " + user);
        // SELECT id,name,age,email FROM user WHERE id=?
        // 添加逻辑删除字段后 ：SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE uid=? AND is_deleted=0
    }

    @Test // 根据多个 ID 查询多条记录
    public void testSelectByList(){
        List<Long> list = Arrays.asList(1L, 2L, 3L);
        List<User> users = userMapper.selectBatchIds(list);
        System.out.println("users = " + users);
        // SELECT id,name,age,email FROM user WHERE id IN ( ? , ? , ? )
    }

    @Test // 多条件查询
    public void testSelectByMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("age", 18);
        List<User> users = userMapper.selectByMap(map);
        System.out.println("users = " + users);
        // SELECT id,name,age,email FROM user WHERE (name = ? AND age = ?)
    }

    @Test // 无条件查询所有用户记录
    public void testSelectByNull(){
        List<User> users = userMapper.selectList(null);
        System.out.println("users = " + users);
        // SELECT id,name,age,email FROM user
    }

    @Test // 自定义的查询 sql 语句
    public void testSelfFunction(){
        Map<String, Object> map = userMapper.selectMapById(1L);
        System.out.println("map = " + map);
    }

    /**
     * mybatis-plus
     * 实现了一个 IService接口 && 实现类 ServiceImpl，
     * 封装了常见的业务逻辑
     */







}
