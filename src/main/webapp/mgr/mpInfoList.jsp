<%--
  Created by IntelliJ IDEA.
  User: Huzi
  Date: 2015-05-05
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
  <link rel="stylesheet" href="../assets/css/common-space.css">
  <title>微信公众号管理</title>
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">公众号管理</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="#">菜单管理</a></li>
        <li><a href="#">消息设置</a></li>
        <li><a href="#">用户管理</a></li>
        <li><a href="#">素材管理</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

<div class="container">
  <div class="pull-right pbm">
    <button type="button" class="btn btn-success">添加公众号</button>
  </div>

  <table class="table table-bordered">
    <thead>
    <tr>
      <th>#</th>
      <th>物业公司登录名称</th>
      <th>公众账号名称</th>
      <th>微信公众账号原始id</th>
      <th>appId</th>
      <th>appSecret</th>
      <th>token</th>
      <th>EncodingAESKey</th>
      <th>创建时间</th>
      <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <tr>
      <th scope="row">1</th>
      <td>Mark</td>
      <td>Otto</td>
      <td>@mdo</td>
      <td>Mark</td>
      <td>Otto</td>
      <td>@mdo</td>
      <td>Mark</td>
      <td>Otto</td>
      <td><a>修改</a></td>
    </tr>
    </tbody>
  </table>
</div>

</body>
</html>

