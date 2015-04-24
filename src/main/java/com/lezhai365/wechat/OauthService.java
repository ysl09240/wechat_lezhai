package com.lezhai365.wechat;

import com.alibaba.fastjson.JSONObject;
import com.lezhai365.wechat.utils.ConfigUtil;
import com.lezhai365.wechat.utils.HttpUtil;
import com.lezhai365.wechat.utils.SignatureUtil;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

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
     * 获取 code url
     *
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getCodeUrl() throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", getAppid());
        params.put("response_type", "code");
        params.put("redirect_uri", ConfigUtil.REDIRECT_URI);
        params.put("scope", ConfigUtil.SCOPE); // snsapi_base（不弹出授权页面，只能拿到用户openid）snsapi_userinfo
        // （弹出授权页面，这个可以通过 openid 拿到昵称、性别、所在地）
        params.put("state", "365#wechat_redirect");
        String para = SignatureUtil.createSign(params, false);
        return CODE_URI + "?" + para;
    }

    /**
     *
     * 通过code 换取 access_token
     *
     * @param code String
     * @return Map<String,Object>
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException
     * @throws NoSuchProviderException
     */
    public JSONObject getTokenInfo(String code) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        JSONObject tokenInfo = null;
        params.put("appid", getAppid());
        params.put("secret", getSecret());
        params.put("code", code);
        params.put("grant_type", "authorization_code");

        String jsonStr = HttpUtil.get(TOKEN_URI, params);

        if(StringUtils.isNotEmpty(jsonStr)){
            JSONObject obj = JSONObject.parseObject(jsonStr);
            if(obj.get("errcode") != null){
                throw new Exception(obj.getString("errmsg"));
            }
            tokenInfo =  obj;
        }

        return tokenInfo;
    }

    /**
     * 刷新 access_token
     * @param refreshToken
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException
     * @throws NoSuchProviderException
     */
    public Map<String,Object>  getRefreshToken(String refreshToken) throws InterruptedException, ExecutionException, NoSuchAlgorithmException, KeyManagementException, IOException, NoSuchProviderException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", getAppid());
        params.put("grant_type", "refresh_token");
        params.put("refresh_token", refreshToken);

        String jsonStr = HttpUtil.get(REFRESH_TOKEN_URI, params);
        Map<String,Object> tokenInfo = JSONObject.parseObject(jsonStr);

        return tokenInfo;
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
        String code = ou.getCodeUrl();
        System.out.println(code);
    }
}
