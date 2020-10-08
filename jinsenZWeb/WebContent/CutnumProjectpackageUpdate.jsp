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
   <title>创建采伐证工程包</title>
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
.htv {
    float:left;font-weight:bold;color:#966f2b;text-decoration: underline;
}
</style>
<script type="text/javascript">
//保存工程包对应的采伐证号
function addproject(){
	var map={};
	var group=[];
	//var projectPackageid=$("#projectPackageid").val();
	var projectPackageName=document.getElementById("projectPackageName").value;
	var contractNum=document.getElementById("contractNum").value;
	var contractionSide=document.getElementById("contractionSide").value;
	var str=$("#table2").bootstrapTable('getSelections');
	if(str.length<1) {
		alert("无选中记录！");
	}
	else{
		
	    if(projectPackageName=="")
	   {
	    alert("请先输入工程包号！");
	    }
    else{
    	for(var i=0;i<str.length;i++){
    		var j=str[i];
    		group[i]=j.cutnum;
    	}
 var mymap=JSON.stringify(group);
$.ajax({
    url:"cutnumServlet",
    data:{
        "action":"updateproject",
        "project":mymap,
        "projectPackageName":projectPackageName,
        "contractNum":contractNum,
        "contractionSide":contractionSide
    },
    type: "POST",
    dataType:"html",
    success: function (data) {
    	//alert(data);
    	if(data>0)
    		{
    	        alert("添加成功！");
    	        $("#table1").bootstrapTable('refresh');
    	        $("#table2").bootstrapTable('refresh');
    		}
    	else{
    		 alert("添加失败");
    	}
    }
})
   }
}
}
//从工程包中删除采伐证号
function overtimedelete(cutnum)
{
	//var projectPackageid=document.getElementById("projectPackageid").value;
	$.ajax({
        url:"cutnumServlet",
        data:{
            "action":"alldelete",
            "cutnum":cutnum,
            //"projectPackageid":projectPackageid
        },
        type: "Post",
        dataType:"json",
        success: function (data) {
        	if(data)
        		{
        		alert("删除成功！");
        		$("#table1").bootstrapTable('refresh');
        		$("#table2").bootstrapTable('refresh');
        		
        		}
        		
        	else
        		alert("删除失败！");
        }
    })
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
<% projectpackage c=(projectpackage)request.getAttribute("projectpackage");%>
<% List<cutnum> o=null;
o=(List<cutnum>)request.getAttribute("cutnum");
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
    <div id="breadcrumb"> <a href="" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
  </div>
<!--End-breadcrumbs-->
<main>
    <div class="find-top">
        <p class="p-tail"><i
         class="i-tail"></i> 该页面主要是：移除和添加新的采伐证号</p>
    </div>
    <div class="find-top1">
    <h1 class="book_h01" align="top">编辑工程包（新增和移除采伐证）</h1>
    <div class="top" id="divprint">
    <p class="htv"><span>工程包信息（不可修改）:</span></p>
    <table style="margin-left: auto;margin-right: auto;">
       <tr>
       <td>&nbsp;&nbsp;&nbsp;&nbsp;工程包创建时间:</td><td><input type="text" name="projpackageStarttime" id="projpackageStarttime" value="<%=c.getProjpackageStarttime() %>" readonly="readonly"></td>
       <td>&nbsp;&nbsp;&nbsp;&nbsp;工程包名称:</td><td><input type="text" name="projectPackageName" id="projectPackageName" value="<%=c.getProjectPackageName() %>" readonly="readonly"></td>
       <td>&nbsp;&nbsp;&nbsp;&nbsp;合同编号:</td><td><input type="text" name="contractNum" id="contractNum" value="<%=c.getContractNum() %>" readonly="readonly"></td>
       </tr>
       <tr>
       <td>&nbsp;&nbsp;&nbsp;&nbsp;施工方:</td><td><input type="text" name="contractionSide" id="contractionSide" value="<%=c.getContractionSide() %>" readonly="readonly"></td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;采伐工期:</td><td><input type="text" name="cuttime" id="cuttime" value="<%=c.getCuttime() %>" readonly="readonly"></td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;伐区管理员:</td><td><input type="text" name="forester" id="forester" value="<%=c.getForester() %>" readonly="readonly"></td>   
       </tr>
       <tr>
       <td>&nbsp;&nbsp;&nbsp;&nbsp;合同文件:</td>
       <td>${projectfile}<a href="DownfileServlet?action=projectfile&filename=<%=c.getAccessory() %>">下载</a></td>
       </tr>
       <tr>
       <td>采伐证数量</td><td><input type='text' id="tlength" value="<%=o.get(0).getProjectid() %>" readonly="readonly"></td>
       <td>总采伐面积</td><td><input type='text' id="cutarea" value="<%=o.get(0).getCutarea() %>" readonly="readonly"></td>
       <td>总出材量</td><td><input type='text' id="volume" value="<%=o.get(0).getVolume() %>" readonly="readonly"></td>
       </tr>
       <tr>
       <td>${tree1}</td><td><input type='text' id="total1" value="<%=o.get(0).getTotal() %>" readonly="readonly"></td>
       <td>${tree2}</td><td><input type='text' id="total2" value="<%=o.get(1).getTotal() %>" readonly="readonly"></td>
       <td>${tree3}</td><td><input type='text' id="total3" value="<%=o.get(2).getTotal() %>" readonly="readonly"></td>
       </tr>
       <%-- <tr>
       <% int i=1;%>
		<c:forEach items="${cutnum}" var="b"> 
       <td id="<%=i%>" colspan="12"><input type="text" id="tree<%=i%>" value="${b.getTreetype()}"><input type='text' id="total<%=i%>" value="${b.getTotal()}" readonly="readonly"></td>
       <%i++; %>
        </c:forEach>
       </tr>--%>
    </table>
    <!-- 显示最近新增的采伐证号 -->
    <div class="htv" style="font-size:20px"><span>已添加采伐证号:</span></div>
    <div class="table-con">
        <table id="table1" class="table-style"></table>
    </div>
    <div class="htv" style="font-size:20px"><span>未添加采伐证号:（工程包新增采伐证信息，请勾线以下未添加采伐证）</span></div>
    <div class="table-con">
        <table id="table2" class="table-style"></table>
    </div>
    </div>
    <div class=" but_p" style="float:center;">
    <button class="but_save" type="button" onclick="addproject()" value="更新工程包">更新工程包</button>
    <button class="but_save" type="submit" id="mybutton" value="返回添加工程包" onclick="javascrtpt:window.location.href='cutnumProjectpackage.jsp'">返回添加工程包</button></div>
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
<script src="js/jquery.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
<script>
    $(function(){
    	var ta1=new table1();
    	var ta2=new table2();
    	ta1.Init();
    	ta2.Init();
    	//table1();
    	//table2();
    })
    function table2(){
            $('#table2').bootstrapTable({
                method: "get",
                striped: true,
                singleSelect: false,
                url: "cutnumServlet?action=cutnumproject",
               // async : false,
                data:{},
                dataType: "json",
                pagination: true, //分页
                pageSize: 8,
                pageNumber: 1,
                search: true, //显示搜索框
                contentType: "application/x-www-form-urlencoded",
                showRefresh: true,                      //是否显示刷新按钮
                showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
                detailView: true,
            columns: [[
            	{
            		"title": "采伐证信息",
                    "font-size":"100px",
                    "halign":"center",
                    "align":"center",
                    "valign": "middle",
                    "colspan": 8
                }],
                [
                	{
                        checkbox: "true",
                        field: 'check',
                        align: 'center',
                        valign: 'middle'
                    },
                    {
                        title: '采伐证号',
                        field: 'certificatenum',
                        width:200,
                        align: 'center',
                        valign: 'middle'
                    },
                	{
                    title: "采伐证编号",
                    field: 'cutnum',
                    width:200,
                    align: 'center',
                    valign: 'middle'
                },
                
                {
                    title: '采法许开始时间',
                    field: 'starttime',
                    width:200,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采法许结束时间',
                    field: 'endtime',
                    width:200,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '林权单位',
                    field: 'company',
                    width:200,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐面积',
                    field: 'cutarea',
                    width:200,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐蓄积',
                    field: 'cutstore',
                    width:200,
                    align: 'center',
                    valign: 'middle'
                },
                ],
            ]
        });
    }
    
    /*$(function(){
    	table2();
    })*/
    function table1(){
            $('#table1').bootstrapTable({
                method: "get",
                //async : false,
                striped: true,
                singleSelect: false,
                url: "cutnumServlet?action=cutnumprojectS",
                data:{},
                dataType: "json",
                pagination: true, //分页
                pageSize: 8,
                pageNumber: 1,
                search: true, //显示搜索框
                contentType: "application/x-www-form-urlencoded",
                showRefresh: true,                      //是否显示刷新按钮
                showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
                detailView: true,
                queryParams:function queryParams(params){
                    var temp = {
    						//action:"cutnumproject",
    						projectPackageName:document.getElementById("projectPackageName").value,
    				};     
                    return temp;
                },
            columns: [[
            	{
            		"title": "采伐证信息",
                    "font-size":"100px",
                    "halign":"center",
                    "align":"center",
                    "valign": "middle",
                    "colspan": 9
                }],
                [
                	{
                        checkbox: "true",
                        field: 'check',
                        align: 'center',
                        valign: 'middle'
                    },
                	{
                        title: '采伐证号',
                        field: 'certificatenum',
                        width:200,
                        align: 'center',
                        valign: 'middle'
                    },
                	
                	{
                    title: "采伐证编号",
                    field: 'cutnum',
                    width:200,
                    align: 'center',
                    valign: 'middle'
                },
                
                {
                    title: '采法许开始时间',
                    field: 'starttime',
                    width:200,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采法许结束时间',
                    field: 'endtime',
                    width:200,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '林权单位',
                    field: 'company',
                    width:'200px',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐面积',
                    field: 'cutarea',
                    width:200,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐蓄积',
                    field: 'volume',
                    width:200,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<button type="button" data-id="98"  class="btn btn-xs btn-primary" onclick="overtimedelete(\''+ row.cutnum + '\')">移出本工程包</button> ';
                        //var  d = '<a href="workpageSevrlet?action=alldelete&workid=\''+ row.workid + '\'"><button  id="id="delete" data-id="98" class="btn btn-xs btn-primary">删除</button></a>';
                        return  d;
                    }
                },
                ],
            ]
        });
    }

</script> 
</body>
</html>