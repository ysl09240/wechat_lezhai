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
    <title>我的小区</title>
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
                            <ul class="wx-functions">
                                <li class="wx-item">
                                    <div class="wx-header">我的小区</div>
                                </li>
                            </ul>
                        </div>
                        <div class="wx-group">
                            <div class="wx-space"></div>
                            <ul class="wx-functions">
                                <c:forEach var="myestate" items="${estateList}">
                                    <li class="wx-item">
                                    <span class="wx-icon">
                                        <img  src="/assets/img/icon-estate.png">
                                    </span>
                                        <div class="wx-name">
                                           ${myestate.housingEstateName}
                                            <div class="pull-right prm">
                                                <c:choose>
                                                    <c:when test="${myestate.defaultEstateId eq myestate.housingEstateId}">
                                                        <span class="wx-btn inverse">常住</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a href="/${signinName}/infomation/myhouse?houseEstateId=${myestate.housingEstateId}&openid=${openid}" class="wx-btn">设为常住</a>
                                                    </c:otherwise>
                                                </c:choose>

                                               <c:choose>
                                                   <c:when test="${myestate.ownerId == null}">
                                                        <a href="/${signinName}/infomation/authhouse?houseEstateId=${myestate.housingEstateId}&openid=${openid}" class="wx-btn mlm">申请认证</a>
                                                   </c:when>
                                                   <c:otherwise>
                                                       <c:if test="${myestate.isApplyed > 0}">
                                                            <a href="/${signinName}/infomation/myhouse?houseEstateId=${myestate.housingEstateId}&openid=${openid}" class="wx-btn mlm">查看详情</a>
                                                       </c:if>
                                                   </c:otherwise>
                                               </c:choose>
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
