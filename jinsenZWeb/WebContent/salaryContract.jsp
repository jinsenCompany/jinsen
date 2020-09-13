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
   <title>输入销售合同页面</title>
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
.table2{margin-left:auto; margin-right:auto;padding:10px;border-collapse:collapse}
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
.table_p{line-height: 28px;border-bottom: 1px #d0e6ec solid;position: relative;margin-bottom: 10px; 
            margin-top: 35px; margin-left:10px}
        .table_p span{border-bottom: 3px #42cdec solid;display: inline-block;position: absolute;bottom: -1px;font-weight: bold;font-size: 20px}
        .but_p button{width: 200px}
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
	}
 .btnS{ 	
	font-family: "'微软雅黑','Helvetica Neue',Helvetica,Arial,sans-serif"; 	
	font-size: 20px!important; 	height: 30px; 	
	line-height: 18px!important; 	
	padding: 3px 18px; 	
	display: inline-block; 	vertical-align: middle; 	
	font-weight: normal; 	border-radius: 3px; 	
	margin: 0 8px 0 3px; 	
	border: 1px solid #CC99FF; 	
	color: #ffffff; 	
	background-color: #CC99FF; 	
	cursor: pointer; 
	}
 .btnR{ 	
	font-family: "'微软雅黑','Helvetica Neue',Helvetica,Arial,sans-serif"; 	
	font-size: 20px!important; 	height: 30px; 	
	line-height: 18px!important; 	
	padding: 3px 18px; 	
	display: inline-block; 	vertical-align: middle; 	
	font-weight: normal; 	border-radius: 3px; 	
	margin: 0 8px 0 3px; 	
	border: 1px solid red; 	
	color: #ffffff; 	
	background-color: red; 	
	cursor: pointer; 
	}
</style>
<script type="text/javascript">
var k=Number(1);
$(function(){
	aaad();
})
function aaad()
{
 	var str="<tr id="+k+" display:block;><td style='font-size:20px;'><input type='checkbox' style='width:20px;height:20px;' value="+k+">树材种<span></span>"
          +"<select style='width: 180px' name='treetype' id='sss"+k+"'>"
           +"<option>--请选择--</option>"
           +"<option value='杉木' selected='selected'>--杉木--</option>"
           +"<option value='松木'>--松木--</option>"
           +"<option value='杂木'>--杂木--</option>"
           +"<option value='衫短材'>--衫短材--</option>"
           +"<option value='杉木兜'>--杉木兜--</option>"
           +"<option value='杉直柄'>--杉直柄--</option>"
           +"<option value='特种材'>--特种材--</option>"
           +"<option value='其他'>--其他--</option></select>"
        +"长度(米)<span></span><input type='text' style='width: 180px' name='tlong' id='tl"+k+"' value='2-4'>"
        +"口径(厘米)<span></span><input type='text' style='width: 180px' name='tradius' id='tr"+k+"' value='8-9'>"
          +"单价(元/立方米)<span></span><input type='text' style='width: 180px' name='unitprice' id='up"+k+"' value='0' onfocus='if(this.value=='0') this.value=''' onblur='if(this.value=='') this.value='0''></td></tr>";
             k=Number(k)+Number(1);
            $("#ttt5").append(str); 
             
}
$(function(){
	aaadS();
})
function aaadS()
{
 	var str="<tr id="+k+" display:block;><td style='font-size:20px;'><input type='checkbox' style='width:20px;height:20px;' value="+k+">树材种<span></span>"
          +"<select style='width: 180px' name='treetype' id='sss"+k+"'>"
           +"<option>--请选择--</option>"
           +"<option value='杉薪' selected='selected'>--杉薪--</option>"
           +"<option value='松薪'>--松薪--</option>"
           +"<option value='杂薪'>--杂薪--</option></select>"
        +"长度(米)<span></span><input type='text' style='width: 180px' name='tlong' id='tl"+k+"' value='2-4'>"
        +"<input type='text' style='width: 180px;display:none' name='tradius' id='tr"+k+"' value=''>"
          +"单价(元/立方米、吨)<span></span><input type='text' style='width: 180px' name='unitprice' id='up"+k+"' value='0' onfocus='if(this.value=='0') this.value=''' onblur='if(this.value=='') this.value='0''></td></tr>";
             k=Number(k)+Number(1);
            $("#ttt5S").append(str); 
             
}
/**
 * 
 +"口径(厘米)<span></span><select style='width: 180px' name='tradius' id='tr"+k+"'>"
 +"<option>--请选择--</option>"
 +"<option value='3-7'>3-7</option>"
 +"<option value='7以下'>7以下</option>"
 +"<option value='8-9'>8-9</option>"
 +"<option value='10-12'>10-12</option>"
 +"<option value='14-18'>14-18</option>"
 +"<option value='20以上'>20以上</option>"
 +"<option value='20-26'>20-26</option>"
 +"<option value='20-28'>20-28</option>"
 +"<option value='28以上'>28以上</option>"
 +"<option value='20-28'>30以上</option></select>"
 */
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
function treeAdd()
{
	var map={};
	var kk=Number(0),ki=Number(0);
	var contractnum=$("#contractnum").val();
	var treenumber=$("#treenumber").val();
	var provider=$("#provider").val();
	var demander=$("#demander").val();
	var deliveryplace=$("#deliveryplace").val();
	var contractfile=$("#contractfile").val();
	var cost=$("#cost").val();
	var measurestard=$("#measurestard").val();
	var settlemethod=$("#settlemethod").val();
	var starttime=$("#starttime").val();
	var endtime=$("#endtime").val();
	var margin=$("#margin").val();
	//var cutnum=document.getElementById("cutnum").value;
	if(contractnum=="")
		{
		alert("请先输入合同编号！");
		}
	else{
    $("input[type='checkbox']:checked").each(function(i){
    	var group=[];
    	var id=$(this).val();
    	alert(id);
    	    group[0]=document.getElementById("sss"+id+"").value;
    	    group[1]=document.getElementById("tl"+id+"").value;
    	    group[2]=document.getElementById("tr"+id+"").value;
    	    group[3]=document.getElementById("up"+id+"").value;
    	    //System.out.println("...." + group[4] + "...");
    	    if(group[0]==""|| group[1]==""|| group[2]=="" || group[3]=="")
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
    	    group[0]=document.getElementById("sss"+id+"").value;
    	    group[1]=document.getElementById("tl"+id+"").value;
    	    group[2]=document.getElementById("tr"+id+"").value;
    	    group[3]=document.getElementById("up"+id+"").value;
    	    if(group[0]=="" || group[1]=="" ||group[2]=="")
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
        url:"salaryServlet",
        data:{
            "action":"addcontract",
            "newtree":mymap,
            "id":ki+kk,
            "contractnum":contractnum,
            "provider":provider,
            "demander":demander,
            "treenumber":treenumber,
            "deliveryplace":deliveryplace,
            "cost":cost,
            "measurestard":measurestard,
            "settlemethod":settlemethod,
            "starttime":starttime,
            "endtime":endtime,
            "contractfile":contractfile,
            "margin":margin
        },
        type: "POST",
        dataType:"html",
        success: function (data) {
        	alert(data);
        	/*if(data>0)
        		{
        	        alert("添加成功！");
        		}
        	else{
        		 alert("请检查输入的材积是否已经超标");
        	}*/
        }
    })
    }
}
function dddelete()
{
	var ck= $("input[type='checkbox']:checked");
	for (var i=0;i<ck.length;i++)
		{
		     var j=$(ck[i]).val();
		     alert(j);
		     $("#"+j+"").empty();
		}
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
//利用fromdata保存文件
function addsalaryContract()
{
	/* var map={};
	var kk=Number(0),ki=Number(0);
	$("input[type='checkbox']:checked").each(function(i){
    	var group=[];
    	var id=$(this).val();
    	alert(id);
    	    group[0]=document.getElementById("sss"+id+"").value;
    	    group[1]=document.getElementById("tl"+id+"").value;
    	    group[2]=document.getElementById("tr"+id+"").value;
    	    group[3]=document.getElementById("up"+id+"").value;
    	    if(group[0]==""|| group[1]==""|| group[2]=="" || group[3]=="")
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
    	    group[0]=document.getElementById("sss"+id+"").value;
    	    group[1]=document.getElementById("tl"+id+"").value;
    	    group[2]=document.getElementById("tr"+id+"").value;
    	    group[3]=document.getElementById("up"+id+"").value;
    	    if(group[0]=="" || group[1]=="" ||group[2]=="")
	    	{
	    	alert("请将信息填写完整！");
	    	}
	        else{ 
        map[kk+j]=group;
        ki=Number(j)+Number(1);
	    }
    }); 
    var mymap=JSON.stringify(map); */
    $.ajax({
        url:"salaryServlet?action=addcontract1",
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
        	        alert("上传成功！");
        	        window.location.href = 'salaryContract.jsp';
        		}
        	else{
        		 alert("上传失败");
        		 //window.location.href = 'salaryContract.jsp';
        	}
        }
    })
}
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
            	
            	$("#deliveryplace").show().append(str);
            }
        }
    })
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
function toggle(table6) {
if (document.getElementById){
 target = document.getElementById(table6);
  if ( target.style.display == "none") {
   target.style.display = ""; 
  } else { 
   target.style.display = "none";
  }
 }
}
</script>
</head>
<body onload="load()">
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
    <li><%
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
  <li class="submenu"> <a href="#"><i class="icon icon-home"></i> <span>合同管理</span> <span class="label label-important">2</span></a>
     <ul>
        <li><a href="salaryContract.jsp">创建合同</a></li>
        <li><a href="salaryContractList.jsp">合同进度</a></li>
      </ul>
     </li>
    <li class="submenu"> <a href="#"><i class="icon icon-home"></i> <span>客户信息管理</span> <span class="label label-important">2</span></a>
     <ul>
        <li><a href="productowner.jsp">创建客户信息</a></li>
        <li><a href="productownerSee.jsp">查看客户信息</a></li>
      </ul>
     </li>
     <li class="submenu"> <a href="#"><i class="icon icon-home"></i> <span>销售调令管理</span> <span class="label label-important">3</span></a>
     <ul>
        <li><a href="saleCalloutOrder.jsp">录入销售调令</a></li>
        <li><a href="saleCalloutOrdersee.jsp">查看调令材料</a></li>
        <li><a href="saleCalloutOrderShenheModer.jsp">查看调令审核</a></li>
      </ul>
     </li>
     <li class="submenu"> <a href="#"><i class="icon icon-home"></i> <span>销售结算</span> <span class="label label-important">3</span></a>
     <ul>
        <li><a href="outyardCostS.jsp">结算检尺费</a></li>
        <li><a href="treeoutPrice.jsp">木材销售结算单</a></li>
        <li><a href="treeoutPriceTable.jsp">木材销售结算台账</a></li>
      </ul>
     </li>
    <li><a href="treeoutCodepage.jsp"><i class="icon icon-th-list"></i> <span>打印销售码单</span></a></li>
    <li class="submenu"> <a href="#"><i class="icon icon-home"></i> <span>库存与销售</span> <span class="label label-important">2</span></a>
     <ul>
        <li><a href="producetreeTableSalaryper.jsp">木材进仓库存</a></li>
        <li><a href="treeoutTableSalayper.jsp">木材出仓销售</a></li>
      </ul>
     </li>
  </ul>
</div>
<!--sidebar-menu-->

<div id="content">
<div id="content-header">
    <div id="breadcrumb"> <a href="salaryper" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> 首页</a></div>
  </div>
<!--End-breadcrumbs-->
<main>
    <div class="find-top">
        <p class="p-tail"><i
         class="i-tail"></i> 该页面主要是销售部门输入销售合同</p>
    </div>
    <div class="find-top1">
    <form id="myform"  enctype="multipart/form-data">
    <div class="top" id="divprint">
    <table style="margin-left:auto; margin-right:auto">
    <caption class="book_h01">木材购销合同</caption>
    <tbody>
       <tr>
       <td>合同编号</td><td><input type="text" name="contractnum" id="contractnum"></td>
       <td>供方（甲方）</td><td><input type="text" name="provider" id="provider"></td>
       <td>需方（乙方）</td><td><input type="text" name="demander" id="demander"></td>
       <td>标品号</td><td><input type="text" name="marknumber" id="marknumber"></td>  
       </tr>
       </tbody>
    </table>
<!--     <p class="table_p"><span>树材信息录入</span></p> -->
<!--        <table class="table2" id="table5" style="width:100%;height:auto;margin-left:120px"> -->
<!--           <tbody id="ttt5"> -->
                                
<!--            </tbody> -->
<!--            </table> -->
<!--            <table class="table2" id="table5" style="width:100%;height:auto;margin-left:0"> -->
<!--            <tbody id="ttt5S"> -->
           
<!--            </tbody> -->
<!--            </table> -->
       <p class="table_p"><span>树材信息录入</span></p>
                        <div style="text-align:center">
                        <button  class="btn" type="button"  value="添加普通树材" onclick="toggle('table5');"><span style="font-size:26px">添加普通树材</span></button>
                        <button  class="btnR" type="button"  value="添加薪材" onclick="toggle('table6');"><span style="font-size:26px">添加薪材</span></button>
                        <button class="btnS" type="button"  onclick="deleteAll()" value="清空"><span style="font-size:26px">清空</span></button>
                        </div>
                        <br>
                        <table class="table2" id="table5" style="width:100%;height:auto;display: none;margin-left:12px">
                           <tbody id="ttt5">
                                
                            </tbody>
                            <tr>
                            <td>
                            <div>
                            <button class="btn btn-warning" type="button"  onclick="aaad()" value="添加普通树材">添加普通树材</button>
                            <button class="btn btn-warning" type="button"  onclick="dddelete()" value="删除">删除</button>
                             <!--  <button class="btn btn-warning" type="button"  onclick="deleteAll()" value="清空">清空</button>-->
                            </div>
                            </td>
                            </tr>
                            </table>
                            <table class="table2" id="table6" style="width:100%;height:auto;display: none;margin-left:0">
                            <tbody id="ttt5S">
                                
                            </tbody>
                            <tr>
                            <td>
                            <div>
                            <button class="btn btn-warning" type="button"  onclick="aaadS()" value="添加薪材">添加薪材</button>
                            <button class="btn btn-warning" type="button"  onclick="dddelete()" value="删除">删除</button>
                             <!--  <button class="btn btn-warning" type="button"  onclick="deleteAll()" value="清空">清空</button>-->
                            </div>
                            </td>
                            </tr>
                            </table>
<!--       <table class="table"> -->
<!--       <tbody> -->
<!--            <tr> -->
<!--            <div style="text-align:left"> -->
<!--            <button class="btn btn-warning" type="button"  onclick="aaad()" value="添加普通树材">添加普通树材</button> -->
<!--            <button class="btnR btn-warning" type="button"  onclick="aaadS()" value="添加薪材">添加薪材</button> -->
<!--            <button class="btnS btn-default" type="button"  onclick="dddelete()" value="删除">删除</button> -->
<!--           <button class="btnS btn-default" type="button"  onclick="deleteAll()" value="清空">清空</button> -->
<!--           </div> -->
<!--             </tr> -->
<!--         </tbody> -->
<!--       </table> -->
      <div style="border:1px solid #3383da;font-size:18px">
      <p style="font-style: italic;color: red;">费用负担说明：     &nbsp;&nbsp;1：装车费&nbsp;&nbsp;&nbsp;&nbsp; 2：检尺费&nbsp;&nbsp;&nbsp;&nbsp;3：其他费用&nbsp;&nbsp;&nbsp;&nbsp;4：木材装运过程中所发生的安全事故，其民事（经济赔偿或经济补偿等）、行政等法律责任</p>
      </div>
      <br>
      <table style="margin-left:auto; margin-right:auto">
      <tr>
      <td>交货地点</td><td><select name="deliveryplace" id="deliveryplace"><option selected="selected">选择</option></select></td>
      <td>数量(立方米/吨)</td><td><input style="width:400px" type="text" name="treenumber" id="treenumber"></td>
      </tr>
      <tr>
      <td>费用负担</td><td><input style="text-decoration: underline;" type="text" name="cost" id="cost" value="甲方：      乙方：   "></td>
      <!--  <td>检尺标准</td><td><select id="measurestard" name="measurestard" multiple><option>衫小径单材积足码检</option><option>衫原木逢五进</option><option>杂木双码逢五进</option><option>小径单材积足码检</option></select></td>-->
      <td>检尺标准</td><td><input type="text" style="width:400px" id="measurestard" name="measurestard" value="衫小径单材积足码检"></td>
      </tr>
      <tr>
      <td>结算方式</td><td><input type="text" name="settlemethod" id="settlemethod"></td>
      <td>保证金(元)</td><td><input type="text" style="width:400px" name="margin" id="margin"></td>
      </tr>
      <tr>
      <td>合同开始时间</td><td><input type="date"  name="starttime" id="starttime"></td>
      <td>合同结束时间</td><td><input type="date" style="width:400px" name="endtime" id="endtime"></td>   
      </tr>
      <tr>
      <td class="top-table-label">上传文件</td><td><input class="filepath"  type="file" id="contractfile" name="contractfile"></td>
      </tr>
      </table>
    </div>
   <div class="1" style="text-align:center">

		    <!--  <span  style="text-align: center;"><button class="btn" type="button" onclick="treeAdd()" value="保存">保存</button></span>-->
		    <span  style="text-align: center;"><button class="btn" type="button" onclick="addsalaryContract()" value="保存">保存</button></span>
		     <span  ><button class="btn" type="button" id="btnPrint" value="打印">打印</button></span> 
		 </div>
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
</body>
</html>