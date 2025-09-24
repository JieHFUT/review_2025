package jiehfut.schedule.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jiehfut.schedule.common.Result;
import jiehfut.schedule.util.WebUtil;

import java.io.IOException;

/**
 * ClassName: CrosFilter
 * Package: jiehfut.schedule.filter
 * Description:
 * 跨域问题的过滤器，过滤全部资源
 *    如果请求是 OPTIONS 请求,就是跨域请求，响应允许跨域的信息，请求到此为止
 *    如果请求不是 OPTIONS 请求，直接放行
 * @Author jieHFUT
 * @Create 2024/12/20 21:55
 * @Version 1.0
 */
@WebFilter("/*")
public class CrosFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println(request.getMethod());
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 允许跨域，哪些域都行
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 哪些方法允许跨域
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT,OPTIONS, DELETE, HEAD");
        // 一次预检请求后，3600s 内再次跨域不用发送预检请求
        response.setHeader("Access-Control-Max-Age", "3600");
        //
        response.setHeader("Access-Control-Allow-Headers", "access-control-allow-origin, authority, content-type, version-info, X-Requested-With");
        // 如果是跨域预检请求,则直接在此响应 200 业务码
        if(request.getMethod().equalsIgnoreCase("OPTIONS")){
            // 跨域预检请求，直接响应，响应的内容无所谓，需要是成功的响应 200 即可，代表是一场成功的预检，然后客户端再次发送请求
            WebUtil.writeJson(response, Result.ok(null));
        }else{
            // 非预检请求,放行即可
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}