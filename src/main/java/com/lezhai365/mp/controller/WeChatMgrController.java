package com.lezhai365.mp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * 微信公众账号管理后台
 * 1.
 *
 * @author :  Huzi.Wang [huzi.wh@gmail.com]
 * @version :  1.0
 * @encoding : UTF-8
 * @package : com.lezhai365.mp.controller
 * @link :  http://lezhai365.com
 * @created on   :  2015-05-04
 * @copyright :  Copyright(c) 2013 西安乐宅网络科技有限公司
 * @description :
 */
@Controller
@RequestMapping(value = "/{pmcSiginName}/wechat/mgr")
public class WeChatMgrController {

    @RequestMapping(value = {"", "/", "/index"})
    public ModelAndView indexView() {

        return null;
    }

    @RequestMapping(value = {"edit"})
    public ModelAndView editView() {

        return null;
    }


    @RequestMapping(value = {"do/edit"})
    public ModelAndView doEditView() {

        return null;
    }

}
