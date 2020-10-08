<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%@page import="java.text.SimpleDateFormat"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*"%>
 <%@ page import="jinshen.bean.*" %>
<%@ page import="java.util.List" %>
    <!-- 销售结算页面 -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>查看木材销售货款结算单</title>
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
.table1{width:90%; height:auto;margin-left:auto; margin-right:auto;padding:10px;border-collapse:collapse}
    </style>
<script src="js/bstable/js/bootstrap.min.js"></script>  

</head>
<body>
<%
List<Laowu> t=null;
   t=(List<Laowu>)request.getAttribute("treein");
%>
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
        <p class="p-tail"><i class="i-tail"></i>该界面是生成木材销售货款结算单</p>
    </div>
    <div class="find-top1" id="divprint">
    <p class="table_p" style="margin-left: 40%;"><span style="font-size:20pt">木材销售货款结算单</span></p>
           <table class="table1" border="1" style="margin-left:auto; margin-right:auto;">
           
            <tr>
            <td colspan="7" style="width:300px">结算时间：<%=now%></td>
            </tr>
            <tr>
            <td style="width:320px" colspan="1">调令号</td>
            <td colspan="2"><input  type="text" style="border:0px;background-color: transparent;" name="saleCalloutOrder" id="saleCalloutOrder" value="<%=t.get(0).getSaleCalloutOrder() %>"></td>
            <td colspan="2">合同号</td>
            <td colspan="2"><input style="border:0px;background-color: transparent;" type="text" name="contractnum" id="contractnum" value="<%=t.get(0).getContractnum() %>"></td> 
            </tr>
            <tr>
                <td style="width:320px" colspan="1">采伐证编号</td>
                <td colspan="2"><input type="text" style="border:0px;background-color: transparent;" id="cutnum" value="" name="cutnum" value="<%=t.get(0).getCutnum() %>"></td>
                <td colspan="2">收货单位</td>
                <td colspan="2"><input style="border:0px;background-color: transparent;" readonly="readonly" type="text" name="consignee" id="consignee" value="<%=t.get(0).getConsignee() %>"></td>
            </tr>
            <tr>
            <td style="width:320px" colspan="1">发货单位</td>
                <td colspan="2"><input style="border:0px;background-color: transparent;" readonly="readonly" type="text" name="company" id="company" value="<%=t.get(0).getCompany() %>"></td>
                <td colspan="2">供货地点</td>
                <td colspan="2"><input style="border:0px;background-color: transparent;" readonly="readonly" type="text" name="yard" id="yard" value="<%=t.get(0).getYard() %>"></td>
            </tr>
            
            <tr>
            <td style="width:320px">树材种</td>
            <td style="width:200px">长度</td>
            <td style="width:200px">径级</td>
            <td style="width:200px">根数</td>
            <td style="width:200px">材积</td>
            <td style="width:200px">单价</td>
            <td colspan="1">金额</td>
            </tr>
            <tr>
               <% int i=1;%>
               <c:forEach items="${treein}" var="b">      
            <tr id="<%=i%>" display:block;>
            <td>
          <input style="width: 180px;border:0px;background-color: transparent;" type="text" name="treetype" id="sss<%=i%>" value="${b.getTreetype()}"></td>
          <td><input type='text'  style='width: 180px;border:0px;background-color: transparent;' name='tdouble' id='td<%=i%>' value="${b.getTlong()}"></td>
          <td><input type='text' style='width: 180px;border:0px;background-color: transparent;' name='tradius' id='tr<%=i%>' value="${b.getTradius()}"></td>
          <td> <input type='text'  style='width: 180px;border:0px;background-color: transparent;' name='num' id='n<%=i%>' value="${b.getNum()}"></td>
         <td> <input type='text' style='width: 180px;border:0px;background-color: transparent;' name='tvolume' id='tv<%=i%>' value="${b.getTvolume()}"></td>
          <td><input type='text' style='width: 180px;border:0px;background-color: transparent;' name='unitprice' id='tv<%=i%>' value="${b.getUnitprice()}"></td>
          <td><input type='text'  style='width: 180px;border:0px;background-color: transparent;' name='price' id='tv<%=i%>' value="${b.getprice()}"></td>
         </tr>
          <%i++; %>
           </c:forEach> 
           </tr>
              <tr>
              <td>合计</td>
              <td colspan="2">&nbsp;</td>
              <td colspan="1"><input type="text" style="border:0px;background-color: transparent;" readonly="readonly" name="tnum" id="tnum" value="<%=t.get(0).getTnum() %>"><span></span></td>
              <td colspan="1"><input type="text" style="border:0px;background-color: transparent;" readonly="readonly" name="ttvolume" id="ttvolume" value="<%=t.get(0).getttvolume() %>"><span></span></td>
              <td colspan="1">&nbsp;</td>
              <td colspan="2"><input type="text" style="border:0px;background-color: transparent;" readonly="readonly" name="tprice" id="tprice" value="<%=t.get(0).getTprice() %>"><span></span></td>
              </tr>
              <tr>
              <td>注：本批结算单附码单有${worklength}份，码单号如下<br/></td>
              <td colspan="7">
              <input style="width:100%;border:0px;background-color: transparent;" id="bworkid" name="bworkid" value="<%=t.get(0).getTworkid() %>">
              </td>
              </tr>
        </table>
        <table class="table1" >
            <tbody>
            <tr>
                <td style="margin-left:65px;">生产经营部（公章）:&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td style="margin-left:65px;">负责人:&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td style="margin-left:65px;">复核:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td style="margin-left:65px;">制单:</td>
                <td><input type="text" style="border:0px;background-color: transparent;" name="person" id="person" value="<%=t.get(0).getPerson() %>"></td>
            </tr>
            </tbody>
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