package jiehfut.schedule.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * ClassName: BaseController
 * Package: jie.hfut.schedule.controller
 * Description:
 * 将前后端交互路径判别的方法封装到一个 Controller 类中
 * 其他的 Controller 类就不用去判断路径要使用哪一种行为
 * 只需要实现该有的行为处理即可
 * @Author jieHFUT
 * @Create 2024/11/24 13:26
 * @Version 1.0
 */
public class BaseController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 需要判断此次的请求是哪一种行为（增、删、改、查）
        String URI = req.getRequestURI(); // 拿到的 URI 就是形如：/schedule/add
        // 通过判断最后的 /** 获得具体行为
        String[] split = URI.split("/");
        String method = split[split.length - 1];

        Class aClass = this.getClass();
        // 获取方法
        try {
            // 获取方法
            Method declaredMethod = aClass.getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            // 设置暴力破解方法的访问修饰符
            declaredMethod.setAccessible(true);
            // 执行方法
            declaredMethod.invoke(this, req, resp);
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n没有实现该方法：" + method);
        }

    }
}
