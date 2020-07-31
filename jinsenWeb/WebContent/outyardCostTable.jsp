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
   <title>查看检尺费</title>
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
 .table_p{line-height: 28px;border-bottom: 1px #d0e6ec solid;position: relative;margin-bottom: 10px; 
            margin-top: 35px; margin-left:10px}
.table_p span{border-bottom: 3px #42cdec solid;display: inline-block;position: absolute;bottom: -1px;font-weight: bold;font-size: 20px}
            

.table1{width:60%; height:200px;margin-left:auto; margin-right:auto;padding:10px;border-collapse:collapse}
.table2{width:60%;margin-left:auto; margin-right:auto;padding:10px;border-collapse:collapse}
td,th{text-align:center;vertical-align:middle}
tr{text-align:center}
         


</style>
<script type="text/javascript">
//计算金额
function priceCount(id)
{
	
	var volume=document.getElementById("tv"+id+"").value;
	var unitprice=document.getElementById("un"+id+"").value;
	document.getElementById("pr"+id+"").value=(volume*unitprice);
}
//计算合计金额
function makecount()
{
	var length=$("#table5 tr").length;
	    length=length-1;
	    //var length=document.getElementById("sss").length
	var surveyorFee=Number(0);
	//alert(length);
	for(var id=1;id<=length;id++)
    {
	     surveyorFee+=Number(document.getElementById("pr"+id+"").value);
	}
   document.getElementById("surveyorFee").value=Number(surveyorFee);
}
function mysave()
{
	var map={};
	var kk=0;
	var workid=document.getElementById("workid").value;
	var surveyorFee=document.getElementById("surveyorFee").value;
	var length=$("#table5 tr").length;
	length=length-1;
	alert(length);
    for(var id=1;id<=length;id++){
    	var group=[];
    	group[0]=document.getElementById("un"+id+"").value;
    	group[1]=document.getElementById("pr"+id+"").value;
    	if(group[0]==""|| group[1]=="")
    	    	{
    	    	alert("请将信息填写完整！");
    	    	}
    	    else{
               map[id-1]=group;
    	    }
    	}
    	kk=length;
    var mymap=JSON.stringify(map);
    $.ajax({
        url:"treeServlet",
        data:{
            "action":"saveSurveyorFee",
            "newtree":mymap,
            "id":kk,
             "workid":workid,
             "surveyorFee":surveyorFee
        },
        type: "POST",
        dataType:"html",
        success: function (data) {
        	alert(data);
        	if(data>0)
    		{
    	        alert("添加成功！");
    		}
        	else{
        		alert("添加失败！");
        	}
        }
    })
  }

</script>
</head>
<body>
<main>
<!-- 下拉框代码 -->
				


</main>
<script type="text/javascript" src="static/js/jquery-1.12.4.min.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
</head>

<body>
<%outyard c=(outyard)request.getAttribute("workid");
//List<tree> t=null;
//t=(List<tree>)request.getAttribute("tree");
List<surveyorFee> t=null;
t=(List<surveyorFee>)request.getAttribute("surveyorFee");
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
     <li ><a href="index.html"><i class="icon icon-home"></i> <span>信息通知</span></a> </li>
    <li> <a href="manageCutnum.jsp"><i class="icon icon-th-list"></i> <span>录入砍伐证材料</span></a></li>
    <li> <a href="manageCutnumsee.jsp"><i class="icon icon-th-list"></i> <span>查看采伐证材料</span></a></li>
    <li> <a href="manageCutnumshenhe.jsp"><i class="icon icon-th-list"></i> <span>审核采伐证材料</span></a></li>
  </ul>
</div>


<!--sidebar-menu-->
<div id="content">
<div id="content-header">
    <div id="breadcrumb"> <a href="managerP.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
  </div>
<!--End-breadcrumbs-->

<main>
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>检尺费</span></div>
    <div class="find-top">
        <p class="p-tail"><i
         class="i-tail"></i> 该页面主要是检尺费</p>
    </div>
    <div class="find-top1">
    <form action="">
    <div class="top" id="divprint">
    
    <table class="table1" >
    <caption class="book_h01">检尺费</caption>
				<tr>
					<td>销售工单号</td>
					<td><input  type="text" name="workid" id="workid" value="<%=c.getWorkid()%>"></td>
				    <td>销售合同号</td>
					<td><input  type="text" name="contractnum" id="contractnum" value="<%=c.getContractnum()%>"></td>
					<td >销售调令</td>			
					<td><input type="text" name="sale_callout_orderid" id="sale_callout_orderid" value="<%=c.getSaleCalloutorderid()%>"></td>
			    </tr>	
			  	<tr>
					<td >销售货场</td>
					<td><input type="text" name="yard" id="yard" value="<%=c.getYard()%>"></td>				
			        <td>货场分区</td>
				    <td><input type="text" name="section" id="section" value="<%=c.getSection()%>"></td>	
				    <td>检尺标准</td>
				    <td><input type="text" name="batchNum" id="batchNum" value="<%=c.getBatchNum()%>"></td>
			    </tr>   
			  	<tr> 
				    <td>出场时间</td>
		    		<td><input type="date" name="yarddate" id="yarddate" value="<%=c.getYarddate()%>"></td>
		    		<td>车牌号</td>
				    <td><input type="text" name="carNumber" id="carNumber" value="<%=c.getCarNumber()%>"></td>
				    <td>检尺员</td>
				    <td><input type="text" name="surveyor" id="surveyor" value="<%=c.getSurveyor()%>"></td>
			   </tr>
			   </table>
				
			<p class="table_p"><span>树材信息</span></p>
			<table   class="table2" id="table5" style="width:1600px;height:auto">
                           <tbody id="ttt">
                                <% int i=1;%>
                                <c:forEach items="${surveyorFee}" var="b">      
                            <tr id="<%=i%>" display:block>
                            <td style='font-size:20px;'>
                                                                               树材种：<span></span><input type='text' style='width: 130px' name='treetype' id='sss<%=i%>' value="${b.getTreetype()}">                  
                                                                                            检尺长(m)&nbsp;<input type='text' style='width: 130px' name='tdouble' id='td<%=i%>' value="${b.getTlong()}">
                                                                                            检尺径&nbsp;<span></span><input type='text' style='width: 130px' name='tradius' id='tr<%=i%>' value="${b.getTradius()}">
                                                                                            根数&nbsp;<span></span><input type='text' style='width: 130px' name='num' id='n<%=i%>' value="${b.getNum()}">
                                                                                            材积(m^3、T、根)&nbsp;<span></span><input type='text' style='width: 130px' name='tvolume' id='tv<%=i%>' value="${b.getTvolume()}">
                                                                                            单价&nbsp;<span></span><input type='text' style='width: 130px' name='unitprice'  id='un<%=i%>' value="${b.getUnitprice()}">
                                                                                            金额&nbsp;<span></span><input type='text' style='width: 130px' name='price' id='pr<%=i%>' onclick='priceCount("<%=i%>")' value="${b.getPrice()}">
                           </td>
                           </tr>
                         <%i++; %>
                         </c:forEach>                       
          </tbody>
       </table>
       <p class="table_p"><span>检尺费</span></p>
	   <table   class="table2" id="table5" style="width:1500px;height:auto">
			<tr>
	        <td>总检尺费<input type="text" name="surveyorFee" id="surveyorFee" onclick="makecount()"></td>
			</tr>
	   </table>
    </div>
        </form> 
             <div class="but_p" style=text-align:center; >
               <button class="but_save" type="button" onclick="mysave()" value="保存检尺费">保存检尺费</button>
                <button class="but_save"  type="button" id="btnPrint" value="打印">打印</button></div>
                  <div style="clear: both;padding-bottom: 40px"></div>
                
                

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
function goPage (newURL) {

    // if url is empty, skip the menu dividers and reset the menu selection to default
    if (newURL != "") {
    
        // if url is "-", it is this page -- reset the menu:
        if (newURL == "-" ) {
            resetMenu();            
        } 
        // else, send page to designated URL            
        else {  
          document.location.href = newURL;
        }
    }
}

$(function(){
    $("#btnPrint").click(function(){ $("#divprint").printArea(); });
});
</script>
</body>
</html>