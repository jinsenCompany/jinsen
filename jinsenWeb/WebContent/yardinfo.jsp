<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增货场管理页面</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
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
        body,td,th {font-family: Verdana, Arial, Helvetica, sans-serif;font-size: 18px;color: #1d1007; line-height:24px}
.table_p{line-height: 28px;border-bottom: 1px #d0e6ec solid;position: relative;margin-bottom: 10px; 
            margin-top: 35px; margin-left:10px}
.table_p span{border-bottom: 3px #42cdec solid;display: inline-block;position: absolute;bottom: -1px;font-weight: bold;font-size: 20px}
.but_p button{width: 180px}
.table2{margin-left:auto; margin-right:auto;padding:10px;border-collapse:separate; border-spacing:2px 20px;CELLPADDING:5;CELLSPACING:5}
.table2 td{font-size:20pt}
</style> 
<script type="text/javascript">
function yardAdd(){
	var yardname=$("#yardname").val();
	var yardvolume=$("#yardvolume").val();
	var manageyard=$("#yardmanager").val();
	var managephone=$("#managephone").val();
	var remarks=$("#remarks").val();
	if(yard=="")
		{
		alert("请先填写货场信息！");
		}
	else{
    $.ajax({
        url:"workpageServlet",
        data:{
            "action":"yardAdd",
            "yardname":yardname,
            "yardvolume":yardvolume,
            "yardmanager":yardmanager,
            "managephone":managephone,
            "remarks":remarks,
        },
        type: "POST",
        dataType:"html",
        success: function (data) {
        	alert(data);
        	if(data>0)
        		{
        	        alert("添加成功！");
        		}
        	else{
        		 alert("添加失败");
        	}
        }
    })
    }
}
</script>
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
<body> 
 <div id="header">
  <h1><a href="dashboard.html">货场管理平台</a></h1>
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
      <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>审核</span> <span class="label label-important">2</span></a>
       <ul>
        <li><a href="yardMoneryDirector.jsp">费用结算</a></li>
        <li><a href="yardInventoryBootstrapDirector.jsp">货场盘点</a></li>
      </ul>
     </li>
     <li><a href="workpageTreeBuyDirector.jsp"><i class="icon icon-th-list"></i> <span>木材收购单</span></a></li>
    <li><a href="compareTreeDirector.jsp"><i class="icon icon-th-list"></i> <span>木材装车对比</span></a></li>
    <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>录入数据</span> <span class="label label-important">2</span></a>
       <ul>
        <li><a href="treeinDirector.jsp">录入进场木材数据</a></li>
        <li><a href="treeoutDirector.jsp">录入出场木材数据</a></li>
      </ul>
     </li>
    <li><a href="passworkpageDirector.jsp"><i class="icon icon-th-list"></i> <span>查询采伐工单信息</span></a></li>
    <li class="action"><a href="yardinfo.jsp"><i class="icon icon-home"></i> <span>新增货场信息</span></a> </li>
   <li><a href="cancellingStocksTable.jsp"><i class="icon icon-home"></i> <span>货场报损</span></a> </li>
  </ul>
</div>
<!--sidebar-menu-->
   <div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"> <a href="hello2.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
  </div>
    <p class="table_p"><span>货场信息列表</span></p>
<div class="table-con">
    <table id="table1" class="table-style"></table>
</div>
  <p style="font-size:28px;color:blue;border-bottom: 3px #42cdec solid;display: inline-block">新增货场信息：<a href = "JavaScript:void(0)" onclick = "openDialog()">请点这里</a></p> 
<div id="light" class="white_content">
   <a style="float:right" href = "javascript:void(0)" onclick = "closeDialog()">关闭</a>
<form onSubmit="return inputNull(this)" action="workpageSevrlet?action=yardAdd" method="POST">
<table class="table2">
<tbody>
<caption class="book_h01">货场管理人员信息</caption>
<tr><td style="font-size:20pt">货场</td><td><input class="text" name="yardname" id="yardname"></td></tr>
<tr><td>货场容积</td><td><input class="text" name="yardvolume" id="yardvolume"></td></tr>
<tr><td>货场管理员</td><td><input class="text" name="yardmanager" id="yardmanager"></td></tr>
<tr><td>联系方式</td><td><input class="text" name="managephone" id="managephone"></td></tr>
<tr><td>备注</td><td><input class="text" name="remarks" id="remarks"></td></tr>
</tbody>
 </table>
 <div class=" but_p" style="float:center;">
  <button class="but_save" type="submit" id="mybutton" value="保存货场信息">保存货场信息</button>
 <!--  <input style="float:right;" type="button" onclick="yardAdd()" value="保存货场信息">-->
 </div>
 </form> 
  </div> 
 <div id="fade" class="black_overlay"></div> 
 </div>
</body> 
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
            url: "workpageSevrlet?action=yardinfo",
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
            columns: [
            	{
                    checkbox: "true",
                    field: 'check',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: "序号",
                    field: 'yardid',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '货场',
                    field: 'yardname',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '货场容积',
                    field: 'yardvolume',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '管理员',
                    field: 'yardmanager',
                    align: 'center'
                },
                {
                    title: '联系方式',
                    field: 'managephone',
                    align: 'center'
                },
                /*{
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a><button type="button" data-id="98"  class="btn btn-xs btn-primary" onclick="overtimedelete(\''+ row.workid + '\')">删除</button> ';
                        //var  d = '<a href="workpageSevrlet?action=alldelete&workid=\''+ row.workid + '\'"><button  id="id="delete" data-id="98" class="btn btn-xs btn-primary">删除</button></a>';
                        return  d;
                    }
                },*/
            ]
        });
    }
 </script>
    <script type="text/javascript">
        $(function(){

        })
        function openDialog(){
            document.getElementById('light').style.display='block';
            document.getElementById('fade').style.display='block'
        }

        function closeDialog(){
            document.getElementById('light').style.display='none';
            document.getElementById('fade').style.display='none'
        }
    </script>
</html>