<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>合同管理系统 - 合同列表</title>
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
						<h5>
							合同列表&nbsp;<small>分类，查找</small>
						</h5>
						<div class="ibox-tools">
							<a href="${ctx }/contract/list.do"
								class="collapse-link" data-toggle="tooltip"
								data-placement="left" title="刷新"> <i class="fa fa-refresh"></i></a>
						</div>
					</div>
					<div class="ibox-content">
						<form class="form-inline" method="post">
							<div class="form-group">
								<label for="exampleInputName2">搜索信息：</label> 
								<input type="text" name="partbinformation"
									class="form-control" id="partbinformation"
									placeholder="编号 名称 乙方信息" style="width: 160px;">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail2">执行状态：</label> 
								<select id="contract_state" name="contract_state" class="form-control">
								<option value="0">请选择</option>
									<c:forEach items="${states }" var="state">
									   <option value="${state.state_id }">${state.state_name }</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label for="exampleInputEmail2">收款状态：</label> 
								<select id="contract_status" name="contract_status" class="form-control">
								<option value="0">请选择</option>
									<c:forEach items="${statuses }" var="status">
									   <option value="${status.status_id }">${status.status_name }</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label for="exampleInputEmail2">签订时间：</label> 
								<input type="text" name="startsigningtime"
									class="form-control" id="startsigningtime"
									placeholder="开始时间" style="width: 160px;">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail2">至</label> 
								<input type="text" name="endsigningtime"
									class="form-control" id="endsigningtime"
									placeholder="截止时间" style="width: 160px;">
							</div>
							<button type="submit" class="btn btn-primary btn-sm">查询</button>
						</form>
						<table
							class="table table-striped table-bordered table-hover dataTables-example"
							id="editable">
							<thead>
								<tr>
									<th>合同编号</th>
									<th>合同名称</th>
									<th>乙方</th>
									<th>乙方代表人</th>
									<th>乙方电话</th>
									<th>签订时间</th>
									<th>预收款时间</th>
									<th style="color: purple;">状态</th>
									<th>收款跟踪</th>
									<th>跟踪权限</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${contracts }" var="c">
									<tr>
										<td>${c.contract_id }</td>
										<td>${c.contract_name }</td>
										<td>${c.contract_partb }</td>
										<td>${c.contract_partbrepresentative }</td>
										<td>${c.contract_partbtelephone }</td>
										<td>${c.contract_partbsigningtime }</td>
										<td>${c.contract_advancetime }</td>
										<td><b style='color: purple;'> ${c.state.state_name }&nbsp;
										<c:if test="${c.state.state_name=='执行中' }">
										${c.status.status_name }
										</c:if>
										</b>
										</td>
										<td>
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
										<td>
										<c:if test="${c.contract_trackstatus==1 }">
										所有人
										</c:if>
										<c:if test="${c.contract_trackstatus==0 }">
										${c.user.user_name }
										</c:if>
										</td>
										<td>
										<a onclick="track('${c.contract_id }')">跟踪</a>
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
	<script src="${ctx }/js/plugins/layer/laydate/laydate.js"></script>
	<script src="${ctx }/js/content.js?v=1.0.0"></script>
	<script src="${ctx }/js/sweetalert.min.js"></script>
	<script type="text/javascript">
		laydate({
			elem : '#startsigningtime',
			min : '1900-08-18',
			max : '2200-08-18',
		});
		laydate({
			elem : '#endsigningtime',
			min : '1900-08-18',
			max : '2200-08-18',
		});
		$('.dataTables-example').dataTable({
			"order" : [ [ 0, 'desc' ] ],
			"language" : {
				"emptyTable" : "没有合同"
			},
		    bFilter: false,    //去掉搜索框
		    bLengthChange: false   //去掉每页显示多少条数据方法 
		/* "lengthMenu": [[5,10,25,50], [5,10,25,50]] */
		});
		//数据回显
		$("#partbinformation").val("${partbinformation}");
		$("#contract_state").find("option[value='${contract_state}']").attr("selected",true);
		$("#contract_status").find("option[value='${contract_status}']").attr("selected",true);
		$("#startsigningtime").val("${startsigningtime}");
		$("#endsigningtime").val("${endsigningtime}");
		if("${datesize}"=="error"){
			swal("开始时间不要大于截止时间","","error");
		}
		//判断权限
        function track(contract_id){
        	$.post("${ctx}/contract/tudgementauthority.do",{contract_id:contract_id},function(data){
        		if(data=="yes"){
        			var contract_detailed=layer.open({
        				type:2,
        				title:"合同跟踪详情",
        				maxmin:true,
        				content:'${ctx }/contract/detail.do?contract_id='+contract_id
        			});
        			layer.full(contract_detailed);
        		}
        		else{
        			swal("没有权限跟踪","","error");
        		}
        	});
        }
	</script>
</body>
</html>
