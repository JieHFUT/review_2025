package com.jiehfut.cloud.customize;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * 自己定义一个过滤器用来统计所有调用接口的耗时情况
 * 自定义全局过滤器
 *
 * todo: Ordered 过滤器的调用优先级
 */
@Component //不要忘记
@Slf4j
public class MyGlobalFilter implements GlobalFilter, Ordered {

    private static final String BEGIN_VISIT_TIME = "begin_visit_time"; // 开始调用接口的时间

    /**
     *
     * @param exchange 这个对象是一个网关的核心组件，可以对请求中的属性等进行修改
     * @param chain 网关的过滤器链
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 记录下访问接口的时间
        exchange.getAttributes().put(BEGIN_VISIT_TIME, System.currentTimeMillis());
        // 2. 调用接口实现控制层方法
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            // 获取调用的开始时间
            Long beginTime = exchange.getAttribute(BEGIN_VISIT_TIME);

            if (beginTime != null) {
                // 被调用
                log.info("访问接口主机："      + exchange.getRequest().getURI().getHost());
                log.info("访问接口端口："      + exchange.getRequest().getURI().getPort());
                log.info("访问接口URL："      + exchange.getRequest().getURI().getPath());
                log.info("访问接口URL参数："   + exchange.getRequest().getURI().getRawQuery());
                log.info("访问接口时长："      + (System.currentTimeMillis() - beginTime) + " ms");
                log.info("===========================================");
                System.out.println();
            }
        }));
    }

    @Override
    public int getOrder() {
        return 0; // 返回的数值越小，调用越靠前
    }
}