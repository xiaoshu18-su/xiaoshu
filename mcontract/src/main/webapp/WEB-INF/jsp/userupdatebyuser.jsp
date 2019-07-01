<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>企业合同管理系统-修改个人资料</title>
<link href="${ctx }/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="${ctx }/css/font-awesome.css?v=4.4.0" rel="stylesheet">
<link href="${ctx }/css/plugins/iCheck/custom.css" rel="stylesheet">
<link href="${ctx }/css/animate.css" rel="stylesheet">
<link href="${ctx }/css/style.css?v=4.1.0" rel="stylesheet">
<link href="${ctx }/css/sweetalert.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							修改个人资料 &nbsp;&nbsp; <small>默认为原信息</small>
						</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<form role="form" method="post" class="form-horizontal"
							id="update_user_form">
							<!-- id隐藏域 -->
							<input id="user_id" type="hidden" name="user_id">
							<div class="form-group">
								<label class="col-sm-2 control-label">名字:</label>
								<div class="col-sm-10">
									<input id="user_name" type="text" class="form-control"
										name="user_name">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">性别:</label>
								<div class="col-sm-10">
									<select id="user_sex" class="form-control m-b" name="user_sex">
										<option value="男">男</option>
										<option value="女">女</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">地址:</label>
								<div class="col-sm-10">
									<textarea rows="5" cols="" id="user_address"
										class="form-control" name="user_address">${user.user_address }</textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">手机号码:</label>
								<div class="col-sm-10">
									<input id="user_phone" type="text" class="form-control"
										name="user_phone">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">qq号码:</label>
								<div class="col-sm-10">
									<input id="user_qq_no" type="text" class="form-control"
										name="user_qq_no">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">生日:</label>
								<div class="col-sm-10">
									<input id="user_birthday"
										class="laydate-icon form-control layer-date"
										name="user_birthday">
								</div>
							</div>
							<c:if
								test="${user.user_secret_id == null || user.user_secret_id == ''}">
								<div class="form-group">
									<label class="col-sm-2 control-label">设置我的密保问题:</label>
									<div class="col-sm-10">
										<select id="user_secret_id" class="form-control m-b"
											name="user_secret_id">
											<option value="">请选择</option>
											<c:forEach items="${secrets }" var="secret">
												<option value="${secret.secret_id }">${secret.secret_name }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">答案:</label>
									<div class="col-sm-10">
										<input id="user_secret_answer" type="text"
											class="form-control" name="user_secret_answer">
									</div>
								</div>
							</c:if>
							<c:if
								test="${user.user_secret_id != null && user.user_secret_id != ''}">
								<div class="form-group">
									<label class="col-sm-2 control-label">修改我的密保问题:</label>
									<div class="col-sm-10">
										<select id="user_secret_id" class="form-control m-b"
											name="user_secret_id">
											<c:forEach items="${secrets }" var="secret">
												<option value="${secret.secret_id }">${secret.secret_name }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">答案:</label>
									<div class="col-sm-10">
										<input id="user_secret_answer" type="text"
											class="form-control" name="user_secret_answer">
									</div>
								</div>
							</c:if>
							<div class="form-group">
								<div class="col-sm-4 col-sm-offset-2">
									<button id="add_user_btn" class="btn btn-primary" type="submit">保存内容</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${ctx }/js/jquery.min.js"></script>
	<script src="${ctx }/js/bootstrap.min.js"></script>
	<script src="${ctx }/js/content.js"></script>
	<script src="${ctx }/js/plugins/iCheck/icheck.min.js"></script>
	<script src="${ctx }/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="${ctx }/js/plugins/validate/messages_zh.min.js"></script>
	<script src="${ctx }/js/plugins/layer/laydate/laydate.js"></script>
	<script src="${ctx }/js/sweetalert.min.js"></script>
	<script type="text/javascript">
		//设置默认值
		$("#user_id").val("${user.user_id}");
		$("#user_name").val("${user.user_name}");
		$("#user_sex").find("option[value='${user.user_sex}']").attr(
				"selected", true);
		$("#user_secret_id").find("option[value='${user.user_secret_id }']")
				.attr("selected", true);
		$("#user_secret_answer").val("${user.user_secret_answer}");
		$("#user_phone").val("${user.user_phone}");
		$("#user_qq_no").val("${user.user_qq_no}");
		$("#user_birthday")
				.val(
						"<fmt:formatDate value='${user.user_birthday }' pattern='yyyy-MM-dd'/>");
		//日期范围限制
		laydate({
			elem : '#user_birthday',
			min : '1900-06-16',
			max : laydate.now(),
		});
		//错误提示信息图标、
		var icon = "<i class='fa fa-times-circle'></i>  ";
		//使用jquery.validate.js进行校验
		$("#update_user_form").validate(
				{
					/*  自定义规则 */
					rules : {
						user_name : {
							required : true,
						},
						user_address : {
							required : true,
						},
						user_email : {
							required : true,
							email : true,
						},
						user_phone : {
							required : true,
							rangelength : [ 11, 11 ],
							number : true,
							digits : true,
						},
						user_qq_no : {
							required : true,
							rangelength : [ 5, 11 ],
							number : true,
							digits : true,
						},
					},
					messages : {
						user_name : {
							required : icon + "必填信息",
						},
						user_address : {
							required : icon + "必填信息",
						},
						user_email : {
							required : icon + "必填信息",
							email : icon + "输入邮箱格式，如@qq.com  @163.com结尾",
						},
						user_phone : {
							required : icon + "必填信息",
							rangelength : icon + "输入正确的手机号码",
							number : icon + "输入正确的手机号码",
							digits : icon + "输入正确的手机号码"
						},
						user_qq_no : {
							required : icon + "必填信息",
							rangelength : icon + "输入正确的qq号码",
							number : icon + "输入正确的qq号码",
							digits : icon + "输入正确的qq号码"
						},
					},
					/* 表单按钮提交触发函数 */
					submitHandler : function(form) {
						$.post("${ctx}/user/updateuserbyid.do", $(form)
								.serialize(), function(msg) {
							//console.log(msg);
							if (msg == "yes") {
								swal({
									title : "修改成功",
									text : "查看新的信息吧",
									type : "success"
								});
								setTimeout(function() {
									window.location.reload()
								}, 1000);
							} else {
								swal({
									title : "修改失败",
									text : "可能存在相同的信息",
									type : "error"
								});
							}
						});
					}
				});
	</script>
</body>
</html>