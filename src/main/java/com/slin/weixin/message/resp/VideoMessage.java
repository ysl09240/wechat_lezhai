package com.slin.weixin.message.resp;

/**
 * 类名: VideoMessage </br>
 * 描述: 视频消息 </br>
 * @author SongLin.Yang
 * @data 2016-04-13 10:20
 */
public class VideoMessage extends BaseMessage {
    // 视频
    private Video Video;

    public Video getVideo() {
        return Video;
    }

    public void setVideo(Video video) {
        Video = video;
    }
}
