<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jinshen.bean.*" %>
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>输入进场木材数据</title>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %> 
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
    body,td,th {font-family: Verdana, Arial, Helvetica, sans-serif;font-size: 20px;color: #1d1007; line-height:24px}
    </style>
    <link href="css/registe.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        .table_p{line-height: 28px;border-bottom: 1px #d0e6ec solid;position: relative;margin-bottom: 10px; 
            margin-top: 35px; margin-left:10px}
        .table_p span{border-bottom: 3px #42cdec solid;display: inline-block;position: absolute;bottom: -1px;font-weight: bold;font-size: 20px}
        .but_p button{width: 80px}
        #h li{float: left; }
#h a{font-size:15px;width: 230px; height: 30px;padding: 10px 0;text-align: center;  background: #3c763d; display: block; text-decoration:none; color:white}
#h a:hover{color:white;background: green}
.table1{margin-left:auto; margin-right:auto;padding:10px;border-collapse:collapse}
.btn{ 	
	font-family: "'微软雅黑','Helvetica Neue',Helvetica,Arial,sans-serif"; 	
	font-size: 20px!important; 	height: 30px; 	
	line-height: 18px!important; 	
	padding: 3px 18px; 	
	display: inline-block; 	vertical-align: middle; 	
	font-weight: normal; 	border-radius: 3px; 	
	margin: 0 8px 0 3px; 	
	border: 1px solid #3383da; 	
	color: #ffffff; 	
	background-color: #3383da; 	
	cursor: pointer;
	width:120px;height:40px; 
	}
.btna,.btnb,.btnc,.btnd,.btnred{ 	
	font-family: "'微软雅黑','Helvetica Neue',Helvetica,Arial,sans-serif"; 	
	font-size: 40px!important; 	height: 30px; 	
	line-height: 18px!important; 	
	padding: 3px 18px; 	
	display: inline-block; 	vertical-align: middle; 	
	font-weight: blod; 	border-radius: 3px; 	
	margin: 0 8px 0 3px; 	
	border: 1px solid #FFA500; 	
	color: #ffffff; 		
	cursor: pointer; 
	width:200px;height:80px;
	margin-right:30px
	}
.btna {background-color: #3383da;}
.btnb {background-color: #F08080;}
.btnc {background-color: #F4A460}
.btnd {background-color: #CC99FF}
.btnred {background-color: red;}
    </style>
</head>
<body>
<script type="text/javascript">
var k=Number(1);
$(function(){
	aaad();
})
function aaad()
{
 	var str="<tr id="+k+" display:block;><td style='font-size:20px;'><input type='checkbox' style='width:20px;height:20px;' value="+k+">"
 	+"树材种<span></span><input type='text' style='width: 180px' name='treetype' id='sss"+k+"' value='杉木' readonly='readonly'>"
        +"检尺长(米)<span></span><input type='text' style='width: 180px' name='tdouble' id='td"+k+"' value='4' oninput='vvvolume("+k+")' onclick='locationInput'>检尺径(厘米)<span></span>"
          +"<input type='text' style='width: 180px' name='tradius' id='tr"+k+"' oninput='vvvolume("+k+")&setTimeout(makecount, 100)' onclick='locationInput'>"
          +"根数<span></span><input type='text' style='width: 180px' name='num' id='n"+k+"' oninput='vvvolume("+k+")&setTimeout(makecount, 100)' onclick='locationInput'>"
             +"材积(立方米)<span></span><input type='text' style='width: 180px' name='tvolume' id='tv"+k+"' oninput='makecount("+k+")' onclick='locationInput'></td></tr>";
             k=Number(k)+Number(1);
            $("#ttt5").append(str); 
             
}
$(function(){
	aaad1();
})
function aaad1()
{
 	var str="<tr id="+k+" display:block;><td style='font-size:20px;'><input type='checkbox' style='width:20px;height:20px;' value="+k+">"
 	+"树材种<span></span><input type='text' style='width: 180px' name='treetype' id='sss"+k+"' value='松木' readonly='readonly'>"
        +"检尺长(米)<span></span><input type='text' style='width: 180px' name='tdouble' id='td"+k+"'  value='4' oninput='vvvolume("+k+")&setTimeout(makecount, 100)' onclick='locationInput'>检尺径(厘米)<span></span>"
          +"<input type='text' style='width: 180px' name='tradius' id='tr"+k+"' oninput='vvvolume("+k+")&setTimeout(makecount, 100)' onclick='locationInput'>"
          +"根数<span></span><input type='text' style='width: 180px' name='num' id='n"+k+"' oninput='vvvolume("+k+")&setTimeout(makecount, 100)' onclick='locationInput'>"
             +"材积(立方米)<span></span><input type='text' style='width: 180px' name='tvolume' id='tv"+k+"'></td></tr>";
             k=Number(k)+Number(1);
            $("#ttt6").append(str); 
             
}
$(function(){
	aaad2();
})
function aaad2()
{
 	var str="<tr id="+k+" display:block;><td style='font-size:20px;'><input type='checkbox' style='width:20px;height:20px;' value="+k+">"
 	+"树材种<span></span><input type='text' style='width: 180px' name='treetype' id='sss"+k+"' value='杂木' readonly='readonly'>"
        +"检尺长(米)<span></span><input type='text' style='width: 180px' name='tdouble' id='td"+k+"'  value='4' oninput='vvvolume("+k+")&setTimeout(makecount, 100)' onclick='locationInput'>检尺径(厘米)<span></span>"
          +"<input type='text' style='width: 180px' name='tradius' id='tr"+k+"' oninput='vvvolume("+k+")&setTimeout(makecount, 100)' onclick='locationInput'>根数<span></span>"
          +"<input type='text' style='width: 180px' name='num' id='n"+k+"' oninput='vvvolume("+k+")&setTimeout(makecount, 100)' onclick='locationInput'>"
             +"材积(立方米)<span></span><input type='text' style='width: 180px' name='tvolume' id='tv"+k+"'></td></tr>";
             k=Number(k)+Number(1);
            $("#ttt7").append(str); 
             
}
$(function(){
	aaad3();
})
function aaad3()
{
 	var str="<tr id="+k+" display:block;><td style='font-size:20px;'><input type='checkbox' style='width:20px;height:20px;' value="+k+">树材种<span></span>"
          +"<select style='width: 180px' name='treetype' id='sss"+k+"'>"
           +"<option>--请选择--</option>"
          +"<option value='杉薪材'>--杉薪材--</option>"
          +"<option value='松薪材'>--松薪材--</option>"
          +"<option value='杂薪材'>--杂薪材--</option>"
          +"<option value='杉短材'>--杉短材--</option>"
          +"<option value='杉木兜'>--杉木兜--</option>"
          +"<option value='杉直柄'>--杉直柄--</option>"
          +"<option value='特种材'>--特种材--</option>"
          +"<option value='其他'>--其他--</option></select>"
          +"检尺长(米)<span></span><input type='text' style='width: 180px' name='tdouble' id='td"+k+"'  value='4' oninput='vvvolume("+k+")&setTimeout(makecount, 100)' onclick='locationInput'>检尺径(厘米)<span></span>"
          +"<input type='text' style='width: 180px' name='tradius' id='tr"+k+"' oninput='vvvolume("+k+")&setTimeout(makecount, 100)' onclick='locationInput'>根数<span></span>"
          +"<input type='text' style='width: 180px' name='num' id='n"+k+"' oninput='vvvolume("+k+")&setTimeout(makecount, 100)' onclick='locationInput'>"
          +"材积(立方米)<span></span><input type='text' style='width: 180px' name='tvolume'  id='tv"+k+"'></td></tr>";
             k=Number(k)+Number(1);
            $("#ttt8").append(str); 
             
}
function dddelete()
{
	var ck= $("input[type='checkbox']:checked");
	for (var i=0;i<ck.length;i++)
		{
		     var j=$(ck[i]).val();
		     alert(j);
		     $("#"+j+"").empty();
		}
}
function deleteAll()
{
	var mk=$("input[type='checkbox']:checked");
	var index=$("input[type='checkbox']:not(:checked)");
	for (var i=0;i<mk.length;i++)
    {
	    var h=$(mk[i]).val();
	    
	     $("#"+h+"").empty();
	}
   for (var a=0;a<index.length;a++)
	{
	    var hh=$(index[a]).val();
	    $("#"+hh+"").empty();
	}
}
/*function vvvolume(id)
{
	var td=$("#td"+id+"").val();
	var tr=$("#tr"+id+"").val();
	var num=$("#n"+id+"").val();
	if(num!="" && td!="" && tr!="")
	{
	$.ajax({
        url:"treeServlet",
        data:{
            "action":"volume",
            "td":td,
            "tr":tr 
        },
        type: "POST",
        dataType:"html",
        success: function (data) {
        	document.getElementById("tv"+id+"").value=Number(data*num);
        }
    })}
	}*/
	function vvvolume(id)
	{
		var td=$("#td"+id+"").val();
		var tr=$("#tr"+id+"").val();
		var num=$("#n"+id+"").val();
		if(num!="" && td!="" && tr!="")
		{
		$.ajax({
	        url:"treeServlet",
	        data:{
	            "action":"volume",
	            "td":td,
	            "tr":tr 
	        },
	        type: "POST",
	        dataType:"html",
	        success: function (data) {
	        	var vvv=Number(data*num);
	        	vvv=vvv.toFixed(4);
	        	document.getElementById("tv"+id+"").value=Number(vvv);
	        }
	    })}
		}
	//鼠标锁定
window.onload = function () {
        locationInput = function () {
        };
    }
</script>
<script type="text/javascript">
function GetRequest(){
    var url = location.search; //获取url中"?"符后的字串  
    var theRequest = new Object();  
    if (url.indexOf("?") != -1) {  
        var str = url.substr(1);  
        strs = str.split("&");  
        //截取&后循环
        for (var i = 0; i < strs.length; i++) {  
            theRequest[strs[i].split("=")[0]] = decodeURI(strs[i].split("=")[1]);  //decodeURI()解决中文乱码
        }  
    }  
    return theRequest;
}
function showList(){
    var request = new Object();  
        request = GetRequest(); //取到传过来的数据
        var workid = request['workid']; 
        var tolstere = request['tolstere']; 
        var toltree = request['toltree']; 
        document.getElementById("workid").value=workid;
        document.getElementById("tolstere").value=tolstere;
        document.getElementById("toltree").value=toltree;
        //alert(workid)
}
</script>
</head>
<body onload="showList()">
<% Date d = new Date();

SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

String now = df.format(d); %>
<% workpage c=(workpage)request.getAttribute("workpage");%>
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
     <!--<li><a href="workpageTreeBuy.jsp"><i class="icon icon-th-list"></i> <span>木材收购单</span></a></li>
    <li><a href="compareTreeList.jsp"><i class="icon icon-th-list"></i> <span>木材装车对比</span></a></li>
    <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>录入数据</span> <span class="label label-important">2</span></a>
       <ul>
        <li><a href="passworkpage.jsp">录入进仓木材数据</a></li>
        <li><a href="treeout.jsp">录入出场木材数据</a></li>
      </ul>
     </li>
     <li><a href="InyardShenhesu.jsp"><i class="icon icon-th-list"></i> <span>查看进场木材反馈</span></a></li>-->
     <li class="active"><a href="passworkpage.jsp"><i class="icon icon-th-list"></i> <span>录入进仓木材数据</span></a></li>
    <li><a href="treeoutCover.jsp"><i class="icon icon-th-list"></i> <span>录入出仓木材数据</span></a></li>
  </ul>
</div>
<!--sidebar-menu-->
<!--main-container-part-->
<div id="content">
  <div id="content-header">
    <div id="breadcrumb"> <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a></div>
  </div>
    <article>
        <div>
            <h1 class="book_h01">上传检尺野账附件</h1>
            <!--  <form  onSubmit="return inputNull(this)" action="treeServlet?action=inyard" method="POST" >-->
            <form  id="myform" action="treeServlet?action=addTreeYezhang" method="POST" enctype="multipart/form-data">
            <div id="divprint">
                <div class="top">
                <p class="table_p"><span>采伐工单信息</span></p>
                            <table class="table1" >
                                <tbody>
                                <tr>
                                <td>工单号</td>
                              <td> 
                             <input readonly="readonly" name="workid" type="text" maxlength="32" id="workid">
                            </td>
                                    <td style="font-size:20px;width:auto">合计树材<span></span>
                                        <input type="text" style="width:120px; font-size:20px" name="toltree" id="toltree"  readonly="readonly">根(块、件)<span></span></td>
                                    <td style="font-size:20px">合计树材<span></span>
                                        <input type="text" style="width:120px; font-size:20px" name="tolstere" id="tolstere" readonly="readonly">立方米(吨、根)<span></span>
                                        </td>
                                </tr>
                                <tr>
                                <td>上传野账附件</td>
                                <td>
                                <input type="file" class="form-control" name="workidfile" id="workidfile">
                                </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                  <div class="" style="text-align:center">
                  <span><button style="width:120px;heigth:60px" class="btn" type="submit"  id="mybutton" value="上传附件">上传附件</button></span>
                <div style="clear: both;padding-bottom: 40px"></div>
                </div>
            </form>
        </div>
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