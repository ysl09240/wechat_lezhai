package com.slin.weixin.message.resp;

/**
 * 类名: ImageMessage </br>
 * 描述: 图片消息</br>
 * @author SongLin.Yang
 * @data 2016-04-13 10:16
 */
public class ImageMessage extends BaseMessage {

    private Image Image;

    public Image getImage() {
        return Image;
    }

    public void setImage(Image image) {
        Image = image;
    }
}
