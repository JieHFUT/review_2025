package com.jiehfut.redis.bredistemplate.controller;

import com.jiehfut.redis.bredistemplate.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "订单接口")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Operation(summary = "新增订单")
    @RequestMapping(value = "/order/add", method = RequestMethod.POST)
    public void addOrder() {
        orderService.addorder();
    }


    @Operation(summary = "按照 keyId 查询订单")
    @RequestMapping(value = "/order/{keyId}", method = RequestMethod.GET)
    public String getOrderById(@PathVariable("keyId") Integer keyId) {
        String orderById = orderService.getOrderById(keyId);
        return orderById;
    }


}
