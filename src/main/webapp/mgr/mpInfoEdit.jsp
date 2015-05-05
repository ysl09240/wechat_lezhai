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
  <link rel="stylesheet" href="./assets/css/common-space.css">
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
  <form>
    <div class="form-group">
      <label for="pmcSigninName">物业公司登录名称(乐宅365)</label>
      <input type="text" name="pmcSigninName" class="form-control" id="pmcSigninName" placeholder="物业公司登录名称(乐宅365)">
    </div>
    <div class="form-group">
      <label for="wechatName">微信公众号名称</label>
      <input type="text" name="wechatName" id="wechatName" class="form-control" placeholder="微信公众号名称">
    </div>
    <div class="form-group">
      <label for="wechatOriginId">微信公众原始ID</label>
      <input type="text" id="wechatOriginId" name="wechatOriginId" class="form-control" placeholder="微信公众原始ID">
    </div>

    <div class="form-group">
      <label for="wechatAppId">appId</label>
      <input type="text" name="wechatAppId" id="wechatAppId" class="form-control" placeholder="appId">
    </div>
    <div class="form-group">
      <label for="wechatAppSecret">appSecret</label>
      <input type="text" name="wechatAppSecret" id="wechatAppSecret" class="form-control" placeholder="appSecret">
    </div>
    <div class="form-group">
      <label for="wechatToken">token</label>
      <input type="text" name="wechatToken" id="wechatToken" class="form-control" placeholder="Enter email">
    </div>
    <div class="form-group">
      <label for="wechatEncodingASKey">EncodingAESKey</label>
      <input type="text" id="wechatEncodingASKey" id="wechatEncodingASKey" class="form-control" placeholder="Enter email">
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
  </form>
</div>

</body>
</html>

