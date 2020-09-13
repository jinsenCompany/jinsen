<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="jinshen.bean.*" %>
<%@ page import="java.util.List" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<title>木材检验野账</title>
<head>
<meta charset="utf-8">
<link type="text/css" rel="stylesheet" href="css/PrintArea.css" />
<link href="https://unpkg.com/bootstrap-table@1.17.1/dist/bootstrap-table.min.css" rel="stylesheet">

<script src="https://unpkg.com/bootstrap-table@1.17.1/dist/bootstrap-table.min.js"></script>
<script src="https://unpkg.com/bootstrap-table@1.17.1/dist/extensions/print/bootstrap-table-print.min.js"></script>
<style type="text/css">
			.mcjc{
				width:90%;text-align: center;border-collapse:collapse;
			}
			.mcjc input{
				width: auto;
			}
			 body,td,th {font-family: Verdana, Arial, Helvetica, sans-serif;font-size: 22px;color: #1d1007; line-height:24px}
		 .table1{width:80%;margin-left:auto; margin-right:auto;padding:10px;border-collapse:collapse}
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

<script type="text/javascript">    //主函数    
   function DXZH() {
   var n=$("#toltree").val();
   if (!/^(0|[1-9]\d*)(\.\d+)?$/.test(n))
   return "数据非法";
   var unit = "千百拾亿千百拾万千百拾元角分", str = "";
   n += "00";
   var p = n.indexOf('.');
   if (p >= 0)
   n = n.substring(0, p) + n.substr(p+1, 2);
   unit = unit.substr(unit.length - n.length);
   for (var i=0; i < n.length; i++)
   str += '零壹贰叁肆伍陆柒捌玖'.charAt(n.charAt(i)) + unit.charAt(i);
   var str1= str.replace(/零(千|百|拾)/g, "零").replace(/(零)+/g, "零").replace(/零(万|亿)/g, "$1").replace(/(亿)万|壹(拾)/g, "$1$2").replace(/^元零?|零分/g, "").replace(/元$/g, "元整");
   //alert('3256894.66 '+str1);
   document.getElementById("toltree1").value=str1;
   {
	   var t=$("#tolstere").val();
	   if (!/^(0|[1-9]\d*)(\.\d+)?$/.test(t))
	   return "数据非法";
	   var unit = "千百拾亿千百拾万千百拾元角分", str = "";
	   t += "00";
	   var p1 = t.indexOf('.');
	   if (p1 >= 0)
	   t = t.substring(0, p1) + t.substr(p1+1, 2);
	   unit = unit.substr(unit.length - t.length);
	   for (var i=0; i < t.length; i++)
	   str += '零壹贰叁肆伍陆柒捌玖'.charAt(t.charAt(i)) + unit.charAt(i);
	   var str1= str.replace(/零(千|百|拾|角)/g, "零").replace(/(零)+/g, "零").replace(/零(万|亿|元)/g, "$1").replace(/(亿)万|壹(拾)/g, "$1$2").replace(/^元零?|零分/g, "").replace(/元$/g, "元整");
	   //alert('3256894.66 '+str1);
	   document.getElementById("tolstere1").value=str1;
   }
}
   
   //以下为测试输出
   
   </script>
</head>
<% inyard i=(inyard)request.getAttribute("inyard"); 
List<tree> t=null;
t=(List<tree>)request.getAttribute("tree");
%>
<% workpage w=(workpage)request.getAttribute("workpage");%>
<% cutnum c=(cutnum)request.getAttribute("cutnum");%>
<% projectpackage p=(projectpackage)request.getAttribute("projectpackage");%>
<body>
      <div id="divprint">
             <h2 style="font-size:24px;text-align:center">木材检尺野账</h2>
            <table class="mcjc">
					<tr>
						<td colspan="1" style="font-size:24px">检验单位(印章)</td>
						<td colspan="7"></td>
					</tr>
            </table>
			<table class="mcjc" border="1" id="tablecenter">
					<tr>	
					<td style="width:10%">发货单位</td>
					<td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type="text" name="company" value="<%=c.getCompany() %>" /></td>
					<td style="width:10%">合同号</td>
					<td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type="text" name="" value="<%=p.getContractNum() %>" /></td>
					<td style="width:10%">工程包</td>
					<td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type="text" name="" value="<%=c.getProjectPackageName() %>" /></td>
					<td style="width:10%">施工方</td>
					<td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type="text" name="" value="<%=p.getContractionSide() %>" /></td>
				</tr>
				<tr>
					<td>收货单位</td>
					<td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type="text" name="" value="将乐县金森贸易有限公司" /></td>
					<td>检验地点(货场)</td>
					<!-- <td></td> -->
					<td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type="text" id="yard" name="yard" value="<%=i.getYard() %>" /></td>
					<td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type="text" id="section" name="section" value="<%=i.getSection() %>" /></td>
					<td>运输起讫</td>
					<td colspan="2"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type="text" name="" value="<%=w.getCutSite() %>" /></td>	
				</tr>
				
				<tr>
					<td colspan="1">有效时间</td>
					<td colspan="2"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type="text" name="" value="<%=w.getCutdate() %>" /></td>
					<td>车号</td>
					<td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type="text" id="carnumber" name="carnumber" value="<%=i.getCarNumber() %>" /></td>
					<td>调令通知单</td>
					<td colspan="2"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type="text" id="" name="" value="" /></td>
				</tr>
				<tr>
					<td>采伐证号</td>
					<td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type="text" name="" value="<%=w.getCutNum() %>" /></td>
					<td colspan="6">乡镇:<input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type="text" name="" value="<%=c.getCutaddress() %>" />
					村:<input type="text" style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="" value="<%=c.getCutvillage() %>" />
					<!--  土名<input type="text" style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="" value="" />-->
					林班:<input type="text" style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="" value="<%=c.getQuartel() %>" />
					大班:<input type="text" style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="" value="<%=c.getLargeblock() %>" />
					小班:<input type="text" style="border:0px;background-color: transparent;font-size:20px;text-align:center" disabled="disabled" value="<%=c.getSmallblock() %>" /></td>
					
				</tr>
				<tr>
					<th colspan="2">树材种</th>
					
					<th colspan="1">检尺长(m)</th>
					
					<th colspan="2">检尺径(cm)</th>
					
					<th colspan="1">根数</th>
				
					<th colspan="2">材积（m³、T、根）</th>	
				</tr>
				<tr>
				<% int j=1;%>
              <c:forEach items="${tree}" var="b">    
              <tr id="<%=j%>">
              <td colspan="2"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="treetype" id="sss<%=j%>" value="${b.getTreetype()}"></td>         
              <td colspan="1"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type='text' style='' name='tlong' id='tl<%=j%>' value="${b.getTlong()}"></td>
              <td colspan="2"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type='text' name='tradius' id='tr<%=j%>' value="${b.getTradius()}"></td>
              <td colspan="1"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type='text' name='num' id='nu<%=j%>' value="${b.getNum()}"></td>
               <td colspan="2"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" type='text' name='tvolume' id='tv<%=j%>' value="${b.getTvolume()}"></td></tr>
             <%j++; %>
            </c:forEach>
				</tr>

				<tr>
       <td rowspan="2">合计(大写)</td>
      <td colspan="4"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="toltree1" id="toltree1" value=""><span>根（块、件）</span></td>
      
      <td rowspan="2">合计(小写)</td>
      <td colspan="4"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="toltree" id="toltree" value="<%=i.getTolTree() %>"><span>根（块、件）</span></td>
      </tr>
      <tr>
      <td colspan="4"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="tolstere1" id="tolstere1"><span>立方米吨、根</span></td> 
      <td colspan="4"><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="tolstere" id="tolstere" value="<%=i.getTolStere()%>"><span>立方米吨、根</span></td> 
      </tr> 
			</table>
			<table class="table1">
   <tr>
   <td>检验员:</td><td><input type="text" style="border:0px;background-color: transparent;font-size:20px;text-align:center" name="surveyor" id="surveyor" value=""></td>
   <td>记码员:</td><td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center"></td>
   <td>发货人:</td><td><input style="border:0px;background-color: transparent;font-size:20px;text-align:center" ></td>
   <td>收货人:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
   <td>施工方:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
   <td>驾驶人:</td>
   </tr>
   </table>
   </div>
   <div style="text-align:center">      
     <span><input style="width:120px;heigth:60px" class="btn" type="button" id="btnPrint" value="打印"/></span>
     <input class="btn" type="button" onclick="btn_export()" value="导出">
      </div>
	</body>
<script src="js/xlsx.full.min.js"></script>
<script src="js/export.js"></script>
<script>
function btn_export() {
    var table1 = document.querySelector("#tablecenter");
    var opt = {
        rowIndex: 4
    }; //开头空4行
    var sheet = XLSX.utils.table_to_sheet(table1, opt);
    sheet["A1"] = {
        t: "s",
        v: '福建金森林业有限公司'
    }; //给A1单元格赋值
    sheet["A1"].s = {
        font: {
            name: '宋体',
            sz: 24,
            bold: true,
            underline: true,
            color: {
                rgb: "FFFFAA00"
            }
        },
        alignment: {
            horizontal: "center",
            vertical: "center",
            wrap_text: true
        },
        fill: {
            bgColor: {
                rgb: 'ffff00'
            }
        }
    };
    //["!merges"]这个属性是专门用来进行单元格合并的 
    sheet["!merges"].push({ //如果不为空push 为空 = 赋值
        //合并单元格 index都从0开始
        s: { //s开始
            c: 0, //开始列
            r: 0 //开始行
        },
        e: { //e结束
            c: 3, //结束列
            r: 2 //结束行
        }
    });
    sheet["A9"].s = { //样式
        font: {
            sz: 13,
            bold: true,
            color: {
                rgb: "FFFFAA00"
            }
        },
        alignment: {
            horizontal: "center",
            vertical: "center",
            wrap_text: true
        }
    };

    sheet["!cols"] = [{
        wpx: 70
    }, {
        wpx: 70
    }, {
        wpx: 70
    }, {
        wpx: 70
    }, {
        wpx: 150
    }, {
        wpx: 120
    }]; //单元格列宽

    openDownloadDialog(sheet2blob(sheet), '下载.xlsx');
}
</script>
	<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/jQuery.print.js"></script>
<script src="js/jquery.PrintArea.js" type="text/JavaScript"></script>
<script type="text/javascript">
$(function(){
    $("#btnPrint").click(function(){ $("#divprint").printArea(); });
});
</script>
</html>