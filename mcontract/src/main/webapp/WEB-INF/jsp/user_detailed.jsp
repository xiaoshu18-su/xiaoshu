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
<title>企业合同管理系统-员工信息</title>
<link href="${ctx }/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="${ctx }/css/font-awesome.css?v=4.4.0" rel="stylesheet">
<link href="${ctx }/css/plugins/iCheck/custom.css" rel="stylesheet">
<link href="${ctx }/css/animate.css" rel="stylesheet">
<link href="${ctx }/css/style.css?v=4.1.0" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>员工详细信息</h5>
						<div class="ibox-tools"></div>
					</div>
					<div class="ibox-content">
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">名字：</label>
								<div class="col-sm-9">
									<input type="text" value="${user.user_name }"
										class="form-control" readonly="readonly"> <span
										class="help-block m-b-none">&nbsp;</span>

								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">年龄：</label>
								<div class="col-sm-9">
									<input type="text" value="${user_age }" class="form-control"
										readonly="readonly"> <span
										class="help-block m-b-none">&nbsp;</span>

								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">头像：</label>
								<div class="col-sm-9">
									<img width="100px" height="100px" class="img-circle"
										src="${ctx }${user.user_image }" alt="头像" title="头像" />
								</div>
							</div>
						</div>
					</div>
					<div class="ibox-content">
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">性别：</label>
								<div class="col-sm-9">
									<input type="text" value="${user.user_sex }"
										class="form-control" readonly="readonly"> <span
										class="help-block m-b-none">&nbsp;</span>

								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">邮箱：</label>
								<div class="col-sm-9">
									<input type="text" value="${user.user_email }"
										class="form-control" readonly="readonly"> <span
										class="help-block m-b-none">&nbsp;</span>

								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">身份证：</label>
								<div class="col-sm-9">
									<input type="text" value="${user.user_id_card }"
										class="form-control" readonly="readonly"> <span
										class="help-block m-b-none">&nbsp;</span>

								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">手机号码：</label>
								<div class="col-sm-9">
									<input type="text" value="${user.user_phone }"
										class="form-control" readonly="readonly"> <span
										class="help-block m-b-none">&nbsp;</span>

								</div>
							</div>
						</div>
					</div>
					<div class="ibox-content">
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">qq号码：</label>
								<div class="col-sm-9">
									<input type="text" value="${user.user_qq_no }"
										class="form-control" readonly="readonly"> <span
										class="help-block m-b-none">&nbsp;</span>

								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">角色：</label>
								<div class="col-sm-9">
									<span class="form-control">${user.usertype.usertype_name }</span>
									<span class="help-block m-b-none">&nbsp;</span>

								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">部门：</label>
								<div class="col-sm-9">
									<input type="text" value="${user.department.department_name }"
										class="form-control" readonly="readonly"> <span
										class="help-block m-b-none">&nbsp;</span>

								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">学历：</label>
								<div class="col-sm-9">
									<input type="text" value="${user.user_education }"
										class="form-control" readonly="readonly"> <span
										class="help-block m-b-none">&nbsp;</span>

								</div>
							</div>
						</div>
					</div>
					<div class="ibox-content">
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">政治面貌：</label>
								<div class="col-sm-9">
									<input type="text" value="${user.user_political }"
										class="form-control" readonly="readonly"> <span
										class="help-block m-b-none">&nbsp;</span>

								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">民族：</label>
								<div class="col-sm-9">
									<input type="text" value="${user.user_nation }"
										class="form-control" readonly="readonly"> <span
										class="help-block m-b-none">&nbsp;</span>

								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">生日：</label>
								<div class="col-sm-9">
									<input type="text"
										value="<fmt:formatDate value='${user.user_birthday }' type='date' dateStyle='default'/>"
										class="form-control" readonly="readonly"> <span
										class="help-block m-b-none">&nbsp;</span>

								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">入职日期：</label>
								<div class="col-sm-9">
									<input type="text"
										value="<fmt:formatDate value='${user.user_entry }' type='date' dateStyle='default'/>"
										class="form-control" readonly="readonly"> <span
										class="help-block m-b-none">&nbsp;</span>
								</div>
							</div>
						</div>
					</div>
					<div class="ibox-content">
						<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">状态：</label>
								<div class="col-sm-9">
									<span class="form-control"> <c:if
											test="${user.user_status == 0}">正常</c:if> <c:if
											test="${user.user_status == 1}">停用</c:if>
									</span> <span class="help-block m-b-none">&nbsp;</span>

								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">地址：</label>
								<div class="col-sm-9">
									<textarea rows="5" cols="" class="form-control"
										readonly="readonly">${user.user_address }</textarea>
									<span class="help-block m-b-none">&nbsp;</span>

								</div>
							</div>

						</div>
						<div class="col-md-6">
							<div class="form-group">
								<div class="col-sm-9"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${ctx }/js/jquery.min.js?v=2.1.4"></script>
	<script src="${ctx }/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${ctx }/js/content.js?v=1.0.0"></script>
	<script src="${ctx }/js/plugins/iCheck/icheck.min.js"></script>
	<script type="text/javascript">
	</script>
</body>
</html>