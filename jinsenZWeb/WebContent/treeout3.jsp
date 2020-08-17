<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="jinshen.bean.*" %>
<%@ page import="java.util.List" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>木材产销管理平台导航</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link type="text/css" rel="stylesheet" href="css/PrintArea.css" />
</head>
<style>
 body,td,th {font-family: Verdana, Arial, Helvetica, sans-serif;font-size: 22px;color: #1d1007; line-height:24px}
 .table1{width:auto;margin-left:auto; margin-right:auto;padding:10px;border-collapse:collapse}
 .table1 td{width:100px}
 .btn{ 	
	font-family: "'微软雅黑','Helvetica Neue',Helvetica,Arial,sans-serif"; 	
	font-size: 13px!important; 	height: 30px; 	
	line-height: 18px!important; 	
	padding: 3px 18px; 	
	display: inline-block; 	vertical-align: middle; 	
	font-weight: normal; 	border-radius: 3px; 	
	margin: 0 8px 0 3px; 	
	border: 1px solid #3383da; 	
	color: #ffffff; 	
	background-color: #3383da; 	
	cursor: pointer; 
	}
</style>
<body>
<% saleCalloutOrder s=(saleCalloutOrder)request.getAttribute("saleCalloutOrder");%>
<% salecontract w=(salecontract)request.getAttribute("contractPrivoder");%>
<% customer c=(customer)request.getAttribute("compShip");%>
<% outyard o=(outyard)request.getAttribute("outyard");
List<tree> t=null;
   t=(List<tree>)request.getAttribute("tree");
%>
<!--main-container-part-->
<div id="divprint">
<!--breadcrumbs-->
    <table class="table1" border="1">
    <caption class="book_h01">木材销售码单</caption>
    <tbody>
    <tr>
    <td style="width:200px">供货单位</td><td><input style="border:0;background-color: transparent;font-size:20px" name="provider" id="provider" value="<%=w.getProvider() %>"></td>
    <td>合同号</td><td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="contractnum" id="contractnum" value="<%=s.getContractnum() %>"></td>
    <td>采伐证号</td><td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="cutnum" id="cutnum" value="<%=o.getCutnum() %>"></td>
    <td colspan="2">调运通知单号</td><td colspan="2"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="saleCalloutOrder" id="saleCalloutOrder" value="<%=s.getSaleCalloutOrder() %>"></td>
    </tr>
    <tr>
    <td>收货单位</td><td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="company" id="company" value="<%=c.getCompany() %>"></td>
    <td>检验地点(货场)</td><td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="yard" id="yard" value="<%=s.getYard() %>"></td>
    <td colspan="2">运输起讫</td><td colspan="4"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="shipaddress" id="shipaddress" value="<%=c.getShipaddress() %>"></td>
    </tr>
    <tr>
    <td>有效时间</td><td colspan="3"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="callidtime" id="callidtime" value="<%=s.getCallidtime() %>"></td>
    <td>车号</td><td colspan="2"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="carnumber" id="carnumber" value="<%=o.getCarNumber() %>"></td>
    <td>标品号</td><td colspan="2"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="marknumber" id="marknumber" value="<%=w.getMarknumber() %>"></td>
    </tr>
    </tbody>
    <tbody>
    <tr>
    <th colspan="2">树材种</th>
    <th colspan="2">检尺长(m)</th>
    <th colspan="2">检尺径(cm)</th>
    <th colspan="2">根数</th>
    <th colspan="2">材积（m³、T、根）</th>
    </tr>
    <% int i=1;%>
     <c:forEach items="${tree}" var="b">    
     <tr id="<%=i%>">
     <td colspan="2"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="treetype" id="sss<%=i%>" value="${b.getTreetype()}"></td>         
     <td colspan="2"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type='text' style='' name='tlong' id='tl<%=i%>' value="${b.getTlong()}"></td>
     <td colspan="2"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type='text' name='tradius' id='tr<%=i%>' value="${b.getTradius()}"></td>
     <td colspan="2"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type='text' name='num' id='nu<%=i%>' value="${b.getNum()}"></td>
     <td colspan="2"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type='text' name='tvolume' id='tv<%=i%>' value="${b.getTvolume()}"></td></tr>
          <%i++; %>
       </c:forEach>
       <tr>
       <td rowspan="2">合计(大写)</td>
      <td colspan="4"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="toltree" id="toltree" value=""><span>根（块、件）</span></td>
      
      <td rowspan="2">合计(小写)</td>
      <td colspan="4"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="toltree" id="toltree" value="<%=o.getToltree() %>"><span>根（块、件）</span></td>
      </tr>
      <tr>
      <td colspan="4"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="tolstere" id="tolstere"><span>立方米吨、根</span></td> 
      <td colspan="4"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="tolstere" id="tolstere" value="<%=o.getTolstere() %>"><span>立方米吨、根</span></td> 
      </tr>  
    </tbody>
    </table>
   <table class="table1">
   <tr>
   <td>检验员:</td><td><input type="text" style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="surveyor" id="surveyor" value="<%=o.getSurveyor() %>"></td>
   <td>记码员:</td><td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center"></td>
   <td>发货人:</td><td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" ></td>
   <td>收货人:</td>
   </tr>
   </table>
   
    <hr/>
</div>
<div style="text-align:center">
    <span><button style="width:120px;heigth:60px" class="btn" type="button"  value="返回" onclick="javascrtpt:window.location.href='treeoutCover.jsp'">返回添加</button></span>
                 
     <span><input style="width:120px;heigth:60px" class="btn" type="button" id="btnPrint" value="打印"/>  </span>
      </div>
</body>
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/jQuery.print.js"></script>
<script src="js/jquery.PrintArea.js" type="text/JavaScript"></script>
<script type="text/javascript">
$(function(){
    $("#btnPrint").click(function(){ $("#divprint").printArea(); });
});
</script>
</html>
