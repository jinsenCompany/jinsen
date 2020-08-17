<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>合同进度</title>
<style type="text/css">
    #progress:after {
        content: '%';
    }
</style>
</head>
<script type="text/javascript">
function progress(){
	$.ajax({
	    url:"salaryServlet",
	    data:{
	        "action":"contractListProg",
	    },
	    type: "get",
	    dataType:"html",
	    success: function (data) {
	    	if(data==null)
	    		{
	    	        alert("信息有误！");
	    		}
	    	else{
	    		 //alert(data);
	    		 var cp=data["contractProgress"];
	    		 
	    		 $("#sss").empty();
	    		 {
	    			 for(var i=0;i<cp.length;i++){
	    				 var j=cp[i];
	    				 var tolstere=j["tolstere"];
	    				 var treenumber=j["treenumber"];
	    				 alert(treenumber);
	    			 }
	    			 $("#sss").append(str2); 
	    		 }
}
		}
	    })

}
</script>
<body>
<div>
<button onclick="progress()" value="点击">单击</button>
</div>
</body>
</html>