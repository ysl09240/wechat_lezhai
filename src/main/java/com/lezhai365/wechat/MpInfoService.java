package com.lezhai365.wechat;

import com.alibaba.dubbo.config.annotation.Service;
import com.lezhai365.nosql.mongo.Mongodb;
import com.lezhai365.wechat.model.MpInfo;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * 微信公众号信息管理
 *
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

    @Autowired
    WeChatService weChatService;

    /**
     *
     * 添加微信公众号信息
     *
     * @param mpInfo
     * @return int
     */
    public int addMpInfo(MpInfo mpInfo) throws Exception {
        DBObject obj = new BasicDBObject();

        obj.put("pmcSigninName",mpInfo);    // 物业公司ID
        obj.put("wechatName",mpInfo.getWechatName()); // 微信名称
        obj.put("wechatOriginId",mpInfo.getWechatOriginId()); // 微信原始ID
        obj.put("wechatAppId",mpInfo.getWechatAppId()); // 微信公众号AppId
        obj.put("wechatAppSecret",mpInfo.getWechatAppSecret()); // 微信公众号AppSecret
        obj.put("wechatToken",mpInfo.getWechatToken());     // 微信公众号token
        obj.put("wechatEncodingAESKey",mpInfo.getWechatEncodingASKey()); // 公众号 EncodingAESKey
        obj.put("isDeleted",mpInfo.getIsDeleted());     //是否已经删除
        obj.put("createTime",new Date());   // 创建时间

        //取一次accessToken
        Map tokenInfo = weChatService.getAccessTokenInfo(mpInfo.getWechatAppId(), mpInfo.getWechatAppSecret());

        obj.put("accessToken",tokenInfo.get("access_token"));
        obj.put("accessTokenUpdateTime",new Date().getTime());


        return mpInfoColl.save(obj).getN();
    }

    /**
     *
     * 根据pmcSigninName取acessToken(中控模式)
     * 将获取到的accessToken进行缓存，如果accessToken超过 7200就重新获取并更新
     *
     *
     * @param pmcSigninName
     * @return Map<String,Object>
     *
     */
    public String getAccessToken(String pmcSigninName) throws Exception {
        String accessToken = "";
        BasicDBObject where = new BasicDBObject();
        where.put("pmcSigninName", pmcSigninName);
        DBObject field = new BasicDBObject();
        field.put("accessToken",1);
        field.put("accessTokenUpdateTime",1);

        DBObject dbo = mpInfoColl.findOne(where,field);

        if (null != dbo) {
            accessToken = dbo.get("accessToken").toString();
            Long accessTokenUpdateTime = (Long)dbo.get("accessToken");
            Long currentTime = new Date().getTime();

            //如果超过两个小时从新获取access token
            if ((currentTime - accessTokenUpdateTime) > 7200) {
                Map<String,String> appInfo = getAppInfo(pmcSigninName);
                String appId = appInfo.get("wechatAppId");
                String appSecrect = appInfo.get("wechatAppSecret");
                Map tokenInfo = weChatService.getAccessTokenInfo(appId, appSecrect);

                //更新最新的access token 信息
                BasicDBObject update = new BasicDBObject();
                update.put("accessToken",tokenInfo.get("access_token").toString());
                update.put("accessTokenUpdateTime",new Date().getTime());
                update.put("accessTokenExpiresIn",tokenInfo.get("expires_in").toString());

                mpInfoColl.update(where,update);
            }
        }

        return accessToken;
    }

    /**
     * 根据pmcSigninName获取微信公众账号appId,appSecret信息
     * @param pmcSigninName
     * @return
     */
    public  Map<String,String> getAppInfo(String pmcSigninName){
        Map result = null;
        BasicDBObject where = new BasicDBObject();
        where.put("pmcSigninName", pmcSigninName);

        DBObject field = new BasicDBObject();
        field.put("wechatName",1);
        field.put("wechatAppId",1);
        field.put("wechatApp",1);
        field.put("wechatOriginId",1);
        field.put("wechatAppId",1);
        field.put("wechatAppSecret",1);
        field.put("wechatToken",1);
        field.put("wechatEncodingAESKey",1);

        DBObject dbo = mpInfoColl.findOne(where,field);
        if (null != dbo) {
            result = dbo.toMap();
        }

        return result;
    }

    /**
     *
     * 根据pmcSigninName查询公众号信息
     *
     * @param pmcSigninName
     * @return DBObject
     */
    public DBObject getOneByPmcSigninName(String pmcSigninName){
        BasicDBObject where = new BasicDBObject();
        where.put("pmcSigninName", pmcSigninName);
        DBObject result = mpInfoColl.findOne(where);
        return result;
    }

    /**
     * 获取所有的微信公众号信息
     *
     * @return List
     */
    public List getAllMpInfo(){
        DBCursor dbc = mpInfoColl.find();
        List<Object> results = new ArrayList<Object>();
        while (dbc.hasNext()){
            results.add(dbc.next());
        }
        return results;
    }
}
