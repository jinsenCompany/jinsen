<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Connection" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
   <title>创建采伐证工程包</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/tableall.css">
    <link rel="stylesheet" href="css/registe.css"/>
    <link rel="stylesheet" href="js/jQueryCalendar/calendar.css">
    <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
    <!-- font-awesome icons CSS -->
    <link href="css/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons CSS-->

    <!-- side nav css file -->
    <link href='css/SidebarNav.min.css' media='all' rel='stylesheet' type='text/css'/>
    <!-- js-->
    <script src="js/jquery-1.11.1.min.js"></script>
    <script src="js/modernizr.custom.js"></script>
    <!-- jQuery.print -->
	<script src="js/jQuery.print.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/fullcalendar.css" />
<link rel="stylesheet" href="css/matrix-style.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="css/jquery.gritter.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
<style>
.table1{width:70%; height:400px;margin-left:auto; margin-right:auto;padding:10px;border-collapse:collapse}
td,th{text-align:center;vertical-align:middle}
         tr{text-align:center}
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
.htv {
    float:left;font-weight:bold;color:#966f2b;text-decoration: underline;
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
//保存工程包对应的采伐证号
function addproject(){
	var map={};
	var group=[];
	//var projectPackageid=$("#projectPackageid").val();
	var projectPackageid=document.getElementById("projectPackageid").value;
	var projectPackagename=document.getElementById("projectPackagename").value;
	var managerPhone=document.getElementById("managerPhone").value;
	var str=$("#table1").bootstrapTable('getSelections');
	if(str.length<1) {
		alert("无选中记录！");
	}
	else{
		
	    if(projectPackageid=="")
	   {
	    alert("请先输入工程包号！");
	    }
    else{
    	for(var i=0;i<str.length;i++){
    		var j=str[i];
    		group[i]=j.cutnum;
    	}
 var mymap=JSON.stringify(group);
$.ajax({
    url:"cutnumServlet",
    data:{
        "action":"addproject",
        "project":mymap,
        "projectPackageid":projectPackageid,
        "projectPackagename":projectPackagename,
        "managerPhone":managerPhone
    },
    type: "POST",
    dataType:"html",
    success: function (data) {
    	alert(data);
    	if(data>0)
    		{
    	        alert("添加成功！");
    	        $("#table1").bootstrapTable('refresh');
    		}
    	else{
    		 alert("添加失败");
    	}
    }
})
   }
}
}
function inputNull(form){
	for(i=0;i<form.length;i++){
            //form属性的elements的首字母e要小写
		if(form.elements[i].value == ""){ 
			alert("" + form.elements[i].placeholder + "不能为空");
			form.elements[i].focus();	
			return false;
		}
	}
	}
</script>
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
<!--start-top-serch-->
<div id="search">
  <input type="text" placeholder="Search here..."/>
  <button type="submit" class="tip-bottom" title="Search"><i class="icon-search icon-white"></i></button>
</div>
<!--close-top-serch-->
<!--sidebar-menu-->
<div id="sidebar"><a href="#" class="visible-phone"><i class="icon icon-home"></i> 仪表盘</a>
  <ul>
    <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>工单管理</span> <span class="label label-important">2</span></a>
       <ul>
        <li><a href="workpageAddManager.jsp">输入工单</a></li>
        <li><a href="workpageShenheFaquManager.jsp">审核工单</a></li>
      </ul>
     </li>
     <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>工程包管理</span> <span class="label label-important">3</span></a>
     <ul>
        <li><a href="CutnumProjectpackageManager.jsp">创建工程包</a></li>
        <li><a href="cutnumProjectpackageShenhe.jsp">审核工程包</a></li>
        <li><a href="cutareaAllotManager.jsp">伐区拨交</a></li>
      </ul>
     </li>
    <li> <a href="manageCutnumCheckManager.jsp"><i class="icon icon-inbox"></i> <span>生产管理</span></a> </li>
    <li><a href="productPriceManager.jsp"><i class="icon icon-th"></i> <span>生产结算</span></a></li>
    <li><a href="CutnumProjectpackageTableManager.jsp"><i class="icon icon-inbox"></i><span>工程包台账</span></a></li>
    <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>施工方管理</span> <span class="label label-important">2</span></a>
     <ul>
        <li><a href="managesdatecardManager.jsp">录入施工方资料</a></li>
        <li><a href="managersdatecardSeeManager.jsp">施工方台账</a></li>
      </ul>
     </li>
  </ul>
</div>
<!--sidebar-menu-->

<div id="content">
<div id="content-header">
    <div id="breadcrumb"> <a href="managerP.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
  </div>
<!--End-breadcrumbs-->
<main>
    <div class="find-top">
        <p class="p-tail"><i
         class="i-tail"></i> 该页面主要是创建采伐证工程包</p>
    </div>
    <div class="find-top1">
    <h1 class="book_h01" align="top">创建工程包</h1>
    <form onSubmit="return inputNull(this)" action="cutnumServlet?action=addprojectid" method="POST">
    <div class="top" id="divprint">
    <p class="htv"><span>请输入工程包信息:</span></p>
    <br>
    <br>
    <br>
    <table style="margin:left">
       <tr>
       <td>&nbsp;&nbsp;&nbsp;&nbsp;工程包:</td><td><input type="text" name="projectPackageid" id="projectPackageid"></td>
       <td>&nbsp;&nbsp;&nbsp;&nbsp;工程包管理人员:</td><td><input type="text" name="projectPackagename" id="projectPackagename"></td>
       <td>&nbsp;&nbsp;&nbsp;&nbsp;管理人员电话:</td><td><input type="text" name="managerPhone" id="managerPhone"></td>
       </tr>
    </table>
    <br>
    <br>
    <!-- 显示最近新增的采伐证号 -->
    <div class="htv" style="font-size:20px"><span>请勾选需要的采伐证号:</span></div>
    <div class="table-con">
        <table id="table1" class="table-style"></table>
    </div>
    
    </div>
    <div class="1" ; style="text-align:center">

		    <span  style="text-align: center;"><button class="btn" type="submit" id="mybutton" value="保存申请书">保存</button></span>
		     <span  ><button class="btn" type="button" id="btnPrint" value="打印">打印</button></span> 
		 </div>
    <div style="clear: both;padding-bottom: 40px"></div>
    </form> 

    </div>

</main>
</div>
<script src="js/jquery.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
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
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/jQuery.print.js"></script>
<script src="js/jquery.PrintArea.js" type="text/JavaScript"></script>
<script type="text/javascript">
$(function(){
    $("#btnPrint").click(function(){ $("#divprint").printArea(); });
});
</script>
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
                singleSelect: false,
                url: "cutnumServlet?action=cutnumproject",
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
            		"title": "采伐证信息",
                    "font-size":"100px",
                    "halign":"center",
                    "align":"center",
                    "valign": "middle",
                    "colspan": 8
                }],
                [
                	{
                        checkbox: "true",
                        field: 'check',
                        align: 'center',
                        valign: 'middle'
                    },
                	{
                    title: "采伐证号",
                    field: 'cutnum',
                    //width:'auto',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采法许可证编号',
                    field: 'number',
                    width:'auto',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采法许开始时间',
                    field: 'starttime',
                    //width:'auto',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采法许结束时间',
                    field: 'endtime',
                    //width:'auto',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐公司',
                    field: 'company',
                    width:'200px',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐地点',
                    field: 'cutsite',
                    //width:'40px',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐蓄积',
                    field: 'cutstore',
                    //width:'40px',
                    align: 'center',
                    valign: 'middle'
                },
                ],
            ]
        });
    }

</script> 
</body>
</html>