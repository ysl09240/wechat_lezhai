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
                                    <div class="wx-header">新增报修-房间1-1101</div>
                                </li>
                            </ul>
                        </div>
                        <form method="post" action="/service/do/addfault">
                            <div class="wx-group">
                                <div class="upload-space"></div>
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
                                <span class="wx-icon wx-icon-sanmarino wx-yang">
                                    <i class="fa fa-qrcode"></i>
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
                                <span class="wx-icon wx-icon-sanmarino wx-yang">
                                    <i class="fa fa-qrcode"></i>
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
                                <span class="wx-icon wx-icon-sanmarino wx-yang">
                                    <i class="fa fa-qrcode"></i>
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
                                <span class="wx-icon wx-icon-sanmarino wx-yang">
                                    <i class="fa fa-coffee"></i>
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
                                <span class="wx-icon wx-icon-sanmarino wx-yang">
                                    <i class="fa fa-coffee"></i>
                                </span>
                                        <div class="wx-name">联系电话</div>
                                        <input class="feild" name="contactNumber"/>
                                    </li>
                                </ul>
                            </div>
                            <div class="wx-group fiexd-b">
                                <ul class="wx-functions">
                                    <li class="wx-item">
                                        <div class="wx-header "><button type="submit" class="text-c">提交报修</buttons></div>
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
