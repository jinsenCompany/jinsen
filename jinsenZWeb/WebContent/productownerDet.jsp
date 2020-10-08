<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>
 <%@ page import="jinshen.bean.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>生产业主管理平台导航</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="css/registe.css"/>
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/fullcalendar.css" />
<link rel="stylesheet" href="css/matrix-style.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="css/jquery.gritter.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="css/fselect.css" />

<style type="text/css">
.table1{width:70%; height:400px;margin-left:auto; margin-right:auto;padding:10px;border-collapse:collapse}
td,th{text-align:center;vertical-align:middle}
   tr{text-align:center}
    .table_p{line-height: 28px;border-bottom: 1px #d0e6ec solid;position: relative;margin-bottom: 10px;}
    
    .btn{ 	
	font-family: "'微软雅黑','Helvetica Neue',Helvetica,Arial,sans-serif"; 	
	font-size: 22px!important; 	height: 40px; 	
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
</style>
</head>
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
function prodcutAdd()
{
	var creatdate=$("#creatdate").val();
	var customernum=$("#customernum").val();
	var kname=$("#kname").val();
	var company=$("#company").val();
	var address=$("#address").val();
	var IDCard=$("#IDCard").val();
	var customertype=$("#customertype").val();
	var b=customertype.join(",");
	//alert(b)
	var contractnum=$("#contractnum").val();
	var treenumber=$("#treenumber").val();
	var totaltree=$("#totaltree").val();
	var shipaddress=$("#shipaddress").val();
	var breakcontract=$("#breakcontract").val();
	if(contractnum=="")
		{
		alert("请先输入合同编号！");
		}
	else{
    $.ajax({
        url:"salaryServlet",
        data:{
            "action":"addcustomer",
            "creatdate":creatdate,
            "customernum":customernum,
            "kname":kname,
            "company":company,
            "address":address,
            "IDCard":IDCard,
            "customertype":b,
            "contractnum":contractnum,
            "treenumber":treenumber,
            "totaltree":totaltree,
            "shipaddress":shipaddress,
            "breakcontract":breakcontract
        },
        type: "POST",
        dataType:"html",
        success: function (data) {
        	//alert(data);
        	if(data>0)
        		{
        	        alert("保存成功！");
        	        window.location.href ='productowner.jsp';
        	        //setTimeout("window.location.href ='productowner.jsp';", 3000);
        		}
        	else{
        		 alert("保存失败");
        	}
        }
    })
    }
}
</script>

<body>
<% customer s=(customer)request.getAttribute("cutnumDet");%>
<% customer t=(customer)request.getAttribute("treenum");%>
<% Date d = new Date();

SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

String now = df.format(d); %>
<!--Header-part-->
<div id="header">
  <h1><a href="dashboard.html">生产业主管理平台导航</a></h1>
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
     <li><%
	String staff_id = request.getSession().getAttribute("staff_id").toString();
				%><%
 	String staff_name = request.getSession().getAttribute("staff_name").toString();
 %> 您好，<%=staff_id%> <%=staff_name%>欢迎登录
 </li>
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
   <!--  <ul>
    <li class="submenu"> <a href="#"><i class="icon icon-home"></i> <span>合同管理</span> <span class="label label-important">1</span></a>
     <ul>
        <li><a href="salaryContract.jsp">创建合同</a></li>
      </ul>
     </li>
    <li class="submenu"> <a href="#"><i class="icon icon-home"></i> <span>客户信息管理</span> <span class="label label-important">2</span></a>
     <ul>
        <li><a href="productowner.jsp">创建客户信息</a></li>
        <li><a href="productownerSee.jsp">查看客户信息</a></li>
      </ul>
     </li>
  </ul>-->
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

<!--main-container-part-->
<div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"> <a href="salaryper.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> 首页</a></div>
  </div>
<!--End-breadcrumbs-->
   <div  id="divprint">
   <form action="salaryServlet?action=addcustomer" method="POST" >
   <div class="top">
   <p class="p-tail"><i class="i-tail"></i> 创建客户信息页面</p>
   <div id="divprint" >
   <table class="table1" border="1">
    <caption class="book_h01">客户资料卡</caption>
    <tr>
    <th>序号</th>
    <th>内容</th>
    <th>信息</th>
    </tr>
    <tr>
    <td>1</td>
    <td >建卡日期</td>
    <td><input style="border:0px;background-color: transparent;width: 100%;" type="text" name="creatdate" id="creatdate" readonly="readonly" value="<%=s.getCreatdate() %>"></td>
    </tr>
    <tr>
    <td>2</td>
    <td >客户/公司名称</td>
    <td><input style="border:0px;background-color: transparent;width: 100%;" type="text" name="customernum" id="customernum" value="<%=s.getCustomernum() %>"></td>
    </tr>
    <tr>
    <td>3</td>
    <td>客户姓名</td>
    <td><input style="border:0px;background-color: transparent;width: 100%;" type="text" name="kname" id="kname" value="<%=s.getKname() %>"></td>
    </tr>
    <tr>
    <td>4</td>
    <td>组织机构代码</td>
    <td><input style="border:0px;background-color: transparent;width: 100%;" type="text" name="company" id="company" value="<%=s.getCompany() %>"></td>
    </tr>
    <tr>
    <td>5</td>
    <td>地址</td>
    <td><input style="border:0px;background-color: transparent;width: 100%;" type="text" name="address" id="address" value="<%=s.getAddress() %>"></td>
    </tr>
    <tr>
    <td>6</td>
    <td>身份证号码</td>
    <td><input style="border:0px;background-color: transparent;width: 100%;" type="text" name="IDCard" id="IDCard" value="<%=s.getIDCard() %>"></td>
    </tr>
    <tr>
    <td>7</td>
    <td>联系电话</td>
    <td><input style="border:0px;background-color: transparent;width: 100%;" type="text" name="telephone" id="telephone" value="<%=s.getTelephone() %>"></td>
    </tr>
    <tr>
    <td>8</td>
    <td>客户类型</td>
    <td><input style="border:0px;background-color: transparent;width: 100%;" name="customertype" id="customertype" type="text" value="<%=s.getCustomertype() %>"></td>
    </tr>
    <tr>
    <td>9</td>
    <td>签定合同号</td>
    <td><textarea  style="border:0px;background-color: transparent;width: 100%;" rows="1" cols="1" name="contractnum" id="contractnum"><%=t.getContractnum() %></textarea>
    </td>
    </tr>
    <tr>
    <td>10</td>
    <td>签订合同木材数量</td>
    <td><input style="border:0px;background-color: transparent;width: 100%;" type="text" name="treenumber" id="treenumber" value="<%=t.getTreenumber() %>"></td>
    </tr>
    <tr>
    <td>11</td>
    <td>调运数量累计</td>
    <td><input style="border:0px;background-color: transparent;width: 100%;" type="text" name="totaltree" id="totaltree" value="<%=t.getTotaltree() %>"></td>
    </tr>
    <tr>
    <td>12</td>
    <td>收货地址</td>
    <td><input style="border:0px;background-color: transparent;width: 100%;" type="text" name="shipaddress" id="shipaddress" value="<%=s.getShipaddress() %>"></td>
    </tr>
    <tr>
    <td>13</td>
    <td>违约历史记录</td>
    <td><input style="border:0px;background-color: transparent;width: 100%;" type="text" name="breakcontract" id="breakcontract" value="<%=s.getBreakcontract() %>"></td>
    </tr>
    <!--  <tr>
       <th>客户姓名</th>
       <th>销售合同编号</th>
       <th>公司名称</th>
       <th>地址</th>
       </tr>
       <tr>
      <td><input type="text" name="kname" id="kname" placeholder="客户姓名"></td>
      <td><input type="text" name="contractnum" id="contractnum" placeholder="销售合同编号"></td>
      <td><input type="text" name="company" id="company" placeholder="公司"></td>
      <td><input type="text" name="address" id="address" placeholder="地址"></td>
      </tr>
      <tr>
      <th colspan="">身份证号</th>
      <th colspan="">联系电话</th>
      <th colspan="2">发货地址</th>
       </tr>
       <tr style="font-size:20px">
       <td colspan=""><input type="text" name="IDCard" id="IDCard"></td>
       <td colspan=""><input type="text" name="telephone" id="telephone"></td>
       <td colspan="2"><input type="text" name="shipaddress" id="shipaddress"></td>
       </tr>-->
   </table>
   </div>
   </div>
  <div class="1" style="text-align:center;margin-top:20px;">
	<!-- <span  style="text-align: center;"><button class="btn" type="submit" id="mybutton" value="保存资料卡">保存资料卡</button></span> -->
	<button class="btn" type="button" id="btnPrint" value="打印">打印</button>
		 </div>
        
   </form>
</div>


</div>
<!--end-Footer-part-->
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
<script src="js/fselect.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
$(function(){
    $("#btnPrint").click(function(){ $("#divprint").printArea(); });
});
</script>
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
</script>

</body>
</html>
