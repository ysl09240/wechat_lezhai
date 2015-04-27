package com.lezhai365.wechat;

import com.alibaba.fastjson.JSONObject;
import com.lezhai365.wechat.utils.HttpUtil;

import java.util.Map;

/**
 * @author :  Huzi.Wang [huzi.wh@gmail.com]
 * @version :  1.0
 * @encoding : UTF-8
 * @package : com.lezhai365.wechat
 * @link :  http://lezhai365.com
 * @created on   :  2015-04-27
 * @copyright :  Copyright(c) 2013 西安乐宅网络科技有限公司
 * @description :
 */
public class WeChatOpenApi {
    private static final String ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
    String appId;
    String appSecret;


    /**
     * 获取access_token
     *
     * @return
     * @throws Exception
     */
    public static String getAccessToken(String appid, String secret) throws Exception {
        String jsonStr = HttpUtil.get(ACCESSTOKEN_URL.concat("&appid=") + appid + "&secret=" + secret);
        Map<String, Object> map = JSONObject.parseObject(jsonStr);
        return map.get("access_token").toString();
    }
}
