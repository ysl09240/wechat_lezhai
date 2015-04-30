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
    <title>新增报修-房间1-1101</title>
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/wx.css">
    <link rel="stylesheet" href="/assets/css/common-space.css">
    <link rel="stylesheet" type="text/css" href="/assets/libs/webupload/lib/webuploader.css"/>
    <link rel="stylesheet" type="text/css" href="/assets/libs/webupload/style.css"/>
    <script src="/assets/libs/jquery/jquery-1.9.1.min.js"></script>
    <script src="/assets/libs/jquery/plugins/jquery.validate.min.js"></script>
    <script src="/assets/app/propertyservice/faultValidate.js"></script>
    <script type="text/javascript" src="/assets/libs/webupload/lib/webuploader.js"></script>
    <script type="text/javascript" src="/assets/libs/webupload/upload.js"></script>
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
                                    <div class="wx-header">新增报修-房间${houseInfo.num}</div>
                                </li>
                            </ul>
                        </div>
                        <form id="addFaultForm" method="post" action="/${signinName}/service/do/addfault?openid=${openid}">
                            <div class="wx-group">
                                <div class="upload-space fault-bg">
                                    <div id="uploader">
                                        <div id="queueList" class="queueList">
                                            <a id="filePicker" class="webuploader-container">
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <ul class="wx-functions">
                                    <li class="wx-item">
                                        <textarea name="description" class="textarea" placeholder="故障描述"></textarea>
                                    </li>
                                </ul>
                            </div>
                            <div class="wx-group">
                                <div class="wx-space"></div>
                                <ul class="wx-functions">
                                    <li class="wx-item">
                                <span class="wx-icon">
                                    <img src="/assets/img/icon-fault-type.png">
                                </span>
                                        <div class="wx-name">故障类型</div>
                                        <select class="feild" name="typeId">
                                            <c:forEach var="type" items="${repairFaultValue.typeMap}">
                                                <option value="${type.id}">${type.name}</option>
                                            </c:forEach>
                                        </select>
                                    </li>
                                </ul>
                            </div>
                            <div class="wx-group">
                                <div class="wx-space"></div>
                                <ul class="wx-functions">
                                    <li class="wx-item">
                                <span class="wx-icon">
                                    <img src="/assets/img/icon-level.png">
                                </span>
                                        <div class="wx-name inline-block">紧急程度</div>
                                        <select class="feild" name="urgency">
                                            <c:forEach var="emergency" items="${repairEmergencyValue.typeMap}">
                                                <option value="${emergency.id}">${emergency.name}</option>
                                            </c:forEach>
                                        </select>
                                    </li>
                                </ul>
                            </div>
                            <div class="wx-group">
                                <div class="wx-space"></div>
                                <ul class="wx-functions">
                                    <li class="wx-item">
                                <span class="wx-icon">
                                    <img src="/assets/img/icon-location.png">
                                </span>
                                        <div class="wx-name">故障地点</div>
                                        <input class="feild" name="adress" />
                                    </li>
                                </ul>
                            </div>

                            <div class="wx-group">
                                <div class="wx-space"></div>
                                <ul class="wx-functions">
                                    <li class="wx-item">
                                <span class="wx-icon">
                                    <img src="/assets/img/icon-people.png">
                                </span>
                                        <div class="wx-name">保修人</div>
                                        <input class="feild" name="repairPeople"/>
                                    </li>
                                </ul>
                            </div>
                            <div class="wx-group">
                                <div class="wx-space"></div>
                                <ul class="wx-functions">
                                    <li class="wx-item">
                                <span class="wx-icon">
                                    <img src="/assets/img/icon-phone.png">
                                </span>
                                        <div class="wx-name">联系电话</div>
                                        <input class="feild" name="contactNumber"/>
                                    </li>
                                    <span class="errorMessage" style="color:red"></span>
                                </ul>
                            </div>
                            <div class="wx-group fiexd-b">
                                <ul class="wx-functions">
                                    <li class="wx-item">
                                        <div class="wx-header "><button  type="submit" class="form-button">提交报修</button></div>
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
