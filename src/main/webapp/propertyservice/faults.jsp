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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
    <title>wx demo</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/assets/css/wx.css">
    <link rel="stylesheet" href="/assets/css/common-space.css">
    <style>
        body {padding-top: 40px; background: #666666; font-family: "Heiti SC", "DroidSansFallback",  "微软雅黑";}
    </style>
</head>
<html>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-sm-6">
                    <div class="mobileframe">
                        <div class="wx-group">
                            <ul class="wx-functions">
                                <li class="wx-item">
                                    <div class="wx-header">故障列表-房间1-1101</div>
                                </li>
                            </ul>
                        </div>
                        <c:forEach var="faultInfo" items="${faultInfoList.content}">
                        <div class="wx-group">
                            <div class="wx-space"></div>
                            <ul class="wx-messages">
                                <li class="wx-item">
                                    <div class="pull-right prm pts">
                                        <div class="status text-c">
                                        <c:if test="${faultInfo.processStatus==1}">
                                            新报修
                                        </c:if>
                                        <c:if test="${faultInfo.processStatus==2}">
                                            已派修
                                        </c:if>
                                        <c:if test="${faultInfo.processStatus==3}">
                                            已结单
                                        </c:if>
                                        <c:if test="${faultInfo.processStatus==4}">
                                            已回访
                                        </c:if>
                                        </div>
                                    </div>
                                    <div class="wx-name b">${faultInfo.ppvname}</div>
                                    <div class="wx-content">${faultInfo.createdTimeStr}</div>
                                </li>
                                <li class="wx-item">
                                    <div class="ln"> ${faultInfo.description}</div>
                                    <div class="mtm">
                                        <img src="http://s.cn.bing.net/az/hprichbg/rb/Husafell_ZH-CN9632204692_1920x1080.jpg" alt="..." class="img-thumbnail">
                                        <img src="http://s.cn.bing.net/az/hprichbg/rb/Husafell_ZH-CN9632204692_1920x1080.jpg" alt="..." class="img-thumbnail">
                                        <img src="http://s.cn.bing.net/az/hprichbg/rb/Husafell_ZH-CN9632204692_1920x1080.jpg" alt="..." class="img-thumbnail">
                                    </div>
                                </li>
                            </ul>
                        </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
