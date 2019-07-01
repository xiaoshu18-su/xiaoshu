<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>企业合同管理系统-首页</title>
<link href="${ctx }/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="${ctx }/css/font-awesome.css?v=4.4.0" rel="stylesheet">
<link href="${ctx }/css/animate.css" rel="stylesheet">
<link href="${ctx }/css/style.css?v=4.1.0" rel="stylesheet">
</head>
<body class="gray-bg">
	<div class="row  border-bottom white-bg dashboard-header">
		<div class="col-sm-12" style="text-align: center;">
			<blockquote class="text-warning" style="font-size: 20px">
				<h2>欢迎使用企业销售合同管理系统</h2>
			</blockquote>
			<hr>
		</div>
	</div>
	<div class="wrapper wrapper-content">
		<div class="row">
			<div class="col-sm-4">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>联系信息</h5>
					</div>
					<div class="ibox-content">
						<p>
							<i class="fa fa-send-o"></i> 邮箱：<a>1540479307@qq.com</a>
						</p>
						<p>
							<i class="fa fa-qq"></i> QQ：<a>1540479307</a>
						</p>
						<p>
							<i class="fa fa-weixin"></i> 微信：<a>15627526650</a>
						</p>
					</div>
				</div>
			</div>
			<!-- <div class="col-sm-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>无法登录</h5>
                    </div>
                    <div class="ibox-content">
                        <p>
                        可能密码错误或者账号已被停用
                        </p>
                        <p>
                        如果是密码错误，请自行找回密码，要注意保护好自己的重要信息，若无法找回密码，联系<a>15627526650</a>
                        </p>
                        <p>
                        若是账号被停用，联系<a>15627526650</a>	
                        </p>
                    </div>
                </div>
            </div> -->
			<div class="col-sm-4">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>初次登录</h5>
					</div>
					<div class="ibox-content">
						<p>
							初始密码为<a>身份证后六位</a>
						</p>
						<p>
							初次登录后请尽快<a>修改自己的密码</a>
						</p>
						<p>
						    尽快设置密保和答案，以便丢失找回密码
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${ctx }/js/jquery.min.js?v=2.1.4"></script>
	<script src="${ctx }/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${ctx }/js/plugins/layer/layer.min.js"></script>
	<script src="${ctx }/js/content.js"></script>
	<script src="${ctx }/js/welcome.js"></script>
	<script src="${ctx }/js/layer.js"></script>
</body>
</html>
