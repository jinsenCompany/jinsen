<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="jinshen.bean.*" %>
<%@ page import="java.util.List" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>木材产销管理平台导航</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link type="text/css" rel="stylesheet" href="css/PrintArea.css" />
<!-- Custom CSS -->
<link rel="stylesheet" href="css/registe.css"/>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/fullcalendar.css" />
<link rel="stylesheet" href="css/matrix-style.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="css/jquery.gritter.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
</head>
<style>
 body,td,th {font-family: Verdana, Arial, Helvetica, sans-serif;font-size: 20px;color: #1d1007; line-height:24px}
 .table1{width:100%;margin-left:auto; margin-right:auto;padding:10px;border-collapse:collapse}
 .table1 td{width:100px}
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
<body>
<% saleCalloutOrder s=(saleCalloutOrder)request.getAttribute("saleCalloutOrder");%>
<% salecontract w=(salecontract)request.getAttribute("contractPrivoder");%>
<% customer c=(customer)request.getAttribute("compShip");%>
<% outyard o=(outyard)request.getAttribute("outyard");
List<tree> t=null;
   t=(List<tree>)request.getAttribute("tree");
%>
<!--Header-part-->
<div id="header">
  <h1><a href="dashboard.html">产销部门平台</a></h1>
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
<!--main-container-part-->
<div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"> <a href="salesmanTwo.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> 首页</a></div>
  </div>
<div id="divprint">
<!--breadcrumbs-->
    <table class="table1" border="1">
    <caption class="book_h01">木材销售码单</caption>
    <tbody>
    <tr>
    <td>销售码单号</td>
    <td colspan="9">
    <input style="border:0px;background-color: transparent;font-size:20px;" type="text" name="workid" maxlength="32" id="wworkid" readonly value="<fmt:formatNumber value="${workid}" pattern="#0.##"/>" ></td></tr>
    <tr>
    <td style="width:200px">供货单位</td><td><input style="border:0;background-color: transparent;font-size:20px" name="provider" id="provider" value="<%=w.getProvider() %>"></td>
    <td>合同号</td><td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="contractnum" id="contractnum" value="<%=s.getContractnum() %>"></td>
    <td>采伐证号</td><td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="cutnum" id="cutnum" value="<%=o.getCutnum() %>"></td>
    <td colspan="2">调运通知单号</td><td colspan="2"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="saleCalloutOrder" id="saleCalloutOrder" value="<%=s.getSaleCalloutOrder() %>"></td>
    </tr>
    <tr>
    <td>收货单位</td><td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="company" id="company" value="<%=c.getCompany() %>"></td>
    <td>检验地点(货场)</td><td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="yard" id="yard" value="<%=s.getYard() %>"></td>
    <td colspan="2">运输起讫</td><td colspan="4"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="shipaddress" id="shipaddress" value="<%=c.getShipaddress() %>"></td>
    </tr>
    <tr>
    <td>有效时间</td><td colspan="3"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="callidtime" id="callidtime" value="<%=s.getCallidtime() %>"></td>
    <td>车号</td><td colspan="2"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="carnumber" id="carnumber" value="<%=o.getCarNumber() %>"></td>
    <td>标品号</td><td colspan="2"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="marknumber" id="marknumber" value="<%=w.getMarknumber() %>"></td>
    </tr>
    </tbody>
    <tbody>
    <tr>
    <th colspan="2">树材种</th>
    <th colspan="2">检尺长(m)</th>
    <th colspan="2">检尺径(cm)</th>
    <th colspan="2">根数</th>
    <th colspan="2">材积（m³、T、根）</th>
    </tr>
    <% int i=1;%>
     <c:forEach items="${tree}" var="b">    
     <tr id="<%=i%>">
     <td colspan="2"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="treetype" id="sss<%=i%>" value="${b.getTreetype()}"></td>         
     <td colspan="2"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type='text' style='' name='tlong' id='tl<%=i%>' value="${b.getTlong()}"></td>
     <td colspan="2"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type='text' name='tradius' id='tr<%=i%>' value="${b.getTradius()}"></td>
     <td colspan="2"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type='text' name='num' id='nu<%=i%>' value="${b.getNum()}"></td>
     <td colspan="2"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type='text' name='tvolume' id='tv<%=i%>' value="${b.getTvolume()}"></td></tr>
          <%i++; %>
       </c:forEach>
       <tr>
       <td rowspan="2">合计(大写)</td>
      <td colspan="4"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="toltree" id="toltree" value=""><span>根（块、件）</span></td>
      
      <td rowspan="2">合计(小写)</td>
      <td colspan="4"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="toltree" id="toltree" value="<%=o.getToltree() %>"><span>根（块、件）</span></td>
      </tr>
      <tr>
      <td colspan="4"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="tolstere" id="tolstere"><span>立方米吨、根</span></td> 
      <td colspan="4"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="tolstere" id="tolstere" value="<%=o.getTolstere() %>"><span>立方米吨、根</span></td> 
      </tr>  
    </tbody>
    </table>
   <table class="table1">
   <tr>
   <td>检验员:</td><td><input type="text" style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="surveyor" id="surveyor" value="<%=o.getSurveyor() %>"></td>
   <td>记码员:</td><td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center"></td>
   <td>发货人:</td><td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" ></td>
   <td>收货人:</td>
   </tr>
   </table>
   
    <hr/>
</div>
<div style="text-align:center">
<!--      <span><input style="width:120px;heigth:60px" class="btn" type="button" id="btnPrint" value="打印"/>  </span> -->
<a href="DownfileServlet?action=treeoutYeExcel"><button type="button" style="width:150px;height:50px;" class="btn">下载</button></a>
      </div>
      
</div>
</body>
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/jQuery.print.js"></script>
<script src="js/jquery.PrintArea.js" type="text/JavaScript"></script>
<script type="text/javascript">
$(function(){
    $("#btnPrint").click(function(){ $("#divprint").printArea(); });
});
</script>
<script src="js/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
<script src="js/bootstrap-table-export.js"></script>
<script src="js/tableExport.js"></script>
<script src="js/jquery.base64.js"></script>
<script type="text/javascript" src="/static/js/xlsx.full.min.js"></script>
<script src="https://unpkg.com/tableexport.jquery.plugin/libs/jsPDF/jspdf.min.js"></script>
<script src="https://unpkg.com/tableexport.jquery.plugin/libs/jsPDF-AutoTable/jspdf.plugin.autotable.js"></script>
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
</html>
