<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>伐区管理</title>
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
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="css/jquery.gritter.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
<link type="text/css" rel="stylesheet" href="css/PrintArea.css" />
</head>
<script type="text/javascript">
function search(){
	var cutnum = document.getElementById("cutnum").value;
	alert(cutnum)
	$.ajax({
        url:"salaryServlet",
        data:{
            "action":"findCutarea",
            "cutnum":cutnum
        },
        type: "POST",
        dataType:"json",
        success: function (data) {
        	var cutCompack=data["cutCompack"];
        	alert(cutCompack.length)
        	for(var i=0;i<cutCompack.length;i++)
    		{
        		var j=cutCompack[i];
        		var company=j.company;
        		var certificatenum=j.certificatenum;
        		var forestid=j.forestid;
        		var cutaddress=j.cutaddress;
        		var cutvillage=j.cutvillage;
        		var quartel=j.quartel;
        		var largeblock=j.largeblock;
        		var smallblock=j.smallblock;
        		var cuttype=j.cuttype;
        		var cutmethod=j.cutmethod;
        		var cutqiang=j.cutqiang;
        		var cutarea=j.cutarea;
        		var starttime=j.starttime;
        		var endtime=j.endtime;
    		}
        	document.getElementById("company").value = company;
            document.getElementById("certificatenum").value = certificatenum;	
            document.getElementById("forestid").value =forestid;
            document.getElementById("cutaddress").value =cutaddress;
            document.getElementById("cutvillage").value =cutvillage;
            document.getElementById("quartel").value = quartel;
            document.getElementById("largeblock").value = largeblock;
            document.getElementById("smallblock").value =smallblock;
            document.getElementById("cuttype").value = cuttype;
            document.getElementById("cutmethod").value = cutmethod;
            document.getElementById("cutqiang").value = cutqiang;
            document.getElementById("cutarea").value = cutarea;
            document.getElementById("starttime").value = starttime;
            document.getElementById("endtime").value = endtime;
        	}
        })
}
</script>
<body>
<p>伐区质量进度表</p>
	<table border="1">
		<tr>
			<td>林权单位</td>
			<td><input type="text" value="" id="company" ></td>
			<td>采伐证号</td>
			<td><input type="text"value="" id="certificatenum" ></td>
		
		</tr>
		
		<tr>
			<td>林权证号</td>
			<td><input type="text" value="" id="forestid"></td>
			<td>采伐证编号</td>
			<td><input type="text" value=""  id="cutnum" ></td>
			<td><input type="button" value="更新"  onclick="search()" ></td>
		
		</tr>
	
		<tr>
			<td>乡</td>
			<td><input type="text" value="" id="cutaddress"></td>
			<td>村</td>
			<td><input type="text" value="" id="cutvillage"></td>
			<td>林班</td>
			<td><input type="text"value="" id="quartel"></td>
			<td>大班</td>
			<td><input type="text"value="" id="largeblock"></td>
			<td>小班</td>
			<td><input type="text" value=""id="smallblock"></td>
		</tr>
		<tr>
			<td>采伐类型</td>
			<td><input type="text" value=""id="cuttype"></td>
			<td>采伐方式</td>
			<td><input type="text" value=""id="cutmethod"></td>
		
		</tr>
		<tr>
			<td>采伐强度</td>
			<td><input type="text" value=""id="cutqiang"></td>
			<td>采伐面积</td>
			<td><input type="text" value=""id="cutarea"></td>
			<td>开始时间</td>
			<td><input type="text" value=""id="starttime"></td>
			<td>结束时间</td>
			<td><input type="text" value=""id="endtime"></td>
		</tr>
		
	</table>
	
	<input type="button" id="btn1" value="质量检查卡"  onclick="add()" > 
	<div id="div1"><form action="addServlet"><input type="submit" value="提交" id="btn"></form></div>
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
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/jQuery.print.js"></script>
<script src="js/jquery.PrintArea.js" type="text/JavaScript"></script>
</html>