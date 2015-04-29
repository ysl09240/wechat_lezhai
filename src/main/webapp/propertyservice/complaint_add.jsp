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
    <title>新增投诉-房间1-1101</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/assets/css/wx.css">
    <link rel="stylesheet" href="/assets/css/common-space.css">
    <link rel="stylesheet" type="text/css" href="/assets/libs/webupload/lib/webuploader.css"/>
    <link rel="stylesheet" type="text/css" href="/assets/libs/webupload/style.css"/>
    <script src="/assets/libs/jquery/jquery-1.9.1.min.js"></script>
    <script src="/assets/libs/jquery/plugins/jquery.validate.min.js"></script>
    <script src="/assets/app/propertyservice/complaintValidate.js"></script>
</head>
<html>
    <body>
        <div class="wx-page">
            <div class="row">
            <div class="col-lg-4 col-sm-6">
                <div class="mobileframe">
                    <form id="addComplaintForm" action="/${signinName}/service/do/addcomplaint">
                        <div class="wx-group">
                            <div class="upload-space">
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
                                <span class="wx-icon wx-icon-sanmarino wx-yang">
                                    <i class="fa fa-coffee"></i>
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
                                <span class="wx-icon wx-icon-sanmarino wx-yang">
                                    <i class="fa fa-coffee"></i>
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
                                    <div class="wx-header "><button class="text-c">提交投诉</button></div>
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
