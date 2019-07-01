<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>合同管理系统 - 系统维护</title>
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
			<div class="col-sm-7">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							系统维护&nbsp;&nbsp;&nbsp;<small>用户账号管理</small>
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
									<th>角色</th>
									<th>部门</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${users }" var="u">
									<tr>
										<td>${u.user_id }</td>
										<td>${u.user_name }</td>
										<td>${u.usertype.usertype_name }</td>
										<td>${u.department.department_name }</td>
										<c:if test="${u.user_status==0 }">
											<td id="text${u.user_id }">正常</td>
											<td>
												<button id="btn${u.user_id }" class="btn btn-warning btn-xs"
													type="button" onclick="updatastatusforstop(${u.user_id })">停用</button>
											</td>
										</c:if>
										<c:if test="${u.user_status==1 }">
											<td id="text${u.user_id }">停用</td>
											<td>
												<button id="btn${u.user_id }" class="btn btn-primary btn-xs"
													type="button"
													onclick="updatastatusfornormal(${u.user_id })">启用</button>
											</td>
										</c:if>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-sm-5">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							系统维护&nbsp;&nbsp;&nbsp;<small>清楚登陆记录</small>
						</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a>
						</div>
					</div>
					<table class="table table-bordered">
						<tr>
							<th>用户登录信息清除</th>
							<th>
								<button onclick="maintenance(30)" type="button"
									class="btn btn-info btn-sm">保留30天</button>
								<button onclick="maintenance(7)" type="button"
									class="btn btn-info btn-sm">保留7天</button>
								<button onclick="empty()" type="button"
									class="btn btn-danger btn-sm">全部清除</button>
							</th>
						</tr>
					</table>
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
	   		     "order": [[0,'asc']]
	   		     /* "language": {
		           "emptyTable": "没有员工"
		         } */,
		         "lengthMenu": [[5,10,25,50], [5,10,25,50]]
			 });
	    });
	    //test
	    /* function alerttest(id){
	    	var text=$("#status"+id+"").text();
	    	console.log(text);
	    } */
	    //updatastatusforstop方法
	    //停用方法
	    function updatastatusforstop(id){
	    	$.post("${ctx}/user/updatestatus.do",
    			{user_id:id,user_status:1},
    			function(data){
    				if(data=="yes"){
    			    	$("#text"+id+"").text("停用");
    			    	$("#btn"+id+"").text("启用");
    			    	$("#btn"+id+"").removeAttr("class");
    			    	$("#btn"+id+"").attr("class","btn btn-primary btn-xs");
    			    	$("#btn"+id+"").removeAttr("onclick");
    			    	$("#btn"+id+"").attr("onclick","updatastatusfornormal("+id+")");
    				}
    				else{
                    	swal({
							title:"修改失败",
							text:"请重试",
							type:"error"
						   });
    				}
    			});
	    }
	    //updatastatusfornormal方法
	    //启用方法
	    function updatastatusfornormal(id){
	    	$.post("${ctx}/user/updatestatus.do",
	    			{user_id:id,user_status:0},
	    			function(data){
	    				if(data=="yes"){
	    			    	$("#text"+id+"").text("正常");
	    			    	$("#btn"+id+"").text("停用");
	    			    	$("#btn"+id+"").removeAttr("class");
	    			    	$("#btn"+id+"").attr("class","btn btn-warning btn-xs");
	    			    	$("#btn"+id+"").removeAttr("onclick");
	    			    	$("#btn"+id+"").attr("onclick","updatastatusforstop("+id+")");
	    				}
	    				else{
	                    	swal({
								title:"修改失败",
								text:"请重试",
								type:"error"
							   });
	    				}
	    			});
	    }
	    //清除用户登录记录方法
	    function maintenance(day){
	    	swal({
                title: "确定保留近"+day+"天，清除其他的记录吗？",
                text: "此操作不可逆",
                type: "warning",
                showCancelButton: true,
                closeOnConfirm: false,
                confirmButtonText: "清除！",
                confirmButtonColor: "#ec6c62",
                cancelButtonText: "不清了"
            }, function (isConfirm) {
                if (!isConfirm) return;
                $.post(
                    "${ctx}/count/deleteCountByDay.do",
                    {day:day},
                    function (data) {
                        if (data=="yes") { //后端删除成功
                        	swal({
    							title:"已清除",
    							text:"",
    							type:"success"
    						   });
                        }
                        if(data=="no"){
                        	swal({
    							title:"清除失败",
    							text:"",
    							type:"error"
    						   });  //后端删除失败
                        }
                    });
            });
	    }
	    //清除用户登录记录方法
	    function empty(){
	    	swal({
                title: "确定要清除所有的登录记录吗？",
                text: "此操作不可逆",
                type: "warning",
                showCancelButton: true,
                closeOnConfirm: false,
                confirmButtonText: "清除！",
                confirmButtonColor: "#ec6c62",
                cancelButtonText: "再想想"
            }, function (isConfirm) {
                if (!isConfirm) return;
                $.post(
                    "${ctx}/count/deleteAllCount.do",
                    {},
                    function (data) {
                        if (data=="yes") { //后端删除成功
                        	swal({
    							title:"已清除",
    							text:"",
    							type:"success"
    						   });
                        }
                        if(data=="no"){
                        	swal({
    							title:"清除失败",
    							text:"",
    							type:"error"
    						   });  //后端删除失败
                        }
                    });
            });
	    }
    </script>
</body>
</html>
