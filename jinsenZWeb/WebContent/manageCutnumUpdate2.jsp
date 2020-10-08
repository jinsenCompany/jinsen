<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<title>输入采伐证材料</title>
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
.btn{ 	
	font-family: "'微软雅黑','Helvetica Neue',Helvetica,Arial,sans-serif"; 	
	font-size: 18px!important; 	height: 40px; width:120px;	
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
	.btnt{ 	
	font-family: "'微软雅黑','Helvetica Neue',Helvetica,Arial,sans-serif"; 	
	font-size: 18px!important; 	height: 40px; width:120px;	
	line-height: 18px!important; 	
	padding: 3px 18px; 	
	display: inline-block; 	vertical-align: middle; 	
	font-weight: normal; 	border-radius: 3px; 	
	margin: 0 8px 0 3px; 	
	border: 1px solid #F08080; 	
	color: #ffffff; 	
	background-color: #F08080; 	
	cursor: pointer; 
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
<script type="text/javascript">
function cutnumtreeUp()
{
	var map={};
	var kk=Number(0),ki=Number(0);
	var cutnum=$("#cutnum").val();
	var company=$("#company").val();
	var certificatenum=$("#certificatenum").val();
	var forestid=$("#forestid").val();
	var cutaddress=$("#cutaddress").val();
	var cutvillage=$("#cutvillage").val();
	var quartel=$("#quartel").val();
	var largeblock=$("#largeblock").val();
	var smallblock=$("#smallblock").val();
	var cuttype=$("#cuttype").val();
	var cutmethod=$("#cutmethod").val();
	var cutqiang=$("#cutqiang").val();
	var cutarea=$("#cutarea").val();
	var starttime=$("#starttime").val();
	var endtime=$("#endtime").val();
	var volume=$("#lvolume").val();
	var sizewoodall=$("#lsize4").val();
	var smalltimberall=$("#lsmall4").val();
	var shorttimberall=$("#lshort4").val();
	var firewoodall=$("#lfire4").val();
	var length=$("#tabletree tr").length;
	length=length-3;//去掉合计与文件
	//alert(length);
	if(cutnum=="")
		{
		alert("请输入采伐证编号！");
		}
	else{
		for(var id=1;id<=length;id++){
    		var group=[];
    		group[0]=document.getElementById("ltree"+id+"").value;
    	    group[1]=document.getElementById("ltot"+id+"").value;
    	    group[2]=document.getElementById("lsize"+id+"").value;
    	    group[3]=document.getElementById("lsmall"+id+"").value;
    	    group[4]=document.getElementById("lshort"+id+"").value;
    	    group[5]=document.getElementById("lfire"+id+"").value;
    	    if(group[0]==""|| group[1]==""|| group[2]=="" || group[3]==""|| group[4]==""|| group[5]=="")
    	    	{
    	    	alert("请将信息填写完整！");
    	    	}
    	    else{
               map[id-1]=group;
    	    }
    	}
    	kk=length;
    var mymap=JSON.stringify(map);
    //alert( mymap);
    $.ajax({
        url:"cutnumServlet",
        data:{
            "action":"cutnumUpdate",
            "newtree":mymap,
            "id":kk,
            "cutnum":cutnum,
            "company":company,
            "certificatenum":certificatenum,
            "forestid":forestid,
            "cutaddress":cutaddress,
            "cutvillage":cutvillage,
            "quartel":quartel,
            "largeblock":largeblock,
            "smallblock":smallblock,
            "cuttype":cuttype,
            "cutmethod":cutmethod,
            "cutqiang":cutqiang,
            "cutarea":cutarea,
            "starttime":starttime,
            "endtime":endtime,
            "volume":volume,
            "sizewoodall":sizewoodall,
            "smalltimberall":smalltimberall,
            "shorttimberall":shorttimberall,
            "firewoodall":firewoodall, 
        },
        type: "POST",
        dataType:"html",
        success: function (data) {
        	//alert(data);
        	if(data>0)
        		{
        	        alert("更新成功！");
        	        window.location.href = 'manageCutnumsee.jsp';
        		}
        	else{
        		 alert("更新失败");
        		 //window.location.href = 'manageCutnumsee.jsp';
        	}
        }
    })
    }
}
//利用fromdata保存文件
function addcutnum()
{
	//var formData = new FormData($('#myform'));
	//formData.append('file', $('#file'));
	//formData.append('cutnum', $('#cutnum'));
	//alert(formData);
    $.ajax({
        url:"cutnumServlet?action=addcutnum1",
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
        	        alert("保存成功！");
        	        window.location.href = 'manageCutnumsee.jsp';
        		}
        	else{
        		 alert("保存失败");
        		 window.location.href = 'manageCutnumsee.jsp';
        	}
        }
    })
}
// function total1(){
// 	var total1=Number(document.getElementById("lsize1").value) + Number(document.getElementById("lsmall1").value) + Number(document.getElementById("lshort1").value);
// 	vvv=total1.toFixed(2);
// 	document.getElementById("ltot1").value=Number(vvv);	
// }
// function total2(){
// 	var total2=Number(document.getElementById("lsize2").value) + Number(document.getElementById("lsmall2").value) + Number(document.getElementById("lshort2").value);
// 	vvv=total2.toFixed(2);
// 	document.getElementById("ltot2").value=Number(vvv);
// }
// function total3(){
// 	var total3=Number(document.getElementById("lsize3").value) + Number(document.getElementById("lsmall3").value) + Number(document.getElementById("lshort3").value);
// 	vvv=total3.toFixed(2);
// 	document.getElementById("ltot3").value=Number(vvv);
// }
function totalALL(id){
	//alert(id);
	var total3=Number(document.getElementById("lsize"+id+"").value) + Number(document.getElementById("lsmall"+id+"").value) + Number(document.getElementById("lshort"+id+"").value);
	vvv=total3.toFixed(2);
	document.getElementById("ltot"+id+"").value=Number(vvv);
}
function tvolume(){
	var volume=Number(document.getElementById("ltot1").value) + Number(document.getElementById("ltot2").value) + Number(document.getElementById("ltot3").value);
	vvv=volume.toFixed(2);
	document.getElementById("lvolume").value=Number(vvv);
}
function sizeTall(){
	var size4=Number(document.getElementById("lsize1").value) + Number(document.getElementById("lsize2").value) + Number(document.getElementById("lsize3").value);
	vvv=size4.toFixed(2);
	document.getElementById("lsize4").value=Number(vvv);
}
function smallTall(){
	var length=$("#tabletree tr").length;
	length=length-3;//去掉合计与文件
	//alert(length);
	var smalltall=0;
	for(var id=1;id<=length;id++){
		smalltall+=Number(document.getElementById("lsmall"+id+"").value);
		vvv=smalltall.toFixed(2);
		document.getElementById("lsmall4").value=Number(vvv);
	}
}
//短小材
function shortTall(){
	var length=$("#tabletree tr").length;
	length=length-3;//去掉合计与文件
	//alert(length);
	var shorttall=0;
	for(var id=1;id<=length;id++){
		shorttall+=Number(document.getElementById("lshort"+id+"").value);
		vvv=shorttall.toFixed(2);
		document.getElementById("lshort4").value=Number(vvv);
	}
}
//薪材
function fireTall(){
	var length=$("#tabletree tr").length;
	length=length-3;//去掉合计与文件
	//alert(length);
	var firetall=0;
	for(var id=1;id<=length;id++){
		firetall+=Number(document.getElementById("lfire"+id+"").value);
		vvv=firetall.toFixed(2);
		document.getElementById("lfire4").value=Number(vvv);
	}
}
window.onload = function () {
    locationInput = function () {
    };
}
</script>
<script type="text/javascript">
function toggle(tabletree) {
if (document.getElementById){
 target = document.getElementById(tabletree);
  if ( target.style.display == "none") {
   target.style.display = ""; 
  } else { 
   target.style.display = "none";
  }
 }
}
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
  <!-- 
    <li> <a href="cutnumApplysee.jsp"><i class="icon icon-home"></i> <span>审核采伐证申请</span></a> </li> 
  --> 
    <li> <a href="manageCutnum.jsp"><i class="icon icon-inbox"></i> <span>录入采伐证</span></a></li>
    <li> <a href="manageCutnumsee.jsp"><i class="icon icon-th"></i> <span>采伐证汇总</span></a></li>
    <li> <a href="manageCutnumseeDelate.jsp"><i class="icon icon-th"></i><span>采伐证退证</span></a></li>
    <li> <a href="manageCutnumseeUse.jsp"><i class="icon icon-th"></i> <span>采伐证使用情况</span></a></li>   
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
			<i class="tab-i"></i> 所在位置：<span>查看采伐证材料详情</span>
		</div>
		<div class="find-top">
			<p class="p-tail">
				<i class="i-tail"></i> 该页面主要是管理部门查看采伐证材料详情
			</p>
		</div>
		<div class="find-top1">
		<!-- 	
		<form onSubmit="return inputNull(this)" action="cutnumServlet?action=cutnumUpdate" method="POST">
		-->			
		<form onSubmit="return inputNull(this)" action="1" method="POST">
				<div class="top" id="divprint">
				<table  class="table1" border="1">
			<caption class="book_h01">查看采伐证材料详情</caption>
			<tr>
				<td >林权单位</td>
				<td colspan="2"><input  style="border:0px;background-color: transparent;text-align:center;width: 230px;" type="text" id="company" name="company" value="<%=c.getCompany()%>"/></td>
				<td>采伐证号</td>
				<td colspan="2"><input  style="border:0px;background-color: transparent;text-align:center;width: 300px;" type="text" readonly="readonly" id="certificatenum" name="certificatenum" value="<%=c.getCertificatenum()%>"/></td>
			</tr>
			<tr>
			 <td>林权证号</td>
			 <td colspan="2"><input  style="border:0px;background-color: transparent;width: 400px;" type="text" id="forestid" name="forestid" value="<%=c.getForestid()%>"></td>
			 <td>采伐证编号</td>
				<td colspan="2"><input  style="border:0px;background-color: transparent;width: 300px;" type="text" id="cutnum" readonly="readonly" name="cutnum" value="<%=c.getCutnum()%>"></td>
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
				<td colspan="2"><input style="border:0px;background-color: transparent;width: 300px;" type="text" id="cutqiang" name="cutqiang" value="<%=c.getCutqiang() %>"></td>
				<td>采伐面积(公顷)</td>
				<td colspan="2"><input  style="border:0px;background-color: transparent;width: 300px;"type="text" id="cutarea" name="cutarea" value="<%=c.getCutarea()%>"></td>		   
			</tr>
			
			<tr>
				<td>采伐开始时间</td>
				<td colspan="2"><input style="border:0px;background-color: transparent;width: 300px;" type="date" name="starttime" id="starttime" value="<%=c.getStarttime()%>"></td>
				<td >采伐截止时间</td>
				<td colspan="2"><input style="border:0px;background-color: transparent;width: 300px;" type="date" name="endtime" id="endtime" value="<%=c.getEndtime()%>"></td>
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
		     <td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type='text' name="treetype" id="ltree<%=i%>" value="${b.getTreetype()}"></td>         
		     <td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type='text' name="total"  id='ltot<%=i%>' value="${b.getTotal()}"></td>
		     <td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type='text'  name="sizewood" id='lsize<%=i%>' value="${b.getSizewood()}" oninput='sizeTall("<%=i%>")&totalALL("<%=i%>")&tvolume("<%=i%>")' onclick='locationInput'></td>
		     <td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type='text'  name="smalltimber" id='lsmall<%=i%>' value="${b.getSmalltimber()}" oninput='smallTall("<%=i%>")&totalALL("<%=i%>")&tvolume("<%=i%>")' onclick='locationInput'></td>
		     <td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type='text'  name="shorttimber" id='lshort<%=i%>' value="${b.getShorttimber()}" oninput='shortTall("<%=i%>")&totalALL("<%=i%>")&tvolume("<%=i%>")' onclick='locationInput'></td>
		     <td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type='text'  name="firewood" id='lfire<%=i%>' value="${b.getFirewood()}" oninput='fireTall("<%=i%>")' onclick='locationInput'></td>
		     </tr>     
        <%i++; %>
             </c:forEach>

		    <tr>   <!--，此行为出材量、总计 -->
		    <th><input style="border:0px;background-color: transparent"  type="text"   value="合计(出材量)"></th>
		    <td><input style="border:0px;background-color: transparent"  type="text"   name="volume" id='lvolume' value="<%=c.getVolume()%>"></td>
		    <td><input style="border:0px;background-color: transparent"  type="text"   name="sizewoodAll" id='lsize4' value="<%=c.getSizewoodAll()%>"></td>
		    <td><input style="border:0px;background-color: transparent"  type="text"   name="smalltimberAll" id='lsmall4' value="<%=c.getSmalltimberAll()%>"></td>
		    <td><input style="border:0px;background-color: transparent"  type="text"   name="shorttimberAll" id='lshort4' value="<%=c.getShorttimberAll()%>"></td>
		    <td><input style="border:0px;background-color: transparent"  type="text"  name="firewoodAll" id='lfire4' value="<%=c.getFirewoodAll()%>"></td>
		    </tr>
		    </table>
		  <br>
		  <%-- <div style="text-align:center">
          <button  class="btnt" type="button"  value="修改出材量" onclick="toggle('tabletree');" style="width:200px"><span style="font-size:22px">修改出材量</span></button>
          </div>
		  <table id="tabletree2" class="table1" border="1" style="display: none">
		    <tr>
		     <th colspan="6">出材量</th>
		    </tr>
		    <tr>
		    <th>树种</th>    <!--treetype  -->
		    <th>小计</th>     <!-- 暂定total -->
		    <th>规格材</th>  <!--sizewood  -->
		    <th>小径材(非规格材)</th>  <!--smalltimber  -->
		    <th>短小材(非规格材)</th>   <!-- shorttimber -->
		    <th>薪碳材</th>             <!-- firewood -->
		    </tr>
		    <tr id="1">
		    <th><input style="border:0px;background-color: transparent" type="text" name="treetype" id='ltree1'  value="杉木"></th>
		    <td><input style="border:0px;background-color: transparent" type="text" name="total" id='ltot1' readonly="readonly" value=0 ></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="sizewood" id='lsize1' oninput='sizeTall()&total1()&tvolume()' onclick='locationInput'  value="0" onfocus="if(this.value=='0') this.value=''" onblur="if(this.value=='') this.value='0'" /></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="smalltimber" id='lsmall1' oninput='smallTall()&total1()&tvolume()' onclick='locationInput'   value="0" onfocus="if(this.value=='0') this.value=''" onblur="if(this.value=='') this.value='0'" /></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="shorttimber" id='lshort1' oninput='total1()' onclick='locationInput' oninput='shortTall()&total1()&tvolume()' onclick='locationInput'  value="0" onfocus="if(this.value=='0') this.value=''" onblur="if(this.value=='') this.value='0'" /></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="firewood" id='lfire1'oninput='fireTall()' onclick='locationInput'  value="0" onfocus="if(this.value=='0') this.value=''" onblur="if(this.value=='') this.value='0'" /></td>
		    </tr>
		    <tr id="2">
		    <th><input style="border:0px;background-color: transparent"  type="text" name="treetype" id='ltree2'  value="松木"></th>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="total" id='ltot2' readonly="readonly" value=0></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="sizewood" id='lsize2'oninput='sizeTall()&total2()&tvolume()' onclick='locationInput'  value="0" onfocus="if(this.value=='0') this.value=''" onblur="if(this.value=='') this.value='0'" /></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="smalltimber" id='lsmall2'oninput='smallTall()&total2()&tvolume()' onclick='locationInput'  value="0" onfocus="if(this.value=='0') this.value=''" onblur="if(this.value=='') this.value='0'" /></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="shorttimber" id='lshort2' oninput='shortTall()&total2()&tvolume()' onclick='locationInput'  value="0" onfocus="if(this.value=='0') this.value=''" onblur="if(this.value=='') this.value='0'" /></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="firewood" id='lfire2' oninput='fireTall()' onclick='locationInput'   value="0" onfocus="if(this.value=='0') this.value=''" onblur="if(this.value=='') this.value='0'" /></td>
		    </tr>
		    <tr id="3">
		    <th><input style="border:0px;background-color: transparent"  type="text" name="treetype" id='ltree3'  value="阔叶林"></th>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="total" id='ltot3' readonly="readonly" value=0></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="sizewood" id='lsize3' oninput='sizeTall()&total3()&tvolume()' onclick='locationInput'onclick='locationInput' value="0" onfocus="if(this.value=='0') this.value=''" onblur="if(this.value=='') this.value='0'" /></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="smalltimber" id='lsmall3' oninput='smallTall()&total3()&tvolume()' onclick='locationInput' value="0" onfocus="if(this.value=='0') this.value=''" onblur="if(this.value=='') this.value='0'" /></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="shorttimber" id='lshort3' oninput='shortTall()&total3()&tvolume()' onclick='locationInput' value="0" onfocus="if(this.value=='0') this.value=''" onblur="if(this.value=='') this.value='0'" /></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="firewood" id='lfire3'  onclick='locationInput' value="0" onfocus="if(this.value=='0') this.value=''" onblur="if(this.value=='') this.value='0'" /></td>
		    </tr>
		    <tr>   <!--，此行为出材量、总计 -->
		    <th><input style="border:0px;background-color: transparent"  type="text" name="chucai" id='chucai' value="合计(出材量)"></th>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="volume" id='lvolume' onclick="tvolume()" readonly="readonly" value=0></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="sizewoodAll" id='lsize4' onclick="sizeTall()" readonly="readonly" value=0></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="smalltimberAll" id='lsmall4' onclick="sizeTall()" readonly="readonly" value=0></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="shorttimberAll" id='lshort4' onclick="shortTall()" readonly="readonly" value=0></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="firewoodAll" id='lfire4' onclick="fireTall()" readonly="readonly" value=0></td>
		    </tr>
		</table>--%>
		<br>
				</div>
				<div style="text-align:center">
				<button class="btn" type="button" onclick="cutnumtreeUp()"  value="更新采伐证">更新采伐证</button>
				<button class="btn" style="width:200px" type="button" value="返回" onclick="javascrtpt:window.location.href='manageCutnumsee.jsp'">返回采伐证信息列表</button>
				<button class="btn" type="button" id="btnPrint" value="打印">打印</button>
				</div>
				
				<div style="clear: both; padding-bottom: 40px"></div>
			</form>

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