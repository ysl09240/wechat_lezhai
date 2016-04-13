package com.slin.weixin.message.resp;

/**
 * @author SongLin.Yang
 * @data 2016-04-13 10:18
 */
public class VoiceMessage extends BaseMessage {
    // 语音
    private Voice Voice;

    public Voice getVoice() {
        return Voice;
    }

    public void setVoice(Voice voice) {
        Voice = voice;
    }
}
