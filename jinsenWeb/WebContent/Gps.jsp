<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GPS输入界面</title>
<script src="js/jquery-1.11.1.min.js"></script>
<script>
function gpsz(){
		//dataurl:"http://api.heclouds.com/devices/606617689/datastreams",
		$.ajax({
		url:"gpsServlet",
		data:{
			action:"gpsv"
		},
		//data:{},
		type:"get",
		dataType: "json",
		success:function(result){
			alert(result);
			/*if(result){
				gpsdata.data=[];
				for(var i=0;i<result.length;i++){
					gpsdata.data.push(result[i].str);
				}
			}
			else{
				alert("获取数据失败");
			}*/
		}
		})
}
</script>
<script type="text/javascript">

</script>
</head>
<body>

<table>
<tr><td><button onclick="gpsz()">查询</button></td></tr>
</table>

</body>
</html>