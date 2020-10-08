<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
   <title>查看销售信息</title>
    <link rel="stylesheet" href="css/tableall.css"/>
<link rel="stylesheet" href="css/registe.css"/>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link href="https://unpkg.com/bootstrap-table@1.18.0/dist/bootstrap-table.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/fullcalendar.css" />
<link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/fullcalendar.css" />
<link rel="stylesheet" href="css/matrix-style.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="css/jquery.gritter.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
    <style>
#h li{float: left; }
#h a{font-size:15px;width: 230px; height: 30px;padding: 10px 0;text-align: center;  background: #3c763d; display: block; text-decoration:none; color:white}
#h a:hover{color:white;background: green}
.p-tail {
    padding: 10px;
    font-size: 12px;
    color: #8a6d3b;
}
.i-tail {
    width: 14px;
    height: 11px;
    position: relative;
    display: inline-block;
    background-image: url(../img/listicon.jpg);
    top: 1px;
}
</style>
</head>
<body>
<!--Header-part-->
<div id="header">
  <h1><a href="dashboard.html">管理部门平台导航</a></h1>
</div>
<!--close-Header-part--> 
<!--top-Header-menu-->
<div id="user-nav" class="navbar navbar-inverse">
  <ul class="nav">
    <li  class="dropdown" id="profile-messages" ><a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle"><i class="icon icon-user"></i>  <span class="text">欢迎使用者</span><b class="caret"></b></a>
      <ul class="dropdown-menu">
        <li><a href="#"><i class="icon-user"></i> 我的个人资料 </a></li>
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
    <li class=""><a title="" href="#"><i class="icon icon-cog"></i> <span class="text">设置</span></a></li>
    <li class=""><a title="" href="./logout"><i class="icon icon-share-alt"></i> <span class="text">注销</span></a></li>
    <li><%
	String staff_id = request.getSession().getAttribute("staff_id").toString();
				%> <%
 	String staff_name = request.getSession().getAttribute("staff_name").toString();
 %> 您好，<%=staff_id%> <%=staff_name%>欢迎登录
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

<div id="content">
<div id="content-header">
    <div id="breadcrumb"> <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> 首页</a></div>
  </div>
<!--End-breadcrumbs-->

<div class="find-top">
     <p class="p-tail"><i class="i-tail"></i> 该页面主要是销售部门查看销售调令</p>
</div>
<!--  <div class="find-top1">
    <table class="top-table">
     <tr><td class="top-table-label">销售调令</td><td><input id="saleCalloutOrder" name="saleCalloutOrder"></tr>
     <tr><td colspan="10" style="text-align: center"><button class="add-but" onclick="table1()"><i class="glyphicon glyphicon-edit"></i>销售调令材料</button></td></tr>
      </table>
  </div>-->
 <div class="table-con">
        <table id="table1" class="table-style"></table>
</div>
</div>
</body>
<script src="js/jquery.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
<script>
$(function(){
	table1();
})
    function table1(){
        $('#table1').bootstrapTable({
        	theadClasses: "thead-blue",//设置thead-blue为表头样式
    	    classes: "table table-bordered table-striped table-sm table-dark", 
            method: "post",
            striped: true,
            singleSelect: false,
            url: "salaryServlet",
            //data:{},
            dataType: "json",
            pagination: true, //分页
            pageSize: 15,
            pageNumber: 1,
            search:true, //显示搜索框
            showColumns: true,                  //是否显示所有的列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            showRefresh: true,                  //是否显示刷新按钮
            contentType: "application/x-www-form-urlencoded",
            exportDataType:'all',//'basic':当前页的数据, 'all':全部的数据, 'selected':选中的数据    
            showExport: true,  //是否显示导出按钮    
            buttonsAlign:"right",  //按钮位置    
            exportTypes:['excel','xlsx','csv','pdf'],
            exportOptions:{
                // ignoreColumn: [0,1],  //忽略某一列的索引
                fileName: "销售调令信息",  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: "销售调令信息",
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight'], //设置格式
            },
            queryParams:function queryParams(params){
                var temp = {
						action:"seesalecalloutorder",
						//saleCalloutOrder:document.getElementById("saleCalloutOrder").value,
						//saleCalloutOrder:document.getElementById("saleCalloutOrder").value,
				};     
                return temp;
            },
            columns: [[
            	{
            		"title": "销售调令信息",
                    "font-size":"100px",
                    "halign":"center",
                    "align":"center",
                    "valign": "middle",
                    "colspan": 13
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
                        title: "调令序号",
                        field: 'saleCallid',
                        align: 'center',
                        valign: 'middle',
                        visible: false
                    },
                	{
                        title: "调运时间",
                        width:'200px',
                        field: 'useTime',
                        sortable: true,
                        align: 'center',
                        valign: 'middle'
                    },
                    {
                        title: '合同编号',
                        width:'200px',
                        sortable: true,
                        field: 'contractnum',
                        align: 'center',
                        valign: 'middle'
                    },
                	{
                    title: "调令号",
                    field: 'saleCalloutOrder',
                    /*formatter:function(value,row,index){
                  	   return document.getElementById("cutnum").value;
                     },*/
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '调令状态',
                    field: 'salestatus',
                    width:'200px',
                    align: 'center',
                     valign: 'middle',
                     sortable: true,
                    formatter: function(value,row,index) {
                    var a = ""; 
                    var b="待审核";
                    var c="完成调运";
                    var d="调运进行中";
                    var e="调令取消";
                        if(value == "0") {  
                            var a = '<span style="color:#c12e2a;width:100px;font-size:18px"><i class="fa fa-times-circle-o" aria-hidden="true"></i>'+b+'</span>';  
                        }else if(value == "4"){  
                            var a = '<span style="color:#3e8f3e;width:100px;font-size:18px"><i class="fa fa-check-circle-o" aria-hidden="true"></i>'+c+'</span>';  
                        }
                        else if(value == "1" || value == "3"){  
                            var a = '<span style="color:#21a63e;width:100px;font-size:18px"><i class="fa fa-check-circle-o" aria-hidden="true"></i>'+d+'</span>';  
                        }
                        else if(value == "2")
                        {  
                            var a = '<span style="color:#d733c7;width:100px;font-size:18px"><i class="fa fa-check-circle-o" aria-hidden="true"></i>'+e+'</span>';  
                        }
                        return a;
                }
                },
                {
                    title: '购货人',
                    width:'200px',
                    field: 'demander',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '供货地点',
                    width:'200px',
                    field: 'yard',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '供货分区',
                    field: 'section',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '合计数量',
                    field: 'totalnum',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: "检尺员",
                    field: 'surveyor',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '通知签发人',
                    field: 'signer',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<a href="salaryServlet?action=salecallupdateM&saleCalloutOrder=\''+ row.saleCalloutOrder + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        //var  d = '<a href="workpageSevrlet?action=alldelete&workid=\''+ row.workid + '\'"><button  id="id="delete" data-id="98" class="btn btn-xs btn-primary">删除</button></a>';
                        return  d;
                    }
                },
                ],
            ]
        });
    }

    </script>
  <script src="js/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
<script src="js/bootstrap-table-export.js"></script>
<script src="js/tableExport.js"></script>
<script src="js/jquery.base64.js"></script>
<script type="text/javascript" src="/static/js/xlsx.full.min.js"></script>
<script src="https://unpkg.com/tableexport.jquery.plugin/libs/jsPDF/jspdf.min.js"></script>
<script src="https://unpkg.com/tableexport.jquery.plugin/libs/jsPDF-AutoTable/jspdf.plugin.autotable.js"></script>
<script src="js/matrix.js"></script> 
<script src="js/matrix.dashboard.js"></script>   
</html>