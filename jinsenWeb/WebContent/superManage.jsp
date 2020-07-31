<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>超级管理平台导航</title>
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
<script src="js/echarts.js"></script>
<script src="js/echarts.min.js"></script>
<script src="js/jquery.min.js"></script>
</head>
<body>

<!--Header-part-->
<div id="header">
  <h1><a href="dashboard.html">木材产销管理平台导航</a></h1>
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
  <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>管理部门</span> <span class="label label-important">3</span></a>
       <ul>
        <li><a href="managerP.jsp">管理部操作员</a></li>
        <li><a href="managePassis.jsp">管理部副经理</a></li>
        <li><a href="managePdirector.jsp">管理部经理</a></li>
      </ul>
     </li>
    <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>生产系统</span> <span class="label label-important">3</span></a>
       <ul>
        <li><a href="forestManager.jsp">生产部副经理</a></li>
        <li><a href="forestP.jsp">伐区管理员</a></li>
        <li><a href="planteam.jsp">规划队</a></li>
      </ul>
     </li>
     <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>销售系统</span> <span class="label label-important">5</span></a>
       <ul>
        <li><a href="salaryper.jsp">销售部门操作员</a></li>
        <li><a href="salesdirector.jsp">销售经理</a></li>
        <li><a href="salesmanOne.jsp">销售合同操作员</a></li>
        <li><a href="salesmanTwo.jsp">销售调令操作员</a></li>
        <li><a href="salesmanThree.jsp">销售结算操作员</a></li>
      </ul>
     </li>
     <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>货场管理系统</span> <span class="label label-important">6</span></a>
      <ul>
        <li><a href="yardmanager.jsp">货场管理员</a></li>
        <li><a href="yardDirector.jsp">货场经理</a></li>
        <li><a href="Surveyor.jsp">检尺中心</a></li>
        <li><a href="yardstaffA.jsp">货场制表员A</a></li>
        <li><a href="yardstaffB.jsp">货场制表员B</a></li>
        <li><a href="yardstaffC.jsp">货场制表员C</a></li>
      </ul>
    </li>
    <li><a href="infoCenter.jsp"><i class="icon icon-tint"></i> <span>信息中心</span></a></li>
    <li class="submenu"><a href="#"><i class="icon icon-pencil"></i> <span>超级管理员</span><span class="label label-important">3</span></a>
          <ul>
        <li><a href="managerindex.jsp">人员信息管理</a></li>
        <li><a href="treeoutTable.jsp">木材销售报表</a>
        <li><a href="producetreeTable.jsp">木材产出报表</a>
      </ul>
    </li>
  </ul>
</div>
<!--sidebar-menu-->

<!--main-container-part-->
<div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"> <a href="hello2.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
  </div>
<!--End-breadcrumbs-->

<!--Action boxes-->
  <div class="container-fluid">
    <div class="quick-actions_homepage">
      <ul class="quick-actions">
        <li class="bg_lb"> <a href="managerP.jsp"> <i class="icon-dashboard"></i>管理系统 </a> </li>
        <li class="bg_lg span3"> <a href="forestP.jsp"><i class="icon-signal"></i> <span>产销系统</span> <span class="label label-important">2</span></a></li>
        <li class="bg_ly"> <a href="salaryper.jsp"> <i class="icon-inbox"></i><span class="label label-success"></span> 销售管理系统 </a> </li>
        <li class="bg_lo"> <a href="planteam.jsp"> <i class="icon-th"></i>规划队</a> </li>
        <li class="bg_ls"> <a href="Surveyor.jsp"> <i class="icon-fullscreen"></i> <span class="label label-important">3</span>伐区管理系统</a> </li>
        <li class="bg_lo span3"> <a href="infoCenter.jsp"> <i class="icon-th-list"></i> 信息中心</a> </li>
        <li class="bg_ls"> <a href="treeoutTable.jsp"> <i class="icon-tint"></i>木材销售报表</a> </li>
        <li class="bg_lb"> <a href="managerindex.jsp"> <i class="icon-pencil"></i>人员信息管理</a> </li>
        <li class="bg_lg span3"> <a href="producetreeTable.jsp"> <i class="icon-calendar"></i>木材产出报表</a> </li>

      </ul>
    </div>
<!--End-Action boxes-->    

<!--Chart-box-->    
    <div class="row-fluid">
      <div class="widget-box">
        <div class="widget-title bg_lg"><span class="icon"><i class="icon-signal"></i></span>
          <h5>木材销售分析</h5>
        </div>
        <div class="widget-content" >
          <div class="row-fluid">
            <div class="span9" id="chartmain" style="width:1000px;height:400px; display:inline-block">
              <script type="text/javascript">
//折线销售价格
function loadDate(option){
$.ajax({
	type:"get",
	async : false,
	url:"chartServlet?action=treechart",
    data:{},
    dataType:"json",
    success:function(result){
    	if(result){
    		option.xAxis[0].data=[];
    		 for(var i=0;i<result.length;i++){
    			 option.xAxis[0].data.push(result[i].yarddate);
    			 //yarddate.push(result[i].yarddate);
                 //names.push(result[i].name);
    			 //alert(result[i].yarddate);
               }
    		  option.series[0].data=[];
               for(var i=0;i<result.length;i++){
            	   option.series[0].data.push(result[i].price);
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


var mychart = echarts.init(document.getElementById('chartmain'));
var option={
		title:{
			text:"木材按月销售统计分析"
		},
		tooltip:{
			show: true
		},
		dataZoom: {
            show: true,
            realtime: true,
            //type: 'inside'
            //startValue: '2009-09-20 12:00',
            //end: 100
        },
		grid: {
            containLabel: true
        },
		legend:{
			data:['销量']
		
		},
		xAxis:[{
			type: 'category',
		        //data: [],
		}],
		yAxis : [ {
			type : 'value',
			axisLabel: {
		        formatter: '{value} 元'
		        },
		} ],
		series : [ {
			name : '销量',
			type : 'bar',
			color: 'green',
            smooth: true,
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
          <div class="span3">
              <div id="tab1" class="tab-pane active">             
              <img src="img/森林.jpg" alt="demo-image"/>
               <p>金森林业有限公司木材产销软件超级管理员平台</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
<!--End-Chart-box--> 

<!--Chart-box-->    
    <div class="row-fluid">
      <div class="widget-box">
        <div class="widget-title bg_lg"><span class="icon"><i class="icon-signal"></i></span>
          <h5>木材库存分析</h5>
        </div>
        <div class="widget-content" >
          <div class="row-fluid">
            <div class="" id="myPieDiv" style="width:45%;height:400px; display:inline-block">
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
            		               for(var i=0;i<result.length;i++){
            		            	   option.series[0].data.push(result[i].num);
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
            					text:"木材库存"
            				},
            				tooltip:{
            					show: true
            				},
            				grid: {
            		            containLabel: true
            		        },
            				legend:{
            					data:['数量']
            				
            				},
            				xAxis:[{
            					type: 'category',
            				        //data: [],
            				}],
            				yAxis : [ {
            					type : 'value',
            					axisLabel: {
            				        formatter: '{value} 根'
            				        },
            				} ],
            				series : [ {
            					name : '数量',
            					type : 'bar',
            					color: 'red',
            		            smooth: true,
            		           // data:[]
            				} ]
            		};
            		//加载数据到option
            		loadDate(option);
            		//设置option
            		mychart.setOption(option);
          </script>
            </div>
            <div class="" id="myline" style="width:45%;height:400px;display:inline-block">
              <script type="text/javascript">
              function loadDate(option){
            		$.ajax({
            			type:"get",
            			async : false,
            			url:"chartServlet?action=treeout",
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
            		            	   option.series[1].data.push(result[i].price)
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


            		var mychart = echarts.init(document.getElementById('myline'));
            		var option={
            				title:{
            					text:"木材销售情况"
            				},
            				tooltip:{
            					show: true
            				},
            				grid: {
            		            containLabel: true
            		        },
            				legend:{
            					data:['数量','销售']
            		            
            				
            				},
            				xAxis:[{
            					type: 'category',
            				        //data: [],
            				}],
            				yAxis : [ {
            					type : 'value',
            					axisLabel: {
            				        formatter: '{value} 根'
            				        },
            				},
            				{
            	                type: 'value',
            	                name: '销售',
            	                axisLabel: {
            	                    formatter: '{value} 元'
            	                }
            	            }
            				
            				],
            				series : [ {
            					name : '数量',
            					type : 'bar',
            					color: 'blue',
            		            smooth: true,
            		           // data:[]
            				},
            					{
            	                    name:'销售',
            	                    type:'line',
            	                    color: 'red',
            	                    yAxisIndex: 1,
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
<!--End-Chart-box--> 
    <hr/>
    
  </div>
</div>

<!--end-main-container-part-->

<!--Footer-part-->
<!--end-Footer-part-->

<script src="js/excanvas.min.js"></script> 
<script src="js/jquery.min.js"></script> 
<script src="js/jquery.ui.custom.js"></script> 
<script src="js/bootstrap.min.js"></script> 
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
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
