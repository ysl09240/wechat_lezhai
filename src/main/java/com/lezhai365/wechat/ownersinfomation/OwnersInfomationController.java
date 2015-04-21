package com.lezhai365.wechat.ownersinfomation;

import com.lezhai365.base.spi.estate.IHousingEstateService;
import com.lezhai365.base.spi.user.IPersonalUserService;
import com.lezhai365.pms.spi.sns.ISNSUserHouseService;
import com.lezhai365.pms.spi.sns.ISNSUserService;
import com.lezhai365.wechat.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
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
@RequestMapping(value="/infomation")
public class OwnersInfomationController extends BaseController {
    @Autowired
    ISNSUserService SNSUserService;
    @Autowired
    ISNSUserHouseService SNSUserHouseService;
    @Autowired
    IHousingEstateService housingEstateService;
    @Autowired
    IPersonalUserService personalUserService;
    /**
     * 我的房产
     * 我的小区
     */

    public ModelAndView profileView(
            @RequestParam String tab){
        ModelAndView mv = new ModelAndView();
        Long userId = 110l;
        Map<String, Object> profile = personalUserService.queryPersonalDetail(userId);
        List<Map<String, Object>> estateList = housingEstateService.getEstateSimpleListByUserId(userId);
        mv.addObject("profile", profile);
        mv.addObject("estateList", estateList);
        try {
            mv.addObject("authList",SNSUserService.queryPersonalAuthDetail(userId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if("estate".equals(tab)){
            mv.setViewName("");
        }else{
            mv.setViewName("");
        }
        return mv;
    }












}
