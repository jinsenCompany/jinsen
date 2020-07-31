<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jinshen.bean.*" %>
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
</style>
<script type="text/javascript">
//拨交采伐证号
function cutareaAllot(){
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
    	for(var i=0;i<str.length;i++){
    		var j=str[i];
    		group[i]=j.cutnum;
 var mymap=JSON.stringify(group);
$.ajax({
    url:"cutnumServlet",
    data:{
        "action":"cutareaAllot",
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
    	        alert("激活成功！");
    	        $("#table1").bootstrapTable('refresh');
    		}
    	else{
    		 alert("激活失败");
    	}
    }
})
   }
}
}
//从工程包中删除采伐证号
function overtimedelete(cutnum)
{
	//var projectPackageid=document.getElementById("projectPackageid").value;
	$.ajax({
        url:"cutnumServlet",
        data:{
            "action":"alldelete",
            "cutnum":cutnum,
            //"projectPackageid":projectPackageid
        },
        type: "Post",
        dataType:"json",
        success: function (data) {
        	if(data)
        		{
        		alert("hh删除成功！");
        		$("#table1").bootstrapTable('refresh');
        		$("#table2").bootstrapTable('refresh');
        		
        		}
        		
        	else
        		alert("hh删除失败！");
        }
    })
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
<% projectpackage c=(projectpackage)request.getAttribute("projectpackage");%>
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
        <!--  <li><a href="workorderAdd.jsp">发送工单通知</a></li>-->
        <li><a href="workpageAdd.jsp">输入工单</a></li>
        <li><a href="workpageShenheFaqu.jsp">审核工单</a></li>
      </ul>
     </li>
     <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>工程包管理</span> <span class="label label-important">3</span></a>
     <ul>
        <li><a href="CutnumProjectpackage.jsp">创建工程包</a></li>
        <li><a href="CutnumProjectpackageTable.jsp">工程包管理台账</a></li>
        <li><a href="cutareaAllot.jsp">伐区拨交</a></li>
      </ul>
     </li>
    <li> <a href="manageCutnumCheck.jsp"><i class="icon icon-inbox"></i> <span>生产管理</span></a> </li>
    <li><a href="productPrice.jsp"><i class="icon icon-th"></i> <span>生产结算</span></a></li>
    <li><a href="CutnumProjectpackage.jsp"><i class="icon icon-inbox"></i><span>工程包台账</span></a></li>
    <li><a href="managesdatecard.jsp"><i class="icon icon-inbox"></i><span>施工方资料</span></a></li>
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
         class="i-tail"></i> 该页面主要是伐区拨交页面</p>
    </div>
    <div class="find-top1">
    <h1 class="book_h01" align="top">伐区拨交</h1>
    <div class="top" id="divprint">
    <p class="htv"><span>工程包信息:</span></p>
    <table style="margin:auto">
       <tr>
       <td>工程包</td><td><input type="text" name="projectPackageid" id="projectPackageid" value="<%=c.getprojectPackageid() %>"></td>
       <td>工程包管理人员</td><td><input type="text" name="projectPackagename" id="projectPackagename" value="<%=c.getprojectPackagename() %>"></td>
       <td>管理人员电话</td><td><input type="text" name="managerPhone" id="managerPhone" value="<%=c.getmanagerPhone() %>"></td>
       </tr>
    </table>
    <!-- 显示最近新增的采伐证号 -->
    <div class="htv" style="font-size:20px"><span>已添加采伐证号:</span></div>
    <div class="table-con">
        <table id="table1" class="table-style"></table>
    </div>
    </div>
    <div class=" but_p" style="float:right;">
    <button class="but_save" type="button" onclick="cutareaAllot()" value="伐区拨交">伐区拨交</button>
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
    /*$(function(){
    	var ta1=new table1();
    	var ta2=new table2();
    	ta1.Init();
    	ta2.Init();
    	//table1();
    	//table2();
    })*/
    
    $(function(){
    	table1();
    })
    function table1(){
            $('#table1').bootstrapTable({
                method: "get",
                //async : false,
                striped: true,
                singleSelect: false,
                url: "cutnumServlet?action=cutnumprojectAllot",
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
                queryParams:function queryParams(params){
                    var temp = {
    						//action:"cutnumproject",
    						projectPackageid:document.getElementById("projectPackageid").value,
    				};     
                    return temp;
                },
            columns: [[
            	{
            		"title": "采伐证信息",
                    "font-size":"100px",
                    "halign":"center",
                    "align":"center",
                    "valign": "middle",
                    "colspan": 9
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
                {
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<a href="cutnumServlet?action=watchcutnumAllot&cutnum=\''+ row.cutnum + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        return  d;
                    }
                },
                ],
            ]
        });
    }

</script> 
</body>
</html>