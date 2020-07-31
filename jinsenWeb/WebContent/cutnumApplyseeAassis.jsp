<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>管理部门经理查看申请采伐证材料</title>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/tableall.css">
    <link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/fullcalendar.css" />
<link rel="stylesheet" href="css/matrix-style.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="css/jquery.gritter.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
        <style>

    </style>  
    <style>
/*定义类名为.thead-blue的样式*/
.table .thead-blue th {
color: #fff;
background-color: #3195f1;
border-color: #0d7adf;
}
</style>
<script src="js/jquery.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript">
    $(function(){
    	table1();
    })
    function table1()
    {
    	$('#table1').bootstrapTable('destroy');
    	$('#table1').bootstrapTable({
            method: "post",
            striped: false,
            pagination:true,   //是否分页
            singleSelect: false,
            cache: false,//缓存
            url: "cutnumServlet?action=seecutnumApply&type=notshenhe",
            dataType: "json",
            pagination: true, //分页
            pageSize: 10,
            pageNumber: 1,
            search:true, //显示搜索框
            showColumns: true,                  //是否显示所有的列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            showRefresh: true,                  //是否显示刷新按钮
            contentType: "application/x-www-form-urlencoded",
            exportDataType:'all',//'basic':当前页的数据, 'all':全部的数据, 'selected':选中的数据   
            showExport: true,  //是否显示导出按钮    
            //buttonsAlign:"right",  //按钮位置    
            exportTypes:['excel','xls','xlsx','csv','pdf'],  //导出文件类型，[ 'csv', 'txt', 'sql', 'doc', 'excel', 'xlsx', 'pdf']
            exportOptions:{
                // ignoreColumn: [0,1],  //忽略某一列的索引
                fileName: "未审核",  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: "未审核",
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight'], //设置格式
            },
            columns: [
            	{
                    checkbox: "true",
                    field: 'check',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: "序号",
                    field: 'applyid',
                    align: 'center',
                    valign: 'middle'
                },
                {
                	title: "申请时间",
                    field: 'applaydate',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '设计采伐证号',
                    field: 'designum',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐地点',
                    field: 'cutaddress',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '树种',
                    field: 'treetype',
                    align: 'center'
                },
                {
                    title: '采伐蓄积',
                    field: 'cutvolume',
                    align: 'center'
                },
                {
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<a href="cutnumServlet?action=seeallapply&applyid=\''+ row.applyid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button>未审核</a>';
                        //var  d = '<a href="workpageSevrlet?action=alldelete&workid=\''+ row.workid + '\'"><button  id="id="delete" data-id="98" class="btn btn-xs btn-primary">删除</button></a>';
                        return  d;
                    }
                },
            ]
        });
    }
    </script>
    <script type="text/javascript">
    function month()
    {
    	$('#myModal_month_year').modal('hide');
    	$('#table1').bootstrapTable('destroy');
    	$('#table1').bootstrapTable({
            method: "post",
            striped: false,
            singleSelect: false,
            cache: false,//缓存
            url: "cutnumServlet?action=seecutnumApply&type=all",
            dataType: "json",
            pagination: true, //分页
            pageSize: 10,
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
            exportTypes:['excel','xlsx','csv'],  //导出文件类型，[ 'csv', 'txt', 'sql', 'doc', 'excel', 'xlsx', 'pdf']
            exportOptions:{
                // ignoreColumn: [0,1],  //忽略某一列的索引
                fileName: "全部申请",  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: "全部申请",
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight'], //设置格式
            },
            columns: [
            	{
                    checkbox: "true",
                    field: 'check',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: "序号",
                    field: 'applyid',
                    align: 'center',
                    valign: 'middle'
                },
                {
                	title: "申请时间",
                    field: 'applaydate',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '设计采伐证号',
                    field: 'designum',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐地点',
                    field: 'cutaddress',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '树种',
                    field: 'treetype',
                    align: 'center'
                },
                {
                    title: '采伐蓄积',
                    field: 'cutvolume',
                    align: 'center'
                },
                {
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<a href="cutnumServlet?action=seeallapplyPass&applyid=\''+ row.applyid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button>全部申请</a>';
                        //var  d = '<a href="workpageSevrlet?action=alldelete&workid=\''+ row.workid + '\'"><button  id="id="delete" data-id="98" class="btn btn-xs btn-primary">删除</button></a>';
                        return  d;
                    }
                },
            ]
        });
    }
    </script>
    <script type="text/javascript">
    $(function(){
    	year();
    })
    function year()
    {
    	$('#myModal_statement').modal('hide');
    	$('#table1').bootstrapTable('destroy');
    	$('#table1').bootstrapTable({
            method: "post",
            striped: false,
            pagination:true,   //是否分页
            singleSelect: false,
            cache: false,//缓存
            url: "cutnumServlet?action=seecutnumApply&type=notshenhe",
            dataType: "json",
            pagination: true, //分页
            pageSize: 10,
            pageNumber: 1,
            search:true, //显示搜索框
            showColumns: true,                  //是否显示所有的列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            showRefresh: true,                  //是否显示刷新按钮
            contentType: "application/x-www-form-urlencoded",
            exportDataType:'all',//'basic':当前页的数据, 'all':全部的数据, 'selected':选中的数据   
            showExport: true,  //是否显示导出按钮    
            //buttonsAlign:"right",  //按钮位置    
            exportTypes:['excel','xls','xlsx','csv','pdf'],  //导出文件类型，[ 'csv', 'txt', 'sql', 'doc', 'excel', 'xlsx', 'pdf']
            exportOptions:{
                // ignoreColumn: [0,1],  //忽略某一列的索引
                fileName: "未审核",  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: "未审核",
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight'], //设置格式
            },
            columns: [
            	{
                    checkbox: "true",
                    field: 'check',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: "序号",
                    field: 'applyid',
                    align: 'center',
                    valign: 'middle'
                },
                {
                	title: "申请时间",
                    field: 'applaydate',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '设计采伐证号',
                    field: 'designum',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐地点',
                    field: 'cutaddress',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '树种',
                    field: 'treetype',
                    align: 'center'
                },
                {
                    title: '采伐蓄积',
                    field: 'cutvolume',
                    align: 'center'
                },
                {
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<a href="cutnumServlet?action=seeallapply&applyid=\''+ row.applyid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button>未审核</a>';
                        //var  d = '<a href="workpageSevrlet?action=alldelete&workid=\''+ row.workid + '\'"><button  id="id="delete" data-id="98" class="btn btn-xs btn-primary">删除</button></a>';
                        return  d;
                    }
                },
            ]
        });
    }
    </script>
    
<script type="text/javascript">
    function treePrice()
    {
    	$('#myModal_salary_tree').modal('hide');
    	$('#table1').bootstrapTable('destroy');
    	$('#table1').bootstrapTable({
    	    theadClasses: "thead-blue",//设置thead-blue为表头样式
    	    classes: "table table-bordered table-striped table-sm table-dark", 
            method: "post",
            striped: false,
            singleSelect: false,
            cache: false,//缓存
            url: "cutnumServlet?action=seecutnumApply&type=pass",
            dataType: "json",
            pagination: true, //分页
            pageSize: 10,
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
            exportTypes:['excel','xlsx','csv'],  //导出文件类型，[ 'csv', 'txt', 'sql', 'doc', 'excel', 'xlsx', 'pdf']
            exportOptions:{
                // ignoreColumn: [0,1],  //忽略某一列的索引
                fileName: "已通过",  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: "已通过",
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight'], //设置格式
            },
            columns: [
            	{
                    checkbox: "true",
                    field: 'check',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: "序号",
                    field: 'applyid',
                    align: 'center',
                    valign: 'middle'
                },
                {
                	title: "申请时间",
                    field: 'applaydate',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '设计采伐证号',
                    field: 'designum',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐地点',
                    field: 'cutaddress',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '树种',
                    field: 'treetype',
                    align: 'center'
                },
                {
                    title: '采伐蓄积',
                    field: 'cutvolume',
                    align: 'center'
                },
                {
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<a href="cutnumServlet?action=seeallapplyPass&applyid=\''+ row.applyid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button>已通过</a>';
                        //var  d = '<a href="workpageSevrlet?action=alldelete&workid=\''+ row.workid + '\'"><button  id="id="delete" data-id="98" class="btn btn-xs btn-primary">删除</button></a>';
                        return  d;
                    }
                },
            ]
        });
    }
    </script>
    <script type="text/javascript">
    function produce()
    {
    	$('#myModal_produce').modal('hide');
    	$('#table1').bootstrapTable('destroy');
    	$('#table1').bootstrapTable({
            method: "post",
            striped: false,
            singleSelect: false,
            cache: false,//缓存
            url: "cutnumServlet?action=seecutnumApply&type=supplynot",
            dataType: "json",
            pagination: true, //分页
            pageSize: 10,
            pageNumber: 1,
            search:true, //显示搜索框
            showColumns: true,                  //是否显示所有的列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            showRefresh: true,                  //是否显示刷新按钮
            contentType: "application/x-www-form-urlencoded",
            exportDataType:'all',//'basic':当前页的数据, 'all':全部的数据, 'selected':选中的数据    
            showExport: true,  //是否显示导出按钮    
            //buttonsAlign:"right",  //按钮位置    
            exportTypes:['excel','xlsx','csv'],  //导出文件类型，[ 'csv', 'txt', 'sql', 'doc', 'excel', 'xlsx', 'pdf']
            exportOptions:{
                // ignoreColumn: [0,1],  //忽略某一列的索引
                fileName: "未通过",  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: "未通过",
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight'], //设置格式
            },
            columns: [
            	{
                    checkbox: "true",
                    field: 'check',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: "序号",
                    field: 'applyid',
                    align: 'center',
                    valign: 'middle'
                },
                {
                	title: "申请时间",
                    field: 'applaydate',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '设计采伐证号',
                    field: 'designum',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐地点',
                    field: 'cutaddress',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '树种',
                    field: 'treetype',
                    align: 'center'
                },
                {
                    title: '采伐蓄积',
                    field: 'cutvolume',
                    align: 'center'
                },
                {
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<a href="cutnumServlet?action=seeallapplyBu&applyid=\''+ row.applyid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button>未通过</a>';
                        //var  d = '<a href="workpageSevrlet?action=alldelete&workid=\''+ row.workid + '\'"><button  id="id="delete" data-id="98" class="btn btn-xs btn-primary">删除</button></a>';
                        return  d;
                    }
                },
            ]
        });
    }
    </script>
 <script type="text/javascript">
    function probuchong()
    {
    	$('#myModal_buchong').modal('hide');
    	$('#table1').bootstrapTable('destroy');
    	$('#table1').bootstrapTable({
            method: "post",
            striped: false,
            singleSelect: false,
            cache: false,//缓存
            url: "cutnumServlet?action=seecutnumApply&type=notpass",
            dataType: "json",
            pagination: true, //分页
            pageSize: 10,
            pageNumber: 1,
            search:true, //显示搜索框
            showColumns: true,                  //是否显示所有的列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            showRefresh: true,                  //是否显示刷新按钮
            contentType: "application/x-www-form-urlencoded",
            exportDataType:'all',//'basic':当前页的数据, 'all':全部的数据, 'selected':选中的数据    
            showExport: true,  //是否显示导出按钮    
            //buttonsAlign:"right",  //按钮位置    
            exportTypes:['excel','xlsx','csv'],  //导出文件类型，[ 'csv', 'txt', 'sql', 'doc', 'excel', 'xlsx', 'pdf']
            exportOptions:{
                // ignoreColumn: [0,1],  //忽略某一列的索引
                fileName: "审核补充材料申请书",  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: "审核充材料申请书",
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight'], //设置格式
            },
            columns: [
            	{
                    checkbox: "true",
                    field: 'check',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: "序号",
                    field: 'applyid',
                    align: 'center',
                    valign: 'middle'
                },
                {
                	title: "申请时间",
                    field: 'applaydate',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '设计采伐证号',
                    field: 'designum',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐地点',
                    field: 'cutaddress',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '树种',
                    field: 'treetype',
                    align: 'center'
                },
                {
                    title: '采伐蓄积',
                    field: 'cutvolume',
                    align: 'center'
                },
                {
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<a href="cutnumServlet?action=seeallapplyBu&applyid=\''+ row.applyid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button>审核补充材料申请书</a>';
                        //var  d = '<a href="workpageSevrlet?action=alldelete&workid=\''+ row.workid + '\'"><button  id="id="delete" data-id="98" class="btn btn-xs btn-primary">删除</button></a>';
                        return  d;
                    }
                },
            ]
        });
    }
    </script>   
</head> 
<body>
<div id="header">
  <h1><a href="dashboard.html">管理部门平台</a></h1>
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
    <li ><a href="cutnumApplyseeAassis.jsp"><i class="icon icon-home"></i> <span>审核采伐证申请</span></a> </li>
    <li> <a href="manageCutnumAssis.jsp"><i class="icon icon-inbox"></i> <span>录入采伐证材料</span></a></li>
    <li> <a href="manageCutnumseeAssis.jsp"><i class="icon icon-th"></i> <span>查看采伐证材料</span></a></li>
    <li> <a href="cutnumTableAssis.jsp"><i class="icon icon-th-list"></i> <span>采伐证报表</span></a></li>
    <li> <a href="manageCutnumshenhe.jsp"><i class="icon icon-th-list"></i> <span>审核采伐证材料</span></a></li>
  </ul>
</div>
<!--sidebar-menu-->

<!--main-container-part-->
<div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"> <a href="hello2.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
  </div>
<main>
  <div class="find-top1" style="vertical-align:middle">
      <table style="border:0px;align:center;valign:middle" >
		   <tr><td colspan="6" style="text-align: center;vertical-align:middle">
		   <button class="add-but" data-toggle="modal" id="myModal_month_year" onclick="month()">全部申请</button>
		   <button class="add-but" data-toggle="modal" id="myModal_statement" onclick="year()">未审核</button>
		   <button class="add-but" data-toggle="modal" id="myModal_salary_tree" onclick="treePrice()">已通过</button> 
		   <button class="add-but" data-toggle="modal" id="myModal_produce" onclick="produce()">未通过</button>
		   <button class="add-but" data-toggle="modal" id="myModal_buchong" onclick="probuchong()">审核补充材料申请</button>
		   </td></tr>
      </table>
    </div>
    <div class="table-responsive"><!-- 斑马线表格 -->
          <!--<table id="table1" class="table table-striped"></table>-->
         <table id="table1" class="table table-bordered"></table>
         <!--<table id="table1" class="table-condensed"></table>-->
         <!--<table id="table1" class="table-hover"></table>-->

    </div>
</main>
</div>
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
</body>
</html>