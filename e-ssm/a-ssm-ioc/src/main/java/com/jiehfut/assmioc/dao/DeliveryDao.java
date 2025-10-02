package com.jiehfut.assmioc.dao;

import com.jiehfut.assmioc.datasource.DataSources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DeliveryDao {


    /**
     * 1.运行环境的设置：数据源有三个
     * 能不能自动设置什么情况下，哪种数据源才会生效，每次只会激活一个 @Conditional
     * @Profile("环境标识")
     */
    @Autowired
    DataSources dataSources;


    public void saveDelivery(){
        System.out.println("数据源来保存数据 " + dataSources);
    }
}
