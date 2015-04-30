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
    <title>积分兑换规则</title>
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
                                    <div class="wx-header">积分兑换规则</div>
                                </li>
                            </ul>
                        </div>
                        <div class="wx-group">
                            <div class="wx-space"></div>
                            <ul class="wx-functions">
                                <li class="wx-item">
                                    <div class="wx-detail mtm">
                                        物业公司开通智能垃圾系统之后，分类垃圾可以产生积分，

                                        可以使用积分去物业公司兑换商品。

                                        兑换规则如下：
                                    </div>
                                    <div class="wx-detail ptn pbn">
                                        <table class="table table-bordered">
                                            <thead>
                                            <tr>
                                                <th>垃圾分类</th>
                                                <th>重量</th>
                                                <th>积分</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:choose>
                                                <c:when test="${empty wasteInfoList}">
                                                   <tr>
                                                       <td colspan="3">暂无</td>
                                                   </tr>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:forEach var="waste" items="${wasteInfoList}">

                                                <tr>
                                                    <th scope="row">${waste.wasteName}</th>
                                                    <td><fmt:formatNumber value="${waste.weight}" pattern="##.00" /></td>
                                                    <td><fmt:formatNumber value="${waste.wasteUnitPrice}" pattern="##.00" /></td>
                                                </tr>
                                            </c:forEach>
                                                </c:otherwise>
                                            </c:choose>

                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="wx-detail ptn">
                                        其他规则
                                        业主不遵守物业公司智能垃圾规则，刻意破坏。根据物业公司的相关处罚规定进行相应的积分处罚。

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
