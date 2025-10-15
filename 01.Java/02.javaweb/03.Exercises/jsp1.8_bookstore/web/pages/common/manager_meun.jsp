<%--
  Created by IntelliJ IDEA.
  User: iceri
  Date: 2022/7/5
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
<%--    <a href="pages/manager/book_manager.jsp">图书管理</a>--%>
<%--    <a href="bookServlet?action=query">图书管理</a>&lt;%&ndash;  页面如果写成jsp文件，则无法实现获取数据库的数据，所以先设置为向后台服务器发送请求，后台服务器拿到请求后去数据库找数据【转到BookServlet】 &ndash;%&gt;--%>
<%--    <a href="manage/bookServlet?action=query">图书管理</a>&lt;%&ndash;  直接跳转到后台程序有权限隐患 &ndash;%&gt;--%>
    <a href="manage/bookServlet?action=page">图书管理</a><%--  跳转到查询结果页面前,先到page页面,看看怎么分页 --%>
    <a href="pages/manager/order_manager.jsp">订单管理</a>
    <a href="order_manager.jsp">订单管理</a>
    <a href="index.jsp">返回商城</a>
</div>
