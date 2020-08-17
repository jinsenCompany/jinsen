<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="jinshen.bean.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Connection"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>采伐证进程</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="css/registe.css"/>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/fullcalendar.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="css/jquery.gritter.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
<style>
.black_overlay{ 
            display: none; 
            position: absolute; 
            top: 0%; 
            left: 0%; 
            width: 100%; 
            height: 100%; 
            background-color: black; 
            z-index:1001; 
            -moz-opacity: 0.8; 
            opacity:.80; 
            filter: alpha(opacity=88); 
        } 
        .white_content { 
            display: none; 
            position: absolute; 
            top: 25%; 
            left: 25%; 
            width: 55%; 
            height: 55%; 
            padding: 20px; 
            border: 1px solid blue; 
            background-color: white; 
            z-index:1002; 
            overflow: auto; 
        }
 .table2{margin-left:auto; margin-right:auto;padding:10px;border-collapse:separate; border-spacing:2px 20px;CELLPADDING:5;CELLSPACING:5}
.table2 td{font-size:20pt}
</style>
</head>
<body>
<%cutnumProgress p=(cutnumProgress) request.getAttribute("cutnumProgress"); %>
<div class="row-fluid">
<table class="table2">
<tbody>
<caption class="book_h01">采伐证进度</caption>
<tr><td style="font-size:20pt">采伐证号</td><td><input class="text" name="cutnum" id="cutnum" value="<%=p.getCutnum() %>>"></td></tr>
<tr><td>工&nbsp;程&nbsp;包:&nbsp;</td><td><input class="text" name="projectPackageid" id="projectPackageid" value="<%=p.getProjectPackageid() %>>"></td></tr>
<tr><td>采伐地点:&nbsp;</td><td><input class="text" name="cutsite" id="cutsite" value="<%=p.getCutsite() %>>"></td></tr>
<tr><td>采伐面积(亩):&nbsp;</td><td><input class="text" name="cutarea" id="cutarea" value="<%=p.getCutarea() %>>"></td></tr>
<tr><td>采伐蓄积(立方米):&nbsp;</td><td><input class="text" name="cutstore" id="cutstore" value="<%=p.getCutstore() %>>"></td></tr>
<tr><td>采伐开始时间:&nbsp;</td><td><input class="date" name="starttime" id="starttime" value="<%=p.getStarttime() %>>"></td></tr>
<tr><td>采伐结束时间:&nbsp;</td><td><input class="date" name="endtime" id="endtime" value="<%=p.getEndtime() %>>"></td></tr>
</tbody>
</table>
</div> 
 
</body>
</html>