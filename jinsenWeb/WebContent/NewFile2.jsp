<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 弹出窗口 -->
<!DOCTYPE html>  

<html >  

<head>  

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  
<script type="text/javascript" src="//www.klaviyo.com/media/js/public/klaviyo_subscribe.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/fullcalendar.css" />
<title>弹出层</title>  
</head>  

<body>  
<div class="klaviyo_modal" id="k_id_modal" style="display:none;">
    <div class="klaviyo_inner">
        <a href="#" class="klaviyo_close_modal klaviyo_header_close">×</a>
        <form action="//manage.kmail-lists.com/subscriptions/subscribe" method="POST" novalidate="novalidate" class="klaviyo_subscription_form">
            <input type="hidden" name="g" value="LIST_ID">
            <div class="klaviyo_fieldset">
                <p class="klaviyo_header">采伐证信息</p>
                <p class="klaviyo_subheader">进度.</p>
            </div>
        <div class="klaviyo_fieldset">
            <div class="klaviyo_field_group">
            <label for="k_id_modal_$email">采伐证号</label>
            <input type="text" id="cutnum" name="cutnum">
            <label for="klaviyo_form_first_name">工程包:</label>
            <input id="projectPackageid" type="text" name="projectPackageid" value="" />
            <label for="klaviyo_form_last_name">采伐地点:</label>
            <input id="cutsite" type="text" name="cutsite" value="" />
            <label for="k_id_modal_$email">采伐面积(亩)</label>
            <input type="text" id="cutarea" name="cutarea">
            <label for="klaviyo_form_first_name">采伐蓄积(立方米):</label>
            <input id="cutstore" type="text" name="cutstore" value="" />
            <label for="klaviyo_form_last_name">采伐开始时间:</label>
            <input id="starttime" type="text" name="starttime" value="" />
            <label for="klaviyo_form_first_name">采伐结束时间:</label>
            <input id="endtime" type="text" name="endtime" value="" />
            <label for="klaviyo_form_last_name">采伐进度:</label>
            <input id="status" type="text" name="status" value="" />
        </div>
    </div>
    <div class="klaviyo_fine_print"></div>
        <div class="klaviyo_form_actions">
            <button type="submit" class="klaviyo_submit_button">
                <span>Subscribe</span>
            </button>
        </div>
        <div class="klaviyo_below_submit" ></div>
        </form>
        <div class="error_message" ></div>
        <div class="success_message" ></div>
    </div>
</div>
<div class="table-con">
        <table id="table1" class="table table-bordered"></table>
    </div>
<button onclick="KlaviyoSubscribe.attachToModalForm('#k_id_modal',{delay_seconds: 0.01, hide_form_on_success: true, ignore_cookie: true, success_message: false});">Click me</button>
</body>  
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
            url: "cutnumServlet?action=seecutP",
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
        columns: [[
        	{
        		"title": "采伐证信息",
                "font-size":"100px",
                "halign":"center",
                "align":"center",
                "valign": "middle",
                "colspan": 8
            }],
            [
            	{
                    checkbox: "true",
                    field: 'check',
                    align: 'center',
                    valign: 'middle'
                },
                {
                	title: "序号",
                    field: 'cutnumid',
                    align: 'center',
                    valign: 'middle'
                },
            	{
                title: "采伐证号",
                field: 'cutnum',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '采法许可证编号',
                field: 'number',
                align: 'center',
                valign: 'middle'
            },
            {
                title: '采伐公司',
                field: 'company',
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
                title: '进度',
                field: 'opr',
                width: 180,
                align: 'center',
                formatter: function (cellval, row) {
                	var  d = '<a <button   onclick = "openDialog()" class="btn btn-xs btn-primary">进度</button></a>';
                	//var  d = '<a <button  href = "javascript:void(0)" onclick = "window.open("map.jsp")" class="btn btn-xs btn-primary">进度</button></a>';
                    //var  d = '<a <button  href = "cutnumServlet?action=watchProgress&cutnumid=\''+ row.cutnumid + '\'" onclick = "openDialog()" class="btn btn-xs btn-primary">进度</button></a>';
                    //var  d = '<a href="cutnumServlet?action=watchProgress&cutnumid=\''+ row.cutnumid + '\'"><button  href = "javascript:void(0)" target="_blank" onclick = "openDialog()" data-id="98" class="btn btn-xs btn-primary">进度</button></a>';
                    return  d;
                }
            },
            {
                title: '查看',
                field: 'opr',
                width: 180,
                align: 'center',
                formatter: function (cellval, row) {
                    //var  d = '<a href="workpageSevrlet?action=single&workid=\''+ row.workid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                    var  d = '<a href="cutnumServlet?action=watch&cutnumid=\''+ row.cutnumid + '\'"><button  id="add" data-id="98" class="btn btn-xs btn-primary">查看</button></a>';
                    return  d;
                }
            },
            ],
        ]
    });
    }
</script>
</html>  
