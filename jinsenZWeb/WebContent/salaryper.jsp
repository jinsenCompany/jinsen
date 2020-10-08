<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>销售部门平台导航</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/fullcalendar.css" />
<link rel="stylesheet" href="css/matrix-style.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="css/jquery.gritter.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
<script src="js/excanvas.min.js"></script> 
<script src="js/jquery.min.js"></script> 
<script src="js/jquery.ui.custom.js"></script> 
<script src="js/bootstrap.min.js"></script> 
<script src="js/jquery.flot.min.js"></script> 
<script src="js/jquery.flot.resize.min.js"></script> 
<script src="js/jquery.peity.min.js"></script> 
<script src="js/fullcalendar.min.js"></script> 
<script src="js/matrix.js"></script> 
<script src="js/matrix.dashboard.js"></script> 
<script src="js/jquery.gritter.min.js"></script> 
<script src="js/matrix.interface.js"></script> 
<script src="js/matrix.chat.js"></script> 
<script src="js/jquery.validate.js"></script> 
<script src="js/matrix.form_validation.js"></script> 
<script src="js/jquery.wizard.js"></script> 
<script src="js/jquery.uniform.js"></script> 
<script src="js/select2.min.js"></script> 
<script src="js/matrix.popover.js"></script> 
<script src="js/jquery.dataTables.min.js"></script> 
<script src="js/matrix.tables.js"></script>
<script src="js/echarts.js"></script>
<script src="js/echarts.min.js"></script>
<script src="js/jquery.min.js"></script> 
</head>

<body>

<!--Header-part-->
<div id="header">
  <h1><a href="dashboard.html">销售部门平台导航</a></h1>
</div>
<!--close-Header-part--> 


<!--top-Header-menu-->
<div id="user-nav" class="navbar navbar-inverse">
  <ul class="nav">
    <li  class="dropdown" id="profile-messages" ><a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle"><i class="icon icon-user"></i>  <span class="text">欢迎使用者</span><b class="caret"></b></a>
      <ul class="dropdown-menu">
        <li><a href="ChangePassword.jsp"><i class="icon-user"></i> 我的个人资料 </a></li>
        <li class="divider"></li>
        <li><a href="#"><i class="icon-check"></i> 我的任务</a></li>
        <li class="divider"></li>
        <li><a href="./logout"><i class="icon-key"></i> 注销</a></li>
      </ul>
    </li>
    <li class="dropdown" id="menu-messages"><a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle"><i class="icon icon-envelope"></i> <span class="text">消息</span> <span class="label label-important">5</span> <b class="caret"></b></a>
      <ul class="dropdown-menu">
        <li><a class="sAdd" title="" href="#"><i class="icon-plus"></i> 系的消息</a></li>
        <li class="divider"></li>
        <li><a class="sInbox" title="" href="#"><i class="icon-envelope"></i> 收件箱</a></li>
        <li class="divider"></li>
        <li><a class="sOutbox" title="" href="#"><i class="icon-arrow-up"></i> 发件箱</a></li>
        <li class="divider"></li>
        <li><a class="sTrash" title="" href="#"><i class="icon-trash"></i> 垃圾箱</a></li>
      </ul>
    </li>
    <li class=""><a title="" href="ChangePassword.jsp"><i class="icon icon-cog"></i> <span class="text">设置</span></a></li>
    <li class=""><a title="" href="./logout"><i class="icon icon-share-alt"></i> <span class="text">注销</span></a></li>
    <li><%
	String staff_id = request.getSession().getAttribute("staff_id").toString();
				%> <%
 	String staff_name = request.getSession().getAttribute("staff_name").toString();
	String power_type = request.getSession().getAttribute("power_type").toString();%> 您好，<%=staff_id%> <%=staff_name%>欢迎登录
 </li>
  </ul>
</div>
<!--close-top-Header-menu-->
<!--start-top-serch-->
<div id="search">
  <input type="text" placeholder="Search here..."/>
  <button type="submit" class="tip-bottom" title="Search"><i class="icon-search icon-white"></i></button>
</div>
<!--close-top-serch-->
<!--sidebar-menu-->
<div id="sidebar"><a href="#" class="visible-phone"><i class="icon icon-home"></i> 仪表盘</a>
  <ul>
  <li class="submenu"> <a href="#"><i class="icon icon-home"></i> <span>合同管理</span> <span class="label label-important">2</span></a>
     <ul>
        <li><a href="salaryContract.jsp">创建合同</a></li>
        <li><a href="salaryContractList.jsp">合同进度</a></li>
      </ul>
     </li>
    <li class="submenu"> <a href="#"><i class="icon icon-signal"></i> <span>客户信息管理</span> <span class="label label-important">2</span></a>
     <ul>
        <li><a href="productowner.jsp">创建客户信息</a></li>
        <li><a href="productownerSee.jsp">查看客户信息</a></li>
      </ul>
     </li>
     <li class="submenu"> <a href="#"><i class="icon icon-inbox"></i> <span>销售调令管理</span> <span class="label label-important">3</span></a>
     <ul>
        <li><a href="saleCalloutOrder.jsp">录入销售调令</a></li>
        <li><a href="saleCalloutOrdersee.jsp">查看调令材料</a></li>
        <li><a href="saleCalloutOrderShenheModer.jsp">查看调令审核</a></li>
      </ul>
     </li>
     <li class="submenu"> <a href="#"><i class="icon icon-th"></i> <span>销售结算</span> <span class="label label-important">2</span></a>
     <ul>
<!--         <li><a href="outyardCostS.jsp">结算检尺费</a></li> -->
        <li><a href="treeoutPrice.jsp">木材销售结算单</a></li>
        <li><a href="treeoutPriceTable.jsp">木材销售结算台账</a></li>
      </ul>
     </li>
    <li><a href="treeoutCodepage.jsp"><i class="icon icon-fullscreen"></i> <span>打印销售码单</span></a></li>
    <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>库存与销售</span> <span class="label label-important">2</span></a>
     <ul>
        <li><a href="producetreeTableSalaryper.jsp">木材进仓库存</a></li>
        <li><a href="treeoutTableSalayper.jsp">木材出仓销售</a></li>
      </ul>
     </li>
  </ul>
</div>
<!--sidebar-menu-->

<!--main-container-part-->
<div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"> <a href="salaryper.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> 首页</a></div>
  </div>
<!--End-breadcrumbs-->

<!--Action boxes-->
  <div class="container-fluid">
    <div class="quick-actions_homepage">
      <ul class="quick-actions">
        <li class="bg_lb"> <a href="salaryContract.jsp"> <i class="icon-dashboard"></i>创建合同 </a> </li>
        <li class="bg_lg span3"> <a href="salaryContractList.jsp"><i class="icon-signal"></i><span>合同进度</span></a></li>
        <li class="bg_ly"> <a href="productowner.jsp"> <i class="icon-inbox"></i><span class="label label-success"></span>创建客户信息 </a> </li>
        <li class="bg_lo"> <a href="productownerSee.jsp"> <i class="icon-th"></i>查看客户信息</a> </li>
        <li class="bg_ls"> <a href="saleCalloutOrder.jsp"> <i class="icon-fullscreen"></i>录入销售调令</a></li>
        <li class="bg_lo span3"> <a href="saleCalloutOrderShenheModer.jsp"> <i class="icon-th-list"></i>查看调令审核</a> </li>
        <li class="bg_ls"> <a href="treeoutPrice.jsp"> <i class="icon-tint"></i> 木材销售结算单</a> </li>
        <li class="bg_lb"> <a href="treeoutPriceTable.jsp"> <i class="icon-pencil"></i>木材销售结算台账</a> </li>
        <li class="bg_lg"> <a href="treeoutCodepage.jsp"> <i class="icon-calendar"></i>打印销售码单</a> </li>
        <li class="bg_lr"> <a href="producetreeTableSalaryper.jsp"> <i class="icon-info-sign"></i>木材进仓库存</a> </li>
      </ul>
    </div>
<!--End-Action boxes-->    

<!--Chart-box-->  
 <div class="row-fluid">
      <div class="widget-box">
        <div class="widget-title bg_lg"><span class="icon"><i class="icon-signal"></i></span>
          <h5>木材销售分析</h5>
        </div>
        <div class="widget-content" >
          <div class="row-fluid">
            <div class="span6" id="chartmain" style="width:45%;height:400px; display:inline-block">
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
			text:"木材按月销售统计分析"
		},
		tooltip:{
			show: true
		},
		dataZoom: {
            show: true,
            realtime: true,
            //type: 'inside'
            //startValue: '2009-09-20 12:00',
            //end: 100
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
			type : 'bar',
			color: 'green',
            //smooth: true,
           // data:[]
		}
		]
};
     //加载数据到option
       loadDate(option);
       //设置option
      mychart.setOption(option);
     </script>
          </div>
          <div class="span6" id="myline" style="width:45%;height:400px;display:inline-block">
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
            		            	   option.series[0].data.push(result[i].tvolume);
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
            					text:"木材销售情况"
            				},
            				tooltip:{
            					show: true
            				},
            				grid: {
            		            containLabel: true
            		        },
            				legend:{
            					data:['材积','销售']
            		            
            				
            				},
            				xAxis:[{
            					type: 'category',
            				        //data: [],
            				}],
            				yAxis : [ {
            					type : 'value',
            					axisLabel: {
            				        formatter: '{value} 立方米'
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
            					name : '材积',
            					type : 'bar',
            					color: 'blue',
            		            //smooth: true,
            		           // data:[]
            				},
            					{
            	                    name:'销售',
            	                    type:'line',
            	                    color: 'red',
            	                    yAxisIndex: 1,
            	                }
            				]
            		};
            		//加载数据到option
            		loadDate(option);
            		//设置option
            		mychart.setOption(option);
             </script>
            </div> 
          </div>
        </div>
      </div>
    </div> 
    <hr> 
    <div class="row-fluid">
      <div class="widget-box">
        <div class="widget-title bg_lg"><span class="icon"><i class="icon-signal"></i></span>
          <h5>合同进度</h5>
        </div>
        <div class="widget-content" >
          <div class="row-fluid">
            <div class="span12">
              <div class="table-responsive">
        <table id="table1" class="table text-nowrap"></table>
    </div>
            </div>
          </div>
        </div>
      </div>
    </div>
<!--End-Chart-box--> 
    <hr/>
  </div>
</div>

<!--end-main-container-part-->

<!--Footer-part-->


<!--end-Footer-part-->
<script src="js/jquery.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
<!-- 销售页面显示销售单号树种表格 -->
<script>
    $(function(){
        table1();
    })
    /*function table1(){
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
                    title: "销售单号",
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
    }*/
    function table1(){
        $('#table1').bootstrapTable({
            method: "get",
            striped: true,
            singleSelect: false,
            url: "salaryServlet?action=contractProgress",
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
            columns: [[
            	{
            		"title": "合同信息",
                    "font-size":"100px",
                    "halign":"center",
                    "align":"center",
                    "valign": "middle",
                    "colspan": 17
                }],
                [
                	{						
 						title: '序号',
 						width: 100,
                        align: 'center',
 						formatter: function (value, row, index) {
 							return index+1;
 						}
 	                },//该列显示序号，分页不是从1开始
                    {
                        title: "合同序号",
                        field: 'contractid',
                        width: 200,
                        align: 'center',
                        valign: 'middle',
                        visible: false
                    },
                	{
                    title: "合同编号",
                    width: 200,
                    field: 'contractnum',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '合同状态',
                    field: 'contractstatus',
                    //width:'40px',
                    align: 'center',
                     valign: 'middle',
                     sortable: true,
                    formatter: function(value,row,index) {
                    var a = ""; 
                    var b="未完成";
                    var c="已完成";
                        if(value == "0") {  
                            var a = '<span style="color:#c12e2a;width:100px;font-size:18px"><i class="fa fa-times-circle-o" aria-hidden="true"></i>'+b+'</span>';  
                        }else if(value == "1"){  
                            var a = '<span style="color:#3e8f3e;width:100px;font-size:18px"><i class="fa fa-check-circle-o" aria-hidden="true"></i>'+c+'</span>';  
                        }
                        return a;
                }
                },
                {
                    title: '供方（甲方）',
                    field: 'provider',
                    width: 200,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '需方（乙方）',
                    field: 'demander',
                    align: 'center',
                    width: 200,
                    valign: 'middle'
                },
                {
                    title: '合同签订人',
                    field: 'contractsigner',
                    align: 'center',
                    width: 200,
                    valign: 'middle'
                },
                {
                    title: '合同签订数量(立方米/吨)',
                    field: 'treenumber',
                    align: 'center',
                    width: 200,
                    valign: 'middle'
                },
                {
                    title: '已完成数量(立方米/吨)',
                    field: 'useTreenum',
                    align: 'center',
                    width: 200,
                    valign: 'middle'
                },
                {
                    title: '未完成数量(立方米/吨)',
                    field: 'surplusTreenum',
                    align: 'center',
                    width: 200,
                    valign: 'middle'
                },
                {
                    title: '执行进度',
                    field: 'percentageTreenum',
                    align: 'center',
                    width: 200,
                    valign: 'middle'
                },
                {
                    title: '合同开始时间',
                    field: 'begin',
                    align: 'center',
                    width: 200,
                    valign: 'middle'
                },
                {
                    title: '合同结束时间',
                    field: 'end',
                    align: 'center',
                    width: 200,
                    valign: 'middle'
                },
                {
                    title: '保证金',
                    field: 'margin',
                    align: 'center',
                    width: 200,
                    valign: 'middle'
                },
                {
                    title: '创建时间',
                    field: 'creatTime',
                    align: 'center',
                    width: 200,
                    valign: 'middle',
                    visible: false
                },
                /*{
                    title: '合同进度',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="salaryServlet?action=contractListProg&contractid=\''+ row.contractid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<a><button type="button" data-id="98"  class="btn btn-xs btn-primary" onclick="progress(\''+ row.contractid + '\')">查看</button></a>';
                        return  d;
                    }
                },*/
                {
                    title: '合同内容详情',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    valign: 'middle',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<a href="salaryServlet?action=contractList&contractid=\''+ row.contractid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        return  d;
                    }
                },
                {
                    title: "合同操作",
                    field: 'opr',
                    width: 200,
                    align: 'center',
                    valign: 'middle',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<a href="salaryServlet?action=finishContract&contractid=\''+ row.contractid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">合同完成</button></a>';
                        //var  d = '<a href="workpageSevrlet?action=alldelete&workid=\''+ row.workid + '\'"><button  id="id="delete" data-id="98" class="btn btn-xs btn-primary">删除</button></a>';
                        return  d;
                    }
                },
                ],
            ]
    });
}
    </script>

<script type="text/javascript">
  // This function is called from the pop-up menus to transfer to
  // a different page. Ignore if the value returned is a null string:
  function goPage (newURL) {

      // if url is empty, skip the menu dividers and reset the menu selection to default
      if (newURL != "") {
      
          // if url is "-", it is this page -- reset the menu:
          if (newURL == "-" ) {
              resetMenu();            
          } 
          // else, send page to designated URL            
          else {  
            document.location.href = newURL;
          }
      }
  }

// resets the menu selection upon entry to this page:
function resetMenu() {
   document.gomenu.selector.selectedIndex = 2;
}
</script>
</body>
</html>
