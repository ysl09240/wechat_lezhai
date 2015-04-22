package com.lezhai365.wechat.account;

import com.lezhai365.pms.sns.PersonalAuth;
import com.lezhai365.pms.sns.PersonalCheck;
import com.lezhai365.wechat.controller.BaseController;
import de.ruedigermoeller.serialization.annotations.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping(value="/applyAuth")
    public ModelAndView applyAuthenticationView(){
        ModelAndView mv = new ModelAndView();
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


}
