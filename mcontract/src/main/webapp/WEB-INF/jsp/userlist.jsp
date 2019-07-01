<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>合同管理系统 - 员工列表</title>
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
							员工列表<small>分类，查找</small>
						</h5>
						<div class="ibox-tools">
							<a href="javascript:window.location.reload();"
								class="collapse-link" data-toggle="tooltip"
								data-placement="left" title="刷新列表"> <i class="fa fa-refresh"></i></a>
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
									<th>性别</th>
									<th>部门</th>
									<th>角色</th>
									<th>邮箱</th>
									<th>状态</th>
									<c:if test="${user_type==2 or user_type==3}">
										<th>操作</th>
									</c:if>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list }" var="user">
									<tr class="gradeX">
										<td>${user.user_id }</td>
										<td>${user.user_name }</td>
										<td>${user.user_sex }</td>
										<td>${user.department.department_name }</td>
										<td class="center">${user.usertype.usertype_name }</td>
										<td class="center">${user.user_email }</td>
										<td class="center"><c:if test="${user.user_status == 0}">正常</c:if>
											<c:if test="${user.user_status == 1}">停用</c:if></td>
										<c:if test="${user_type==2 or user_type==3}">
											<td><c:if test="${user_type > user.user_type }">
													<%-- <a
														href="${ctx}/user/touser_detailed.do?id=${user.user_id }">查看</a>
													<a href="${ctx}/user/toupdateuser.do?id=${user.user_id }">修改</a> --%>
													<a onclick="find(${user.user_id })">查看</a>
                                    	            <a onclick="update(${user.user_id })">修改</a>
													<a onclick="deleteuser(${user.user_id })">删除</a>
												</c:if> <c:if test="${COOKIE_USER.user_id == user.user_id }">
													<%-- <a
														href="${ctx}/user/touser_detailed.do?id=${user.user_id }">查看</a>
													<a href="${ctx}/user/toupdateuser.do?id=${user.user_id }">修改</a> --%>
													<a onclick="find(${user.user_id })">查看</a>
                                    	            <a onclick="update(${user.user_id })">修改</a>
												</c:if></td>
										</c:if>
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
	<script src="${ctx }/js/layer-v3.1.1/layer/layer.js"></script>
	<script src="${ctx }/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${ctx }/js/plugins/jeditable/jquery.jeditable.js"></script>
	<script src="${ctx }/js/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="${ctx }/js/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="${ctx }/js/content.js?v=1.0.0"></script>
	<script src="${ctx }/js/sweetalert.min.js"></script>
	<script>
    $(document).ready(function () {
        $('.dataTables-example').dataTable({
   		     /* "order": [[0,'asc']], */
   		     "language": {
	           "emptyTable": "没有员工"
	         }/* ,
	         "lengthMenu": [[5,10,25,50], [5,10,25,50]] */
		 });
    });
    
    function find(id){
    	var find=layer.open({
    		type:2,
    		title:"详细信息",
    		maxmin:true,
    		content:"${ctx}/user/touser_detailed.do?id="+id
    	});
    	layer.full(find);
    }
    
    function update(id){
    	var update=layer.open({
    		type:2,
    		title:"详细信息",
    		maxmin:true,
    		content:"${ctx}/user/toupdateuser.do?id="+id
    	});
    	layer.full(update);
    }
    
    function deleteuser(id) {
        	swal({
                title: "确定要删除这个员工吗？",
                text: "删除后可就无法恢复了。",
                type: "warning",
                showCancelButton: true,
                closeOnConfirm: false,
                confirmButtonText: "是的，我要删除！",
                confirmButtonColor: "#ec6c62",
                cancelButtonText: "容我三思"
            }, function (isConfirm) {
                if (!isConfirm) return;
                $.post(
                    "${ctx}/user/deleteuserbyid.do",
                    {"id": id},
                    function (data) {
                        if (data == "yes") { //后端删除成功
                        	swal({
    							title:"删除成功",
    							text:"该员工已经成功删除了",
    							type:"success"
    						   });
                        } else {
                        	swal({
    							title:"删除失败",
    							text:"删除失败，请重试",
    							type:"error"
    						   });  //后端删除失败
                        }
                        setTimeout(function(){ 
                        window.location.reload();
                        },1500);
                    });
            });
		}
    </script>
</body>
</html>
