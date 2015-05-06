package com.lezhai365.mp.controller;

import com.lezhai365.nosql.mongo.Mongodb;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
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

    /**
     * 编辑添加公众号信息视图
     * @return
     */
    @RequestMapping(value = {"mpInfo/edit"})
    public ModelAndView editView() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mgr/mpInfoEdit");
        return mv;
    }

    /**
     * 公众号列表
     * @return
     */
    @RequestMapping(value = {"mpInfo/list"})
    public ModelAndView mpInfoListView() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mgr/mpInfoList");
        return mv;
    }

    /**
     * 编辑公众号
     * @return
     */
    @RequestMapping(value = {"mpInfo/do/edit"})
    public ModelAndView doEdit() {

        return null;
    }

}
