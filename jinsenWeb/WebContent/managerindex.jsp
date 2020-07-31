<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>人员信息管理</title>
    <link rel="stylesheet" href="css/all.css">
    <link rel="stylesheet" href="js/jQueryCalendar/calendar.css">
    <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
    <!-- font-awesome icons CSS -->
    <link href="css/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons CSS-->

    <!-- side nav css file -->
    <link href='css/SidebarNav.min.css' media='all' rel='stylesheet' type='text/css'/>
    <!-- //side nav css file -->
    <link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/fullcalendar.css" />
<link rel="stylesheet" href="css/matrix-style.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="css/jquery.gritter.css" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>

    <!-- js-->
    <script src="js/jquery-1.11.1.min.js"></script>
    <script src="js/modernizr.custom.js"></script>
    <style>
    </style>
    <link href="css/registe.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        .table_p{line-height: 28px;border-bottom: 1px #d0e6ec solid;position: relative;margin-bottom: 10px;
            margin-top: 35px;}
        .table_p span{border-bottom: 3px #42cdec solid;display: inline-block;position: absolute;bottom: -1px;font-weight: bold;font-size: 20px}
        .but_p button{width: 80px}
body {
	overflow-x: hidden;
	margin-top: -10px; font-family: 'Open Sans', sans-serif; font-size:20px; color:#666;
}
    </style>
</head>
<body>
<!--Header-part-->
<div id="header">
  <h1><a href="dashboard.html">木材产销管理平台导航</a></h1>
</div>
<!--close-Header-part--> 
<!--top-Header-menu-->
<div id="user-nav" class="navbar navbar-inverse">
  <ul class="nav">
    <li  class="dropdown" id="profile-messages" ><a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle"><i class="icon icon-user"></i>  <span class="text">欢迎使用者</span><b class="caret"></b></a>
      <ul class="dropdown-menu">
        <li><a href="#"><i class="icon-user"></i> 我的个人资料 </a></li>
        <li class="divider"></li>
        <li><a href="#"><i class="icon-check"></i> 我的任务</a></li>
        <li class="divider"></li>
        <li><a href="login.jsp"><i class="icon-key"></i> 注销</a></li>
      </ul>
    </li>
    <li class="dropdown" id="menu-messages"><a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle"><i class="icon icon-envelope"></i> <span class="text">消息</span> <span class="label label-important">5</span> <b class="caret"></b></a>
      <ul class="dropdown-menu">
        <li><a class="sAdd" title="" href="#"><i class="icon-plus"></i> 系的消息</a></li>
        <li class="divider"></li>
        <li><a class="sInbox" title="" href="#"><i class="icon-envelope"></i> 收件箱</a></li>
        <li class="divider"></li>
        <li><a class="sOutbox" title="" href="#"><i class="icon-arrow-up"></i> 发件箱</a></li>
        <li class="divider"></li>
        <li><a class="sTrash" title="" href="#"><i class="icon-trash"></i> 垃圾箱</a></li>
      </ul>
    </li>
    <li class=""><a title="" href="#"><i class="icon icon-cog"></i> <span class="text">设置</span></a></li>
    <li class=""><a title="" href="login.jsp"><i class="icon icon-share-alt"></i> <span class="text">注销</span></a></li>
  </ul>
</div>
<!--close-top-Header-menu-->
<!--sidebar-menu-->
<div id="sidebar" style="font-size:18px"><a href="#" class="visible-phone"><i class="icon icon-home"></i> 仪表盘</a>
  <ul>
   <li><a href="superManage.jsp"><i class="icon icon-home" font-size="20px"></i> <span>返回超级管理员界面</span></a> </li>
    <li class="active"><a href="managerindex.jsp"><i class="icon icon-th-list"></i> <span>用户管理</span></a> </li>
    <li> <a href="rolemanage.jsp"><i class="icon icon-inbox"></i> <span>角色管理</span></a> </li>
    </ul>

    </div>
<div id="content">
<!--breadcrumbs-->
  <div id="content-header">
    <div id="breadcrumb"> <a href="hello2.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
  </div>
<!--End-breadcrumbs-->

<main class="all">
    <article class="artlce">
        <section class="section">
            <iframe class="ifraem-style" frameborder="0" src="usermanage2.jsp" name="fraName"></iframe>

        </section>
    </article>
</main>
</div>
<script src="js/jquery.js"></script>
<script src="js/jQueryCalendar/js/calendar.js"></script>
<script>
    // $('#ca').calendar({
    //     width: 240,
    //     height: 280,
    //     data: [
    //         {
    //             date: '2015/12/24',
    //             value: 'Christmas Eve'
    //         },
    //         {
    //             date: '2015/12/25',
    //             value: 'Merry Christmas'
    //         },
    //         {
    //             date: '2016/01/01',
    //             value: 'Happy New Year'
    //         }
    //     ],
    //     onSelected: function (view, date, data) {
    //         console.log('view:' + view)
    //         alert('date:' + date)
    //         console.log('data:' + (data || 'None'));
    //     }
    // });
    $(function(){
        $(".nav-ul li").click(function(){
            $(this).addClass("active-li").siblings().removeClass("active-li");
            $(this).find("ul").show();
            $(this).siblings().find('ul').hide();
        })
    })
</script>
</body>
</html>