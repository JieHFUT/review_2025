package com.jiehfut.httpservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

/**
 * 发现无论是 GET 还是 POST 请求，只要是满足 key-value 类型的数据都可以通过以下 API 捕获，无论其在 URL 中还是在请求体中
 *
 * GET 请求可以有请求体，但是通过 form 表单标签提交请求的时候，不会往请求体里面放数据，会把 key-value 缀在 URL 后面
 * POST 请求在发送请求的时候，会把 key-value 放在请求体里面
 *
 */
@WebServlet("/httpServletRequestHead")
public class HttpServletRequestHeadTest extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收请求中的参数
        // 根据参数名来获取单个值 => 键值对
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("username = " + username);
        System.out.println("password = " + password);

        // 多个值的参数
        String[] hobbies = req.getParameterValues("hobby");
        for (String hobby : hobbies) {
            System.out.println("hobby = " + hobby);
        }


        // 先获取所有的参数名
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            String[] parameterValues = req.getParameterValues(parameterName);
            if (parameterValues.length > 1)
                System.out.println(parameterName + " = " + Arrays.toString(parameterValues));
            else
                System.out.println(parameterName + " = " + parameterValues[0]);
        }



        // 返回所有参数的 map 集合   【key = 参数名】 【value = 参数值】
        System.out.println("=================map.get.key.value======================");

        // 每一个做键值都认为其是一个数组
        Map<String, String[]> parameterMap = req.getParameterMap();
        // 所有的键值
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
            // 参数名
            String key = entry.getKey();
            // 参数值
            String[] value = entry.getValue();

            // 输出
            for (String v : value) {
                System.out.println(key + " = " + v);
            }
        }























        /**
         * 获得请求体中的非键值对信息？ JSON 串、文件
         *
         *
         * 获得一个从请求体中读取字符的字符输入流
         * BufferedReader reader = req.getReader();  JSON 串
         *
         *
         * 获得一个从请求体中读取二进制数据字节的输入流
         * ServletInputStream inputStream = req.getInputStream(); 文件
         *
         *
         */



    }
}
