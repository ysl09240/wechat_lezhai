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
                                <div class="wx-header">小区通知</div>
                            </li>
                        </ul>
                    </div>
                    <div class="wx-group">
                        <div class="wx-space"></div>
                        <ul class="wx-functions">
                            <c:forEach var="notice" items="${noticeList.content}">
                                <li class="wx-item">
                                    <span class="wx-icon wx-icon-sanmarino wx-yang">
                                        <i class="fa fa-coffee"></i>
                                    </span>
                                    ${notice.id}
                                    <div class="wx-name">${notice.title}</div>
                                    <div class="text-right prm text-muted"><span>2015-06-12</span></div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
</html>
