package com.jiehfut.cloud.apis;

import com.jiehfut.cloud.fallback.PayFeignSentinelApiFallBack;
import com.jiehfut.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ClassName: S
 * Package: com.jiehfut.cloud.apis
 * Description:
 * 进行 openfeign 整合 sentinel 来统一处理异常进行降级的服务接口
 * @Author jieHFUT
 * @Create 2025/10/21 13:53
 * @Version 1.0
 */

// 接口是访问 9001 nacos-payment-provider，微服务调用这个接口如果发生异常需要降级，统一去找 PayFeignSentinelApiFallBack
@FeignClient(value = "nacos-payment-provider", fallback = PayFeignSentinelApiFallBack.class)
public interface PayFeignSentinelApi {

    @GetMapping("/pay/nacos/get/{orderNo}")
    public ResultData getPayByOrderNo(@PathVariable("orderNo") String orderNo);

}
