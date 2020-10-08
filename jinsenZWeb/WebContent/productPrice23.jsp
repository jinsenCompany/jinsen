<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%@page import="java.text.SimpleDateFormat"%>

<%@page import="java.util.*"%>
    <!-- 销售结算页面 -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>木材生产工资结算单</title>
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
.table1{width:70%; height:aito;margin-left:auto; margin-right:auto;padding:10px;border-collapse:collapse}
.button{
    COLOR:   #FF0000;
    background-color:   #f7f7f7 ;
    BORDER-RIGHT:   #999999   1px   solid;
    BORDER-TOP:   #ffffff   1px   solid;
    FONT-SIZE:   15pt;
    BORDER-LEFT:   #ffffff   1px   solid;
    WIDTH:   40px;             
    BORDER-BOTTOM:   #999999   1px   solid;
    HEIGHT:   18px;               
}
    </style>
<script src="js/bstable/js/bootstrap.min.js"></script>  
<script type="text/javascript">

/*function mycreate1()
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
        	alert(project);
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
}*/
function mycreate()
{
	//var length=$("#codetable tr").length;
	var cutnum=document.getElementById("cutnum").value;
	var timeStart=document.getElementById("timeStart").value;
	var timeEnd=document.getElementById("timeEnd").value;
	var yard=document.getElementById("yard").value;
	var section=document.getElementById("section").value;
	length=(length-1)*2;
	var group=[];
	$.ajax({
        url:"salaryServlet",
        data:{
            "action":"savew23",
            "cutnum":cutnum,
            "timeStart":timeStart,
            "timeEnd":timeEnd,
            "yard":yard,
            "section":section
        },
        type: "POST",
        dataType:"json",
        success: function (data) {
        	if(data==null)
        		{
        		alert("您所需要生成的码单信息有误，请重新核对");
        		}
        	else{
        	var cutCompack=data["cutCompack"];
        	var contractionSide=data["contractionSide"];
        	var workidlist=data["workid"];
        	var worktree=data["worktree"];
        	//alert(worktree)
        	$("#mysomething").empty();
        	var aworkid, bworkid;
        	var group=[];
        	for(var i=0;i<workidlist.length;i++)
    		{
        		var j=workidlist[i];
        		var workid=j.workid;
        		var str2="<tr><td><input type='text' id='workid' disabled='disabled' value='"+j.workid+"'></td></tr>";
        		$("#mysomething").append(str2); 
        		group[i]=j.workid;
    		}
        	aworkid = new Array(group);
    		bworkid = aworkid.join("-");
        	for(var i=0;i<cutCompack.length;i++)
    		{
        		var j=cutCompack[i];
        		var manageUnit=j.company;
        		var projectPackageName=j.projectPackageName;
        		var cutaddress=j.cutaddress;
        		var cutvillage=j.cutvillage;
        		var quartel=j.quartel;
        		var largeblock=j.largeblock;
        		var smallblock=j.smallblock;
    		}
        	for(var i=0;i<contractionSide.length;i++){
        		var j=contractionSide[i];
        		var forperson=j.contractionSide;
        	}
        	//$("#ttt").empty();
        	/*var str="<tr><td class='top-table-label'>材种：</td>"
                +"<td class='top-table-label'>长度：</td>"
                +"<td class='top-table-label'>径级：</td>"
                +"<td class='top-table-label'>材积：</td>"
                +"<td class='top-table-label'>单价：</td>"
                +"<td class='top-table-label'>金额：</td></tr>";*/
                $("#ttt").empty();
        	for(var i=0;i<worktree.length;i++){
        		var j=worktree[i];
        		var str="<tr id='"+(i+1)+"'><td><input type=text id='treetype"+(i+1)+"' value='"+j.type+"'></td>"
        		+"<td><input type=text disabled=disabled value='"+j.tlong+"' id='tlong"+(i+1)+"'></td>"
        		+"<td><input type=text disabled=disabled value='"+j.tradius+"' id='tradius"+(i+1)+"'></td>"
                +"<td><input type=text disabled=disabled value='"+j.tvolume+"' id='volume"+(i+1)+"'></td>"
                 +"<td><input type='text' id='unitprice"+(i+1)+"' value='"+j.unitprice+"' oninput='priceCount("+(i+1)+")' onclick='locationInput'></td>"
                +"<td><input type='text' id='price"+(i+1)+"' onclick='priceCount("+(i+1)+")' value='"+j.price+"'><td></tr>"
                $("#ttt").append(str); 
        	}
      	//ttt.innerHTML=str;
        	document.getElementById("manageUnit").value=manageUnit;
        	document.getElementById("projectPackageName").value=projectPackageName;
        	document.getElementById("forperson").value=forperson;
        	document.getElementById("cutaddress").value=cutaddress;
        	document.getElementById("cutvillage").value=cutvillage;
        	document.getElementById("quartel").value=quartel;
        	document.getElementById("largeblock").value=largeblock;
        	document.getElementById("smallblock").value=smallblock;
        	document.getElementById("bworkid").value=bworkid;
        	
        }}
    })
}
function mysave()
{
	var map={};
	var kk=0;
	var cutnum=$("#cutnum").val();
	var projectPackageName=document.getElementById("projectPackageName").value;
	var forperson=document.getElementById("forperson").value;
	var manageUnit=document.getElementById("manageUnit").value;
	var person=document.getElementById("person").value;
	var ttvolume=document.getElementById("ttvolume").value;
	var tprice=document.getElementById("tprice").value;
	var timeStart=document.getElementById("timeStart").value;
	var timeEnd=document.getElementById("timeEnd").value;
	var bworkid=document.getElementById("bworkid").value;
	var yard=document.getElementById("yard").value;
	var section=document.getElementById("section").value;
	if(ttvolume=="" || tprice=="")
	{
	alert("请填写合计材积和金额")
	}
else{
		var length=$("#ttt tr").length;
		//alert(length)
		//length=length-2;
		//alert(length)
    	for(var id=1;id<=length;id++){
    		var group=[];
    	    group[0]=document.getElementById("treetype"+id+"").value;
    	    group[1]=document.getElementById("tlong"+id+"").value;
    	    group[2]=document.getElementById("tradius"+id+"").value;
    	    group[3]=document.getElementById("volume"+id+"").value;
    	    group[4]=document.getElementById("unitprice"+id+"").value;
    	    group[5]=document.getElementById("price"+id+"").value;
    	    if(group[0]==""|| group[1]==""|| group[2]==""|| group[3]==""|| group[4]==""|| group[5]=="")
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
            "action":"saveLaowuRad",
            "newtree":mymap,
            "id":kk,
            "cutnum":cutnum,
            "projectPackageName":projectPackageName,
            "forperson":forperson,
            "manageUnit":manageUnit,
            "person":person,
            "ttvolume":ttvolume,
            "tprice":tprice,
            "timeEnd":timeEnd,
            "timeStart":timeStart,
            "bworkid":bworkid,
            "yard":yard,
            "section":section
        },
        type: "POST",
        dataType:"html",
        success: function (data) {
        	//alert(data);
        	if(data>0)
    		{
    	        alert("添加成功！");
    	        window.location.href = 'productPrice23.jsp';
    		}
        	else 
        		{
        		alert("添加失败！");
        		}
        }
    })
}
  }
//计算金额
function priceCount(id)
{
	
	var volume=document.getElementById("volume"+id+"").value;
	var unitprice=document.getElementById("unitprice"+id+"").value;
	var vvvo=volume*unitprice;
	var vvvo=vvvo.toFixed(2);
	document.getElementById("price"+id+"").value=vvvo;
}
//计算合计材积和金额
function makecount()
{
	var length=$("#ttt tr").length;
	    length=length;
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
	ttvolume=ttvolume.toFixed(2);
	tprice=tprice.toFixed(2);
   document.getElementById("ttvolume").value=Number(ttvolume);
   document.getElementById("tprice").value=Number(tprice);
}
window.onload = function () {
    locationInput = function () {
    };
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
    <li>
    <%
	String staff_id = request.getSession().getAttribute("staff_id").toString();
				%> <%
 	String staff_name = request.getSession().getAttribute("staff_name").toString();
 %> 您好，<%=staff_id%> <%=staff_name%>欢迎登录
    </li>
  </ul>
</div>
<!--close-top-Header-menu-->

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
    <div id="breadcrumb"> <a href="forestP.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a></div>
  </div>
<main>
    <article class="artlce">
    <div class="find-top">
        <p class="p-tail"><i class="i-tail"></i>该界面是生成木材生产结算的主要界面</p>
    </div>
    <br>
    <div style="text-align: center">
    <button class="bg_lb" style="width:300px;height:60px;margin-right:65px;" type="button" onclick="window.location='productPrice2.jsp'"><i class="icon-dashboard"></i>详细木材生产结算</button>
    <button class="bg_lr" style="width:300px;height:60px;" type="button" onclick="window.location='productPrice2.jsp'"><i class="icon-tint"></i>分径级木材生产结算</button>
    </div>
    <br>
    <div class="find-top1" id="divprint">
     <p class="table_p" style="margin-left: 40%;"><span style="font-size:20pt">生产工资结算</span></p>
           <table class="top-table" border="1">
            <tr>
            <td colspan="7" style="width:300px">结算时间：<%=now%></td>
            </tr>
            <tr><p><span style="font-size:16pt;color: blue; border-left: medium none; border-right: medium none; border-top: medium none; border-bottom: 1px solid rgb(192,192,192)">请选择时间以及输入采伐证号:</span></p></tr>
            <tr>
               <td colspan="2">选择开始日期：</td><td><input width="160" type="date" name="timeStart" id="timeStart"  value="2020-01-01"></td>
               <td colspan="2">选择结束日期：</td><td><input width="160" type="date" name="timeEnd" id="timeEnd" value="2020-12-01"></td>
                <td class="top-table-label" colspan="2">采伐证编号：</td>
                <td colspan="2"><input type="text" id="cutnum" name="cutnum" value="" oninput='mycreate()' onclick='locationInput'></td>
            </tr>
            <tr>
                <td class="top-table-label" colspan="2">货场：</td>
                <td><select  style="width:230px" name="yard" id="yard" oninput='mycreate()' onclick='locationInput'><option  value="" selected="selected"></option></select></td>
                <td class="top-table-label" colspan="2">货场分区：</td>
                <td colspan="2"><select id="section" name="section"  oninput='mycreate()' onclick='locationInput'>
                <option value='' selected></option>
                <option value='A区'>A区</option>
                <option value='B区'>B区</option>
                <option value='C区'>C区</option>
                <option value='D区'>D区</option>
                <option value='E区'>E区</option>
                <option value='F区'>F区</option>
                <option value='无固定分区'>无固定分区</option>
                </select></td>
            </tr>
            </table>
            <br>
            <table class="top-table">
            <tr>
            <td>工程包:</td>
                <td colspan="1"><input type="text" name="projectPackageName" id="projectPackageName" value=""></td>
            <td colspan="1">劳务承包人：</td>
                <td colspan="2"><input type="text" name="forperson" id="forperson" value=""></td>
                <td colspan="2">伐区管理单位：</td>
                <td colspan="2"><input type="text" name="manageUnit" id="manageUnit" value=""></td>
            </tr>
            <tr>
              <td colspan="8"><input style="width:200px" type="text" name="cutaddress" id="cutaddress" >乡(镇)<input style="width:200px" type="text" name="cutvillage" id="cutvillage">村<input style="width:250px" type="text" name="quartel" id="quartel">林班<input style="width:200px" type="text" name="largeblock" id="largeblock">大班<input style="width:200px" type="text" name="smallblock" id="smallblock">小班</td>
            </tr>
            <tr>
            <td  style="width:200px">树材种</td>
            <td style="width:200px" >长度</td>
            <td style="width:200px">径级</td>
            <td style="width:200px">材积</td>
            <td style="width:200px">单价</td>
            <td style="width:200px">金额</td>
            <td style="width:200px" colspan="2">码单号</td>
            </tr>
              <tr>
              <td colspan="6">
              <table id="ttt" class="top-table" border="1">
              </table>
              </td>
              <td colspan="2">
              <table id="mysomething" class="top-table">
              <tr>
              
              </tr>
              </table>
              </td>
              </tr>
              <tr>
              <td>合计</td>
              <td colspan="3"><input type="text" style="width:120px; font-size:20px" name="ttvolume" id="ttvolume" onclick="makecount()">立方米<span></span></td>
              <td  colspan="4"><input type="text" style="width:120px; font-size:20px" name="tprice" id="tprice">元<span></span></td>
              </tr>

        </table>  
        <table class="table" >
            <tbody>
            <tr>
                <td style="margin-left:65px;">分管领导:&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td style="margin-left:65px;">生产经营部负责人:&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td style="margin-left:65px;">复核:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td style="margin-left:65px;">填单人:</td>
                <td><input type="text" name="person" id="person"></td>
            </tr>
            </tbody>
        </table>
        <table class="table1"  style="display: none">
              <tr>
              <td colspan="7">
              <textarea style="width:100%" id="bworkid" name="bworkid"></textarea>
              </td>
              </tr>
              </table>
    </div>
    <br>
    <div style="text-align: center">
    <button class="add-but" style="color:red;margin-right:65px;" type="button" onclick="mysave()"><i class="glyphicon glyphicon-edit"></i>保存</button>
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