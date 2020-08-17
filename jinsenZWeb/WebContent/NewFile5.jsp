<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>input框中自动展示当前日期</title>
</head>

<body>
<table>
<tr>
<td><input type='text' id='name'></td>
</tr>
<tr>
<td><input type="text" id="inputU" onclick="fun()"></td>
</tr>
</table>
    <input type="text" id="input">
    <script type="text/javascript">
    function fun(){
    	var _name=document.getElementById('name').value;
    	 var _input = document.getElementById('inputU');
         var name=_name.substring(2,1);
    	    var date = new Date();
           //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          // String now = df.format(date);
           var day=date.getDate();
           var hour=date.getHours();
           var mini=date.getMinutes();
           var second=date.getSeconds();
    	    var seperator = "/";
    	    var year = date.getFullYear();
    	    var month = date.getMonth() + 1;
    	    var strDate = date.getDate();
    	    if (month >= 1 && month <= 9) {
    	        month = "0" + month;
    	    }
    	    if (strDate >= 0 && strDate <= 9) {
    	        strDate = "0" + strDate;
    	    }
    	   // var currentDate = year + seperator + month + seperator + strDate; //当前日期
    	    //var currentDate = name + year + '7' +strDate; //当前日期
    	    //var currentDate = name + montht + day + hour + mini; //当前日期
    	     var currentDate = name + month + strDate + hour + mini + second; //当前日期
    	    _input.value = currentDate; //赋值给input.value
    	    _input.setAttribute('disabled', 'disabled'); //不可编辑
    }
    </script>
    <script>
    //获取input元素
    var _input = document.getElementById('input');

    var date = new Date();
    var seperator = "/";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentDate = year + seperator + month + seperator + strDate; //当前日期
    //var currentDate = year + '7' +strDate; //当前日期
    _input.value = currentDate; //赋值给input.value
    _input.setAttribute('disabled', 'disabled'); //不可编辑
    </script>
</body>

</html>