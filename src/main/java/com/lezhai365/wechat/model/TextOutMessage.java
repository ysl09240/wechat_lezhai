package com.lezhai365.wechat.model;

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

public class TextOutMessage extends OutMessage{

    private String	MsgType	= "text";
    // 文本消息
    private String	Content;

    public TextOutMessage() {
    }

    public TextOutMessage(String content) {
        Content = content;
    }

    public String getMsgType() {
        return MsgType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
