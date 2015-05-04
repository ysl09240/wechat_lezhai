package com.lezhai365.wechat;

import com.alibaba.dubbo.config.annotation.Service;
import com.lezhai365.nosql.mongo.Mongodb;
import com.lezhai365.pms.model.weixin.MpInfo;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import java.util.Date;

/**
 * @author :  SongLin.Yang [ysl09240@gmail.com]
 * @version :  1.0
 * @encoding : UTF-8
 * @package : com.lezhai365.wechat
 * @link :  http://lezhai365.com
 * @created on   :  15-5-4
 * @copyright :  Copyright(c) 2013 西安乐宅网络科技有限公司
 * @description :
 */
@Service
public class MpInfoService {

    DBCollection mpInfoColl = Mongodb.getCollection("wechat", "mp_info");



    public int addMpInfo(MpInfo mpInfo){
        DBObject obj = new BasicDBObject();
        obj.put("pmc_signin_name",mpInfo);    // 物业公司ID
        obj.put("wechat_name",mpInfo.getWeixinName()); // 微信名称
        obj.put("origin_id",mpInfo.getOriginId()); // 微信原始ID
        obj.put("app_id",mpInfo.getAppId()); // 微信公众号AppId
        obj.put("app_secret",mpInfo.getAppSecret()); // 微信公众号AppSecret
        obj.put("token",mpInfo.getToken());     // 微信公众号token
        obj.put("encodingAESKey",mpInfo.getEncodingaeskey()); // 公众号 EncodingAESKey
        obj.put("is_deleted",mpInfo.getIsDeleted());     //是否已经删除
        obj.put("create_time",new Date());   // 创建时间
        return mpInfoColl.save(obj).getN();
    }
}
