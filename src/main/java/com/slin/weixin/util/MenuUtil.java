package com.slin.weixin.util;

import com.alibaba.fastjson.JSONObject;
import com.slin.weixin.menu.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类名: MenuUtil </br>
 * 描述: 菜单工具类 </br>
 * @author SongLin.Yang
 * @data 2016-04-13 15:54
 */
public class MenuUtil {
    private static Logger log = LoggerFactory.getLogger(MenuUtil.class);

    // 菜单创建（POST） 限100（次/天）
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    /**
     * 创建菜单
     *
     * @param menu 菜单实例
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static int createMenu(Menu menu, String accessToken) {
        int result = 0;
        // 拼装创建菜单的url
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSONObject.toJSONString(menu);
        // 调用接口创建菜单
        JSONObject jsonObject = CommonUtil.httpsRequest(url, "POST", jsonMenu);
        if (null != jsonObject) {
            if (0 != jsonObject.getIntValue("errcode")) {
                result = jsonObject.getIntValue("errcode");
                log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getIntValue("errcode"), jsonObject.getString("errmsg"));
            }
        }

        return result;
    }

}
