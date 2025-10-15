<%--
  Created by IntelliJ IDEA.
  User: iceri
  Date: 2022/7/4
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        td{
            padding: 4px 6px;
        }
    </style>
</head>
<body>
    <table>
        <% for (int i = 1; i < 9; i++) { %>
            <tr>
                <% for (int j = 1; j < i; j++) { %>
                    <td> <%= j + "x" + i + "=" + (i * j) %> </td>
                <% } %>
            </tr>
        <% } %>
    </table>
</body>
</html>
