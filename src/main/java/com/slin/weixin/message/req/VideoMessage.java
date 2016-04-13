package com.slin.weixin.message.req;

/**
 * 类名: VideoMessage </br>
 * 描述: 请求消息之视频消息 </br>
 * @author SongLin.Yang
 * @data 2016-04-13 10:08
 */
public class VideoMessage  extends BaseMessage{

    // 媒体ID
    private String MediaId;
    // 语音格式
    private String ThumbMediaId;

    public String getMediaId() {
        return MediaId;
    }
    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
    public String getThumbMediaId() {
        return ThumbMediaId;
    }
    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }



}
