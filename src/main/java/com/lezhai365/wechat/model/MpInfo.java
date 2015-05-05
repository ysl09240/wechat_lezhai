package com.lezhai365.wechat.model;

/**
 *
 * 公众号信息model
 *
 * @author :  Huzi.Wang [huzi.wh@gmail.com]
 * @version :  1.0
 * @encoding : UTF-8
 * @package : com.lezhai365.wechat.model
 * @link :  http://lezhai365.com
 * @created on   :  2015-05-04
 * @copyright :  Copyright(c) 2013 西安乐宅网络科技有限公司
 * @description :
 */
public class MpInfo {
  private String pmcSigninName;
  private String wechatName;
  private String wechatOriginId;
  private String wechatAppId;
  private String wechatAppSecret;
  private String wechatToken;
  private String wechatEncodingASKey;
  private int isDeleted;

  public String getPmcSigninName() {
    return pmcSigninName;
  }

  public void setPmcSigninName(String pmcSigninName) {
    this.pmcSigninName = pmcSigninName;
  }

  public String getWechatName() {
    return wechatName;
  }

  public void setWechatName(String wechatName) {
    this.wechatName = wechatName;
  }

  public String getWechatOriginId() {
    return wechatOriginId;
  }

  public void setWechatOriginId(String wechatOriginId) {
    this.wechatOriginId = wechatOriginId;
  }

  public String getWechatAppId() {
    return wechatAppId;
  }

  public void setWechatAppId(String wechatAppId) {
    this.wechatAppId = wechatAppId;
  }

  public String getWechatAppSecret() {
    return wechatAppSecret;
  }

  public void setWechatAppSecret(String wechatAppSecret) {
    this.wechatAppSecret = wechatAppSecret;
  }

  public String getWechatToken() {
    return wechatToken;
  }

  public void setWechatToken(String wechatToken) {
    this.wechatToken = wechatToken;
  }

  public String getWechatEncodingASKey() {
    return wechatEncodingASKey;
  }

  public void setWechatEncodingASKey(String wechatEncodingASKey) {
    this.wechatEncodingASKey = wechatEncodingASKey;
  }

  public int getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(int isDeleted) {
    this.isDeleted = isDeleted;
  }
}
