<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>打印木材销售码单</title>
<!-- Custom CSS -->
<link rel="stylesheet" href="css/registe.css"/>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link href="https://unpkg.com/bootstrap-table@1.18.0/dist/bootstrap-table.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/fullcalendar.css" />
<link rel="stylesheet" href="css/matrix-style.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="css/jquery.gritter.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
<link type="text/css" rel="stylesheet" href="css/PrintArea.css" />

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

</script>
</head>
<body>
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
    <li class="submenu"> <a href="#"><i class="icon icon-signal"></i> <span>客户信息管理</span> <span class="label label-important">2</span></a>
     <ul>
        <li><a href="productowner.jsp">创建客户信息</a></li>
        <li><a href="productownerSee.jsp">查看客户信息</a></li>
      </ul>
     </li>
     <li class="submenu"> <a href="#"><i class="icon icon-inbox"></i> <span>销售调令管理</span> <span class="label label-important">3</span></a>
     <ul>
        <li><a href="saleCalloutOrder.jsp">录入销售调令</a></li>
        <li><a href="saleCalloutOrdersee.jsp">查看调令材料</a></li>
        <li><a href="saleCalloutOrderShenheModer.jsp">查看调令审核</a></li>
      </ul>
     </li>
     <li class="submenu"> <a href="#"><i class="icon icon-th"></i> <span>销售结算</span> <span class="label label-important">2</span></a>
     <ul>
<!--         <li><a href="outyardCostS.jsp">结算检尺费</a></li> -->
        <li><a href="treeoutPrice.jsp">木材销售结算单</a></li>
        <li><a href="treeoutPriceTable.jsp">木材销售结算台账</a></li>
      </ul>
     </li>
    <li class="active"><a href="treeoutCodepage.jsp"><i class="icon icon-fullscreen"></i> <span>打印销售码单</span></a></li>
    <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>库存与销售</span> <span class="label label-important">2</span></a>
     <ul>
        <li><a href="producetreeTableSalaryper.jsp">木材进仓库存</a></li>
        <li><a href="treeoutTableSalayper.jsp">木材出仓销售</a></li>
      </ul>
     </li>
  </ul>
</div>
<!--sidebar-menu-->

<!--main-container-part-->
<div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"> <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> 首页</a></div>
  </div>
<main class="all">
    <article class="artlce">
    <div>
    <div class="find-top">
        <p class="p-tail"><i class="i-tail"></i> 请选择销售码单进行打印。</p>
    </div>
    <div class="table-con">
    <div align="center"><h4 style="color:black;font-weight:bold">销售码单</h4></div>
        <table id="table1" class="table-style"></table>
    </div>
   </div> 
</article>
</main>
</div>
<script src="js/jquery.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
<script>
    $(function(){
        table1();
    })
    function table1()
    {
    	$('#table1').bootstrapTable('destroy');
    	$('#table1').bootstrapTable({
    	    theadClasses: "thead-blue",//设置thead-blue为表头样式
    	    classes: "table table-bordered table-striped table-sm table-dark", 
            method: "post",
            striped: false,
            singleSelect: false,
            cache: false,//缓存
            url: "treeServlet?action=treeoutCodepage",
            dataType: "json",
            pagination: true, //分页
            pageSize: 10,
            pageNumber: 1,
            search:true, //显示搜索框
            showColumns: true,                  //是否显示所有的列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            showRefresh: true,                  //是否显示刷新按钮
            contentType: "application/x-www-form-urlencoded",
            exportDataType:'all',//'basic':当前页的数据, 'all':全部的数据, 'selected':选中的数据    
            showExport: true,  //是否显示导出按钮    
            buttonsAlign:"right",  //按钮位置    
            exportTypes:['excel','xlsx','csv'],  //导出文件类型，[ 'csv', 'txt', 'sql', 'doc', 'excel', 'xlsx', 'pdf']
            exportOptions:{
                // ignoreColumn: [0,1],  //忽略某一列的索引
                fileName: "木材销售码单",  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: "木材销售码单",
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight'], //设置格式
            },
            columns: [
            	{						
						title: '序号',
						width: 100,
                    align: 'center',
						formatter: function (value, row, index) {
							return index+1;
						}
	                },//该列显示序号，分页不是从1开始
                {
                    title: "出场时间",
                    field: 'yarddate',
                    width:'200px',
                    align: 'center',
                    sortable: true,
                    valign: 'middle'
                },
                {
                    title: "销售码单号",
                    field: 'workid',
                    width:'200px',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: "供货单位",
                    width:'200px',
                    field: 'provider',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '购货人',
                    width:'200px',
                    field: 'demander',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '合同号',
                    width:'200px',
                    field: 'contractnum',
                    align: 'center',
                    valign: 'middle'
                },
            	{
                title: "调令号",
                field: 'saleCalloutorderid',
                width:'200px',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '检验地点(货场)',
                width:'200px',
                field: 'yard',
                align: 'center',
                valign: 'middle'
            },
            {
                title: "合计数量",
                field: 'toltree',
                width:'200px',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '合计材积',
                field: 'tolstere',
                width:'200px',
                align: 'center',
                valign: 'middle'
            },
                {
                    title: '打印码单',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<a href="treeServlet?action=seetreeout1&saleCalloutorderid=\''+ row.saleCalloutorderid + '&yarddate=\''+ row.yarddate + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">打印销售码单</button></a>';
                        //var  d = '<a href="workpageSevrlet?action=alldelete&workid=\''+ row.workid + '\'"><button  id="id="delete" data-id="98" class="btn btn-xs btn-primary">删除</button></a>';
                        return  d;
                    }
                },
            ]
        });
    }
    </script>
    <script type="text/javascript">
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
</body>
</html>