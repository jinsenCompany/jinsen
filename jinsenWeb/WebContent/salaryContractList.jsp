<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>合同进度</title>
<link rel="stylesheet" href="css/tableall.css"/>
<link rel="stylesheet" href="css/registe.css"/>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/fullcalendar.css" />
<link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/fullcalendar.css" />
<link rel="stylesheet" href="css/matrix-style.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="css/jquery.gritter.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
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
    <style>
#h li{float: left; }
#h a{font-size:15px;width: 230px; height: 30px;padding: 10px 0;text-align: center;  background: #3c763d; display: block; text-decoration:none; color:white}
#h a:hover{color:white;background: green}
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
    top: 1px;
}
</style>
</head>
<script type="text/javascript">
function progress(contractid){
	$.ajax({
	    url:"salaryServlet",
	    data:{
	        "action":"contractListProg",
	        "contractid":contractid
	    },
	    async:false,
	    type: "get",
	    dataType:"json",
	    success: function (result) {
	    	if(result==null)
	    		{
	    	        alert("信息有误！");
	    		}
	    	else{
	    		var htmlStr = "";
	    		 //alert(result);
	    		 var list=result["contractProgress"];
	    		 alert(list);
	    		 var t=Object.keys(list).length;
	    		 //alert(Object.values(list));//获得值
	    		 //alert(Object.values(list["tolstere"]));//获得值
	    		/*$("#mysomething").empty();
	         	for(var i=0;i<t;i++)
	     		{
	         		var j=list[i];
	         		var str2="<table class='top-table'>"
	         			+"<tr><td class='top-table-label'>已发去材积：</td>"
	                     +"<td><input type='text' id='tolstere' disabled='disabled' value='"+j.tolstere+"'></td>"
	                     +"</tr><tr><td class='top-table-label'>总材积：</td>"
	                     +"<td><input type='text' id='treenumber' disabled='disabled' value='"+j.treenumber+"'></td></tr></table>";
	         		$("#mysomething").append(str2); 
	     		}*/
           }
		}
	    })

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
    <li> <a href="salaryContractList.jsp"><i class="icon icon-home"></i> <span>合同进度</span></a></li>
    <li> <a href="saleCalloutOrderShenhe.jsp"><i class="icon icon-share-alt"></i><span>审批调令</span></a></li>
  </ul>
</div>
<!--sidebar-menu-->

<div id="content">
<div id="content-header">
    <div id="breadcrumb"> <a href="managerP.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
  </div>
<!--End-breadcrumbs-->

<div class="find-top">
     <p class="p-tail"><i class="i-tail"></i> 该页面是查看合同详细信息查看</p>
</div>
 <div class="table-con">
        <table id="table1" class="table-style"></table>
</div>
<!--  <div id="dataList">
        <table class="top-table">
          <tr><p class="table_p"><span>主要信息</span></p>
          </tr>
          <tr><td class="top-table-label">已使用材积：</td>
              <td><input type="text" id="tolstere" disabled="disabled"></td>
              <td class="top-table-label">总材积：</td>
              <td><input type="text" id="treenumber" disabled="disabled"></td>
              </tr>
      </table>
        </div>-->
</div>
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
                url: "salaryServlet?action=contractProgress",
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
            		"title": "合同信息",
                    "font-size":"100px",
                    "halign":"center",
                    "align":"center",
                    "valign": "middle",
                    "colspan": 10
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
                        field: 'contractid',
                        align: 'center',
                        valign: 'middle'
                    },
                	{
                    title: "合同编号",
                    field: 'contractnum',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '供方（甲方）',
                    field: 'provider',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '需方（乙方）',
                    field: 'demander',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '数量',
                    field: 'treenumber',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '合同开始时间',
                    field: 'starttime',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '合同结束时间',
                    field: 'endtime',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '保证金',
                    field: 'margin',
                    align: 'center',
                    valign: 'middle'
                },
                /*{
                    title: '合同进度',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="salaryServlet?action=contractListProg&contractid=\''+ row.contractid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<a><button type="button" data-id="98"  class="btn btn-xs btn-primary" onclick="progress(\''+ row.contractid + '\')">查看</button></a>';
                        return  d;
                    }
                },*/
                {
                    title: '合同内容详情',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<a href="salaryServlet?action=contractList&contractid=\''+ row.contractid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        return  d;
                    }
                },
                ],
            ]
        });
    }

</script> 
</body>
</html>