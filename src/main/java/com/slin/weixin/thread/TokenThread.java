package com.slin.weixin.thread;

import com.slin.weixin.pojo.AccessToken;
import com.slin.weixin.util.CommonUtil;
import com.slin.weixin.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author SongLin.Yang
 * @data 2016-04-13 14:32
 */
public class TokenThread implements Runnable {
    private static Logger log = LoggerFactory.getLogger(TokenThread.class);
    // 第三方用户唯一凭证
    public static String appid = "";
    // 第三方用户唯一凭证密钥
    public static String appsecret = "";
    public static AccessToken accessToken = null;

    public void run() {
        while (true) {
            try {
                accessToken = CommonUtil.getToken(appid, appsecret);
                if (null != accessToken) {
                    //调用存储到数据库
                    TokenUtil.saveToken(accessToken);
                    log.info("获取access_token成功，有效时长{}秒 token:{}", accessToken.getExpiresIn(), accessToken.getAccessToken());
                    // 休眠7000秒
                    Thread.sleep((accessToken.getExpiresIn() - 200)*1000);
                } else {
                    // 如果access_token为null，60秒后再获取
                    Thread.sleep(60 * 1000);
                }
            } catch (InterruptedException e) {
                try {
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException e1) {
                    log.error("{}", e1);
                }
                log.error("{}", e);
            }
        }
    }
}