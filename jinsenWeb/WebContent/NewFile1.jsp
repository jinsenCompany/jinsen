<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>My Test Document</title>

<style>
.box{
    width:50%; margin-top:10%; margin:auto; padding:28px;
    height:350px; border:1px #111 solid;
    display:none;            /* 默认对话框隐藏 */
}
.box.show{display:block;} 
.box .x{ font-size:18px; text-align:right; display:block;}
.box input{width:80%; font-size:18px; margin-top:18px;}
</style>
</head>  
<body> 
    
    <h2>测试</h2>
    <input type="button" onClick="msgbox(1)" value="点击弹出输入框">

    <script>  
        function msgbox(n){
            document.getElementById('inputbox').style.display=n?'block':'none';     /* 点击按钮打开/关闭 对话框 */
        }
     </script>  
     
     <div id='inputbox' class="box">
        <a class='x' href=''; onclick="msgbox(0); return false;">关闭</a>
        <input type="text">
        <input type="text">
        <input type="button" value="确定">
     </div>
</body> 
 

 

<div id="fade" class="black_overlay"></div>  

</body>  

</html>  
