function vvvolume()
	{
		var td=$("#td").val();
		var tr=$("#tr").val();
		var num=$("#n").val();
		if(num!="" && td!="" && tr!="")
		{
			$.ajax({
		        url:"treeServlet",
		        data:{
		            "action":"volume",
		            "td":td,
		            "tr":tr 
		        },
		        type: "POST",
		        dataType:"html",
		        success: function (data) {
		        	document.getElementById("tv").value=Number(data*num);
		        }
	  })}
	}