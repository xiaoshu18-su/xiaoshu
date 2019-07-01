<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>企业合同管理系统-合同统计图</title>
<link href="${ctx }/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="${ctx }/css/font-awesome.css?v=4.4.0" rel="stylesheet">
<link href="${ctx }/css/animate.css" rel="stylesheet">
<link href="${ctx }/css/style.css?v=4.1.0" rel="stylesheet">
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
			    <div class="ibox float-e-margins">
				    <div class="ibox-title">
	                     <h5>合同信息统计</h5>
	                     <div class="ibox-tools">
	                     <a  href="javascript:window.location.reload();"
	                     class="collapse-link" data-toggle="tooltip" data-placement="left" title="刷新">
	                     <i class="fa fa-refresh"></i></a>
	                         <a class="collapse-link">
	                             <i class="fa fa-chevron-up"></i>
	                         </a>
	                     </div>
	                </div>
		            <div class="ibox-content">
		                <!-- 饼状图 -->
						<div id="cakelikecharts" style="height: 400px"></div>
					</div>
					<div class="ibox-content">
						<!-- 柱状图 -->
						<div id="brokenlinecharts" style="height: 400px"></div>
					</div>
					<div class="ibox-content">
					    <a onclick="lastyear()"> <span
							class="label label-success pull-right">上一年</span>
						</a> <a onclick="thisyear()"> <span
							class="label label-success pull-right">本年</span>
						</a>
						<!-- 柱状图 -->
						<div id="columnarecharts" style="height: 400px"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${ctx }/js/jquery.min.js?v=2.1.4"></script>
	<script src="${ctx }/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${ctx }/js/echarts-all.js"></script>
	<script src="${ctx }/js/content.js?v=1.0.0"></script>
	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts图表
		var cakelikecharts = echarts.init(document.getElementById('cakelikecharts'));
		var brokenlinecharts = echarts.init(document.getElementById('brokenlinecharts'));
		var columnarecharts = echarts.init(document.getElementById('columnarecharts'));
		//调用饼状图方法
		cakelikefunction();
		//调用折线图方法
		brokenlinefunction();
		
		//饼状图方法
		function cakelikefunction(){
			var cakelikename=[];
			var cakelikecount=[];
			cakelikecharts.clear();
			$.post(
			    "${ctx}/contract/echartallcontract.do",
			    {},
			    function(data){
			    	for(var key in data){
			    		cakelikename.push(data[key].cakelikename);
			    		cakelikecount.push(
			    			{value:data[key].cakelikecount,name:data[key].cakelikename}	
			    		);
			    	}
			    	cakelikecharts.hideLoading();    //隐藏加载动画
			    	cakelikecharts.setOption({
			    		title : {
					        text: '合同收款状态占比',
					        x:'center'
					    },
					    tooltip : {
					        trigger: 'item',
					        formatter: "{a} <br/>{b} : {c} ({d}%)"
					    },
					    legend: {
					        orient : 'vertical',
					        x : 'left',
					        data:cakelikename
					    },
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
					    series : [
					        {
					            name:'合同占比',
					            type:'pie',
					            radius : '55%',
					            center: ['50%', '60%'],
					            data:cakelikecount
					        }
					    ]
			    	});
			    }
			);
		}
		
		//柱状图
		function brokenlinefunction(){
			var brokenlinename=[];
			var moneycount=[];
			var receiptscount=[];
			var arrearscount=[];
			brokenlinecharts.clear();
			$.post(
			    "${ctx}/contract/echartsbyday.do",
			    {},
			    function(data){
			    	for(var key in data){
			    		brokenlinename.push(data[key].contractname);
			    		moneycount.push(data[key].moneycount);
			    		receiptscount.push(data[key].receiptscount);
			    		arrearscount.push(data[key].arrearscount);
			    	}
			    	brokenlinecharts.hideLoading();    //隐藏加载动画
			    	brokenlinecharts.setOption(
	    			{
	    			    title : {
	    			        text: '近7份合同收款金额情况图',
	    			    },
	    			    tooltip : {
	    			        trigger: 'axis'
	    			    },
	    			    legend: {
	    			        data:['金额','累计收款','欠款']
	    			    },
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
	    			    xAxis : [
	    			        {
	    			            type : 'category',
	    			            data : brokenlinename
	    			        }
	    			    ],
	    			    yAxis : [
	    			        {
	    			            type : 'value'
	    			        }
	    			    ],
	    			    series : [
	    			        {
	    			            name:'金额',
	    			            type:'bar',
	    			            data:moneycount,
	    			        },
	    			        {
	    			            name:'累计收款',
	    			            type:'bar',
	    			            data:receiptscount,
	    			        },
	    			        {
	    			            name:'欠款',
	    			            type:'bar',
	    			            data:arrearscount,
	    			        }
	    			    ]
	    			}
			    	);
			    }
			);
		}
		
		//柱状图初始化
		columnarecharts.setOption(
				{
					tooltip : {
						show : true
					},
					legend : {
						data : [ '合同数量' ]
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
				            magicType : {show: true, type: ['line', 'bar']},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    calculable : true,
					series : [ {
						"name" : "合同数量",
						"type" : "bar",
						"data" : []
					} ]
				}	
		);
		
		function thisyear(){
			var columnarmonth=[];
			var columnarcount=[];
			columnarecharts.clear();
			$.post(
			    "${ctx}/contract/echarthisyear.do",
			    {},
			    function(data){
			    	for(var key in data){
			    		columnarmonth.push(data[key].columnarmonth);
			    		columnarcount.push(data[key].columnarcount);
			    	}
			    	columnarecharts.hideLoading();    //隐藏加载动画
					columnarecharts.setOption(
							{
								title : {
			            	        text: "本年每月签订的合同数量"
			            	    },
								tooltip : {
									show : true
								},
								legend : {
									data : [ '当月签订合同数量' ]
								},
								xAxis : [ {
									type : 'category',
									data : columnarmonth
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
									"name" : "当月签订合同数量",
									"type" : "bar",
									"data" : columnarcount
								} ]
							}	
					);
			    }
			);
		}
		
		function lastyear(){
			var columnarmonth=[];
			var columnarcount=[];
			columnarecharts.clear();
			$.post(
			    "${ctx}/contract/echartlastyear.do",
			    {},
			    function(data){
			    	for(var key in data){
			    		columnarmonth.push(data[key].columnarmonth);
			    		columnarcount.push(data[key].columnarcount);
			    	}
			    	columnarecharts.hideLoading();    //隐藏加载动画
					columnarecharts.setOption(
							{
								title : {
			            	        text: "上一年每月开始的合同数量"
			            	    },
								tooltip : {
									show : true
								},
								legend : {
									data : [ '上一年每月合同数量' ]
								},
								xAxis : [ {
									type : 'category',
									data : columnarmonth
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
									"name" : "上一年每月合同数量",
									"type" : "bar",
									"data" : columnarcount
								} ]
							}	
					);
			    }
			);
		}
	</script>
</body>
</html>
