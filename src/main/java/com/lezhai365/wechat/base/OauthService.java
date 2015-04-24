package com.lezhai365.wechat.base;

import com.lezhai365.wechat.util.ConfigUtil;
import com.lezhai365.wechat.util.HttpUtil;
import com.lezhai365.wechat.util.SignatureUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author :  Huzi.Wang [huzi.wh@gmail.com]
 * @version :  1.0
 * @encoding : UTF-8
 * @package : com.lezhai365.wechat.base
 * @link :  http://lezhai365.com
 * @created on   :  2015-04-23
 * @copyright :  Copyright(c) 2013 西安乐宅网络科技有限公司
 * @description :
 */
public class OauthService {

    private static final String CODE_URI = "http://open.weixin.qq.com/connect/oauth2/authorize";
    private static final String TOKEN_URI = "https://api.weixin.qq.com/sns/oauth2/access_token";
    private static final String REFRESH_TOKEN_URI = "https://api.weixin.qq.com/sns/oauth2/refresh_token";

    private String appid;
    private String secret;

    public OauthService() {
        super();
        this.appid = ConfigUtil.APPID;
        this.secret = ConfigUtil.APPSECRET;
    }

    public OauthService(String appid, String secret) {
        super();
        this.appid = appid;
        this.secret = secret;
    }

    /**
     * 获取code
     *
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getCode() throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", getAppid());
        params.put("response_type", "code");
        params.put("redirect_uri", ConfigUtil.REDIRECT_URI);
        params.put("scope", ConfigUtil.SCOPE); // snsapi_base（不弹出授权页面，只能拿到用户openid）snsapi_userinfo
        // （弹出授权页面，这个可以通过 openid 拿到昵称、性别、所在地）
        params.put("state", "3");
        String para = SignatureUtil.createSign(params, false);
        return CODE_URI + "?" + para;
    }

    /**
     * 通过code 换取 access_token
     * @param code
     * @return
     * @throws IOException
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public String getToken(String code) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", getAppid());
        params.put("secret", getSecret());
        params.put("code", code);
        params.put("grant_type", "authorization_code");
        return HttpUtil.get(TOKEN_URI, params);
    }

    /**
     * 刷新 access_token
     * @param refreshToken
     * @return
     * @throws IOException
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public String getRefreshToken(String refreshToken) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", getAppid());
        params.put("grant_type", "refresh_token");
        params.put("refresh_token", refreshToken);
        return HttpUtil.get(REFRESH_TOKEN_URI, params);
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        OauthService ou = new OauthService();
        String code = ou.getCode();
        System.out.println(code);
    }
}
