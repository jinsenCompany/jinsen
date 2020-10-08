<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查看采伐证使用情况</title>
<link rel="stylesheet" href="css/tableall.css"/>
<link rel="stylesheet" href="css/registe.css"/>
<link href="https://unpkg.com/bootstrap-table@1.18.0/dist/bootstrap-table.min.css" rel="stylesheet">
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
    background-image: url(../img/listicon.jpg);
    top: 1px;
}
</style>
</head>
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
  <li>
  <%
	String staff_id = request.getSession().getAttribute("staff_id").toString();
				%> <%
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
  <ul>
  <!-- 
    <li> <a href="cutnumApplysee.jsp"><i class="icon icon-home"></i> <span>审核采伐证申请</span></a> </li> 
  --> 
    <li> <a href="manageCutnum.jsp"><i class="icon icon-th"></i> <span>录入采伐证</span></a></li>
    <li> <a href="manageCutnumsee.jsp"><i class="icon icon-th"></i> <span>采伐证汇总</span></a></li>
    <li> <a href="manageCutnumseeDelate.jsp"><i class="icon icon-th"></i> <span>采伐证退证</span></a></li>
    <li   class="active"> <a href="manageCutnumseeUse.jsp"><i class="icon icon-th"></i> <span>采伐证使用情况</span></a></li>   
    
<!-- 
    <li> <a href="cutnumTable.jsp"><i class="icon icon-th-list"></i> <span>采伐证报表</span></a></li>
  
   -->
  </ul>
</div>
<!--sidebar-menu-->

<div id="content">
<div id="content-header">
    <div id="breadcrumb"> <a href="managerP.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a></div>
  </div>
<!--End-breadcrumbs-->

<div class="find-top">
     <p class="p-tail"><i class="i-tail"></i> 该页面是采伐证汇总界面</p>
</div>
 <div class="table-con">
        <table id="table1" class="table table-bordered"></table>
</div>
</div>
</body>
<script src="js/jquery.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>

<script>
$(function(){
	printCutnum();
})
    function printCutnum(){
    	$('#table1').bootstrapTable('destroy');
        $('#table1').bootstrapTable({
            method: "post",
            striped: true,
            singleSelect: false,
            url: "cutnumServlet?action=printCutnum4",
            //data:{},
            dataType: "json",
            pagination: true, //分页
            pageSize: 15,
            pageNumber: 1,
            search:true, //显示搜索框
            showColumns: true,                  //是否显示所有的列
            //showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                      //是否显示详细视图
            showRefresh: true,                  //是否显示刷新按钮
            contentType: "application/x-www-form-urlencoded",
            exportDataType:'all',//'basic':当前页的数据, 'all':全部的数据, 'selected':选中的数据    
            showExport: true,  //是否显示导出按钮    
            buttonsAlign:"right",  //按钮位置    
            exportTypes:['excel','xlsx','csv','pdf'],
            exportOptions:{
                // ignoreColumn: [0,1],  //忽略某一列的索引
                fileName: "采伐证信息",  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: "采伐证信息",
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight'], //设置格式
            },
            /*queryParams:function queryParams(params){
                var temp = {
						action:"printCutnum",
						cutnum:document.getElementById("cutnum").value,
				};     
                return temp;
            },*/
            columns: [
            	[
            	{
            		"title": "采伐证信息",
                    "font-size":"100px",
                    "halign":"center",
                    "align":"center",
                    "valign": "middle",
                    "colspan": 27
                }],
                [

                	{						
 						title: '序号',
 						width: 100,
 						rowspan: 2,
                        align: 'center',
                        valign: 'middle',
 						formatter: function (value, row, index) {
 							return index+1;
 						}
 	                },//该列显示序号，分页不是从1开始
 	               {
 	                    title: '创建时间',
 	                    width: 180,
 	                    rowspan: 2,
 	                    field: 'creatcutDate',
 	                    align: 'center',
 	                    valign: 'middle'
 	                },
                	{
                        title: '林权单位',
                        field: 'company',
                        width: 180,
                        rowspan: 2,
                        align: 'center',
                        valign: 'middle'
                    },
                    {
                        title: '采伐证号',
                        width: 180,
                        rowspan: 2,
                        field: 'certificatenum',
                        align: 'center',
                        valign: 'middle'
                    },
                    {
                        title: '采伐证编号',
                        width: 180,
                        rowspan: 2,
                        field: 'cutnum',
                        align: 'center',
                        valign: 'middle'
                    },        	
                {
                    title: '乡镇',
                    field: 'cutaddress',
                    width: 180,
                    rowspan: 2,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '村',
                    field: 'cutvillage',
                    width: 180,
                    rowspan: 2,
                    align: 'center',
                    valign: 'middle'
                },
               
                {
                	title: '设计出材量',
                    colspan: 4,
                    align: 'center'
                },
                /*
                {
                	title: '已生产量',
                    colspan: 4,
                    align: 'center'
                },
                */
                {
                	title: '已出材量',
                    colspan: 4,
                    align: 'center'
                },
                {
                	title: '未出材量',
                    colspan: 4,
                    align: 'center'
                },
                {
                	title: '采伐证使用情况',
                    colspan: 4,
                    align: 'center'
                }, 
                {
                	title: '操作',
                    colspan: 4,
                    align: 'center'
                }, 
                ],
                [               
                {                                  //设计出材量
                    title: '合计',
                    field: 'totalDesign',
                    align: 'center',
                    valign: 'middle'
                },
          	  {
                    title: '杉木',
                    field: 'shanTreeDesign',
                    align: 'center',
                    valign: 'middle'
                },  
          	  {
                    title: '松木',
                    field: 'songTreeDesign',
                    align: 'center',
                    valign: 'middle'
                }, 
                {
                    title: '杂木',
                    field: 'zaTreeDesign',
                    align: 'center',
                    valign: 'middle'
                },                	 
                /* 
                {
                    title: '合计',              //已生产量
                    field: 'totalProduced',
                    align: 'center',
                    valign: 'middle'
                },                	                 	  
                {
                    title: '杉木',
                    field: 'shamu',
                    align: 'center',
                    valign: 'middle'
                },                	  
                {
                    title: '松木',
                    field: 'songmu',
                    align: 'center',
                    valign: 'middle'
                },                	  
                {
                    title: '杂木',
                    field: 'zamu',
                    align: 'center',
                    valign: 'middle'
                },                	  
                */
                {
                    title: '合计',            //实际出材量
                    field: 'totalActual',
                    align: 'center',
                    valign: 'middle'
                },                	  
                {
                    title: '杉木',
                    field: 'shanTreeActual',
                    align: 'center',
                    valign: 'middle'
                },                	  
                {
                    title: '松木',
                    field: 'songTreeActual',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '杂木',
                    field: 'zaTreeActual',
                    align: 'center',
                    valign: 'middle'
                },                	
            
                {                            //剩余量
                    title: '合计',
                    field: 'totalSurplus',
                    align: 'center',
                    valign: 'middle'
                },                	  
                {
                    title: '杉木',
                    field: 'shanSurplus',
                    align: 'center',
                    valign: 'middle'
                },                	  
                {          	  
                    title: '松木',
                    field: 'songSurplus',
                    align: 'center',
                    valign: 'middle'
                },                	  
                {
                    title: '杂木',
                    field: 'zaSurplus',
                    align: 'center',
                    valign: 'middle'
                },   
                {                             //采伐证使用情况
                    title: '合计',
                    field: 'totalImplement',
                    align: 'center',
                    valign: 'middle'
                },                	  
                {
                    title: '杉木',
                    field: 'shanTreeImplement',
                    align: 'center',
                    valign: 'middle'
                },                	  
                {
                    title: '松木',
                    field: 'songTreeImplement',
                    align: 'center',
                    valign: 'middle'
                },                	 
                {
                    title: '杂木',
                    field: 'zaTreeImplement',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '查看',
                    field: 'opr',
                		rowspan: 1,
                    width: 100,
                    align: 'center',
                    valign: 'middle',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<a href="cutnumServlet?action=watch&cutnum=\''+ row.cutnum + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        //var  d = '<a href="workpageSevrlet?action=alldelete&workid=\''+ row.workid + '\'"><button  id="id="delete" data-id="98" class="btn btn-xs btn-primary">删除</button></a>';
                        return  d;
                    }
                }, 
//                 {
//                 	title: '更新',
//                     field: 'opr',
//                     width: 100,
//                     rowspan: 1,
//                     align: 'center',
//                     valign: 'middle',
//                     formatter: function (cellval, row) {
//                         //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
//                         //var  d = '<a href="cutnumServlet?action=watch&cutnum=\''+ row.cutnum + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
//                         var  d = '<a href="cutnumServlet?action=watchUpdatec&cutnum=\''+ row.cutnum + '\'"><button  id="id="delete" data-id="98" class="btn btn-xs btn-primary">更新</button></a>';
//                         return  d;
//                     }
//                 },
//                 {
//                     title: '删除',
//                     field: 'opr',
//                     width: 100,
//                     rowspan: 1,
//                     align: 'center',
//                         valign: 'middle',
//                     formatter: function (cellval, row) {
//                         //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
//                         var  d = '<button type="button" data-id="98"  class="btn btn-xs btn-primary" onclick="overtimedelete(\''+ row.cutnum + '\')">删除</button> ';
//                         //var  d = '<a href="workpageSevrlet?action=alldelete&workid=\''+ row.workid + '\'"><button  id="id="delete" data-id="98" class="btn btn-xs btn-primary">删除</button></a>';
//                         return  d;
//                     }
//                 }
                ],    

           	
            ]
        });
    }
function overtimedelete(cutnum)
{
	$.ajax({
        url:"cutnumServlet",
        data:{
            "action":"alldelete1",
            "cutnum":cutnum,
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
  <script src="js/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
<script src="js/bootstrap-table-export.js"></script>
<script src="js/tableExport.js"></script>
<script src="js/jquery.base64.js"></script>
<script type="text/javascript" src="/static/js/xlsx.full.min.js"></script>
<script src="https://unpkg.com/tableexport.jquery.plugin/libs/jsPDF/jspdf.min.js"></script>
<script src="https://unpkg.com/tableexport.jquery.plugin/libs/jsPDF-AutoTable/jspdf.plugin.autotable.js"></script>
<script src="js/matrix.js"></script> 
<script src="js/matrix.dashboard.js"></script>   
</html>