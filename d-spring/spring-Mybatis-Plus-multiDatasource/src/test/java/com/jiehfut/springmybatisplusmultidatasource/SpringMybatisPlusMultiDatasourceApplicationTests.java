package com.jiehfut.springmybatisplusmultidatasource;

import com.jiehfut.springmybatisplusmultidatasource.pojo.Product;
import com.jiehfut.springmybatisplusmultidatasource.pojo.User;
import com.jiehfut.springmybatisplusmultidatasource.service.ProductService;
import com.jiehfut.springmybatisplusmultidatasource.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringMybatisPlusMultiDatasourceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;


    /**
     * 测试多数据源进行查询操作
     */
    @Test
    public void testMuliDatasource(){
        User user = userService.getById(1);
        System.out.println(user);

        Product product = productService.getById(1);
        System.out.println(product);
    }

}
