<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>企业合同管理系统 - 写信</title>
<link href="${ctx }/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="${ctx }/css/font-awesome.css?v=4.4.0" rel="stylesheet">
<link href="${ctx }/css/plugins/iCheck/custom.css" rel="stylesheet">
<link href="${ctx }/css/animate.css" rel="stylesheet">
<link href="${ctx }/css/style.css?v=4.1.0" rel="stylesheet">
<link href="${ctx }/css/sweetalert.css" rel="stylesheet">
<style type="text/css">
#suggest {
	position: abslute;
	background-color: #FFFFFF;
	text-align: left;
	display: none;
}

.suggest_link_over {
	background-color: #E8F2FE;
	padding: 2px 6px 2px 6px;
	cursor: pointer;
}
</style>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
		<div class="row">
			<div class="col-sm-12 animated fadeInRight">
				<div class="mail-box-header">
					<div class="pull-right tooltip-demo">
						<a href="${ctx }/email/emailbox.do" data-placement="top"
							data-toggle="tooltip" type="button" data-original-title="返回邮箱"
							class="btn btn-sm btn-white" id="toemailbox"> <i
							class="glyphicon glyphicon-envelope"></i> 邮箱
						</a>
					</div>
					<h2>写信</h2>
				</div>
				<div class="mail-box">
					<div class="mail-body">
						<form role="form" class="form-horizontal" method="post"
							id="form_mail_compose" enctype="multipart/form-data">
							<div class="form-group">
								<label class="col-sm-2 control-label" for="email_receive">发送到：</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="email_receive"
										name="email_receive" placeholder="邮箱">
									<div id="suggest" style="width: 500px"></div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="email_theme">主题：</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="email_theme"
										name="email_theme" placeholder="主题">
								</div>
							</div>
							<div class="mail-text h-200">
								<div class="summernote">
									<div class="form-group">
										<label class="col-sm-2 control-label" for="email_content">正文：</label>
										<div class="col-sm-10">
											<textarea id="email_content" name="email_content" rows="10"
												class="form-control"></textarea>
										</div>
									</div>
								</div>
								<div class="clearfix"></div>
							</div>
							<div class="mail-body text-right tooltip-demo">
								<div class="mail-body text-left tooltip-demo">
									<label for="email_file"> <a data-toggle="tooltip"
										data-placement="top" title="添加附件"> <i
											class="glyphicon glyphicon-paperclip"></i> 添加附件
									</a>
									</label> <input id="email_file" type="file" name="email_file" />
								</div>
								<button type="submit" class="btn btn-sm btn-primary"
									id="form_submit" data-toggle="tooltip" data-placement="top"
									title="Send">
									<i class="fa fa-reply"></i> 发送
								</button>
								<button type="reset" class="btn btn-white btn-sm"
									id="form_reset" data-toggle="tooltip" data-placement="top"
									title="Discard email">
									<i class="fa fa-times"></i> 放弃
								</button>
							</div>
						</form>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${ctx }/js/jquery.min.js?v=2.1.4"></script>
	<script src="${ctx }/js/jquery-form.js"></script>
	<script src="${ctx }/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${ctx }/js/content.js?v=1.0.0"></script>
	<script src="${ctx }/js/plugins/iCheck/icheck.min.js"></script>
	<script src="${ctx }/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="${ctx }/js/plugins/validate/messages_zh.min.js"></script>
	<script src="${ctx }/js/sweetalert.min.js"></script>
	<script type="text/javascript">
        var replyemail='${replyemail}';
        if(replyemail != null && replyemail != ''){
        	$("#email_receive").val(replyemail);
        }
        var compose='${compose}';
        if(compose == 'compose'){
        	$("#toemailbox").css({"display":"none"});
        }
        //错误提示信息图标、
	    var icon = "<i class='fa fa-times-circle'></i>  ";
		//使用jquery.validate.js进行校验
		$("#form_mail_compose").validate({
			errorPlacement: function(error, element) {
				// Append error within linked label
				$( element )
					.closest( "form" )
						.find( "label[for='" + element.attr( "id" ) + "']" )
							.append( error );
			},
			rules:{
				email_receive:{
					required:true,
					email:true,
					//检查是否有这个邮箱后台需要返回  boolean类型
					remote: {
					    url: "${ctx}/user/check_email_receive.do",     //后台处理程序
					    type: "post",               //数据发送方式
					    dataType: "json",           //接受数据格式   
					}
				},
				email_theme:{
					required:true,
				},
			},
			messages:{
				email_receive:{
					required:icon+"必填信息",
					email:icon+"输入邮箱格式，如@qq.com  @163.com结尾",
					remote:icon+"该邮箱不存在"
				},
				email_theme:{
					required:icon+"必填信息",
				},
			},
			//使用formdata和ajax提交表单，包括文件上传
			submitHandler:function(form){
				var email_file=$("#email_file").val();
	        	var extStart=email_file.lastIndexOf(".");
				var ext=email_file.substring(extStart,email_file.length).toUpperCase();
				if(ext!=".BMP"&&ext!=".PNG"&&ext!=".GIF"&&ext!=".JPG"&&ext!=".JPEG"&&ext!=".DOC"
						&&ext!=".DOCX"&&ext!=".PPT"&&ext!=".PPTX"&&ext!=".XLS"&&ext!=".XLSX"&&ext!=".WPS"
							&&ext!=".PDF"&&ext!=".TXT"&&ext!=".ICO"&&ext!=".XMIND"&&ext!=".RAR"&&ext!=".ZIP"
								&&ext!=".7Z"&&ext!=".ARJ"&&email_file!=""){
						swal({
							title:"发送失败",
							text:"请上传合适的附件",
							type:"error"
					    });
						return false;
				}
				 var formData = new FormData($( "#form_mail_compose" )[0]);  
			     $.ajax({  
			          url: '${ctx}/email/compose.do' ,  
			          type: 'POST',  
			          data: formData,  
			          async: false,  
			          cache: false,  
			          contentType: false,  
			          processData: false,  
			          success: function (data) {  
			        	  if(data == "no"){
			 					swal({
			 						title:"发送失败",
			 						text:"文件不要大于10m",
			 						type:"error"
			 					});
			 				}else{
			 					swal({
			 					title:"发送成功",
			 					text:"",
			 					type:"success"
			 				   });
			 				setTimeout(function(){ 
			 					window.location.reload();
			 					//父页面刷新  parent.location.reload()
		 						//parent.location.reload();
			 				},1000);
			 				} 
			          },  
			     });   
			}
			
		});
		//给用户输入的提示框
		 $("#email_receive").keyup(function(){
			$.post(
			  "${ctx}/email/selectEmailByemail.do",
			  {user_email:$("#email_receive").val()},
			  function(data){
				//显示大层
				$('#suggest').show();
				//清空大层的内容
				$('#suggest').empty();
				if(data != null){
					$.each(data,function(i,user){
						//构建每一条数据，放在小层
						var rowDiv = $("<div class='form-control'>"+user.user_email+"</div>");
						$('#suggest').append($(rowDiv));
						//$(rowDiv).mouseover(function(){}).mouseout(function(){});
						$(rowDiv).hover(function(){
							$(this).addClass("suggest_link_over");
						},function(){
							$(this).removeClass("suggest_link_over");
						});
						$(rowDiv).click(function(){
							$('#email_receive').val($(this).text());
							$('#suggest').hide();
							$('#email_receive').blur();
						});
					});
					
				}
			  }
			);
		}); 
    </script>
</body>
</html>
