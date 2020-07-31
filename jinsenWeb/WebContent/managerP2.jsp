<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jinshen.bean.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Connection"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>管理部门平台导航</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
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
.black_overlay{ 
            display: none; 
            position: absolute; 
            top: 0%; 
            left: 0%; 
            width: 100%; 
            height: 100%; 
            background-color: black; 
            z-index:1001; 
            -moz-opacity: 0.8; 
            opacity:.80; 
            filter: alpha(opacity=88); 
        } 
        .white_content { 
            display: none; 
            position: absolute; 
            top: 25%; 
            left: 25%; 
            width: 55%; 
            height: 55%; 
            padding: 20px; 
            border: 1px solid blue; 
            background-color: white; 
            z-index:1002; 
            overflow: auto; 
        }
 .table2{margin-left:auto; margin-right:auto;padding:10px;border-collapse:separate; border-spacing:2px 20px;CELLPADDING:5;CELLSPACING:5}
.table2 td{font-size:20pt}
</style>
<script type="text/javascript">
function addProgress(){
	var length=$("#codetable tr").length;
	var map={};
	length=(length-1)*2;
	var group=[];
	//var projectPackageid=$("#projectPackageid").val();
	//var cutnumid=document.getElementById("cutnumid").value;
	var str=$("#table1").bootstrapTable('getSelections');
	if(str.length<1) {
		alert("无选中记录！");
	}
    else{
    	for(var i=0;i<str.length;i++){
    		var j=str[i];
    		group[i]=j.cutnumid;
    	}
 var mymap=JSON.stringify(group);
$.ajax({
    url:"cutnumServlet",
    data:{
        "action":"watchProgress",
        "project":mymap,
    },
    type: "POST",
    dataType:"html",
    success: function (data) {
    	if(data==null)
    		{
    	        alert("信息有误！");
    		}
    	else{
    		 alert(data);
    		 var cp=data["cutnumProgress"];
    		 var sv=data["svstatus"];
    		 $("#sss").empty();
    		 //for(var i=0;i<sv.length;i++)
    		 {
    			 //var j=sv[i];
    			 var str2="<table class='top-table'>"
         			+"</tr><tr><td class='top-table-label'>采伐证状态：</td>"
                     +"<td><input type='text' id='status' disabled='disabled' value='"+sv+"'></td></tr></table>";
    			 //alert(status);
    			 $("#sss").append(str2); 
    		 }
    		 $("#ttt").empty();
    		 var str1="<tr><p class='table_p'><span>采伐证信息</span></p></tr>"
                 +"<tr><td class='top-table-label' >采伐证号：</td>"
                 +"<td class='top-table-label'>工程包：</td>"
                 +"<td class='top-table-label'>采伐地点：</td>"
                 +"<td class='top-table-label'>采伐面积：</td>"
                 +"<td class='top-table-label'>采伐蓄积：</td>"
                 +"<td class='top-table-label'>采伐开始时间：</td>"
                 +"<td class='top-table-label'>采伐结束时间：</td></tr>";
    		 for(var i=0;i<cp.length;i++)
    	    {
    			var j=cp[i];
    			/*var cutnum=j["cutnum"];
    			var projectPackageid=j["projectPackageid"];
    			var cutsite=j["cutsite"];
    			var cutarea=j["cutarea"];
    			var cutstore=j["cutstore"];
    			var starttime=j["starttime"];
    			var endtime=j["endtime"];
    			var status=j["status"];*/
    			str+="<tr id='"+(i+1)+"'><td><select id='cutnum"+(i+1)+"' ><option>"+j.cutnum+"</option></select></td>"
                +"<td><input type=text disabled=disabled value='"+j.projectPackageid+"' id='projectPackageid"+(i+1)+"'></td>"
                 +"<td><input type='text' id='cutsite"+(i+1)+"' value='"+j.cutsite+"'></td>"
                 +"<td><input type='text' id='cutarea"+(i+1)+"' value='"+j.cutarea+"'></td>"
                 +"<td><input type='text' id='cutstore"+(i+1)+"' value='"+j.cutstore+"'></td>"
                 +"<td><input type='text' id='starttime"+(i+1)+"' value='"+j.starttime+"'></td>"
                 +"<td><input type='text' id='endtime"+(i+1)+"' value='"+j.endtime+"'></td>"
                +"<td><input type='text' id='status"+(i+1)+"' value='"+j.status+"'></td></tr>"
    	    }
    		 ttt.innerHTML=str1;
    	}
    }
})
   }
}
</script>
<body>

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
    <li ><a href="cutnumApplysee.jsp"><i class="icon icon-home"></i> <span>审核采伐证申请</span></a> </li>
    <li> <a href="manageCutnum.jsp"><i class="icon icon-inbox"></i> <span>录入采伐证材料</span></a></li>
    <li> <a href="manageCutnumsee.jsp"><i class="icon icon-th"></i> <span>查看采伐证材料</span></a></li>
    <li> <a href="cutnumTable.jsp"><i class="icon icon-th-list"></i> <span>采伐证报表</span></a></li>
  </ul>
</div>
<!--sidebar-menu-->

<!--main-container-part-->
<div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"> <a href="hello2.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
  </div>
<!--End-breadcrumbs-->    

<!--Chart-box-->    
    <div class="row-fluid">
      <div class="widget-box">
        <div class="widget-title bg_lg"><span class="icon"><i class="icon-signal"></i></span>
          <h5>采伐证信息列表</h5>
        </div>
        <div class="widget-content" >
          <div class="row-fluid">
            <div class="span9">
              <div class="table-con">
        <table id="table1" class="table table-bordered"></table>
    </div>
            </div>
            <div class="span3">
              <ul class="site-stats">
                <li class="bg_lh"><i class="icon-user"></i> <strong>2540</strong> <small>Total Users</small></li>
                <li class="bg_lh"><i class="icon-plus"></i> <strong>120</strong> <small>New Users </small></li>
                <li class="bg_lh"><i class="icon-shopping-cart"></i> <strong>656</strong> <small>Total Shop</small></li>
                <li class="bg_lh"><i class="icon-tag"></i> <strong>9540</strong> <small>Total Orders</small></li>
                <li class="bg_lh"><i class="icon-repeat"></i> <strong>10</strong> <small>Pending Orders</small></li>
                <li class="bg_lh"><i class="icon-globe"></i> <strong>8540</strong> <small>Online Orders</small></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
<!--End-Chart-box--> 
    <hr/>
    <div class="row-fluid">
  <div>
  <table class="top-table" id="ttt">
<tbody>
  <caption class="book_h01">采伐证进度</caption>
<tr><td class="top-table-label">采伐证号</td>
<td class="top-table-label">工程包:</td>
<td class="top-table-label">采伐地点:</td>
<td class="top-table-label">采伐面积(亩):</td>
<td class="top-table-label">采伐蓄积(立方米):</td>
<td class="top-table-label">采伐开始时间:</td>
<td class="top-table-label">采伐结束时间:</td>
<td class="top-table-label">采伐进度:</td></tr>
<tr>
<td><input class="text" disabled="disabled" name="cutnum" id="cutnum"></td>
<td><input class="text" disabled="disabled" name="projectPackageid" id="projectPackageid"></td>
<td><input class="text" disabled="disabled" name="cutsite" id="cutsite"></td>
<td><input class="text" disabled="disabled" name="cutarea" id="cutarea"></td>
<td><input class="text" disabled="disabled" name="cutstore" id="cutstore"></td>
<td><input class="text" disabled="disabled" name="starttime" id="starttime"></td>
<td><input class="text" disabled="disabled" name="endtime" id="endtime"></td>
<td><input class="text" disabled="disabled" name="status" id="status"></td>
</tr>
</tbody>
 </table>
 </div>
  <div id="light" class="white_content">
   <a style="float:right" href = "javascript:void(0)" onclick = "closeDialog()">点这里关闭本窗口</a>
<table class="table2" id="ttt">
<tbody>
  <caption class="book_h01">采伐证进度</caption>
<tr><td style="font-size:20pt">采伐证号</td><td><input class="text" name="cutnum" id="cutnum"></td></tr>
<tr><td>工&nbsp;程&nbsp;包:&nbsp;</td><td><input class="text" name="projectPackageid" id="projectPackageid"></td></tr>
<tr><td>采伐地点:&nbsp;</td><td><input class="text" name="cutsite" id="cutsite"></td></tr>
<tr><td>采伐面积(亩):&nbsp;</td><td><input class="text" name="cutarea" id="cutarea"></td></tr>
<tr><td>采伐蓄积(立方米):&nbsp;</td><td><input class="text" name="cutstore" id="cutstore"></td></tr>
<tr><td>采伐开始时间:&nbsp;</td><td><input class="text" name="starttime" id="starttime"></td></tr>
<tr><td>采伐结束时间:&nbsp;</td><td><input class="text" name="endtime" id="endtime"></td></tr>
<tr><td>采伐进度:&nbsp;</td><td><input class="text" name="status" id="status"></td></tr>
</tbody>
 </table>
  </div> 
 <div id="fade" class="black_overlay"></div> 
      
</div>

<!--end-main-container-part-->

<!--Footer-part-->

<div class="row-fluid">
  <div id="footer" class="span12"> 2013 &copy; Matrix Admin. Brought to you by <a href="http://themedesigner.in">Themedesigner.in</a> </div>
</div>
</div>
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
<script src="js/jquery.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
<script>
    $(function(){
    	table1();
    })
    function table1(){
    	$('#table1').bootstrapTable({
            method: "get",
            striped: true,
            singleSelect: false,
            url: "cutnumServlet?action=seecutP",
            data:{},
            dataType: "json",
            pagination: true, //分页
            pageSize: 8,
            pageNumber: 1,
            search: true, //显示搜索框
            contentType: "application/x-www-form-urlencoded",
            showRefresh: true,                      //是否显示刷新按钮
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            detailView: true,
        columns: [[
        	{
        		"title": "采伐证信息",
                "font-size":"100px",
                "halign":"center",
                "align":"center",
                "valign": "middle",
                "colspan": 9
            }],
            [
            	{
                    checkbox: "true",
                    field: 'check',
                    align: 'center',
                    valign: 'middle'
                },
                {
                	title: "序号",
                    field: 'cutnumid',
                    align: 'center',
                    valign: 'middle'
                },
            	{
                title: "采伐证号",
                field: 'cutnum',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '采法许可证编号',
                field: 'number',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '采伐公司',
                field: 'company',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '采伐地点',
                field: 'cutsite',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '进度',
                field: 'opr',
                width: 180,
                align: 'center',
                formatter: function (cellval, row) {
                	var  d = '<a <button style="margin-top:100px;" href = "javascript:void(0)" onclick = "openDialog()" class="btn btn-xs btn-primary">进度</button></a>';
                	//var  d = '<a <button  href = "javascript:void(0)" onclick = "window.open("map.jsp")" class="btn btn-xs btn-primary">进度</button></a>';
                    //var  d = '<a <button  href = "cutnumServlet?action=watchProgress&cutnumid=\''+ row.cutnumid + '\'" onclick = "openDialog()" class="btn btn-xs btn-primary">进度</button></a>';
                    //var  d = '<a href="cutnumServlet?action=watchProgress&cutnumid=\''+ row.cutnumid + '\'"><button  href = "javascript:void(0)" target="_blank" onclick = "openDialog()" data-id="98" class="btn btn-xs btn-primary">进度</button></a>';
                    return  d;
                }
            },
            {
                title: '进度2',
                field: 'opr',
                width: 180,
                align: 'center',
                formatter: function (cellval, row) {
                	var  d = '<a <button style="margin-top:100px;" onclick = "addProgress()" class="btn btn-xs btn-primary">进度</button></a>';
                	//var  d = '<a <button  href = "javascript:void(0)" onclick = "window.open("map.jsp")" class="btn btn-xs btn-primary">进度</button></a>';
                    //var  d = '<a <button  href = "cutnumServlet?action=watchProgress&cutnumid=\''+ row.cutnumid + '\'" onclick = "openDialog()" class="btn btn-xs btn-primary">进度</button></a>';
                    //var  d = '<a href="cutnumServlet?action=watchProgress&cutnumid=\''+ row.cutnumid + '\'"><button  href = "javascript:void(0)" target="_blank" onclick = "openDialog()" data-id="98" class="btn btn-xs btn-primary">进度</button></a>';
                    return  d;
                }
            },
            {
                title: '查看',
                field: 'opr',
                width: 180,
                align: 'center',
                formatter: function (cellval, row) {
                    //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                    var  d = '<a href="cutnumServlet?action=watch&cutnumid=\''+ row.cutnumid + '\'"><button style="margin-top:100px;" id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                    return  d;
                }
            },
            ],
        ]
    });
    }
</script>
 <script type="text/javascript">
        $(function(){
        	
        })
        function openDialog(){
        	//document.getElementById('ttt').style.display='block';
            document.getElementById('light').style.display='block';
            document.getElementById('fade').style.display='block'
            
        }

        function closeDialog(){
            document.getElementById('light').style.display='none';
            document.getElementById('fade').style.display='none'
        }
    </script> 
</body>
</html>
