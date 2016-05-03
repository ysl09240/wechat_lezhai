package com.slin.weixin.controller;

import com.slin.weixin.pojo.User;
import com.slin.weixin.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author SongLin.Yang
 * @data 2016-04-29 15:54
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    private IUserService userService;

    @RequestMapping("/getUserById")
    public ModelAndView showUser(HttpServletRequest request){

        ModelAndView mv = new ModelAndView();

        User user = userService.getUser(1);
        mv.setViewName("login");

        return mv;
    }

}
