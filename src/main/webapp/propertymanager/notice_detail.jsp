<%--
  Created by IntelliJ IDEA.
  User: songlinyang
  Date: 15-4-22
  Time: 上午9:44
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
    <title>通知详情</title>
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
                                <a href="/${signinName}/manager/notice?openid=${openid}" class="wx-icon back-arrow">
                                    <img  src="/assets/img/back-arrow.png">
                                </a>
                                <div class="wx-header">通知详情</div>
                            </li>
                        </ul>
                    </div>
                    <div class="wx-group">
                        <div class="wx-space"></div>
                        <ul class="wx-functions">
                            <li class="wx-item">
                                <h4 class="text-center"> <span class="label label-info c-label"></span>${noticeMap.title} </h4>
                                <div class="wx-detail mtm">
                                    ${noticeMap.content}
                                </div>
                                <div class="text-right prml">
                                    <h4>${noticeMap.pmcName}</h4>
                                    <span>${noticeMap.createdDateStr2}</span>
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
