package jiehfut.schedule.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import jiehfut.schedule.common.Result;

import java.io.IOException;

/**
 * 专门封装，用于向客户端响应 JSON 串的类
 */
public class WebUtil {

    private static ObjectMapper objectMapper = null;

    public static void writeJson(HttpServletResponse resp, Result result) {
        // 告诉客户端响应是一种 JSON 串
        resp.setContentType("application/json;charset=utf-8");

        // 把 result 对象转换成为 JSON 串
        objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(result);
            // 如果没有该用户 => 响应可用
            resp.getWriter().write(json);
        } catch (IOException e) {
            System.out.println(e.getMessage() + " result 对象转换称为 JSON 串发给客户端出现异常！");
        }
    }
}
