package com.slin.wechat;

import com.alibaba.fastjson.JSONObject;
import com.slin.wechat.utils.ConfigUtil;
import com.slin.wechat.utils.HttpUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
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
    public  String getAccessToken(String appid, String secret) throws Exception {
        String jsonStr = HttpUtil.get(ACCESSTOKEN_URL.concat("&appid=") + appid + "&secret=" + secret);
        Map<String, Object> map = JSONObject.parseObject(jsonStr);
        System.out.println("------------token");
        System.out.println(map);
        return map.get("access_token").toString();
    }

    /**
     * 获取accessToken info
     * @param appid
     * @param secret
     * @return
     * @throws Exception
     */
    public  Map getAccessTokenInfo(String appid, String secret) throws Exception {
        String jsonStr = HttpUtil.get(ACCESSTOKEN_URL.concat("&appid=") + appid + "&secret=" + secret);
        Map<String, Object> map = JSONObject.parseObject(jsonStr);
        return map;
    }

    /**
     * 创建菜单
     * @param pmcSignName
     * @return
     * @throws Exception
     */
    public String menuCreate(String pmcSignName) throws Exception {
        String accessToken = getAccessToken(ConfigUtil.APPID,ConfigUtil.APPSECRET);
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken;
        String menuFile = WeChatOpenApi.class.getResource("/").getPath()+ "WeChatMenu.json";
        String menuStr = FileUtils.readFileToString(new File(menuFile),"utf-8");
        menuStr = menuStr.replaceAll("SIGNNAME",pmcSignName);
        String res = HttpUtil.post(url,menuStr);
        System.out.println("------------------------");
        System.out.println(res);
        return res;
    }

    public String menuGet(){

        return "";
    }

    public String menuDelete(){

        return "";
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

}
