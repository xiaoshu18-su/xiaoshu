<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>合同管理系统 - 修改头像</title>
<link href="${ctx }/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="${ctx }/css/font-awesome.css?v=4.4.0" rel="stylesheet">
<link href="${ctx }/css/plugins/iCheck/custom.css" rel="stylesheet">
<link href="${ctx }/css/animate.css" rel="stylesheet">
<link href="${ctx }/css/style.css?v=4.1.0" rel="stylesheet">
<link href="${ctx }/css/sweetalert.css" rel="stylesheet">
</head>
<body class="gray-bg">
	<div class="container" style="margin-top: 30px;">
		<div class="row">
			<div class="col-sm-12">
					<form id="form_update_img" method="post" enctype="multipart/form-data">
						<div class="form-group has-success">
							<label for="user_image" class="font-noraml">头像选择</label> 
							<input
								type="file" id="user_image" class="form-control"
								name="user_image">
						</div>
						<div class="form-group has-success" >
							<button type="button" class="btn btn-primary" id="submit_btn" style="width: 100%;">Save
								change</button>
						</div>
					</form>
			</div>
		</div>
	</div>
	<script src="${ctx }/js/jquery.min.js?v=2.1.4"></script>
	<script src="${ctx }/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${ctx }/js/content.js?v=1.0.0"></script>
	<script src="${ctx }/js/plugins/iCheck/icheck.min.js"></script>
	<script src="${ctx }/js/sweetalert.min.js"></script>
	<script type="text/javascript">
		$("#submit_btn").click(function(){
			var filepath=$("#user_image").val();
			if(filepath == ''){
				swal({
						title:"上传失败",
						text:"请先选择文件",
						type:"error"
				 });
				return false;
			}
			var extStart=filepath.lastIndexOf(".");
			var ext=filepath.substring(extStart,filepath.length).toUpperCase();
			if(ext!=".BMP"&&ext!=".PNG"&&ext!=".GIF"&&ext!=".JPG"&&ext!=".JPEG"){
					swal({
						title:"上传失败",
						text:"图片限于png,gif,jpeg,jpg,bmp格式",
						type:"error"
				    });
					return false;
			}
			var formData = new FormData($( "#form_update_img" )[0]);  
		     $.ajax({  
		          url: '${ctx}/user/updateimg.do' ,  
		          type: 'POST',  
		          data: formData,  
		          async: false,  
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
		 						//父页面刷新  parent.location.reload()
		 						parent.location.reload();
			 				},1000);
		 				} 
		          }  
		     }); 
		});
	</script>
</body>
</html>