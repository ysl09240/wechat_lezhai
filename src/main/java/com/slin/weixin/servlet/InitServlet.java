package com.slin.weixin.servlet;

import com.slin.weixin.thread.TokenThread;
import com.slin.weixin.util.CommonUtil;
import com.slin.weixin.util.ConfigUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * 类名: InitServlet </br>
 * 描述: 初始化servlet </br>
 * @author SongLin.Yang
 * @data 2016-04-13 14:38
 */
public class InitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

    public void init() throws ServletException {
        // 获取web.xml中配置的参数
//        TokenThread.appid = getInitParameter("appid");
//        TokenThread.appsecret = getInitParameter("appsecret");

        TokenThread.appid = ConfigUtil.APPID;
        TokenThread.appsecret = ConfigUtil.APPSECRET;

        log.info("weixin api appid:{}", TokenThread.appid);
        log.info("weixin api appsecret:{}", TokenThread.appsecret);

        // 未配置appid、appsecret时给出提示
        if ("".equals(TokenThread.appid) || "".equals(TokenThread.appsecret)) {
            log.error("appid and appsecret configuration error, please check carefully.");
        } else {
            // 启动定时获取access_token的线程
            new Thread(new TokenThread()).start();
        }
    }
}
