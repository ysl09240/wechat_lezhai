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
                                    <div class="wx-header">账单查询-房间1-1101</div>
                                </li>
                            </ul>
                        </div>
                        <div class="wx-group">
                            <div class="wx-space"></div>
                            <ul class="wx-messages">
                                <li class="wx-item">
                                    <div class="pull-right prm pts text-c">￥778.87 </div>
                                    <div class="wx-name">未缴金额合计:</div>
                                </li>
                            </ul>
                        </div>

                        <div class="wx-group">
                            <%--<div class="wx-title">3月</div>--%>
                            <ul class="wx-messages">
                                <c:forEach var="bill" items="${billsList.content}">
                                    <li class="wx-item">
                                        <div class="pull-right prm pts">
                                            <div class="fee">￥${bill.amountMoney}</div>
                                            <div class="status text-muted">
                                                <c:if test="${bill.paymentStatus == 0}">未缴费</c:if>
                                                <c:if test="${bill.paymentStatus == 1}">己缴费</c:if>
                                                <c:if test="${bill.paymentStatus == 2}">己作废</c:if>
                                            </div>
                                        </div>
                                        <span class="wx-icon wx-icon-sanmarino">
                                          <i class="fa fa-envelope"></i>
                                        </span>
                                        <div class="wx-name">${bill.itemName}</div>
                                        <div class="wx-content"><fmt:formatDate value="${bill.billingDate}" pattern="yyyy-MM-dd HH:ss"></fmt:formatDate> </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                        <div class="wx-group fiexd-b">
                            <ul class="wx-functions">
                                <li class="wx-item">
                                    <div class="wx-header"><a class="text-c">物业账号</a></div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>
