package com.lezhai365.wap.controller.propertymanager;

import com.lezhai365.common.model.Page;
import com.lezhai365.pms.model.Notice;
import com.lezhai365.pms.spi.office.INoticeService;
import com.lezhai365.pms.spi.wechat.IPropertyManagerService;
import com.lezhai365.wap.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author :  SongLin.Yang [ysl09240@gmail.com]
 * @version :  1.0
 * @encoding : UTF-8
 * @package : com.lezhai365.wap.controller
 * @link :  http://lezhai365.com
 * @created on   :  15-4-20
 * @copyright :  Copyright(c) 2013 西安乐宅网络科技有限公司
 * @description :
 */
@Controller
@RequestMapping(value="/{signinName}/manager")
public class PropertyManagerController extends BaseController {
    @Autowired
    IPropertyManagerService propertyManagerService;
    @Autowired
    INoticeService noticeService;

    public static Long estateId = 103500l;
    public static Long userId = 351l;



/**
 * 小区通知
 * 物业介绍
 * 服务指南
 * 常用电话
 */
    /**
     * 小区通知列表
     * @param pageSize
     * @param pageIndex
     * @return
     */
    @RequestMapping(value="/notice")
    public ModelAndView getNoticeList(
            @PathVariable String signinName,
            @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize,
            @RequestParam(value = "pageIndex", defaultValue = "1")Integer pageIndex){
        ModelAndView mv = new ModelAndView();
//        Long estateId = 110l;
        Page<Map<String,Object>> noticeList = propertyManagerService.queryNoticeListSNS(pageSize,pageIndex,estateId);
        mv.addObject("noticeList",noticeList);
        mv.addObject("signinName",signinName);
        mv.setViewName("propertymanager/notice");
        return mv;


    }

    /**
     * 通知详情
     * @param noticeId
     * @return
     */
    @RequestMapping(value="/detail/notice")
    public ModelAndView getNoticeDetail(
            @RequestParam Long noticeId){
        ModelAndView mv = new ModelAndView();
        Notice notice = new Notice();
        notice.setHousingEstateId(estateId);
        notice.setId(noticeId);
        Map<String,Object> noticeMap = noticeService.queryOneNotice(notice);
        mv.addObject("noticeMap",noticeMap);
        mv.setViewName("propertymanager/notice_detail");
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
    @RequestMapping(value="/guide")
    public ModelAndView getPropertyGuide(){
        ModelAndView mv = new ModelAndView();
            mv.setViewName("propertymanager/guide");
        //common method
        return mv;
    }
    /**
     * 服务指南详情
     * @return
     */
    @RequestMapping(value="/detail/guide")
    public ModelAndView getPropertyGuideDetail(
            @RequestParam Integer item){
        ModelAndView mv = new ModelAndView();

        mv.setViewName("propertymanager/guide_detail_"+item);
        //common method
        return mv;
    }

    /**
     * 常用电话
     * @return
     */
    @RequestMapping(value="/commonphone")
    public ModelAndView getCommonPhone(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("propertymanager/common_phone");
        return mv;
    }











}
