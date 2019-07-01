<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>企业合同管理系统-合同明细</title>
<link rel="shortcut icon" href="${ctx }/favicon.ico">
<link href="${ctx }/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="${ctx }/css/font-awesome.css?v=4.4.0" rel="stylesheet">
<link href="${ctx }/css/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">
<link href="${ctx }/css/animate.css" rel="stylesheet">
<link href="${ctx }/css/style.css?v=4.1.0" rel="stylesheet">
<link href="${ctx }/css/sweetalert.css" rel="stylesheet">
<link href="${ctx }/css/htmleaf-demo.css" rel="stylesheet">
<link href="${ctx }/css/progress.css" rel="stylesheet">
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>合同明细</h5>
						<div class="ibox-tools">
							<a href="javascript:window.location.reload();"
								class="collapse-link" data-toggle="tooltip"
								data-placement="left" title="刷新列表"> <i class="fa fa-refresh"></i></a>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<table class="table table-bordered">
								<tr>
									<td colspan="2">合同编号:</td>
									<td colspan="2">${c.contract_id }</td>
									<td colspan="2">合同名称:</td>
									<td colspan="2">${c.contract_name }</td>
								</tr>
								<tr>
									<td>甲方</td>
									<td>${c.contract_parta }</td>
									<td>乙方</td>
									<td>${c.contract_partb }</td>
									<td>合同文件</td>
									<td colspan="3"><a
										href="${ctx }/index/filedownload.do?myfile=${c.contract_file }&filename=${c.contract_filename }">
											${c.contract_filename } </a></td>
								</tr>
								<tr>
								    <td>签订时间</td>
									<td>${c.contract_partbsigningtime }</td>
									<td>乙方代表人</td>
									<td>${c.contract_partbrepresentative }</td>
									<td>乙方地址</td>
									<td>${c.contract_partbaddress }</td>
									<td>乙方电话</td>
									<td>${c.contract_partbtelephone }</td>
								</tr>
								<tr>
									<td>开始时间:</td>
									<td>${c.contract_startime }</td>
									<td>结束时间:</td>
									<td>${c.contract_endtime }</td>
									<td>预收款时间:</td>
									<td>${c.contract_advancetime }</td>
									<td>合同修改状态:</td>
									<td>${c.modify.modify_name }</td>
								</tr>
								<tr>
									<td>合同状态:</td>
									<td colspan="2">
									<b style="color: red;">
									${c.state.state_name }&nbsp; 
									<c:if test="${c.state.state_name=='执行中' }">
									${c.status.status_name }
									</c:if>
									</b>
									</td>
									<td>收款进度:</td>
									<td colspan="2">
									<c:if test="${c.status.status_name=='收款跟踪中' or c.status.status_name=='已完全收款'}">
										<h6 class="progress-title">收款进度 - ${c.progress }%</h6>
										<div class="progress">
											<span
												class="progress-bar progress-bar-primary progress-bar-striped active"
												style="width: ${c.progress }%;"></span>
										</div>
									</c:if>
									<c:if test="${c.status.status_name!='收款跟踪中' and c.status.status_name!='已完全收款'}">
										<h6 class="progress-title">未开始收款</h6>
									</c:if>
									</td>
									<td>最近修改:</td>
									<td>${c.contract_modify_time }</td>
								</tr>
								<tr>
									<td>金额:</td>
									<td colspan="2">${c.contract_money }</td>
									<td>累计收款:</td>
									<td colspan="2">${c.contract_accumulatedreceipts }</td>
									<td>欠款:</td>
									<td colspan="4">${c.contract_accumulatedarrears }</td>
								</tr>
								<tr>
									<td>备注:</td>
									<td colspan="2">
									<textarea readonly="readonly" rows="5" cols="30">${c.contract_remarks }</textarea>
									</td>
									<td>跟踪权限:</td>
									<td>
									<c:if test="${c.contract_trackstatus==0 }">
									我
									</c:if>
									<c:if test="${c.contract_trackstatus==1 }">
									所有人
									</c:if>
									</td>
									<td>合同操作:</td>
									<td colspan="4">
									<c:if test="${c.state.state_name=='执行中' }">
									<a onclick="contractrack('${c.contract_id }')" title="收款跟踪" 
									class="btn btn-success btn-sm"
								     data-toggle="modal" data-target=".bs-example-modal-lg">
								     <i class="glyphicon glyphicon-search"></i>
								    </a> 
								    <a onclick="updatecontract('${c.contract_id }')"title="修改" 
								    class="btn btn-warning btn-sm">
								    <i class="glyphicon glyphicon-pencil"></i>
								    </a>
								    <a onclick="suspension('${c.contract_id }')" 
								    title="终止" class="btn btn-danger btn-sm">
								    <i class="glyphicon glyphicon-off"></i>
								    </a>
									</c:if>
									<c:if test="${c.state.state_name=='已终止' }">
									<a onclick="recovery('${c.contract_id }')" title="恢复"
									class="btn btn-primary btn-sm">
									<i class="glyphicon glyphicon-wrench"></i>
									</a>
									<a onclick="contractdelete('${c.contract_id }')" title="删除"
									class="btn btn-danger btn-sm">
									<i class="glyphicon glyphicon-trash"></i>
									</a>
									</c:if>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 收款跟踪模态框 -->
	<!-- Modal -->
	<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
		aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">合同收款跟踪记录</h4>
				</div>
				<div class="modal-body">
				    <table class="table table-condensed table-striped">
					   <thead>
					      <tr>
					        <th>序号</th>
					        <th>跟踪人</th>
					        <th>合同编号</th>
					        <th>收款日期</th>
					        <th>实收金额</th>
					        <th>备注</th>
					      </tr>
					   </thead>
					   <tbody id="trackbody"></tbody>
					</table>
				</div>
				<div class="modal-footer">
				   合同金额:<label id="contract_money"></label>&nbsp;&nbsp;
				    累计收款:<label id="contract_accumulatedreceipts"></label>&nbsp;&nbsp;
				    欠款:<label id="contract_accumulatedarrears"></label>&nbsp;&nbsp;&nbsp;&nbsp;
					<button id="addreceiptsbtn" type="button" data-toggle="modal" 
					data-target="#myModal" class="btn btn-primary">添加收款</button>
				</div>
			</div>
		</div>
	</div>

    <!-- 添加收款 -->
    <!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加收款</h4>
				</div>
				<form role="form" class="form-horizontal" method="post" id="add_track_form">
				<div class="modal-body">
					<!-- 隐藏域 合同编号 -->
					<input type="hidden" id="track_contractid" name="track_contractid">
					<div class="form-group">
						<label class="col-sm-2 control-label">收款日期:</label>
						<div class="col-sm-10">
							<input id="track_collectiondate" type="text" class="form-control"
								name="track_collectiondate">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">收款金额:</label>
						<div class="col-sm-10">
							<input id="track_amountreceived" type="text" class="form-control"
								name="track_amountreceived">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">备注:</label>
						<div class="col-sm-10">
							<input id="tract_remarks" type="text" class="form-control"
								name="tract_remarks">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					可收款:<label id="receivable"></label>&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="submit" class="btn btn-primary">收款</button>
				</div>
				</form>
			</div>
		</div>
	</div>
	<script src="${ctx }/js/jquery.min.js?v=2.1.4"></script>
	<script src="${ctx }/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${ctx }/js/plugins/jeditable/jquery.jeditable.js"></script>
	<script src="${ctx }/js/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="${ctx }/js/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="${ctx }/js/plugins/layer/laydate/laydate.js"></script>
	<script src="${ctx }/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="${ctx }/js/plugins/validate/messages_zh.min.js"></script>
	<script src="${ctx }/js/content.js?v=1.0.0"></script>
	<script src="${ctx }/js/sweetalert.min.js"></script>
	<script>
	//日期范围限制
	laydate({
		elem : '#track_collectiondate',
		min : '1900-08-18',
		max : laydate.now(),
	});
	function contractrack(contract_id){
		$.post("${ctx}/contract/track.do",{contract_id:contract_id},function(data){
			$("#addreceiptsbtn").attr("onclick","addreceipts('"+contract_id+"')");
			$("#contract_money").text(data.contract.contract_money);
			$("#contract_accumulatedreceipts").text(data.contract.contract_accumulatedreceipts);
			$("#contract_accumulatedarrears").text(data.contract.contract_accumulatedarrears);
			if($("#contract_accumulatedarrears").text()=="0"){
				$("#addreceiptsbtn").attr('disabled',true);
			}
			if(data.tracks!=null){
				var tr="";
				$.each(data.tracks,function(i,track){
					if(track.track_id!=null){
						tr+="<tr><td>"+track.track_id+"</td><td>"+track.user.user_name+"</td>"+
						"<td>"+track.track_contractid+"</td><td>"+track.track_collectiondate+"</td>"+
						"<td>"+track.track_amountreceived+"</td><td>"+track.tract_remarks+"</td></tr>";
					}
				});
				$("#trackbody").html(tr);
			}
		});
	}
	function addreceipts(contract_id){
		$("#track_contractid").val(contract_id);
		$("#receivable").text($("#contract_accumulatedarrears").text());
	}
	//错误提示信息图标、
	var icon = "<i class='fa fa-times-circle'></i>";
	//使用jquery.validate.js进行校验
	$("#add_track_form").validate({
			/*  自定义规则 */
			rules : {
				track_collectiondate : {
					required : true,
				},
				track_amountreceived : {
					required : true,
					number : true,
					min : 0.0000,
					maxlength : 20,
					remote: {
					    url: "${ctx}/track/check.do",     //后台处理程序
					    type: "post",               //数据发送方式
					    dataType: "json",           //接受数据格式   
					    data: {                     //要传递的数据
					    	track_contractid: function() {
					            return $("#track_contractid").val();
					        },
					        track_amountreceived: function() {
					            return $("#track_amountreceived").val();
					        }
					    }
					}
				},
			},
			messages : {
				track_collectiondate : {
					required : icon + "请选择日期",
				},
				track_amountreceived : {
					required : icon + "请输入金额",
					number : icon + "请输入合法的数字",
					min : icon + "金额不要小于0元",
					maxlength : icon + "金额不要超出20位",
					remote: icon + "金额超出范围",
				},
			},
			//使用formdata和ajax提交表单，包括文件上传
			submitHandler : function(form) {
				var formData = new FormData($("#add_track_form")[0]);
				$.ajax({
					url : '${ctx}/track/add.do',
					type : 'POST',
					data : formData,
					cache : false,
					contentType : false,
					processData : false,
					success : function(data) {
						if(data=="yes"){
							window.location.reload();
						}
						else {
							swal("收款失败","","error");
						}
					}
				});
			}
	});
	function updatecontract(contract_id){
		window.location.href="${ctx }/contract/toupdate.do?contract_id="+contract_id;
	}
	function suspension(contract_id){
		swal({
            title: "确认要终止该合同吗？",
            text: "",
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false,
            confirmButtonText: "立即终止！",
            confirmButtonColor: "#ec6c62",
            cancelButtonText: "三思"
        }, function (isConfirm) {
            if (!isConfirm) return;
            $.post(
                "${ctx}/contract/suspension.do",
                {contract_id:contract_id},
                function (data) {
                    if (data=="yes") {
                    	swal({
							title:"已终止合同!",
							text:"",
							type:"success"
						   });
                    }
                    if(data=="no"){
                    	swal({
							title:"终止失败!",
							text:"",
							type:"error"
						   });
                    }
                    setTimeout(function(){ 
                    window.location.reload();
                    },1000);
                });
        });
	}
	function recovery(contract_id){
		$.post("${ctx}/contract/recovery.do", 
				{contract_id : contract_id}, 
		      function(data) {
				if (data == "yes") {
					swal({
						title:"已恢复合同!",
						text:"",
						type:"success"
					   });
				}
				if (data == "no") {
					swal({
						title:"恢复失败!",
						text:"",
						type:"error"
					   });
				}
				setTimeout(function(){ 
                    window.location.reload();
                    },1000);
		});
	}
	function complete(contract_id){
		swal({
            title: "确认完成该合同吗？",
            text: "",
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false,
            confirmButtonText: "立即完成！",
            confirmButtonColor: "#ec6c62",
            cancelButtonText: "再想想"
        }, function (isConfirm) {
            if (!isConfirm) return;
            $.post(
                "${ctx}/contract/complete.do",
                {contract_id:contract_id},
                function (data) {
                    if (data=="yes") {
                    	swal({
							title:"已完成",
							text:"",
							type:"success"
						   });
                    }
                    if(data=="no"){
                    	swal({
							title:"失败了",
							text:"",
							type:"error"
						   });
                    }
                    setTimeout(function(){ 
                    window.location.reload();
                    },1000);
                });
        });
	}
	function place(contract_id){
		swal({
            title: "确认归档吗？",
            text: "",
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false,
            confirmButtonText: "归档！",
            confirmButtonColor: "#ec6c62",
            cancelButtonText: "不了"
        }, function (isConfirm) {
            if (!isConfirm) return;
            $.post(
                "${ctx}/contract/place.do",
                {contract_id:contract_id},
                function (data) {
                    if (data=="yes") {
                    	swal({
							title:"已归档",
							text:"",
							type:"success"
						   });
                    }
                    if(data=="no"){
                    	swal({
							title:"归档失败",
							text:"",
							type:"error"
						   });
                    }
                    setTimeout(function(){ 
                    window.location.reload();
                    },1000);
                });
        });
	}
	function contractdelete(contract_id){
    	swal({
            title: "确定要删除该合同吗？",
            text: "",
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false,
            confirmButtonText: "删除！",
            confirmButtonColor: "#ec6c62",
            cancelButtonText: "三思"
        }, function (isConfirm) {
            if (!isConfirm) return;
            $.post(
                "${ctx}/contract/delete.do",
                {contract_id:contract_id},
                function (data) {
                    if (data=="yes") { //后端删除成功
                    	console.log(data);
                    	swal({
							title:"删除成功",
							text:"",
							type:"success"
						   });
                    }
                    if(data=="no"){
                    	console.log(data);
                    	swal({
							title:"删除失败",
							text:"",
							type:"error"
						   });  //后端删除失败
                    }
                    setTimeout(function(){ 
                    window.location.href="${ctx }/contract/list.do";
                    },1000);
                });
        });
	}
	</script>
</body>
</html>