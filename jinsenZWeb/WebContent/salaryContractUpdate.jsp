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
   <title>输入销售合同页面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="css/bootstrap-2.0.3.css" rel="stylesheet">
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
.loader-container {
  height: 6px;
  width: 600px;
  position: absolute;
  top: 50%;
  left: 50%;
  margin-top: -3px;
  margin-left: -300px;
  background-color: transparent;
  background-image: -webkit-linear-gradient(left, #5bd8ff, #ff0000);
  background-image: -moz-linear-gradient(left, #5bd8ff, #ff0000);
  background-image: -o-linear-gradient(left, #5bd8ff, #ff0000);
  background-image: -ms-linear-gradient(left, #5bd8ff, #ff0000);
  background-image: linear-gradient(left, #5bd8ff, #ff0000);
  box-shadow: inset 0 -2px 2px rgba(0, 0, 0, 0.4);
  border-radius: 3px 0 0 3px;
}
.loader-container:after {
  content: "";
  display: block;
  position: absolute;
  right: 0;
  top: 50%;
  width: 1em;
  height: 1em;
  border-radius: 50%;
  margin-top: -0.5em;
  margin-right: -1em;
  background-image: -webkit-linear-gradient(top, #000000, #212121);
  background-image: -moz-linear-gradient(top, #000000, #212121);
  background-image: -o-linear-gradient(top, #000000, #212121);
  background-image: -ms-linear-gradient(top, #000000, #212121);
  background-image: linear-gradient(top, #000000, #212121);
}
</style>
<script type="text/javascript">
</script>
</head>
<body>
<% salecontract s=(salecontract)request.getAttribute("salecontract"); 
List<salecontract> b=null;
b=(List<salecontract>)request.getAttribute("salecontractTree");
%>
<% contractProgress c=(contractProgress)request.getAttribute("contractProgress"); %>
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
  <li class="submenu"> <a href="#"><i class="icon icon-home"></i> <span>合同管理</span> <span class="label label-important">2</span></a>
     <ul>
        <li><a href="salaryContract.jsp">创建合同</a></li>
        <li><a href="salaryContractList.jsp">合同进度</a></li>
      </ul>
     </li>
    <li class="submenu"> <a href="#"><i class="icon icon-signal"></i> <span>客户信息管理</span> <span class="label label-important">2</span></a>
     <ul>
        <li><a href="productowner.jsp">创建客户信息</a></li>
        <li><a href="productownerSee.jsp">查看客户信息</a></li>
      </ul>
     </li>
     <li class="submenu"> <a href="#"><i class="icon icon-inbox"></i> <span>销售调令管理</span> <span class="label label-important">3</span></a>
     <ul>
        <li><a href="saleCalloutOrder.jsp">录入销售调令</a></li>
        <li><a href="saleCalloutOrdersee.jsp">查看调令材料</a></li>
        <li><a href="saleCalloutOrderShenheModer.jsp">查看调令审核</a></li>
      </ul>
     </li>
     <li class="submenu"> <a href="#"><i class="icon icon-th"></i> <span>销售结算</span> <span class="label label-important">2</span></a>
     <ul>
<!--         <li><a href="outyardCostS.jsp">结算检尺费</a></li> -->
        <li><a href="treeoutPrice.jsp">木材销售结算单</a></li>
        <li><a href="treeoutPriceTable.jsp">木材销售结算台账</a></li>
      </ul>
     </li>
    <li><a href="treeoutCodepage.jsp"><i class="icon icon-fullscreen"></i> <span>打印销售码单</span></a></li>
    <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>库存与销售</span> <span class="label label-important">2</span></a>
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
    <div id="breadcrumb"> <a href="managerP.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
  </div>
<!--End-breadcrumbs-->
<main>
    <div class="find-top">
        <p class="p-tail"><i
         class="i-tail"></i> 该页面主要是销售部门输入销售合同</p>
    </div>
    <div class="find-top1">
    <form onSubmit="return inputNull(this)" action="salaryServlet?action=addcontract" method="POST">
    <div class="top" id="divprint">
    <table style="margin-left:auto; margin-right:auto">
    <caption class="book_h01">木材购销合同</caption>
    <tbody>
       <tr>
       <td>合同编号</td><td><input type="text" name="contractnum" id="contractnum" value="<%=s.getContractnum() %>"></td>
       <td>供方（甲方）</td><td><input type="text" name="provider" id="provider" value="<%=s.getProvider() %>"></td>
       <td>需方（乙方）</td><td><input type="text" name="demander" id="demander" value="<%=s.getDemander() %>"></td>
       </tr>
       <tr>
       <td>标品号</td><td><input type="text" name="marknumber" id="marknumber" value="<%=s.getMarknumber() %>"></td>
       <td>合同签订人</td><td><input type="text" name="contractsigner" id="contractsigner" value="<%=s.getContractsigner() %>"></td>
       <td>采伐证编号</td><td><input type="text" name="cutnum" id="cutnum" value="<%=s.getCutnum()%>"></td>   
       </tr>
       </tbody>
    </table>
    <p class="table_p"><span>树村信息</span></p>
       <table id="table5" style="width:100%;height:auto;margin-left:auto; margin-right:auto">
     <tbody id="ttt5">
      <% int i=1;%>
        <c:forEach items="${salecontractTree}" var="b">      
     <tr id="<%=i%>" display:block;><td style='font-size:20px;'>
                                 树材种<input style="width: 180px" name="treetype" type='text' id="sss<%=i%>" value="${b.getTreetype()}">                    
                                  长度(米)<input type='text' style='width: 180px' name='tlong' id='tl<%=i%>' value="${b.getTlong()}">
                                  口径(厘米)<span></span><input type='text' style='width: 180px' name='tradius' id='tr<%=i%>' value="${b.getTradius()}">
                                单价(元/立方米、吨)<span></span><input type='text' style='width: 180px' name='unitprice' id='up<%=i%>' value="${b.getUnitprice()}"></td></tr>
          <%i++; %>
           </c:forEach>
            </tbody>
            </table>
            <!--  <div style="border:1px solid #3383da;font-size:18px">
      <p style="font-style: italic;color: red;">费用负担说明：     &nbsp;&nbsp;1：装车费&nbsp;&nbsp;&nbsp;&nbsp; 2：检尺费&nbsp;&nbsp;&nbsp;&nbsp;3：其他费用&nbsp;&nbsp;&nbsp;&nbsp;4：木材装运过程中所发生的安全事故，其民事（经济赔偿或经济补偿等）、行政等法律责任</p>
      </div>-->
      <br>
      <p class="table_p"><span>合同详情</span></p>
      <table style="margin-left:auto; margin-right:auto">
      <tr>
      <td>交货地点</td><td><input style="width:400px" type="text" name="deliveryplace" id="deliveryplace" value="<%=s.getDeliveryplace() %>"></td>
      <td>供货分区:</td><td><input style="width:400px" type="text" name="section" id="section" value="<%=s.getSection() %>"></td>
      </tr>
      <tr>
      <td>合同签订数量</td><td><input style="width:400px" type="text" name="treenumber" id="treenumber" value="<%=s.getTreenumber() %>"></td>
      <td>检尺标准</td><td><input style="width:400px" type="text" name="measurestard" id="measurestard" value="<%=s.getMeasurestard() %>"></td>
      </tr>
      <tr>
      <td>费用负担（甲方）</td><td><input style="width:400px" type="text" name="cost" id="cost" value="<%=s.getCost() %>"></td>
      <td>费用负担（乙方）</td><td><input style="width:400px" type="text" name="costB" id="costB" value="<%=s.getCostB() %>"></td>
      </tr>
      <tr>
      <td>结算方式</td><td><input style="width:400px" type="text" name="settlemethod" id="settlemethod" value="<%=s.getSettlemethod() %>"></td>
      <td>合同开始时间</td><td><input style="width:400px" type="text" name="starttime" id="starttime" value="<%=s.getStarttime() %>"></td>
      </tr>
      <tr>
      <td>合同结束时间</td><td><input style="width:400px" type="text" name="endtime" id="endtime" value="<%=s.getEndtime() %>"></td>
      <td>保证金</td><td><input style="width:400px" type="text" name="margin" id="margin" value="<%=s.getMargin() %>"></td>  
      </tr>
      <tr>
      <td class="top-table-label">上传文件</td>
      <td>${contractfile}<a href="DownfileServlet?action=contractfile&filename=<%=s.getContractfile() %>">下载</a></td>
      </tr>
      </table>
    </div>
    <!--  <div class=" but_p" style="float:center;">
     <button style="width:150px;height:50px" class="but_save" type="button"  id="btnPrint" value="打印">打印</button></div>-->
    </form>

    </div>
    <div class="widget-box">
          <div class="widget-title"> <span class="icon"><i class="icon-ok"></i></span>
            <h5>合同进度</h5>
          </div>
          <div class="widget-content">
            <ul class="unstyled">
              <%-- <li> <span class="icon24 icomoon-icon-arrow-up-2 green"></span><%=c.getTotalStere()/c.getTreenumber()*100 %>%<span class="pull-right strong"><%=c.getTreenumber() %></span>
                <div class="progress progress-striped active">
                  <div style="width: <%=c.getTotalStere()/c.getTreenumber()*100 %>%;" class="bar"></div>
                </div>
              </li>--%>
               <li> <span class="icon24 icomoon-icon-arrow-up-2 green"></span>${treebaifenb}<span class="pull-right strong"><%=c.getTreenumber() %></span>
                <div class="progress progress-striped active">
                  <div style="width:${treebaifenb};" class="bar"></div>
                </div>
              </li>
            </ul>
          </div>
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