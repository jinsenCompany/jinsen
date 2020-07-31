<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>货场制表员C</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/fullcalendar.css" />
<link rel="stylesheet" href="css/matrix-style.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="css/jquery.gritter.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
</head>
<style type="text/css">
			#gj{
				width: 60px;
			}
		</style>
<body>

<!--Header-part-->
<div id="header">
  <h1><a href="dashboard.html">木材产销管理平台导航</a></h1>
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
    <li><a href="yardMonery.jsp"><i class="icon icon-home"></i> <span>费用统计</span></a> </li>
  </ul>
</div>
<!--sidebar-menu-->

<!--main-container-part-->
<div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"> <a href="yardstaffC.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a></div>
  </div>
<!--End-breadcrumbs-->

<h4 align="center">货场费用统计表</h4>
		<table border="" cellspacing="" cellpadding="" style="margin-left:auto; margin-right:auto;">			
			<tr><td colspan="15">货场</td><!--<td></td><!--<td></td><!--<td></td><!--<td></td><!--<td></td><!--<td></td><!--<td></td><!--<td></td><!--<td></td>--><!--<td></td>--><!--<td></td>--><!--<td></td>--><!--<td></td>--><!--<td></td>--></tr>
			<tr><td>序号</td><td>项目</td><td>1月</td><td>2月</td><td>3月</td><td>4月</td><td>5月</td><td>6月</td><td>7月</td><td>8月</td><td>9月</td><td>10月</td><td>11月</td><td>12月</td><td>合计</td></tr>
			<tr><td>1</td><td>钩机整堆</td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td></tr>
			<tr><td>2</td><td>人工整堆</td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td></tr>
			<tr><td>3</td><td>装车工资</td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td></tr>
			<tr><td>4</td><td>场地维护</td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td></tr>
			<tr><td>5</td><td>场地资金</td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td></tr>
			<tr><td>6</td><td>看护工资</td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td></tr>
			<tr><td>7</td><td>电费</td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td></tr>
			<tr><td>8</td><td>设备维护</td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td></tr>
			<tr><td>9</td><td>运费</td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td></tr>
			<tr><td>10</td><td>检尺费</td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td><td><input type="text" name="gj" id="gj" value="" /></td></tr>
			<tr><td colspan="2">小计</td><!--<td >小计</td>--><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
		</table>
</div>

<!--end-main-container-part-->

<!--Footer-part-->

<!--end-Footer-part-->

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