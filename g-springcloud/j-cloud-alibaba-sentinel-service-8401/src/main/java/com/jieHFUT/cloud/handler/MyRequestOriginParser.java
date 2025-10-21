package com.jieHFUT.cloud.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;


/**
 * 授权规则中，调用方法的调用方信息需要通过 ContextUtil.enter(resourceName, origin) 中的 origin 传入
 * RequestOriginParser 请求来源
 * 这里是去配置黑白名单（授权规则）的调用者的信息入口
 * 来源的处理器转换，由这个来设置白名单还是黑名单
 *
 * 例如在 sentinel 中设置黑名单 /empower 路由的黑名单是 test1,test2，那么访问 /empower 携带参数 serverName=test1 就会被拦截
 *                                                             那么访问 /empower 携带参数 serverName=test2 就会被拦截
 */
@Component
public class MyRequestOriginParser implements RequestOriginParser {

    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {

        return httpServletRequest.getParameter("serverName");
    }

}