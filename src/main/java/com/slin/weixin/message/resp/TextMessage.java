package com.slin.weixin.message.resp;

/**
 *  类名: TextMessage </br>
 * 描述: 文本消息 </br>
 * @author SongLin.Yang
 * @data 2016-04-13 10:15
 */
public class TextMessage extends BaseMessage {
    // 回复的消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}