package com.lezhai365.wechat.controller;

import com.lezhai365.base.spi.user.IUserAccountService;
import com.lezhai365.common.config.WebAppConfig;
import com.lezhai365.common.model.CacheUser;
import com.lezhai365.common.web.CommonController;
import com.lezhai365.wechat.util.SignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
@Controller
public class BaseController{

    public static String APPID = "";

    public static String APPSECRET = "";


//    @Autowired
//    public IUserAccountService userAccountService;

    /**
     * 微信验证成为开发者
     * @param response
     * @param signature
     * @param timestamp
     * @param echostr
     * @param nonce
     * @throws IOException
     */
    @RequestMapping(value="/wechatcheck/signin")
    public void wechatCheck(
            HttpServletResponse response,
            @RequestParam String signature,//微信加密签名
            @RequestParam String timestamp,// 时间戳
            @RequestParam String echostr,  // 随机字符串
            @RequestParam String nonce) throws IOException {   // 随机数

        PrintWriter out = response.getWriter();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
        out = null;
    }

}
