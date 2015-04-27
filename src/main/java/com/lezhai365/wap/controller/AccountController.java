package com.lezhai365.wap.controller;

import com.alibaba.fastjson.JSONObject;
import com.lezhai365.common.config.WebAppConfig;
import com.lezhai365.common.model.CacheUser;
import com.lezhai365.common.model.UserType;
import com.lezhai365.common.web.util.CookieUtil;
import com.lezhai365.wechat.OauthService;
import com.lezhai365.wechat.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author :  SongLin.Yang [ysl09240@gmail.com]
 * @version :  1.0
 * @encoding : UTF-8
 * @package : com.lezhai365.wechat.account
 * @link :  http://lezhai365.com
 * @created on   :  15-4-20
 * @copyright :  Copyright(c) 2013 西安乐宅网络科技有限公司
 * @description :
 */
@Controller
@RequestMapping(value="/account")
public class AccountController extends BaseController {

    public  static Long userId = 8l;

    OauthService oauthService;
    UserService userService;

    /**
     *
     * 网页授权回调接口
     *
     * @param code
     * @param state
     * @return
     */
    @RequestMapping(value="/wx_callback")
    public ModelAndView applyAuthenticationView(
            @RequestParam("pmcSigninName") String pmcSigninName,
            @RequestParam("code") String code,
            @RequestParam("state") String state){

        System.out.println("-----------wx callback-------------------");
        ModelAndView mv = new ModelAndView();
        JSONObject tokenInfo = null;
        JSONObject userInfo = null;
        try {
            //TODO 根据 code 获取 token
            tokenInfo = oauthService.getTokenInfo(code);
            //TODO 根据access_token 和openid获取
            userInfo = userService.getUserInfo(tokenInfo.getString("access_token"), tokenInfo.getString("openid"));
            //TODO 根据openid查询对应用户信息,如果不存在，则跳转到绑定账号页面(注册页面)

        } catch (Exception e) {
            e.printStackTrace();
        }
        //TODO 带上微信用户信息,跳转到账号绑定页面
        mv.addObject("tokenInfo", tokenInfo);
        mv.addObject("userInfo", userInfo);
        mv.addObject("signinName", pmcSigninName);
        mv.setViewName("account/signup");

        return mv;
    }


    /*******************************用户登录*******************************/

    /**
     * 进入登录页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public ModelAndView signinView(
            @RequestParam("pmcSigninName") String pmcSigninName,
            HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView result = new ModelAndView();

        System.out.println("-------------go signin----------------------");
        //获取当前cookie
        Cookie cookie = CookieUtil.getCookie(WebAppConfig.APP_TOKEN_KEY);
        String codeUrl = "/error.jsp";

        //默认采用微信登录，如果用户没有登录，直接跳转到微信登录授权页面
        if (cookie == null) {
            try {
                codeUrl = oauthService.getCodeUrl(pmcSigninName);
                System.out.println("coder url:");
                System.out.println(codeUrl);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            result.setViewName(this.redirectURI(codeUrl));

        } else {
            CacheUser user = getCacheUser(response);
            //用户登录信息存储到cookie
            setCacheUser(user, response);
            //TODO 查询默认房产信息,如果常住房产为空，跳转到我的常住小区页面，绑定常住房产
            if (user.getEstateList().size() > 0) {
                if (user.getUserType() == UserType.USER_TYPE_PERSONAL) {
                    //TODO 回跳到来源页面
                    result.setViewName("redirect:/estate/center");
                }
            } else {
                //跳转到关联房产页面
                result.setViewName("redirect:/estate/join");
            }

        }
        return result;
    }

    public OauthService getOauthService() {
        return oauthService;
    }

    public void setOauthService(OauthService oauthService) {

        this.oauthService = oauthService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
