package com.lezhai365.mp.controller.ownersinfomation;

import com.lezhai365.base.model.user.UserAccounts;
import com.lezhai365.base.spi.estate.IHousingEstateService;
import com.lezhai365.base.spi.user.IPersonalUserService;
import com.lezhai365.base.spi.user.IUserAccountService;
import com.lezhai365.pms.model.weixin.UserAuthApplyLog;
import com.lezhai365.pms.model.weixin.UserWx;
import com.lezhai365.pms.model.weixin.UserWxEstate;
import com.lezhai365.pms.spi.sns.ISNSUserHouseService;
import com.lezhai365.pms.spi.sns.ISNSUserService;
import com.lezhai365.mp.controller.BaseController;
import com.lezhai365.pms.spi.wechat.IUserWxAuthService;
import com.lezhai365.pms.spi.wechat.IUserWxService;
import org.apache.cxf.management.annotation.ManagedAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
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
@RequestMapping(value="/{signinName}/infomation")
public class OwnersInfomationController extends BaseController {
    @Autowired
    ISNSUserService SNSUserService;
    @Autowired
    ISNSUserHouseService SNSUserHouseService;
    @Autowired
    IHousingEstateService housingEstateService;
    @Autowired
    IPersonalUserService personalUserService;
    @Autowired
    IUserAccountService userAccountService;

    @Autowired
    IUserWxService userWxService;
    @Autowired
    IUserWxAuthService userWxAuthService;

    /**
     * 我的房产
     * 我的小区
     */
    @RequestMapping(value="/myestate")
    public ModelAndView profileView(
            @PathVariable String signinName){
        ModelAndView mv = new ModelAndView();
        Long userId = 351l;
        String openId = "";
        UserAccounts userAccounts = userAccountService.queryUserInfoBySigninName(signinName);
        userId = userAccounts.getId();

        UserWx where = new UserWx();
        where.setWeixinOpenid(openId);
        where.setPmcId(userId);
        Map<String,Object> userWxMap = userWxService.queryUserWxByPmcIdAndOpenId(where);
        Long userWxId = (Long) userWxMap.get("id");

        UserWxEstate userWxEstateWhere = new UserWxEstate();
        userWxEstateWhere.setUserWxId(userWxId);

        List<Map<String, Object>> estateList = userWxAuthService.queryMyEstate(userWxEstateWhere);
        mv.addObject("estateList", estateList);
        mv.addObject("signinName",signinName);
        mv.setViewName("propertyinfomation/myestate");
        return mv;
    }

    /**
     * 我的房产
     * @return
     */
    @RequestMapping(value="/myhouse")
    public ModelAndView myHouse(
            @PathVariable String signinName,
            @RequestParam(value = "houseEstateId" ,required = false) Long houseEstateId){
        ModelAndView mv = new ModelAndView();
        UserAccounts userAccounts = userAccountService.queryUserInfoBySigninName(signinName);
        Long pmcUserId = userAccounts.getId();
        Long userWxId = 0l;
        UserAuthApplyLog where = new UserAuthApplyLog();
        where.setUserWxId(userWxId);
        if(houseEstateId != null){
            where.setHousingEstateId(houseEstateId);
        }

        List<Map<String,Object>> myHouseList = userWxAuthService.queryMyHouse(where);

        mv.addObject("signinName",signinName);
        mv.addObject("myHouseList",myHouseList);
        mv.setViewName("propertyinfomation/myhouse");
        return mv;
    }

    /**
     * 设置默认房产
     * @param houseInfoId
     * @param houseEstateId
     * @return
     */
    @RequestMapping(value="/dodefault")
    public ModelAndView defaultEstateAndHouse(
            @RequestParam Long houseInfoId,
            @RequestParam Long houseEstateId){
        ModelAndView mv = new ModelAndView();
        String openId = "";
        Long pmcUserId = 0l;
        int flag = userWxService.doDefaultEstateAnHouse(houseEstateId,houseInfoId,pmcUserId,openId);

        mv.setViewName("propertyinfomation/myhouse");

        return mv;

    }

    /**
     * 认证房产
     * @param userAuthApplyLog
     * @param signinName
     * @return
     */
    @RequestMapping(value="/authhouse")
    public ModelAndView authHouse(
            @ModelAttribute UserAuthApplyLog userAuthApplyLog,
            @PathVariable String signinName){
        ModelAndView mv = new ModelAndView();

        int flag = userWxAuthService.addUserWxAuth(userAuthApplyLog);

        mv.setViewName("redirect:/"+signinName+"/infomation/myhouse");

        return mv;

    }






























































}
