<%--
  Created by IntelliJ IDEA.
  User: iceri
  Date: 2022/7/5
  Time: 8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="fileUploadServlet" method="post" enctype="multipart/form-data">
    用户名：<input type="text" name="username"/>
    <br>${pageContext.request.contextPath}
    头像：<input type="file" name="photo"/>
    <br>
    <input type="submit"/>
</form>
</body>
</html>
