<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jinshen.bean.*" %>

<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>更新工单</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
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
<link type="text/css" rel="stylesheet" href="css/PrintArea.css" />
<script src="js/jquery.js" type="text/javascript"></script>
<style type="text/css">
 .table_p{line-height: 28px;border-bottom: 1px #d0e6ec solid;position: relative;margin-bottom: 10px; 
            margin-top: 35px; margin-left:10px}
        .table_p span{border-bottom: 3px #42cdec solid;display: inline-block;position: absolute;bottom: -1px;font-weight: bold;font-size: 20px}
        .but_p button{width: 80px}
        #h li{float: left; }
        #h a{font-size:15px;width: 230px; height: 30px;padding: 10px 0;text-align: center;  background: #3c763d; display: block; text-decoration:none; color:white}
         #h a:hover{color:white;background: green}
                  input{margin-top:5px;margin-bottom:-2px;}
         
          .table1{width:80%; height:400px;margin-left:auto; margin-right:auto;padding:10px;border-collapse:collapse}
         td,th{text-align:center;vertical-align:middle}
         tr{text-align:center}
</style>
    
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

</head>
<body
<% workpage c=(workpage)request.getAttribute("workpage");%>>
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
  </ul>
</div>
<!--sidebar-menu-->

<!--main-container-part-->
<div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"> <a href="hello2.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
  </div>
<main class="all">
    <article class="artlce">

        <div class="book_con01">
            <form  onSubmit="return inputNull(this)" action="workpageSevrlet?action=updateWorkpage" method="POST" >
                <div class="top" id="divprint">
                    <div>
                        <div class="top_out">
                           <table class="table1" border="1">
                           <caption class="book_h01">更新工单</caption>
                           <tr>
                           <th colspan="2">工单号</th>
                           <th colspan="2">采伐证号</th>
                          <th colspan="2">时间</th>
                           </tr>
                           <tr>
                            <td colspan="2">
                              <input style="width:50%;border:0px;background-color: transparent" type="text" name="workid" id="wworkid" value="<%=c.getWorkid() %>" placeholder="工单号">
                           </td>
                           <td colspan="2">
                             <input style="width:50%;border:0px;background-color: transparent" type="text" name="cutnum" id="cccutnum" value="<%=c.getCutNum() %>" placeholder="采伐证号">
                           </td>
                           <td colspan="2">
                              <input  type="date" name="cutdate" value="<%=c.getCutdate() %>"  id="ccutdate" placeholder="采伐时间">
                           </td>
                           </tr>
                           <tr>
                            <th colspan="2">采伐地点</th>
                            <th colspan="2">检验地点</th>
                            <th colspan="2">车牌号</th>                          
                           </tr>
                           <tr>
                           <td colspan="2">
                              <input style="width:50%;border:0px;background-color: transparent" type="text" name="cutsite" id="ccutsite" value="<%=c.getCutSite() %>" placeholder="采伐地点">
                           </td>
                           <td colspan="2">
                              <input style="width:50%;border:0px;background-color: transparent" type="text" name="checksite" id="ccchecksite" value="<%=c.getCheckSite() %>" placeholder="检验地点">
                           </td>
                           <td colspan="2">
                              <input style="width:50%;border:0px;background-color: transparent" type="text" name="carnumber" id="ccarnumber" value="<%=c.getCarNumber() %>" placeholder="车牌号">
                           </td>
                           </tr>
                           
                           
                              <tr>
                                 <th scope="col">序号</th>
                                 <th scope="col">树  材  种</th>
                                  <th scope="col">检  尺  长</th>
                                  <th scope="col">检  尺  径</th>
                                  <th scope="col">根  数</th>
                                  <th scope="col">材  积</th>
                              </tr>
                              <tr>
                                 <td>1</td>
                                 <td>&nbsp; </td>
                                 <td> &nbsp;</td>
                                 <td> &nbsp;</td>
                                 <td>&nbsp; </td>
                                 <td>&nbsp;</td>
                              </tr>
                              <tr>
                                 <td>2</td>
                                 <td>&nbsp; </td>
                                 <td>&nbsp;</td>
                                 <td>&nbsp;</td>
                                 <td>&nbsp; </td>
                                 <td>&nbsp;</td>
                              </tr>
                                <tr>
                                <td>3</td>
                                 <td>&nbsp; </td>
                                 <td>&nbsp;</td>
                                 <td>&nbsp;</td>
                                 <td>&nbsp; </td>
                                 <td>&nbsp;</td>
                              </tr>
                                <tr>
                                 <td>5</td>
                                 <td>&nbsp; </td>
                                 <td>&nbsp;</td>
                                 <td>&nbsp;</td>
                                 <td>&nbsp; </td>
                                 <td>&nbsp;</td>
                              </tr>
                                 <tr>
                                 <td>6</td>
                                 <td>&nbsp; </td>
                                 <td>&nbsp;</td>
                                 <td>&nbsp;</td>
                                 <td>&nbsp; </td>
                                 <td>&nbsp;</td>
                             <tr>
                                 <td>7</td>
                                 <td>&nbsp; </td>
                                 <td>&nbsp;</td>
                                 <td>&nbsp;</td>
                                 <td>&nbsp; </td>
                                 <td>&nbsp;</td>
                              </tr>
                               <tr>
                                 <td>8</td>
                                 <td>&nbsp; </td>
                                 <td>&nbsp;</td>
                                 <td>&nbsp;</td>
                                 <td>&nbsp; </td>
                                 <td>&nbsp;</td>
                              </tr>
                               <tr>
                                 <td>9</td>
                                 <td>&nbsp; </td>
                                 <td>&nbsp;</td>
                                 <td>&nbsp;</td>
                                 <td>&nbsp; </td>
                                 <td>&nbsp;</td>
                              </tr>
                               <tr>
                                 <td>10</td>
                                 <td>&nbsp; </td>
                                 <td>&nbsp;</td>
                                 <td>&nbsp;</td>
                                 <td>&nbsp; </td>
                                 <td>&nbsp;</td>
                              </tr>
                               <tr>
                                 <td>总计</td>
                                 <td>&nbsp; </td>
                                 <td>&nbsp;</td>
                                 <td>&nbsp;</td>
                                 <td>&nbsp; </td>
                                 <td>&nbsp;</td>
                              </tr>
                             <tr>
                                <td colspan="2">货场管理员：</td>
                                <td colspan="2">检尺员：</td>
                                <td colspan="2">货场监管员：</td>
                             </tr>
                             <tr>
                                <td colspan="2"></td>
                                <td colspan="2"></td>
                                <td colspan="2"><input style="width:50%;border:0px;background-color: transparent" type="text" name="forester" id="fforester" value="<%=c.getForester() %>" placeholder="伐区监管员"></td>
                             </tr>
                            </table>
                        </div>
                    </div>
                </div>
                 <div style=text-align:center; class=" but_p" ><button class="but_save" type="submit" id="mybutton" value="更新工单">更新工单</button>
                <button class="but_save" type="button" value="返回" onclick="javascrtpt:window.location.href='workpageAdd.jsp'">返回添加</button>
                
                 <button type="button" id="btnPrint" value="打印">打印</button>
                <span style="clear: both;padding-bottom: 40px"></span></div>
            </form>
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