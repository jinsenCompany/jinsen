<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>信息中心审核进场工单</title>
<!-- Custom CSS -->
<link rel="stylesheet" href="css/registe.css"/>
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
		.btn{ 	
	font-family: "'微软雅黑','Helvetica Neue',Helvetica,Arial,sans-serif"; 	
	font-size: 13px!important; 	height: 30px; 	
	line-height: 18px!important; 	
	padding: 3px 18px; 	
	display: inline-block; 	vertical-align: middle; 	
	font-weight: normal; 	border-radius: 3px; 	
	margin: 0 8px 0 3px; 	
	border: 1px solid #3383da; 	
	color: #ffffff; 	
	background-color: #3383da; 	
	cursor: pointer; 
	}
</style>    
<script type="text/javascript">
function mypass(workid)
{
	$.ajax({
        url:"treeServlet",
        data:{
            "action":"mypass",
            "workid":workid,
        },
        type: "POST",
        dataType:"html",
        success: function (data) {
        	alert(data);
        }
    })
	}

function alldo()
{
    var mytype=document.getElementById("mytype").value;
    var group=[];
    if(mytype=="")
    	{
    	alert("请选择批量处理得方式");
    	}
    else
    {   
        if(mytype=="alldelete"){
        	var str=$("#table1").bootstrapTable('getSelections');
        	if(str.length<1)
        		{
        		alert("无选中记录");
        		}
        	else{
        	for(var i=0;i<str.length;i++)
        		{
        		var j=str[i];
        		group[i]=j.workid;
        		}
        	var mymap=JSON.stringify(group);
        	$.ajax({
                url:"treeServlet?action=writepage&type="+mytype,
                data:{
                    "action":mytype,
                    "workid":mymap,
                },
                type: "POST",
                dataType:"html",
                success: function (data) {
                	alert(data);
                }
            })
        	}
        }
        else if(mytype=="notpass")
        {
        	var str=$("#table1").bootstrapTable('getSelections');
        	if(str.length<1)
        		{
        		alert("无选中记录");
        		}
        	else{
        	for(var i=0;i<str.length;i++)
        		{
        		var j=str[i];
        		group[i]=j.workid;
        		}
        	var mymap=JSON.stringify(group);
        	$.ajax({
                url:"treeServlet?action=writepage&type="+mytype,
                data:{
                    "action":mytype,
                    "workid":mymap,
                },
                type: "POST",
                dataType:"html",
                success: function (data) {
                	alert(data);
                }
            })
        	}
        }
        else{
    	var param = { 
    	        url: "treeServlet?action=writepage&type="+mytype, 
    	       }
    	$('#table1').bootstrapTable('refresh',param);
        }
	}
}
</script>
</head>
<body>
<!--Header-part-->
<div id="header">
  <h1><a href="dashboard.html">产销部门平台</a></h1>
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
    <li><a href="workpageShenheinfo.jsp"><i class="icon icon-home"></i> <span>审核进场工单</span></a> </li>
    <li class="active"><a href="InyardShenheinfo.jsp"><i class="icon icon-home"></i> <span>审核木材数据</span></a> </li>
    <li><a href="compareTreeinfo.jsp"><i class="icon icon-home"></i><span>审核装车照片</span></a></li>
  </ul>
</div>
<!--sidebar-menu-->

<!--main-container-part-->
<div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"> <a href="hello2.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
  </div>
<main class="all">
    <article class="artlce">
    <div>
    <div class="find-top">
        <p class="p-tail"><i class="i-tail"></i> 请选择你要所要进行的操作。</p>
    </div>
    <!--  <div class="find-top">
        <select style="width: 100px" id="mytype">
            <option value="">---请选择---</option>
            <option value="notpass">未通过</option>
            <option value="didpass">已通过</option>
            <option value="notshenhe">未审核</option>
            <option value="alldelete">删除</option>
        </select>
        <button class="add-but" onclick="alldo()"><i class="glyphicon glyphicon-edit"></i>确定</button>
    </div>-->
    <div class="find-top1" style="vertical-align:middle">
      <table style="border:0px;align:center;valign:middle" >
		   <tr><td colspan="6" style="text-align: center;vertical-align:middle">
		   <!--  <button class="add-but" data-toggle="modal" data-target="#myModal_all">全部申请</button>-->
		   <button class="btn" data-toggle="modal" id="myModal_notshenhe" onclick="notshenhe()">未审核</button>
		   <button class="btn" data-toggle="modal" id="myModal_pass" onclick="pass()">已通过</button> 
		   <button class="btn" data-toggle="modal" id="myModal_notpass" onclick="notpass()">未通过</button>
		   <button class="btn" data-toggle="modal" id="myModal_buchong" onclick="probuchong()">审核补充材料申请</button>
		   </td></tr>
      </table>
    </div>
    <div class="table-con">
    <div align="center"><h4 style="color:black;font-weight:bold">当前页面全部工单申请页面</h4></div>
        <table id="table1" class="table-style"></table>
    </div>
   </div> 
</article>
</main>
</div>
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
            method: "get",
            striped: true,
            theadClasses: "thead-blue",//设置thead-blue为表头样式
    	    classes: "table table-bordered table-striped table-sm table-dark", 
            singleSelect: false,
            url: "treeServlet?action=writepage&type=all",
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
                    title: '采伐证号',
                    field: 'cutNum',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '进场时间',
                    field: 'yarddate',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐地点',
                    field: 'cutSite',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '车牌号',
                    field: 'carNumber',
                    align: 'center'
                },
                {
                    title: '货场',
                    field: 'yard',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '检尺员',
                    field: 'surveyor',
                    align: 'center'
                },
                {
                    title: '货场分区',
                    field: 'section',
                    align: 'center'
                },
                {
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<a href="treeServlet?action=inyardinfo&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a><button type="button" data-id="98"  class="btn btn-xs btn-primary" onclick="overtimedelete(\''+ row.workid + '\')">删除</button> ';
                        //var  d = '<a href="workpageSevrlet?action=alldelete&workid=\''+ row.workid + '\'"><button  id="id="delete" data-id="98" class="btn btn-xs btn-primary">删除</button></a>';
                        return  d;
                    }
                },
            ]
        });
    }
    /*function overtimedelete(workid)
    {
    	$.ajax({
    		 url:"workpageSevrlet",
    		 type:"POST",
    		 data:{"action":"alldelete","workid":workid},
    	     dataType:"text",
    	     success:function (data)
         	{
         		if(data=="true")
         		{
         			alert("修改成功！");	
         			window.location.reload();
         		}
         		else
         		{
         			alert("查看失败");	  
         		}
         	},
          error:function(e){
         	 alert("请联系管理员！");	  
         }
    	});
    }*/
    
    function overtimedelete(workid)
    {
		$.ajax({
            url:"treeServlet",
            data:{
                "action":"alldelete1",
                "workid":workid,
            },
            type: "Post",
            dataType:"json",
            success: function (data) {
            	if(data)
            		{
            		alert("hh删除成功！");
            		$("#table1").bootstrapTable('refresh');
            		
            		}
            		
            	else
            		alert("hh删除失败！");
            }
        })
    }
    </script>
  <!--  <script type="text/javascript">
    function all()
    {
    	$('##myModal_all').modal('hide');
    	$('#table1').bootstrapTable('destroy');
    	$('#table1').bootstrapTable({
            method: "post",
            striped: false,
            singleSelect: false,
            cache: false,//缓存
            url: "workpageSevrlet?action=writepage&type=all",
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
                    title: "工单号",
                    field: 'workid',
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
                    title: '采伐地点',
                    field: 'cutsite',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '检尺员',
                    field: 'surveyor',
                    align: 'center'
                },
                {
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a><button type="button" data-id="98"  class="btn btn-xs btn-primary" onclick="overtimedelete(\''+ row.workid + '\')">删除</button> ';
                        //var  d = '<a href="workpageSevrlet?action=alldelete&workid=\''+ row.workid + '\'"><button  id="id="delete" data-id="98" class="btn btn-xs btn-primary">删除</button></a>';
                        return  d;
                    }
                },
            ]
        });
    }
    </script>-->
    <script type="text/javascript">
    function notshenhe()
    {
    	$('#myModal_notshenhe').modal('hide');
    	$('#table1').bootstrapTable('destroy');
    	$('#table1').bootstrapTable({
            method: "post",
            striped: false,
            theadClasses: "thead-blue",//设置thead-blue为表头样式
    	    classes: "table table-bordered table-striped table-sm table-dark", 
            pagination:true,   //是否分页
            singleSelect: false,
            cache: false,//缓存
            url: "treeServlet?action=writepage&type=notshenhe",
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
                    title: "工单号",
                    field: 'workid',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐证号',
                    field: 'cutNum',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '进场时间',
                    field: 'yarddate',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐地点',
                    field: 'cutSite',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '车牌号',
                    field: 'carNumber',
                    align: 'center'
                },
                {
                    title: '货场',
                    field: 'yard',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '检尺员',
                    field: 'surveyor',
                    align: 'center'
                },
                {
                    title: '货场分区',
                    field: 'section',
                    align: 'center'
                },
                {
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<a href="treeServlet?action=inyardinfo&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a><button type="button" data-id="98"  class="btn btn-xs btn-primary" onclick="overtimedelete(\''+ row.workid + '\')">删除</button> ';
                        //var  d = '<a href="workpageSevrlet?action=alldelete&workid=\''+ row.workid + '\'"><button  id="id="delete" data-id="98" class="btn btn-xs btn-primary">删除</button></a>';
                        return  d;
                    }
                },
            ]
        });
    }
    </script>
    
<script type="text/javascript">
    function pass()
    {
    	$('#myModal_pass').modal('hide');
    	$('#table1').bootstrapTable('destroy');
    	$('#table1').bootstrapTable({
    	    theadClasses: "thead-blue",//设置thead-blue为表头样式
    	    classes: "table table-bordered table-striped table-sm table-dark", 
            method: "post",
            striped: false,
            singleSelect: false,
            cache: false,//缓存
            url: "treeServlet?action=writepage&type=pass",
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
                    title: "工单号",
                    field: 'workid',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐证号',
                    field: 'cutNum',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '进场时间',
                    field: 'yarddate',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐地点',
                    field: 'cutSite',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '车牌号',
                    field: 'carNumber',
                    align: 'center'
                },
                {
                    title: '货场',
                    field: 'yard',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '检尺员',
                    field: 'surveyor',
                    align: 'center'
                },
                {
                    title: '货场分区',
                    field: 'section',
                    align: 'center'
                },
                {
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<a href="treeServlet?action=inyardtreePass&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a><button type="button" data-id="98"  class="btn btn-xs btn-primary" onclick="overtimedelete(\''+ row.workid + '\')">删除</button> ';
                        //var  d = '<a href="workpageSevrlet?action=alldelete&workid=\''+ row.workid + '\'"><button  id="id="delete" data-id="98" class="btn btn-xs btn-primary">删除</button></a>';
                        return  d;
                    }
                },
            ]
        });
    }
    </script>
    <script type="text/javascript">
    function notpass()
    {
    	$('#myModal_notpass').modal('hide');
    	$('#table1').bootstrapTable('destroy');
    	$('#table1').bootstrapTable({
            method: "post",
            striped: false,
            theadClasses: "thead-blue",//设置thead-blue为表头样式
    	    classes: "table table-bordered table-striped table-sm table-dark", 
            singleSelect: false,
            cache: false,//缓存
            url: "treeServlet?action=writepage&type=notpass",
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
                    title: "工单号",
                    field: 'workid',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐证号',
                    field: 'cutNum',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '进场时间',
                    field: 'yarddate',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐地点',
                    field: 'cutSite',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '车牌号',
                    field: 'carNumber',
                    align: 'center'
                },
                {
                    title: '货场',
                    field: 'yard',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '检尺员',
                    field: 'surveyor',
                    align: 'center'
                },
                {
                    title: '货场分区',
                    field: 'section',
                    align: 'center'
                },
                {
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<a href="treeServlet?action=inyardtreePass&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a><button type="button" data-id="98"  class="btn btn-xs btn-primary" onclick="overtimedelete(\''+ row.workid + '\')">删除</button> ';
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
            theadClasses: "thead-blue",//设置thead-blue为表头样式
    	    classes: "table table-bordered table-striped table-sm table-dark", 
            singleSelect: false,
            cache: false,//缓存
            url: "treeServlet?action=writepage&type=buchong",
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
                    title: "工单号",
                    field: 'workid',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐证号',
                    field: 'cutNum',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '进场时间',
                    field: 'yarddate',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐地点',
                    field: 'cutSite',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '车牌号',
                    field: 'carNumber',
                    align: 'center'
                },
                {
                    title: '货场',
                    field: 'yard',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '检尺员',
                    field: 'surveyor',
                    align: 'center'
                },
                {
                    title: '货场分区',
                    field: 'section',
                    align: 'center'
                },
                {
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<a href="treeServlet?action=inyardinfosee&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a><button type="button" data-id="98"  class="btn btn-xs btn-primary" onclick="overtimedelete(\''+ row.workid + '\')">删除</button> ';
                        //var  d = '<a href="workpageSevrlet?action=alldelete&workid=\''+ row.workid + '\'"><button  id="id="delete" data-id="98" class="btn btn-xs btn-primary">删除</button></a>';
                        return  d;
                    }
                },
            ]
        });
    }
    </script>  
    <script type="text/javascript">
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