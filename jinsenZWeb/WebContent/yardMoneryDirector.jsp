<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>货场费用台账</title>
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

<style>
#h li{float: left; }
#h a{font-size:15px;width: 230px; height: 30px;padding: 10px 0;font-family: 微软雅黑;text-align: center;  background: #3c763d; display: block; text-decoration:none; color:white}
#h a:hover{color:white;background: green}
    </style>  
      <style>
/*定义类名为.thead-blue的样式*/
.table .thead-blue th {
color: #fff;
background-color: #3195f1;
border-color: #0d7adf;
}
.bar1 {background: #e8e9ef;margin-left:auto; margin-right:auto;padding:10px;border-collapse:collapse}
        .bar1 input,select{
            border: 2px solid #7BA7AB;
            border-radius: 5px;
            background: #faf7f7;
            color: #9E9C9C;
        }
        .bar1 button {
            top: 0;
            right: 0;
            background: #4bb7e3;
            border-radius: 0 5px 5px 0;
        }
        .bar1 button:before {
            content: "\f002";
            font-family: FontAwesome;
            font-size: 16px;
            color: #F9F0DA;
        }
</style>
<script type="text/javascript">
    function submit_year()
    {
    	var now = new Date();
    	var year = now.getFullYear();

    	for(var i = year; i > year -10 ; i --)
    	{
    		var str = '<option>'+i+'</option>';
    		
    	    $("#sa_year").show().append(str);
    	    
    	}
    }
    window.onload = function () {
        locationInput = function () {
        };
    }
    </script>
    <script src="js/jquery.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript">
$(function(){
	goodsyardList1();
})
    function goodsyardList1()
    {
     	$('#myModal_monthtree').modal('hide');
    	$('#table1').bootstrapTable('destroy');
        $('#table1').bootstrapTable({
            method: "post",
            striped: true,
            theadClasses: "thead-blue",//设置thead-blue为表头样式
    	    classes: "table table-bordered table-striped table-sm table-dark", 
            singleSelect: false,
            cache: false,//缓存
            url: "workpageSevrlet",
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
            //buttonsAlign:"right",  //按钮位置    
            exportTypes:['excel','xlsx','csv'],  //导出文件类型，[ 'csv', 'txt', 'sql', 'doc', 'excel', 'xlsx', 'pdf']
            exportOptions:{
                // ignoreColumn: [0,1],  //忽略某一列的索引
                fileName: "货场费用台账",  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: "货场费用台账",
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight'], //设置格式
            },
            queryParams:function queryParams(params){
                var temp = {
                		action:"goodsyardList1",
						timeStart:document.getElementById("timeStart").value,
						timeEnd:document.getElementById("timeEnd").value,
						yard:document.getElementById("yard").value,
						feetype:document.getElementById("feetype").value,				
				};               
                return temp;
            },
            columns: [
            	[
            		{
            			"title": "货场费用台账",
                        "font-size":"100px",
                        "halign":"center",
                        "align":"center",
                        "valign": "middle",
                        "colspan": 13
            		}
            	],
            	[     /* 	{
                    checkbox: "true",
                    field: 'check',
                    align: 'center',
                    valign: 'middle'
                }, */
                {						
						title: '序号',
						width: 100,
                    align: 'center',
						formatter: function (value, row, index) {
							return index+1;
						}
	                },//该列显示序号，分页不是从1开始
                {
                    title: "时间",
                    field: 'luRuDate',
                    align: 'center',
                    width: 200,
                    valign: 'middle'
                },
                {
                    title: "货场",
                    field: 'yard',
                    align: 'center',
                    width: 200,
                    valign: 'middle'
                },
                {
                	 title: "项目",
                     field: 'feetype',
                     align: 'center',
                     width: 200,
                     valign: 'middle'
                },
            	{
                title: "数量",
                field: 'num',
                width: 200,
                align: 'center',
                valign: 'middle'
            },
            {
                title: '单位',
                field: 'unit',
                align: 'center',
                width: 200,
                valign: 'middle'
            },
            {
                title: '单价',
                field: 'unitprice',
                align: 'center',
                width: 200,
                valign: 'middle'
            },
            {
                title: '金额',
                field: 'price',
                align: 'center',
                width: 200,
                valign: 'middle'
            },
            {
                title: '备注',
                field: 'remarks',
                align: 'center',
                width: 200,
                valign: 'middle'
            },
            {
                title: "录入人员",
                field: 'luruperson',
                width: 200,
                align: 'center',
                valign: 'middle'
            }
            ]
            ]
        });
    }
    </script> 
    
    <script type="text/javascript">
    function submit_year()
    {
    	var now = new Date();
    	var year = now.getFullYear();

    	for(var i = year; i > year -10 ; i --)
    	{
    		var str = '<option>'+i+'</option>';
    		
    	    $("#sa_year").show().append(str);
    	    
    	}
    }
    function load()
    {
    	$.ajax({
            url:"salaryServlet",//要发送的地址
            data:{
                "action":"loadyardinfo"
            },
            type: "POST",
            dataType:"json",
            success: function (data) {
                for(i = 0;i<data.length;i++)
                {
                	str = "<option>"+data[i].yardname+"</option>";
                	
                	$("#yard").show().append(str);
                }
            }
        })
    }
    </script>
</head>
<body onload="load()">
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
        <li><a href="ChangePassword.jsp"><i class="icon-user"></i> 我的个人资料 </a></li>
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
    <li class=""><a title="" href="ChangePassword.jsp"><i class="icon icon-cog"></i> <span class="text">设置</span></a></li>
    <li class=""><a title="" href="./logout"><i class="icon icon-share-alt"></i> <span class="text">注销</span></a></li>
    <li>
    <%
	String staff_id = request.getSession().getAttribute("staff_id").toString();
				%> <%
 	String staff_name = request.getSession().getAttribute("staff_name").toString();
	String power_type = request.getSession().getAttribute("power_type").toString();
 %> 您好，<%=staff_id%> <%=staff_name%>欢迎登录
    </li>
  </ul>
</div>
<!--close-top-Header-menu-->

<!--sidebar-menu-->
<div id="sidebar"><a href="#" class="visible-phone"><i class="icon icon-home"></i> 仪表盘</a>
  <ul>
      <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>货场费用</span> <span class="label label-important">6</span></a>
       <ul>
        <li ><a href="goodsYardCost.jsp">货场费用录入</a></li>
        <li ><a href="yardInventoryBootstrapDirector.jsp">货场盘点</a></li>
        <li ><a href="yardMonerySettle.jsp">货场管理费用</a></li>
        <li><a href="yardMoneryDirector.jsp">货场管理费用台账</a></li>
        <li><a href="workpageTreeBuyDirector.jsp">木材收购单</a></li>
        <li ><a href="cancellingStocksTable.jsp">货场报损</a> </li>       
      </ul>
     </li>
    <li><a href="compareTreeListDirector.jsp"><i class="icon icon-home"></i> <span>木材装车对比</span></a></li>
    <li><a href="InyardShenhesuDirector.jsp"><i class="icon icon-home"></i> <span>查看进场木材反馈</span></a></li>
    <li><a href="yardinfo.jsp"><i class="icon icon-home"></i> <span>货场信息管理</span></a></li>
    <li><a href="SurveyorAddinfo.jsp"><i class="icon icon-home"></i> <span>检尺员管理</span></a></li>
    <li><a href="saleCalloutOrderShenhe.jsp"><i class="icon icon-home"></i> <span>审批调令</span></a></li>
  </ul>
</div>
<!--sidebar-menu-->

<!--main-container-part-->
<div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"> <a href="yardDirector.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> 首页</a></div>
  </div>
  
   <div class="find-top1">
           <div class="find-top">
           <p class="p-tail"><i class="i-tail"></i> 该页面是查询货场费用台账</p>
          </div>
           <div class="find-top1">
            <table class="bar1" >
           <!--  <tr><td class="top-table-label">年份：</td><td><select id="sa_year" name="sa_year" onChange="change_year()" readonly unselectable="on"></select></tr>
           <tr><td class="top-table-label">月份：</td><td><select id="sa_month" name="sa_month"><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option><option>7</option><option>8</option><option>9</option><option>10</option><option>11</option><option>12</option><option>1-12</option><option>1-3</option><option>4-6</option><option>7-9</option><option>10-12</option></select></td></tr>-->
           <tr>
           <td width="200">选择开始日期：<input width="160" type="date" name="timeStart" id="timeStart"  value="2020-01-01" oninput='goodsyardList1()' onclick='locationInput'></td>
           <td width="200">选择结束日期：<input width="160" type="date" name="timeEnd" id="timeEnd" value="2020-12-01" oninput='goodsyardList1()' onclick='locationInput'></td>
           </tr>
           <tr></tr>
           <tr>
           <td width="400">
            <span>货场：</span><select name="yard" id="yard" oninput='goodsyardList1()' onclick='locationInput'><option selected="selected"></option></select></td>          
           
           <td width="400">
            <span>项目：</span><select  name="feetype" id="feetype" oninput='goodsyardList1()' onclick='locationInput'>                        
    <option></option>
    <option value='钩机整堆'>钩机整堆</option>
    <option value='人工整堆'>人工整堆</option>
    <option value='装车工资'>装车工资</option>
    <option value='场地维护'>场地维护</option>
    <option value='场地资金'>场地资金</option>
    <option value='看护工资'>看护工资</option>
    <option value='电费'>电费</option>
    <option value='设备维护'>设备维护</option>
    <option value='运费'>运费</option>
    <option value='检尺费'>检尺费</option></select></td>         
            </tr>
<!--             <tr> -->
<!--            <td colspan="8" style="text-align: center"> -->
<!--            <button  onclick="goodsyardList1()">台账</button>                    -->
<!--            </tr> -->
            </table>
           </div>
           <div class="table-con">
        <table id="table1" class="table-style"></table>
        </div>          
        </div>
  
</div>
 
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