<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jinshen.bean.*"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Connection" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
   <title>生产施工方资料</title>
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
<!--<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>  -->
<style>
<style>
.table1{width:70%; height:50px;margin-left:auto; margin-right:auto;padding:10px;border-collapse:collapse}
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
	#a{
	text-align:right;
	}
	.biao{
	margin:auto;
	}

	#b{
	text-align:left;
	}
			.btn{ 	
	font-family: "'微软雅黑','Helvetica Neue',Helvetica,Arial,sans-serif"; 	
	font-size: 18px!important; 	height: 40px; 	
	line-height: 20px!important; 	
	padding: 3px 18px; 	
	display: inline-block; 	vertical-align: middle; 	
	font-weight: normal; 	border-radius: 3px; 	
	margin: 0 8px 0 3px; 	
	border: 1px solid #3383da; 	
	color: #ffffff; 	
	background-color: #3383da; 	
	cursor: pointer; 
	}
		.sgf{
				text-align: center;margin-left:auto; margin-right:auto;padding:10px;border-collapse:collapse;
			}
			.sgf input{
				width: 150px;
				border: 0;
				text-align:center;vertical-align:middle;
				margin-top:10px;
			}
			.sgf td{text-align:center;vertical-align:middle}
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
//利用fromdata保存文件
function addmmmmm()
{
	//var formData = new FormData($('#myform'));
	//formData.append('file', $('#file'));
	//formData.append('cutnum', $('#cutnum'));
	//alert(formData);
    $.ajax({
        url:"workpageSevrlet?action=UpdatemanagesdateCard",
        /*data:{
            "action":"cutnumtreeAdd",
            "newtree":mymap,
            "id":kk,
            "cutnum":cutnum 
        },*/
        type: "POST",
        cache: false,
        data: new FormData($('#myform')[0]), 
        processData: false,
        contentType: false,
        dataType:"html",
        success: function (data) {
        	//alert(data);
        	if(data>0)
        		{
        	        alert("更新成功！");
        	        window.location.href = 'managesdatecard.jsp';
        		}
        	else{
        		 alert("更新失败");
        	}
        }
    })
}
</script>
</head>
<body>
<%managesdatecard m = (managesdatecard) request.getAttribute("managesdatecard");%>
<% List<projectpackage> p=null;
p=(List<projectpackage>)request.getAttribute("projectpackage");
%>
<% List<cutnum> c=null;
c=(List<cutnum>)request.getAttribute("Cutareav");
%>
<% List<cutnum> v=null;
v=(List<cutnum>)request.getAttribute("Volumev");
%>
<!--Header-part-->
<div id="header">
  <h1><a href="dashboard.html">生产部门平台导航</a></h1>
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
    <li>
    <%
	String staff_id = request.getSession().getAttribute("staff_id").toString();
				%> <%
 	String staff_name = request.getSession().getAttribute("staff_name").toString();
 %> 您好，<%=staff_id%> <%=staff_name%>欢迎登录
    </li>
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
    <div id="breadcrumb"> <a href="forestP.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> 首页</a></div>
  </div>
<!--End-breadcrumbs-->
<main>
    <div class="find-top">
        <p class="p-tail"><i
         class="i-tail"></i> 该页面主要是木材生产施工方管理资料卡</p>
    </div>
    <div class="find-top1">
    <form id="myform" action="workpageSevrlet?action=managesdateCard1" method="POST" enctype="multipart/form-data">
    <div class="top" id="divprint">
			<table border="" cellspacing="" cellpadding="" class="sgf">
			<caption class="book_h01">录入施工方资料</caption>
				<tr>
					<td>施工方</td>
					<td><input style="border:0px;background-color: transparent;" type="text" id="ownername" name="ownername" value="<%=m.getOwnername() %>" /></td>
					<td>性别</td>
					<td><input style="border:0px;background-color: transparent;" name="sex" id="sex" value="<%=m.getSex() %>"></td>
					<td>出生年月</td>
					<td><input style="border:0px;background-color: transparent;" type="date" id="borndate" name="borndate" value="<%=m.getBorndate() %>" /></td>
					<td rowspan="2"><input type="file" name="managepath" id="managepath" value="" /><img src="DownfileServlet?action=managecartPic&filename=<%=m.getManagepath() %>" alt="150"  width="250" height="150" /></td>
				</tr>
				<tr>
					<td>身份证号</td>
					<td colspan="3"><input style="width: 220px;border:0px;background-color: transparent;" type="text" id="idnumber" name="idnumber" value="<%=m.getIdnumber() %>" /></td>
					
					<td>籍贯</td>
					<td><input style="border:0px;background-color: transparent;" type="text" id="bornplace" name="bornplace" value="<%=m.getBornplace() %>" /></td>
				<!-- 	<td></td> -->
				</tr>
				<tr>
					<td>企业名称</td>
					<td colspan="3"><input style="width: 220px;border:0px;background-color: transparent;" type="text" id="enterprisename" name="enterprisename" value="<%=m.getEnterprisename() %>" /></td>
					<td>企业营业执照</td>
					<td colspan="2"><input style="width: 300px;border:0px;background-color: transparent;" type="text" id="businesslicense" name="businesslicense" value="<%=m.getBusinesslicense() %>" /></td>
				</tr>
				<tr>
					<td>家庭住址</td>
					<td colspan="3"><input style="width: 220px;border:0px;background-color: transparent;" type="text" id="address" name="address" value="<%=m.getAddress() %>" /></td>
					<td>联系方式</td>
					<td colspan="2"><input style="width: 300px;border:0px;background-color: transparent;" type="text" id="contact" name="contact" value="<%=m.getContact() %>" /></td>
				</tr>
				<tr>
					<td>总面积(亩)</td>
					<td colspan="3"><input style="width: 220px;border:0px;background-color: transparent;" type="text" id="allarea" name="allarea" value="${Cutarea}" readonly="readonly" /></td>
					<td>总出材量</td>
					<td colspan="2"><input style="width: 300px;border:0px;background-color: transparent;" type="text" id="totaloutwood" name="totaloutwood" value="${Volume}" readonly="readonly" /></td>
				</tr>
		     <!--<c:forEach items="${projectpackage}" var="p" varStatus="loop"> 
		     <tr>
		     <td><input value="${p.getProjpackageStarttime()}"></td>
		     <td><input  value="${p.getprojectPackageid()}"></td>
		     <td><c:out  value="${Volumev[loop.count-1]}"/></td> 
		     <td><c:out  value="${Cutareav[loop.count-1]}"/></td> 
		     </tr>
		     </c:forEach>-->
				<tr>
					<td colspan="7">施工业绩</td>
				</tr>
                   <c:forEach items="${projectpackage}" var="p" varStatus="loop"> 
		        <tr>
		         <td>签订工程包时间</td><td style="width:300px"><input style="width:300px" value="${p.getProjpackageStarttime()}"></td>
		         <td style="width:300px;vertical-align:middle;text-align:center">工程名称:<input style="width:100px" value="${p.getProjectPackageName()}"></td>
		        <td>出材量</td><td style="width:200px"><c:out  value="${Volumev[loop.count-1]}"/></td> 
		        <td>面积</td><td style="width:200px"><c:out  value="${Cutareav[loop.count-1]}"/></td> 
		       </tr>
		      </c:forEach>
					
				
			</table>
		</div>
		<br>
		<div class="1" style="text-align:center">

		    <span  style="text-align: center;"><button class="btn" type="button" onclick="addmmmmm()" value="更新修改">更新修改</button></span>
		     <span style="margin-left:100px"><button class="btn" type="button" id="btnPrint" value="打印">打印</button></span> 
		 </div>
						<div style="clear: both;padding-bottom: 40px"></div>
	</form> 
		
	</div>
  <!-- 
  <form  id="myForm" action="pictureServlet" method="post" enctype="multipart/form-data">
    <table class="top-table">
        <tr><td class="top-table-label">输入资料卡号：</td><td colspan="5"><input type="text" name="workid" id="workid"></td></tr>
        <tr><td class="top-table-label">上传照片</td><td colspan="5"><input class="filepath" onchange="changepic(this)" type="file" id="pic" name="pic"></td></tr>
        <tr><td class="top-table-label">上传文件</td><td colspan="5"><input type="file" name="da" id="da"></td></tr>
        <tr><td colspan="6" style="text-align: center"> <button class="add-but"  onclick="UpLoad()"><i class="glyphicon glyphicon-edit"></i> 提交</button>
         <td>
              <input type="submit" name="submit" value="Submit"></td>
    </table>
 </form> -->  

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