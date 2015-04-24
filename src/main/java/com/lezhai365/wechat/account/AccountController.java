package com.lezhai365.wechat.account;

import com.lezhai365.pms.sns.PersonalAuth;
import com.lezhai365.pms.sns.PersonalCheck;
import com.lezhai365.wechat.base.OauthService;
import com.lezhai365.wechat.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    /**
     *
     * 网页授权回调接口
     *
     * @param code
     * @param state
     * @return
     */
    @RequestMapping(value="/wap_auth")
    public ModelAndView applyAuthenticationView(
            @RequestParam("code") String code,
            @RequestParam("state") String state){

        ModelAndView mv = new ModelAndView();
        //TODO 根据code获取用户信息
        try {
            oauthService.getToken(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //TODO 带上微信用户信息,跳转到账号绑定页面
        mv.addObject("");
        mv.setViewName("");

        return mv;
    }

    /**
     * 进行房产认证
     * @return
     */
    @RequestMapping(value="/do/authentication")
    public ModelAndView doAuthentication(
            PersonalAuth personalAuth,
            PersonalCheck personalCheck){
        ModelAndView mv = new ModelAndView();

        return mv;
    }

    public OauthService getOauthService() {
        return oauthService;
    }

    public void setOauthService(OauthService oauthService) {
        this.oauthService = oauthService;
    }
}
