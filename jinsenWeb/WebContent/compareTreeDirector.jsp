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
    <title>货场管理核实装车情况</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/tableall.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/fullcalendar.css" />
<link rel="stylesheet" href="css/matrix-style.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="css/jquery.gritter.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
    <script src="js/jquery-1.11.1.min.js"></script>
    <script src="js/modernizr.custom.js"></script>

<style>
.file-box {
    position: relative;
    display: inline-block;
}
.file-box img {
    width: 50px;
    height: 50px;
    border-radius: 50%;
    position: absolute;
    top: -30px;
    left: 0;
    display: inline-block;
    border: none;
}
.file-box .txt,.file-box .file {
    width: 70px;
    height: 36px;
    position: absolute;
    top: -20px;
    left: 100px;
    text-align: center;
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
<style>
    body,td,th {font-family: Verdana, Arial, Helvetica, sans-serif;font-size: 18px;color: #1d1007; line-height:24px}
    </style>
    <link href="css/registe.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        .table_p{line-height: 28px;border-bottom: 1px #d0e6ec solid;position: relative;margin-bottom: 10px; 
            margin-top: 35px; margin-left:10px}
        .table_p span{border-bottom: 3px #42cdec solid;display: inline-block;position: absolute;bottom: -1px;font-weight: bold;font-size: 20px}
        .but_p button{width: 200px;height:50px}
        #h li{float: left; }
#h a{font-size:15px;width: 230px; height: 30px;padding: 10px 0;text-align: center;  background: #3c763d; display: block; text-decoration:none; color:white}
#h a:hover{color:white;background: green}
.table1{margin-left:auto; margin-right:auto;padding:10px;border-collapse:collapse}
.table2{width:70%; height:100px;margin-left:auto; margin-right:auto;padding:10px;border-collapse:collapse}
</style>
<script type="text/javascript">
function treecompare(){
	var workid=$("#wworkid").val();
	var pic=document.getElementById("pic").value;
	var da=document.getElementById("da").value;
	//var designum=$("designum").val();
	//alert(workid);
	$.ajax({
		url:"pictureServlet",
        //url:"cutnumServlet",
        data:{
        	"action":"treecompare",
            "workid":workid,
            "pic":pic,
            "da":da,
        },
        type: "POST",
        dataType:"html",
        success: function (data) {
        	alert(data);
        }
    })
}
</script>
</head>
<body>
<% inyard c=(inyard)request.getAttribute("inyard"); 
List<tree> t=null;
t=(List<tree>)request.getAttribute("tree");
%>
<% workpageStatus w=(workpageStatus)request.getAttribute("workpageStatus");%>
<% workpage o=(workpage)request.getAttribute("workpage");%>
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
      <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>审核</span> <span class="label label-important">2</span></a>
       <ul>
        <li><a href="yardMoneryDirector.jsp">费用结算</a></li>
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
    <li><a href="yardinfo.jsp"><i class="icon icon-home"></i> <span>新增货场信息</span></a> </li>
   <li><a href="cancellingStocksTable.jsp"><i class="icon icon-home"></i> <span>货场报损</span></a> </li>
  </ul>
</div>
<!--sidebar-menu-->

<div id="content">
<div id="content-header">
    <div id="breadcrumb"> <a href="yardmanager.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
  </div>
<!--End-breadcrumbs-->

<main>
    <article class="artlce">
    <div class="find-top">
        <p class="p-tail"><i
         class="i-tail"></i> 该页面主要是货场管理员上传装车情况</p>
    </div>
    <!--  <form  id="myForm" action="pictureServlet?action=treecompare" method="post">-->
    <form action="">
    <div class="top">
    <p class="table_p"><span>采伐工单信息</span></p>
      
                       <table class="table1">
                         <tr>
                         <td>工单号<span></span></td>
                          <td>
                          <input type="text" name="workid" id="wworkid" value="<%=o.getWorkid() %>">
                          </td>
                          <td>采伐证号<span></span></td>
                          <td>
                          <input type="text" name="cutnum" id="ccutnum" value="<%=o.getCutNum() %>">
                           </td>
                           <td>采伐地点<span></span></td>
                          <td>
                          <input type="text" name="cutsite" id="ccutsite" value="<%=o.getCutSite() %>">
                           </td>
                           <td>货场地点<span></span></td>
                              <td>
                              <input type="text" name="yard" id="yyard" value="<%=o.getCheckSite() %>">
                              </td>
                           </tr>
                           <tr>
                              <td>车牌号<span></span></td>
                              <td>
                              <input type="text" name="carnumber" id="ccarnumber" value="<%=o.getCarNumber() %>">
                              </td>
                              <td>采伐时间<span></span></td>
                              <td>
                              <input  name="cutdate" id="cutdate" value="<%=o.getCutdate() %>">
                              </td>
                              <td>进场时间<span></span></td>
                              <td>
                              <input  name="yarddate" id="yyarddate" value="<%=c.getYarddate() %>">
                              </td>
                              <td>伐区监管员<span></span></td>
                              <td>
                              <input type="text" name="forester" id="forester" value="<%=o.getForester() %>">
                              </td>
                           </tr>
                        </table>
                        <p class="table_p"><span>树材信息</span></p>
                        <table class="table" id="table5" style="width:1500px;height:auto">
                           <tbody id="ttt5">
                                <% int i=1;%>
                                <c:forEach items="${tree}" var="b">      
                            <tr id="<%=i%>" display:block;><td style='font-size:20px;'>
                           <input type="checkbox" style='width:20px;height:20px;' value="<%=i%>">树材种<span></span>
                           <select style="width: 180px" name="treetype" id="sss<%=i%>">
                           <option value='杉原木' <c:if test="${b.getTreetype()eq'杉原木'}">selected='selected'</c:if> >--杉原木--</option>
                           <option value='杉小径' <c:if test="${b.getTreetype()eq'杉小径'}">selected='selected'</c:if> >--杉小径--</option>
                           <option value='松原木' <c:if test="${b.getTreetype()eq'松原木'}">selected='selected'</c:if> >--松原木--</option>
                           <option value='松小径' <c:if test="${b.getTreetype()eq'松小径'}">selected='selected'</c:if> >--松小径--</option>
                           <option value='杂原木' <c:if test="${b.getTreetype()eq'杂原木'}">selected='selected'</c:if> >--杂原木--</option>
                           <option value='杂小径' <c:if test="${b.getTreetype()eq'杂小径'}">selected='selected'</c:if> >--杂小径--</option>
                            </select>                     
                                                                                            检尺长(m)<input type='text' style='width: 180px' name='tdouble' id='td<%=i%>' value="${b.getTlong()}">
                                                                                            检尺径<span></span><input type='text' style='width: 180px' name='tradius' id='tr<%=i%>' value="${b.getTradius()}">
                                                                                            根数<span></span><input type='text' style='width: 180px' name='num' id='n<%=i%>' value="${b.getNum()}">
                                                                                            材积(m^3、T、根)<span></span><input type='text' style='width: 180px' name='tvolume' id='tv<%=i%>' value="${b.getTvolume()}"></td></tr>
                         <%i++; %>
                         </c:forEach>
                                </tbody>
                            </table>   
                            <table class="table1" >
                                <tbody>
                                <tr>
                                    <td style="font-size:20px;width:auto">合计树材<span></span>
                                        <input type="text" style="width:120px; font-size:20px" name="toltree" id="toltree" value="<%=c.getTolTree()%>" onclick="makecount()">根(块、件)<span></span></td>
                                    <td style="font-size:20px">合计树材<span></span>
                                        <input type="text" style="width:120px; font-size:20px" name="tolstere" id="tolstere" value="<%=c.getTolStere()%>">立方米(吨、根)<span></span></td>
                                         <td style="font-size:20px">货场分区<span></span>
                                       <input name="section" id="ssection" value="<%=c.getSection()%>"></td>
                                     <td style="font-size:20px">检尺员<span></span>
                                      <input type="text" name="surveyor" id="ssurveyor" value="<%=c.getSurveyor() %>"></td>
                                </tr>
                                </tbody>
                            </table>
                <p class="table_p"><span>上传装车照片</span></p>
          <table class="top-table">
        <tr><td class="top-table-label">上传照片</td><td colspan="5"><input class="filepath" type="file" id="pic" name="pic"></td><td><button type="button" class="btn" style="float:right" data-toggle="modal" data-target="#myModal_1" onclick="a()">查看图片</button></td></tr>
        <tr><td class="top-table-label">上传文件</td><td colspan="5"><input type="file" name="da" id="da"></td></tr>
         <!--  <tr><td><input type="submit" name="submit" class="btn" value="保存"></td></tr>-->
    </table>
    </div>
    <div class="but_p" style="float:center;"><button class="but_save" type="button" onclick="treecompare()" value="保存">保存</button></div>
    </form>
<!-- 显示最近添加的员工 -->
    <div class="table-con">
        <table id="table1" class="table-style"></table>
    </div>
    </article>
</main>
</div>
<div class="modal fade" id="myModal_1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h4 class="modal-title" id="myModalLabel">
								图片查看
							</h4>
						</div>
						<div class="modal-body">
							<div>
								<img alt="" src="" id="image" name="image" style="width:500px;height:600px">
							</div>
						</div>
					</div>
				</div>
</div>
<script type="text/javascript">
function a(){
	$("#image").attr("src",$("#pic").val());
}
</script>
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
</body>
</html>