<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Connection" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
   <title>工程包管理台账</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/tableall.css">
    <link rel="stylesheet" href="css/registe.css"/>
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
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/fullcalendar.css" />
<link rel="stylesheet" href="css/matrix-style.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="css/jquery.gritter.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
<link href="https://unpkg.com/bootstrap-table@1.17.1/dist/bootstrap-table.min.css" rel="stylesheet">

<script src="https://unpkg.com/tableexport.jquery.plugin/tableExport.min.js"></script>
<script src="https://unpkg.com/bootstrap-table@1.17.1/dist/bootstrap-table.min.js"></script>
<script src="https://unpkg.com/bootstrap-table@1.17.1/dist/bootstrap-table-locale-all.min.js"></script>
<script src="https://unpkg.com/bootstrap-table@1.17.1/dist/extensions/export/bootstrap-table-export.min.js"></script>

<style>
  .select,
  #locale {
    width: 100%;
  }
  .like {
    margin-right: 10px;
  }
</style>
<style>
.table1{width:70%; height:400px;margin-left:auto; margin-right:auto;padding:10px;border-collapse:collapse}
td,th{text-align:center;vertical-align:middle}
         tr{text-align:center}
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
.htv {
    float:left;font-weight:bold;color:#966f2b;text-decoration: underline;
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
  <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>施工方管理</span> <span class="label label-important">2</span></a>
     <ul>
        <li><a href="managesdatecard.jsp">录入施工方资料</a></li>
        <li><a href="managersdatecardSee.jsp">施工方台账</a></li>
      </ul>
     </li>
     <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>工程包管理</span> <span class="label label-important">3</span></a>
     <ul>
        <li><a href="CutnumProjectpackage.jsp">创建工程包</a></li>
        <li><a href="CutnumProjectpackageTable.jsp">工程包台账</a></li>
        <li><a href="cutareaAllot.jsp">伐区拨交</a></li>
      </ul>
     </li>
    <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>工单管理</span> <span class="label label-important">2</span></a>
       <ul>
        <li><a href="workpageAdd.jsp">输入工单</a></li>
        <li><a href="workpageShenheFaqu.jsp">审核工单</a></li>
      </ul>
     </li>
    <li> <a href="manageCutnumCheck.jsp"><i class="icon icon-inbox"></i> <span>生产管理</span></a> </li>
    <li><a href="productPrice.jsp"><i class="icon icon-th"></i> <span>生产结算</span></a></li>   
  </ul>
</div>
<!--sidebar-menu-->

<div id="content">
<div id="content-header">
    <div id="breadcrumb"> <a href="forestP.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> 首页</a></div>
  </div>
<!--End-breadcrumbs-->
<main>
    <div class="find-top">
        <p class="p-tail"><i
         class="i-tail"></i> 该页面主要是查看工程包管理台帐</p>
    </div>
    <div class="find-top1">
    <div class="top" id="divprint">
  
    <table style="margin:auto">
       <tr>
      
       </tr>
    </table>
    <!-- 显示最近新增的采伐证号 -->
    
    <div class="table-con">
        <table id="table1" class="table table-bordered"></table>
    </div>
    
    </div>
    <div class=" but_p" style="float:right;">
    <!--  <button class="but_save" type="submit" id="mybutton" value="保存工程包">保存工程包</button></div>
    <div class=" but_p" style="float:left;"><input style="float:right;" type="button" id="btnPrint" value="打印"/></div>-->
    <div style="clear: both;padding-bottom: 40px"></div>
    </div>
    </div>

</main>
<div class="select">
  <select class="form-control" id="locale">
    <option value="af-ZA">af-ZA</option>
    <option value="ar-SA">ar-SA</option>
    <option value="ca-ES">ca-ES</option>
    <option value="cs-CZ">cs-CZ</option>
    <option value="da-DK">da-DK</option>
    <option value="de-DE">de-DE</option>
    <option value="el-GR">el-GR</option>
    <option value="en-US" selected>en-US</option>
    <option value="es-AR">es-AR</option>
    <option value="es-CL">es-CL</option>
    <option value="es-CR">es-CR</option>
    <option value="es-ES">es-ES</option>
    <option value="es-MX">es-MX</option>
    <option value="es-NI">es-NI</option>
    <option value="es-SP">es-SP</option>
    <option value="et-EE">et-EE</option>
    <option value="eu-EU">eu-EU</option>
    <option value="fa-IR">fa-IR</option>
    <option value="fi-FI">fi-FI</option>
    <option value="fr-BE">fr-BE</option>
    <option value="fr-FR">fr-FR</option>
    <option value="he-IL">he-IL</option>
    <option value="hr-HR">hr-HR</option>
    <option value="hu-HU">hu-HU</option>
    <option value="id-ID">id-ID</option>
    <option value="it-IT">it-IT</option>
    <option value="ja-JP">ja-JP</option>
    <option value="ka-GE">ka-GE</option>
    <option value="ko-KR">ko-KR</option>
    <option value="ms-MY">ms-MY</option>
    <option value="nb-NO">nb-NO</option>
    <option value="nl-NL">nl-NL</option>
    <option value="pl-PL">pl-PL</option>
    <option value="pt-BR">pt-BR</option>
    <option value="pt-PT">pt-PT</option>
    <option value="ro-RO">ro-RO</option>
    <option value="ru-RU">ru-RU</option>
    <option value="sk-SK">sk-SK</option>
    <option value="sv-SE">sv-SE</option>
    <option value="th-TH">th-TH</option>
    <option value="tr-TR">tr-TR</option>
    <option value="uk-UA">uk-UA</option>
    <option value="ur-PK">ur-PK</option>
    <option value="uz-Latn-UZ">uz-Latn-UZ</option>
    <option value="vi-VN">vi-VN</option>
    <option value="zh-CN">zh-CN</option>
    <option value="zh-TW">zh-TW</option>
  </select>
</div>

<div id="toolbar">
  <button id="remove" class="btn btn-danger" disabled>
    <i class="fa fa-trash"></i> Delete
  </button>
</div>
<table
  id="table"
  data-toolbar="#toolbar"
  data-search="true"
  data-show-refresh="true"
  data-show-toggle="true"
  data-show-fullscreen="true"
  data-show-columns="true"
  data-show-columns-toggle-all="true"
  data-detail-view="true"
  data-show-export="true"
  data-click-to-select="true"
  data-detail-formatter="detailFormatter"
  data-minimum-count-columns="2"
  data-show-pagination-switch="true"
  data-pagination="true"
  data-id-field="id"
  data-page-list="[10, 25, 50, 100, all]"
  data-show-footer="true"
  data-side-pagination="server"
  data-url="https://examples.wenzhixin.net.cn/examples/bootstrap_table/data"
  data-response-handler="responseHandler">
</table>

<script>
  var $table = $('#table')
  var $remove = $('#remove')
  var selections = []

  function getIdSelections() {
    return $.map($table.bootstrapTable('getSelections'), function (row) {
      return row.id
    })
  }

  function responseHandler(res) {
    $.each(res.rows, function (i, row) {
      row.state = $.inArray(row.id, selections) !== -1
    })
    return res
  }

  function detailFormatter(index, row) {
    var html = []
    $.each(row, function (key, value) {
      html.push('<p><b>' + key + ':</b> ' + value + '</p>')
    })
    return html.join('')
  }

  function operateFormatter(value, row, index) {
    return [
      '<a class="like" href="javascript:void(0)" title="Like">',
      '<i class="fa fa-heart"></i>',
      '</a>  ',
      '<a class="remove" href="javascript:void(0)" title="Remove">',
      '<i class="fa fa-trash"></i>',
      '</a>'
    ].join('')
  }

  window.operateEvents = {
    'click .like': function (e, value, row, index) {
      alert('You click like action, row: ' + JSON.stringify(row))
    },
    'click .remove': function (e, value, row, index) {
      $table.bootstrapTable('remove', {
        field: 'id',
        values: [row.id]
      })
    }
  }

  function totalTextFormatter(data) {
    return 'Total'
  }

  function totalNameFormatter(data) {
    return data.length
  }

  function totalPriceFormatter(data) {
    var field = this.field
    return '$' + data.map(function (row) {
      return +row[field].substring(1)
    }).reduce(function (sum, i) {
      return sum + i
    }, 0)
  }

  function initTable() {
    $table.bootstrapTable('destroy').bootstrapTable({
      height: 550,
      locale: $('#locale').val(),
      columns: [
        [{
          field: 'state',
          checkbox: true,
          rowspan: 2,
          align: 'center',
          valign: 'middle'
        }, {
          title: 'Item ID',
          field: 'id',
          rowspan: 2,
          align: 'center',
          valign: 'middle',
          sortable: true,
          footerFormatter: totalTextFormatter
        }, {
          title: 'Item Detail',
          colspan: 3,
          align: 'center'
        }],
        [{
          field: 'name',
          title: 'Item Name',
          sortable: true,
          footerFormatter: totalNameFormatter,
          align: 'center'
        }, {
          field: 'price',
          title: 'Item Price',
          sortable: true,
          align: 'center',
          footerFormatter: totalPriceFormatter
        }, {
          field: 'operate',
          title: 'Item Operate',
          align: 'center',
          clickToSelect: false,
          events: window.operateEvents,
          formatter: operateFormatter
        }]
      ]
    })
    $table.on('check.bs.table uncheck.bs.table ' +
      'check-all.bs.table uncheck-all.bs.table',
    function () {
      $remove.prop('disabled', !$table.bootstrapTable('getSelections').length)

      // save your data, here just save the current page
      selections = getIdSelections()
      // push or splice the selections if you want to save all data selections
    })
    $table.on('all.bs.table', function (e, name, args) {
      console.log(name, args)
    })
    $remove.click(function () {
      var ids = getIdSelections()
      $table.bootstrapTable('remove', {
        field: 'id',
        values: ids
      })
      $remove.prop('disabled', true)
    })
  }

  $(function() {
    initTable()

    $('#locale').change(initTable)
  })
</script>
</div>
<script src="js/jquery.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
<script>
    /*$(function(){
    	table1();
    })*/
    var state = "loadDate";
    var StartSearch =null;
	var EndSearch =null;
	var mPage =1;
    var TableInit=function table1(){
    	var oTableInit = new Object();
    	oTableInit.Init=function table1(){
            $('#table1').bootstrapTable({
                method: "get",
                striped: true,
                singleSelect: false,
                url: "cutnumServlet?action=findCutnumproject34",
                data:{},
              //data:{},
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
                exportTypes:['excel','xlsx','csv','pdf'],
                exportOptions:{
                    // ignoreColumn: [0,1],  //忽略某一列的索引
                    fileName: "工程包台账",  //文件名称设置
                    worksheetName: 'sheet1',  //表格工作区名称
                    tableName: "工程包台账",
                    excelstyles: ['background-color', 'color', 'font-size', 'font-weight'], //设置格式
                },
            columns: [[
            	{
            		"title": "工程包管理台账",
                    "font-size":"100px",
                    "halign":"center",
                    "align":"center",
                    "valign": "middle",
                    "colspan": 32
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
 	                    title: '工程包创建时间',
 	                    field: 'projpackageStarttime',
 	                    width:'200',
 	                   rowspan: 2,
 	                    align: 'center',
 	                    valign: 'middle'
 	                },
                {
                    title: '工程包名称',
                    field: 'projectPackageName',
                    width:'200',
                    rowspan: 2,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐证号',
                    field: 'certificatenum',
                    width:'auto',
                    rowspan: 2,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐证编号',
                    field: 'cutnum',
                    width:'auto',
                    rowspan: 2,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐面积',
                    field: 'cutarea',
                    //width:'auto',
                    rowspan: 2,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '合同编号',
                    field: 'contractNum',
                    rowspan: 2,
                    width:200,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '施工方',
                    field: 'contractionSide',
                    rowspan: 2,
                    width:200,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐工期',
                    field: 'cuttime',
                    rowspan: 2,
                    width:200,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '伐区管理员',
                    field: 'forester',
                    rowspan: 2,
                    //width:'auto',
                    align: 'center',
                    valign: 'middle'
                },
                {
                	title: '设计出材量',
                    colspan: 4,
                    align: 'center'
                },
                {
                	title: '实际出材量',
                    colspan: 4,
                    align: 'center'
                },
                {
                	title: '剩余出材量',
                    colspan: 4,
                    align: 'center'
                },
                {
                	title: '工程包执行情况',
                    colspan: 4,
                    align: 'center'
                },
                ],
                [
                	{
                        title: '合计',
                        field: 'totalDesign',
                        //width:'auto',
                        align: 'center',
                        valign: 'middle'
                    },
                    {
                        title: '杉木',
                        field: 'shanTreeDesign',
                        //width:'auto',
                        align: 'center',
                        valign: 'middle'
                    },
                    {
                        title: '松木',
                        field: 'songTreeDesign',
                        //width:'auto',
                        align: 'center',
                        valign: 'middle'
                    },
                    {
                        title: '杂木',
                        field: 'zaTreeDesign',
                        //width:'auto',
                        align: 'center',
                        valign: 'middle'
                    },
                    {
                        title: '合计',
                        field: 'totalActual',
                        //width:'auto',
                        align: 'center',
                        valign: 'middle'
                    },
                    {
                        title: '杉木',
                        field: 'shanTreeActual',
                        //width:'auto',
                        align: 'center',
                        valign: 'middle'
                    },
                    {
                        title: '松木',
                        field: 'songTreeActual',
                        //width:'auto',
                        align: 'center',
                        valign: 'middle'
                    },
                    {
                        title: '杂木',
                        field: 'zaTreeActual',
                        //width:'auto',
                        align: 'center',
                        valign: 'middle'
                    },
                    {
                        title: '合计',
                        field: 'totalSurplus',
                        //width:'auto',
                        align: 'center',
                        valign: 'middle'
                    },
                    {
                        title: '杉木',
                        field: 'shanSurplus',
                        //width:'auto',
                        align: 'center',
                        valign: 'middle'
                    },
                    {
                        title: '松木',
                        field: 'songSurplus',
                        //width:'auto',
                        align: 'center',
                        valign: 'middle'
                    },
                    {
                        title: '杂木',
                        field: 'zaSurplus',
                        //width:'auto',
                        align: 'center',
                        valign: 'middle'
                    },
                    {
                        title: '合计',
                        field: 'totalImplement',
                        //width:'auto',
                        align: 'center',
                        valign: 'middle'
                    },
                    {
                        title: '杉木',
                        field: 'shanTreeImplement',
                        //width:'auto',
                        align: 'center',
                        valign: 'middle'
                    },
                    {
                        title: '松木',
                        field: 'songTreeImplement',
                        //width:'auto',
                        align: 'center',
                        valign: 'middle'
                    },
                    {
                        title: '杂木',
                        field: 'zaiTreeImplement',
                        //width:'auto',
                        align: 'center',
                        valign: 'middle'
                    },
                ]
            ]
        });
    	};
    	oTableInit.destroy=function(){
	        $("#table1").bootstrapTable('destroy');
	    }
	    return oTableInit;
    }
    var oTable = new TableInit();//新定义一个表格
	oTable.destroy();//如果之前存在，则先毁灭
	oTable.Init();//初始化表格

</script> 
<script type="text/javascript">
$(function(){
    $("#btnPrint").click(function(){ $("#divprint").printArea(); });
});
</script>
</body>
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