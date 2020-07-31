<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-CN">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

<!-- Loding font -->
<link href="https://fonts.googleapis.com/css?family=Montserrat:300,700"
	rel="stylesheet">

<!-- Custom Styles -->
<link rel="stylesheet" type="text/css" href="css/styles.css">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
<link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/moment.js"></script>
<title>Login</title>
<script type="text/javascript">
	function login() {

		var staff_id = document.getElementById('staff_id').value;
		var password = document.getElementById('password').value;
		var v_code0 = document.getElementById('v_code0').value;
		//var par="action="+login+"&username="+username+"&password="+password;
		//alert("66666");
		$.ajax({
			url : "managerServlet",
			type : "POST",
			data : {
				"staff_id" : staff_id,
				"action" : "login",
				"password" : password,
				"v_code0" :v_code0
			},
			dataType : "text",
			success : function(data) {
				alert(data);
				if (data == "fail") {
					alert("用户名或密码错误");
					window.location.href = 'login.jsp';
				} else if (data == "supermanager") {
					alert("超级管理员登录成功");
					window.location.href = 'superManage.jsp';
				} else if (data == "manager") {
					alert("管理部操作员登录成功");
					window.location.href = 'managerP.jsp';
				} else if (data == "managerS") {
					alert("管理部副经理登录成功");
					window.location.href = 'managePassis.jsp';
				}
				else if (data == "managerD") {
					alert("管理部经理登录成功");
					window.location.href = 'managePdirector.jsp';
				}
				else if (data == "planteam") {
					alert("规划队登录成功");
					window.location.href = 'planteam.jsp';
				}
				else if (data == "salarypers") {
					alert("销售部操作员登录成功");
					window.location.href = 'salaryper.jsp';
				}
				else if (data == "salaryperD") {
					alert("销售部经理登录成功");
					window.location.href = 'salesdirector.jsp';
				}
				else if (data == "salescontract") {
					alert("销售合同操作员登陆成功");
					window.location.href = 'salesmanOne.jsp';
				}
				else if (data == "salesorder") {
					alert("销售调令操作员登录成功");
					window.location.href = 'salesmanTwo.jsp';
				}
				else if (data == "salesprice") {
					alert("销售结算员登陆成功");
					window.location.href = 'salesmanThree.jsp';
				}
				else if (data == "YardmanagerD") {
					alert("货场经理登陆成功");
					window.location.href = 'yardDirector.jsp';
				}
				else if (data == "YardtableA") {
					alert("货场制表员A登录成功");
					window.location.href = 'yardstaffA.jsp';
				}
				else if (data == "YardtableB") {
					alert("货场制表员B登陆成功");
					window.location.href = 'yardstaffB.jsp';
				}
				else if (data == "YardtableC") {
					alert("货场制表员C登陆成功");
					window.location.href = 'yardstaffC.jsp';
				}
				else if (data == "Yardmanager") {
					alert("货场管理员登录成功");
					window.location.href = 'yardmanager.jsp';
				} else if (data == "Cutmanager") {
					alert("伐区监管员登录成功");
					window.location.href = 'forestP.jsp';
				}else if (data == "Surveyor") {
					alert("检尺员登陆成功");
					window.location.href = 'Surveyor.jsp';
				} else if (data == "InfoCenter") {
					alert("信息中心登陆成功");
					window.location.href = 'infoCenter.jsp';
				} else if (data == "验证码错误") {
					alert("验证码错误");
					window.location.href = 'login.jsp';
				}
			},
			error : function(e) {
				alert("dsds");
			}
		})
	}
</script>
<script src="js/jquery.js"></script>
<script src="js/jquery.singlePageNav.min.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
<script src="js/province.js"></script>
<script src="js/wow.min.js"></script>
</head>
<body>

	<!-- Backgrounds -->

	<div id="login-bg" class="container-fluid">
		<div class="bg-img"></div>

	</div>

	<!-- End Backgrounds -->

	<div class="container" id="login">
		<div class="row justify-content-center">
			<div class="col-lg-8">
				<div class="login">
					<h1 lign="center" style="color: white;">欢迎进入金森产销管理平台</h1>
					<!-- Loging form -->

					<div class="form-group">
						<input type="text" class="form-control" id="staff_id"
							placeholder="请输入员工号" style="width: 500px; height: 20px;">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" id="password"
							placeholder="请输入密码" style="width: 500px; height: 20px;">
					</div>
					
					<div class="form-group">
						<input type="text" class="form-control" id="v_code0"
							placeholder="请输入验证码" style="width: 500px; height: 20px;">
						<img src="/jinsenWeb/CheckCodeServlet" id="img" /> <a
							href="" id="a">看不清？点击换一张</a>
					</div>

					<div class="form-check">
						<label class="switch"> <input type="checkbox"> <!--span class="slider round"></span-->
						</label>


						<!--label class="form-check-label" for="exampleCheck1">记住密码</label-->
						<!--label class="form-check-label" for="">记住密码</label-->

						<label class="forgot-password"><a href="#">忘记密码</a></label>
						<!-- label class="forgot-password"><a href="#">忘记密码<a></label-->

					</div>

					<br>
					<!-- button type="submit" class="btn btn-lg btn-block btn-success">确认登录</button-->
					<button class="btn btn-lg btn-block btn-success" id="yes"
						onclick="login()" style="width: 400px; height: 50px;">确认登录</button>

					<!-- End Loging form -->

				</div>
			</div>
		</div>
	</div>


</body>
</html>