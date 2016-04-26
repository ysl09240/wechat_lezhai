package com.slin.weixin.controller;

import com.slin.weixin.pojo.SNSUserInfo;
import com.slin.weixin.pojo.WeixinOauth2Token;
import com.slin.weixin.service.CoreService;
import com.slin.weixin.util.CommonUtil;
import com.slin.weixin.util.ConfigUtil;
import com.slin.weixin.util.SignUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * 类名: CoreController </br>
 * 描述: 来接收微信服务器传来信息 </br>
 * @author SongLin.Yang
 * @data 2016-04-13 10:36
 */
@Controller
@RequestMapping(value = "/{wechatName}")
public class CoreController extends BaseController {

    /**
     *
     * @param request
     * @param signature 微信加密签名
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @param echostr 随机字符串
     * @param wechatName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"","/","/index"}, method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String index(
            HttpServletRequest request,
            @RequestParam(required = false) String signature,
            @RequestParam(required = false) String timestamp,
            @RequestParam(required = false) String nonce,
            @RequestParam(required = false) String echostr,
            @PathVariable String wechatName) throws Exception {
        String result = "error";
        //如果带有echostr
        if(echostr !=null && StringUtils.isNotBlank(echostr)){
            if(SignUtil.checkSignature(signature, timestamp, nonce)){
                //第一次认证的时候创建菜单
//                weChatService.menuCreate(wechatName);
                result = echostr;
            }
        } else {
           result = CoreService.processRequest(request);
        }
        return result;

    }

    /**
     *授权后的回调请求处理
     * @param code 用户同意授权后获得的code
     * @param state
     * @return
     */
    @RequestMapping(value="/oauthCallback",method=RequestMethod.GET)
    @ResponseBody
    public ModelAndView oAuthCallback(
         @RequestParam String code,
         @RequestParam String state){
        ModelAndView mv = new ModelAndView();
        if (!"authdeny".equals(code)) {
            // 获取网页授权access_token
            WeixinOauth2Token weixinOauth2Token = CommonUtil.getOauth2AccessToken(ConfigUtil.APPID, ConfigUtil.APPSECRET, code);
            // 网页授权接口访问凭证
            String accessToken = weixinOauth2Token.getAccessToken();
            // 用户标识
            String openId = weixinOauth2Token.getOpenId();
            // 获取用户信息
            SNSUserInfo snsUserInfo = CommonUtil.getSNSUserInfo(accessToken, openId);
            // 设置要传递的参数
            mv.addObject("snsUserInfo",snsUserInfo);
            mv.addObject("state",state);
            mv.setViewName("index");
        }
        return mv;

    }



}
