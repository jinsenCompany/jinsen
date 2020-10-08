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
<title>生产台账</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://unpkg.com/bootstrap-table@1.18.0/dist/bootstrap-table.min.css" rel="stylesheet">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
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
window.onload = function () {
    locationInput = function () {
    };
}
</script>
<script src="js/jquery.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
 
<script>

// $(function () {

//     //1.初始化Table

//     var oTable = new TableInit();

//     oTable.Init();

//     //2.初始化Button的点击事件

//     var oButtonInit = new ButtonInit();

//     oButtonInit.Init();

// });

// var TableInit = function cutWorkid() {

//     var oTableInit = new Object();

    //初始化Table
$(function(){
	cutWorkid();
})
    function cutWorkid() {
    	$("#table1").bootstrapTable('destroy');
        $('#table1').bootstrapTable({

        	method: "get",
            striped: true,
            singleSelect: false,
            url: "cutnumServlet",
            data:{},
          //data:{},
            dataType: "json",
            pagination: true, //分页
            pageSize: 10,
            pageNumber: 1,
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            uniqueId: "cutnum",                     //每一行的唯一标识，一般为主键列
            search:true, //显示搜索框
            showColumns: true,                  //是否显示所有的列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            showRefresh: true,                  //是否显示刷新按钮
            contentType: "application/x-www-form-urlencoded",
            detailView: true,                   //是否显示父子表
            sortable: true,
            exportDataType:'all',//'basic':当前页的数据, 'all':全部的数据, 'selected':选中的数据    
            showExport: true,  //是否显示导出按钮    
            buttonsAlign:"right",  //按钮位置    
            exportTypes:['excel','xlsx','csv','pdf'],
            exportOptions:{
                // ignoreColumn: [0,1],  //忽略某一列的索引
                fileName: "工程包台账",  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: "工程包台账",
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight'], //设置格式
            },
            queryParams:function queryParams(params){
            var temp = {
					action:"findCutnumproject56",
					timeStart:document.getElementById("timeStart").value,
					timeEnd:document.getElementById("timeEnd").value,
					cutnum:document.getElementById("cutnum").value,
					projectPackageName:document.getElementById("projectPackageName").value,
					contractionSide:document.getElementById("contractionSide").value
			};     
            return temp;
        },
            columns: [[
            	{
            		"title": "木材生产台账",
                    "font-size":"100px",
                    "halign":"center",
                    "align":"center",
                    "valign": "middle",
                    "colspan": 34
                }],
                [
                	{						
 						title: '序号',
 						width: 100,
 						rowspan: 2,
                        align: 'center',
                        valign: 'middle',
 						formatter: function (value, row, index) {
 							return index+1;
 						}
 	                },//该列显示序号，分页不是从1开始
 	               {
 	                    title: '工程包创建时间',
 	                    field: 'projpackageStarttime',
 	                    width:'200',
 	                    rowspan: 2,
 	                    align: 'center',
 	                    valign: 'middle',
 	                   sortable: true
 	                },
                {
                    title: '工程包名称',
                    field: 'projectPackageName',
                    width:'200',
                    rowspan: 2,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐证号',
                    field: 'certificatenum',
                    width:'auto',
                    rowspan: 2,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐证编号',
                    field: 'cutnum',
                    width:'auto',
                    rowspan: 2,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐面积',
                    field: 'cutarea',
                    //width:'auto',
                    rowspan: 2,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '合同编号',
                    field: 'contractNum',
                    rowspan: 2,
                    width:200,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '施工方',
                    field: 'contractionSide',
                    rowspan: 2,
                    width:200,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐工期',
                    field: 'cuttime',
                    rowspan: 2,
                    width:200,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '伐区管理员',
                    field: 'forester',
                    rowspan: 2,
                    //width:'auto',
                    align: 'center',
                    valign: 'middle'
                },
                {
                	title: '设计出材量',
                    colspan: 4,
                    align: 'center'
                },
                {
                	title: '已出材量',
                    colspan: 4,
                    align: 'center'
                },
                {
                	title: '倒材量',
                    colspan: 4,
                    align: 'center'
                },
                {
                	title: '未出材量',
                    colspan: 4,
                    align: 'center'
                },
                {
                	title: '工程包执行情况',
                    colspan: 4,
                    align: 'center'
                },
                {
                	title: '下载工程包合同',
                    colspan: 1,
                    align: 'center'
                }
                ],
                [
                	{
                        title: '合计',
                        field: 'totalDesign',
                        //width:'auto',
                        align: 'center',
                        width:200,
                        valign: 'middle'
                    },
                    {
                        title: '杉木',
                        field: 'shanTreeDesign',
                        //width:'auto',
                        align: 'center',
                        width:200,
                        valign: 'middle'
                    },
                    {
                        title: '松木',
                        field: 'songTreeDesign',
                        //width:'auto',
                        align: 'center',
                        width:200,
                        valign: 'middle'
                    },
                    {
                        title: '杂木',
                        field: 'zaTreeDesign',
                        //width:'auto',
                        align: 'center',
                        width:200,
                        valign: 'middle'
                    },
                    {
                        title: '合计',
                        field: 'totalActual',
                        //width:'auto',
                        align: 'center',
                        width:200,
                        valign: 'middle'
                    },
                    {
                        title: '杉木',
                        field: 'shanTreeActual',
                        //width:'auto',
                        align: 'center',
                        width:200,
                        valign: 'middle'
                    },
                    {
                        title: '松木',
                        field: 'songTreeActual',
                        //width:'auto',
                        align: 'center',
                        width:200,
                        valign: 'middle'
                    },
                    {
                        title: '杂木',
                        field: 'zaTreeActual',
                        //width:'auto',
                        align: 'center',
                        width:200,
                        valign: 'middle'
                    },
                    {
                        title: '合计',
                        field: 'totalProduced',
                        //width:'auto',
                        align: 'center',
                        width:100,
                        valign: 'middle'
                    },
                    {
                        title: '杉木',
                        field: 'shamu',
                        //width:'auto',
                        align: 'center',
                        width:100,
                        valign: 'middle'
                    },
                    {
                        title: '松木',
                        field: 'songmu',
                        //width:'auto',
                        align: 'center',
                        width:100,
                        valign: 'middle'
                    },
                    {
                        title: '杂木',
                        field: 'zamu',
                        //width:'auto',
                        align: 'center',
                        width:100,
                        valign: 'middle'
                    },
                    {
                        title: '合计',
                        field: 'totalSurplus',
                        //width:'auto',
                        align: 'center',
                        width:200,
                        valign: 'middle'
                    },
                    {
                        title: '杉木',
                        field: 'shanSurplus',
                        //width:'auto',
                        align: 'center',
                        width:200,
                        valign: 'middle'
                    },
                    {
                        title: '松木',
                        field: 'songSurplus',
                        //width:'auto',
                        align: 'center',
                        width:200,
                        valign: 'middle'
                    },
                    {
                        title: '杂木',
                        field: 'zaSurplus',
                        //width:'auto',
                        align: 'center',
                        width:200,
                        valign: 'middle'
                    },
                    {
                        title: '合计',
                        field: 'totalImplement',
                        //width:'auto',
                        align: 'center',
                        width:200,
                        valign: 'middle'
                    },
                    {
                        title: '杉木',
                        field: 'shanTreeImplement',
                        //width:'auto',
                        align: 'center',
                        width:200,
                        valign: 'middle'
                    },
                    {
                        title: '松木',
                        field: 'songTreeImplement',
                        //width:'auto',
                        align: 'center',
                        width:200,
                        valign: 'middle'
                    },
                    {
                        title: '杂木',
                        field: 'zaiTreeImplement',
                        //width:'auto',
                        align: 'center',
                        width:200,
                        valign: 'middle'
                    },
                    {
                        title: '下载合同',
                        field: 'accessory',
                        //width:'auto',
                        align: 'center',
                        width:200,
                        valign: 'middle',
                        formatter: function (cellval, row) {
                        	var d='<a href="DownfileServlet?action=projectfile12&filename=\''+ row.accessory + '\'">下载</a>';
                            //var  d = '<a href="cutnumServlet?action=watch&cutnum=\''+ row.cutnum + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                            return  d;
                        }
                    },
                ]
            ],

          //注册加载子表的事件。注意下这里的三个参数！

            onExpandRow: function (index, row, $detail) {

            	InitSubTable(index, row, $detail);
            }

        });

    };

  //初始化子表格(无线循环)

    InitSubTable = function (index, row, $detail) {
        var parentid = row.cutnum;

        var cur_table = $detail.html('<table></table>').find('table');

        $(cur_table).bootstrapTable({

            url: 'workpageSevrlet?action=cutwworkid',
            method: 'get',
            contentType: 'application/json;charset=UTF-8',//这里我就加了个utf-8
            dataType: 'json',
            //queryParams: { id: parentid },
            ajaxOptions: { id: parentid },
            clickToSelect: true,
            //height: 500,
            //detailView: false,//父子表
            uniqueId: "workid",
            pageSize: 10,
            pageList: [10, 25],
            search:true, //显示搜索框
            showColumns: true,                  //是否显示所有的列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            showRefresh: true,                  //是否显示刷新按钮
            contentType: "application/x-www-form-urlencoded",
            detailView: true,                   //是否显示父子表
            exportDataType:'all',//'basic':当前页的数据, 'all':全部的数据, 'selected':选中的数据    
            showExport: true,  //是否显示导出按钮    
            buttonsAlign:"right",  //按钮位置    
            exportTypes:['excel','xlsx','csv','pdf'],
            exportOptions:{
                // ignoreColumn: [0,1],  //忽略某一列的索引
                fileName: "野战台账",  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: "野战台账",
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight'], //设置格式
            },
            queryParams:function queryParams(params){
                var temp = {
						//action:"passall2",
						timeStart:document.getElementById("timeStart").value,
						timeEnd:document.getElementById("timeEnd").value,
						yard:document.getElementById("yard").value,
						treetype:document.getElementById("treetype").value,
						tradius:document.getElementById("tradius").value,
						tlong:document.getElementById("tlong").value,
						id: parentid
						
				};     
                return temp;
            },
            columns: [
             	{						
						title: '序号',
						width: 100,
                        align: 'center',
						formatter: function (value, row, index) {
							return index+1;
						}
	                },//该列显示序号，分页不是从1开始
                {
                    title: "工单号",
                    field: 'workid',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '工程包',
                    field: 'proj_package_Name',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐证编号',
                    field: 'cutnum',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '发货单位',
                    field: 'company',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '货场',
                    field: 'yard',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '分区',
                    field: 'section',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '合计根数',
                    field: 'toltree',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '合计材积',
                    field: 'tolstere',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '检尺野账',
                    field: 'opr',
                    width: 200,
                    align: 'center',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<a href="workpageSevrlet?action=jianchiYzhang&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">打印</button></a>';
                        //var  d = '<a href="workpageSevrlet?action=alldelete&workid=\''+ row.workid + '\'"><button  id="id="delete" data-id="98" class="btn btn-xs btn-primary">删除</button></a>';
                        return  d;
                    }
                },
            ],

            //无线循环取子表，直到子表里面没有记录

//             onExpandRow: function (index, row, $Subdetail) {

//                 oInit.InitSubTable(index, row, $Subdetail);
//             }
            onExpandRow: function (index, row, $detail) {

            	InitSubTable2(index, row, $detail);
            }

        });

    };
    
      InitSubTable2 = function (index, row, $detail) {

        var parentid = row.workid;

        var cur2_table = $detail.html('<table></table>').find('table');

        $(cur2_table).bootstrapTable({
            url: 'workpageSevrlet?action=cuttreeWw',
            method: 'get',
            contentType: 'application/json;charset=UTF-8',//这里我就加了个utf-8
            dataType: 'json',
            //queryParams: { id: parentid },
            ajaxOptions: { id: parentid },
            clickToSelect: true,
            //height: 500,
            detailView: false,//父子表
            uniqueId: "workid",
            pageSize: 10,
            pageList: [10, 25],
            search:true, //显示搜索框
            showColumns: true,                  //是否显示所有的列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            showRefresh: true,                  //是否显示刷新按钮
            contentType: "application/x-www-form-urlencoded",
            //detailView: true,                   //是否显示父子表
            exportDataType:'all',//'basic':当前页的数据, 'all':全部的数据, 'selected':选中的数据    
            showExport: true,  //是否显示导出按钮    
            buttonsAlign:"right",  //按钮位置    
            exportTypes:['excel','xlsx','csv','pdf'],
            exportOptions:{
                // ignoreColumn: [0,1],  //忽略某一列的索引
                fileName: "野战台账",  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: "野战台账",
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight'], //设置格式
            },
            queryParams:function queryParams(params){
                var temp = {
						//action:"passall2",
						yard:document.getElementById("yard").value,
						treetype:document.getElementById("treetype").value,
						tradius:document.getElementById("tradius").value,
						tlong:document.getElementById("tlong").value,
						id:parentid
				};     
                return temp;
            },
            columns: [
             	{						
						title: '序号',
						width: 100,
                        align: 'center',
						formatter: function (value, row, index) {
							return index+1;
						}
	                },//该列显示序号，分页不是从1开始
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
                    title: '检尺长',
                    field: 'tlong',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '检尺径',
                    field: 'tradius',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '根数',
                    field: 'num',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '材积',
                    field: 'tvolume',
                    align: 'center',
                    valign: 'middle'
                },
            ],

            //无线循环取子表，直到子表里面没有记录

            onExpandRow: function (index, row, $Subdetail) {
                InitSubTable(index, row, $Subdetail);

            }

        });

    };

//     return oTableInit;

// };
// var ButtonInit = function () {

//     var oInit = new Object();

//     var postdata = {};

//     oInit.Init = function () {

//         //初始化页面上面的按钮事件
//     };

//     return oInit;

// };

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
    <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>施工方管理</span> <span class="label label-important">2</span></a>
     <ul>
        <li><a href="managesdatecard.jsp">施工方资料卡</a></li>
        <li><a href="managersdatecardSee.jsp">施工方台账</a></li>
      </ul>
     </li>
     <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>工程包管理</span> <span class="label label-important">4</span></a>
     <ul>
        <li><a href="CutnumProjectpackage.jsp">创建工程包</a></li>
        <li><a href="cutnumProjectpackageShenhe.jsp">审核工程包</a></li>
        <li><a href="CutnumProjectpackageTable.jsp">工程包执行情况</a></li>
      </ul>
     </li>
     <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>野账管理</span> <span class="label label-important">3</span></a>
       <ul>
      
        <li><a href="workpageAdd.jsp">野账录入</a></li>
        <li><a href="workpageShenheFaqu.jsp">野账审核</a></li>
          <li><a href="treeinYezhang.jsp"> <span>野帐打印</span></a> </li>
      </ul>
     </li>
     <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>伐区管理</span> <span class="label label-important">2</span></a>
       <ul>
       <li><a href="cutareaAllot.jsp">伐区拨交</a></li>
        <li><a href="manageCutnumCheck.jsp">伐区管理</a></li>
      </ul>
     </li>
    <li class="submenu"> <a href="#"><i class="icon"></i> <span>生产结算和报表</span><span class="label label-important">6</span></a>
       <ul>
        <li><a href="productPrice.jsp">生产工资和其他费用</a></li>
        <li><a href="productPriceTable.jsp">生产其他费用台账</a></li>
        <li><a href="productPrice2.jsp">生产工资结算</a></li>
        <li><a href="productPrice23Table.jsp">生产工资结算台账</a></li>
        <li><a href="productTreePrice.jsp">木材销售货款结算</a></li>
        <li><a href="productTreePriceTable.jsp">木材销售货款台账</a></li>
      </ul>
     </li>
     <li><a href="manageCutnumProduced.jsp"><i class="icon icon-inbox"></i> <span>录入已生产量</span></a></li>
     <li class="action"><a href="produceCutWorkidTable.jsp"><i class="icon icon-inbox"></i><span>生产总台账</span></a></li>       
  </ul>
</div>
<!--sidebar-menu-->

<!--main-container-part-->
<div id="content"> 
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"> <a href="forestP.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a></div>
  </div>

        <div class="find-top1">
           <div class="find-top">
           <p class="p-tail"><i class="i-tail"></i> 生产台账界面</p>
          </div>
           <div class="find-top1">
            <table class="bar1">
           <tr>
           <td style="font-size:22px;color:red">查询时间：</td>
           <td colspan="4" ><span>时&nbsp;&nbsp;&nbsp;&nbsp;间：&nbsp;&nbsp;&nbsp;&nbsp;</span><input style="width:230px" type="date" name="timeStart" id="timeStart"  value="2020-01-01" oninput='cutWorkid()' onclick='locationInput'>
           <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><input style="width:230px" type="date" name="timeEnd" id="timeEnd" value="2020-12-01" oninput='cutWorkid()' onclick='locationInput'></td>
           </tr>
           
           <tr>
           <td style="font-size:22px;color:red">采伐证信息：</td>
           <td>工程包：&nbsp;&nbsp;&nbsp;&nbsp;<input  width="140px" type="text" name="projectPackageName" id="projectPackageName" oninput='cutWorkid()' onclick='locationInput'></td>
           <td>施工方：<input  width="160" type="text" name="contractionSide" id="contractionSide" oninput='cutWorkid()' onclick='locationInput'></td>
           <td colspan="2">采伐证编号：<input  width="160" type="text" name="cutnum" id="cutnum" oninput='cutWorkid()' onclick='locationInput'></td>
           </tr>
           
           <tr>
           <td style="font-size:22px;color:red">查询树材信息：</td>
           <td>
            <span>货&nbsp;&nbsp;&nbsp;&nbsp;场：&nbsp;&nbsp;&nbsp;&nbsp;</span><select  style="width:230px" name="yard" id="yard" oninput='cur_table()' onclick='locationInput'><option selected="selected"></option></select></td>
           <td>
            <span>&nbsp;&nbsp;&nbsp;&nbsp;树&nbsp;&nbsp;种：&nbsp;&nbsp;</span><select style="width:230px" name="treetype" id="treetype" oninput='cutWorkid()' onclick='locationInput'>
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
            <span>&nbsp;&nbsp;&nbsp;&nbsp;检尺长：&nbsp;&nbsp;&nbsp;</span><input  type="text" name="tlong" id="tlong" oninput='cutWorkid()' onclick='locationInput'></td>
           <td>
            <span>检尺径：</span><input  type="text" name="tradius" id="tradius" oninput='cutWorkid()' onclick='locationInput'></td>
           </tr>
<!--            <tr> -->
<!--            <td colspan="8" style="text-align: center"><button  onclick="cutWorkid()">查询进仓总库存</button> -->
<!--            </tr> -->
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