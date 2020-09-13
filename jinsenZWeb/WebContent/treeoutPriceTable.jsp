<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
<title>(销售部门)木材销售货款结算台账</title>
<link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/tableall.css">
    <link rel="stylesheet" href="js/jQueryCalendar/calendar.css">
    <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />

    <!-- font-awesome icons CSS -->
    <link href="css/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons CSS-->

    <link href='css/SidebarNav.min.css' media='all' rel='stylesheet' type='text/css'/>
    <!-- js-->
    <script src="js/jquery-1.11.1.min.js"></script>
    <script src="js/modernizr.custom.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/fullcalendar.css" />
<link rel="stylesheet" href="css/matrix-style.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="css/jquery.gritter.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
<link type="text/css" rel="stylesheet" href="css/PrintArea.css" />
    <style type="text/css">
        .table_p{line-height: 28px;border-bottom: 1px #d0e6ec solid;position: relative;margin-bottom: 10px;
            margin-top: 35px;}
        .table_p span{border-bottom: 3px #42cdec solid;display: inline-block;position: absolute;bottom: -1px;font-weight: bold;font-size: 20px}
        .but_p button{width: 80px}
        #h li{float: left; }
#h a{font-size:15px;width: 230px; height: 30px;padding: 10px 0;text-align: center;  background: #3c763d; display: block; text-decoration:none; color:white}
#h a:hover{color:white;background: green}
.table1{width:90%; height:auto;margin-left:auto; margin-right:auto;padding:10px;border-collapse:collapse}
    </style>
</head>
<body>
<div id="header">
  <h1><a href="dashboard.html">销售管理平台</a></h1>
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
				%> <%
 	String staff_name = request.getSession().getAttribute("staff_name").toString();
 %> 您好，<%=staff_id%> <%=staff_name%>欢迎登录
 </li>
  </ul>
</div>
<!--close-top-Header-menu-->

<!--sidebar-menu-->
<div id="sidebar"><a href="#" class="visible-phone"><i class="icon icon-home"></i> 仪表盘</a>
    <ul>
  <li class="submenu"> <a href="#"><i class="icon icon-home"></i> <span>合同管理</span> <span class="label label-important">2</span></a>
     <ul>
        <li><a href="salaryContract.jsp">创建合同</a></li>
        <li><a href="salaryContractList.jsp">合同进度</a></li>
      </ul>
     </li>
    <li class="submenu"> <a href="#"><i class="icon icon-home"></i> <span>客户信息管理</span> <span class="label label-important">2</span></a>
     <ul>
        <li><a href="productowner.jsp">创建客户信息</a></li>
        <li><a href="productownerSee.jsp">查看客户信息</a></li>
      </ul>
     </li>
     <li class="submenu"> <a href="#"><i class="icon icon-home"></i> <span>销售调令管理</span> <span class="label label-important">3</span></a>
     <ul>
        <li><a href="saleCalloutOrder.jsp">录入销售调令</a></li>
        <li><a href="saleCalloutOrdersee.jsp">查看调令材料</a></li>
        <li><a href="saleCalloutOrderShenheModer.jsp">查看调令审核</a></li>
      </ul>
     </li>
     <li class="submenu"> <a href="#"><i class="icon icon-home"></i> <span>销售结算</span> <span class="label label-important">3</span></a>
     <ul>
        <li><a href="outyardCostS.jsp">结算检尺费</a></li>
        <li><a href="treeoutPrice.jsp">木材销售结算单</a></li>
        <li><a href="treeoutPriceTable.jsp">木材销售结算台账</a></li>
      </ul>
     </li>
    <li><a href="treeoutCodepage.jsp"><i class="icon icon-th-list"></i> <span>打印销售码单</span></a></li>
    <li class="submenu"> <a href="#"><i class="icon icon-home"></i> <span>库存与销售</span> <span class="label label-important">2</span></a>
     <ul>
        <li><a href="producetreeTableSalaryper.jsp">木材进仓库存</a></li>
        <li><a href="treeoutTableSalayper.jsp">木材出仓销售</a></li>
      </ul>
     </li>
  </ul>
</div>
<!--sidebar-menu-->
<div id="content">
  <div id="content-header">
    <div id="breadcrumb"> <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a></div>
  </div>
  <div class="table-con">
        <table id="table1" class="table-style"></table>
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
        	theadClasses: "thead-blue",//设置thead-blue为表头样式
    	    classes: "table table-bordered table-striped table-sm table-dark", 
            method: "post",
            striped: true,
            singleSelect: false,
            url: "salaryServlet?action=printTreeoutsalary",
            //data:{},
            dataType: "json",
            pagination: true, //分页
            pageSize: 15,
            pageNumber: 1,
            search:true, //显示搜索框
            showColumns: true,                  //是否显示所有的列
            //showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            showRefresh: true,                  //是否显示刷新按钮
            contentType: "application/x-www-form-urlencoded",
            exportDataType:'all',//'basic':当前页的数据, 'all':全部的数据, 'selected':选中的数据    
            showExport: true,  //是否显示导出按钮    
            buttonsAlign:"right",  //按钮位置    
            exportTypes:['excel','xlsx','csv','pdf'],
            exportOptions:{
                // ignoreColumn: [0,1],  //忽略某一列的索引
                fileName: "木材销售货款结算台账",  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: "木材销售货款结算台账",
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight'], //设置格式
            },
//             onLoadSuccess : function(data) {        
//                 var data = $('#table1').bootstrapTable('getData', true);
//                 //合并单元格
//                 mergeCells(data, "projectPackageName", 1, $('#table1'));
//                 mergeCells(data, "cutnum", 1, $('#table1'));
//                 mergeCells(data, "forperson", 1, $('#table1'));
//                 mergeCells(data, "company", 1, $('#table1'));
//                 mergeCells(data, "yard", 1, $('#table1'));
             
//             },
            columns: [[
            	{
            		"title": "木材销售货款结算台账",
                    "font-size":"100px",
                    "halign":"center",
                    "align":"center",
                    "valign": "middle",
                    "colspan": 14
                }],
                [
                	{						
 						title: '序号',
 						width: 100,
                        align: 'center',
 						formatter: function (value, row, index) {
 							return index+1;
 						}
 	                },//该列显示序号，分页不是从1开始
 	               {
 	                    title: '结算时间',
 	                    width: 180,
 	                    field: 'creatTime',
 	                    align: 'center',
 	                    valign: 'middle'
 	                },
                	{
                        title: '合同编号',
                        field: 'contractnum',
                        width: 180,
                        align: 'center',
                        valign: 'middle'
                    },
                    {
                        title: '调令号',
                        width: 180,
                        field: 'saleCalloutOrder',
                        align: 'center',
                        valign: 'middle'
                    },
                    {
                        title: '收货单位',
                        width: 180,
                        field: 'consignee',
                        align: 'center',
                        valign: 'middle'
                    },
                {
                    title: '供货单位',
                    field: 'company',
                    width: 180,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '供货地点',
                    field: 'yard',
                    width: 180,

                    align: 'center',
                    valign: 'middle'
                },
//                 {
//                     title: '树种',
//                     width: 180,
//                     field: 'treetype',
//                     align: 'center',
//                     valign: 'middle'
//                 },
//                 {
//                     title: '长度',
//                     width: 180,

//                     field: 'tlong',
//                     align: 'center',
//                     valign: 'middle'
//                 },
//                 {
//                     title: "径级",
//                     width: 180,

//                     field: 'tradius',
//                     align: 'center',
//                     valign: 'middle'
//                 },
                {
                    title: '总根数',
                    width: 180,
                    field: 'tnum',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '总材积',
                    width: 180,
                    field: 'ttvolume',
                    align: 'center',
                    valign: 'middle'
                },
//                 {
//                     title: '销售单价',
//                     width: 180,
//                     field: 'unitprice',
//                     align: 'center',
//                     valign: 'middle'
//                 },
                {
                    title: '总金额',
                    width: 180,
                    field: 'tprice',
                    align: 'center',
                    valign: 'middle'
                },
                
               {
                    title: '查看',
                    field: 'opr',
                    width: 100,
                    align: 'center',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<a href="salaryServlet?action=watchTreeoutPrice&creatTime=\''+ row.creatTime + '\'&contractnum=\''+ row.contractnum + '\'&saleCalloutOrder=\''+ row.saleCalloutOrder + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        //var  d = '<a href="workpageSevrlet?action=alldelete&workid=\''+ row.workid + '\'"><button  id="id="delete" data-id="98" class="btn btn-xs btn-primary">删除</button></a>';
                        return  d;
                    }
                }, 
                /*{
                	title: '更新',
                    field: 'opr',
                    width: 100,
                    align: 'center',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        //var  d = '<a href="cutnumServlet?action=watch&cutnum=\''+ row.cutnum + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<a href="cutnumServlet?action=watchUpdatec&cutnum=\''+ row.cutnum + '\'"><button  id="id="delete" data-id="98" class="btn btn-xs btn-primary">更新</button></a>';
                        return  d;
                    }
                },*/
                ],
            ]
        });
    }

    </script>
    <script type="text/javascript">
    /**
     * 合并单元格
     * @param data 原始数据（在服务端完成排序）
     * @param fieldName 合并属性名称
     * @param colspan 合并列
     * @param target 目标表格对象
     */
    function mergeCells(data,fieldName,colspan,target){
     //声明一个map计算相同属性值在data对象出现的次数和
     var sortMap = {};
     for(var i = 0 ; i < data.length ; i++){
      for(var prop in data[i]){
       if(prop == fieldName){
        var key = data[i][prop]
        if(sortMap.hasOwnProperty(key)){
         sortMap[key] = sortMap[key] * 1 + 1;
        } else {
         sortMap[key] = 1;
        }
        break;
       } 
      }
     }
     for(var prop in sortMap){
      console.log(prop,sortMap[prop])
     }
     var index = 0;
     for(var prop in sortMap){
      var count = sortMap[prop] * 1;
      $(target).bootstrapTable('mergeCells',{index:index, field:fieldName, colspan: colspan, rowspan: count}); 
      index += count;
     }
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