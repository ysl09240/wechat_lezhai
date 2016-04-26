import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.slin.weixin.pojo.WeixinUserInfo;
import com.slin.weixin.util.*;
import com.slin.weixin.pojo.AccessToken;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author SongLin.Yang
 * @data 2016-04-13 13:38
 */
public class TokenTest {
    private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
    @Test
    public void testGetToken1() throws Exception {
        String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ConfigUtil.APPID+"&secret="+ConfigUtil.APPSECRET;
        // 建立连接
        URL url = new URL(tokenUrl);
        HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();

        // 创建SSLContext对象，并使用我们指定的信任管理器初始化
        TrustManager[] tm = { new MyX509TrustManager() };
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        sslContext.init(null, tm, new java.security.SecureRandom());
        // 从上述SSLContext对象中得到SSLSocketFactory对象
        SSLSocketFactory ssf = sslContext.getSocketFactory();

        httpUrlConn.setSSLSocketFactory(ssf);
        httpUrlConn.setDoOutput(true);
        httpUrlConn.setDoInput(true);

        // 设置请求方式（GET/POST）
        httpUrlConn.setRequestMethod("GET");

        // 取得输入流
        InputStream inputStream = httpUrlConn.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(
                inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        // 读取响应内容
        StringBuffer buffer = new StringBuffer();
        String str = null;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
        }
        bufferedReader.close();
        inputStreamReader.close();
        // 释放资源
        inputStream.close();
        httpUrlConn.disconnect();
        // 输出返回结果
        System.out.println(buffer);
    }

    @Test
    public void testGetToken2() {
        AccessToken token = CommonUtil.getToken(ConfigUtil.APPID,ConfigUtil.APPSECRET);
        System.out.println("access_token:"+token.getAccessToken());
        System.out.println("expires_in:"+token.getExpiresIn());
    }

    @Test
    public void testGetToken3() {
        String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ConfigUtil.APPID+"&secret="+ConfigUtil.APPSECRET;
        AccessToken token = null;
        try {
            JSONObject jsonObject = JSONObject.parseObject(HttpUtil.get(tokenUrl));
            if (null != jsonObject) {
                try {
                    token = new AccessToken();
                    token.setAccessToken(jsonObject.getString("access_token"));
                    token.setExpiresIn(jsonObject.getIntValue("expires_in"));
                } catch (JSONException e) {
                    token = null;
                    // 获取token失败
                    log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getIntValue("errcode"), jsonObject.getString("errmsg"));
                }
            }
            System.out.println("access_token:"+token.getAccessToken());
            System.out.println("expires_in:"+token.getExpiresIn());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSaveToken4() {
        AccessToken token = CommonUtil.getToken(ConfigUtil.APPID,ConfigUtil.APPSECRET);
        TokenUtil.saveToken(token);
    }
    @Test
    public void testGetUserInfo() {
        AccessToken token = CommonUtil.getToken(ConfigUtil.APPID,ConfigUtil.APPSECRET);
        // 获取接口访问凭证
        String accessToken = CommonUtil.getToken(ConfigUtil.APPID, ConfigUtil.APPSECRET).getAccessToken();
        /**
         * 获取用户信息
         */
        WeixinUserInfo user = CommonUtil.getUserInfo(accessToken, "ooK-yuJvd9gEegH6nRIen-gnLrVw");
        System.out.println("OpenID：" + user.getOpenId());
        System.out.println("关注状态：" + user.getSubscribe());
        System.out.println("关注时间：" + user.getSubscribeTime());
        System.out.println("昵称：" + user.getNickname());
        System.out.println("性别：" + user.getSex());
        System.out.println("国家：" + user.getCountry());
        System.out.println("省份：" + user.getProvince());
        System.out.println("城市：" + user.getCity());
        System.out.println("语言：" + user.getLanguage());
        System.out.println("头像：" + user.getHeadImgUrl());
    }

}
