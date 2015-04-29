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
    <title>我的房产</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
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
                            <div class="wx-space"></div>
                           <ul class="wx-functions">
                               <c:if test="${myHouseList == null}">
                                   亲，您还没有认证房产，赶快去<a href="/${signinName}/infomation/authhouse">申请认证</a>哦
                               </c:if>
                               <c:forEach var="myhouse" items="${myHouseList}">

                                  <li class="wx-item">
                                       <span class="wx-icon wx-icon-sanmarino wx-yang">
                                        <i class="fa fa-qrcode"></i>
                                        </span>
                                        <div class="wx-name">
                                           ${myhouse.houseInfoStr}
                                            <div class="pull-right prm">
                                                <a href="/${signinName}/infomation/dodefault?houseInfoId=${myhouse.houseInfoId}&houseEstateId=${myhouse.housingEstateId}" class="wx-btn">设为常住</a>
                                            </div>
                                        </div>
                                    </li>
                               </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
