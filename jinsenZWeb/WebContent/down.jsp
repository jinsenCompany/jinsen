<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>下载文件显示页面</title>
  </head>

  <body>
  <%
   String fileName = request.getParameter("filename");
  %>
        <%=fileName%><a href="DownLoadServlet?filename=<%=fileName%>">下载</a>
<img src="DownLoadServlet?filename=<%=fileName%>" alt="111"  width="170" height="110" />
  </body>
</html>