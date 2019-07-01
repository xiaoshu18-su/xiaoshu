<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>企业合同管理系统 - 500错误</title>
    <link href="${ctx }/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx }/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx }/css/animate.css" rel="stylesheet">
    <link href="${ctx }/css/style.css?v=4.1.0" rel="stylesheet">
</head>
<body class="gray-bg">
    <div class="middle-box text-center animated fadeInDown">
        <h1>500</h1>
        <h3 class="font-bold">服务器内部错误</h3>
        <div class="error-desc">
            程序员快码加鞭地改bug中...
            <br/>您可以刷新看看
            <br/><a href="javascript:window.location.reload();" class="btn btn-primary m-t">刷新</a>
        </div>
    </div>
    <!-- 全局js -->
    <script src="${ctx }/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx }/js/bootstrap.min.js?v=3.3.6"></script>
</body>
</html>