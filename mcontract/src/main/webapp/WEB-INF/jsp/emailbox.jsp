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
<title>合同管理系统 - 邮箱列表</title>
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
							邮箱列表<small>查找</small>
						</h5>
						<div class="ibox-tools">
							<a href="${ctx }/email/emailbox.do" class="collapse-link"
								data-toggle="tooltip" data-placement="left" title="刷新列表"> <i
								class="fa fa-refresh"></i>
							</a> <a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<table
							class="table table-striped table-bordered table-hover dataTables-example"
							id="editable">
							<thead>
								<tr>
									<th>发件人</th>
									<th>邮件主题</th>
									<th>发件时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${emails }" var="email">
									<tr class="gradeX">
										<td>
										<a onclick="look(${email.email_id },'${email.email_send }')">
										${email.email_send }
										</a>
										</td>
										<td>
										<a onclick="look(${email.email_id },'${email.email_send }')">
										<c:if test="${email.email_theme eq '合同提醒' }">
										<div style="color: #FF00FF">
										${email.email_theme }
										</div>
										</c:if>
										<c:if test="${email.email_theme != '合同提醒' }">
										${email.email_theme }
										</c:if>
										</a>
										</td>
										<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
												value="${email.email_datetime}" /></td>
										<td>
										<%-- <a href="${ctx}/email/findemailByid.do?email_id=${email.email_id }&user_email=${email.email_send }">
												查看
										</a> --%>
										<a onclick="look(${email.email_id },'${email.email_send }')">
												查看
										</a>
										<a onclick="deleteemail(${email.email_id })">删除</a>
										</td>
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
         $('.dataTables-example').dataTable({
   		     "order": [[2,'desc']],
   		     "language": {
 			           "emptyTable": "还没有收到邮件哦"
 			         }
	    });
         
        function look(id,send){
        	var email=layer.open({
        		type:2,
        		title:"邮件详情",
        		maxmin:true,
        		content:"${ctx}/email/findemailByid.do?email_id="+id+"&user_email="+send,
        	});
        	layer.full(email);
        }
         
        function deleteemail(email_id){
        	swal({
                title: "确定要删除这个邮件吗？",
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
                	"${ctx}/email/deleteemail.do",
                	{email_id:email_id},
                    function (data) {
                        if (data == "yes") { //后端删除成功
                        	swal({
    							title:"删除成功",
    							text:"该邮件已经成功删除了",
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
                        //parent.location.reload();
                        },1000);
                    });
            });
        }
    </script>
</body>
</html>
