<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>生产管理平台</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/fullcalendar.css" />
<link rel="stylesheet" href="css/matrix-style.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="css/jquery.gritter.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
</head>
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
        	theadClasses: "thead-blue",//设置thead-blue为表头样式
    	    classes: "table table-bordered table-striped table-sm table-dark",
            method: "get",
            striped: true,
            singleSelect: false,
            url: "workpageSevrlet?action=writepage&type=passall",
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
                    title: "工单号",
                    field: 'workid',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐证号',
                    field: 'cutnum',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐时间',
                    field: 'cutdate',
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
                    title: '伐区监管员',
                    field: 'forester',
                    align: 'center'
                },
                /*{
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<a href="workpageSevrlet?action=finishWorkpage&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        //var  d = '<a href="workpageSevrlet?action=alldelete&workid=\''+ row.workid + '\'"><button  id="id="delete" data-id="98" class="btn btn-xs btn-primary">删除</button></a>';
                        return  d;
                    }
                },*/
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
<body>

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
        <li><a href="ChangePassword.jsp"><i class="icon-user"></i> 我的个人资料 </a></li>
        <li class="divider"></li>
        <li><a href="#"><i class="icon-check"></i> 我的任务</a></li>
        <li class="divider"></li>
        <li><a href="./logout"><i class="icon-key"></i>
							注销</a></li>
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
    <li class=""><a title="" href="./logout">
    <i class="icon icon-share-alt"></i> <span class="text">注销</span></a></li>
    <li>
    <%
	String staff_id = request.getSession().getAttribute("staff_id").toString();
				%> <%
 	String staff_name = request.getSession().getAttribute("staff_name").toString();
    String power_type = request.getSession().getAttribute("power_type").toString();%> 您好，<%=staff_id%> <%=staff_name%>欢迎登录
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
<!--      <li><a href="workpageUnlock.jsp"><i class="icon icon-inbox"></i> <span>采伐证解锁</span></a></li>        -->
     <li><a href="produceCutWorkidTable.jsp"><i class="icon icon-inbox"></i><span>生产总台账</span></a></li> 
  </ul>
</div>
<!--sidebar-menu-->

<!--main-container-part-->
<div id="content"> 
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"> <a href="forestP.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> 首页</a></div>
  </div>
<!--End-breadcrumbs-->
<!--Action boxes-->
  <div class="container-fluid">
    <div class="quick-actions_homepage">
      <ul class="quick-actions">
        <li class="bg_lb"> <a href="workpageShenheFaqu.jsp"> <i class="icon-dashboard"></i>野账审核 </a> </li>
        <li class="bg_lg span3"> <a href="workpageAdd.jsp"><i class="icon-signal"></i><span>野账录入</span></a></li>
        <li class="bg_ly span3"> <a href="CutnumProjectpackage.jsp"> <i class="icon-inbox"></i><span class="label label-success"></span>创建工程包</a> </li>
        <li class="bg_lo"> <a href="treeinYezhang.jsp"> <i class="icon-info-sign"></i>打印检尺野帐</a> </li>
      </ul>
    </div>   
  </div>
  <!--Chart-box-->  
   
   
<!--End-Chart-box--> 
  <hr/>
    <!--Chart-box-->    
    <div class="row-fluid">
      <div class="widget-box">
        <div class="widget-title bg_lg"><span class="icon"><i class="icon-signal"></i></span>
          <h5>木材进仓库存分析</h5>
        </div>
        <div class="widget-content" >
          <div class="row-fluid">
            <div id="myPieDiv" style="height:500px; display:inline-block" class="span9">
            <script src="js/echarts.js"></script>
            <script src="js/echarts.min.js"></script>
              <script type="text/javascript">
              function loadDate(option){
            		$.ajax({
            			type:"get",
            			async : false,
            			url:"chartServlet?action=treetype",
            		    data:{},
            		    dataType:"json",
            		    success:function(result){
            		    	if(result){
            		    		option.xAxis[0].data=[];
            		    		 for(var i=0;i<result.length;i++){
            		    			 option.xAxis[0].data.push(result[i].treetype);
            		    			 //yarddate.push(result[i].yarddate);
            		                 //names.push(result[i].name);
            		    			 //alert(result[i].yarddate);
            		               }
            		    		  option.series[0].data=[];
            		    		  option.series[1].data=[];
            		               for(var i=0;i<result.length;i++){
            		            	   option.series[0].data.push(result[i].num);
            		            	   option.series[1].data.push(result[i].tvolume);
            		            	   //price.push(result[i].price);
            		            	   //alert(result[i].price);
            		               }
            		        }
            		     },
            		     error : function(errorMsg) {
            		          //请求失败时执行该函数
            		      alert("图表请求数据失败!");
            		      //mychart.hideLoading();
            		    }
            		})
            		}


            		var mychart = echarts.init(document.getElementById('myPieDiv'));
            		var option={
            				title:{
            					text:"木材进仓库存"
            				},
            				tooltip:{
            					show: true
            				},
            				grid: {
            		            containLabel: true
            		        },
            				legend:{
            					data:['数量','材积']
            				},
            				
            				xAxis:[{
            					type: 'category',
            				        //data: [],
            				}],
            				yAxis : [
            					{
            					type : 'value',
            					axisLabel: {
            				        formatter: '{value} 根'
            				        },
            				},
            				{
            					type : 'value',
            					name:'材积',
            					axisLabel: {
            				        formatter: '{value} 立方米'
            				        },
            				}
            				],
            				series : [ {
            					name : '数量',
            					type : 'line',
            					color: 'blue',
            		            //smooth: true,
            		           // data:[]
            				},
            				{
            					name : '材积',
            					type : 'bar',
            					color: 'red',
            					yAxisIndex: 1,
            		           // data:[]
            				}
            				]
            		};
            		//加载数据到option
            		loadDate(option);
            		//设置option
            		mychart.setOption(option);
          </script>
            </div>
          </div>
        </div>
      </div>
    </div>
    <hr/>
     <div class="row-fluid">
      <div class="widget-box">
        <div class="widget-title bg_lg"><span class="icon"><i class="icon-signal"></i></span>
          <h5>采伐工单信息</h5>
        </div>
        <div class="widget-content" >
            <div class="table-con">
        <table id="table1" class="table-style"></table>
        </div>
        </div>
      </div>
    </div>
    
 </div>
<!--End-Action boxes-->  




<!--end-Footer-part-->

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