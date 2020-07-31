<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<html>
<head>
<meta charset="UTF-8">
<title>货场库存盘点</title>
<!--  <link rel="stylesheet" href="css/all.css">-->
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
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/fullcalendar.css" />
<link rel="stylesheet" href="css/matrix-style.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="css/jquery.gritter.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
<link type="text/css" rel="stylesheet" href="css/PrintArea.css" />
    <style>
    body,td,th {font-family: Verdana, Arial, Helvetica, sans-serif;font-size: 18px;color: #1d1007; line-height:24px}
    </style>
    <link href="css/registe.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        .table_p{line-height: 28px;border-bottom: 1px #d0e6ec solid;position: relative;margin-bottom: 10px; 
            margin-top: 35px; margin-left:10px}
        .table_p span{border-bottom: 3px #42cdec solid;display: inline-block;position: absolute;bottom: -1px;font-weight: bold;font-size: 20px}
        .but_p button{width: 100px;height:40px}
        #h li{float: left; }
#h a{font-size:15px;width: 230px; height: 30px;padding: 10px 0;text-align: center;  background: #3c763d; display: block; text-decoration:none; color:white}
#h a:hover{color:white;background: green}
.table1{margin-left:auto; margin-right:auto;padding:10px;border-collapse:collapse}
.table1 tr,td,th{height:50px;font-size:22px;text-align: center;}
.table1 input{height:50px;font-size:22px;text-align: center;width:100%}
    </style>
</head>
<body>
<script type="text/javascript">
</script>
</head>
<body>
<%List<yardInventory> i=null;i=(List<yardInventory>)request.getAttribute("inyardT"); %>
<%List<yardInventory> o=null;o=(List<yardInventory>)request.getAttribute("outyardT");%>
<%yardInventory y=(yardInventory)request.getAttribute("yardyear"); %>>
<!--Header-part-->
  <div id="header">
  <h1><a href="dashboard.html">伐区监管平台</a></h1>
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
    <li><a href="yardInventoryBootstrap.jsp"><i class="icon icon-home"></i> <span>货场盘点</span></a></li>
  </ul>
</div>
<!--sidebar-menu-->
<!--main-container-part-->
<div id="content">
  <div id="content-header">
    <div id="breadcrumb"> <a href="Surveyor.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> 检尺员首页</a></div>
</div>
  <article>
            <div id="divprint">
            <div>
            <p class="table_p"><span><%=y.getYeart() %>年货场盘点表</span></p>
            <table class="table1" border="1">
              <caption class="book_h01"><%=y.getYeart() %>年货场盘点</caption>
            <tr>
               <td>货场</td>
               <td>
               <table border="1">
                 <tr>
                  <th width="180px">&nbsp;月进仓&nbsp;</th>
                  <th>材积(m^3、T、根)</th>
                  </tr>
                </table>
               </td>
               <td>
                 <table border="1"> 
                   <tr>
                     <th width="180px">月出仓</th>
                     <th>材积(m^3、T、根)</th>
                  </tr>
                  </table>
               </td>
               <td>备注</td>
            </tr>
            <tr>
               <td><input type="text" name="yardname" id="yardname" value="<%=y.getYardname() %>" /></td>
             <td>
               <table>
               <% int j=1;%>
               <c:forEach items="${inyardT}" var="b">      
               <tr id="<%=j%>" display:block;><td style='font-size:20px;'>
               <td><input type='text' style='width: 180px' name='montht' id='mt<%=j%>' value="${b.getMontht()}"></td>
               <td><input type='text' style='width: 180px' name='involume' id='iv<%=j%>' value="${b.getInvolume()}"></td>
               </tr>
               <%j++; %>
             </c:forEach>
             </table>
               </td>
               <td>
               <table>
               <% int h=1;%>
               <c:forEach items="${outyardT}" var="b">      
               <tr id="<%=h%>" display:block;><td style='font-size:20px;'>
               <td><input type='text' style='width: 180px' name='montht' id='mt<%=h%>' value="${b.getMontht()}"></td>
               <td><input type='text' style='width: 180px' name='involume' id='iv<%=h%>' value="${b.getInvolume()}"></td>
               </tr>
                <%h++; %>
                </c:forEach>
                </table>
              </td>
              <td><input id="" value=""></td>
              </tr>
              <tr>
              <td>库存总量</td><td colspan="5"><input id="" name=""></td>
              </tr>
              </table>
              </div>
            </div>
             <div class=" but_p" style="float:center;">
                  <button class="btn btn-default" type="button" id="btnPrint" value="打印">打印</button>
              </div>
        <%--  <div>
        <h1 class="book_h01">录入货场盘点表</h1>
         <!--  <form  onSubmit="return inputNull(this)" action="treeServlet?action=findhcpd" method="POST" >-->
         <form>
         <div id="divprint">
         <div class="top">
            <p class="table_p"><span>货场盘点表</span></p>
            <%--  <table>
               <tbody>
               <tr>
               <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
               <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;货&nbsp;&nbsp;场&nbsp;&nbsp;&nbsp;</th>
               <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;份&nbsp;&nbsp;&nbsp;</th>
               <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;份&nbsp;&nbsp;</th>
               <th>材积(m^3、T、根)</th>
               </tr>
               <% int j=1;%>
               <c:forEach items="${inyardT}" var="b">      
               <tr id="<%=j%>" display:block;><td style='font-size:20px;'>
               <td><input type='text' style='width: 180px' name='yardname' id='yn<%=j%>' value="${b.getYardname()}"></td>
               <td><input type='text' style='width: 180px' name='yeart' id='yt<%=j%>' value="${b.getYeart()}"></td>
               <td><input type='text' style='width: 180px' name='montht' id='mt<%=j%>' value="${b.getMontht()}"></td>
               <td><input type='text' style='width: 180px' name='involume' id='iv<%=j%>' value="${b.getInvolume()}"></td>
               </tr>
                <%j++; 
                     </c:forEach>
                      </tbody>
              </table>
              <table class="table" id="table5" style="width:1500px;height:auto">
               <tbody id="ttt5">
               <% int h=1;%>
               <c:forEach items="${outyardT}" var="b">      
               <tr id="<%=j%>" display:block;><td style='font-size:20px;'>
                                        货场<input type='text' style='width: 180px' name='yardname' id='yn<%=h%>' value="${b.getYardname()}">
                                        年份<input type='text' style='width: 180px' name='yeart' id='yt<%=h%>' value="${b.getYeart()}">
                                        月份<input type='text' style='width: 180px' name='montht' id='mt<%=h%>' value="${b.getMontht()}">
                                        材积(m^3、T、根)<span></span><input type='text' style='width: 180px' name='involume' id='iv<%=h%>' value="${b.getInvolume()}"></td></tr>
                <%h++; %>
                     </c:forEach>
                      </tbody>
              </table>
	        <table border="" cellspacing="" cellpadding="">
	        <caption class="book_h01"><%=y.getYeart() %>年货场盘点</caption>
	        <tbody>
			<tr>
			<td>货场</td>
			<td>月进仓</td>
			<td>材积m<sup>3</sup>/T</td>
			<td>库存总量</td>
			<td>备注</td>
			</tr>
			<tr>
			<td rowspan="12"><input type="text" name="yardname" id="yardname" value="<%=y.getYardname() %>" /></td>
			<td>1月</td><td><input type="text" id="tvolume" value="" /></td>
			<td><input type="text" id="totalnum" value="" /></td>
			<td><input type="text" id="" value="" /></td>
			</tr>
			<tr>
			<!-- <td></td> -->
			<td>2月</td><td><input type="text" name="tvolume" id="tvolume" value="" /></td>
			<td><input type="text" id="" value="" /></td>
			<td><input type="text" id="" value="" /></td>
			</tr>
			<tr><!-- <td></td> --><td>3月</td><td><input type="text" name="tvolume" id="" value="" /></td><td><input type="text" id="" value="" /></td><td><input type="text" id="" value="" /></td></tr>
			<tr><!-- <td></td> --><td>4月</td><td><input type="text" name="" id="" value="" /></td><td><input type="text" id="" value="" /></td><td><input type="text" id="" value="" /></td></tr>
			<tr><!-- <td></td> --><td>5月</td><td><input type="text" name="" id="" value="" /></td><td><input type="text" id="" value="" /></td><td><input type="text" id="" value="" /></td></tr>
			<tr><!-- <td></td> --><td>6月</td><td><input type="text" name="" id="" value="" /></td><td><input type="text" id="" value="" /></td><td><input type="text" id="" value="" /></td></tr>
			<tr><!-- <td></td> --><td>7月</td><td><input type="text" name="" id="" value="" /></td><td><input type="text" id="" value="" /></td><td><input type="text" id="" value="" /></td></tr>
			<tr><!-- <td></td> --><td>8月</td><td><input type="text" name="" id="" value="" /></td><td><input type="text" id="" value="" /></td><td><input type="text" id="" value="" /></td></tr>
			<tr><!-- <td></td> --><td>9月</td><td><input type="text" name="" id="" value="" /></td><td><input type="text" id="" value="" /></td><td><input type="text" id="" value="" /></td></tr>
		    <tr><!-- <td></td> --><td>10月</td><td><input type="text" name="" id="" value="" /></td><td><input type="text" id="" value="" /></td><td><input type="text" id="" value="" /></td></tr>
			<tr><!-- <td></td> --><td>11月</td><td><input type="text" name="" id="" value="" /></td><td><input type="text" id="" value="" /></td><td><input type="text" id="" value="" /></td></tr>
			<tr><!-- <td></td> --><td>12月</td><td><input type="text" name="" id="" value="" /></td><td><input type="text" id="" value="" /></td><td><input type="text" id="" value="" /></td></tr>
			<tr><td>总库存量</td><td><input type="text" name="" id="" value="" /></td></tr>
			</tbody>
		</table>				
           </div>
                    </div>
                  <div class=" but_p" style="float:right;">
                  <button class="btn btn-default" type="button"  onclick="treeAdd()" value="提交">提交</button>
                  <!--  <button class="but_save" type="submit" id="mybutton" value="保存工单">保存工单</button>--></div>
                 <input style="float:right;" type="button" id="btnPrint" value="打印"/>   
                <div style="clear: both;padding-bottom: 40px"></div>
            </form>
        </div>--%>
        
    </article>
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
  // This function is called from the pop-up menus to transfer to
  // a different page. Ignore if the value returned is a null string:
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

// resets the menu selection upon entry to this page:
function resetMenu() {
   document.gomenu.selector.selectedIndex = 2;
}
  
$(function(){
    $("#btnPrint").click(function(){ $("#divprint").printArea(); });
});
</script> 
</body>
</html>