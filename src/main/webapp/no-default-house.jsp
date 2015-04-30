<%--
  Created by IntelliJ IDEA.
  User: Huzi
  Date: 2015-04-30
  Time: 3:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
  <title>请申请认证</title>
  <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
  <link rel="stylesheet" href="/assets/css/wx.css">
  <link rel="stylesheet" href="/assets/css/common-space.css">
</head>
<body>

<div class="wx-page">
  <div class="row">
    <div class="col-lg-4 col-sm-6">
      <div class="mobileframe bg">
        <div class="wx-group">
          <ul class="wx-functions">
            <li class="wx-item">
              <div class="wx-header">我的房产</div>
            </li>
          </ul>
        </div>
        <div class="wx-group">
          <div class="wx-space"></div>
          <ul class="wx-functions">
            <li class="wx-item">
              <div class="wx-tip-message">
                <div class="tip-bg"></div>
                <div class="tip-message">
                  您还未<a href="http://wx.lezhai365.com/${signinName}/infomation/authhouse?openid=${openid}" class="text-c">申请认证</a>或者未<a  href="/${signinName}/infomation/authhouse?openid=${openid}" class="text-c">设置常住房产</a>
                </div>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </div>
    </div>
</div>
</body>
</html>
