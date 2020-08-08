<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Connection" %>
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
   <title>货场费用</title>
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
	//求和货场费用
	function QiuHe(){
    document.getElementById("totolCost").value=(parseInt(document.getElementById("gouJiZD").value)+parseInt(document.getElementById("renGongZD").value)+
    		parseInt(document.getElementById("zhuangCheGZ").value)+parseInt(document.getElementById("changDiWH").value)+
    		parseInt(document.getElementById("changDiZJ").value)+parseInt(document.getElementById("dianFei").value)+
    		parseInt(document.getElementById("kanHuGZ").value)+parseInt(document.getElementById("sheBeiWH").value)+
    		parseInt(document.getElementById("yunFei").value)+parseInt(document.getElementById("surveyorFee").value));
	}
</script>
<script type="text/javascript">
function feeAdd()
{
	var map={};
	var kk=Number(0),ki=Number(0);
	var LuRuDate=$("#LuRuDate").val();
	var yard=$("#yard").val();
	var luruperson=$("#luruperson").val();
	//var cutnum=document.getElementById("cutnum").value;
	if(yard=="")
		{
		alert("请先输入货场！");
		}
	else{
    $("input[type='checkbox']:checked").each(function(i){
    	var group=[];
    	var id=$(this).val();
    	//alert(id);
    	//alert(document.getElementById("fy"+id+"").value);
    	    group[0]=document.getElementById("fy"+id+"").value;
    	    group[1]=document.getElementById("num"+id+"").value;
    	    group[2]=document.getElementById("unit"+id+"").value;
    	    group[3]=document.getElementById("up"+id+"").value;
    	    group[4]=document.getElementById("pr"+id+"").value;
    	    group[5]=document.getElementById("rmk"+id+"").value;
    	    
    	    if(group[0]==""|| group[1]==""|| group[2]=="" || group[3]==""|| group[4]==""|| group[5]=="")
    	    	{
    	    	alert("请将信息填写完整！");
    	    	}
    	      else{
               map[i]=group;
               kk=Number(i)+Number(1);
    	    }
    });
    $("input[type='checkbox']:not(:checked)").each(function(j){
    	var group=[];
    	var id=$(this).val();
    	group[0]=document.getElementById("fy"+id+"").value;
	    group[1]=document.getElementById("num"+id+"").value;
	    group[2]=document.getElementById("unit"+id+"").value;
	    group[3]=document.getElementById("up"+id+"").value;
	    group[4]=document.getElementById("pr"+id+"").value;
	    group[5]=document.getElementById("rmk"+id+"").value;
	    //System.out.println("...." + group[4] + "...");
	    if(group[0]==""|| group[1]==""|| group[2]=="" || group[3]==""|| group[4]==""|| group[5]=="")
	    	{
	    	alert("请将信息填写完整！");
	    	}
	        else{ 
        map[kk+j]=group;
        ki=Number(j)+Number(1);
	    }
    }); 
    var mymap=JSON.stringify(map);
    $.ajax({
        url:"workpageSevrlet",
        data:{
            "action":"feeAdd",
            "newtree":mymap,
            "id":ki+kk,
            "LuRuDate":LuRuDate,
            "yard":yard,
            "luruperson":luruperson     
        },
        type: "POST",
        dataType:"html",
        success: function (data) {
        	alert(data);
        	if(data>0)
        		{
        	        alert("保存成功！");
        		}
        	else{
        		 alert("保存失败");
        	}
        }
    })
    }
}
</script>
</head>
<body>
<% Date d = new Date();

SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

String now = df.format(d); %>
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
      <li><a href="goodsYardCost2.jsp"><i class="icon icon-home"></i><span>费用结算</span></a></li>
    <li><a href="yardMonery.jsp"><i class="icon icon-home"></i> <span>费用统计</span></a> </li>
  </ul>
</div>
<!--sidebar-menu-->

<div id="content">
<div id="content-header">
    <div id="breadcrumb"> <a href="yardstaffC.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> 首页</a></div>
  </div>
<!--End-breadcrumbs-->
<main>
    <div class="find-top">
        <p class="p-tail"><i
         class="i-tail"></i> 该页面主要是管理部门上传货场费用表</p>
    </div>
    <div class="find-top1">
    <form>
    <div class="top" id="divprint">
		<div id="table">
		<table>
		<caption class="book_h01">货场费用录入</caption>
		   <tr>
		     <td></td>
		     <td colspan="1">录入时间:</td><td colspan="2"><input style="width: 240px; font-size:22px" type="text" name="LuRuDate" id="LuRuDate" value="<%=now%>"></td>
		     <td colspan="1">货场:</td><td colspan="2"><input style="width: 172px;" type="text" name="yard" id="yard"></td>
		    </tr>
		    <tr>
		    <th>选择框</th>
		    <th>项目</th>
		    <th>数量</th>
		    <th>单位(m3、T、H)(月、车、人)</th>
		    <th>单价</th>
		    <th>金额</th>
		    <th>备注</th>
		    </tr>
		    <tr id="1">
		    <td><input type='checkbox' style='width:20px;height:20px;' value="1"></td>
		    <td><input type="text" name="feetype" id='fy1'  value="钩机整堆"></td>
		    <td><input type="text" name="num" id='num1'></td>
		    <td><input type="text" name="unit" id='unit1'></td>
		    <td><input type="text" name="unitprice" id='up1'></td>
		    <td><input type="text" name="price" id='pr1'></td>
		    <td><input type="text" name="remarks" id='rmk1'></td>
		    </tr>
		    <tr id="2">
		    <td><input type='checkbox' style='width:20px;height:20px;' value="2"></td>
		    <td><input type="text" name="feetype" id='fy2'  value="人工整堆"></td>
		    <td><input type="text" name="num" id='num2'></td>
		    <td><input type="text" name="unit" id='unit2'></td>
		    <td><input type="text" name="unitprice" id='up2'></td>
		    <td><input type="text" name="price" id='pr2'></td>
		    <td><input type="text" name="remarks" id='rmk2'></td>
		    </tr>
		    <tr id="3">
		    <td><input type='checkbox' style='width:20px;height:20px;' value="3"></td>
		    <td><input type="text" name="feetype" id='fy3'  value="装车工资"></td>
		    <td><input type="text" name="num" id='num3'></td>
		    <td><input type="text" name="unit" id='unit3'></td>
		    <td><input type="text" name="unitprice" id='up3'></td>
		    <td><input type="text" name="price" id='pr3'></td>
		    <td><input type="text" name="remarks" id='rmk3'></td>
		    </tr>
		    <tr id="4">
		    <td><input type='checkbox' style='width:20px;height:20px;' value="4"></td>
		    <td><input type="text" name="feetype" id='fy4' value="场地维护"></td>
		    <td><input type="text" name="num" id='num4'></td>
		    <td><input type="text" name="unit" id='unit4'></td>
		    <td><input type="text" name="unitprice" id='up4'></td>
		    <td><input type="text" name="price" id='pr4'></td>
		    <td><input type="text" name="remarks" id='rmk4'></td>
		    </tr>
		    <tr id="5">
		    <td><input type='checkbox' style='width:20px;height:20px;' value="4"></td>
		    <td><input type="text" name="feetype" id='fy5' value="场地资金"></td>
		    <td><input type="text" name="num" id='num5'></td>
		    <td><input type="text" name="unit" id='unit5'></td>
		    <td><input type="text" name="unitprice" id='up5'></td>
		    <td><input type="text" name="price" id='pr5'></td>
		    <td><input type="text" name="remarks" id='rmk5'></td>
		    </tr>
		    <tr id="6">
		    <td><input type='checkbox' style='width:20px;height:20px;' value="6"></td>
		    <td><input type="text" name="feetype" id='fy6'  value="看护工资"></td>
		   <td><input type="text" name="num" id='num6'></td>
		    <td><input type="text" name="unit" id='unit6'></td>
		    <td><input type="text" name="unitprice" id='up6'></td>
		    <td><input type="text" name="price" id='pr6'></td>
		    <td><input type="text" name="remarks" id='rmk6'></td>
		    </tr>
		    <tr id="7">
		    <td><input type='checkbox' style='width:20px;height:20px;' value="7"></td>
		    <td><input type="text" name="feetype" id='fy7'  value="电费"></td>
		    <td><input type="text" name="num" id='num7'></td>
		    <td><input type="text" name="unit" id='unit7'></td>
		    <td><input type="text" name="unitprice" id='up7'></td>
		    <td><input type="text" name="price" id='pr7'></td>
		    <td><input type="text" name="remarks" id='rmk7'></td>
		    </tr>
		    <tr id="8">
		    <td><input type='checkbox' style='width:20px;height:20px;' value="8"></td>
		    <td><input type="text" name="feetype" id='fy8'  value="设备维护"></td>
		    <td><input type="text" name="num" id='num8'></td>
		    <td><input type="text" name="unit" id='unit8'></td>
		    <td><input type="text" name="unitprice" id='up8'></td>
		    <td><input type="text" name="price" id='pr8'></td>
		    <td><input type="text" name="remarks" id='rmk8'></td>
		    </tr>
		    <tr id="9">
		    <td><input type='checkbox' style='width:20px;height:20px;' value="9"></td>
		    <td><input type="text" name="feetype" id='fy9'  value="运费"></td>
		    <td><input type="text" name="num" id='num9'></td>
		    <td><input type="text" name="unit" id='unit9'></td>
		    <td><input type="text" name="unitprice" id='up9'></td>
		    <td><input type="text" name="price" id='pr9'></td>
		    <td><input type="text" name="remarks" id='rmk9'></td>
		    </tr>
		    <tr id="10">
		    <td><input type='checkbox' style='width:20px;height:20px;' value="10"></td>
		    <td><input type="text" name="feetype" id='fy10'  value="检尺费"></td>
		    <td><input type="text" name="num" id='num10'></td>
		    <td><input type="text" name="unit" id='unit10'></td>
		    <td><input type="text" name="unitprice" id='up10'></td>
		    <td><input type="text" name="price" id='pr10'></td>
		    <td><input type="text" name="remarks" id='rmk10'></td>
		    </tr>
		    <tr>
		    <td colspan="2">部门经理</td><td></td>
		    <td>货场管理员</td><td></td>
		    <td>填单人</td><td><input type="text" name="luruperson" id="luruperson"></td>
		    </tr>
		</table>
		</div>
		</div>
		
		<div class="1"  style="text-align:center">

		    <span  style="text-align: center;"><button class="btn" type="button" onclick="feeAdd()" value="保存申请书">保存</button></span>
		     <span  ><button class="btn" type="button" id="btnPrint" value="打印">打印</button></span> 
		 </div>
			<div style="clear: both;padding-bottom: 40px"></div>
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
$(function(){
    $("#btnPrint").click(function(){ $("#divprint").printArea(); });
});
</script>
</body>
</html>