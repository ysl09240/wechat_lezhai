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
    <title>环保积分房间${houseInfo.num}</title>
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
                                    <div class="wx-header">环保积分-房间${houseInfo.num}</div>
                                </li>
                            </ul>
                        </div>
                        <div class="wx-group">
                            <div class="wx-space"></div>
                            <div class="env-box">
                                <ul class="env-statis clearfix">
                                    <li>
                                        <div class="count text-r">${integralInfo.validIntegral}</div>
                                        <div class="small pts">我的积分</div>
                                    </li>
                                    <li class="line"></li>
                                    <li>
                                        <div class="count text-c">${integralInfo.usedIntegral}</div>
                                        <div class="small pts">累计消耗积分</div>
                                    </li>
                                </ul>
                            </div>
                            <ul class="wx-messages env">
                                <li class="wx-item">
                                    <div class="">
                                        <div class="clearfix small ptm">
                                            <div class="pull-left"><a href="/${signinName}/service/integralintro?openid=${openid}" class="text-c">积分规格说明</a></div>
                                            <div class="pull-right text-muted">最后积分时间:${integralInfo.lastTimeStr}</div>
                                        </div>
                                        <ul class="c-tab clearfix">
                                            <li class="c-tab-item <c:if test="${flag eq 'iHistory'}">select</c:if> "><a href="/${signinName}/service/integralslist?flag=iHistory&openid=${openid}">积分查询</a></li>
                                            <li class="c-tab-item <c:if test="${flag eq 'iExchange'}">select</c:if> "><a href="/${signinName}/service/integralslist?flag=iExchange&openid=${openid}">积分兑换</a></li>
                                            <li class="c-tab-item <c:if test="${flag eq 'iAdjust'}">select</c:if> "><a href="/${signinName}/service/integralslist?flag=iAdjust&openid=${openid}">积分调整</a></li>
                                        </ul>
                                    </div>
                                </li>
                                <c:choose>
                                    <c:when test="${flag eq 'iExchange'}">
                                        <c:forEach var="exchange" items="${integralExchangePage.content}">
                                            <li class="wx-item bt-none">
                                                <div class="pull-right prm">
                                                    <div class="fee text-c small"> -${exchange.usedIntegral} </div>
                                                    <div class="status text-muted small">${exchange.createdTimeStr} </div>
                                                </div>
                                                <div class="wx-name">${exchange.goodsName}*${exchange.goodsNumber}</div>
                                            </li>
                                        </c:forEach>
                                    </c:when>
                                    <c:when test="${flag eq 'iAdjust'}">
                                        <c:forEach var="adjust" items="${integralAdjustPage.content}">
                                            <li class="wx-item bt-none ">
                                                <div class="pull-right prm">
                                                    <div class="fee text-c small">${adjust.newIntegral-adjust.oldIntegral}</div>
                                                    <div class="status text-muted small">${adjust.updateTimeStr} </div>
                                                </div>
                                                <div class="wx-name">${adjust.remarks}</div>
                                            </li>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach var="history" items="${integralHistoryPage.content}">
                                            <li class="wx-item bt-none">
                                                <div class="pull-right prm">
                                                    <div class="fee text-c small">${history.integralGrade}</div>
                                                    <div class="status text-muted small">${history.createdTimeStr} </div>
                                                </div>
                                                <div class="wx-name">${history.wasteName}</div>
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
