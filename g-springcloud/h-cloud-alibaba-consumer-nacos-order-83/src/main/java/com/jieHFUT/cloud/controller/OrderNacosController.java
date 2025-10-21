package com.jieHFUT.cloud.controller;

import com.jiehfut.cloud.apis.PayFeignSentinelApi;
import com.jiehfut.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class OrderNacosController {
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private PayFeignSentinelApi payFeignSentinelApi;

    @Value("${service-url.nacos-user-service}") // nacos-payment-service
    private String serverURL;

    @GetMapping("/consumer/pay/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Integer id) {
        String result = restTemplate.getForObject(serverURL + "/pay/nacos/" + id, String.class);
        return result + "\t" + " 我是 OrderNacosController83 调用者...";
    }

    // 进行 feign 和 sentinel 对异常降级的统一处理
    @GetMapping(value = "/consumer/pay/nacos/get/{orderNo}")
    public ResultData getPayByOrderNo(@PathVariable("orderNo") String orderNo) {
        return payFeignSentinelApi.getPayByOrderNo(orderNo);
    }


}
