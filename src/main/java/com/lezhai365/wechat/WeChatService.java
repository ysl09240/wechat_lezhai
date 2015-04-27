package com.lezhai365.wechat;

import com.lezhai365.wechat.model.InMessage;
import com.lezhai365.wechat.model.TextOutMessage;
import com.lezhai365.wechat.utils.XStreamUtil;
import com.thoughtworks.xstream.XStream;

import java.io.InputStream;
import java.util.Date;

/**
 *
 * 微信业务处理
 *
 * @author :  Huzi.Wang [huzi.wh@gmail.com]
 * @version :  1.0
 * @encoding : UTF-8
 * @package : com.lezhai365.wechat
 * @link :  http://lezhai365.com
 * @created on   :  2015-04-27
 * @copyright :  Copyright(c) 2013 西安乐宅网络科技有限公司
 * @description :
 */
public class WeChatService extends WeiXinApi {
    public String processWxMsg(InputStream xmlInputStream){
        String result = "";
        //转换微信post过来的xml内容
        XStream xs = XStreamUtil.init(false);
        xs.ignoreUnknownElements();
        xs.alias("xml", InMessage.class);

        InMessage msg = (InMessage) xs.fromXML(xmlInputStream);
        String event = msg.getEvent();
        String MsgType = msg.getMsgType();
        System.out.println("=============================================");
        System.out.println(msg);
        System.out.println("=============================================");


        if(msg.getMsgType().equals("event")){
            switch (event) {
                case "subscribe"://关注
                    System.out.println("关注");
                    // 获取用户信息，

                    break;
                case "unsubscribe"://取消关注
                    System.out.println("取消关注");
                    break;
                case "SCAN"://扫描
                    System.out.println("扫描");
                    break;
                case "LOCATION"://地址
                    System.out.println("地址");
                    break;
                case "CLICK"://点击事件
                    System.out.println("点击事件");
                    //提醒用户绑定房产
                    break;
                case "VIEW"://跳转
                    System.out.println("跳转");
                    break;
                case "card_pass_check"://卡券审核通过
                    System.out.println("卡券审核通过");
                    break;
                case "card_not_pass_check"://卡券审核失败
                    System.out.println("卡券审核失败");
                    break;
                case "user_get_card"://用户领取卡券
                    System.out.println("用户领取卡券");
                    break;
                case "user_del_card"://用户删除卡券
                    System.out.println("用户删除卡券");
                    break;
                case "user_view_card"://用户浏览会员卡
                    System.out.println("用户浏览会员卡");
                    break;
                case "user_consume_card"://用户核销卡券
                    System.out.println("用户核销卡券");
                    break;
                case "merchant_order"://微小店用户下单付款
                    System.out.println("微小店用户下单付款");
                    break;
                default:
                    System.out.println("unkonw event");
                    break;
            }
        } else {
            switch (MsgType) {
                case "text"://文本格式
                    System.out.println("文本消息");
                    break;
                case "image"://图片格式
                    System.out.println("图片");
                    break;
                case "voice"://声音
                    System.out.println("声音");
                    break;
                case "video"://视频
                    System.out.println("视频");
                    break;
                case "shortvideo"://小视频
                    System.out.println("小视频");
                    break;
                case "location"://上传地理位置
                    System.out.println("地理位置");
                    break;
                case "link"://链接相应
                    System.out.println("连接");
                    break;
                default:
                    break;
            }
        }
        //return msg;

        xs = XStreamUtil.init(true);

        TextOutMessage oms  = new TextOutMessage();
        oms.setContent("欢迎光临!");
        oms.setCreateTime(new Date().getTime());
        oms.setToUserName(msg.getFromUserName());
        oms.setFromUserName(msg.getToUserName());
        xs.alias("xml", oms.getClass());

        result = xs.toXML(oms);

        return result;
    }
}
