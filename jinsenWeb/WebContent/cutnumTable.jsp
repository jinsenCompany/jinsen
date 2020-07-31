<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>查询采伐证号</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
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
</script>
<script type="text/javascript">
    function table1(){
    	$('#table1').bootstrapTable('destroy');
        $('#table1').bootstrapTable({
            method: "get",
            striped: true,
            singleSelect: false,
            url: "cutnumServlet?action=findCutnumF",
            data:{},
            dataType: "json",
            pagination: true, //分页
            pageSize: 3,
            pageNumber: 1,
            search: true, //显示搜索框
            contentType: "application/x-www-form-urlencoded",
            showColumns: true,                  //是否显示所有的列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            showRefresh: true,
            showExport: true,  //是否显示导出按钮    
            buttonsAlign:"right",  //按钮位置    
            exportTypes:['excel','xlsx','csv','pdf'],  //导出文件类型，[ 'csv', 'txt', 'sql', 'doc', 'excel', 'xlsx', 'pdf']
            exportOptions:{
                // ignoreColumn: [0,1],  //忽略某一列的索引
                fileName: "采伐证信息表",  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: "采伐证信息表",
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight'], //设置格式
            },
            showRefresh: true,                      //是否显示刷新按钮
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            //detailView: true,
            queryParams:function queryParams(params){
                var temp = {
						//action:"findCutnumF",
						year:document.getElementById("sa_year").value,
						month:document.getElementById("sa_month").value
				};     
                return temp;
            },
            columns: [
            	[
                    {
                        "title": "采伐证信息表",
                        "font-size":"100px",
                        "halign":"center",
                        "align":"center",
                        "valign": "middle",
                        "colspan": 26
                    }
                ],
            	[ 
                {
                    title: "序号",
                    field: 'cutnumid',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐证号',
                    field: 'cutnum',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐证编号',
                    field: 'certificatenum',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '编号',
                    field: 'number',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '公司',
                    field: 'company',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐地点',
                    field: 'cutsite',
                    align: 'center'
                },
                {
                    title: '林分四至',
                    field: 'sizhi',
                    align: 'center'
                },
                {
                    title: 'gps信息',
                    field: 'gpsinfo',
                    align: 'center'
                },
                {
                    title: '林分起源',
                    field: 'treeorigin',
                    align: 'center'
                },
                {
                    title: '林种',
                    field: 'foresttype',
                    align: 'center'
                },
                {
                    title: '树种',
                    field: 'treetype',
                    align: 'center'
                },
                {
                    title: '权属',
                    field: 'ownership',
                    align: 'center'
                },
                {
                    title: '林权证号',
                    field: 'forestid',
                    align: 'center'
                },
                {
                    title: '采伐类型',
                    field: 'cuttype',
                    align: 'center'
                },
                {
                    title: '采伐方式',
                    field: 'cutmethod',
                    align: 'center'
                },
                {
                    title: '采伐强度',
                    field: 'cutqiang',
                    align: 'center'
                },
                {
                    title: '采伐面积',
                    field: 'cutarea',
                    align: 'center'
                },
                {
                    title: '公顷（株数）',
                    field: 'treenum',
                    align: 'center'
                },
                {
                    title: '采伐储积',
                    field: 'cutstore',
                    align: 'center'
                },
                {
                    title: '立方米（出材量）',
                    field: 'volume',
                    align: 'center'
                },
                {
                    title: '采伐日期（起）',
                    field: 'starttime',
                    align: 'center'
                },
                {
                    title: '采伐日期（终）',
                    field: 'endtime',
                    align: 'center'
                },
                {
                    title: '政领人',
                    field: 'certifier',
                    align: 'center'
                },
                {
                    title: '更新期限',
                    field: 'updatedate',
                    align: 'center'
                },
                {
                    title: '更新面积',
                    field: 'updatevolume',
                    align: 'center'
                },
                {
                    title: '更新数量',
                    field: 'updatenum',
                    align: 'center'
                },
           ]     
            ]
        });
    }
    </script>
   </head>

    
<body onload="submit_year()">
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
    <li ><a href="cutnumApplysee.jsp"><i class="icon icon-home"></i> <span>审核采伐证申请</span></a> </li>
    <li> <a href="manageCutnum.jsp"><i class="icon icon-inbox"></i> <span>录入采伐证材料</span></a></li>
    <li> <a href="manageCutnumsee.jsp"><i class="icon icon-th"></i> <span>查看采伐证材料</span></a></li>
    <li> <a href="cutnumTable.jsp"><i class="icon icon-th-list"></i> <span>采伐证报表</span></a></li>
  </ul>
</div>
<!--sidebar-menu-->
<div id="content">
  <div id="content-header">
    <div id="breadcrumb"> <a href="superManage.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>返回超级管理员首页</a></div>
  </div>
<main class="all">
        <div class="find-top1">
           <div class="find-top">
           <p class="p-tail"><i class="i-tail"></i> 该页面是查询采伐证页面</p>
          </div>
           <div class="find-top1">
            <table class="top-table">
           <tr><td class="top-table-label">年份：</td><td><select id="sa_year" name="sa_year" onChange="change_year()" readonly unselectable="on"></select></tr>
           <tr><td class="top-table-label">月份：</td><td><select id="sa_month" name="sa_month"><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option><option>10</option><option>11</option><option>12</option><option>all</option><option>1-3</option><option>4-6</option><option>7-9</option><option>10-12</option></select></td></tr>
           <tr><td colspan="26" style="text-align: center"><button class="add-but" onclick="table1()"><i class="glyphicon glyphicon-edit"></i>查询采伐证信息</button></td></tr>
            </table>
           </div>
           <div class="table-con">
        <table id="table1" class="table-style"></table>
    </div>
          
        </div> 
</main>
</div>
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

</body>
</html>