<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Connection" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
   <title>输入销售调令</title>
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
.table2{width:80%;margin-left:auto; margin-right:auto;padding:10px;border-collapse:collapse}
.table3{margin-left:auto; margin-right:auto;font-size: 20pt}
.table3 td{margin-right:20px}
td,th{text-align:center;vertical-align:middle}
         tr{text-align:center}
    .p-tail {
    padding: 10px;
    font-size: 18px;
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
	font-size: 24px!important; 	height: 30px; 	
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
var k=Number(1);
$(function(){
	aaad();
})
function aaad()
{
 	var str="<tr id="+k+" display:block;><td style='font-size:20pt;'><input type='checkbox' style='width:20px;height:20px;' value="+k+">树材种<span></span>"
          +"<select style='width: 180px' name='treetype' id='sss"+k+"'>"
           +"<option>--请选择--</option>"
          +"<option value='杉木'>--杉木--</option>"
          +"<option value='松木'>--松木--</option>"
          +"<option value='杂木'>--杂木--</option>"
          +"<option value='杉薪'>--杉薪--</option>"
          +"<option value='松薪'>--松薪--</option>"
          +"<option value='杂薪'>--杂薪--</option>"
          +"<option value='衫短材'>--衫短材--</option>"
          +"<option value='杉木兜'>--杉木兜--</option>"
          +"<option value='杉直柄'>--杉直柄--</option>"
          +"<option value='特种材'>--特种材--</option>"
          +"<option value='其他'>--其他--</option></select>"
          +"长度(米)<span></span><input type='text' style='width: 180px' name='tlong' id='tl"+k+"' value='2-4米'>"
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
        +"<option value='20-28'>30以上</option></select></tr>";
             k=Number(k)+Number(1);
            $("#ttt5").append(str); 
             
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
function treeAdd()
{
	var map={};
	var kk=Number(0),ki=Number(0);
	var saleCalloutOrder=$("#saleCalloutOrder").val();
	var contractnum=$("#contractnum").val();
	var yardname=$("#yardname").val();
	var demander=$("#demander").val();
	//alert(demander)
	var section=$("#section").val();
	var Paymentamount=$("#Paymentamount").val();
	var totalnum=$("#totalnum").val();
	var Signer=$("#Signer").val();
	var callidtime=$("#callidtime").val();
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
    	    group[0]=document.getElementById("treetype"+id+"").value;
    	    group[1]=document.getElementById("tlong"+id+"").value;
    	    group[2]=document.getElementById("tradius"+id+"").value;
    	    //group[3]=document.getElementById("tn"+id+"").value;
    	    if(group[0]==""|| group[1]=="")
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
    	    group[0]=document.getElementById("treetype"+id+"").value;
    	    group[1]=document.getElementById("tlong"+id+"").value;
    	    group[2]=document.getElementById("tradius"+id+"").value;
    	    //group[3]=document.getElementById("tn"+id+"").value;
    	    if(group[0]=="" || group[1]=="")
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
            "action":"salecalloutAdd",
            "newtree":mymap,
            "id":ki+kk,
            "contractnum":contractnum,
            "saleCalloutOrder":saleCalloutOrder,
            "demander":demander,
            "yardname":yardname,
            "section":section,
            "Paymentamount":Paymentamount,
            "Signer":Signer,
            "totalnum":totalnum,
            "callidtime":callidtime
        },
        type: "POST",
        dataType:"html",
        success: function (data) {
        	//alert(data);
        	if(data>0)
        		{
        	        alert("添加成功！");
        	        window.location.href = 'saleCalloutOrder.jsp';
        		}
        	else{
        		 alert("添加失败");
        		 //window.location.href = 'saleCalloutOrder.jsp';
        	}
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
		     
		     $("#"+j+"").empty();
		     alert("删除成功");
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
function makecount()
{
	var mk=$("input[type='checkbox']:checked");
	var index=$("input[type='checkbox']:not(:checked)");
	var totalt=Number(0);
	var totalst=Number(0);
	var total=Number(0);
	var tot=Number(0);
	//System.out.println("...." + mk + "...");
	for (var i=0;i<mk.length;i++)
    {
	     var h=$(mk[i]).val();
	     totalst+=Number(document.getElementById("tn"+h+"").value);
		 //totalt+=Number(document.getElementById("n"+h+"").value);
	}
   for (var a=0;a<index.length;a++)
	{
	    var hk=$(index[a]).val();
	    total+=Number(document.getElementById("tn"+hk+"").value);
	    //tot+=Number(document.getElementById("n"+hk+"").value);
	}
   //document.getElementById("toltree").value=Number(totalt+tot);
   document.getElementById("totalnum").value=Number(totalst+total);
}
function mycreate()
{
	//var length=$("#codetable tr").length;
	var contractnum=document.getElementById("contractnum").value;
	length=(length-1)*2;
	var group=[];
	$.ajax({
        url:"salaryServlet",
        data:{
            "action":"findContracynum",
            "contractnum":contractnum
        },
        type: "POST",
        dataType:"json",
        success: function (data) {
        	if(data==null)
        		{
        		alert("您所输入的合同编号错误，请重新输入");
        		}
        	else{
        	//var cutCompack=data["cutCompack"];
        	//var contractionSide=data["contractionSide"];
        	var tree=data["tree"];
        	var demander=tree[0].demander;
        	$("#ttt").empty();
        	for(var i=0;i<tree.length;i++){
        		var j=tree[i];
        	      var str="<tr id='"+(i+1)+"'><td style='width:50px'><input type='checkbox' style='width:20px;height:20px;display:none' value="+(i+1)+"></td>"
        	      +"<td>树材种：<span></span><input type=text  readonly='readonly' id='treetype"+(i+1)+"' value='"+j.treetype+"'></td>"
                  +"<td>长度(米)：<span></span><input type=text  readonly='readonly' value='"+j.tlong+"' id='tlong"+(i+1)+"'></td>"
                   +"<td>口径(厘米)：<span></span><input type='text' id='tradius"+(i+1)+"' value='"+j.tradius+"'readonly='readonly'></td></tr>"
                  $("#ttt").append(str); 
        	}
        	//ttt.innerHTML=str;
        	document.getElementById("demander").value=demander;
        }}
    })
}
window.onload = function () {
    locationInput = function () {
    };
}
</script>
<script type="text/javascript">
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
            	
            	$("#yardname").show().append(str);
            }
        }
    })
}

/*$(function (){
	$("#yardname").change(function load(){
		var parent = $(this).val(); //获取当前选项的value值
		//alert(parent)
		$.ajax({
			url:"salaryServlet",
			data:{
	            "action":"loadyardsection",
	            "yardname":parent
	        },
	        type: "POST",
			dataType : "json",
			success : function(data){
				var str = "";
				for(var i = 0; i < data.length; i++){
					str = "<option>"+data[i].section+"</option>";
					$("#section").show().append(str);
				}
				
			}
		})
	})
})*/
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
    <div id="breadcrumb"> <a href="salesmanTwo.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> 首页</a></div>
  </div>
<!--End-breadcrumbs-->
<main>
    <div class="find-top">
        <p class="p-tail"><i
         class="i-tail"></i> 该页面主要是销售部门输入销售调令页面，操作说明：录入销售合同自动生成木材信息</p>
    </div>
    <div class="find-top1">
    <form>
    <div class="top" id="divprint">
    <table class="table3">
    <caption class="book_h01">木材销售调令</caption>
    <tbody>
       <tr>
       <td>调运时间:</td><td><input type="datetime-local" name="callidtime" id="callidtime" style="border:0px;font-size:20pt;"></td>
       </tr>
       <tr>
       <td>合同编号:</td><td><input type="text" name="contractnum" id="contractnum" oninput='mycreate()' onclick='locationInput'></td>
       <td>购货人:</td><td><input type="text" name="demander" id="demander" readonly='readonly'></td>
       <!--  <td>供货地点</td><td><input type="text" name="yard" id="yard"></td>-->
       </tr>
       <tr>
       <td>供货地点:</td>
       <td><select name="yardname" id="yardname"><option selected="selected">选择</option></select></td>
       <td>供货分区:</td><td><select name="section" id="section">
       <option selected='selected'>无固定分区</option>
       <option>A</option>
       <option>B</option>
       <option>C</option>
       <option>D</option>
       <option>E</option>
       <option>F</option>
       </select></td>
       </tr>
       <tr>
        <td>调令号:</td><td><input type="text" name="saleCalloutOrder" id="saleCalloutOrder"></td> 
        <td style="display:none">货款金额(元):</td><td style="display:none"><input type="text" name="Paymentamount" id="Paymentamount" value="0"></td> 
       </tr>
       </tbody>
    </table>
    <p class="table_p"><span>树材信息</span></p>
       <table class="table2" id="ttt">
       <tr id="+k+"><td style='font-size:20pt;'><input type='checkbox' style='width:20px;height:20px;' value="+k+">树材种<span></span>
          <select style='width: 180px' name='treetype' id='sss"+k+"'>
           <option>--请选择--</option>
          <option value='杉木'>--杉木--</option>
          <option value='松木'>--松木--</option>
          <option value='杂木'>--杂木--</option>
          <option value='杉薪'>--杉薪--</option>
          <option value='松薪'>--松薪--</option>
          <option value='杂薪'>--杂薪--</option>
          <option value='衫短材'>--衫短材--</option>
          <option value='杉木兜'>--杉木兜--</option>
          <option value='杉直柄'>--杉直柄--</option>
          <option value='特种材'>--特种材--</option>
          <option value='其他'>--其他--</option></select>
            长度(米)<span></span><input type='text' style='width: 180px' name='tlong' id='tl"+k+"' value='2-4米'>
    口径(厘米)<span></span><input type='text' style='width: 180px' name='tradius' id='tr"+k+"'></tr>
           </table>
      <!--  <table class="table">
      <tbody>
           <tr>
           <div style="float:left;"><button class="btn btn-warning" type="button"  onclick="aaad()" value="添加">添加</button>
           <button class="btn btn-default" type="button"  onclick="dddelete()" value="删除">删除</button>
          <button class="btn btn-default" type="button"  onclick="deleteAll()" value="清空">清空</button>
          </div>
            </tr>
        </tbody>
      </table>
      -->
      <br>
      <table class="table3">
      <tr>
      <td>数量(立方米/吨):</td><td><input type="text" name="totalnum" id="totalnum"></td>
      <td>通知签发人:</td><td><input type="text" name="Signer" id="Signer"></td>
      </tr>
      </table>
    </div>
   <div class="1" style="text-align:center">

		    <span  style="text-align: center;"><button class="btn" type="button" onclick="treeAdd()" value="保存">保存</button></span>
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
</body>
</html>