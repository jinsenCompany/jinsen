<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
    <head>

	<meta charset="UTF-8">
	<title>修改密码</title>

	<style type="text/css">
		#modal-overlay {
			visibility: hidden;
			position: absolute;
			/* 使用绝对定位或固定定位  */
			left: 0px;
			top: 0px;
			width: 100%;
			height: 100%;
			text-align: center;
			z-index: 1000;
			background-color: #8393a9;
			opacity: 0.9;
			/* 背景半透明 */
		}
		/* 模态框样式 */
		
		.modal-data {
			margin: 3rem auto;
			background-color: #87CEEB;
			border: 1px solid #87CEEB;
			padding: 0.3rem;
			text-align: center;
			border-radius: 0.5rem;
			box-shadow: 0 0 15px #f9f9f8;
			width: 36rem;
			height: 18rem;
		}
		
		.modal-data .sapnctyle0 {
			text-align: left;
			font-size: 0.4rem;
			display: inline-block;
			width: 90%;
			color: white;
		}
		
		.modal-data .sapnctyle1 {
			text-align: left;
			font-size: 0.4rem;
			display: inline-block;
			width: 9%;
		}
		
		.modal-data .classstyle0 {
			color: red;
			font-size: 0.3rem;
			text-align: left;
			margin-left: 0.24rem;
		}
		
		.modal-data .bottom {
			margin-top: 0.1rem;
		}
		
		.modal-data .trClass {
			width: 100%;
			padding: 0.2rem 0.5rem;
		}
		
		.modal-data .trClass .tdLabel {
			width: 9%;
			display: inline-block;
			text-align: right;
			font-size: 0.4rem;
			color: white;

		}
		
		.modal-data .trClass .tdInput {
			width: 70%;
			display: inline;
		}
		
		.modal-data .trClass .tdInput .divStyle {
			margin-left: 3.3rem;
		}
		
		.modal-data .trClass .tdInput .inputbox {
			width: 27rem;
            height: 1.7rem;
			font-size: 0.25rem;
			color: #000
		}
		
		.inputbox.change::-webkit-input-placeholder {
			color: red;
		}
		
		.modal-data .trClass .tdCancel {
			width: 20%;
			display: inline;
		}
		
		.modal-data .trClass .tdChoose {
			width: 20%;
			display: inline-block;
			margin-left: 1rem;
		}
		
		.bottomstyle {
			height: 2.65rem;
            width: 4.2rem;
			color: #3b4555;
			font-size: 0.4rem;
		}
		
		.lableStyle {
			display: inline-block;
			width: 9rem;
			background-color: #adb9cb;
			margin-top: 0.1rem;
			border-radius: 2rem;
			color: #f9f9f8;
		}
		
		.lableCountNumStyle {
			display: inline-block;
			width: 1.65rem;
			background-color: #adb9cb;
			border-radius: 1rem;
			text-align: center;
			color: #f9f9f8;
		}
	</style>
</head>

<body>
    <div><span onclick="overlay()">显示修改密码浮窗</sapn></div>
	<div style="width: 100%; height: 100%;">
		<div>
			<div class="modal-data">
				<span class="sapnctyle0">修改密码</span>
				<span class="sapnctyle1"></span>
				<p class="classstyle0">新密码长度必须是8位或8位以上，包含英文大、小写字母及阿拉伯数字， 且不能与原密码相同或用户名相同
				</p>
				<div class="bottom">
					<div class="trClass">
						<div class="tdLabel">原密码</div>
						<div class="tdInput">
							<input class="inputbox" id="initpw" type="password" οnpaste="return false;" οndragenter="return false;" οncοntextmen="return false"></input>
						</div>
					</div>
					<div class="trClass" style="padding-right: 0rem;">
						<div class="tdLabel" style="width: 13.5%;">新密码</div>
						<div class="tdInput">
							<input id="pw" class="inputbox" type="password" placeholder="" onKeyUp="checkStrong(value)"></input><label class="lableCountNumStyle">0</label>
							<div class="divStyle"><label class="lableStyle" id="jyxyzm">小写字母</label> <label class="lableStyle" id="jysz">数字</label> <label class="lableStyle" id="jydyzm">大写字母</label></div>
						</div>
					</div>

					<div class="trClass">
						<div class="tdLabel">确认密码</div>
						<div class="tdInput"><input id="repw" class="inputbox" type="password" placeholder="" onKeyUp=""></input>
						</div>
					</div>

					<div class="trClass">
						<div class="tdChoose">
							<button class="bottomstyle" onclick="submit()">确定</button>
						</div>
						<div class="tdCancel">
							<button class="bottomstyle" onclick="overlay()">取消</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	//html +css 模态框展示关闭事件
	function overlay() {
		var e1 = document.getElementById('modal-overlay');
		e1.style.visibility = (e1.style.visibility == "visible") ? "hidden" : "visible";
		$(".inputbox").val("").removeClass('change').attr("placeholder", ""); //清空输入框//清除自定义placeholde样式//清空placeholde文本
		/*$("#repw").attr("placeholder", "");*/
		$(".lableCountNumStyle").html('0');
		$('#jysz,#jyxyzm,#jydyzm,.lableCountNumStyle').removeAttr("style");
	}
	/*修改密码提交*/
	function submit() {
		if(checkStrong($("#pw").val()) && checkpassword() && ($("#initpw").val().length > 0)) {
			//发送请求       
			alert("请求成功");
		} else {
			if($("#initpw").val().length <= 0) {
				$("#initpw").val("");
				$("#initpw").addClass('inputbox change');
				$("#initpw").attr("placeholder", "原密码不能为空");
			}
			if(!checkpassword()) {
				$("#repw").val("");
				$("#repw").addClass('inputbox change');
				$("#repw").attr("placeholder", "两次密码输入不一致");
			}
			if(!checkStrong($("#pw").val())) {
				$("#pw").val("");
				$("#pw").addClass('inputbox change');
				$("#pw").attr("placeholder", "密码强度不够");
				$(".lableCountNumStyle").html('0');
				$('#jysz,#jyxyzm,#jydyzm,.lableCountNumStyle').removeAttr("style");
			}
		}
	}
	/*检测密码强度*/
	function checkStrong(value) {
		var strength = 0;
		/*value.match(/[\da-zA-Z]+/)*/
		if(value.length > 0) {
			/*匹配数字*/
			if(value.match(/\d+/)) {
				strength++;
				$("#jysz").css("background-color", "#339ed1");
			} else {
				$("#jysz").css("background-color", "#adb9cb");
			}
			/*匹配小写字母*/
			if(value.match(/[a-z]+/)) {
				strength++;
				$("#jyxyzm").css("background-color", "#339ed1");
			} else {
				$("#jyxyzm").css("background-color", "#adb9cb");
			}
			/*匹配大写字母*/
			if(value.match(/[A-Z]+/)) {
				strength++;
				$("#jydyzm").css("background-color", "#339ed1");
			} else {
				$("#jydyzm").css("background-color", "#adb9cb");
			}
			/*匹配特殊字符*/
			if(value.match(/[!@*$-_()+=&￥]+/)) {
				strength++;
			}
			$(".lableCountNumStyle").html(value.length);
			if(value.length > 7) {
				$(".lableCountNumStyle").css('background-color', '#339ed1')
			} else {
				$(".lableCountNumStyle").css('background-color', '#adb9cb')
			}
		} else {
			$(".lableCountNumStyle").html('0');
			$('#jysz,#jyxyzm,#jydyzm,.lableCountNumStyle').removeAttr("style");
		}
		if(strength >= 3) {
			return true;
		}
		return false;
	};
	/*检测密码是否一致*/
	function checkpassword() {
		var password = $("#pw").val();
		var repassword = $("#repw").val();

		if(password == repassword) {
			return true;
		} else {
			return false;
		}
	};
</script>
</html>


