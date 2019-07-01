<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>合同管理系统 - 列表列表</title>
<link rel="shortcut icon" href="${ctx }/favicon.ico">
<link href="${ctx }/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="${ctx }/css/font-awesome.css?v=4.4.0" rel="stylesheet">
<link href="${ctx }/css/animate.css" rel="stylesheet">
<link href="${ctx }/css/style.css?v=4.1.0" rel="stylesheet">
<link href="${ctx }/css/sweetalert.css" rel="stylesheet">
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight" id="temptop">
		<div class="row">
		    <div class="col-sm-2">
		        <a href="#templateform" class="btn btn-primary btn-xs">添加模板</a>
		    </div>
			<div class="col-sm-9">
				<table class="table table-striped">
				  <thead>
				   <tr>
				      <th>模板编号</th>
				      <th>模板文件</th>
				      <th>操作</th>
				   </tr>
				  </thead>
				  <tbody id="template"></tbody>
				</table>
			</div>
			<div class="col-sm-1">
			    <a href="javascript:window.location.reload();"
                    class="collapse-link" data-toggle="tooltip" data-placement="left" title="刷新">
                <i class="fa fa-refresh"></i></a>
			</div>
		</div>
		<div class="row">
		    <div class="col-sm-2"></div>
		    <div class="col-sm-10">
		        <form class="form-inline" id="templateform" method="post" enctype="multipart/form-data" role="form">
				  <div class="form-group">
				    <label for="template_file">选择文件</label>
				    <input type="file" id="template_file" name="template_file">
				  </div>
				  <button type="submit" class="btn btn-default btn-xs">上传模板</button>
				</form>
		    </div>
		    <div class="col-sm-1">
		       <a href="#temptop" class="btn btn-info btn-xs">回到顶部</a>
		    </div>
		</div>
	</div>
	<script src="${ctx }/js/jquery.min.js?v=2.1.4"></script>
	<script src="${ctx }/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${ctx }/js/content.js?v=1.0.0"></script>
    <script src="${ctx }/js/plugins/iCheck/icheck.min.js"></script>
	<script src="${ctx }/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="${ctx }/js/plugins/validate/messages_zh.min.js"></script>
	<script src="${ctx }/js/sweetalert.min.js"></script>
	<script>
    //调用方法
    querylist();
	
	//查出列表方法
    function querylist(){
    	$.post(
    	   "${ctx}/template/list.do",
    	   {},
    	   function(data){
    		  var tr="";
    		  $("#template").empty();
    		  if(data!=null){
    			  $.each(data,function(i,t){
        			  tr+="<tr><td>"+t.template_id+"</td><td>"+
        			  "<a href='${ctx }/index/filedownload.do?myfile="
        			  +t.template_file+"&filename="+t.template_filename+"'>"+
        			  t.template_filename+"</a></td><td><button onclick='fdelete("+t.template_id+")'"+ 
        			  "type='button' class='btn btn-danger btn-xs'>删除</button></td></tr>";
        		  });
    		  }
    		  $("#template").html(tr);
    	   }
    	);
    }
	
  //错误提示信息图标、
    var icon = "<i class='fa fa-times-circle'></i>";
	//使用jquery.validate.js进行校验
	$("#templateform").validate({
		/*  自定义规则 */
		rules:{
			template_file:{
				required:true,
			}
		},
		messages:{
			template_file:{
				required:icon+"请选择文件",
			}
		},
		//使用formdata和ajax提交表单，包括文件上传
		submitHandler:function(form){
			var file=$("#template_file").val();
        	var extStart=file.lastIndexOf(".");
			var ext=file.substring(extStart,file.length).toUpperCase();
			if(ext!=".DOC"&&ext!=".DOCX"&&ext!=".WPS"&&ext!=".PDF"){
					swal({
						title:"添加失败",
						text:"请上传doc、docx、wps、pdf等文件",
						type:"error"
				    });
					return false;
			} 
		     var formData = new FormData($( "#templateform" )[0]); 
		     $.ajax({  
		          url: '${ctx}/template/add.do',  
		          type: 'POST',  
		          data: formData,  
		          cache: false,  
		          contentType: false,  
		          processData: false,  
		          success: function (data) {  
		        	  if(data == "no"){
		 					swal({
		 						title:"添加失败",
		 						text:"请检查信息",
		 						type:"error"
		 					});
		 				}
		        	  querylist();
		          }, 
		          error:function(){
		        	  swal({
	 						title:"添加失败",
	 						text:"服务器出错",
	 						type:"error"
	 					});
		          }
		     }); 
		}
	});
	function fdelete(id){
		$.post(
		    "${ctx}/template/delete.do",
		    {template_id:id},
		    function(data){
		    	if(data=="no"){
		    		swal({
 						title:"删除失败",
 						text:"",
 						type:"error"
 					});
		    	}
		    	else{
		    		querylist();
		    	}
		    }
		);
	}
    </script>
</body>
</html>
