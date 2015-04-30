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
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
    <title>故障列表-房间${houseInfo.num}</title>
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
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
                                    <div class="wx-header">故障列表-房间${houseInfo.num}</div>
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
                                    <c:if test="${!empty faultInfo.imgs}">
                                        <div class="mtm">

                                            <c:set var="imgs" value="${fn:split(faultInfo.imgs, ',')}"/>
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
                                    <div class="wx-header "><a href="/${signinName}/service/faultview?openid=${openid}" class="text-c">新增报修</a></div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
