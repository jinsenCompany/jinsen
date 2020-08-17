<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>将图片储存在数据库中</title>
</head>
<script type="text/javascript">
//以下都为图片转base64格式
$(document).on("change","#file",function(){
    run(this, function (data) {
        $('#showImg').attr('src', data);
        $('#base64').val(data);
    });
});
function run(input_file, get_data) {
    /*input_file：文件按钮对象*/
    /*get_data: 转换成功后执行的方法*/
    if (typeof (FileReader) === 'undefined') {
        alert("抱歉，你的浏览器不支持 FileReader，不能将图片转换为Base64，请使用现代浏览器操作！");
    } else {
        try {
            /*图片转Base64 核心代码*/
            var file = input_file.files[0];
            //这里我们判断下类型如果不是图片就返回 去掉就可以上传任意文件
            if (!/image\/\w+/.test(file.type)) {
                alert("请确保文件为图像类型");
                return false;
            }
            var reader = new FileReader();
            reader.onload = function () {
                get_data(this.result);
            }
            reader.readAsDataURL(file);
        } catch (e) {
            alert('图片转Base64出错啦！' + e.toString())
        }
    }
}

//监听提交
form.on('submit(formDemo)', function(data){
    var index = parent.layer.getFrameIndex(window.name);
    $.ajax({
       url:"${pageContext.request.contextPath}/up_newsInsert",
       data:$("#insertForm").serialize(),
       type:"post",
       success:function(msg){
            if(msg.code==100){
                layer.msg("新增成功");
                parent.layer.close(index);
            }else{
                layer.msg("请检查信息有无错误！");
            }
       },
        error:function(){
            layer.msg("请检查信息有无错误！");
        }
    });
    return false;
});

</script>

<body>

<form  id="myForm" action="actorInfo_save" method="post" enctype="multipart/form-data">
    <table class="top-table">
        <tr><td class="top-table-label">输入采伐工单号：</td><td colspan="5"><input type="text" name="workid" id="workid"></td></tr>
        <tr><td class="top-table-label">上传照片</td><td colspan="5"><input class="filepath" onchange="changepic(this)" type="file" id="pic" name="pic"></td></tr>
        <tr><td class="top-table-label">上传文件</td><td colspan="5"><input type="file" name="da" id="da"></td></tr>
        <tr><td colspan="6" style="text-align: center"> <button class="add-but"  onclick="UpLoad()"><i class="glyphicon glyphicon-edit"></i> 提交</button>
    </table>
 </form>
</body>
</html>