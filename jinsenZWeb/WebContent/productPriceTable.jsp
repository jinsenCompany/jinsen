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
<title>生产工资和其他费用汇总</title>
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
.bar1 {background: #A3D0C3;margin-left:auto; margin-right:auto;padding:10px;border-collapse:collapse}
        .bar1 input,select {
            border: 2px solid #7BA7AB;
            border-radius: 5px;
            background: #F9F0DA;
            color: #9E9C9C;
        }
        .bar1 button {
            top: 0;
            right: 0;
            background: #7BA7AB;
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
    $(function(){
    	producetree();
    })
    function producetree()
    {
    	//$('#myModal_monthtree').modal('hide');
    	$('#table1').bootstrapTable('destroy');
    	$('#table1').bootstrapTable({
            method: "post",
            striped: false,
            singleSelect: false,
            cache: false,//缓存
            url: "salaryServlet",
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
                fileName: "生产工资和其他费用汇总",  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: "生产工资和其他费用汇总",
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight'], //设置格式
            },
            queryParams:function queryParams(params){
                var temp = {
						action:"producetreetable",
						timeStart:document.getElementById("timeStart").value,
						timeEnd:document.getElementById("timeEnd").value,
						projectPackageName:document.getElementById("projectPackageName").value,
						cutnum:document.getElementById("cutnum").value,
						section:document.getElementById("section").value,
						yard:document.getElementById("yard").value
				};     
                return temp;
            },
            columns: [
            	[
                    {
                        "title": "生产工资和其他费用汇总",
                        "font-size":"100px",
                        "halign":"center",
                        "align":"center",
                        "valign": "middle",
                        "colspan": 15
                    }
                ],
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
                    title: "工程包",
                    field: 'projectPackageName',
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
                  	title: '开始时间',
                   field: 'timeStart',
                   align: 'center',
                   valign: 'middle'
                 },
                 {
                	 title:'结束时间',
                	 field: 'timeEnd',
                     align: 'center',
                     valign: 'middle'
                 },
                 {
                     title: '劳务承包人',
                     width: 180,
                     field: 'forperson',
                     align: 'center',
                     valign: 'middle'
                 },
                 {
                     title: '伐区管理单位',
                     width: 180,
                     field: 'manageUnit',
                     align: 'center',
                     valign: 'middle'
                 },
                 {
                	 title:'总金额',
                	 field: 'totalAll',
                     align: 'center',
                     valign: 'middle'
                 },
                 {
                	 title:'货场',
                	 field: 'yard',
                     align: 'center',
                     valign: 'middle'
                 },
                 {
                	 title:'货场分区',
                	 field: 'section',
                     align: 'center',
                     valign: 'middle'
                 },
                 {
                	 title:'创建时间',
                	 field: 'creatTime',
                     align: 'center',
                     valign: 'middle'
                 },
                 {
                     title: '查看',
                     field: 'opr',
                     width: 100,
                     align: 'center',
                     formatter: function (cellval, row) {
                         //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                         var  d = '<a href="salaryServlet?action=seeProductPriceT&creatTime=\''+ row.creatTime + '\'&projectPackageName=\''+ row.projectPackageName + '\'&cutnum=\''+ row.cutnum + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                         //var  d = '<a href="workpageSevrlet?action=alldelete&workid=\''+ row.workid + '\'"><button  id="id="delete" data-id="98" class="btn btn-xs btn-primary">删除</button></a>';
                         return  d;
                     }
                 },
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
        <li><a href="login.jsp"><i class="icon-key"></i> 注销</a></li>
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
    <li class=""><a title="" href="login.jsp"><i class="icon icon-share-alt"></i> <span class="text">注销</span></a></li>
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
     <li><a href="produceCutWorkidTable.jsp"><i class="icon icon-inbox"></i><span>生产总台账</span></a></li>       
  </ul>
</div>
<!--sidebar-menu-->
<div id="content">
  <div id="content-header">
    <div id="breadcrumb"> <a href="superManage.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>返回超级管理员首页</a></div>
  </div>

        <div class="find-top1">
           <div class="find-top">
           <p class="p-tail"><i class="i-tail"></i> 该页面是生产木材信息报表打印界面</p>
          </div>
           <div class="find-top1">
            <table class="bar1">
           <!--  <tr><td class="top-table-label">年份：</td><td><select id="sa_year" name="sa_year" onChange="change_year()" readonly unselectable="on"></select></tr>
           <tr><td class="top-table-label">月份：</td><td><select id="sa_month" name="sa_month"><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option><option>10</option><option>11</option><option>12</option><option>1-12</option><option>1-3</option><option>4-6</option><option>7-9</option><option>10-12</option></select></td></tr>-->
           <tr>
           <td width="200">选择开始日期：<input width="160" type="date" name="timeStart" id="timeStart"  value="2020-01-01" oninput='producetree()' onclick='locationInput'></td>
           <td width="200">选择结束日期：<input width="160" type="date" name="timeEnd" id="timeEnd" value="2020-12-01" oninput='producetree()' onclick='locationInput'></td>
           <td width="200"> 工程包名称：<input  type="text" name="projectPackageName" id="projectPackageName" oninput='producetree()' onclick='locationInput'></td>
           <td width="200">采伐证编号：<input  type="text" name="cutnum" id="cutnum" oninput='producetree()' onclick='locationInput'></td>
           <td width="200">货场：<select name="yard" id="yard" oninput='producetree()' onclick='locationInput'><option selected="selected"></option></select></td>
           <td width="200">货场分区：<select id="section" name="section"  oninput='producetree()' onclick='locationInput'>
                <option selected></option>
                <option>A区</option>
                <option>B区</option>
                <option>C区</option>
                <option>D区</option>
                <option>E区</option>
                <option>F区</option>
                <option>无固定分区</option>
                </select></td>
           </tr>
<!--            <tr> -->
<!--            <td colspan="8" style="text-align: center"><button  onclick="totaltree()">查询进仓木材总库存情况</button> -->
<!--            </td> -->
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