package com.jiehfut.servletcontext.domain;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/contextDomain2")
public class ServletDomainAnother extends HttpServlet {

    /**
     * ServletContext 是本应用中最大的域对象
     * 1.void setAttribute(String key, Object value)  向域中存储或者修改数据
     * 2.Object getAttribute(String key)              获得域中的数据
     * 3.void removeAttribute(String key)             移除域中的数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();

        // 1.void setAttribute(String key, Object value)  向域中存储或者修改数据
        servletContext.setAttribute("secondContextDomain", "secondDomainValue");
        // 2.Object getAttribute(String key)              获得域中的数据
        String domainValue = (String) servletContext.getAttribute("firstContextDomain");
        System.out.println("second service get = " + domainValue);

        // 3.void removeAttribute(String key)             移除域中的数据

    }
}
