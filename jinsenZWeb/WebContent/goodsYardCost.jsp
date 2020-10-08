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
  <style type="text/css">
        .table_p{line-height: 28px;border-bottom: 1px #d0e6ec solid;position: relative;margin-bottom: 10px; 
            margin-top: 35px; margin-left:10px}
        .table_p span{border-bottom: 3px #42cdec solid;display: inline-block;position: absolute;bottom: -1px;font-weight: bold;font-size: 20px}
        .but_p button{width: 80px}
        #h li{float: left; }
#h a{font-size:15px;width: 230px; height: 30px;padding: 10px 0;text-align: center;  background: #3c763d; display: block; text-decoration:none; color:white}
#h a:hover{color:white;background: green}
.table1{margin-left:auto; margin-right:auto;padding:10px;border-collapse:collapse}
.btn{ 	
	font-family: "'微软雅黑','Helvetica Neue',Helvetica,Arial,sans-serif"; 	
	font-size: 20px!important; 	height: 30px; 	
	line-height: 18px!important; 	
	padding: 3px 18px; 	
	display: inline-block; 	vertical-align: middle; 	
	font-weight: normal; 	border-radius: 3px; 	
	margin: 0 8px 0 3px; 	
	border: 1px solid #3383da; 	
	color: #ffffff; 	
	background-color: #3383da; 	
	cursor: pointer;
	width:120px;height:40px; 
	}
.btna,.btnb,.btnc,.btnd,.btnred{ 	
	font-family: "'微软雅黑','Helvetica Neue',Helvetica,Arial,sans-serif"; 	
	font-size: 40px!important; 	height: 30px; 	
	line-height: 18px!important; 	
	padding: 3px 18px; 	
	display: inline-block; 	vertical-align: middle; 	
	font-weight: blod; 	border-radius: 3px; 	
	margin: 0 8px 0 3px; 	
	border: 1px solid #FFA500; 	
	color: #ffffff; 		
	cursor: pointer; 
	width:200px;height:80px;
	margin-right:30px
	}
.btna {background-color: #3383da;}
.btnb {background-color: #F08080;}
.btnc {background-color: #F4A460}
.btnd {background-color: #CC99FF}
.btnred {background-color: red;}
    </style>
    
<script type="text/javascript">
var k=Number(1);
$(function(){
	aaad();
})
function aaad()
{
 	var str="<tr id="+k+" display:block;><td style='font-size:20px;'><input type='checkbox' style='width:20px;height:20px;' value="+k+">"
 	+"项目<span></span>"
    +"<select style='width: 180px' name='feetype' id='fy"+k+"'>"
    +"<option>请选择</option>"
    +"<option value='钩机整堆'>钩机整堆</option>"
    +"<option value='人工整堆'>人工整堆</option>"
    +"<option value='装车工资'>装车工资</option>"
    +"<option value='场地维护'>场地维护</option>"
    +"<option value='场地资金'>场地资金</option>"
    +"<option value='看护工资'>看护工资</option>"
    +"<option value='电费'>电费</option>"
    +"<option value='设备维护'>设备维护</option>"
    +"<option value='运费'>运费</option>"
    +"<option value='检尺费'>检尺费</option></select>"
 	+"数量<span></span><input type='text' style='width: 180px' name='num' id='num"+k+"' >"
    +"单位<span></span>"
    +"<select style='width: 180px' name='unit' id='unit"+k+"'>"
    +"<option>请选择</option>"
    +"<option value='m³'>m³</option>"
    +"<option value='T'>T</option>"
    +"<option value='H'>H</option>"
    +"<option value='天'>天</option>"
    +"<option value='月'>车</option>"
    +"<option value='人'>人</option>"
    +"<option value='个'>个</option></select>"
    +"单价<span></span><input type='text' style='width: 180px' name='unitprice' id='up"+k+"' oninput='vvvolume("+k+")' onclick='locationInput'>"
    +"金额<span></span><input type='text' style='width: 180px' name='price' id='pr"+k+"' oninput='vvvolume("+k+")' onclick='locationInput'>"
    +"备注<span></span><input type='text' style='width: 180px' name='remarks' id='rmk"+k+"' value='无' ></td></tr>";
    k=Number(k)+Number(1);
    $("#ttt5").append(str);             
}

function dddelete()
{
	var ck= $("input[type='checkbox']:checked");
	for (var i=0;i<ck.length;i++)
		{
		     var j=$(ck[i]).val();		     
		     $("#"+j+"").empty();
		}
	alert("删除成功");
}
function deleteAll()
{
	var mk=$("input[type='checkbox']:checked");
	var index=$("input[type='checkbox']:not(:checked)");
	for (var i=0;i<mk.length;i++)
    {
	    var h=$(mk[i]).val();
	    
	     $("#"+h+"").empty();
	}
   for (var a=0;a<index.length;a++)
	{
	    var hh=$(index[a]).val();
	    $("#"+hh+"").empty();
	}
}
/*function vvvolume(id)
{
	var td=$("#td"+id+"").val();
	var tr=$("#tr"+id+"").val();
	var num=$("#n"+id+"").val();
	if(num!="" && td!="" && tr!="")
	{
	$.ajax({
        url:"treeServlet",
        data:{
            "action":"volume",
            "td":td,
            "tr":tr 
        },
        type: "POST",
        dataType:"html",
        success: function (data) {
        	document.getElementById("tv"+id+"").value=Number(data*num);
        }
    })}
	}*/

	//鼠标锁定
window.onload = function () {
        locationInput = function () {
        };
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
<script type="text/javascript">
function toggle(table5) {
if (document.getElementById){
 target = document.getElementById(table5);
  if ( target.style.display == "none") {
   target.style.display = ""; 
  } else { 
   target.style.display = "none";
  }
 }
}
</script>


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
	//计算金额
	function priceCount(id)
	{
		
		var volume=document.getElementById("num"+id+"").value;
		//alert(volume)
		var unitprice=document.getElementById("up"+id+"").value;
		var vvvo=volume*unitprice;
		var vvvo=vvvo.toFixed(2);
		document.getElementById("pr"+id+"").value=vvvo;
	}
	window.onload = function () {
	    locationInput = function () {
	    };
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
	if(yard=="请选择")
		{
		alert("请先选择货场！");
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
        	//alert(data);
        	if(data>0)
    		{
    	        alert("保存成功！"   );
    	        window.location.href = 'goodsYardCost.jsp';
    		}
    	else{
    		 alert("保存失败");
 	        window.location.href = 'goodsYardCost.jsp';
        	}
        }
    })
    }
}
//获取货场信息
function load()
{
	$.ajax({
        url:"salaryServlet",//要发送的地址
        data:{
            "action":"loadyardinfo"
        },
        type: "POST",
        dataType:"json",
        success: function (data) {
            for(i = 0;i<data.length;i++)
            {
            	str = "<option>"+data[i].yardname+"</option>";
            	
            	$("#yard").show().append(str);
            }
        }
    })
}
</script>

</head>
<body onload="load()">

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
        <li><a href="ChangePassword.jsp"><i class="icon-user"></i> 我的个人资料 </a></li>
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
    <li class=""><a title="" href="ChangePassword.jsp"><i class="icon icon-cog"></i> <span class="text">设置</span></a></li>
    <li class=""><a title="" href="./logout"><i class="icon icon-share-alt"></i> <span class="text">注销</span></a></li>
    <li>
    <%
	String staff_id = request.getSession().getAttribute("staff_id").toString();
				%> <%
 	String staff_name = request.getSession().getAttribute("staff_name").toString();
	String power_type = request.getSession().getAttribute("power_type").toString();
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
      <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>货场费用</span> <span class="label label-important">6</span></a>
       <ul>
        <li ><a href="goodsYardCost.jsp">货场费用录入</a></li>
        <li ><a href="yardInventoryBootstrapDirector.jsp">货场盘点</a></li>
        <li ><a href="yardMonerySettle.jsp">货场管理费用</a></li>
        <li><a href="yardMoneryDirector.jsp">货场管理费用台账</a></li>
        <li><a href="workpageTreeBuyDirector.jsp">木材收购单</a></li>
        <li ><a href="cancellingStocksTable.jsp">货场报损</a> </li>       
      </ul>
     </li>
    <li><a href="compareTreeListDirector.jsp"><i class="icon icon-home"></i> <span>木材装车对比</span></a></li>
    <li><a href="InyardShenhesuDirector.jsp"><i class="icon icon-home"></i> <span>查看进场木材反馈</span></a></li>
    <li><a href="yardinfo.jsp"><i class="icon icon-home"></i> <span>货场信息管理</span></a></li>
    <li><a href="SurveyorAddinfo.jsp"><i class="icon icon-home"></i> <span>检尺员管理</span></a></li>
    <li><a href="saleCalloutOrderShenhe.jsp"><i class="icon icon-home"></i> <span>审批调令</span></a></li>
  </ul>
</div>
<!--sidebar-menu-->

<div id="content">
<div id="content-header">
    <div id="breadcrumb"> <a href="yardDirector.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> 首页</a></div>
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
		<table class="table1">
		 <table  class="table1">
		 		<caption class="book_h01">货场费用录入</caption>		
		   <tr>
		     <td></td>
		     <td colspan="1">录入时间:</td><td><input style="width: 240px; font-size:22px" type="text" name="LuRuDate" id="LuRuDate" value="<%=now%>"></td>
             <td colspan="1"><font color=red>*</font>货场</td><td><select name="yard" id="yard"><option selected="selected">请选择</option></select></td>		 
		    </tr>
		   </table>
		                 <p class="table_p"><span>树材信息录入</span></p>
                        <div style="text-align:center">
                        <button  class="btna" type="button"   onclick="toggle('table5');"><span style="font-size:40px">添加项目</span></button>
                        <button class="btnred" type="button"  onclick="deleteAll()" value="清空"><span style="font-size:40px">清空</span></button>
                        </div>
                        <br>
                        <table class="table" id="table5" style="width:100%;height:auto;display: none">
                           <tbody id="ttt5">                                
                            </tbody>
                            <tr>
                            <td>
                            <div>
                            <button class="btn btn-warning" type="button"  onclick="aaad()" value="添加项目">添加项目</button>
                            <button class="btn btn-warning" type="button"  onclick="dddelete()" value="删除">删除</button>
                             <!--  <button class="btn btn-warning" type="button"  onclick="deleteAll()" value="清空">清空</button>-->
                            </div>
                            </td>
                            </tr>
                            </table>

		   	   
		    <table  class="table1">
		    <tr>
		    <td colspan="2">部门经理</td><td></td>
		    <td>货场经理</td><td></td>
		    <td><font color=red>*</font>填单人</td><td><input type="text" name="luruperson" id="luruperson" value="<%=staff_name%>" readonly></td>
		    </tr>
		    </table>
		</table>
		</div>
		</div>
		
		<div class="1"  style="text-align:center">

		    <!-- <span  style="text-align: center;"><button class="btn" type="submit" id="mybutton" value="保存申请书">保存</button></span> -->
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