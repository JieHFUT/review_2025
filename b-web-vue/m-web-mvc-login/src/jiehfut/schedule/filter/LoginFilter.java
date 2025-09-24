package jiehfut.schedule.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jiehfut.hfut.schedule.pojo.SysUser;

import java.io.IOException;


@WebFilter(
        urlPatterns = {"/showSchedule.html","/schedule/*"}
)
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 参数父转子
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 客户端进行请求
        // 获得 session 域对象
        HttpSession session = request.getSession();
        // 从 session 域对象中去获取登陆的用户信息 sysUser 对象
        SysUser sysUser = (SysUser) session.getAttribute("sysUser");
        // 如果对象为空 => 没有进行登陆
        if (null == sysUser) {
            // 重定向
            response.sendRedirect("/login.html");
        }
        // 进行了登陆 => 放行
        filterChain.doFilter(request, response);

    }
}
