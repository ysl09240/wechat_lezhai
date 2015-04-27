package com.lezhai365.wechat.model;

/**
 *
 * 微信消息类型
 *
 * @author :  Huzi.Wang [huzi.wh@gmail.com]
 * @version :  1.0
 * @encoding : UTF-8
 * @package : com.lezhai365.wechat.model
 * @link :  http://lezhai365.com
 * @created on   :  2015-04-27
 * @copyright :  Copyright(c) 2013 西安乐宅网络科技有限公司
 * @description :
 */
public enum MsgTypes {
    TEXT("text"),
    LOCATION("location"),
    IMAGE("image"),
    LINK("link"),
    VOICE("voice"),
    EVENT("event"),
    VIDEO("video"),
    NEWS("news"),
    MUSIC("music"),
    VERIFY("verify");
    private String type;

    MsgTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
