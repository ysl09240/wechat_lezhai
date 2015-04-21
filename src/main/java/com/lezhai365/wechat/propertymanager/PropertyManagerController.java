package com.lezhai365.wechat.propertymanager;

import com.lezhai365.common.model.Page;
import com.lezhai365.pms.spi.wechat.IPropertyManagerService;
import com.lezhai365.wechat.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author :  SongLin.Yang [ysl09240@gmail.com]
 * @version :  1.0
 * @encoding : UTF-8
 * @package : com.lezhai365.wechat.controller
 * @link :  http://lezhai365.com
 * @created on   :  15-4-20
 * @copyright :  Copyright(c) 2013 西安乐宅网络科技有限公司
 * @description :
 */
@Controller
@RequestMapping(value="/manager")
public class PropertyManagerController extends BaseController {
    @Autowired
    IPropertyManagerService propertyManagerService;



/**
 * 小区通知
 * 物业介绍
 * 服务指南
 * 常用电话
 */
    /**
     * 小区通知
     * @param pageSize
     * @param pageIndex
     * @return
     */
    public ModelAndView getNoticeList(
            @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize,
            @RequestParam(value = "pageIndex", defaultValue = "1")Integer pageIndex){
        ModelAndView mv = new ModelAndView();
        Long estateId = 110l;
        Page<Map<String,Object>> noticList = propertyManagerService.queryNoticeListSNS(pageSize,pageIndex,estateId);
        mv.addObject("noticeList",noticList);
        mv.setViewName("");
        return mv;


    }

    /**
     * 物业介绍
     * @return
     */
    public ModelAndView getPropertyIntroduce(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("");
        return mv;
    }

    /**
     * 服务指南
     * @return
     */
    public ModelAndView getPropertyGuide(){
        ModelAndView mv = new ModelAndView();
        //common method
        mv.setViewName("");
        return mv;
    }

    /**
     * 常用电话
     * @return
     */
    public ModelAndView getCommonPhone(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("");
        return mv;
    }











}
