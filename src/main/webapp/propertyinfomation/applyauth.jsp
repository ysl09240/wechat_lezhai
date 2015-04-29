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
    <title>申请认证</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/assets/css/wx.css">
    <link rel="stylesheet" href="/assets/css/common-space.css">
    <script type="text/javascript">
        window.pmcSignName = '${signinName}';
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
                <form id="authhouseForm" method="post" action="/${signinName}/infomation/doauthhouse">


                    <div class="mtl visible-xs"></div>
                    <div class="col-sm-3">
                    </div>

                    <div class="mtl visible-xs"></div>
                    <div class="col-sm-3">
                    </div>
                    <div class="mtl visible-xs"></div>
                    <div class="col-sm-3">
                    </div>


                    <div class="wx-group">
                        <div class="wx-space"></div>
                        <ul class="wx-functions">
                            <li class="wx-item">
                                <span class="wx-icon wx-icon-sanmarino wx-yang">
                                    <i class="fa fa-qrcode"></i>
                                </span>

                                <div class="wx-name">小区</div>
                                <select class="feild" id="estate-select" data-default="${detail.buildingId}"
                                        name="housingEstateId" data-placeholder="请选择小区" data-_next="unit-select"
                                        data-sync="false">
                                        <option>请选择小区</option>
                                    <c:forEach items="${estateList}" var="estate">
                                        <option value="${estate.id}">${estate.name}</option>
                                    </c:forEach>
                                </select>
                            </li>
                            <li class="wx-item">
                                <span class="wx-icon wx-icon-sanmarino wx-yang">
                                    <i class="fa fa-qrcode"></i>
                                </span>

                                <div class="wx-name inline-block">楼宇</div>
                                <select class="feild" id="building-select" data-default="${detail.buildingId}"
                                        name="buildingId" data-placeholder="请选择楼宇" data-_next="unit-select"
                                        data-sync="true">
                                        <option>请选择楼宇</option>
                                </select>
                            </li>
                            <li class="wx-item">
                                <span class="wx-icon wx-icon-sanmarino wx-yang">
                                    <i class="fa fa-qrcode"></i>
                                </span>

                                <div class="wx-name">单元</div>
                                <select class="feild" id="unit-select" data-default="${detail.unitInfoId}" name="unitId"
                                        data-_next="floor-select" data-placeholder="请选择单元">

                                    <option>请选择单元</option>
                                </select>
                            </li>
                            <li class="wx-item">
                                <span class="wx-icon wx-icon-sanmarino wx-yang">
                                    <i class="fa fa-coffee"></i>
                                </span>

                                <div class="wx-name">房间</div>
                                <select class="feild" id="floor-select" data-default="${detail.floor}" name="floorId"
                                        data-_next="houseInfoId" data-placeholder="请选择楼层">
                                    <option>请选择楼层</option>
                                </select>
                            </li>
                            <li class="wx-item">
                                <span class="wx-icon wx-icon-sanmarino wx-yang">
                                    <i class="fa fa-coffee"></i>
                                </span>

                                <div class="wx-name">房间</div>
                                <select class="feild" name="houseInfoId" data-default="${detail.houseInfoId}"
                                        id="houseInfoId" required data-placeholder="请选择房间">
                                    <option>请选择房间</option>
                                </select>
                            </li>
                            <li class="wx-item">
                                <span class="wx-icon wx-icon-sanmarino wx-yang">
                                    <i class="fa fa-coffee"></i>
                                </span>

                                <div class="wx-name">业主姓名</div>
                                <input name="ownerName" class="feild">
                            </li>

                            <li class="wx-item">
                                <span class="wx-icon wx-icon-sanmarino wx-yang">
                                    <i class="fa fa-coffee"></i>
                                </span>

                                <div class="wx-name">业主电话</div>
                                <input name="ownerPhone" class="feild">
                            </li>
                        </ul>
                    </div>
                    <div class="wx-group fiexd-b">
                        <ul class="wx-functions">
                            <li class="wx-item">
                                <div class="wx-header "><a class="text-c">提交认证</a></div>
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
