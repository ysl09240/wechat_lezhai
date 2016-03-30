package com.slin.mp.filter;


import com.slin.wechat.OauthService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : Minty.Tong [tonglei@lezhai365.com]
 * @version : 1.0
 * @fileName : CheckFilter
 * @encoding : UTF-8
 * @package : com.lezhai365.web.filter
 * @link :  http://lezhai365.com
 * @created on  :  2014/9/29, 15:26
 * @copyright :  Copyright(c) 2014 西安乐宅网络科技有限公司
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

        if (tempArr.length < 1) {
            response.encodeRedirectURL("/error.jsp");
        } else {
            String pmcSignName = tempArr[1];
            String openid = request.getParameter("openid");

            // 如果uri不包含wechat，并且没有openid时进行授权
            if (!url.contains("wechat") && !url.contains("assets") && !url.contains("api/upload")&& (null == openid || "".equals(openid))) {
                //进行授权
                System.out.println("------------授权跳转");
                try {
                    System.out.println("oauth url:" + OauthService.getCodeUrl(uri, pmcSignName));
                    response.sendRedirect(OauthService.getCodeUrl(uri, pmcSignName));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }


    }

    @Override
    public void destroy() {
        System.out.println("-------------destroy check filter-----------------");
    }
}

