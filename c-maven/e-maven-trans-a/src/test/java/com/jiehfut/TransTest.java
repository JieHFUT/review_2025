package com.jiehfut;

import com.alibaba.druid.pool.DruidDataSource;

public class TransTest {


    /**
     b 依赖了 druid && junit && servlet，而 a 依赖了 b
            compile   test    provided
     并不是所有的依赖都能依赖传递
     compile 可以传递，test && provided 不能传递
     不能传递的依赖需要自己配置依赖
     */

/*    @Test
    public void test(){
        DruidDataSource druidDataSource = new DruidDataSource();
        Servlet servlet = new Servlet();
    }*/

}
