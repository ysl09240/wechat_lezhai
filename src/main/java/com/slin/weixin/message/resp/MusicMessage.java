package com.slin.weixin.message.resp;

/**
 * 类名: MusicMessage </br>
 * 描述: 音乐消息 </br>
 * @author SongLin.Yang
 * @data 2016-04-13 10:21
 */
public class MusicMessage extends BaseMessage {
    // 音乐
    private Music Music;

    public Music getMusic() {
        return Music;
    }

    public void setMusic(Music music) {
        Music = music;
    }
}
