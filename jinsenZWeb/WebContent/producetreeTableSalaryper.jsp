<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>销售部门查看进仓木材数据</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link href="https://unpkg.com/bootstrap-table@1.18.0/dist/bootstrap-table.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/tableall.css">
    <link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/fullcalendar.css" />
<link rel="stylesheet" href="css/matrix-style.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="css/jquery.gritter.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
<link type="text/css" rel="stylesheet" href="css/PrintArea.css" />
        <style>
#h li{float: left; }
#h a{font-size:15px;width: 230px; height: 30px;padding: 10px 0;font-family: 微软雅黑;text-align: center;  background: #3c763d; display: block; text-decoration:none; color:white}
#h a:hover{color:white;background: green}
    </style>  
    <style>
/*定义类名为.thead-blue的样式*/
.table .thead-blue th {
color: #fff;
background-color: #3195f1;
border-color: #0d7adf;
}
.bar1 {background: #e8e9ef;margin-left:auto; margin-right:auto;padding:10px;border-collapse:collapse}
        .bar1 input,select{
            border: 2px solid #7BA7AB;
            border-radius: 5px;
            background: #faf7f7;
            color: #9E9C9C;
        }
        .bar1 button {
            top: 0;
            right: 0;
            background: #4bb7e3;
            border-radius: 0 5px 5px 0;
        }
        .bar1 button:before {
            content: "\f002";
            font-family: FontAwesome;
            font-size: 16px;
            color: #F9F0DA;
        }
</style>
    <script type="text/javascript">
    function submit_year()
    {
    	var now = new Date();
    	var year = now.getFullYear();

    	for(var i = year; i > year -10 ; i --)
    	{
    		var str = '<option>'+i+'</option>';
    		
    	    $("#sa_year").show().append(str);
    	    
    	}
    }
    function load()
    {
    	$.ajax({
            url:"salaryServlet",//要发送的地址
            data:{
                "action":"loadyardinfo"
            },
            type: "POST",
            dataType:"json",
            success: function (data) {
                for(i = 0;i<data.length;i++)
                {
                	str = "<option>"+data[i].yardname+"</option>";
                	
                	$("#yard").show().append(str);
                }
            }
        })
    }
    </script>
    
    <script type="text/javascript">
    function producetree()
    {
    	$('#myModal_monthtree').modal('hide');
    	$('#table1').bootstrapTable('destroy');
    	$('#table1').bootstrapTable({
            method: "post",
            striped: false,
            singleSelect: false,
            cache: false,//缓存
            url: "chartServlet",
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
            exportTypes:['excel','xlsx','csv','pdf'],  //导出文件类型，[ 'csv', 'txt', 'sql', 'doc', 'excel', 'xlsx', 'pdf']
            exportOptions:{
                // ignoreColumn: [0,1],  //忽略某一列的索引
                fileName: "采伐木材情况表",  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: "采伐木材情况表",
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight'], //设置格式
            },
            queryParams:function queryParams(params){
                var temp = {
						action:"producetree1",
						timeStart:document.getElementById("timeStart").value,
						timeEnd:document.getElementById("timeEnd").value,
						yard:document.getElementById("yard").value,
						treetype:document.getElementById("treetype").value,
						tradius:document.getElementById("tradius").value,
						tlong:document.getElementById("tlong").value,
						cutnum:document.getElementById("cutnum").value,
						projectPackageName:document.getElementById("projectPackageName").value,
						contractionSide:document.getElementById("contractionSide").value
				};     
                return temp;
            },
            columns: [
            	[
                    {
                        "title": "采伐木材情况表",
                        "font-size":"100px",
                        "halign":"center",
                        "align":"center",
                        "valign": "middle",
                        "colspan": 17
                    }
                ],
            	[ 
                     {
                    	 title: "日期",
                         field: 'cutdate',
                         align: 'center',
                         sortable: true,
                         valign: 'middle' 
                     },
                     {
                    	 title:'工程包',
                    	 field: 'projectPackageName',
                         align: 'center',
                         sortable: true,
                         valign: 'middle'
                     },
                     {
                    	 title:'生产业主',
                    	 field: 'contractionSide',
                         align: 'center',
                         valign: 'middle'
                     },
            		{
                    title: "采伐证号",
                    field: 'cutNum',
                    sortable: true,
                    align: 'center',
                    valign: 'middle'
                },
                 {
                 	title: '货场地点',
                   field: 'yard',
                   align: 'center',
                   valign: 'middle'
                 },
                 {
                  	title: '树的类型',
                   field: 'treetype',
                   align: 'center',
                   valign: 'middle'
                 },
                 {
                	 title:'检尺长',
                	 field: 'tlong',
                     align: 'center',
                     valign: 'middle'
                 },
                 {
                	 title:'检尺径',
                	 field: 'tradius',
                     align: 'center',
                     valign: 'middle'
                 },
                 {
                	 title:'根数',
                	 field: 'num',
                     align: 'center',
                     valign: 'middle'
                 },
                 {
                	 title:'材积',
                	 field: 'tvolume',
                     align: 'center',
                     valign: 'middle'
                 },
                 {
                   	title: '检尺员',
                     field: 'surveyor',
                     align: 'center',
                     visible: false,
                     valign: 'middle'   
                },
                 {
                   	title: '伐区管理员',
                     field: 'forester',
                     align: 'center',
                     valign: 'middle',
                     visible: false
                },
            ]
            	]
        });
    }
    
    function totaltree()
    {
    	$('#myModal_monthtree').modal('hide');
    	$('#table1').bootstrapTable('destroy');
    	$('#table1').bootstrapTable({
            method: "post",
            striped: false,
            singleSelect: false,
            cache: false,//缓存
            url: "chartServlet",
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
            exportTypes:['excel','xlsx','csv','pdf'],  //导出文件类型，[ 'csv', 'txt', 'sql', 'doc', 'excel', 'xlsx', 'pdf']
            exportOptions:{
                // ignoreColumn: [0,1],  //忽略某一列的索引
                fileName: "进仓木材统计表",  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: "进仓木材统计表",
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight'], //设置格式
            },
            queryParams:function queryParams(params){
                var temp = {
						action:"totalproduce",
						timeStart:document.getElementById("timeStart").value,
						timeEnd:document.getElementById("timeEnd").value,
						yard:document.getElementById("yard").value,
						treetype:document.getElementById("treetype").value,
						tradius:document.getElementById("tradius").value,
						tlong:document.getElementById("tlong").value,
						cutnum:document.getElementById("cutnum").value,
						projectPackageName:document.getElementById("projectPackageName").value,
						contractionSide:document.getElementById("contractionSide").value
				};     
                return temp;
            },
            columns: [
            	[
                    {
                        "title": "进仓木材统计表",
                        "font-size":"100px",
                        "halign":"center",
                        "align":"center",
                        "valign": "middle",
                        "colspan": 15
                    }
                ],
            	[ 
//                      {
//                     	 title: "日期",
//                          field: 'cutdate',
//                          align: 'center',
//                          valign: 'middle' 
//                      },
//             		{
//                     title: "采伐证号",
//                     field: 'cutNum',
//                     align: 'center',
//                     valign: 'middle'
//                 },
                 {
                 	title: '货场地点',
                   field: 'yard',
                   align: 'center',
                   valign: 'middle'
                 },
                 {
                  	title: '树的类型',
                   field: 'treetype',
                   align: 'center',
                   valign: 'middle'
                 },
//                  {
//                 	 title:'检尺长',
//                 	 field: 'tlong',
//                      align: 'center',
//                      valign: 'middle'
//                  },
//                  {
//                 	 title:'检尺径',
//                 	 field: 'tradius',
//                      align: 'center',
//                      valign: 'middle'
//                  },
                 {
                	 title:'根数',
                	 field: 'num',
                     align: 'center',
                     valign: 'middle'
                 },
                 {
                	 title:'材积',
                	 field: 'tvolume',
                     align: 'center',
                     valign: 'middle'
                 },
//                  {
//                    	title: '检尺员',
//                      field: 'surveyor',
//                      align: 'center',
//                      valign: 'middle'   
//                 },
//                  {
//                    	title: '伐区管理员',
//                      field: 'forester',
//                      align: 'center',
//                      valign: 'middle'   
//                 },
            ]
            	]
        });
    }
    </script>
    
</head>

    
<body onload="load()">
<% Date d = new Date();

SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

String now = df.format(d); %>
<div id="header">
  <h1><a href="dashboard.html">超级管理员平台</a></h1>
</div>
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
    <div id="breadcrumb"> <a href="salaryper.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a></div>
  </div>

        <div class="find-top1">
           <div class="find-top">
           <p class="p-tail"><i class="i-tail"></i> 该页面是生产木材信息报表打印界面</p>
          </div>
           <div class="find-top1">
            <table class="bar1">
           <!--  <tr><td class="top-table-label">年份：</td><td><select id="sa_year" name="sa_year" onChange="change_year()" readonly unselectable="on"></select></tr>
           <tr><td class="top-table-label">月份：</td><td><select id="sa_month" name="sa_month"><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option><option>10</option><option>11</option><option>12</option><option>1-12</option><option>1-3</option><option>4-6</option><option>7-9</option><option>10-12</option></select></td></tr>-->
           <%-- <tr>
           <td width="200">选择开始日期：<input width="160" type="date" name="timeStart" id="timeStart"  value="2020-01-01"></td>
           <td width="200">选择结束日期：<input width="160" type="date" name="timeEnd" id="timeEnd" value="2020-12-01"></td>
           <td width="200">采伐证编号：<input  width="160" type="text" name="cutnum" id="cutnum"></td>
           <td width="400">工程包：<input  width="160" type="text" name="projectPackageName" id="projectPackageName"></td>
           <td width="400">生产业主：<input  width="160" type="text" name="contractionSide" id="contractionSide"></td>
           </tr>
           <tr></tr>
           <tr>
           <td width="400">
            <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;货&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;场：&nbsp;&nbsp;&nbsp;</span><select style="width:160" name="yard" id="yard"><option selected="selected"></option></select></td>
           <td width="400">
            <span>树种：</span><select style="width:160" name="treetype" id="treetype">
            <option selected="selected"></option>
            <option value='杉木'>杉木</option>
            <option value='松木'>松木</option>
            <option value='杂木'>杂木</option>
            <option value='杉薪'>杉薪</option>
            <option value='松薪'>松薪</option>
            <option value='杂薪'>杂薪</option>
            <option value='衫短材'>衫短材</option>
            <option value='杉木兜'>杉木兜</option>
            <option value='杉直柄'>杉直柄</option>
            <option value='特种材'>特种材</option>
            </select></td>
           <td width="400">
            <span>检尺长：</span><input  type="text" name="tradius" id="tradius"></td>
           <td width="400">
            <span>检尺径：</span><input  type="text" name="tradius" id="tradius"></td>
           </tr>
           --%>
           <tr>
           <td style="font-size:22px;color:red">查询时间：</td>
           <td colspan="4" ><span>时&nbsp;&nbsp;&nbsp;&nbsp;间：&nbsp;&nbsp;&nbsp;&nbsp;</span><input style="width:230px" type="date" name="timeStart" id="timeStart"  value="2020-01-01">
           <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><input style="width:230px" type="date" name="timeEnd" id="timeEnd" value="2020-12-01"></td>
           </tr>
           
           <tr>
           <td style="font-size:22px;color:red">采伐证信息：</td>
           <td>工程包：&nbsp;&nbsp;&nbsp;&nbsp;<input  width="140px" type="text" name="projectPackageName" id="projectPackageName"></td>
           <td>生产业主：<input  width="160" type="text" name="contractionSide" id="contractionSide"></td>
           <td colspan="2">采伐证编号：<input  width="160" type="text" name="cutnum" id="cutnum"></td>
           </tr>
           
           <tr>
           <td style="font-size:22px;color:red">查询树材信息：</td>
           <td>
            <span>货&nbsp;&nbsp;&nbsp;&nbsp;场：&nbsp;&nbsp;&nbsp;&nbsp;</span><select  style="width:230px" name="yard" id="yard"><option selected="selected"></option></select></td>
           <td>
            <span>&nbsp;&nbsp;&nbsp;&nbsp;树&nbsp;&nbsp;种：&nbsp;&nbsp;</span><select style="width:230px" name="treetype" id="treetype">
            <option selected="selected"></option>
            <option value='杉木'>杉木</option>
            <option value='松木'>松木</option>
            <option value='杂木'>杂木</option>
            <option value='杉薪'>杉薪</option>
            <option value='松薪'>松薪</option>
            <option value='杂薪'>杂薪</option>
            <option value='衫短材'>衫短材</option>
            <option value='杉木兜'>杉木兜</option>
            <option value='杉直柄'>杉直柄</option>
            <option value='特种材'>特种材</option>
            </select></td>
           <td>
            <span>&nbsp;&nbsp;&nbsp;&nbsp;检尺长：&nbsp;&nbsp;&nbsp;</span><input  type="text" name="tlong" id="tlong"></td>
           <td>
            <span>检尺径：</span><input  type="text" name="tradius" id="tradius"></td>
           </tr>
           <tr>
           <td colspan="8" style="text-align: center"><button  onclick="totaltree()">查询进仓总库存</button>
           <button  onclick="producetree()">查询进仓详细木材</button></td>
           </tr>
            </table>
           </div>
           <div class="table-con">
        <table id="table1" class="table-style"></table>
        </div>
          
        </div> 

</div>


</body>
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