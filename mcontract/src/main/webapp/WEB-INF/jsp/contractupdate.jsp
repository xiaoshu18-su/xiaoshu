<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>企业合同管理系统-修改合同信息</title>
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
						<h5>修改合同信息&nbsp;&nbsp; <small>默认为原信息</small></h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<form role="form" class="form-horizontal" method="post" id="add_contract_form"
                         enctype="multipart/form-data" >
                            <div class="form-group">
								<label class="col-sm-2 control-label">合同编号:</label>
								<div class="col-sm-10">
									<input id="contract_id" type="text" class="form-control"
										name="contract_id" readonly="readonly" value="${c.contract_id }">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">合同名称:</label>
								<div class="col-sm-10">
									<input id="contract_name" type="text" class="form-control"
										name="contract_name" value="${c.contract_name }">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">甲方:</label>
								<div class="col-sm-10">
									<input id="contract_parta" type="text" class="form-control"
										name="contract_parta" value="${c.contract_parta }">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">乙方:</label>
								<div class="col-sm-10">
									<input id="contract_partb" type="text" class="form-control"
										name="contract_partb" value="${c.contract_partb }">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">乙方代表人:</label>
								<div class="col-sm-10">
									<input id="contract_partbrepresentative" type="text" class="form-control"
										name="contract_partbrepresentative" value="${c.contract_partbrepresentative }">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">乙方地址:</label>
								<div class="col-sm-10">
									<input id="contract_partbaddress" type="text" class="form-control"
										name="contract_partbaddress" value="${c.contract_partbaddress }">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">乙方电话:</label>
								<div class="col-sm-10">
									<input id="contract_partbtelephone" type="text" class="form-control"
										name="contract_partbtelephone" value="${c.contract_partbtelephone }">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">乙方签订时间:</label>
								<div class="col-sm-10">
									<input id="contract_partbsigningtime" type="text" class="form-control"
										name="contract_partbsigningtime" value="${c.contract_partbsigningtime }">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">开始时间:</label>
								<div class="col-sm-10">
									<input id="contract_startime" type="text" class="form-control"
										name="contract_startime" value="${c.contract_startime }">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">结束时间:</label>
								<div class="col-sm-10">
									<input id="contract_endtime" type="text" class="form-control"
										name="contract_endtime" value="${c.contract_endtime }">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">预收款时间:</label>
								<div class="col-sm-10">
									<input id="contract_advancetime" type="text" class="form-control"
										name="contract_advancetime" value="${c.contract_advancetime }">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">金额:</label>
								<div class="col-sm-10">
									<input id="contract_money" type="text" class="form-control"
										name="contract_money" value="${c.contract_money }">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">合同原文件:</label>
								<div class="col-sm-10">
									<span class="form-control">${c.contract_filename }</span>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">重新上传合同文件:</label>
								<div class="col-sm-10">
									<input id="contract_file" type="file" name="contract_file">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">备注:</label>
								<div class="col-sm-10">
									<input id="contract_remarks" type="text" class="form-control"
										name="contract_remarks" value="${c.contract_remarks }">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-4 col-sm-offset-2">
									<button id="add_contract_btn" class="btn btn-primary"
										type="submit">修改合同</button>
									<a href="javascript:window.history.back(-1);" class="btn btn-white">返回</a>
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
        	elem: '#contract_startime',
            min: '1900-08-18', 
            max: laydate.now(), 
        });
		laydate({
			elem: '#contract_endtime',
            min: laydate.now(), 
            max: '2200-08-18', 
        }); 
		laydate({
			elem: '#contract_advancetime',
            min: '1900-08-18', 
            max: '2200-08-18', 
        }); 
		//错误提示信息图标、
	      var icon = "<i class='fa fa-times-circle'></i>";
		//使用jquery.validate.js进行校验
		$("#add_contract_form").validate({
			/*  自定义规则 */
			rules:{
				contract_name:{
					required:true,
				},
				contract_parta:{
					required:true,
				},
				contract_partb:{
					required:true,
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
				contract_startime:{
					required:true,
				},
				contract_endtime:{
					required:true,
				},
				contract_money:{
					number:true,
					min:0.000,
					maxlength:20,
				},
			},
			messages:{
				contract_name:{
					required:icon+"必填信息",
				},
				contract_parta:{
					required:icon+"必填信息",
				},
				contract_partb:{
					required:icon+"必填信息",
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
				contract_startime:{
					required:icon+"必填信息",
				},
				contract_endtime:{
					required:icon+"必填信息",
				},
				contract_money:{
					number:icon+"请输入合法的数字",
					min:icon+"金额不要小于0元",
					maxlength:icon+"数字不要超出20位",
				},
			},
			//使用formdata和ajax提交表单，包括文件上传
			submitHandler:function(form){
				 if($("#contract_file").val()!=null && $("#contract_file").val()!=''){
					var file=$("#contract_file").val();
		        	var extStart=file.lastIndexOf(".");
					var ext=file.substring(extStart,file.length).toUpperCase();
					if(ext!=".DOC"&&ext!=".DOCX"&&ext!=".WPS"&&ext!=".PDF"){
							swal({
								title:"添加失败",
								text:"请上传正确的合同文件",
								type:"error"
						    });
							return false;
					}
				 }
			     var formData = new FormData($( "#add_contract_form" )[0]); 
			     $.ajax({  
			          url: '${ctx}/contract/update.do',  
			          type: 'POST',  
			          data: formData,  
			          cache: false,  
			          contentType: false,  
			          processData: false,  
			          success: function (data) {  
			        	  if(data == "no"){
			 					swal({
			 						title:"修改失败",
			 						text:"请检查信息",
			 						type:"error"
			 					});
			 				}else{
			 					swal({
			 					title:"修改成功",
			 					text:"",
			 					type:"success"
			 				   });
			 				setTimeout(function(){ 
			 					window.location.href="${ctx}/contract/list.do"
			 				},1000);
			 				} 
			          }, 
			          error:function(){
			        	  swal({
		 						title:"修改失败",
		 						text:"服务器出错",
		 						type:"error"
		 					});
			          }
			     }); 
			}
		});
    </script>
</body>
</html>