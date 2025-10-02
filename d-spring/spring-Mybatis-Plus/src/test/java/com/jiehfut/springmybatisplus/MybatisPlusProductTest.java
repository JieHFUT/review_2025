package com.jiehfut.springmybatisplus;


import com.jiehfut.springmybatisplus.mapper.ProductMapper;
import com.jiehfut.springmybatisplus.mapper.UserMapper;
import com.jiehfut.springmybatisplus.pojo.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisPlusProductTest {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;

    @Test  // 没有添加乐观锁插件的时候
    public void testProduct(){
        // 小李查询商品价格
        Product productL = productMapper.selectById(1);
        System.out.println("小李查询的商品价格为：" + productL.getPrice());

        // 小王查询商品价格
        Product productW = productMapper.selectById(1);
        System.out.println("小王查询的商品价格为：" + productW.getPrice());

        // 小李将价格上调 50
        productL.setPrice(productL.getPrice() + 50);
        productMapper.updateById(productL);
        // 小王将价格下调 30
        productW.setPrice(productW.getPrice() - 30);
        productMapper.updateById(productW);

        // 最终老板查询价格
        Product product = productMapper.selectById(1);
        System.out.println("boss check the price = " + product.getPrice());
        // 小李查询的商品价格为：100
        // 小王查询的商品价格为：100
        // boss check the price = 70
    }


    /**
     * 使用乐观锁来实现
     * 每次查询的时候都会多查询一个版本号
     * 在更新数据的时候携带版本号作为查询条件
     * 如果版本号被修改，则更新失败
     */
    @Test
    public void testOptimisticLockerInnerInterceptor(){
        // 小李查询商品价格
        Product productL = productMapper.selectById(1);
        System.out.println("小李查询的商品价格为：" + productL.getPrice());

        // 小王查询商品价格
        Product productW = productMapper.selectById(1);
        System.out.println("小王查询的商品价格为：" + productW.getPrice());

        // 小李将价格上调 50
        productL.setPrice(productL.getPrice() + 50);
        productMapper.updateById(productL);
        // UPDATE t_product SET name=?, price=?, version=? WHERE id=? AND version=?
        // 小王将价格下调 30
        productW.setPrice(productW.getPrice() - 30);
        productMapper.updateById(productW);
        // UPDATE t_product SET name=?, price=?, version=? WHERE id=? AND version=?

        // 最终老板查询价格
        Product product = productMapper.selectById(1);
        System.out.println("boss check the price = " + product.getPrice());
        // 小李查询的商品价格为：100
        // 小王查询的商品价格为：100
        // boss check the price = 150
        
    }



    @Test // 优化第二次操作，操作失败重新操作
    public void test(){
        // 小李查询商品价格
        Product productL = productMapper.selectById(1);
        System.out.println("小李查询的商品价格为：" + productL.getPrice());

        // 小王查询商品价格
        Product productW = productMapper.selectById(1);
        System.out.println("小王查询的商品价格为：" + productW.getPrice());

        // 小李将价格上调 50
        productL.setPrice(productL.getPrice() + 50);
        productMapper.updateById(productL);
        // UPDATE t_product SET name=?, price=?, version=? WHERE id=? AND version=?
        // 小王将价格下调 30
        productW.setPrice(productW.getPrice() - 30);
        int i = productMapper.updateById(productW);
        // UPDATE t_product SET name=?, price=?, version=? WHERE id=? AND version=?
        if (i == 0) {
            // 操作失败，重新尝试
            Product product = productMapper.selectById(1);
            product.setPrice(product.getPrice() - 30);
            productMapper.updateById(product);
        }

        // 最终老板查询价格
        Product product = productMapper.selectById(1);
        System.out.println("boss check the price = " + product.getPrice());
        // 小李查询的商品价格为：100
        // 小王查询的商品价格为：100
        // boss check the price = 120
    }

    





}
