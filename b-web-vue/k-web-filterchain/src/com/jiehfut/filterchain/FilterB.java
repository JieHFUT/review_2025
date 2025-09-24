package com.jiehfut.filterchain;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;


@WebFilter("/*")
public class FilterB implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filterb before dofilter invoked");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("filterb after dofilter invoked");
    }
}
