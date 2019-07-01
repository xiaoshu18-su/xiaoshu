<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>企业合同管理系统-添加合同</title>
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
						<h5>添加一个合同</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<form role="form" class="form-horizontal" method="post"
							id="add_contract_form" enctype="multipart/form-data">
							<div class="form-group">
								<label class="col-sm-2 control-label">合同名称:</label>
								<div class="col-sm-10">
									<input id="contract_name" type="text" class="form-control"
										name="contract_name">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">甲方:</label>
								<div class="col-sm-10">
									<input id="contract_parta" type="text" class="form-control"
										name="contract_parta">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">乙方:</label>
								<div class="col-sm-10">
									<input id="contract_partb" type="text" class="form-control"
										name="contract_partb">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">乙方代表人:</label>
								<div class="col-sm-10">
									<input id="contract_partbrepresentative" type="text" class="form-control"
										name="contract_partbrepresentative">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">乙方地址:</label>
								<div class="col-sm-10">
									<input id="contract_partbaddress" type="text" class="form-control"
										name="contract_partbaddress">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">乙方电话:</label>
								<div class="col-sm-10">
									<input id="contract_partbtelephone" type="text" class="form-control"
										name="contract_partbtelephone">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">乙方签订时间:</label>
								<div class="col-sm-10">
									<input id="contract_partbsigningtime" type="text" class="form-control"
										name="contract_partbsigningtime">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">合同开始时间:</label>
								<div class="col-sm-10">
									<input id="contract_startime" type="text" class="form-control"
										name="contract_startime">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">合同结束时间:</label>
								<div class="col-sm-10">
									<input id="contract_endtime" type="text" class="form-control"
										name="contract_endtime">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">预收款时间:</label>
								<div class="col-sm-10">
									<input id="contract_advancetime" type="text"
										class="form-control" name="contract_advancetime">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">金额:</label>
								<div class="col-sm-10">
									<input id="contract_money" type="text" class="form-control"
										name="contract_money">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">合同文件:</label>
								<div class="col-sm-10">
									<input id="contract_file" type="file" name="contract_file">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">合同跟踪权限:</label>
								<div class="col-sm-10">
									<label class="radio-inline"> <input type="radio" checked
										name="contract_trackstatus" id="contract_trackstatus" value="1">
										所有人
									</label> 
									<label class="radio-inline"> <input type="radio"
										name="contract_trackstatus" id="contract_trackstatus" value="0">
										只有我
									</label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">备注:</label>
								<div class="col-sm-10">
								    <textarea id="contract_remarks" name="contract_remarks"
								     rows="" cols="" class="form-control"></textarea>
								<!-- 	<input id="contract_remarks" type="text" class="form-control"
										name="contract_remarks"> -->
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-4 col-sm-offset-2">
									<button id="add_contract_btn" class="btn btn-primary"
										type="submit">保存合同</button>
									<button id="reset_btn" class="btn btn-white" type="reset">取消</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${ctx }/js/jquery.min.js?v=2.1.4"></script>
	<script src="${ctx }/js/jquery-form.js"></script>
	<script src="${ctx }/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${ctx }/js/content.js"></script>
	<script src="${ctx }/js/plugins/iCheck/icheck.min.js"></script>
	<script src="${ctx }/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="${ctx }/js/plugins/validate/messages_zh.min.js"></script>
	<script src="${ctx }/js/plugins/layer/laydate/laydate.js"></script>
	<script src="${ctx }/js/sweetalert.min.js"></script>
	<script type="text/javascript">
		//日期范围限制
		laydate({
			elem : '#contract_partbsigningtime',
			min : '1900-08-18',
			max : laydate.now(),
		});
		laydate({
			elem : '#contract_startime',
			min : '1900-08-18',
			max : laydate.now(),
		});
		laydate({
			elem : '#contract_endtime',
			min : laydate.now(),
			max : '2200-08-18',
		});
		laydate({
			elem : '#contract_advancetime',
			min : '1900-08-18',
			max : '2200-08-18',
		});
		//错误提示信息图标、
		var icon = "<i class='fa fa-times-circle'></i>";
		//使用jquery.validate.js进行校验
		$("#add_contract_form")
				.validate(
						{
							/*  自定义规则 */
							rules : {
								contract_name : {
									required : true,
								},
								contract_parta : {
									required : true,
								},
								contract_partb : {
									required : true,
								},
								contract_partbrepresentative : {
									required : true,
								},
								contract_partbaddress : {
									required : true,
								},
								contract_partbtelephone : {
									required : true,
									rangelength:[11,11],
									number:true,
									digits:true,
								},
								contract_partbsigningtime : {
									required : true,
								},
								contract_startime : {
									required : true,
								},
								contract_endtime : {
									required : true,
								},
								contract_money : {
									required : true,
									number : true,
									min : 0.000,
									maxlength : 20,
								},
								contract_file : {
									required : true,
								},
							},
							messages : {
								contract_name : {
									required : icon + "请填写合同名称",
								},
								contract_parta : {
									required : icon + "请填写甲方",
								},
								contract_partb : {
									required : icon + "请填写乙方",
								},
								contract_partbrepresentative : {
									required : icon + "请填写乙方代表人",
								},
								contract_partbaddress : {
									required : icon + "请填写乙方地址",
								},
								contract_partbtelephone : {
									required : icon + "请填写乙方电话",
									rangelength:icon + "格式错误",
									number:icon + "格式错误",
									digits:icon + "格式错误",
								},
								contract_partbsigningtime : {
									required : icon + "请填写签订时间",
								},
								contract_startime : {
									required : icon + "请选择开始时间",
								},
								contract_endtime : {
									required : icon + "请选择结束时间",
								},
								contract_money : {
									required : icon + "请输入金额",
									number : icon + "请输入合法的数字",
									min : icon + "金额不要小于0元",
									maxlength : icon + "数字不要超出20位",
								},
								contract_file : {
									required : icon + "请选择文件",
								},
							},
							//使用formdata和ajax提交表单，包括文件上传
							submitHandler : function(form) {
								var file = $("#contract_file").val();
								var extStart = file.lastIndexOf(".");
								var ext = file.substring(extStart, file.length)
										.toUpperCase();
								if (ext != ".DOC" && ext != ".DOCX"
										&& ext != ".WPS" && ext != ".PDF") {
									swal({
										title : "添加失败",
										text : "请上传正确的合同文件",
										type : "error"
									});
									return false;
								}
								var formData = new FormData($("#add_contract_form")[0]);
								$.ajax({
									url : '${ctx}/contract/add.do',
									type : 'POST',
									data : formData,
									cache : false,
									contentType : false,
									processData : false,
									success : function(data) {
										if (data == "no") {
											swal({
												title : "添加失败",
												text : "请检查信息",
												type : "error"
											});
										} else {
											swal({
												title : "添加成功",
												text : "",
												type : "success"
											});
											setTimeout(function() {
												window.location.reload();
											}, 1000);
										}
									}
								});
							}
						});
	</script>
</body>
</html>