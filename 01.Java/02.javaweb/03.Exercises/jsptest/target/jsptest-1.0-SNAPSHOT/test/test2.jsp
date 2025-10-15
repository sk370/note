<%@ page import="java.util.List" %>
<%@ page import="native_.pojo.Student" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: iceri
  Date: 2022/7/4
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        table{
            border-collapse: collapse;
        }
        td{
            padding: 4px 6px;
            border: 1px solid black;
        }
    </style>
</head>
<body>
<%
    List<Student> students = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
        students.add(new Student(i + 1, "name" + i, 18 + i, "phone" + i));
    }
%>
<table>
    <caption>学生信息表</caption>
    <tr>
        <td><%= "编号" %></td>
        <td><%= "姓名" %></td>
        <td><%= "年龄" %></td>
        <td><%= "电话" %></td>
    </tr>
    <% for (Student student : students){ %>
        <tr>
            <td><%= student.getId() %></td>
            <td><%= student.getName() %></td>
            <td><%= student.getAge() %></td>
            <td><%= student.getPhone() %></td>
        </tr>
    <% } %>
</table>


</body>
</html>
