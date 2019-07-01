<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">
<title>企业合同管理系统</title>
<link rel="shortcut icon" href="${ctx }/img/icon.ico" />
<link href="${ctx }/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="${ctx }/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
<link href="${ctx }/css/animate.css" rel="stylesheet">
<link href="${ctx }/css/style.css?v=4.1.0" rel="stylesheet">
<link href="${ctx }/css/sweetalert.css" rel="stylesheet">
</head>
<body class="fixed-sidebar full-height-layout gray-bg"
	style="overflow: hidden">
	<div id="wrapper">
		<!--左侧导航开始-->
		<nav class="navbar-default navbar-static-side" role="navigation">
			<div class="nav-close">
				<i class="fa fa-times-circle"></i>
			</div>
			<div class="sidebar-collapse">
				<ul class="nav" id="side-menu">
					<li class="nav-header">
						<div class="dropdown profile-element">
							<span> <img width="65px" height="65px" alt="头像" title="头像"
								class="img-circle" src="${ctx }${USER_IMG }" />
							</span> <a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<span class="clear"> <span class="block m-t-xs"><strong
										class="font-bold">${COOKIE_USER.user_name }</strong></span> <span
									class="text-muted text-xs block">${COOKIE_USER.usertype.usertype_name }<b
										class="caret"></b></span>
							</span>
							</a>
							<ul class="dropdown-menu animated fadeInRight m-t-xs">
								<li><a class="J_menuItem"
									href="${ctx }/index/to_img_update.do">修改头像</a></li>
								<li><a data-toggle="modal" data-target="#myModel">修改密码</a>
								</li>
								<li><a class="J_menuItem" href="${ctx }/user/toupubu.do">修改个人资料</a>
								</li>
								<li><a class="J_menuItem" href="${ctx }/email/emailbox.do">
										<c:if test="${countemail !='0' }">
											<span class="label label-warning pull-right">${countemail }</span>
										</c:if> 信箱
								</a></li>
								<li class="divider"></li>
								<li><a onclick="userlogout()"> 安全退出 </a></li>
							</ul>
						</div>
						<div class="logo-element">C</div>
					</li>
					<li><a> <i class="glyphicon glyphicon-user"></i> <span
							class="nav-label">员工管理</span> <span class="fa arrow"></span>
					</a>
						<ul class="nav nav-second-level">
							<li><a class="J_menuItem" href="${ctx }/user/selectuser.do">编辑员工</a>
							</li>
							<c:if
								test="${COOKIE_USER.user_type == 2 or COOKIE_USER.user_type == 3}">
								<li><a class="J_menuItem" href="${ctx }/user/toadduser.do">添加员工</a>
								</li>
							</c:if>
						</ul></li>
					<c:if
						test="${COOKIE_USER.user_type == 2 or COOKIE_USER.user_type == 3}">
						<li><a class="J_menuItem" href="${ctx }/department/list.do">
								<i class="glyphicon glyphicon-home"></i> <span class="nav-label">部门管理</span>
						</a></li>
					</c:if>
					<li><a href="#"><i class="glyphicon glyphicon-file"></i> <span
							class="nav-label">合同管理</span><span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a class="J_menuItem" href="${ctx }/contract/list.do">合同编辑</a>
							</li>
							<c:if
								test="${COOKIE_USER.user_type == 2 or COOKIE_USER.user_type == 3}">
								<li><a class="J_menuItem" href="${ctx }/contract/toadd.do">添加合同</a>
								</li>
							</c:if>
						</ul></li>
					<li><a><i class="fa fa-envelope"></i> <span
							class="nav-label">信箱 </span> <c:if test="${countemail !='0' }">
								<span class="label label-warning pull-right">${countemail }</span>
							</c:if> <c:if test="${countemail =='0' }">
								<span class="fa arrow"></span>
							</c:if> </a>
						<ul class="nav nav-second-level">
							<li><a class="J_menuItem" href="${ctx }/email/emailbox.do">
									<span class="label label-warning pull-right">${countemail }</span>收件箱
							</a></li>
							<li><a class="J_menuItem" href="${ctx }/email/to_compose.do">写信</a>
							</li>
						</ul></li>
					<li><a href="#"><i class="glyphicon glyphicon-signal"></i>
							<span class="nav-label">信息和统计</span><span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a class="J_menuItem"
								href="${ctx }/index/chartcontract.do">合同信息统计</a></li>
							<li><a class="J_menuItem" href="${ctx }/count/list.do">用户登录信息统计</a>
							</li>
							<c:if
								test="${COOKIE_USER.user_type == 2 or COOKIE_USER.user_type == 3}">
								<li><a href="#">导出系统信息<span class="fa arrow"></span></a>
									<ul class="nav nav-third-level">
										<li><a href="${ctx }/contract/poiContract.do">导出合同信息</a>
										</li>
										<li><a href="${ctx }/user/poiUser.do">导出员工信息</a></li>
									</ul></li>
							</c:if>
						</ul></li>
					<c:if
						test="${COOKIE_USER.user_type == 2 or COOKIE_USER.user_type == 3}">
						<li><a class="J_menuItem" href="${ctx }/template/template.do">
								<i class="glyphicon glyphicon-duplicate"></i> <span
								class="nav-label">模板管理</span>
						</a></li>
					</c:if>
					<c:if test="${COOKIE_USER.user_type == 3}">
						<li><a class="J_menuItem" href="${ctx }/index/privilege.do">
								<i class="glyphicon glyphicon-lock"></i> <span class="nav-label">权限管理</span>
						</a></li>
						<li><a class="J_menuItem" href="${ctx }/index/maintenance.do">
								<i class="glyphicon glyphicon-wrench"></i> <span
								class="nav-label">系统维护</span>
						</a></li>
					</c:if>
				</ul>
			</div>
		</nav>
		<!--左侧导航结束-->
		<!--右侧部分开始-->
		<div id="page-wrapper" class="gray-bg dashbard-1">
			<div class="row border-bottom">
				<nav class="navbar navbar-static-top" role="navigation"
					style="margin-bottom: 0">
					<div class="navbar-header">
						<a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
							href="#"><i class="fa fa-bars"></i> </a>
						<div class="form-group">
							<img src="${ctx }/img/qyhtglxt.png" alt="企业合同管理系统"
								title="企业合同管理系统" />
						</div>
					</div>
					<ul class="nav navbar-top-links navbar-right">
						<li class="dropdown hidden-xs"><a
							class="right-sidebar-toggle" aria-expanded="false"> <i
								class="fa fa-tasks"></i> 主题
						</a></li>
					</ul>
				</nav>
			</div>
			<div class="row content-tabs">
				<button class="roll-nav roll-left J_tabLeft">
					<i class="fa fa-backward"></i>
				</button>
				<nav class="page-tabs J_menuTabs">
					<div class="page-tabs-content">
						<a href="javascript:;" class="active J_menuTab"
							data-id="index_v1.html">首页</a>
					</div>
				</nav>
				<button class="roll-nav roll-right J_tabRight">
					<i class="fa fa-forward"></i>
				</button>
				<div class="btn-group roll-nav roll-right">
					<button class="dropdown J_tabClose" data-toggle="dropdown">
						关闭操作<span class="caret"></span>
					</button>
					<ul role="menu" class="dropdown-menu dropdown-menu-right">
						<li class="J_tabShowActive"><a>定位当前选项卡</a></li>
						<li class="divider"></li>
						<li class="J_tabCloseAll"><a>关闭全部选项卡</a></li>
						<li class="J_tabCloseOther"><a>关闭其他选项卡</a></li>
					</ul>
				</div>
				<button onclick="userlogout()" class="roll-nav roll-right J_tabExit">
					<i class="fa fa fa-sign-out"></i> 退出
				</button>
			</div>

			<div class="row J_mainContent" id="content-main">
				<iframe class="J_iframe" name="iframe0" width="100%" height="100%"
					src="${ctx }/index/toIndex_v1.do" frameborder="0"
					data-id="index_v1.html" seamless></iframe>
			</div>
		</div>
		<!--右侧部分结束-->
		<!--右侧边栏开始-->
		<div id="right-sidebar">
			<div class="sidebar-container">
				<div class="tab-content">
					<div id="tab-1" class="tab-pane active">
						<div class="sidebar-title">
							<h3>
								<i class="fa fa-comments-o"></i> 主题设置
							</h3>
							<small><i class="fa fa-tim"></i>
								你可以从这里选择和预览主题的布局和样式，这些设置会被保存在本地，下次打开的时候会直接应用这些设置。</small>
						</div>
						<div class="skin-setttings">
							<div class="title">主题设置</div>
							<div class="setings-item">
								<span>收起左侧菜单</span>
								<div class="switch">
									<div class="onoffswitch">
										<input type="checkbox" name="collapsemenu"
											class="onoffswitch-checkbox" id="collapsemenu"> <label
											class="onoffswitch-label" for="collapsemenu"> <span
											class="onoffswitch-inner"></span> <span
											class="onoffswitch-switch"></span>
										</label>
									</div>
								</div>
							</div>
							<div class="setings-item">
								<span>固定顶部</span>

								<div class="switch">
									<div class="onoffswitch">
										<input type="checkbox" name="fixednavbar"
											class="onoffswitch-checkbox" id="fixednavbar"> <label
											class="onoffswitch-label" for="fixednavbar"> <span
											class="onoffswitch-inner"></span> <span
											class="onoffswitch-switch"></span>
										</label>
									</div>
								</div>
							</div>
							<div class="setings-item">
								<span> 固定宽度 </span>

								<div class="switch">
									<div class="onoffswitch">
										<input type="checkbox" name="boxedlayout"
											class="onoffswitch-checkbox" id="boxedlayout"> <label
											class="onoffswitch-label" for="boxedlayout"> <span
											class="onoffswitch-inner"></span> <span
											class="onoffswitch-switch"></span>
										</label>
									</div>
								</div>
							</div>
							<div class="title">皮肤选择</div>
							<div class="setings-item default-skin nb">
								<span class="skin-name "> <a href="#" class="s-skin-0">
										默认皮肤 </a>
								</span>
							</div>
							<div class="setings-item blue-skin nb">
								<span class="skin-name "> <a href="#" class="s-skin-1">
										蓝色主题 </a>
								</span>
							</div>
							<div class="setings-item yellow-skin nb">
								<span class="skin-name "> <a href="#" class="s-skin-3">
										黄色/紫色主题 </a>
								</span>
							</div>
						</div>
					</div>
					<div id="tab-2" class="tab-pane">

						<div></div>
					</div>
				</div>
			</div>
		</div>
		<!--右侧边栏结束-->
	</div>
	<!-- 修改密码模态框 -->
	<!-- Modal -->
	<div class="modal fade" id="myModel" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">修改密码</h4>
				</div>
				<form id="updateUserPwd_form" method="post">
					<div class="modal-body">
						<div class="form-group">
							<label for="exampleInputEmail1">原密码</label> <input
								type="password" class="form-control" id="password1"
								name="password1" placeholder="Password">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">新密码</label> <input
								type="password" class="form-control" id="password2"
								name="password2" placeholder="Password">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">确认密码</label> <input
								type="password" class="form-control" id="password3"
								name="password3" placeholder="Password">
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">确认修改</button>
					</div>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<script src="${ctx }/js/jquery.min.js?v=2.1.4"></script>
	<script src="${ctx }/js/jquery-form.js"></script>
	<script src="${ctx }/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${ctx }/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="${ctx }/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="${ctx }/js/plugins/layer/layer.min.js"></script>
	<script src="${ctx }/js/hplus.js?v=4.1.0"></script>
	<script src="${ctx }/js/contabs.js"></script>
	<script src="${ctx }/js/plugins/iCheck/icheck.min.js"></script>
	<script src="${ctx }/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="${ctx }/js/plugins/validate/messages_zh.min.js"></script>
	<script src="${ctx }/js/plugins/pace/pace.min.js"></script>
	<script src="${ctx }/js/sweetalert.min.js"></script>
	<script src="${ctx }/js/layer.js"></script>
	<script type="text/javascript">
       function userlogout(){
    	    // 动作触发后执行的代码!!
    	   swal({
               title: "确定要退出该系统吗？",
               text: "退出后就要重新登录了。",
               type: "warning",
               showCancelButton: true,
               closeOnConfirm: false,
               confirmButtonText: "是的，我要退出！",
               confirmButtonColor: "#ec6c62",
               cancelButtonText: "容我三思"
           }, function (isConfirm) {
               if (!isConfirm) return;
               $.post(
              	     "${ctx}/user/logout.do",{},
              	     function(msg) {
          				if(msg=="ok"){
          					setTimeout(function(){ 
          						window.location.href="${ctx}/user/tologin.do" 
          					},1000);
          				}
          		 });
           });
    	};
    	/* 
    	下面修改密码方法
    	*/
        //错误提示信息图标、
	    var icon = "<i class='fa fa-times-circle'></i>  ";
		//使用jquery.validate.js进行校验
		$("#updateUserPwd_form").validate({
			/*  自定义规则 */
			rules:{
				password1:{
					required:true,
					minlength : 6
				},
				password2:{
					required:true,
					minlength : 6
				},
				password3:{
					required:true,
					minlength : 6,
					equalTo:"#password2"
				},
			},
			messages:{
				password1:{
					required:icon+"必填信息",
					minlength : icon + "密码最少填写6位"
				},
				password2:{
					required:icon+"必填信息",
					minlength : icon + "密码最少填写6位"
				},
				password3:{
					required:icon+"必填信息",
					minlength : icon + "密码最少填写6位",
					equalTo:icon + "两次输入的密码不一致哦",
				},
			},
			//使用formdata和ajax提交表单，包括文件上传
			submitHandler:function(form){
				if($("#password1").val()==$("#password3").val()){
					swal({
						title : "修改失败",
						text : "新密码不要和原密码一样哦",
						type : "error"
					});
					return false;
				}
				if($("#password1").val()==$("#password2").val()){
					swal({
						title : "修改失败",
						text : "新密码不要和原密码一样哦",
						type : "error"
					});
					return false;
				}
				$.post(
						"${ctx}/user/updatePwd.do",
						$(form).serialize(),
						function(msg) {
						//console.log(msg);
						if (msg == "no") {
							//登录失败
							swal({
								title : "修改失败",
								text : "原密码可能错误了",
								type : "error"
							});
						} else {
						        swal({
									title : "修改成功",
									text : "请重新登录",
									type : "success"
								});
							setTimeout(
							function() {
								window.location.href = "${ctx}/user/logout2.do"
						}, 1500);
				}
			});  
			}
		});
    </script>
</body>
</html>
