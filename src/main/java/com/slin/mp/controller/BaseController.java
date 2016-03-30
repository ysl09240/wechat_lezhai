package com.slin.mp.controller;

import org.springframework.stereotype.Controller;

/**
 * @author :  Minty.Tong [tonglei@lezhai365.com]
 * @version :  1.0
 * @fileName :  BaseController
 * @encoding :  UTF-8
 * @package :  com.lezhai365.web.controller
 * @link :  http://lezhai365.com
 * @created on  :  2014/10/24, 11:29
 * @copyright :  Copyright(c) 2014 西安乐宅网络科技有限公司
 * @description :
 */
@SuppressWarnings("ALL")
@Controller
public class BaseController{

    //登录页面视图地址
    public static String SIGIN_VIEW_PAGE = "redirect:/account/signin";


     /**
     * <p>
     * 根据cookie中的leid从redis cache中取,cache user
     * 如果，cookie中存在leid,cache中不存在,则调用业务(UserAccountService.getCacheUser())
     * 如果，cookie中不存在leid,需要用户重新登录
     * </p>
     *
     * @param response
     * @return CacheUser
     */


}
