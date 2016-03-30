package com.slin.wechat.model;

/**
 * @author :  Huzi.Wang [huzi.wh@gmail.com]
 * @version :  1.0
 * @encoding : UTF-8
 * @package : com.lezhai365.wechat.model
 * @link :  http://lezhai365.com
 * @created on   :  2015-04-27
 * @copyright :  Copyright(c) 2013 西安乐宅网络科技有限公司
 * @description :
 */
public class OutMessage {
    private String	ToUserName;
    private String	FromUserName;
    private Long	CreateTime;
    private int		FuncFlag	= 0;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public int getFuncFlag() {
        return FuncFlag;
    }

    public void setFuncFlag(int funcFlag) {
        FuncFlag = funcFlag;
    }
}
