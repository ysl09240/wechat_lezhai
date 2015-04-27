package com.lezhai365.mp.controller;

import com.lezhai365.wechat.WeChatService;
import com.lezhai365.wechat.utils.SignatureUtil;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * 需要在微信公众号，开发者中心填写的URL(服务器地址url)
 *
 * @author :  Huzi.Wang [huzi.wh@gmail.com]
 * @version :  1.0
 * @encoding : UTF-8
 * @package : com.lezhai365.mp.controller
 * @link :  http://lezhai365.com
 * @created on   :  2015-04-27
 * @copyright :  Copyright(c) 2013 西安乐宅网络科技有限公司
 * @description :
 */

@Controller
@RequestMapping(value="/{pmcSiginName}/wechat")
public class WeChatController {

    WeChatService weChatService = new WeChatService();

    /**
     * <pre>
     *
     * 需要在微信公众号，开发者中心填写的URL(服务器地址url)
     * URL: wx.lezhai365.com/{pmcSiginName}/wechat, {pmcSiginName}为动态参数，对应物业公司的signName
     *
     * </pre>
     *
     * @param signature String 微信加密签名
     * @param timestamp String 时间戳
     * @param nonce  String 随机数
     * @param echostr String 随机字符串
     * @param pmcSiginName
     * @return
     */
    @RequestMapping(value = {"","/","/index"},
            //同时接收get和post
            method = {RequestMethod.GET,RequestMethod.POST}, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String index(
            HttpServletRequest request,
            @RequestParam(required = false) String signature,
            @RequestParam(required = false) String timestamp,
            @RequestParam(required = false) String nonce,
            @RequestParam(required = false) String echostr,
            @PathVariable String pmcSiginName) throws Exception {
        String result = "error";
        System.out.println("物业:" +pmcSiginName);
        //如果带有echostr
        if(echostr !=null && StringUtils.isNotBlank(echostr)){
            System.out.println("-------------check token--------------");
            // 1.获取对应物业公司公众账号的token
            String token = "3lezhai65";
            if(SignatureUtil.checkSignature(token,signature,timestamp,nonce)){
                result = echostr;
            }
        } else {
            System.out.println("---------------------message---------------");
            try {
                InputStream inputStream = request.getInputStream();
                result = weChatService.processWxMsg(inputStream);

            } catch (IOException e) {
                System.out.println("error");
                e.printStackTrace();
            }
        }

        return result;
    }

}
