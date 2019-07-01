<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>合同管理系统 - 权限管理</title>
<link rel="shortcut icon" href="${ctx }/favicon.ico">
<link href="${ctx }/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="${ctx }/css/font-awesome.css?v=4.4.0" rel="stylesheet">
<link href="${ctx }/css/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">
<link href="${ctx }/css/animate.css" rel="stylesheet">
<link href="${ctx }/css/style.css?v=4.1.0" rel="stylesheet">
<link href="${ctx }/css/sweetalert.css" rel="stylesheet">
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							权限管理&nbsp;&nbsp;&nbsp;<small>分配管理员权限或撤销管理员权限</small>
						</h5>
						<div class="ibox-tools">
							<a href="javascript:window.location.reload();"
								class="collapse-link" data-toggle="tooltip"
								data-placement="left" title="刷新"> <i class="fa fa-refresh"></i></a>
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<table
							class="table table-striped table-bordered table-hover dataTables-example"
							id="editable">
							<thead>
								<tr>
									<th>员工编号</th>
									<th>员工姓名</th>
									<th>部门</th>
									<th>状态</th>
									<th>角色</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${users }" var="u">
									<tr>
										<td>${u.user_id }</td>
										<td>${u.user_name }</td>
										<td>${u.department.department_name }</td>
										<c:if test="${u.user_status==0 }">
											<td>正常</td>
										</c:if>
										<c:if test="${u.user_status==1 }">
											<td>停用</td>
										</c:if>
										<!-- 用户类型 -->
										<td id="text${u.user_id }">${u.usertype.usertype_name }</td>
										<!-- 按钮 -->
										<td><c:if test="${u.usertype.usertype_name=='普通用户' }">
												<button id="btn${u.user_id }"
													onclick="setadmin(${u.user_id })" type="button"
													class="btn btn-info btn-xs">设为管理员</button>
											</c:if> <c:if test="${u.usertype.usertype_name=='管理员' }">
												<button id="btn${u.user_id }"
													onclick="setuser(${u.user_id })" type="button"
													class="btn btn-default btn-xs">设为普通用户</button>
											</c:if></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${ctx }/js/jquery.min.js?v=2.1.4"></script>
	<script src="${ctx }/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${ctx }/js/plugins/jeditable/jquery.jeditable.js"></script>
	<script src="${ctx }/js/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="${ctx }/js/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="${ctx }/js/content.js?v=1.0.0"></script>
	<script src="${ctx }/js/sweetalert.min.js"></script>
	<script>
	    $(document).ready(function () {
	        $('.dataTables-example').dataTable({
	   		     "order": [[0,'asc']],
	   		     "language": {
		           "emptyTable": "没有员工"
		         } ,
		         "lengthMenu": [[5,10,25,50], [5,10,25,50]]
			 });
	    }); 
	    //设为管理员方法
	    function setadmin(id){
 	    	$.post(
	    	    "${ctx}/user/setadmin.do",
	    	    {user_id:id},
	    	    function(data){
	    	    	if(data=="yes"){
	    	    		$("#text"+id+"").text("管理员");
	    	    		$("#btn"+id+"").text("设为普通用户");
	    	    		$("#btn"+id+"").removeAttr("onclick");
	    	    		$("#btn"+id+"").attr("onclick","setuser("+id+")");
	    	    		$("#btn"+id+"").removeAttr("class");
	    	    		$("#btn"+id+"").attr("class","btn btn-default btn-xs");
    	    	    }
 	    	    	else{
	    	    		swal({
							title:"修改失败",
							text:"请重试",
							type:"error"
						});
	    	    	}
	    	    }
	    	);
	    }
	    //设为普通用户方法
	    function setuser(id){
	    	$.post(
	    	    "${ctx}/user/setuser.do",
	    	    {user_id:id},
	    	    function(data){
	    	    	if(data=="yes"){
	    	    		$("#text"+id+"").text("普通用户");
	    	    		$("#btn"+id+"").text("设为管理员");
	    	    		$("#btn"+id+"").removeAttr("onclick");
	    	    		$("#btn"+id+"").attr("onclick","setadmin("+id+")");
	    	    		$("#btn"+id+"").removeAttr("class");
	    	    		$("#btn"+id+"").attr("class","btn btn-info btn-xs");
    	    	    }
 	    	    	else{
	    	    		swal({
							title:"修改失败",
							text:"请重试",
							type:"error"
						});
	    	    	}
	    	    }
	    	);
	    }
    </script>
</body>
</html>
