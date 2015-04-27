package com.lezhai365.wechat;

import com.alibaba.fastjson.JSONObject;
import com.lezhai365.wechat.utils.HttpUtil;
import org.apache.commons.lang.StringUtils;

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
public class UserService {
    private static final String USER_INFO_URI = "https://api.weixin.qq.com/cgi-bin/user/info";
    private static final String USER_GET_URI = "https://api.weixin.qq.com/cgi-bin/user/get";

    /**
     *
     * 拉取用户信息
     *
     * @param accessToken String
     * @param openid String
     * @return
     * @throws Exception
     */
    public JSONObject getUserInfo(String accessToken, String openid) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        JSONObject userInfo = null;
        params.put("access_token", accessToken);
        params.put("openid", openid);
        params.put("lang","zh_CN");

        String  jsonStr = HttpUtil.get(USER_INFO_URI, params);

        if(StringUtils.isNotEmpty(jsonStr)){
            JSONObject obj = JSONObject.parseObject(jsonStr);
            if(obj.get("errcode") != null){
                throw new Exception(obj.getString("errmsg"));
            }
            userInfo =  obj;
        }
        return userInfo;
    }

    /**
     *
     * 获取帐号的关注者列表
     *
     * @param accessToken
     * @param next_openid
     * @return
     */
    public JSONObject getFollwersList(String accessToken, String next_openid) throws Exception{
        Map<String, String> params = new HashMap<String, String>();
        params.put("access_token", accessToken);
        params.put("next_openid", next_openid);
        String  jsonStr = HttpUtil.get(USER_GET_URI, params);
        if(StringUtils.isNotEmpty(jsonStr)){
            JSONObject obj = JSONObject.parseObject(jsonStr);
            if(obj.get("errcode") != null){
                throw new Exception(obj.getString("errmsg"));
            }
            return obj;
        }
        return null;
    }

}
