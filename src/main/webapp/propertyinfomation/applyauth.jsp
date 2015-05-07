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
    <title>申请认证</title>
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/wx.css">
    <link rel="stylesheet" href="/assets/css/common-space.css">
    <script type="text/javascript">
        window.wx = {
            pmcSignName:'${signinName}',
            openid:'${openid}'
        };
    </script>
    <script src="/assets/libs/jquery/jquery-1.9.1.min.js"></script>
    <script src="/assets/libs/jquery/plugins/jquery.validate.min.js"></script>
    <script src="/assets/libs/jquery/plugins/cselect.js"></script>
    <script src="/assets/app/propertyinfomation/authHouseValidate.js"></script>

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
                            <a href="/${signinName}/infomation/myestate?openid=${openid}" class="wx-icon back-arrow">
                                <img  src="/assets/img/back-arrow.png">
                            </a>
                            <div class="wx-header">申请认证</div>
                        </li>
                    </ul>
                </div>
                <form id="authhouseForm" method="post" action="/${signinName}/infomation/doauthhouse?openid=${openid}">
                    <div class="wx-group">
                        <div class="wx-space"></div>
                        <ul class="wx-functions">
                            <li class="wx-item">
                                <span class="wx-icon">
                                    <img src="/assets/img/icon-estate.png">
                                </span>

                                <div class="wx-name">小区</div>
                                <c:choose>
                                    <c:when test="${empty houseEstateId}" >
                                        <select class="feild" id="estate-select" data-default="${detail.buildingId}"
                                                name="housingEstateId" data-placeholder="请选择小区" data-_next="building-select"
                                                data-sync="false">
                                            <option>请选择小区</option>
                                            <c:forEach items="${estateList}" var="estate">
                                                <option value="${estate.housingEstateId}" <c:if test="${houseEstateId eq estate.housingEstateId}">select</c:if> >${estate.housingEstateName}</option>
                                            </c:forEach>
                                        </select>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach items="${estateList}" var="estate">
                                            <c:if test="${houseEstateId eq estate.housingEstateId}">
                                                <select class="feild hidden" id="estate-select" data-default="${detail.buildingId}"
                                                        name="housingEstateId" data-placeholder="请选择小区" data-_next="building-select"
                                                        data-sync="false">
                                                        <option>请选择楼宇</option>
                                                        <option value="${estate.housingEstateId}" selected>${estate.housingEstateName}</option>
                                                </select>
                                                <div class="feild pts">${estate.housingEstateName}</div>
                                                <script type="text/javascript">
                                                    $(function(){
                                                        setTimeout(function(){
                                                            $("#estate-select>option:selected").trigger("change");
                                                        },1000)
                                                    });
                                                </script>
                                            </c:if>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </li>
                            <li class="wx-item">
                                <span class="wx-icon">
                                    <img src="/assets/img/icon-building.png">
                                </span>

                                <div class="wx-name inline-block">楼宇</div>
                                <select class="feild" id="building-select" data-default="${detail.buildingId}"
                                        name="buildingId" data-placeholder="请选择楼宇" data-_next="unit-select"
                                        data-sync="true">
                                        <option>请选择楼宇</option>
                                </select>
                            </li>
                            <li class="wx-item">
                                <span class="wx-icon">
                                    <img src="/assets/img/icon-unit.png">
                                </span>

                                <div class="wx-name">单元</div>
                                <select class="feild" id="unit-select" data-default="${detail.unitInfoId}" name="unitId"
                                        data-_next="floor-select" data-placeholder="请选择单元">

                                    <option>请选择单元</option>
                                </select>
                            </li>

                            <li class="wx-item">
                                <span class="wx-icon">
                                    <img src="/assets/img/icon-house.png">
                                </span>

                                <div class="wx-name">楼层</div>
                                <select class="feild" name="floorId"
                                        data-_next="houseinfo-select"
                                        id="floor-select" required data-placeholder="请选择层">
                                    <option>请选择层</option>
                                </select>
                            </li>
                            <li class="wx-item">
                                <span class="wx-icon">
                                    <img src="/assets/img/icon-house.png">
                                </span>

                                <div class="wx-name">房间</div>
                                <select class="feild" name="houseInfoId" data-default="${detail.houseInfoId}"
                                        id="houseinfo-select" required data-placeholder="请选择房间">
                                    <option>请选择房间</option>
                                </select>
                            </li>
                            <li class="wx-item">
                                <span class="wx-icon">
                                    <img src="/assets/img/icon-people.png">
                                </span>

                                <div class="wx-name">业主姓名</div>
                                <input name="ownerName" class="feild">
                            </li>

                            <li class="wx-item">
                                <span class="wx-icon">
                                    <img src="/assets/img/icon-phone.png">
                                </span>

                                <div class="wx-name">业主电话</div>
                                <input name="ownerPhone" class="feild">
                            </li>
                        </ul>
                    </div>
                    <div class="wx-group fiexd-b">
                        <ul class="wx-functions">
                            <li class="wx-item">
                                <div class="wx-header "><input class="form-button" type="submit" value="提交认证"/></div>
                            </li>
                        </ul>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
