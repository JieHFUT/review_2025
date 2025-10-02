package com.jiehfut.springmybatisplusmultidatasource.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiehfut.springmybatisplusmultidatasource.mapper.ProductMapper;
import com.jiehfut.springmybatisplusmultidatasource.pojo.Product;
import com.jiehfut.springmybatisplusmultidatasource.service.ProductService;
import org.springframework.stereotype.Service;


@Service
@DS("slave_1")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {


}
