package com.lezhai365.wechat.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Huzi on 2015-04-23.
 */
public class ConfigUtil {

    private final static Properties props = new Properties();

    //AppId
    public static String APPID;

    //AppSecret
    public static String APPSECRET;

    public static String SCOPE;

    public static String REDIRECT_URI;

    //SMS server配置文件
    private static final String CONFIG_FILE = "wechat.properties";

    static {
        //加载redis配置文件
        InputStream is = ConfigUtil.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
        try {
            props.load(is);
        } catch (IOException ex) {
            Logger.getLogger(ConfigUtil.class.getName()).log(Level.SEVERE, "leid encoder error", ex);
        }

        //set value
        APPID = props.getProperty("AppId", "wx88e6ce5736b6184c");
        APPSECRET = props.getProperty("AppSecret", "245aec90f10d924766d4afabdc99bbbf");
        SCOPE = props.getProperty("scope");
        REDIRECT_URI = props.getProperty("redirect_uri");

    }


    public static String get(String key) {
        return props.getProperty(key);
    }

}
