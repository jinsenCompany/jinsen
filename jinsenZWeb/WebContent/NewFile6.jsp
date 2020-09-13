<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
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
<link rel="stylesheet" href="css/bootstrap-table.min.css" />
<script src="js/bootstrap-table-export.js"></script>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="js/jquery.js"></script>
    <script src="js/bstable/js/bootstrap.min.js"></script>
    <script src="js/bstable/js/bootstrap-table.js"></script>
    <script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
    <!-- 引入bootstrap样式 -->
    <script type="application/javascript">
        $(function(){
        	initTable();
        })
        function initTable(){
            $("#display_result").bootstrapTable({
            	url: "cutnumServlet?action=cutnumproject",
                data:{},
              //data:{},
                dataType: "json",
                method: 'get',
                striped: true, //是否显示行间隔色
                detailView: true,//父子表
                pagination: true,//显示分页
                sidePagination: "client",
                pageNumber:1,
                pageSize: 20,
                pageList: [10, 25],
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
                columns: [{
                    title: "采伐证编号",
                    field: 'cutnum',
                    width:200,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐证号',
                    field: 'certificatenum',
                    width:200,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采法许开始时间',
                    field: 'starttime',
                    width:200,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采法许结束时间',
                    field: 'endtime',
                    width:200,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '林权单位',
                    field: 'company',
                    width:200,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '采伐面积',
                    field: 'cutarea',
                    width:200,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '出材量',
                    field: 'volume',
                    width:200,
                    align: 'center',
                    valign: 'middle'
                },],
                //注册加载子表的事件。注意下这里的三个参数！
                onExpandRow: function (index, row, $detail) {
                    initSubTable(index, row, $detail);
                }
            });
            //初始化子表格(无线循环)
            initSubTable = function (index, row, $detail) {
                var parentid = row.id;
                var cur_table = $detail.html('<table></table>').find('table');
                $(cur_table).bootstrapTable({
                    url: '/db/log/files.do',
                    method: 'get',
                    queryParams: { id: parentid },
                    ajaxOptions: { id: parentid },
                    uniqueId: "id",
                    striped: true, //是否显示行间隔色
                    pagination: true,//显示分页
                    sidePagination: "client",
                    pageNumber:1,
                    pageSize: 15,
                    pageList: [10, 25],
                    columns: [{
                        field: 'fileType',
                        title: '文件类型',
                    }, {
                        field: 'fileName',
                        title: '文件名'
                    }, {
                        field: 'fileDir',
                        title: '保存目录'
                    }, {
                        field: 'category1',
                        title: '推荐标签'
                    }, {
                        field: 'category2',
                        title: '自定义标签'
                    },{
                        field: 'fileSize',
                        title: '文件大小'
                    },],
                    //无线循环取子表，直到子表里面没有记录
                    onExpandRow: function (index, row, $Subdetail) {
                        initSubTable(index, row, $Subdetail);
                    }
                });
            };
        }
    </script>
</head>
<body>
<h3>文档上传日志查询</h3>
<div id="showResult" style="width: 1000px;" >
    <table  id="display_result" class="table-style" style="text-align: center">

    </table>
</div>
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