<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jinshen.bean.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Connection"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>拨交单</title>
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
.table1 {
	width: 80%;
	height: 400px;
	margin-left: auto;
	margin-right: auto;
	padding: 10px;
	border-collapse: collapse
}

td, th {
	text-align: center;
	vertical-align: middle
}

tr {
	text-align: center
}

.p-tail {
	padding: 10px;
	font-size: 16px;
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

	#a{
	text-align: right;
	}
	.biao{
	margin:auto;
	}
	#b{
	text-align:left;
	}
</style>
<script type="text/javascript">
	function inputNull(form) {
		for (i = 0; i < form.length; i++) {
			//form属性的elements的首字母e要小写
			if (form.elements[i].value == "") {
				alert("" + form.elements[i].placeholder + "不能为空");
				form.elements[i].focus();
				return false;
			}
		}
	}
</script>
<script type="text/javascript" src="static/js/jquery-1.12.4.min.js"></script>
	<script src="js/bstable/js/bootstrap.min.js"></script>
	<script src="js/bstable/js/bootstrap-table.js"></script>
	<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
	<script>
		//预览
		function imgPreview(fileDom) {
			//判断是否支持FileReader
			if (window.FileReader) {
				var reader = new FileReader();
			} else {
				alert("您的设备不支持图片预览功能，如需该功能请升级您的设备！");
			}
			//获取文件
			var file = fileDom.files[0];
			var imageType = /^image\//;
			//是否是图片
			if (!imageType.test(file.type)) {
				alert("请选择图片！");
				return;
			}
			//读取完成
			reader.onload = function(e) {
				//获取图片dom
				var img = document.getElementById("preview");
				//图片路径设置为读取的图片
				img.src = e.target.result;
			};
			reader.readAsDataURL(file);
		}

		function UpLoad() {
			var formData = new FormData();
			formDate.append('workid', workid);
			formData.append('file', $('#pic')[0].files[0]); //添加图片信息的参数
			formData.append('file', $('#da')[0].files[0]); //添加其他参数
			$.ajax({
				url : 'pictureServlet?action=treecompare',
				type : 'POST',
				cache : false, //上传文件不需要缓存
				data : formData,
				processData : false, // 告诉jQuery不要去处理发送的数据
				contentType : false, // 告诉jQuery不要去设置Content-Type请求头
				success : function(data) {
					var rs = eval("(" + data + ")");
					if (rs.state == 1) {
						tipTopShow('上传成功！');
					} else {
						tipTopShow(rs.msg);
					}
				},
				error : function(data) {
					tipTopShow("上传失败");
				}
			})
		}
		/*
		 function UpLoad() {
		 //如果有其他的值，判断下是否为空

		 var keyValue = $("#gridList").jqGridRowValue().Id;

		 form2.action = "/List/AddEvidence?keyValue=" + keyValue;
		 form2.submit();
		 }*/
	</script>
</head>
<body>
<%cutnum c = (cutnum) request.getAttribute("cutnum");%>
  <% List<cutnum> o=null;
o=(List<cutnum>)request.getAttribute("cutnumtree");
%>
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
    <div id="breadcrumb"> <a href="managerP.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
  </div>
<!--End-breadcrumbs-->
	
	<main>

		<div class="home-tab">
			<i class="tab-i"></i> 所在位置：<span>上传采伐证材料</span>
		</div>
		<div class="find-top">
			<p class="p-tail">
				<i class="i-tail"></i> 该页面主要是管理部门上传采伐证材料
			</p>
		</div>
		<div class="find-top1">
			<form onSubmit="return inputNull(this)"
				action="cutnumServlet?action=cutnumUpdate" method="POST">
				<div class="top" id="divprint">
				<table  class="table1" border="1">
			<caption class="book_h01">查看采伐证材料详情</caption>
			<tr>
				<td >林权单位</td>
				<td colspan="2"><input  style="border:0px;background-color: transparent;text-align:center;width: 230px;" type="text" id="company" name="company" value="<%=c.getCompany()%>"/></td>
				<td>采伐证号</td>
				<td colspan="2"><input  style="border:0px;background-color: transparent;text-align:center;width: 300px;" type="text" id="certificatenum" name="certificatenum" value="<%=c.getCertificatenum()%>"/></td>
			</tr>
			<tr>
			 <td>林权证号</td>
			 <td colspan="2"><input  style="border:0px;background-color: transparent;width: 400px;" type="text" id="forestid" name="forestid" value="<%=c.getForestid()%>"></td>
			 <td>采伐证编号</td>
				<td colspan="2"><input  style="border:0px;background-color: transparent;width: 300px;" type="text" id="cutnum" name="cutnum" value="<%=c.getCutnum()%>"></td>
			</tr>
			<tr>
				<td>采伐地点</td>
				<td colspan="5" >
				<input  style="border:0px;text-align:right;background-color: transparent;width: 150px;" type="text" id="cutaddress" name="cutaddress" value="<%=c.getCutaddress()%>">乡镇，     
				<input  style="border:0px;text-align:right;background-color: transparent;width: 150px;" type="text" id="cutvillage" name="cutvillage" value="<%=c.getCutvillage()%>">村，
				<input  style="border:0px;text-align:right;background-color: transparent;width: 150px;" type="text" id="quartel" name="quartel" value="<%=c.getQuartel()%>">林班，
				<input  style="border:0px;text-align:right;background-color: transparent;width: 150px;" type="text" id="largeblock" name="largeblock" value="<%=c.getLargeblock()%>">大班，
				<input  style="border:0px;text-align:right;background-color: transparent;width: 150px;" type="text" id="smallblock" name="smallblock" value="<%=c.getSmallblock()%>">小班
				</td>
			</tr>	
			<tr>
				<td>采伐类型</td>
				<td colspan="2"><input style="border:0px;background-color: transparent;text-align:center;width: 230px;" name="cuttype" id="cuttype" value="<%=c.getCuttype()%>"></td>
				<td>采伐方式</td>
				<td colspan="2"><input style="border:0px;background-color: transparent;text-align:center;width: 230px;" name="cutmethod" id="cutmethod" value="<%=c.getCutmethod()%>"> </td>
			</tr>
			<tr>
			    <td>采伐强度(%)</td>
				<td colspan="2"><input style="border:0px;background-color: transparent;width: 300px;" type="text" id="cutqiang" name="cutqiang" value="<%=c.getCutarea()%>"></td>
				<td>采伐面积(公顷)</td>
				<td colspan="2"><input  style="border:0px;background-color: transparent;width: 300px;"type="text" id="cutarea" name="cutarea" value="<%=c.getCutarea()%>"></td>		   
			</tr>
			
			<tr>
				<td>采伐开始时间</td>
				<td colspan="2"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type="date" name="starttime" id="starttime" value="<%=c.getStarttime()%>"></td>
				<td >采伐截止时间</td>
				<td colspan="2"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type="date" name="endtime" id="endtime" value="<%=c.getEndtime()%>"></td>
			</tr>	
		    <tr>
				<td>下载附件:</td>
				<!-- <td id="b"><input class="filepath" onchange="changepic(this)"
				type="file" id="cutnumfile" name="cutnumfile"></td>-->
				<td colspan="5">${cutnumfile}<a href="DownfileServlet?action=cutnumfile&filename=<%=c.getCutnumfile() %>">下载</a></td>
			</tr> 
			</table>
			
			<table id="tabletree" class="table1" border="1">
		    <tr>
		     <th colspan="6">出材量</th>
		    </tr>
		    <tr>
		    <th>树种</th>    <!--treetype  -->
		    <th>小计</th>     <!-- total -->
		    <th>规格材</th>  <!--sizewood  -->
		    <th>小径材(非规格材)</th>  <!--smalltimber  -->
		    <th>短小材(非规格材)</th>   <!-- shorttimber -->
		    <th>薪碳材</th>             <!-- firewood -->
		    </tr>
		    
		<% int i=1;%>
		     <c:forEach items="${cutnumtree}" var="b">    
		     <tr id="<%=i%>">
		     <td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="treetype" id="tree<%=i%>" value="${b.getTreetype()}"></td>         
		     <td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type='text' style='' name='total' id='tot<%=i%>' value="${b.getTotal()}"></td>
		     <td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type='text' name='sizewood' id='size<%=i%>' value="${b.getSizewood()}"></td>
		     <td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type='text' name='smalltimber' id='small<%=i%>' value="${b.getSmalltimber()}"></td>
		     <td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type='text' name='shorttimber' id='short<%=i%>' value="${b.getShorttimber()}"></td>
		     <td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type='text' name='firewood' id='fire<%=i%>' value="${b.getFirewood()}"></td></tr>
		     </tr>     
        <%i++; %>
             </c:forEach>

		    <tr>   <!--，此行为出材量、总计 -->
		    <th><input style="border:0px;background-color: transparent"  type="text" name="chucai" id='chucai' value="合计(出材量)"></th>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="volume" id='volume' onclick="tvolume()" value="<%=c.getVolume()%>"></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="sizewoodAll" id='size4' onclick="sizeTall()" value="<%=c.getSizewoodAll()%>"></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="smalltimberAll" id='small4' onclick="sizeTall()" value="<%=c.getSmalltimberAll()%>"></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="shorttimberAll" id='short4' onclick="shortTall()" value="<%=c.getShorttimberAll()%>"></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="firewoodAll" id='fire4' onclick="fireTall()" value="<%=c.getFirewoodAll()%>"></td>
		    </tr>
		    </table>
		  <br>
				</div>
			</form>
			<!-- 
  <form  id="myForm" action="pictureServlet" method="post" enctype="multipart/form-data">
    <table class="top-table">
        <tr><td class="top-table-label">输入采伐号：</td><td colspan="5"><input type="text" name="workid" id="workid"></td></tr>
        <tr><td class="top-table-label">上传照片</td><td colspan="5"><input class="filepath" onchange="changepic(this)" type="file" id="pic" name="pic"></td></tr>
        <tr><td class="top-table-label">上传文件</td><td colspan="5"><input type="file" name="da" id="da"></td></tr>
        <tr><td colspan="6" style="text-align: center"> <button class="add-but"  onclick="UpLoad()"><i class="glyphicon glyphicon-edit"></i> 提交</button>
         <td>
              <input type="submit" name="submit" value="Submit"></td>
    </table>
 </form> -->

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
		$(function() {
			$("#btnPrint").click(function() {
				$("#divprint").printArea();
			});
		});
	</script>
</body>
</html>