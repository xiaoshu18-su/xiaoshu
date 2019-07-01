<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>合同管理系统-登录</title>
<link rel="shortcut icon" href="${ctx }/img/icon.ico">
<link href="${ctx }/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx }/css/font-awesome.css" rel="stylesheet">
<link href="${ctx }/css/animate.css" rel="stylesheet">
<link href="${ctx }/css/style.css" rel="stylesheet">
<link href="${ctx }/css/sweetalert.css" rel="stylesheet">
<script>
	if (window.top !== window.self) {
		window.top.location = window.location;
	}
</script>
</head>
<body class="gray-bg">
	<div class="middle-box text-center loginscreen  animated fadeInDown"
	style="margin-top: 40px;">
		<div>
			<!-- <div class="mydiv">
				<h1 class="logo-name">CONTRACT</h1>
			</div> -->
			<h2>欢迎使用</h2>
			<h3>企业合同管理系统</h3>
			<form class="m-t" method="post" id="loginForm">
				<div class="form-group">
					<input type="text" id="username" name="username"
						class="form-control" placeholder="邮箱或手机号登录">
				</div>
				<div class="form-group">
					<input type="password" id="password" name="password"
						class="form-control" placeholder="密码">
				</div>
				<div class="form-group">
					<input type="text" id="valideCode" name="valideCode"
						class="form-control" placeholder="验证码">
				</div>
				<div class="form-group">
				<img id="vrifyCode" src="${ctx }/index/kaptcha.do" 
				onclick="updateimg()" 
				width="96" height="27" alt="点击更新验证码" />
				<span><a href="javascript:document.getElementById('vrifyCode').onclick();">看不清，换一张</a></span>
				</div>
				<div class="form-group">
					<span id="myspan"></span>
				</div>
				<button type="submit" class="btn btn-primary block full-width m-b">登
					录</button>
				<div class="form-group">
					<a id="findsecret" data-toggle="modal" data-target="#myModel2">忘记密码了？去找回密码</a>
				</div>
				<div class="form-group">
					<a data-toggle="modal" data-target="#myModel">用户帮助</a>
				</div>
			</form>
		</div>
	</div>
	<!-- 用户帮助模态框 -->
	<div class="modal fade" id="myModel" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">用户帮助</h4>
				</div>
				<div class="modal-body">
					<h5>无法登录</h5>
					<p>可能密码错误或者账号已被停用</p>
					<p>如果是密码错误，请自行找回密码，要注意保护好自己的重要信息</p>
					<p>
						若是账号被停用或无法找回密码，联系<a>15627526650</a>
					</p>
					<p>
					<h5>初次登录</h5>
					<p>
						初始密码为<a>身份证后六位</a>
					</p>
					<p>
						初次登录后请尽快<a>修改自己的密码</a>
					</p>
					<p></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- 用户找回密码模态框 -->
	<div class="modal fade" id="myModel2" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">找回密码</h4>
				</div>
				<form class="m-t" method="post" id="findUserpwd" class="form-horizontal">
					<div class="modal-body">
						<div class="form-group">
							<label class="col-sm-3 control-label">邮箱或手机号:</label>
							<div class="col-sm-9">
								<input id="username1" type="text" class="form-control" name="username1">
							</div>
						</div>
						<br><br>
						<div class="form-group">
							<label class="col-sm-3 control-label">选择密保问题:</label>
							<div class="col-sm-9">
								<select id="user_secret_id" class="form-control m-b" name="user_secret_id"></select>
							</div>
						</div>
						<br><br>
						<div class="form-group">
							<label class="col-sm-3 control-label">答案:</label>
							<div class="col-sm-9">
								<input id="user_secret_answer" type="text" class="form-control" name="user_secret_answer">
							</div>
						</div>
						<br><br>
						<!-- 给用户的提示信息 -->
						<div class="form-group">
						    <label id="myspan2" class="col-sm-12 control-label"></label>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">提交</button>
					</div>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- 全局js -->
	<script src="${ctx }/js/jquery.min.js"></script>
	<!-- 引入jquery.validate.js 表单校验框架 -->
	<script src="${ctx }/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="${ctx }/js/plugins/validate/messages_zh.min.js"></script>
	<script src="${ctx }/js/bootstrap.min.js"></script>
	<script src="${ctx }/js/sweetalert.min.js"></script>
	<script type="text/javascript">
	    function updateimg(){
	    	document.getElementById("vrifyCode").src="${ctx }/index/kaptcha.do?"+timestamp();
	    }
	    // 时间戳
	    function timestamp() {
	        return 't='+new Date().getTime();
	    }
		/* 账号密码框获取光标清空错误提示的信息 */
		$("#username,#password,#valideCode").focus(function() {
			$("#myspan").text("");
		});
		
		$("#username1,#user_secret_answer").focus(function() {
			$("#myspan2").text("");
		});

		//错误提示信息图标、
		var icon = "<i class='fa fa-times-circle'></i>  ";
		//使用jquery.validate.js进行校验
		$("#loginForm").validate(
			{
			/*  自定义规则 */
				rules : {
					    username : {
						    required : true,
						},
						password : {
						    required : true,
						},
						valideCode : {
						    required : true,
						},
					},
				messages : {
					    username : {
						    required : icon + "请填写账号",
						},
						password : {
						    required : icon + "请填写密码",
						},
						valideCode : {
						    required : icon + "请填写验证码",
						},
					},
				/* 表单按钮提交触发函数 */
				submitHandler : function(form) {
				//form，就是普通DOM元素，不是Jquery对象
				//console.log(form);
                 //将表单的数据序列化:将表单的数据拼接成get提交数据一样的字符串

				//var loginFormData = $(form).serialize();
					//console.log(loginFormData);//user_name=admin&user_pwd=abc
                 //使用jquery发送ajax请求向后台发送账号密码进行登录操作
						$.post(
							"${ctx}/user/login.do",
							$(form).serialize(),
							function(msg) {
							//console.log(msg);
							if (msg.state=="no") {
								//登录失败
								$("#myspan").text(msg.msString);
								$("#myspan").css("color", "red");
							} else {
								window.location.href = "${ctx}/index/toIndex.do"
					        }
				});
			}
	});
		//查出密保问题
		$("#findsecret").click(function(){
			$.post('${ctx}/user/findSecrets.do',{},function(data){
				$("#user_secret_id").empty();
				$("#user_secret_id").append("<option value=''>请选择</option>");
				$.each(data,function(i,secret){
					var option=$("<option value='"+secret.secret_id+"'>"+secret.secret_name+"</option>");
					$("#user_secret_id").append($(option));
				});
			});
		});
		/* 用户找回密码代码  */
		$("#findUserpwd").validate(
				{
				/*  自定义规则 */
					rules : {
						    username1 : {
							   required : true,
							},
							user_secret_answer:{
								required:true,
							}
						},
					messages : {
						    username1 : {
							   required : icon + "请填写邮箱或手机号",
							},
							user_secret_answer:{
								required:icon+"请填写密保答案",
							},
						},
					/* 表单按钮提交触发函数 */
					submitHandler : function(form) {
							$.post(
								"${ctx}/user/findpwd.do",
								$(form).serialize(),
								function(msg) {
								if (msg.user_pwd == null) {
									//信息错误
									$("#myspan2").text("信息错误，无法找回密码");
									$("#myspan2").css("color", "red");
								} 
								if(msg.user_pwd != null) {
								        swal({
											title : "找回成功",
											text : "你的密码是："+msg.user_pwd,
											type : "success"
										});
						        }
					});
				}
		});
	</script>
</body>

</html>
