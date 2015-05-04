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
    <title>账单查询-房间${houseInfo.num}</title>
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
                                    <div class="wx-header">账单查询-房间${houseInfo.num}</div>
                                </li>
                            </ul>
                        </div>
                        <div class="wx-group">
                            <div class="wx-space"></div>
                            <ul class="wx-messages">
                                <li class="wx-item">
                                    <c:choose>
                                        <c:when test="${sumNotPayMap.sumMoney eq null}">
                                            <div class="pull-right prm pts text-c">￥<fmt:formatNumber pattern="0.00" value="${0}"></fmt:formatNumber> </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="pull-right prm pts text-c">￥<fmt:formatNumber pattern="0.00" value="${sumNotPayMap.sumMoney}"></fmt:formatNumber> </div>
                                        </c:otherwise>
                                    </c:choose>
                                    <div class="wx-name">未缴金额合计:</div>
                                </li>
                            </ul>
                        </div>

                        <c:forEach var="item" items="${billsMap}">

                        <div class="wx-group">
                            <div class="wx-title">${item.key}</div>
                            <ul class="wx-messages">
                                <c:forEach var="bill" items="${item.value}">
                                    <li class="wx-item">
                                        <div class="pull-right prm pts">
                                            <div class="fee">￥<fmt:formatNumber value="${bill.amountMoney}" pattern="#.00"></fmt:formatNumber> </div>
                                            <div class="status text-muted">
                                                <c:if test="${bill.paymentStatus == 0}">未缴费</c:if>
                                                <c:if test="${bill.paymentStatus == 1}">己缴费</c:if>
                                                <c:if test="${bill.paymentStatus == 2}">己作废</c:if>
                                            </div>
                                        </div>
                                        <span class="wx-icon ">
                                            <img src="/assets/img/icon-fee.png">
                                        </span>
                                        <div class="wx-name">${bill.itemName}</div>
                                        <div class="wx-content"><fmt:formatDate value="${bill.billingDate}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate> </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                        </c:forEach>
                        <div class="wx-group fiexd-b">
                            <div id="account-info" class="hidden pmc-account-info">
                                <div class="pbm">公司名称:${pmcInfo.companyName}</div>
                                <div class="ptm pbm">开户行名称:${pmcInfo.bank}</div>
                                <div class="ptm pbm">开户行账号:${pmcInfo.bankAccount}</div>
                                <div class="ptm pbm">开户用户名:${pmcInfo.bankAccountUser}</div>
                                <div class="text-c small">友情提示:转账汇款24小时内到账，届时物业会发布账单</div>
                            </div>
                            <ul class="wx-functions">
                                <li class="wx-item">
                                    <div class="wx-header">
                                        <a id="show-account-info" class="text-c">物业账号</a>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <script src="/assets/libs/jquery/jquery-1.9.1.min.js"></script>
        <script type="text/javascript">
            $(function(){
                $("#show-account-info").click(function(){
                    if($("#account-info").hasClass("hidden")){
                        $("#account-info").removeClass("hidden");
                    } else {
                        $("#account-info").addClass("hidden");
                    }
                });
            });
        </script>
    </body>
</html>
