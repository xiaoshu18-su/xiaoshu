<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>合同管理系统 - 用户登录信息记录</title>
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
	                     <h5>用户登录信息记录&nbsp;&nbsp;&nbsp;<small>柱状图以及数据表</small></h5>
	                     <div class="ibox-tools">
	                     <a  href="javascript:window.location.reload();"
	                     class="collapse-link" data-toggle="tooltip" data-placement="left" title="刷新列表">
	                     <i class="fa fa-refresh"></i></a>
	                         <a class="collapse-link">
	                             <i class="fa fa-chevron-up"></i>
	                         </a>
	                     </div>
	                </div>
	                <div class="ibox-content">
						<a onclick="findmonth(1)"> <span
							class="label label-success pull-right">上个月</span>
						</a> <a onclick="findmonth(0)"> <span
							class="label label-success pull-right">本月</span>
						</a> <a onclick="findday(30)"> <span
							class="label label-success pull-right">近30天</span>
						</a> <a onclick="findday(7)"> <span
							class="label label-success pull-right">近7天</span>
						</a>
						 <div id="charts" style="height: 400px"></div>
				     </div>
				    <div class="ibox-content">
					<table
						class="table table-striped table-bordered table-hover dataTables-example"
						id="editable">
						<thead>
							<tr>
							    <th>记录编号</th>
							    <th>登陆时间</th>
							    <th>用户编号</th>
								<th>用户姓名</th>
								<th>用户类型</th>
								<th>所在部门</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list }" var="count">
								<tr class="gradeX">
									<td>${count.count_id }</td>
									<td>${count.count_date }</td>
									<td>${count.user.user_id }</td>
									<td>${count.user.user_name }</td>
									<td>${count.user.usertype.usertype_name }</td>
									<td>${count.user.department.department_name }</td>
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
	<script src="${ctx }/js/echarts-all.js"></script>
	<script src="${ctx }/js/plugins/jeditable/jquery.jeditable.js"></script>
	<script src="${ctx }/js/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="${ctx }/js/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="${ctx }/js/content.js?v=1.0.0"></script>
	<script>
    $(document).ready(function () {
        $('.dataTables-example').dataTable({
   		     "order": [[1,'desc']],
   		     "language": {
	           "emptyTable": "还没有登录信息"
	         }
		 });
    });
    var myChart = echarts.init(document.getElementById('charts'));
	var option = {
		tooltip : {
			show : true
		},
		legend : {
			data : [ '访问数量' ]
		},
		xAxis : [ {
			type : 'category',
			data : []
		} ],
		yAxis : [ {
			type : 'value'
		} ],
		toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {
	                show: true, 
	                type: ['pie', 'funnel'],
	                option: {
	                    funnel: {
	                        x: '25%',
	                        width: '50%',
	                        funnelAlign: 'left',
	                        max: 1548
	                    }
	                }
	            },
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
		series : [ {
			"name" : "访问数量",
			"type" : "bar",
			"data" : []
		} ]
	};

	// 为echarts对象加载数据 
	myChart.setOption(option);
	
	//查看天的
    function findday(day){
		var date=[];
		var num=[];
		myChart.clear();
    	$.post(
    	   "${ctx}/count/byday.do",
    	   {day:day},
    	   function(data){
    		   for(var key in data){
                   date.push(data[key].date);
                   num.push(data[key].number);
               }
               myChart.hideLoading();    //隐藏加载动画
               myChart.setOption({
            	title : {
            	        text: "近"+day+"天用户登录信息统计图"
            	},
       			tooltip : {
    				show : true
    			},
    			legend : {
    				data : [ '访问数量' ]
    			},
    			xAxis : [ {
    				type : 'category',
    				data : date    //设置天数
    			} ],
    			yAxis : [ {
    				type : 'value'
    			} ],
    		    toolbox: {
    		        show : true,
    		        feature : {
    		            mark : {show: true},
    		            dataView : {show: true, readOnly: false},
    		            magicType : {show: true, type: ['line', 'bar']},
    		            restore : {show: true},
    		            saveAsImage : {show: true}
    		        }
    		    },
    		    calculable : true,
    			series : [ {
    				"name" : "访问数量",
    				"type" : "bar",
    				"data" : num     //设置数量
    			} ]
               });
    	   }
    	);
    }
	
	//按月查的
	function findmonth(month){
		var textmonth;
		if(month==0){
			textmonth="本";
		}
		if(month==1){
			textmonth="上个";
		}
		var date=[];
		var num=[];
		myChart.clear();
    	$.post(
    	   "${ctx}/count/bymonth.do",
    	   {month:month},
    	   function(data){
    		   for(var key in data){
                   date.push(data[key].date);
                   num.push(data[key].number);
               }
               myChart.hideLoading();    //隐藏加载动画
               myChart.setOption({
            	title : {
           	        text: textmonth+"月用户登录信息统计图"
           	    },   
       			tooltip : {
    				show : true
    			},
    			legend : {
    				data : [ '访问数量' ]
    			},
    			xAxis : [ {
    				type : 'category',
    				data : date    //设置天数
    			} ],
    			yAxis : [ {
    				type : 'value'
    			} ],
    		    toolbox: {
    		        show : true,
    		        feature : {
    		            mark : {show: true},
    		            dataView : {show: true, readOnly: false},
    		            magicType : {show: true, type: ['line', 'bar']},
    		            restore : {show: true},
    		            saveAsImage : {show: true}
    		        }
    		    },
    		    calculable : true,
    			series : [ {
    				"name" : "访问数量",
    				"type" : "bar",
    				"data" : num     //设置数量
    			} ]
               });
    	   }
    	);
	}
    </script>
</body>
</html>