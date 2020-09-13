<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>采伐证解锁</title>
<!-- Custom CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/fullcalendar.css" />
<link rel="stylesheet" href="css/matrix-style.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="css/jquery.gritter.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
<link type="text/css" rel="stylesheet" href="css/PrintArea.css" />
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
    background-image: url(../img/listicon.jpg);
    top: 1px;
}
</style>    
<script type="text/javascript">
function mypass(workid)
{
	$.ajax({
        url:"workpageSevrlet",
        data:{
            "action":"mypass",
            "workid":workid,
        },
        type: "POST",
        dataType:"html",
        success: function (data) {
        	alert(data);
        }
    })
	}

function alldo()
{
    var mytype=document.getElementById("mytype").value;
    var group=[];
    if(mytype=="")
    	{
    	alert("请选择批量处理得方式");
    	}
    else
    {   
        if(mytype=="alldelete"){
        	var str=$("#table1").bootstrapTable('getSelections');
        	if(str.length<1)
        		{
        		alert("无选中记录");
        		}
        	else{
        	for(var i=0;i<str.length;i++)
        		{
        		var j=str[i];
        		group[i]=j.workid;
        		}
        	var mymap=JSON.stringify(group);
        	$.ajax({
                url:"workpageSevrlet?action=writepage&type="+mytype,
                data:{
                    "action":mytype,
                    "workid":mymap,
                },
                type: "POST",
                dataType:"html",
                success: function (data) {
                	alert(data);
                }
            })
        	}
        }
        else if(mytype=="notpass")
        {
        	var str=$("#table1").bootstrapTable('getSelections');
        	if(str.length<1)
        		{
        		alert("无选中记录");
        		}
        	else{
        	for(var i=0;i<str.length;i++)
        		{
        		var j=str[i];
        		group[i]=j.workid;
        		}
        	var mymap=JSON.stringify(group);
        	$.ajax({
                url:"workpageSevrlet?action=writepage&type="+mytype,
                data:{
                    "action":mytype,
                    "workid":mymap,
                },
                type: "POST",
                dataType:"html",
                success: function (data) {
                	alert(data);
                }
            })
        	}
        }
        else{
    	var param = { 
    	        url: "workpageSevrlet?action=writepage&type="+mytype, 
    	       }
    	$('#table1').bootstrapTable('refresh',param);
        }
	}
}
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
  <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>施工方管理</span> <span class="label label-important">2</span></a>
     <ul>
        <li><a href="managesdatecard.jsp">录入施工方资料</a></li>
        <li><a href="managersdatecardSee.jsp">施工方台账</a></li>
      </ul>
     </li>
     <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>工程包管理</span> <span class="label label-important">4</span></a>
     <ul>
        <li><a href="CutnumProjectpackage.jsp">创建工程包</a></li>
        <li><a href="cutareaAllot.jsp">伐区拨交</a></li>
        <li><a href="cutnumProjectpackageShenhe.jsp">审核工程包</a></li>
        <li><a href="CutnumProjectpackageTable.jsp">工程包台账</a></li>
      </ul>
     </li>
     <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>野账管理</span> <span class="label label-important">3</span></a>
       <ul>
        <li><a href="workpageAdd.jsp">野账录入</a></li>
        <li><a href="workpageShenheFaqu.jsp">野账审核</a></li>
          <li><a href="treeinYezhang.jsp"> <span>野帐打印</span></a> </li>
      </ul>
     </li>
    <li><a href="manageCutnumCheck.jsp"><i class="icon icon-inbox"></i> <span>生产管理</span></a> </li>
   <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>生产结算</span> <span class="label label-important">4</span></a>
       <ul>
        <li><a href="productPrice.jsp">生产工资和其他费用</a></li>
        <li><a href="productPrice2.jsp">生产工资结算</a></li>
        <li><a href="productTreePrice.jsp">木材销售货款结算</a></li>
        <li><a href="productTreePriceTable.jsp">木材销售货款台账</a></li>
      </ul>
     </li>
     <li><a href="manageCutnumProduced.jsp"><i class="icon icon-inbox"></i> <span>录入已生产量</span></a></li>
      <li class="active"><a href="workpageUnlock.jsp"><i class="icon icon-inbox"></i> <span>采伐证解锁</span></a></li>         
  </ul>
</div>
<!--sidebar-menu-->

<!--main-container-part-->
<div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"> <a href="hello2.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
  </div>
<main>
    <article>
    <div class="book_con01">
    <div class="find-top">
        <p class="p-tail"><i class="i-tail"></i> 解锁采伐证。</p>
    </div>
    <div class="table-con">
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
    function table1(){
        $('#table1').bootstrapTable({
        	theadClasses: "thead-blue",//设置thead-blue为表头样式
    	    classes: "table table-bordered table-striped table-sm table-dark",
    	    method: "post",
            striped: true,
            singleSelect: false,
            url: "cutnumServlet?action=printCutnumLock",
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
            columns: [[
            	{
            		"title": "采伐证信息",
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
                        title: '林权单位',
                        field: 'company',
                        width: 180,
                        align: 'center',
                        valign: 'middle'
                    },
                    {
                        title: '采伐证号',
                        width: 180,

                        field: 'certificatenum',
                        align: 'center',
                        valign: 'middle'
                    },
                    {
                        title: '采伐证编号',
                        width: 180,

                        field: 'cutnum',
                        align: 'center',
                        valign: 'middle'
                    },
                	
                	
                {
                    title: '乡镇',
                    field: 'cutaddress',
                    width: 180,

                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '村',
                    field: 'cutvillage',
                    width: 180,

                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '林班',
                    width: 180,

                    field: 'quartel',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '大班',
                    width: 180,

                    field: 'largeblock',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: "小班",
                    width: 180,

                    field: 'smallblock',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '出材量',
                    width: 180,

                    field: 'volume',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '已生产出材量',
                    width: 180,
                    field: 'shorttimber',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '创建时间',
                    width: 180,
                    field: 'creatcutDate',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '解锁',
                    field: 'opr',
                    width: 100,
                    align: 'center',
                    valign: 'middle',
                    formatter: function (cellval, row) {
                        //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                        var  d = '<button type="button" data-id="98"  class="btn btn-xs btn-primary" onclick="Onlock(\''+ row.cutnum + '\')">解锁</button> ';
                        //var  d = '<a href="workpageSevrlet?action=alldelete&workid=\''+ row.workid + '\'"><button  id="id="delete" data-id="98" class="btn btn-xs btn-primary">删除</button></a>';
                        return  d;
                    }
                },
                ],
            ]
        });
    }
    /*function overtimedelete(workid)
    {
    	$.ajax({
    		 url:"workpageSevrlet",
    		 type:"POST",
    		 data:{"action":"alldelete","workid":workid},
    	     dataType:"text",
    	     success:function (data)
         	{
         		if(data=="true")
         		{
         			alert("修改成功！");	
         			window.location.reload();
         		}
         		else
         		{
         			alert("查看失败");	  
         		}
         	},
          error:function(e){
         	 alert("请联系管理员！");	  
         }
    	});
    }*/
    
    function Onlock(cutnum)
    {
    	$.ajax({
            url:"cutnumServlet",
            data:{
                "action":"cutnumOnlock",
                "cutnum":cutnum,
            },
            type: "Post",
            dataType:"json",
            success: function (data) {
            	if(data)
            		{
            		alert("解锁成功！");
            		$("#table1").bootstrapTable('refresh');
            		
            		}       		
            	else
            		alert("解锁失败！");
             }
        })
    }
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