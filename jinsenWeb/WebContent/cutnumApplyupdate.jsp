<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="jinshen.bean.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
   <title>管理部门审核采伐证反馈申请</title>
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
.table1{width:50%;margin-left:auto; margin-right:auto;padding:10px;border-collapse:collapse}
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
.table_p span{border-bottom: 3px #42cdec solid;display: inline-block;bottom: -1px;font-weight: bold;font-size: 22px}
	#a{
	text-align: right;
	}
	.biao{
	margin:auto;
	}
	#b{
	text-align:left;
	}
.but_p button{width:100px}
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
function yes(){
	var designum=document.getElementById("designum").value;
	//var designum=$("designum").val();
	$.ajax({
		url:"cutnumServlet?action=applyyesorno&type=yes",
        //url:"cutnumServlet",
        data:{
            //"action":"applyyesorno&type=yes",
            "designum":designum,
        },
        type: "POST",
        dataType:"html",
        success: function (data) {
        	if(data>0){
        		alert("办理成功");
        	}
        	else{
        		alert("办理失败");
        	}
        }
    })
}
function not(){
	var designum=document.getElementById("designum").value;
	var unablereson=$("#unablereson").val();
	//var unablereson=document.getElementById("unablereson").value;
		$.ajax({
        url:"cutnumServlet?action=applyyesorno&type=not",
        data:{
            //"action":"applyyesorno&type=not",
            "designum":designum,
            "unablereson":unablereson
        },
        type: "POST",
        dataType:"html",
        success: function (data) {
        	if(data>0){
        		alert("反馈成功");
        	}
        	else{
        		alert("反馈失败");
        	}
        }
    })
}
</script>
</head>
<body>
<%cutnumApply c=(cutnumApply)request.getAttribute("cutnumApply");%>
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
         class="i-tail"></i> 该页面主要是管理部门审核采伐证申请页面</p>
    </div>
    <div class="find-top1">
    <div class="top" id="divprint">
    <table class="biao" >
    <caption class="book_h01">林木采伐证申请情况表</caption>
       <tr>
       		<td id="a">采伐证号</td><td id="b"><input type="text" name="designum" id="designum" value="<%=c.getDesignum()%>"></td>
       		<td id="a">采伐理由</td><td id="b"><input type="text" name="cutreason" id="cutreason" value="<%=c.getCutreason()%>"></td>
       </tr>
       
       <tr>
      		 <td id="a">乡(镇、场)</td><td id="b"><input type="text" name="cutaddress" id="cutaddress" value="<%=c.getCutaddress()%>"></td>
      		 <td id="a">村(工区)</td><td id="b"><input type="text" name="cutvillage" id="cutvillage" value="<%=c.getCutvillage()%>"></td>
       </tr>
       
       <tr>
       		<td id="a">林班</td><td id="b"><input type="text" name="quartel" id="quartel" value="<%=c.getQuartel()%>"></td>
       		<td id="a">大班</td><td id="b"><input type="text" name="largeblock" id="largeblock" value="<%=c.getLargeblock()%>"></td>      
       </tr>
     
       <tr>
       		<td id="a">小班</td><td id="b"><input type="text" name="smallblock" id="smallblock" value="<%=c.getSmallblock()%>"></td>
        	<td id="a">小班面积</td><td id="b"><input type="text" name="smallblackarea" id="smallblackarea" value="<%=c.getSmallblackarea()%>"></td>
       </tr>
       
       <tr>     
       		<td id="a">林种起源</td><td id="b"><input type="text" name="origin" id="origin" value="<%=c.getOrigin()%>"></td>
       		<td id="a">地类或树种</td><td id="b"><input type="text" name="foresttype" id="foresttype" value="<%=c.getForesttype()%>"></td>       
       </tr>
       
       <tr>      
       		<td id="a">树种组成</td><td id="b"><input type="text" name="typeconsist" id="typeconsist" value="<%=c.getTypeconsist()%>"></td>
       		<td id="a">森岭经营类型</td><td id="b"><input type="text" name="managetype" id="managetype" value="<%=c.getManagetype()%>"></td>
       </tr>
       
       <tr>
       		<td id="a">林龄</td><td id="b"><input type="text" name="forestage" id="forestage" value="<%=c.getForestage()%>"></td>
       		<td id="a">采伐面积</td><td id="b"><input type="text" name="cutarea" id="cutarea" value="<%=c.getCutarea()%>"></td>      
       </tr>
       
       <tr>
        	<td id="a">采伐类型</td><td id="b"><input name="cuttype" id="cuttype" value="<%=c.getCuttype() %>"></td>
       		<td id="a">采伐方式</td><td id="b"><input name="cutway" id="cutway" value="<%=c.getCutway()%>"></td>       
       </tr>
       
       <tr>
      		<td id="a">树种</td><td id="b"><input type="text" name="treetype" id="treetype" value="<%=c.getTreetype() %>"></td>
       		<td id="a">采伐总蓄积</td><td id="b"><input type="text" name="cutvolume" id="cutvolume" value="<%=c.getCutvolume()%>"></td>
       </tr>
       
       <tr>       
       		<td id="a">采伐株树</td><td id="b"><input type="text" name="cutnumer" id="cutnumer" value="<%=c.getCutnumer()%>"></td>
       		<td id="a">计采伐蓄积</td><td id="b"><input type="text" name="total" id="total" value="<%=c.getTotal()%>"></td>
       </tr>
       
       <tr>
            <td id="a">采伐蓄积间伐</td><td id="b"><input type="text" name="cutintermediate" id="cutintermediate" value="<%=c.getCutintermediate()%>"></td> 
			<td id="a">薪炭材</td><td id="b"><input type="text" name="firewood" id="firewood" value="<%=c.getFirewood()%>"></td> 
       </tr>
       
       <tr>    
       		<td id="a">计采伐出材量</td><td id="b"><input type="text" name="total2" id="total2" value="<%=c.getTotal2()%>"></td>
       		<td id="a">规格材</td><td id="b"><input type="text" name="sizewood" id="sizewood" value="<%=c.getSizewood()%>"></td>   		 
       </tr>
       
       <tr>
       		<td id="a">小径材</td><td id="b"><input type="text" name="smalltimber" id="smalltimber" value="<%=c.getSmalltimber()%>"></td>
       		<td id="a">短小材</td><td id="b"><input type="text" name="shorttimber" id="shorttimber" value="<%=c.getShorttimber()%>"></td>
       </tr>
       
       <tr>
        <td id="a">上传附件</td><td id="b"><input class="filepath" onchange="changepic(this)" type="file" id="cutpath" name="cutpath"></td>
        <td id="a">申请时间</td><td id="b"><input type="date" name="applaydate" id="applaydate" value="<%=c.getApplaydate()%>"></td>
       </tr>
    </table>
    <script type="text/javascript">
			function no(){
				document.getElementById('cx').style.display='block'
				
			}
			function no1(){
				document.getElementById('cx1').style.display='block'
				
			}
		</script>
    </div>
<div id="" style="font-size: x-large;font-style: italic;color:  cornflowerblue;text-decoration: underline;font-weight: bold; " >
			审核申请
		</div>
		<div id="">
			<button type="button" onclick="yes()" class="btn">审核通过</button>
		</div>
		<div id="" style="margin-top: 20px;">
			<button type="button" onclick="no()" class="btn">审核未通过</button>
		</div>
		<div id="cx" hidden="hidden">
			<span>请输入原因:<input style="width:800px;" type="text" id="unablereson" value="" /></span><input onclick="not()" style="background-color: lightblue ;" type="submit" id="" name=""  />
		</div>
		
		<div id="" style="text-align: left;margin-top: 20px;">
				<button class="btn"  type="button" id="btnPrint">打印</button>
		</div>
    </div>
    
<!-- 显示最近添加的员工 -->
    <div class="table-con">
        <table id="table1" class="table-style"></table>
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
</body>
</html>