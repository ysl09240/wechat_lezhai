package com.slin.weixin.controller;

import com.alibaba.fastjson.JSONObject;
import com.slin.wechat.OauthService;
import com.slin.wechat.WeChatService;
import com.slin.wechat.utils.SignatureUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value="/{pmcSiginName}")
public class WechatController extends  BaseController{

    OauthService oauthService = new OauthService();

    @Autowired
    WeChatService weChatService;

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
    @RequestMapping(value = {"","/","/index"}, method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String index(
            HttpServletRequest request,
            @RequestParam(required = false) String signature,
            @RequestParam(required = false) String timestamp,
            @RequestParam(required = false) String nonce,
            @RequestParam(required = false) String echostr,
            @PathVariable String pmcSiginName) throws Exception {
        String result = "error";
        //如果带有echostr
        if(echostr !=null && StringUtils.isNotBlank(echostr)){
            // 1.获取对应物业公司公众账号的token
            String token = "dasenglin";
            if(SignatureUtil.checkSignature(token,signature,timestamp,nonce)){
                //第一次认证的时候创建菜单
//                weChatService.menuCreate(pmcSiginName);
                result = echostr;
            }
        } else {
            System.out.println("---------------------message---------------");
            try {
                InputStream inputStream = request.getInputStream();
//                UserAccounts userAccounts = new UserAccounts();
                result = weChatService.processWxMsg(inputStream);
            } catch (IOException e) {
                System.out.println("error");
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     *
     * 网页授权回调接口
     *
     * @param code
     * @param state
     * @return
     */
    @RequestMapping(value="/callback")
    public ModelAndView callback(
            @PathVariable String pmcSiginName,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String state) throws Exception {

        System.out.println("-----------wx callback-------------------");
        String refer = "";//Decoder.symmetricDecrypto(state);
        ModelAndView mv = new ModelAndView();
        JSONObject tokenInfo = null;
        try {
            //TODO 根据 code 获取 token
            tokenInfo = oauthService.getTokenInfo(code);
            //TODO 根据access_token 和openid获取
            String openId = tokenInfo.getString("openid");
            System.out.println("refer:" + refer);
            mv.setViewName("redirect:" + refer + "?openid=" + openId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mv;
    }
}
