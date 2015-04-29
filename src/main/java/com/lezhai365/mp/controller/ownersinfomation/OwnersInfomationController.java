package com.lezhai365.mp.controller.ownersinfomation;

import com.lezhai365.base.model.user.UserAccounts;
import com.lezhai365.base.spi.estate.IHousingEstateService;
import com.lezhai365.base.spi.user.IPersonalUserService;
import com.lezhai365.base.spi.user.IUserAccountService;
import com.lezhai365.common.model.ResultObject;
import com.lezhai365.pms.model.weixin.UserAuthApplyLog;
import com.lezhai365.pms.model.weixin.UserWx;
import com.lezhai365.pms.model.weixin.UserWxEstate;
import com.lezhai365.pms.spi.material.IHouseService;
import com.lezhai365.pms.spi.sns.ISNSUserHouseService;
import com.lezhai365.pms.spi.sns.ISNSUserService;
import com.lezhai365.mp.controller.BaseController;
import com.lezhai365.pms.spi.wechat.IUserWxAuthService;
import com.lezhai365.pms.spi.wechat.IUserWxService;
import org.apache.cxf.management.annotation.ManagedAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
//    @Autowired
//    IUserAccountService userAccountService;

    @Autowired
    IUserWxAuthService userWxAuthService;

    @Autowired
    IHouseService houseService;


    /**
     * 房产资料树
     *
     * @param housingEstateId 小区id
     * @param hipid
     * @param floor
     * @param childType
     * @param pid
     * @return
     */
    @RequestMapping(value = {"/house/tree"})
    public
    @ResponseBody
    ResultObject findTreeList(
            @RequestParam(value = "estateId") Long housingEstateId,
            @RequestParam(value = "id", required = false) Long pid,
            @RequestParam(value = "pid", required = false) Long hipid,
            @RequestParam(value = "floor", required = false) Long floor,
            @RequestParam(value = "childtype", required = false, defaultValue = "B") String childType) {

        ResultObject result = new ResultObject();
        //设置默认小区
        if (childType.equals("HI")) {
            pid = hipid;
        }
        List nodeList = null;
        nodeList = houseService.queryTreeChildNodes(housingEstateId, pid, floor, childType);
        result.setData(nodeList);
        return result;
    }
    /**
     * 我的房产
     * 我的小区
     */
    @RequestMapping(value="/myestate")
    public ModelAndView profileView(
            @RequestParam String openid,
            @PathVariable String signinName){
        ModelAndView mv = new ModelAndView();
        Long userWxId = null;
        Map<String,Object> userWxMap = getUserWx(signinName,openid);
        if(userWxMap!=null){
            userWxId = (Long) userWxMap.get("id");
            UserWxEstate userWxEstateWhere = new UserWxEstate();
            userWxEstateWhere.setUserWxId(userWxId);

            List<Map<String, Object>> estateList = userWxAuthService.queryMyEstate(userWxEstateWhere);
            mv.addObject("estateList", estateList);
            mv.addObject("signinName", signinName);
            mv.addObject("openid", openid);
        }

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
            @RequestParam String openid,
            @RequestParam(value = "houseEstateId" ,required = false) Long houseEstateId){
        ModelAndView mv = new ModelAndView();

        Map<String,Object> userWxMap = getUserWx(signinName,openid);

        if(userWxMap != null){

            Long userWxId = (Long) userWxMap.get("id");
            if(houseEstateId == null){
                houseEstateId = (Long) userWxMap.get("defaultEstateId");
            }
            List<Map<String,Object>> myHouseList = userWxAuthService.queryMyHouse(userWxId,houseEstateId);

            mv.addObject("signinName",signinName);
            mv.addObject("myHouseList",myHouseList);
            mv.addObject("openid", openid);
            mv.addObject("houseEstateId", houseEstateId);
        }
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
            @RequestParam String openid,
            @RequestParam Long houseInfoId,
            @RequestParam Long houseEstateId,
            @PathVariable String signinName){
        ModelAndView mv = new ModelAndView();
        UserAccounts userAccounts = userAccountService.queryUserInfoBySigninName(signinName);
        if(userAccounts != null){
            Long pmcUserId = userAccounts.getId();
            int flag = userWxService.doDefaultEstateAnHouse(houseEstateId, houseInfoId, pmcUserId, openid);
        }

        mv.addObject("openid", openid);
        mv.addObject("signinName", signinName);
        mv.setViewName("redirect:/"+signinName+"/infomation/myhouse");
        return mv;

    }

    /**
     * 认证房产视图
     * @param signinName
     * @return
     */
    @RequestMapping(value="/authhouse")
    public ModelAndView authHouseView(
            @RequestParam String openid,
            @PathVariable String signinName){
        ModelAndView mv = new ModelAndView();

        Map<String,Object> userWxMap = getUserWx(signinName,openid);
        if(userWxMap != null){
            Long userWxId = (Long) userWxMap.get("id");

            UserWxEstate userWxEstateWhere = new UserWxEstate();
            userWxEstateWhere.setUserWxId(userWxId);

            List<Map<String, Object>> estateList = userWxAuthService.queryMyEstate(userWxEstateWhere);
            mv.addObject("estateList", estateList);
            mv.addObject("signinName", signinName);
            mv.addObject("openid", openid);
        }
        mv.setViewName("propertyinfomation/applyauth");

        return mv;

    }

    /**
     * 认证
     * @param openid
     * @param userAuthApplyLog
     * @param signinName
     * @return
     */
    @RequestMapping(value="/doauthhouse",method = RequestMethod.POST)
    public ModelAndView doAuthHouse(
            @RequestParam String openid,
            @ModelAttribute UserAuthApplyLog userAuthApplyLog,
            @PathVariable String signinName){
        ModelAndView mv = new ModelAndView();

        Map<String,Object> userWxMap = getUserWx(signinName,openid);
        if(userWxMap != null){
            Long userWxId = (Long) userWxMap.get("id");
            userAuthApplyLog.setUserWxId(userWxId);
            int flag = userWxAuthService.addUserWxAuth(userAuthApplyLog);
        }
        mv.addObject("signinName",signinName);
        mv.addObject("openid", openid);
        mv.setViewName("redirect:/"+signinName+"/infomation/myhouse");

        return mv;

    }

}
