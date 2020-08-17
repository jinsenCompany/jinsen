<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="jinshen.bean.*" %>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1" />
<title>木材销售绘图（时间）</title>
<!-- 引入echart.js -->
<script src="js/echarts.js"></script>
<script src="js/echarts.min.js"></script>
<script src="js/jquery.min.js"></script>
<!--  <script src="lib/esl.js"></script>
<script src="chart/lib/config.js"></script>
 <script src="chart/lib/jquery.min.js"></script>
<script src="chart/lib/facePrint.js"></script>
<link rel="stylesheet" href="chart/lib/reset.css">-->
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">
    <link rel="stylesheet" href="js/jQueryCalendar/calendar.css">
    <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />

    <!-- Custom CSS -->
    <link href="css/style.css" rel='stylesheet' type='text/css' />

    <!-- font-awesome icons CSS -->
    <link href="css/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons CSS-->

    <!-- side nav css file -->
    <link href='css/SidebarNav.min.css' media='all' rel='stylesheet' type='text/css'/>
    <!-- //side nav css file -->

    <script src="js/jquery-1.11.1.min.js"></script>
    <script src="js/modernizr.custom.js"></script>

</head>
<body "loadDate()">
<div id="chartmain" style="width:600px;height:400px; display:inline-block"></div>
<div id="myPieDiv" style="height: 400px; width: 600px;display:inline-block" ></div>
<div id="myline" style="height: 400px; width: 600px;display:inline-block" ></div>

<script type="text/javascript">
//折线销售价格
function loadDate(option){
$.ajax({
	type:"get",
	async : false,
	url:"chartServlet?action=treechart",
    data:{},
    dataType:"json",
    success:function(result){
    	if(result){
    		option.xAxis[0].data=[];
    		 for(var i=0;i<result.length;i++){
    			 option.xAxis[0].data.push(result[i].yarddate);
    			 //yarddate.push(result[i].yarddate);
                 //names.push(result[i].name);
    			 //alert(result[i].yarddate);
               }
    		  option.series[0].data=[];
               for(var i=0;i<result.length;i++){
            	   option.series[0].data.push(result[i].price);
            	   //price.push(result[i].price);
            	   //alert(result[i].price);
               }
        }
     },
     error : function(errorMsg) {
          //请求失败时执行该函数
      alert("图表请求数据失败!");
      //mychart.hideLoading();
    }
})
}


var mychart = echarts.init(document.getElementById('chartmain'));
var option={
		title:{
			text:"木材销售"
		},
		tooltip:{
			show: true
		},
		grid: {
            containLabel: true
        },
		legend:{
			data:['销量']
		
		},
		xAxis:[{
			type: 'category',
		        //data: [],
		}],
		yAxis : [ {
			type : 'value',
			axisLabel: {
		        formatter: '{value} 元'
		        },
		} ],
		series : [ {
			name : '销量',
			type : 'line',
			color: 'blue',
            smooth: true,
           // data:[]
		} ]
};
//加载数据到option
loadDate(option);
//设置option
mychart.setOption(option);
</script>

<script type="text/javascript">
function loadDate(option){
	$.ajax({
		type:"get",
		async : false,
		url:"chartServlet?action=treetype",
	    data:{},
	    dataType:"json",
	    success:function(result){
	    	if(result){
	    		option.xAxis[0].data=[];
	    		 for(var i=0;i<result.length;i++){
	    			 option.xAxis[0].data.push(result[i].treetype);
	    			 //yarddate.push(result[i].yarddate);
	                 //names.push(result[i].name);
	    			 //alert(result[i].yarddate);
	               }
	    		  option.series[0].data=[];
	               for(var i=0;i<result.length;i++){
	            	   option.series[0].data.push(result[i].num);
	            	   //price.push(result[i].price);
	            	   //alert(result[i].price);
	               }
	        }
	     },
	     error : function(errorMsg) {
	          //请求失败时执行该函数
	      alert("图表请求数据失败!");
	      //mychart.hideLoading();
	    }
	})
	}


	var mychart = echarts.init(document.getElementById('myPieDiv'));
	var option={
			title:{
				text:"木材库存"
			},
			tooltip:{
				show: true
			},
			grid: {
	            containLabel: true
	        },
			legend:{
				data:['数量','平均温度']
	            
			
			},
			xAxis:[{
				type: 'category',
			        //data: [],
			}],
			yAxis : [ {
				type : 'value',
				axisLabel: {
			        formatter: '{value} 根'
			        },
			},
			{
                type: 'value',
                name: '温度',
                min: 0,
                max: 25,
                interval: 5,
                axisLabel: {
                    formatter: '{value} °C'
                }
            }
			
			],
			series : [ {
				name : '数量',
				type : 'bar',
				color: 'red',
	            smooth: true,
	           // data:[]
			},
				{
                    name:'平均温度',
                    type:'line',
                    color: 'blue',
                    yAxisIndex: 1,
                    data:[2.0, 2.2, 3.3, 4.5, 6.3, 10.2]
                }
			]
	};
	//加载数据到option
	loadDate(option);
	//设置option
	mychart.setOption(option);
</script>

<script type="text/javascript">
function loadDate(option){
	$.ajax({
		type:"get",
		async : false,
		url:"chartServlet?action=treeout",
	    data:{},
	    dataType:"json",
	    success:function(result){
	    	if(result){
	    		option.xAxis[0].data=[];
	    		 for(var i=0;i<result.length;i++){
	    			 option.xAxis[0].data.push(result[i].treetype);
	    			 //yarddate.push(result[i].yarddate);
	                 //names.push(result[i].name);
	    			 //alert(result[i].yarddate);
	               }
	    		  option.series[0].data=[];
	    		  option.series[1].data=[];
	               for(var i=0;i<result.length;i++){
	            	   option.series[0].data.push(result[i].num);
	            	   option.series[1].data.push(result[i].price)
	            	   //price.push(result[i].price);
	            	   //alert(result[i].price);
	               }
	        }
	     },
	     error : function(errorMsg) {
	          //请求失败时执行该函数
	      alert("图表请求数据失败!");
	      //mychart.hideLoading();
	    }
	})
	}


	var mychart = echarts.init(document.getElementById('myline'));
	var option={
			title:{
				text:"木材销售"
			},
			tooltip:{
				show: true
			},
			grid: {
	            containLabel: true
	        },
			legend:{
				data:['数量','销售']
	            
			
			},
			xAxis:[{
				type: 'category',
			        //data: [],
			}],
			yAxis : [ {
				type : 'value',
				axisLabel: {
			        formatter: '{value} 根'
			        },
			},
			{
                type: 'value',
                name: '销售',
                axisLabel: {
                    formatter: '{value} 元'
                }
            }
			
			],
			series : [ {
				name : '数量',
				type : 'bar',
				color: 'red',
	            smooth: true,
	           // data:[]
			},
				{
                    name:'销售',
                    type:'line',
                    color: 'blue',
                    yAxisIndex: 1,
                }
			]
	};
	//加载数据到option
	loadDate(option);
	//设置option
	mychart.setOption(option);
</script>
    <script src="js/jquery-1.11.1.min.js"></script>
    <script src="js/modernizr.custom.js"></script>
<script src="js/jquery.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
<div class="span9">
<script>
    $(function(){
        table1();
    })
    function table1(){
        $('#table1').bootstrapTable({
            method: "post",
            striped: true,
            singleSelect: false,
            url: "chartServlet?action=saleman",
            data:{},
            dataType: "json",
            pagination: true, //分页
            pageSize: 8,
            pageNumber: 1,
            search: true, //显示搜索框
            contentType: "application/x-www-form-urlencoded",
            showRefresh: true,                      //是否显示刷新按钮
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            detailView: true,
            columns: [
            	{
                    checkbox: "true",
                    field: 'check',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: "工单号",
                    field: 'workid',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '树种',
                    field: 'treetype',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '售价',
                    field: 'price',
                    align: 'center',
                    valign: 'middle'
                }
            ]
        });
    }
    </script>
    </div>
</body>
</html>