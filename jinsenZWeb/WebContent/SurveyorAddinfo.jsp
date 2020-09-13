<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增检尺员信息</title>
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
/*定义类名为.thead-blue的样式*/
.table .thead-blue th {
color: #fff;
background-color: #3195f1;
border-color: #0d7adf;
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
<script type="text/javascript">
function surveyorAdd(){
	var map={};
	var kk=Number(0);
	var length=$("#tablesec tr").length;
	 //alert(yardname);
	    for(var id=1;id<=length;id++){
    		var group=[];
    	    group[0]=document.getElementById("surveyor"+id+"").value;
    	    if(group[0]=="")
    	    	{
    	    	alert("请将信息填写完整！");
    	    	}
    	    else{
               map[id-1]=group;
    	    }
    	}
	kk=length;
	var mymap=JSON.stringify(map);
	//alert(mymap);
    $.ajax({
        url:"workpageSevrlet",
        data:{
            "action":"surveyorAdd",
            "newtree":mymap,
            "id":kk,
        },
        type: "POST",
        dataType:"html",
        success: function (data) {
        	alert(data);
        	if(data>0)
        		{
        	        alert("添加成功！");
        	        window.location.href = 'SurveyorAddinfo.jsp';
        		}
        	else{
        		 alert("添加失败");
        		 window.location.href = 'SurveyorAddinfo.jsp';
        	}
        }
    })
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
var k=Number(1);
$(function(){
	addSection();
})
function addSection()
{
 	var str="<tr id="+k+" display:block;><td style='font-size:20pt;'><input type='checkbox' style='width:20px;height:20px;' value="+k+">"
 	+"<span style='color:red; font-size: 20px'>*</span>检尺人员姓名:&nbsp;&nbsp;<span style='font-size:20px;'></span><input type='text' style='width:360px;heigth:100px' name='surveyor' id='surveyor"+k+"' value=''></td></tr>";
             k=Number(k)+Number(1);
            $("#ttt5").append(str);        
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
      <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>货场费用</span> <span class="label label-important">2</span></a>
       <ul>
        <li><a href="goodsYardCost.jsp">费用结算</a></li>
        <li><a href="yardInventoryBootstrapDirector.jsp">货场盘点</a></li>
      </ul>
     </li>
     <li><a href="workpageTreeBuyDirector.jsp"><i class="icon icon-th-list"></i> <span>木材收购单</span></a></li>
    <li><a href="compareTreeListDirector.jsp"><i class="icon icon-th-list"></i> <span>木材装车对比</span></a></li>
    <!--  <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>录入数据</span> <span class="label label-important">2</span></a>
       <ul>
        <li><a href="passworkpageDirector.jsp">录入进仓木材数据</a></li>
        <li><a href="treeoutDirector.jsp">录入出场木材数据</a></li>
      </ul>
     </li>-->
     <li><a href="InyardShenhesuDirector.jsp"><i class="icon icon-th-list"></i> <span>查看进场木材反馈</span></a></li>
    <li><a href="yardinfo.jsp"><i class="icon icon-home"></i> <span>新增货场信息</span></a></li>
    <li><a href="SurveyorAddinfo.jsp"><i class="icon icon-home"></i> <span>新增检尺员信息</span></a></li>
   <li><a href="cancellingStocksTable.jsp"><i class="icon icon-home"></i> <span>货场报损</span></a> </li>
   <li><a href="yardMoneryDirector.jsp"><i class="icon icon-home"></i><span>货场费用报表</span></a> </li>
   <li><a href="saleCalloutOrderShenhe.jsp"><i class="icon icon-share-alt"></i><span>审批调令</span></a></li>
  </ul>
</div>
<!--sidebar-menu-->
   <div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"> <a href="yardDirector.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> 首页</a></div>
  </div>
    <p class="table_p"><span>检尺员信息列表</span></p>
<div class="table-con">
    <table id="table1" class="table-style"></table>
</div>
  <p style="font-size:28px;color:blue;border-bottom: 3px #42cdec solid;display: inline-block">新增检尺员信息： <a href = "JavaScript:void(0)"><button class="btn btn-xs btn-primary" type="button" onclick = "openDialog()" value="添加检尺员名单">添加检尺员名单</button></a></p> 
<div id="light" class="white_content">
   <a style="float:right" href = "javascript:void(0)" onclick = "closeDialog()">关闭</a>
<form onSubmit="return inputNull(this)" action="workpageSevrlet?action=yardAdd" method="POST">
 <table class="table2" id="tablesec">
 <caption class="book_h01">新增检尺员名单</caption>
      <tbody id="ttt5" style="font-size:20pt">
                                
      </tbody>
</table>
<div>
       <button class="btn btn-warning" type="button"  onclick="addSection()" value="添加">添加</button>
       <button class="btn btn-warning" type="button"  onclick="dddelete()" value="删除">删除</button>
      <button class="btn btn-warning" type="button"  onclick="deleteAll()" value="清空">清空</button>
       </div>
 <div class=" but_p" style="float:center;">
  <button class="but_save" type="button" onclick="surveyorAdd()" value="保存名单">保存名单</button>
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
            url: "workpageSevrlet?action=surveyorList",
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
                    field: 'surveyorid',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '姓名',
                    field: 'surveyor',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<button type="button" data-id="98"  class="btn btn-xs btn-primary" onclick="overtimedelete(\''+ row.surveyorid + '\')">删除</button> ';
                        //var  d = '<a href="workpageSevrlet?action=alldelete&workid=\''+ row.workid + '\'"><button  id="id="delete" data-id="98" class="btn btn-xs btn-primary">删除</button></a>';
                        return  d;
                    }
                },
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
    <script type="text/javascript">
    function overtimedelete(surveyorid)
    {
		$.ajax({
            url:"workpageSevrlet",
            data:{
                "action":"deteSurv",
                "surveyorid":surveyorid,
            },
            type: "Post",
            dataType:"json",
            success: function (data) {
            	if(data)
            		{
            		alert("删除成功！");
            		$("#table1").bootstrapTable('refresh');
            		
            		}
            		
            	else
            		alert("删除失败！");
            }
        })
    }
    </script>
</html>