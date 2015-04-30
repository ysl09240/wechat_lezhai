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
    <title>新增投诉-房间${houseInfo.num}</title>
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
                                <div class="wx-header">新增投诉-房间${houseInfo.num}</div>
                            </li>
                        </ul>
                    </div>
                    <form id="addComplaintForm" action="/${signinName}/service/do/addcomplaint?openid=${openid}" method="post">
                        <div class="wx-group">
                            <div class="upload-space complaint-bg">
                                <div id="uploader">
                                    <div id="queueList" class="queueList">
                                        <a id="filePicker" class="webuploader-container">
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <ul class="wx-functions">
                                <li class="wx-item">
                                    <textarea name="complaintContent" class="textarea" placeholder="投诉内容"></textarea>
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
                                    <div class="wx-name">投诉人</div>
                                    <input class="feild" name="complainant" />
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
                                    <input id="contactNumber" class="feild" name="contactNumber"/>
                                </li>
                                    <span class="errorMessage" style="color:red"></span>
                            </ul>
                        </div>
                        <div class="wx-group fiexd-b">
                            <ul class="wx-functions">
                                <li class="wx-item">
                                    <div class="wx-header "><button class="form-button">提交投诉</button></div>
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
