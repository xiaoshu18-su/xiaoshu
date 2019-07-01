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
<title>企业合同管理系统 - 查看邮件</title>
<link href="${ctx }/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="${ctx }/css/font-awesome.css?v=4.4.0" rel="stylesheet">
<link href="${ctx }/css/plugins/iCheck/custom.css" rel="stylesheet">
<link href="${ctx }/css/animate.css" rel="stylesheet">
<link href="${ctx }/css/style.css?v=4.1.0" rel="stylesheet">
<link href="${ctx }/css/sweetalert.css" rel="stylesheet">
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
		<div class="row">
			<div class="col-sm-12 animated fadeInRight">
				<div class="mail-box-header">
					<div class="pull-right tooltip-demo">
						<%-- <a href="${ctx }/email/replyemail.do?email=${email.email_send }"
							class="btn btn-white btn-sm" data-toggle="tooltip"
							data-placement="top" title="回复"> <i class="fa fa-reply"></i>
						</a> --%>
						<a onclick="reply('${email.email_send }')"
							class="btn btn-white btn-sm" data-toggle="tooltip"
							data-placement="top" title="回复"> <i class="fa fa-reply"></i>
						</a>
						<%-- <a href="${ctx }/email/emailbox.do"  data-placement="top"
							data-toggle="tooltip"  title="返回邮箱"
							class="btn btn-sm btn-white"> 
							<i class="glyphicon glyphicon-envelope"></i>
						</a> --%>
					</div>
					<h2>查看邮件</h2>
					<c:if test="${email.email_file != null && email.email_file!='' }">
					<span><i class="fa fa-paperclip"></i> 1 个附件 - </span>
					</c:if>
					<div class="mail-tools tooltip-demo m-t-md">
						<h3>
							<span class="font-noraml">主题： </span>${email.email_theme }
						</h3>
						<h5>
							<span class="pull-right font-noraml"> <fmt:formatDate
									type="both" dateStyle="full" value="${email.email_datetime}" />
							</span> <span class="font-noraml">发件人： </span>${email.email_send }
						</h5>
					</div>
				</div>
				<div class="mail-box">
					<div class="mail-body">
						<h4><b>你好：</b></h4>
						<p>
						<textarea readonly="readonly" rows="10" class="form-control">${email.email_content }</textarea>
						</p>
						<p>
						<p class="text-right"><b>${user.user_name }</b></p>
					</div>
					<c:if test="${email.email_file != null && email.email_file!='' }">
						<div class="mail-attachment">
							<p>
								<span><i class="fa fa-paperclip"></i> 1 个附件 - </span> 
								<i class="glyphicon glyphicon-save"></i><a
									href="${ctx }/index/filedownload.do?myfile=${email.email_file}&filename=${email.email_namefile }">下载附件</a>
							</p>
							<div class="attachment">
								<div class="file-box">
									<div class="file">
										<a
											href="${ctx }/index/filedownload.do?myfile=${email.email_file}&filename=${email.email_namefile }">
											<span class="corner"></span>
											<div class="icon">
												<i class="fa fa-file"></i>
											</div>
											<div class="file-name">${email.email_namefile }</div>
										</a>
									</div>
								</div>
							</div>
							<div class="clearfix"></div>
						</div>
					</c:if>
					<div class="mail-body text-right tooltip-demo">
						<%-- <a href="${ctx }/email/replyemail.do?email=${email.email_send }"
							class="btn btn-sm btn-white" data-placement="top"
							data-toggle="tooltip" data-original-title="回复"> <i
							class="fa fa-reply"></i> 回复
						</a> --%>
						<a onclick="reply('${email.email_send }')"
							class="btn btn-sm btn-white" data-placement="top"
							data-toggle="tooltip" data-original-title="回复"> <i
							class="fa fa-reply"></i> 回复
						</a>
						<button onclick="deleteemail(${email.email_id })"
							data-placement="top" data-toggle="tooltip"
							title="删除邮件" class="btn btn-sm btn-white">
							<i class="fa fa-trash-o"></i>
						</button>
						<%-- <a href="${ctx }/email/emailbox.do" data-placement="top"
							data-toggle="tooltip" type="button" data-original-title="返回邮箱"
							class="btn btn-sm btn-white"> <i
							class="glyphicon glyphicon-envelope"></i> 返回邮箱
						</a> --%>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
	<script src="${ctx }/js/jquery.min.js?v=2.1.4"></script>
	<script src="${ctx }/js/layer-v3.1.1/layer/layer.js"></script>
	<script src="${ctx }/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${ctx }/js/content.js?v=1.0.0"></script>
	<script src="${ctx }/js/plugins/iCheck/icheck.min.js"></script>
	<script src="${ctx }/js/sweetalert.min.js"></script>
	<script type="text/javascript">
	
	function reply(send){
		var reply=layer.open({
			type:2,
			title:"回复邮件",
			maxmin:true,
			content:"${ctx }/email/replyemail.do?email="+send
		});
		layer.full(reply);
	}
	
    function deleteemail(email_id){
    	swal({
            title: "确定要删除这个邮件吗？",
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
                    window.location.href="${ctx}/email/emailbox.do";
                    },1000);
                });
        });
    }
    </script>
</body>
</html>
