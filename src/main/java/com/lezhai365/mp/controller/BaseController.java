package com.lezhai365.mp.controller;

import com.lezhai365.base.model.user.UserAccounts;
import com.lezhai365.base.spi.user.IUserAccountService;
import com.lezhai365.common.config.WebAppConfig;
import com.lezhai365.common.model.CacheUser;
import com.lezhai365.common.web.CommonController;
import com.lezhai365.pms.model.weixin.UserWx;
import com.lezhai365.pms.spi.wechat.IUserWxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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
public class BaseController extends CommonController{

    //登录页面视图地址
    public static String SIGIN_VIEW_PAGE = "redirect:/account/signin";

    @Autowired
    public IUserAccountService userAccountService;

    @Autowired
    public  IUserWxService userWxService;

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
    public CacheUser getCacheUser(HttpServletResponse response) {
        CacheUser cacheUser = null;
        String userId = "";
        String time = "";
        String strUserId = getLeIdFromClient();
        String[] objs = getUserIdAndTimeFromClient(strUserId);
        if (objs != null) {
            userId = objs[0];
            time = objs[1];
        }

        if (!"".equals(userId)) {
            Object obj = redisCache.get(userId);
            if (obj != null) {
                cacheUser = (CacheUser) obj;
            } else {
                cacheUser = userAccountService.cacheUser(Long.parseLong(userId));
            }
            //重新设置cacheUser的存活时间
            cacheUser.setExpireAt(WebAppConfig.APP_TOKEN_KEY_IDLE);
            redisCache.put(userId, cacheUser,WebAppConfig.APP_TOKEN_KEY_IDLE);
           //重置cookie的存活时间
            updateCookie(response);
        }
        return cacheUser;
    }

    public Map<String,Object> getUserWx(String pmcSignName,String openId){

        UserAccounts userAccounts = userAccountService.queryUserInfoBySigninName(pmcSignName);
        Long pmcUserId =null;
        Map<String,Object> userWxMap = null;
        if(userAccounts!=null){
            pmcUserId = userAccounts.getId();
            UserWx where = new UserWx();
            where.setWeixinOpenid(openId);
            where.setPmcId(pmcUserId);
            userWxMap = userWxService.queryUserWxByPmcIdAndOpenId(where);
        }

        return userWxMap;
    }

}
