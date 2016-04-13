package com.slin.weixin.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @created on  :  2014/9/29, 15:26
 * @copyright :  Copyright(c) 2014
 * @description : 登陆过滤
 */
public class CheckFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("-------------init check filter-----------------");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURL().toString();
        String uri = request.getRequestURI();
        //取物业公司的signinName,如果没有，就跳转到错误页面
        String[] tempArr = uri.split("\\/");

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        System.out.println("-------------destroy check filter-----------------");
    }
}

