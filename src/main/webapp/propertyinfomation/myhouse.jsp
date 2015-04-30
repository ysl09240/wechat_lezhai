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
    <title>我的房产</title>
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/wx.css">
    <link rel="stylesheet" href="/assets/css/common-space.css">
    <script src="/assets/libs/jquery/jquery-1.9.1.min.js"></script>
    <script src="/assets/libs/jquery/plugins/jquery.validate.min.js"></script>
    <script src="/assets/app/propertyservice/complaintValidate.js"></script>
</head>
<html>
    <body>
        <div class="wx-page">
            <div class="row">
                <div class="col-lg-4 col-sm-6">
                    <div class="mobileframe bg">
                        <div class="wx-group">
                            <ul class="wx-functions">
                                <li class="wx-item">
                                    <a href="/${signinName}/infomation/myestate?openid=${openid}" class="wx-icon back-arrow">
                                        <img  src="/assets/img/back-arrow.png">
                                    </a>
                                    <div class="wx-header">我的房产</div>
                                </li>
                            </ul>
                        </div>
                        <div class="wx-group">
                            <div class="wx-space"></div>
                           <ul class="wx-functions">
                               <c:choose>
                                   <c:when test="${empty myHouseList}">
                                       <div class="wx-tip-message">
                                           <div class="tip-bg"></div>
                                           <div class="tip-message">
                                               尊敬的业主你好，你还未申请认证房产，请<a href="/${signinName}/infomation/myhouse?openid=${openid}&housingEstateId=${housingEstateId}" class="text-c">申请认证</a>
                                           </div>
                                       </div>
                                   </c:when>
                                   <c:otherwise>
                                       <c:forEach var="myhouse" items="${myHouseList}">

                                           <li class="wx-item">
                                       <span class="wx-icon">
                                        <img  src="/assets/img/icon-house.png">
                                        </span>
                                               <div class="wx-name">
                                                       ${myhouse.houseInfoStr}
                                                   <c:choose>
                                                       <c:when test="${myhouse.authStatus eq 0}">
                                                           <div class="pull-right prm">
                                                               <span class="wx-btn inverse">待认证</span>
                                                           </div>
                                                       </c:when>
                                                       <c:when test="${myhouse.authStatus eq 1}">
                                                           <div class="pull-right prm">
                                                               <c:if test="${myhouse.defaultHouseId eq myhouse.houseInfoId}">
                                                                   <span>常住</span>
                                                               </c:if>
                                                               <c:if test="${myhouse.defaultHouseId ne myhouse.houseInfoId}">
                                                                   <a href="/${signinName}/infomation/dodefault?houseInfoId=${myhouse.houseInfoId}&houseEstateId=${myhouse.housingEstateId}&openid=${openid}" class="wx-btn">设为常住</a>
                                                               </c:if>
                                                           </div>
                                                       </c:when>
                                                   </c:choose>
                                               </div>
                                           </li>
                                       </c:forEach>
                                   </c:otherwise>
                               </c:choose>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
