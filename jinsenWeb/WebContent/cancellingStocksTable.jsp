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
   <title>退库单</title>
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
	.tk input{
			width: 100px;
		}
		#cancellingStockDate{
			width: 300px;
			
		}
		#cancellingStockSite{
			width: 450px;
		}
		#tkrq{
		text-align:left;
		}
		#tkdd{
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
</script>
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
          +"<option value='杉原木'>--杉原木--</option>"
          +"<option value='杉小径'>--杉小径--</option>"
          +"<option value='松原木'>--松原木--</option>"
          +"<option value='松小径'>--松小径--</option>"
          +"<option value='杂原木'>--杂原木--</option>"
          +"<option value='杂小径'>--杂小径--</option></select>"
        +"检尺长(m)<span></span><input type='text' style='width: 180px' name='tlong' id='td"+k+"'>检尺径<span></span>"
          +"<input type='text' style='width: 180px' name='tradius' id='tr"+k+"'>根数<span></span><input type='text' style='width: 180px' name='num' id='n"+k+"'>"
             +"材积(m^3、T、根)<span></span><input type='text' style='width: 180px' name='tvolume' id='tv"+k+"' onclick='vvvolume("+k+")'></td></tr>";
             k=Number(k)+Number(1);
            $("#ttt5").append(str); 
             
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
function vvvolume(id)
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
	     totalst+=Number(document.getElementById("tv"+h+"").value);
		 totalt+=Number(document.getElementById("n"+h+"").value);
	}
   for (var a=0;a<index.length;a++)
	{
	    var hk=$(index[a]).val();
	    total+=Number(document.getElementById("tv"+hk+"").value);
	    tot+=Number(document.getElementById("n"+hk+"").value);
	}
   document.getElementById("toltree").value=Number(totalt+tot);
   document.getElementById("tolstere").value=Number(totalst+total);
}
function treeAdd()
{
	var map={};
	var kk=Number(0),ki=Number(0);
	var workid=$("#wworkid").val();
	var cutnum=$("#ccutnum").val();
	var cutsite=$("#ccutsite").val();
	var yarddate=$("#yyarddate").val();
	var yard=$("#yyard").val();
	var carnumber=$("#ccarnumber").val();
	var section=$("#ssection").val();
	var surveyor=$("#ssurveyor").val();
	var toltree=$("#toltree").val();
	var tolstere=$("#tolstere").val();
	//var cutnum=document.getElementById("cutnum").value;
	if(workid=="")
		{
		alert("请先输入工单号！");
		}
	else{
    $("input[type='checkbox']:checked").each(function(i){
    	var group=[];
    	var id=$(this).val();
    	alert(id);
    	    group[0]=document.getElementById("sss"+id+"").value;
    	    group[1]=document.getElementById("td"+id+"").value;
    	    group[2]=document.getElementById("tr"+id+"").value;
    	    group[3]=document.getElementById("n"+id+"").value;
    	    group[4]=document.getElementById("tv"+id+"").value;
    	    //System.out.println("...." + group[4] + "...");
    	    if(group[0]==""|| group[1]==""|| group[2]=="" || group[3]==""|| group[4]=="")
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
    	    group[1]=document.getElementById("td"+id+"").value;
    	    group[2]=document.getElementById("tr"+id+"").value;
    	    group[3]=document.getElementById("n"+id+"").value;
    	    group[4]=document.getElementById("tv"+id+"").value;
    	    if(group[0]=="" || group[1]=="" ||group[2]=="" || group[3]=="" || group[4]=="")
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
        url:"treeServlet",
        data:{
            "action":"treeAdd2",
            "newtree":mymap,
            "id":ki+kk,
            "workid":workid,
            "cutnum":cutnum,
            "cutsite":cutsite,
            "yarddate":yarddate,
            "yard":yard,
            "carnumber":carnumber,
            "section":section,
            "surveyor":surveyor,
            "toltree":toltree,
            "tolstere":tolstere,     
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
function addcancelForm(){
	var formData = new FormData();
	var name = $("input").val();
	//formData.append("file",$("#upload")[0].files[0]);
	//formData.append("name",path);
	$.ajax({
	url : "workpageSevrlet?action=addCancellingStockTable1",
	type : 'POST',
	data : formData,
	// 告诉jQuery不要去处理发送的数据，用于对data参数进行序列化处理 这里必须false
	processData : false,
	// 告诉jQuery不要去设置Content-Type请求头
	contentType : false, //必须
	beforeSend:function(){
	console.log("正在进行，请稍候");
	},
	success : function(responseStr) {
	if(responseStr.status===0){
	console.log("成功"+responseStr);
	}else{
	console.log("失败");
	}
	},
	error : function(responseStr) {
	console.log("error");
	}
	});
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
      <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>货场费用</span> <span class="label label-important">2</span></a>
       <ul>
        <li><a href="goodsYardCost.jsp">费用结算</a></li>
        <li><a href="yardInventoryBootstrapDirector.jsp">货场盘点</a></li>
      </ul>
     </li>
     <li><a href="workpageTreeBuyDirector.jsp"><i class="icon icon-th-list"></i> <span>木材收购单</span></a></li>
    <li><a href="compareTreeListDirector.jsp"><i class="icon icon-th-list"></i> <span>木材装车对比</span></a></li>
    <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>录入数据</span> <span class="label label-important">2</span></a>
       <ul>
        <li><a href="passworkpageDirector.jsp">录入进场木材数据</a></li>
        <li><a href="treeoutDirector.jsp">录入出场木材数据</a></li>
      </ul>
     </li>
     <li><a href="InyardShenhesuDirector.jsp"><i class="icon icon-th-list"></i> <span>查看进场木材反馈</span></a></li>
    <li><a href="yardinfo.jsp"><i class="icon icon-home"></i> <span>新增货场信息</span></a> </li>
   <li><a href="cancellingStocksTable.jsp"><i class="icon icon-home"></i> <span>货场报损</span></a> </li>
   <li><a href="yardMoneryDirector.jsp"><i class="icon icon-home"></i> <span>货场费用报表</span></a> </li>
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
         class="i-tail"></i> 该页面主要是仓储段办理退库单</p>
    </div>
    <div class="find-top1">
    <form id="myform" action="workpageSevrlet?action=addCancellingStockTable1" method="POST" enctype="multipart/form-data" method="POST">
    <div class="top" id="divprint">
		<div id="table">
		<table  class="tk" >
		<caption class="book_h01">退库单</caption>
		    <tr>
		    <td>退库日期</td><td id="tkrq" ><input type="date" id="cancellingStockDate" name="cancellingStockDate"  value="" /></td>
		    <td>退库地点</td><td id="tkdd" ><input type="text" id="cancellingStockSite" name="cancellingStockSite" value="" /></td></tr>
			<tr>
			<td>退库原因</td><td><input type="text" style="width:300px" id="cancellingStockReason" name="cancellingStockReason" value="" /></td>
			<td>上传附件</td><td><input type="file" id="path" name="path"/></td>
			</tr>
			</table>
			<p class="table_p"><span>树材信息录入</span></p>
                        <table class="table" id="table5" style="width:1500px;height:auto">
                           <tbody id="ttt5">
                                
                            </tbody>
                            </table>
	        <table>
             <tr>
               <div style="float:left;"><button class="btn btn-warning" type="button"  onclick="aaad()" value="添加">添加</button>
               <button class="btn btn-default" type="button"  onclick="dddelete()" value="删除">删除</button>
          <button class="btn btn-default" type="button"  onclick="deleteAll()" value="清空">清空</button>
          <!--  <button class="btn btn-default" type="button"  onclick="treeAdd()" value="提交">提交</button>-->
          </div>
               </tr>
         </table>     
		</div>
		</div>
		<div class="1" style="text-align:center">

		    <span  style="text-align: center;"><button class="btn" type="submit" id="mybutton" value="保存申请书">保存</button></span>
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