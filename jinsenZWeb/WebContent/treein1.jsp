<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>输入进场木材数据</title>
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
<link type="text/css" rel="stylesheet" href="css/registe.css"/>
    
    <style>
    body,td,th {font-family: Verdana, Arial, Helvetica, sans-serif;font-size: 18px;color: #1d1007; line-height:24px}
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
         .table1{width:60%; height:400px;margin-left:auto; margin-right:auto;padding:10px;border-collapse:collapse}
         td,th{text-align:center;vertical-align:middle}
         tr{text-align:center}
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
</head>
<body>
<script type="text/javascript">
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
function vvvolume()
	{
		var td=$("#td").val();
		var tr=$("#tr").val();
		var num=$("#n").val();
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
		        	document.getElementById("tv").value=Number(data*num);
		        }
	  })}
	}
	function vvvolume1()
	{
		var td=$("#td1").val();
		var tr=$("#tr1").val();
		var num=$("#n1").val();
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
		        	document.getElementById("tv1").value=Number(data*num);
		        }
	  })}
	}
/*var el = document.getElementById("tv");
	el.addEventListener("click", vvvolume, false);*/
/*function makecount()
{
	var mk=$("input[type='checkbox']:checked");
	var index=$("input[type='checkbox']:not(:checked)");
	var totalt=Number(0);
	var totalst=Number(0);
	var total=Number(0);
	var tot=Number(0);
	//System.out.println("...." + mk + "...");
	for (var i=0;i<mk.length;i++)
    {
	     var h=$(mk[i]).val();
	     totalst+=Number(document.getElementById("tv"+h+"").value);
		 totalt+=Number(document.getElementById("n"+h+"").value);
	}
   for (var a=0;a<index.length;a++)
	{
	    var hk=$(index[a]).val();
	    total+=Number(document.getElementById("tv"+hk+"").value);
	    tot+=Number(document.getElementById("n"+hk+"").value);
	}
   document.getElementById("toltree").value=Number(totalt+tot);
   document.getElementById("tolstere").value=Number(totalst+total);
}*/

function makecount()
{
	var n=$("#n").val();
	var tv=$("#tv").val();
	var totalt=Number(0);
	var totalst=Number(0);
	var total=Number(0);
	var tot=Number(0);
	//System.out.println("...." + mk + "...");
	     totalst+=Number(document.getElementById("tv").value);
		 totalt+=Number(document.getElementById("n").value);
   document.getElementById("toltree").value=Number(totalt);
   document.getElementById("tolstere").value=Number(totalst);
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
    <li class="active"><a href="index.html"><i class="icon icon-home"></i> <span>信息通知</span></a> </li>
    <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>录入数据</span> <span class="label label-important">2</span></a>
       <ul>
        <li><a href="treein.jsp">录入进场木材数据</a></li>
        <li><a href="treeout.jsp">录入出场木材数据</a></li>
      </ul>
     </li>
    <li> <a href="widgets.html"><i class="icon icon-inbox"></i> <span>异常处理</span></a> </li>
  </ul>
</div>
<!--main-container-part-->
<!--sidebar-menu-->
<!--main-container-part-->
<div id="content">
  <div id="content-header">
    <div id="breadcrumb"> <a href="Surveyor.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> 检尺员首页</a></div>
  </div>
    <article class="artlce">
        <div class="book_con01">
            <form  onSubmit="return inputNull(this)" action="codepageSevrlet?action=addCodepage" method="POST" >
            <div class="top" id="divprint">
               <div class="top_out">
                       <table class="table1" border="1">
                       <caption class="book_h01">录入进场木材数据</caption>
                         <tr>
                         <th colspan="2">工单号</th>
                         <th>采伐证号</th>
                         <th>采伐地点</th>
                         <th>进场时间</th>
                         </tr>
                         <tr>
                         <td colspan="2"><input type="text" name="workid" id="wworkid" placeholder="工单号"></td>
                         <td><input type="text" name="cutnum" id="ccutnum" placeholder="采伐证号"></td>
                         <td><input type="text" name="cutsite" id="ccutsite" placeholder="采伐地点"></td>
                         <td><input type="date" name="yarddate" id="yyarddate" placeholder="进场时间"></td>
                         </tr>
                         <tr>
                         <th colspan="2">货场地点</th>
                         <th>车牌号</th>
                         <th>批次号</th>
                         <th>检尺员</th>
                         </tr>
                         <tr>
                         <td colspan="2"><input type="text" name="yard" id="yyard" placeholder="货场地点"></td>
                         <td><input type="text" name="carnumber" id="ccarnumber" placeholder="车牌号"></td>
                         <td><input type="text" name="batchnum" id="bbatchnum" placeholder="批次号"></td>
                         <td><input type="text" name="surveyor" id="ssurveyor" placeholder="检尺员"></td>
                         </tr>
                         <tr>
                         <th>树材种</th>
                         <th>检尺长</th>
                         <th>检尺径</th>
                         <th>根数</th>
                         <th>材积</th>
                         </tr>
                         <tr>
                         <td><input type="text" name='treetype' id='sss' placeholder="树材种"></td>
                         <td><input type='text' style='width: 180px' name='tdouble' id='td'></td>
                         <td><input type='text' style='width: 180px' name='tradius' id='tr'></td>
                         <td><input type='text' style='width: 180px' name='num' id='n'></td>
                         <td><input type='text' style='width: 180px' name='tvolume' id='tv' onclick='vvvolume()'></td>
                         </tr>
                         <tr>
                         <td><input type="text" name='treetype' id='sss'></td>
                         <td><input type='text' style='width: auto' name='tdouble' id='td1'></td>
                         <td><input type='text' style='width: auto' name='tradius' id='tr1'></td>
                         <td><input type='text' style='width: auto' name='num' id='n1'></td>
                         <td><input type='text' style='width: auto' name='tvolume' id='tv1' onclick='vvvolume1()'></td>
                         </tr>
                         <tr>
                         <td><input type="text" name='treetype' id='sss'></td>
                         <td><input type='text' style='width: auto' name='tdouble' id='td'></td>
                         <td><input type='text' style='width: auto' name='tradius' id='tr'></td>
                         <td><input type='text' style='width: auto' name='num' id='n'></td>
                         <td><input type='text' style='width: auto' name='tvolume' id='tv' onclick='vvvolume()'></td>
                         </tr>
                         <tr>
                         <td><input type="text" name='treetype' id='sss'></td>
                         <td><input type='text' style='width: auto' name='tdouble' id='td'></td>
                         <td><input type='text' style='width: auto' name='tradius' id='tr'></td>
                         <td><input type='text' style='width: 180px' name='num' id='n'></td>
                         <td><input type='text' style='width: 180px' name='tvolume' id='tv' onclick='vvvolume()'></td>
                         </tr>
                         <tr>
                         <td><input type="text" name='treetype' id='sss'></td>
                         <td><input type='text' style='width: auto' name='tdouble' id='td'></td>
                         <td><input type='text' style='width: auto' name='tradius' id='tr'></td>
                         <td><input type='text' style='width: auto' name='num' id='n'></td>
                         <td><input type='text' style='width: auto' name='tvolume' id='tv' onclick='vvvolume()'></td>
                         </tr>
                         <tr>
                         <td><input type="text" name='treetype' id='sss'></td>
                         <td><input type='text' style='width: auto' name='tdouble' id='td'></td>
                         <td><input type='text' style='width: auto' name='tradius' id='tr'></td>
                         <td><input type='text' style='width: auto' name='num' id='n'></td>
                         <td><input type='text' style='width: auto' name='tvolume' id='tv' onclick='vvvolume()'></td>
                         </tr>
                         <tr >
                              <td colspan="2" style="font-size:20px;width:auto">合计树材<span></span>
                                 <input type="text" style="width:120px; font-size:20px" name="toltreesmall" id="toltree" onclick="makecount()">
                                    根(块、件)<span></span></td>
                               <td colspan="3" style="font-size:20px">合计树材<span></span>
                                  <input type="text" style="width:120px; font-size:20px" name="tolsteresmall" id="tolstere">
                                   立方米(吨、根)<span></span></td>
                                </tr>
                        </table> 
                        </div>
                        </div>
                        <div class="1" ; style="text-align:center">

		    <span  style="text-align: center;"><button class="btn" type="submit" id="mybutton" value="保存申请书">保存</button></span>
		     <span  ><button class="btn" type="button" id="btnPrint" value="打印">打印</button></span> 
		 </div>
                <div style="clear: both;padding-bottom: 40px"></div>
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