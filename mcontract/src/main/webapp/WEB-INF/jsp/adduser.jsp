<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>企业合同管理系统-添加用户</title>
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
						<h5>添加一个员工信息</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a>

						</div>
					</div>
					<div class="ibox-content">
						<form role="form" method="post" class="form-horizontal"
							id="add_user_form">
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
										class="form-control" name="user_address"></textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">邮箱:</label>
								<div class="col-sm-10">
									<input id="user_email" type="text" class="form-control"
										name="user_email">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">密码:</label>
								<div class="col-sm-10">
									<input id="user_pwd" type="password" class="form-control"
										name="user_pwd">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">身份证号码:</label>
								<div class="col-sm-10">
									<input id="user_id_card" type="text" class="form-control"
										name="user_id_card">
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
								<label class="col-sm-2 control-label">员工职位:</label>
								<div class="col-sm-10">
									<select id="user_type" class="form-control m-b"
										name="user_type">
										<c:forEach items="${usertypes }" var="usertype">
											<option value="${usertype.usertype_id }">${usertype.usertype_name }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">所属部门:</label>
								<div class="col-sm-10">
									<select id="user_part_id" class="form-control m-b"
										name="user_part_id">
										<c:forEach items="${departlist }" var="department">
											<option value="${department.department_id }">${department.department_name }</option>
										</c:forEach>
									</select>
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
								<label class="col-sm-2 control-label">学历:</label>
								<div class="col-sm-10">
									<input id="user_education" type="text" class="form-control"
										name="user_education">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">政治面貌:</label>
								<div class="col-sm-10">
									<input id="user_political" type="text" class="form-control"
										name="user_political">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">民族:</label>
								<div class="col-sm-10">
									<input id="user_nation" type="text" class="form-control"
										name="user_nation">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">生日:</label>
								<div class="col-sm-10">
									<input id="user_birthday" class="form-control"
										name="user_birthday">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">入职日期:</label>
								<div class="col-sm-10">
									<input id="user_entry" class="form-control" name="user_entry">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-4 col-sm-offset-2">
									<button id="add_user_btn" class="btn btn-primary" type="submit">保存内容</button>
									<button id="reset_btn" class="btn btn-white" type="reset">取消</button>
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
	<script src="${ctx }/js/layer.js"></script>
	<script type="text/javascript">
      //错误提示信息图标、
		 var icon = "<i class='fa fa-times-circle'></i>  ";
      //日期范围限制
		laydate({
        	elem: '#user_birthday',
            min: '1900-06-16', 
            max: laydate.now(), 
        });
		laydate({
			elem: '#user_entry',
            min: '1900-06-16', 
            max: laydate.now(), 
        }); 
		
			//使用jquery.validate.js进行校验
			$("#add_user_form").validate({
				/*  自定义规则 */
				rules:{
					user_name:{
						required:true,
					},
					user_address:{
						required:true,
					},
					user_email:{
						required:true,
						email:true,
						//异步查找是邮箱是否已经存在，后台需要返回  boolean类型
						remote: {
						    url: "${ctx}/user/checkEmail.do",     //后台处理程序
						    type: "post",               //数据发送方式
						    dataType: "json",           //接受数据格式   
						}
					},
					user_pwd:{
						required:true,
						minlength:3
					},
					user_id_card:{
						required:true,
						number:true,
						digits:true,
						minlength:18,
						maxlength:18,
						remote: {
						    url: "${ctx}/user/checkId_card.do",     //后台处理程序
						    type: "post",               //数据发送方式
						    dataType: "json",           //接受数据格式   
						}
					},
					user_phone:{
						required:true,
						rangelength:[11,11],
						number:true,
						digits:true,
						remote: {
						    url: "${ctx}/user/checkPhone.do",     //后台处理程序
						    type: "post",               //数据发送方式
						    dataType: "json",           //接受数据格式   
						}
					},
					user_qq_no:{
						required:true,
						rangelength:[5,11],
						number:true,
						digits:true,
					},
					user_education:{
						required:true,
					},
					user_birthday:{
						required:true,
					},
					user_entry:{
						required:true,
					}
				},
				messages:{
	                user_name:{
	            	    required:icon+"必填信息",
					},
					user_address:{
						required:icon+"必填信息",
					},
					user_email:{
						required:icon+"必填信息",
						email:icon+"输入邮箱格式，如@qq.com  @163.com结尾",
						remote:icon+"该邮箱已被使用",
					},
					user_pwd:{
						required:icon+"必填信息",
						minlength:icon+"密码至少3位及以上",
					},
					user_id_card:{
						required:icon+"必填信息",
						number:icon+"输入正确的身份证信息",
						digits:icon+"输入正确的身份证信息",
						minlength:icon+"输入正确的身份证信息",
						maxlength:icon+"输入正确的身份证信息",
						remote:icon+"该身份证已被使用",
					},
					user_phone:{
						required:icon+"必填信息",
						rangelength:icon+"输入正确的手机号码",
						number:icon+"输入正确的手机号码",
						digits:icon+"输入正确的手机号码",
						remote:icon+"该手机号码已被使用",
					},
					user_qq_no:{
						required:icon+"必填信息",
						rangelength:icon+"输入正确的qq号码",
						number:icon+"输入正确的qq号码",
						digits:icon+"输入正确的qq号码"
					},
					user_education:{
						required:icon+"必填信息",
					},
					user_birthday:{
						required:icon+"必填信息",
					},
					user_entry:{
						required:icon+"必填信息",
					}
				},
				/* 表单按钮提交触发函数 */
				 submitHandler:function(form){
					  $.post("${ctx}/user/adduser.do",$(form).serialize(),function(msg){
						//console.log(msg);
						if(msg == "no"){
							swal({
								title:"添加失败",
								text:"请检查表单信息",
								type:"error"
							});
						}else{
							swal({
							title:"添加成功",
							text:"",
							type:"success"
						   });
						setTimeout(function(){ 
						window.location.href="${ctx}/user/toadduser.do" 
						},1000);
						}
					});  
				} 
			});
    </script>
</body>
</html>