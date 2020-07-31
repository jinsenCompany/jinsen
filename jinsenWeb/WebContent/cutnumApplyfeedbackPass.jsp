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
   <title>规划队修改采伐证材料</title>
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
.table1{width:70%; height:400px;margin-left:auto; margin-right:auto;margin-top:10px;padding:10px;border-collapse:collapse;text-align:center;vertical-align:middle}
.table1 input{vertical-align:middle;margin-top:10px;border:0px;background-color: transparent}
.table1 td,th{text-align:center;vertical-align:middle;margin-top:10px;}
.table2{width:70%; height:100px;margin-left:auto; margin-right:auto;padding:10px;border-collapse:collapse;margin-top:10px}
td,th{text-align:center;vertical-align:middle;margin-top:10px}
 tr{text-align:center;}
    .p-tail {
    padding: 10px;
    font-size: 12px;
    color: #8a6d3b;
}
#yhh{
vertical-align:middle;margin-top:10px;
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
        		alert("插入成功");
        	}
        	else{
        		alert("插入失败");
        	}
        }
    })
}
function not(){
	var designum=document.getElementById("designum").value;
	var unablereson=document.getElementById("unablereson").value;
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
<%cutnumfeedback f=(cutnumfeedback)request.getAttribute("cutnumfeedback"); %>
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
         class="i-tail"></i> 该页面主要是规划队上传采伐证材料</p>
    </div>
    <div class="find-top1">
    <form onSubmit="return inputNull(this)" action="cutnumServlet?action=updateCutnumapply" method="POST">
    <div class="top" id="divprint">
    <table class="table1" border="1">
    <caption class="book_h01">林木采伐证申请表</caption>
       <tr>
       <th colspan="1" id="yhh">采伐证号</th><td><input style="width:80%;border:0px;background-color: transparent" type="text" name="designum" id="designum" value="<%=c.getDesignum()%>"></td>
       <th colspan="1" id="yhh">采伐理由</th><td colspan="2"><input style="width:80%;border:0px;background-color: transparent" type="text" name="cutreason" id="cutreason" value="<%=c.getCutreason()%>"></td>
       </tr>
       <tr>
       <th>乡(镇、场)</th>
       <th>村(工区)</th>
       <th>林班</th>
       <th>大班</th>
       <th>小班</th>
       </tr>
       <tr>
       <td ><input style="width:80%;border:0px;background-color: transparent" type="text" name="cutaddress" id="cutaddress" value="<%=c.getCutaddress()%>"></td>
       <td><input style="width:80%;border:0px;background-color: transparent" style="width:80%;border:0px;background-color: transparent" type="text" name="cutvillage" id="cutvillage" value="<%=c.getCutvillage()%>"></td>
       <td><input style="width:80%;border:0px;background-color: transparent" type="text" name="quartel" id="quartel" value="<%=c.getQuartel()%>"></td>
       <td><input style="width:80%;border:0px;background-color: transparent" type="text" name="largeblock" id="largeblock" value="<%=c.getLargeblock()%>"></td>
       <td><input style="width:80%;border:0px;background-color: transparent" type="text" name="smallblock" id="smallblock" value="<%=c.getSmallblock()%>"></td>
       </tr>
       <tr>
       <th>小班面积</th>
       <th>林种起源</th>
       <th>地类或树种</th>
       <th>树种组成</th>
       <th>森岭经营类型</th>
       </tr>
       <tr>
       <td><input style="width:80%;border:0px;background-color: transparent" type="text" name="smallblackarea" id="smallblackarea" value="<%=c.getSmallblackarea()%>"></td>
       <td><input style="width:80%;border:0px;background-color: transparent" type="text" name="origin" id="origin" value="<%=c.getOrigin()%>"></td>
       <td><input style="width:80%;border:0px;background-color: transparent" type="text" name="foresttype" id="foresttype" value="<%=c.getForesttype()%>"></td>
       <td><input style="width:80%;border:0px;background-color: transparent" type="text" name="typeconsist" id="typeconsist" value="<%=c.getTypeconsist()%>"></td>
       <td><input style="width:80%;border:0px;background-color: transparent" type="text" name="managetype" id="managetype" value="<%=c.getManagetype()%>"></td>
       </tr>
       <tr>
       <th>林龄</th>
       <th>采伐面积</th>
       <th>采伐类型</th>
       <th>采伐方式</th>
       <th>采伐强度</th>
       </tr>
       <tr>
       <td><input style="width:80%;border:0px;background-color: transparent" type="text" name="forestage" id="forestage" value="<%=c.getForestage()%>"></td>
       <td><input style="width:80%;border:0px;background-color: transparent" type="text" name="cutarea" id="cutarea" value="<%=c.getCutarea()%>"></td>
       <td><input style="width:80%;border:0px;background-color: transparent" type="text" name="cuttype" id="cuttype" value="<%=c.getCuttype() %>"></td>
       <td><input style="width:80%;border:0px;background-color: transparent" type="text" name="cutway" id="cutway" value="<%=c.getCutway() %>"></td>
       <td><input style="width:80%;border:0px;background-color: transparent" type="text" name="cutstrength" id="cutstrength" value="<%=c.getCutstrength()%>"></td>
       </tr>
       <tr>
      <th rowspan="2">树种</th>
       <th colspan="4">采伐蓄积量</th>

       </tr>
       <tr>
       <th rowspan="1" colspan="1">采伐总蓄积</th>
       <th rowspan="1" colspan="1">采伐株树</th>
       <th rowspan="1" colspan="1">计采伐蓄积</th>
       <th rowspan="1" colspan="1">采伐蓄积间伐</th>

       </tr>
       <tr>
      <td><input style="width:80%;border:0px;background-color: transparent" type="text" name="treetype" id="treetype" value="<%=c.getTreetype() %>"></td>
       <td><input style="width:80%;border:0px;background-color: transparent" type="text" name="cutvolume" id="cutvolume" value="<%=c.getCutvolume()%>"></td>
       <td><input style="width:80%;border:0px;background-color: transparent" type="text" name="cutnumer" id="cutnumer" value="<%=c.getCutnumer()%>"></td>
       <td><input style="width:80%;border:0px;background-color: transparent" type="text" name="total" id="total" value="<%=c.getTotal()%>"></td>
       <td><input style="width:80%;border:0px;background-color: transparent" type="text" name="cutintermediate" id="cutintermediate" value="<%=c.getCutintermediate()%>"></td> 

       </tr>
       <tr>
       	<th colspan="4">采伐出材量</th>
       	<th rowspan="2">薪炭材</th>
       </tr>
       <tr>
       	<th rowspan="1" colspan="1">计采伐出材量</th>
       <th rowspan="1" colspan="1">规格材</th>
       <th rowspan="1" colspan="1">小径材</th>
       <th rowspan="1" colspan="1">短小材</th>
       </tr>
       <tr>
       	<td><input style="width:80%;border:0px;background-color: transparent" type="text" name="total2" id="total2" value="<%=c.getTotal2()%>"></td>
       <td><input style="width:80%;border:0px;background-color: transparent" type="text" name="sizewood" id="sizewood" value="<%=c.getSizewood()%>"></td>
       <td><input style="width:80%;border:0px;background-color: transparent" type="text" name="smalltimber" id="smalltimber" value="<%=c.getSmalltimber()%>"></td>
       <td><input style="width:80%;border:0px;background-color: transparent" type="text" name="shorttimber" id="shorttimber" value="<%=c.getShorttimber()%>"></td>
       <td><input style="width:80%;border:0px;background-color: transparent" type="text" name="firewood" id="firewood" value="<%=c.getFirewood()%>"></td> 
       </tr>
       <tr>
        <td class="top-table-label">上传附件</td><td><input class="filepath" onchange="changepic(this)" type="file" id="cutpath" name="cutpath"></td>
        <td class="top-table-label">申请时间</td><td colspan="2"><input type="date" name="applaydate" id="applaydate" value="<%=c.getApplaydate()%>"></td>
       </tr>
    </table>
    </div>
    
    <div>
    <table class="table2" border="1">
    <caption class="book_h01">管理部门采伐证反馈</caption>
    <tr>
    <td>无法办理原因:</td><td><input style="width:80%;border:0px;background-color: transparent" type="text" name="unablereson" id="unablereson" value="<%=f.getUnablereason()%>"></td>
    </tr>
    <tr>
    <td>请添加补充材料:</td><td><input type="file" name="replenishpath" id="replenishpath"></td>
    </tr>
    </table>
    </div>
    </form>
    <div> 
   <div class="but_p" style="float:center;">
    <button class="but_save" type="button" value="打印" id="btnPrint">打印</button></div>
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