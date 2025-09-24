package com.jiehfut.filterchain;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;


@WebFilter("/*")
public class FilterA implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filtera before dofilter invoked");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("filtera after dofilter invoked");
    }
}
