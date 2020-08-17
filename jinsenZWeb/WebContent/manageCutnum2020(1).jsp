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
   <title>输入采伐证材料</title>
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
.table1{width:80%; height:500px;margin-left:auto; margin-right:auto;padding:10px;border-collapse:collapse}
.table1 input{vertical-align:middle;margin-top:10px;border:0px;background-color: transparent}
.table1 td,th{text-align:center;vertical-align:middle;margin-top:10px;}
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
</script>
<script type="text/javascript">
function cutnumtreeAdd()
{
	var map={};
	var kk=Number(0),ki=Number(0);
	var cutnum=$("#cutnum").val();
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
    	group[0]=document.getElementById("tree"+id+"").value;
	    group[1]=document.getElementById("tot"+id+"").value;
	    group[2]=document.getElementById("size"+id+"").value;
	    group[3]=document.getElementById("small"+id+"").value;
	    group[4]=document.getElementById("short"+id+"").value;
	    group[5]=document.getElementById("fire"+id+"").value;
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
        url:"cutnumServlet",
        data:{
            "action":"cutnumtreeAdd",
            "newtree":mymap,
            "id":ki+kk,
            "cutnum":cutnum 
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
    <li> <a href="cutnumApplysee.jsp"><i class="icon icon-home"></i> <span>审核采伐证申请</span></a> </li>
    <li> <a href="manageCutnum.jsp"><i class="icon icon-inbox"></i> <span>录入采伐证材料</span></a></li>
    <li> <a href="manageCutnumsee.jsp"><i class="icon icon-th"></i> <span>查看采伐证材料</span></a></li>
    <li> <a href="cutnumTable.jsp"><i class="icon icon-th-list"></i> <span>采伐证报表</span></a></li>
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
		         class="i-tail"></i> 该页面主要是管理部门上传采伐证材料</p>
		  </div>
		   
		    <form id="myform" action="cutnumServlet?action=addcutnum1" method="POST" enctype="multipart/form-data">
		  <div class="top" id="divprint" >
		<table  class="table1" border="1">
			<caption class="book_h01">录入采伐证材料</caption>
			<tr>
				<td >林权单位</td>
				<td colspan="2"><select style="border:0px;background-color: transparent;text-align:center;width: 230px;" name="company" id="company">
				    <option value="">--请选择采伐类型--</option>
					<option value="1">万安分公司</option> 
					<option value="2">营林分公司</option> 
					<option value="3">常青分公司</option> 
					<option value="4">青溪林业有限公司</option>
					<option value="5">万森林业采育有限公司</option>
					</select></td>
				<td>采伐证号</td>
				<td colspan="2"><input  style="border:0px;background-color: transparent;width: 300px;" type="text" id="certificatenum" name="certificatenum" value="" /></td>
			</tr>
			<tr>
				<td>采伐地点</td>
				<td colspan="5" >
				<input  style="border:0px;text-align:right;background-color: transparent;width: 150px;" type="text" id="cutaddress" name="cutaddress" value="  " />乡镇，     
				<input  style="border:0px;text-align:right;background-color: transparent;width: 150px;" type="text" id="cutvillage" name="cutvillage" value="  " />村，
				<input  style="border:0px;text-align:right;background-color: transparent;width: 150px;" type="text" id="quartel" name="quartel" value="  " />林班，
				<input  style="border:0px;text-align:right;background-color: transparent;width: 150px;" type="text" id="largeblock" name="largeblock" value="  " />大班，
				<input  style="border:0px;text-align:right;background-color: transparent;width: 150px;" type="text" id="smallblock" name="smallblock" value="  " />小班
				</td>
			</tr>	
			<tr>
				<td>采伐类型</td>
				<td colspan="2"><select style="border:0px;background-color: transparent;text-align:center;width: 230px;" name="cuttype" id="cuttype">
					<option value="">--请选择采伐类型--</option>
					<option value="1">主伐</option> 
					<option value ="2">抚育采伐</option> 
					<option value ="3">更新采伐</option> 
					<option value ="4">低产（效）林改造采伐</option>
				</select></td>
				<td colspan="2">采伐方式</td>
				<td><select style="border:0px;background-color: transparent;text-align:center;width: 230px;" name="cutmethod" id="cutmethod"> 
					<option value="">--请选择采伐方式--</option>
					<option value="1">择伐</option>
					<option value ="2">间伐</option> 
					<option value ="3">皆伐</option> 
				</select></td>
			</tr>
			
			<tr>
			    <td>采伐强度(%)</td>
				<td colspan="2"><input style="border:0px;background-color: transparent;width: 300px;" type="text" id="cutqiang" name="cutqiang" value="   %" /></td>
				<td>采伐面积(公顷)</td>
				<td colspan="2"><input  style="border:0px;background-color: transparent;width: 300px;"type="text" id="cutarea" name="cutarea" value="" /></td>		   
			</tr>
			<tr>
			 <td>林权证号</td>
			 <td colspan="2"><input  style="border:0px;background-color: transparent;width: 400px;" type="text" id="forestid" name="forestid" value="将证林证字(   )第     号" /></td>
			 <td>采伐证编号</td>
				<td colspan="2"><input  style="border:0px;background-color: transparent;width: 300px;" type="text" id="cutnum" name="cutnum" value="" /></td>
			</tr>
			<tr>
				<td colspan="2">采伐开始时间</td>
				<td ><input style="width: 300px;" type="date" name="starttime" id="starttime"></td>
				<td >采伐截止时间</td>
				<td colspan="2"><input style="width: 300px;" type="date" name="endtime" id="endtime"></td>
			</tr>
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
		    <th><input style="border:0px;background-color: transparent" type="text" name="treetype" id='tree1'  value="杉木"></th>
		    <td><input style="border:0px;background-color: transparent" type="text" name="total" id='tot1'></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="sizewood" id='size1'></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="smalltimber" id='small1'></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="shorttimber" id='short1'></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="firewood" id='fire1'></td>
		    </tr>
		    <tr id="2">
		    <th><input style="border:0px;background-color: transparent"  type="text" name="treetype" id='tree2'  value="松木"></th>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="total" id='tot2'></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="sizewood" id='size2'></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="smalltimber" id='small2'></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="shorttimber" id='short2'></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="firewood" id='fire2'></td>
		    </tr>
		    <tr id="3">
		    <th><input style="border:0px;background-color: transparent"  type="text" name="treetype" id='tree3'  value="阔叶林"></th>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="total" id='tot3'></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="sizewood" id='size3'></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="smalltimber" id='small3'></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="shorttimber" id='short3'></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="firewood" id='fire3'></td>
		    </tr>
		    <tr>   <!--，此行为出材量、总计 -->
		    <th><input style="border:0px;background-color: transparent"  type="text" name="chucai" id='chucai' value="合计(出材量)"></th>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="volume" id='volume'></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="sizewoodAll" id='size4'></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="smalltimberAll" id='small4'></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="shorttimberAll" id='short4'></td>
		    <td><input style="border:0px;background-color: transparent"  type="text" name="firewoodAll" id='fire4'></td>
		    </tr>
		    <tr>
		   <td >上传附件</td>
		  <td colspan="4" ><input style="width: 900px;" class="filepath" onchange="changepic(this)" type="file" multiple="multiple" id="cutnumfile" name="cutnumfile"></td>
		  <td><button class="btn" type="submit" id="mybutton" value="上传附件">上传附件</button></td>
		   </tr>
		</table>
		</div>
		<br>
    <div class="1" style="text-align:center">
		    <span  style="text-align: center;"><button style="width:160px" class="btn" type="button" onclick="addcutnum" value="保存采伐证材料">保存采伐证材料</button></span>
		     <span><button class="btn" type="button" id="btnPrint" value="打印">打印</button></span> 
		 </div>
                <div style="clear: both;padding-bottom: 40px"></div>
    </form>
   
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