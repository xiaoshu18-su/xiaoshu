<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>企业合同管理系统-部门管理</title>
<link href="${ctx }/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="${ctx }/css/font-awesome.css?v=4.4.0" rel="stylesheet">
<link href="${ctx }/css/plugins/iCheck/custom.css" rel="stylesheet">
<link href="${ctx }/css/animate.css" rel="stylesheet">
<link href="${ctx }/css/style.css?v=4.1.0" rel="stylesheet">
<link href="${ctx }/css/sweetalert.css" rel="stylesheet">
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
		<div class="">
			<a data-toggle="modal" data-target="#newDeptModel"
				class="btn btn-primary ">新建部门</a>
		</div>
		<br>
		<c:forEach items="${list}" var="d" varStatus="status">
			<c:if test="${status.count eq 1 || (status.count-1) % 4 eq 0}">
				<div class="row">
			</c:if>
			<div class="col-sm-3">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<a onclick="deleteDeptByid(${d.department_id})"> 
						<span class="label label-danger pull-right">删除</span>
						</a> 
						<a onclick="findDeptByid(${d.department_id})" 
						data-toggle="modal" data-target="#updateDeptModel"> 
						<span class="label label-info pull-right">编辑</span>
						</a>
					<%-- 	<h5>${d.department_id }</h5> --%>
					</div>
					<div class="ibox-content">
						<h2 class="no-margins">${d.department_name }</h2>
						<div class="stat-percent font-bold text-success">
							创建时间:
							<fmt:formatDate value='${d.department_time }' type='date'
								dateStyle='default' />
						</div>
					</div>
				</div>
			</div>
			<c:if test="${status.count % 4 eq 0 || status.count eq 4}">
	</div>
	</c:if>
	</c:forEach>
	</div>
	<!-- 新建部门模态框 -->
	<div class="modal inmodal" id="newDeptModel" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content animated flipInY">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">新建部门</h4>
					<small class="font-bold">创建一个新的部门 </small>
				</div>
				<form class="m-t" method="post" id="addDeptForm">
					<div class="modal-body">
						<div class="form-group">
							<input type="text" id="department_name" name="department_name"
								class="form-control" placeholder="部门名称">
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
						<button type="submit" class="btn btn-primary">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- 修改部门模态框 -->
	<div class="modal inmodal" id="updateDeptModel" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content animated flipInY">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">编辑部门</h4>
					<small class="font-bold">修改部门名称</small>
				</div>
				<form class="m-t" method="post" id="updateDeptForm">
					<div class="modal-body">
					    <div class="form-group">
							<input type="text" id="update_dept_id" name="update_dept_id"
								class="form-control" readonly="readonly">
						</div>
						<div class="form-group">
							<input type="text" id="update_dept_name" name="update_dept_name"
								class="form-control" placeholder="部门名称">
						</div>
						<div class="form-group">
					         <a>部门人数:<span id="count"></span></a>
				        </div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
						<button type="submit" class="btn btn-primary">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- 全局js -->
	<script src="${ctx }/js/jquery.min.js"></script>
	<script src="${ctx }/js/bootstrap.min.js"></script>

	<!-- 自定义js -->
	<script src="${ctx }/js/content.js"></script>

	<!-- iCheck -->
	<script src="${ctx }/js/plugins/iCheck/icheck.min.js"></script>
	<!-- 引入jquery.validate.js 表单校验框架 -->
	<script src="${ctx }/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="${ctx }/js/plugins/validate/messages_zh.min.js"></script>
	<!-- layerDate plugin javascript -->
	<script src="${ctx }/js/plugins/layer/laydate/laydate.js"></script>

	<script src="${ctx }/js/sweetalert.min.js"></script>
	<script type="text/javascript">
	 //错误提示信息图标、
	 var icon = "<i class='fa fa-times-circle'></i>";
	 /* 添加部门的validate */
	 $("#addDeptForm").validate({
		 /*  自定义规则 */
			rules:{
				department_name:{
					required:true,
					//异步查找是否已经存在，后台需要返回  boolean类型
					remote: {
					    url: "${ctx}/department/check.do",     //后台处理程序
					    type: "post",               //数据发送方式
					    dataType: "json",           //接受数据格式   
					}
				},
			},
			messages:{
				department_name:{
					required:icon+"必填信息",
					remote:icon+"部门已经存在",
				},
			},
			/* 表单按钮提交触发函数 */
			 submitHandler:function(form){
				  $.post("${ctx}/department/add.do",$(form).serialize(),function(msg){
					//console.log(msg);
					if(msg == "no"){
						swal({
							title:"添加失败",
							text:"",
							type:"error"
						});
					}else{
						swal({
						title:"添加成功",
						text:"",
						type:"success"
					   });
					setTimeout(function(){ 
					window.location.reload() 
					},1000);
					}
				});  
			}  
	 });
	 
	 function deleteDeptByid(id){
		 swal({
             title: "确定要删除这个部门吗？",
             text: "",
             type: "warning",
             showCancelButton: true,
             closeOnConfirm: false,
             confirmButtonText: "是的，我要删除！",
             confirmButtonColor: "#ec6c62",
             cancelButtonText: "容我三思"
         }, function (isConfirm) {
             if (!isConfirm) return;
             $.post(
                 "${ctx}/department/delete.do",
                 {department_id:id},
                 function (data) {
                     if (data == "yes") { //后端删除成功
                     	swal({
 							title:"删除成功",
 							text:"部门已经删除",
 							type:"success"
 						   });
                     }
                     if (data == "no") {
                    	 //后端删除失败
                    	 swal({
  							title:"删除失败",
  							text:"删除失败，请重试",
  							type:"error"
  						 });
                     }
                     if(data == "error"){
 						swal({
 						title:"修改失败",
 						text:"部门下还拥有员工，无法删除",
 						type:"error"
 					   });
 					}
                     setTimeout(function(){ 
                         window.location.reload();
                     },1000);
                 });
         });
	 }
	 /* 设置修改模态框的默认信息 */
	 function findDeptByid(id){
		 $.post(
		   "${ctx}/department/find.do",
		   {department_id:id},
		   function(data){
			   $("#count").text(data.count);
			   $("#update_dept_id").val(data.department.department_id);
			   $("#update_dept_name").val(data.department.department_name);
		   }
		 );
	 }
	 /* 修改部门的validate */
	 $("#updateDeptForm").validate({
		 /*  自定义规则 */
			rules:{
				update_dept_name:{
					required:true,
				},
			},
			messages:{
				update_dept_name:{
					required:icon+"必填信息",
				},
			},
			/* 表单按钮提交触发函数 */
			 submitHandler:function(form){
				  $.post("${ctx}/department/update.do",$(form).serialize(),function(msg){
					//console.log(msg);
					if(msg == "no"){
						swal({
							title:"修改失败",
							text:"",
							type:"error"
						});
					}
					if(msg == "error"){
						swal({
						title:"修改失败",
						text:"部门名称已存在",
						type:"error"
					   });
					}
					if(msg == "yes"){
						swal({
							title:"修改成功",
							text:"",
							type:"success"
						   });
						setTimeout(function(){ 
						window.location.reload() 
						},1000);
					}
				});  
			}  
	 });
	</script>
</body>
</html>
