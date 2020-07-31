<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%@page import="java.text.SimpleDateFormat"%>

<%@page import="java.util.*"%>
    <!-- 销售结算页面 -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>木材生产结算</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/tableall.css">
    <link rel="stylesheet" href="js/jQueryCalendar/calendar.css">
    <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />

    <!-- font-awesome icons CSS -->
    <link href="css/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons CSS-->

    <link href='css/SidebarNav.min.css' media='all' rel='stylesheet' type='text/css'/>
    <!-- js-->
    <script src="js/jquery-1.11.1.min.js"></script>
    <script src="js/modernizr.custom.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/fullcalendar.css" />
<link rel="stylesheet" href="css/matrix-style.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="css/jquery.gritter.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
<link type="text/css" rel="stylesheet" href="css/PrintArea.css" />
    <style type="text/css">
        .table_p{line-height: 28px;border-bottom: 1px #d0e6ec solid;position: relative;margin-bottom: 10px;
            margin-top: 35px;}
        .table_p span{border-bottom: 3px #42cdec solid;display: inline-block;position: absolute;bottom: -1px;font-weight: bold;font-size: 20px}
        .but_p button{width: 80px}
        #h li{float: left; }
#h a{font-size:15px;width: 230px; height: 30px;padding: 10px 0;text-align: center;  background: #3c763d; display: block; text-decoration:none; color:white}
#h a:hover{color:white;background: green}
    </style>
<script src="js/bstable/js/bootstrap.min.js"></script>  
<script type="text/javascript">

function mycreate()
{
	//var length=$("#codetable tr").length;
	var cutnum=document.getElementById("cutnum").value;
	if(cutnum=="")
		{
		    alert("请输入采伐证号！");
		}
	else
		{
	length=(length-1)*2;
	var group=[];
	for(var i=1;i<=length;i++)
		{
		     if(document.getElementById("code"+i+"").value!="")
		     group[i-1]=document.getElementById("code"+i+"").value;
		}
	var mygroup=JSON.stringify(group);	
	$.ajax({
        url:"salaryServlet",
        data:{
            "action":"savew",
            "mygroup":mygroup,
            "cutnum":cutnum
        },
        type: "POST",
        dataType:"json",
        success: function (data) {
        	if(data==null)
        		{
        		alert("您所需要生成的码单信息有误，请重新核对");
        		}
        	else{
        	var tree=data["tree"];
        	var code=data["work"];
        	var project=data["project"];
        	//alert(code);
        	$("#myproject").empty();
        	for(var i=0;i<project.length;i++)
    		{
        		var j=project[i];
        		var str3="<table class='top-table'>"
        			  +"<tr><td class='top-table-label'>工程包：</td>"
        	           +"<td><input type='text' id='projectPackageid' disabled='disabled' value='"+j.projectPackageid+"''></td>"
        	            +"<td class='top-table-label' >劳务承包人：</td>"
        	            +"<td><input type='text' disabled='disabled' id='forperson' value='"+j.forperson+"'></td>"
        	            +"<td class='top-table-label' >伐区管理单位：</td>"
        	            +"<td><input type='text' disabled='disabled' id='manageUnit' value='"+j.manageUnit+"'></td></tr></table>";
        		$("#myproject").append(str3); 
    		}
        	$("#mysomething").empty();
        	for(var i=0;i<code.length;i++)
    		{
        		var j=code[i];
        		var str2="<table class='top-table'>"
        			+"<tr><p class='table_p'><span>工单"+j.workid+"</span></p>"
        			+"</tr><tr><td class='top-table-label'>工单：</td>"
                    +"<td><input type='text' id='workid' disabled='disabled' value='"+j.workid+"'></td></tr></table>";
        		$("#mysomething").append(str2); 
    		}
        	$("#ttt").empty();
        	var str="<tr><p class='table_p'><span>材种信息</span></p></tr>"
                  +"<tr><td class='top-table-label'>材种：</td>"
                  +"<td class='top-table-label'>材积：</td>"
                  +"<td class='top-table-label'>单价：</td>"
                  +"<td class='top-table-label'>金额：</td></tr>";
        	for(var i=0;i<tree.length;i++){
        		var j=tree[i];
        	str+="<tr id='"+(i+1)+"'><td><select id='treetype"+(i+1)+"' ><option>"+j.type+"</option></select></td>"
                  +"<td><input type=text disabled=disabled value='"+j.tvolume+"' id='volume"+(i+1)+"'></td>"
                   +"<td><input type='text' id='unitprice"+(i+1)+"' value='"+j.unitprice+"'></td>"
                  +"<td><input type='text' id='price"+(i+1)+"' onclick='priceCount("+(i+1)+")' value='"+j.price+"'></td></tr>"
        	}
        	ttt.innerHTML=str;
        }}
    })
}
}

function mysave()
{
	var map={};
	var kk=0;
	var cutnum=$("#cutnum").val();
	var projectPackageid=document.getElementById("projectPackageid").value;
	var forperson=document.getElementById("forperson").value;
	var manageUnit=document.getElementById("manageUnit").value;
	var person=document.getElementById("person").value;
	var ttvolume=document.getElementById("ttvolume").value;
	var tprice=document.getElementById("tprice").value;
		var length=$("#ttt tr").length;
		length=length-2;
    	for(var id=1;id<=length;id++){
    		var group=[];
    	    group[0]=document.getElementById("treetype"+id+"").value;
    	    group[1]=document.getElementById("unitprice"+id+"").value;
    	    group[2]=document.getElementById("price"+id+"").value;
    	    if(group[0]==""|| group[1]==""|| group[2]=="")
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
        url:"salaryServlet",
        data:{
            "action":"saveLaowu",
            "newtree":mymap,
            "id":kk,
            "cutnum":cutnum,
            "projectPackageid":projectPackageid,
            "forperson":forperson,
            "manageUnit":manageUnit,
            "person":person,
            "ttvolume":ttvolume,
            "tprice":tprice
        },
        type: "POST",
        dataType:"html",
        success: function (data) {
        	alert(data);
        	if(data>0)
    		{
    	        alert("添加成功！");
    		}
        }
    })
  }
//计算金额
function priceCount(id)
{
	
	var volume=document.getElementById("volume"+id+"").value;
	var unitprice=document.getElementById("unitprice"+id+"").value;
	document.getElementById("price"+id+"").value=(volume*unitprice);
}
//计算合计材积和金额
function makecount()
{
	var length=$("#ttt tr").length;
	    length=length-2;
	var ttvolume=Number(0);
	var tprice=Number(0);
	//alert(length);
	for(var id=1;id<=length;id++)
    {
		//var group=[];
	     ttvolume+=Number(document.getElementById("volume"+id+"").value);
	     //alert(ttvolume);
	     tprice+=Number(document.getElementById("price"+id+"").value);
	}
   document.getElementById("ttvolume").value=Number(ttvolume);
   document.getElementById("tprice").value=Number(tprice);
}
</script>
</head>
<body>
<% Date d = new Date();

SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

String now = df.format(d); %>
<div id="header">
  <h1><a href="dashboard.html">销售管理平台</a></h1>
</div>
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

<!--sidebar-menu-->
<div id="sidebar"><a href="#" class="visible-phone"><i class="icon icon-home"></i> 仪表盘</a>
   <ul>
    <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>工单管理</span> <span class="label label-important">2</span></a>
       <ul>
        <li><a href="workpageAdd.jsp">输入工单</a></li>
        <li><a href="workpageShenheFaqu.jsp">审核工单</a></li>
      </ul>
     </li>
     <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>工程包管理</span> <span class="label label-important">2</span></a>
     <ul>
        <li><a href="CutnumProjectpackage.jsp">创建工程包</a></li>
        <li><a href="cutareaAllot.jsp">伐区拨交</a></li>
      </ul>
     </li>
    <li> <a href="manageCutnumCheck.jsp"><i class="icon icon-inbox"></i> <span>生产管理</span></a> </li>
    <li><a href="productPrice.jsp"><i class="icon icon-th"></i> <span>生产结算</span></a></li>
    <li><a href="CutnumProjectpackageTable.jsp"><i class="icon icon-inbox"></i><span>工程包台账</span></a></li>
    <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>施工方管理</span> <span class="label label-important">2</span></a>
     <ul>
        <li><a href="managesdatecard.jsp">录入施工方资料</a></li>
        <li><a href="managersdatecardSee.jsp">施工方台账</a></li>
      </ul>
     </li>
  </ul>
</div>
<!--sidebar-menu-->
<div id="content">
  <div id="content-header">
    <div id="breadcrumb"> <a href="salaryper.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> 管理员首页</a></div>
  </div>
<main>
    <article class="artlce">
    <div class="find-top">
        <p class="p-tail"><i class="i-tail"></i>该界面是生成木材生产结算的主要界面</p>
    </div>
    <div class="find-top1" id="divprint">
    <pre align="center">结算时间：<%=now%></pre>	
           <table class="top-table">
            <tr ><p class="table_p"><span>请输入采伐证号：</span></p>
            </tr>
            <tr>
                <td class="top-table-label" >采伐证号：</td>
                <td><input type="text" id="cutnum" value=""></td>
                <td colspan="" style="margin-top: 10px;margin-bottom: 10px">
                <button class="add-but" onclick="mycreate()"><i class="glyphicon glyphicon-edit"></i>生成</button></td>
            </tr>
        </table>
        <div id="myproject">
        <table class="top-table">
        <tr>
            <td class="top-table-label">工程包:</td><td><input type="text" name="projectPackageid" id="projectPackageid" disabled="disabled"></td>
            <td class="top-table-label">劳务承包人：</td><td><input type="text" name="forperson" id="forperson" disabled="disabled"></td>
            <td class="top-table-label">伐区管理单位：</td><td><input type="text" name="manageUnit" id="manageUnit" disabled="disabled"></td>
        </tr>
        </table>
        </div>
        <div id="mysomething">
        <table class="top-table">
          <tr><p class="table_p"><span>主要信息</span></p>
          </tr>
          <tr>
          <td class="top-table-label">工单：</td>
           <td><input type="text" id="workid" disabled="disabled"></td>
          </tr>
      </table>
        </div>
          <table class="top-table" id="ttt">
              <tr><p class="table_p"><span>材种信息</span></p>
              </tr>
          <tr><td class="top-table-label">材种：</td>
              <td class="top-table-label">材积：</td>
              <td class="top-table-label">单价：</td>
              <td class="top-table-label">金额：</td>
          </tr>
          <tr>
              <td><select id="treetype"><option value="">材种选择</option>
              <option value="shan">杉原木</option>
              <option value="song">松原木</option>
              <option value="xiao">杉小径</option>
              <option value="songxiao">松小径</option>
              <option value="za">杂原木</option>
              <option value="zaxiao">杂小径</option>
              </select></td>
              <td><input type="text" disabled="disabled" id="tvolume"></td>
              <td><input type="text" disabled="disabled" id="unitprice"></td>
              <td><input type="text" disabled="disabled" id="price"></td>
          </tr>
      </table>
        <table class="table" >
                 <tbody>
                 <p class="table_p" style="margin-top: 65px;"><span>合计信息</span></p>
                <tr>
                 <td style="font-size:20px;width:auto">合计材积<span></span>
                  <input type="text" style="width:120px; font-size:20px" name="ttvolume" id="ttvolume" onclick="makecount()">立方米<span></span></td>
                  <td style="font-size:20px">合计金额<span></span>
                   <input type="text" style="width:120px; font-size:20px" name="tprice" id="tprice">元<span></span></td>
                   </tr>
                   </tbody>
         </table>   
        <table class="table" >
            <tbody>
            <p class="table_p"><span>填单人</span></p>
            <tr>
                <td>分管领导:<span></span></td>
                <td><input type="text"disabled="disabled"></td>
                <td>生产经营部负责人:<span></span></td>
                <td><input type="text" disabled="disabled"></td>
                <td>复核:<span></span></td>
                <td><input type="text" disabled="disabled"></td>
                <td>填单人:<span></span></td>
                <td><input type="text" name="person" id="person"></td>
            </tr>
            </tbody>
        </table>
    </div>
    <div style="text-align: center">
    <button class="add-but"><i class="glyphicon glyphicon-edit" onclick="mysave()"></i> 提交 </button>
    <button class="add-del"><i class="glyphicon glyphicon-remove"></i>取消</button>
    <button class="add-but" id="btnPrint"><i class="glyphicon glyphicon-edit"></i> 打印</button>
    </div>
    <div class="table-con">
        <table id="table1" class="table-style"></table>
    </div>
    		<div class="table-con">
          <table class="table" id="query">
           </table>
           </div>

</article>
</main>
</div>
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