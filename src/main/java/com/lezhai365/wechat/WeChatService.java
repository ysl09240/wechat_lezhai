package com.lezhai365.wechat;

import com.alibaba.fastjson.JSONObject;
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
public class WeChatService extends WeChatOpenApi {

    /**
     * 处理微信消息
     *
     * @param xmlInputStream
     * @return
     * @throws Exception
     */
    public String processWxMsg(InputStream xmlInputStream) throws Exception {
        String result = "";
        //转换微信post过来的xml内容
        XStream xs = XStreamUtil.init(false);
        xs.ignoreUnknownElements();
        xs.alias("xml", InMessage.class);

        TextOutMessage oms  = new TextOutMessage();
        InMessage msg = (InMessage) xs.fromXML(xmlInputStream);
        String event = msg.getEvent();
        String MsgType = msg.getMsgType();
        System.out.println("=============================================");
        System.out.println(msg);
        System.out.println("=============================================");




        if(msg.getMsgType().equals("event")){
            System.out.println("事件是:" + event);
            switch (event) {
                case "subscribe"://关注
                    // 微信用户信息公众账号的时候绑定账号信息，
                    String access_token = getAccessToken("wxc25645bdef1d5f57","bd9108b26ac432faed9a1a24aae92510");
                    UserService us = new UserService();
                    JSONObject userInfo = us.getUserInfo(access_token, msg.getFromUserName());
//                    System.out.println("用户信息如下：--------------------------------------------------------");
//                    System.out.println(userInfo);
                     oms.setContent("感谢您的关注!");
                    break;
                case "unsubscribe"://取消关注
                    //标注用户已经取消关注
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
                    oms.setContent("您发的消息是:" + msg.getContent());
                    System.out.println("文本消息");
                    break;
                case "image"://图片格式
                    oms.setContent("您发的消息是:" + msg.getPicUrl());
                    System.out.println("图片");
                    break;
                case "voice"://声音
                    oms.setContent("您发的消息是:" + msg.getRecognition());
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
        oms.setCreateTime(new Date().getTime());
        oms.setToUserName(msg.getFromUserName());
        oms.setFromUserName(msg.getToUserName());
        xs.alias("xml", oms.getClass());
        result = xs.toXML(oms);

        return result;
    }
}
