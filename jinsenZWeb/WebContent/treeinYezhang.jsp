<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>检尺野账</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="https://unpkg.com/bootstrap-table@1.18.0/dist/bootstrap-table.min.css" rel="stylesheet">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/fullcalendar.css" />
<link rel="stylesheet" href="css/matrix-style.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="css/jquery.gritter.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
<style type="text/css">
.bar {
            padding: 0;
            margin: 0;
        }
        .bar{
            width: 100%;            
            display: flex;
            /*border: 1px solid red;*/
            background:#e2e1ea;
        }
        .bar input,select{
            float: center;
            flex: 4;
            outline: none;
            border: 1px solid #aec7b7;
            box-sizing: border-box;
            padding-left: 2px;
        }
        .bar button{
            float: right;
            flex: 1;
            height: 30px;
            background-color: red;
            color: white;
            border-style: none;
            outline: none;
        }
        .bar button i{
            font-style: normal;
        }
        .bar button:hover{
            font-size: 16px;
        }
</style>
</head>
<script type="text/javascript">
window.onload = function () {
    locationInput = function () {
    };
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
<script src="js/jquery.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>

<script>
    $(function(){
        table1();
    })
    function table1(){
    	$('#table1').bootstrapTable('destroy');
        $('#table1').bootstrapTable({
        	theadClasses: "thead-blue",//设置thead-blue为表头样式
    	    classes: "table table-bordered table-striped table-sm table-dark",
            method: "get",
            striped: true,
            singleSelect: false,
            //url: "workpageSevrlet?action=writepage&type=passall2",
            url: "workpageSevrlet",
            data:{},
            dataType: "json",
            pagination: true, //分页
            pageSize: 15,
            pageNumber: 1,
            search: true, //显示搜索框
            contentType: "application/x-www-form-urlencoded",
            showRefresh: true,                      //是否显示刷新按钮
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            //detailView: true,
            exportDataType:'all',//'basic':当前页的数据, 'all':全部的数据, 'selected':选中的数据    
            showExport: true,  //是否显示导出按钮    
            buttonsAlign:"right",  //按钮位置    
            exportTypes:['excel','xlsx','csv','pdf'],
            exportOptions:{
                // ignoreColumn: [0,1],  //忽略某一列的索引
                fileName: "野战台账",  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: "野战台账",
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight'], //设置格式
            },
            queryParams:function queryParams(params){
                var temp = {
						action:"passall2",
						timeStart:document.getElementById("timeStart").value,
						timeEnd:document.getElementById("timeEnd").value,
						projectPackageName:document.getElementById("projectPackageName").value,
						cutnum:document.getElementById("cutnum").value,
						workid:document.getElementById("workid").value,
						yard:document.getElementById("yard").value,
						tolstere:document.getElementById("tolstere").value,
						toltree:document.getElementById("toltree").value,
						section:document.getElementById("section").value
				};     
                return temp;
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
                    title: "工单号",
                    field: 'workid',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '工程包',
                    field: 'proj_package_Name',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐证编号',
                    field: 'cutnum',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '发货单位',
                    field: 'company',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '货场',
                    field: 'yard',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '分区',
                    field: 'section',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '合计根数',
                    field: 'toltree',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '合计材积',
                    field: 'tolstere',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '检尺野账',
                    field: 'opr',
                    width: 200,
                    align: 'center',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<a href="workpageSevrlet?action=jianchiYzhang&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">打印</button></a>';
                        //var  d = '<a href="workpageSevrlet?action=alldelete&workid=\''+ row.workid + '\'"><button  id="id="delete" data-id="98" class="btn btn-xs btn-primary">删除</button></a>';
                        return  d;
                    }
                },
            ]
        });
    }
    </script>
<body onload="load()">

<!--Header-part-->
<div id="header">
  <h1><a href="dashboard.html">生产管理平台</a></h1>
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
    <%-- <li><%String staff_id = request.getSession().getAttribute("staff_id").toString();%>您好，<%=staff_id %>欢迎登录</li>--%>
   <li>
    <%
	String staff_id = request.getSession().getAttribute("staff_id").toString();
	%> 
	<%
 	String staff_name = request.getSession().getAttribute("staff_name").toString();
 %> 您好，<%=staff_id%> <%=staff_name%>欢迎登录
    </li>
  </ul>
</div>
<!--close-top-Header-menu-->

<%-- <div id="user-nav" class="navbar navbar-inverse">
<table  class="icon-user">
<tr><td><%String staff_id = request.getSession().getAttribute("staff_id").toString();%>您好，<%=staff_id %>欢迎登录</td></tr>
</table>
</div>
  --%>

<!--sidebar-menu-->
<div id="sidebar"><a href="#" class="visible-phone"><i class="icon icon-home"></i> 仪表盘</a>
  <ul>
    <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>施工方管理</span> <span class="label label-important">2</span></a>
     <ul>
        <li><a href="managesdatecard.jsp">施工方资料卡</a></li>
        <li><a href="managersdatecardSee.jsp">施工方台账</a></li>
      </ul>
     </li>
     <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>工程包管理</span> <span class="label label-important">4</span></a>
     <ul>
        <li><a href="CutnumProjectpackage.jsp">创建工程包</a></li>
        <li><a href="cutnumProjectpackageShenhe.jsp">审核工程包</a></li>
        <li><a href="CutnumProjectpackageTable.jsp">工程包执行情况</a></li>
      </ul>
     </li>
     <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>野账管理</span> <span class="label label-important">3</span></a>
       <ul>
      
        <li><a href="workpageAdd.jsp">野账录入</a></li>
        <li><a href="workpageShenheFaqu.jsp">野账审核</a></li>
          <li><a href="treeinYezhang.jsp"> <span>野帐打印</span></a> </li>
      </ul>
     </li>
     <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>伐区管理</span> <span class="label label-important">2</span></a>
       <ul>
       <li><a href="cutareaAllot.jsp">伐区拨交</a></li>
        <li><a href="manageCutnumCheck.jsp">伐区管理</a></li>
      </ul>
     </li>
    <li class="submenu"> <a href="#"><i class="icon"></i> <span>生产结算和报表</span><span class="label label-important">6</span></a>
       <ul>
        <li><a href="productPrice.jsp">生产工资和其他费用</a></li>
        <li><a href="productPriceTable.jsp">生产其他费用台账</a></li>
        <li><a href="productPrice2.jsp">生产工资结算</a></li>
        <li><a href="productPrice23Table.jsp">生产工资结算台账</a></li>
        <li><a href="productTreePrice.jsp">木材销售货款结算</a></li>
        <li><a href="productTreePriceTable.jsp">木材销售货款台账</a></li>
      </ul>
     </li>
     <li><a href="manageCutnumProduced.jsp"><i class="icon icon-inbox"></i> <span>录入已生产量</span></a></li>
     <li><a href="produceCutWorkidTable.jsp"><i class="icon icon-inbox"></i><span>生产总台账</span></a></li>        
  </ul>
</div>
<!--sidebar-menu-->

<!--main-container-part-->
<div id="content"> 
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"> <a href="forestP.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i>首页</a></div>
  </div>
<!--End-breadcrumbs-->

  <!--Chart-box-->  
   <div class="find-top1">
     <table class="bar">
           <tr>
           <td>选择开始日期：</td><td><input width="160" type="date" name="timeStart" id="timeStart"  value="2020-01-01" oninput='table1()' onclick='locationInput'></td>
           <td>选择结束日期：</td><td><input width="160" type="date" name="timeEnd" id="timeEnd" value="2020-12-01" oninput='table1()' onclick='locationInput'></td>
           <td>工程包名称：</td><td><input  type="text" name="projectPackageName" id="projectPackageName" oninput='table1()' onclick='locationInput'></td>
           <td>采伐证编号：</td><td><input  type="text" name="cutnum" id="cutnum" oninput='table1()' onclick='locationInput'></td>
           </tr>
           <tr>
           <td>工单号：</td><td><input width="140" type="text" name="workid" id="workid" oninput='table1()' onclick='locationInput'></td>
           <td>货场：</td><td><select  style="width:230px" name="yard" id="yard" oninput='table1()' onclick='locationInput'><option selected="selected"></option></select></td>
           <td>分区：</td><td><select   style="width:230px" name="section" id="section" oninput='table1()' onclick='locationInput'>
           <option selected="selected"></option>
           <option>A区</option>
           <option>B区</option>
           <option>C区</option>
           <option>D区</option>
           <option>E区</option>
           <option>F区</option>
           <option>无固定分区</option>
           </select></td>
           <td>合计根数：</td><td><input width="160" type="text" name="toltree" id="toltree" oninput='table1()' onclick='locationInput'></td>
           <td>合计材积：</td><td><input  type="text" name="tolstere" id="tolstere" oninput='table1()' onclick='locationInput'></td>
           </tr>
            </table>
           </div>
    <div class="row-fluid">
      <div class="widget-box">
        <div class="widget-title bg_lg"><span class="icon"><i class="icon-signal"></i></span>
          <h5>打印检尺野账</h5>
        </div>
        <div class="widget-content" >
            <div class="table-con">
        <table id="table1" class="table-style"></table>
        </div>
        </div>
      </div>
    </div>
<!--End-Chart-box--> 
  <hr/>
    
 </div>
<!--End-Action boxes-->  




<!--end-Footer-part-->
<script src="js/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
<script src="js/bootstrap-table-export.js"></script>
<script src="js/tableExport.js"></script>
<script src="js/jquery.base64.js"></script>
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
</body>
</html>