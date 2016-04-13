package com.slin.weixin.message.req;

/**
 * 类名: TextMessage </br>
 * 描述: 请求消息之文本消息 </br>
 * @author SongLin.Yang
 * @data 2016-04-13 10:05
 */
public class TextMessage extends BaseMessage {
    // 消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
