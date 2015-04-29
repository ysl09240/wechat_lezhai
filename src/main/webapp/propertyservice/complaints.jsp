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
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
    <title>投诉-房间${houseInfo.num}</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/assets/css/wx.css">
    <link rel="stylesheet" href="/assets/css/common-space.css">
</head>
<html>
    <body>
        <div class="wx-page">
            <div class="row">
                <div class="col-lg-4 col-sm-6">
                    <div class="mobileframe">
                        <div class="wx-group">
                            <ul class="wx-functions">
                                <li class="wx-item">
                                    <div class="wx-header">投诉-房间${houseInfo.num}</div>
                                </li>
                            </ul>
                        </div>
                        <c:forEach var="complaint" items="${complaintList.content}">
                        <div class="wx-group">
                            <div class="wx-space"></div>
                            <ul class="wx-messages">
                                <li class="wx-item">
                                    <div class="lh">
                                        <span class="text-c">
                                            <c:if test="${complaint.dealStatus==0}">
                                                未处理
                                            </c:if>
                                            <c:if test="${complaint.dealStatus==1}">
                                                己处理
                                            </c:if>
                                        </span>
                                        ${complaint.complaintContent}
                                        <span class="wx-content">${complaint.createdDateStr}</span>
                                    </div>
                                    <c:if test="${!empty complaint.imgs}">
                                        <div class="mtm">

                                            <c:set var="imgs" value="${fn:split(complaint.imgs, ',')}"/>
                                            <c:forEach items="${imgs}" var="img">
                                                <img src="http://img.lezhai365.com/${img}" alt="..." class="img-thumbnail">
                                            </c:forEach>
                                        </div>
                                    </c:if>
                                </li>
                            </ul>
                        </div>
                        </c:forEach>
                        <div class="wx-group fiexd-b">
                            <ul class="wx-functions">
                                <li class="wx-item">
                                    <div class="wx-header "><a href="/${signinName}/service/complaintview?openid=${openid}" class="text-c">新增投诉</a></div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>
