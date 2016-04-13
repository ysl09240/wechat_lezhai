package com.slin.weixin.pojo;

/**
 * 类名: Token </br>
 * 描述: 凭证 </br>
 * @author SongLin.Yang
 * @data 2016-04-13 11:49
 */
public class AccessToken {
    // 接口访问凭证
    private String accessToken;
    // 凭证有效期，单位：秒
    private int expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}