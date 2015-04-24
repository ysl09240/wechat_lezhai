package com.lezhai365.wechat.base;

import com.alibaba.fastjson.JSONObject;
import com.lezhai365.wechat.util.ConfigUtil;
import com.lezhai365.wechat.util.HttpUtil;

import java.util.Map;

/**
 *
 *  微信api
 *
 * @author :  Huzi.Wang [huzi.wh@gmail.com]
 * @version :  1.0
 * @encoding : UTF-8
 * @package : com.lezhai365.wechat.base
 * @link :  http://lezhai365.com
 * @created on   :  2015-04-22
 * @copyright :  Copyright(c) 2013 西安乐宅网络科技有限公司
 * @description :
 */
public class WeChat {

    private static final String ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
    private static final String PAYFEEDBACK_URL = "https://api.weixin.qq.com/payfeedback/update";
    private static final String GET_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=";
    private static final String UPLOAD_MEDIA_URL = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=";
    private static final String JSAPI_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=";

    /**
     * 获取access_token
     *
     * @return
     * @throws Exception
     */
    public static String getAccessToken() throws Exception {
        String appid = ConfigUtil.get("AppId");
        String secret = ConfigUtil.get("AppSecret");
        String jsonStr = HttpUtil.get(ACCESSTOKEN_URL.concat("&appid=") + appid + "&secret=" + secret);
        Map<String, Object> map = JSONObject.parseObject(jsonStr);
        return map.get("access_token").toString();
    }

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
