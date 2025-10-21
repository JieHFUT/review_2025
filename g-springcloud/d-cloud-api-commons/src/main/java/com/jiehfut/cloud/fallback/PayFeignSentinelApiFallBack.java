package com.jiehfut.cloud.fallback;

import com.jiehfut.cloud.apis.PayFeignSentinelApi;
import com.jiehfut.cloud.resp.ResultData;
import com.jiehfut.cloud.resp.ReturnCodeEnum;
import org.springframework.stereotype.Component;

/**
 * ClassName: PayFeignSentinelApiFallBack
 * Package: com.jiehfut.cloud.fallback
 * Description:
 *
 * @Author jieHFUT
 * @Create 2025/10/21 14:00
 * @Version 1.0
 */
@Component
public class PayFeignSentinelApiFallBack implements PayFeignSentinelApi {
    @Override
    public ResultData getPayByOrderNo(String orderNo) {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(),"对方服务宕机或不可用，FallBack服务降级o(╥﹏╥)o");
    }
}