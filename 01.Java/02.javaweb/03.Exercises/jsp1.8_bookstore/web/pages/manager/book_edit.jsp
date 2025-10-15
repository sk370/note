<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑图书</title>
    <%@ include file="/pages/common/head.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color: red;
        }

        input {
            text-align: center;
        }
    </style>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">编辑图书</span>
    <%@include file="/pages/common/manager_meun.jsp"%>
</div>

<div id="main">
    <form action="manage/bookServlet" method="get">
        <input type="hidden" value="${empty param.id ? "add" : "update"}" name="action"><%-- 由于添加图书和修改图书用的同一个页面，但后台对相应的方法不同，需要进行处理 --%>
<%--        方案1：请求的a标签href属性加上method属性，分别赋值为add和update，隐藏域的value使用${param.method}获取--%>
<%--        方案2：添加请求和修改请求区别在于修改请求【a标签的href属性】有id属性，隐藏域的value使用${empty param.id ? "add" : "update"}获取--%>
<%--        方案3：添加请求和修改请求区别在于修改时从服务器端返回了request域对象，隐藏域的value使用${empty requestScope.book ? "add" : "update"}获取--%>
        <input type="hidden" value="${requestScope.book.id}" name="id"><%-- 修改图书时需要id属性 --%>
        <input type="hidden" name="pageNo" value="${param.pageNo}"><%-- 修改或删除完图书,跳回当时的页面 --%>
        <table>
            <tr>
                <td>名称</td>
                <td>价格</td>
                <td>作者</td>
                <td>销量</td>
                <td>库存</td>
                <td colspan="2">操作</td>
            </tr>
            <tr>
                <td><input name="name" type="text" value="${requestScope.book.name}"></td>
                <td><input name="price" type="text" value="${requestScope.book.price}"/></td>
                <td><input name="author" type="text" value="${requestScope.book.author}"/></td>
                <td><input name="sales" type="text" value="${requestScope.book.sales}"/></td>
                <td><input name="stock" type="text" value="${requestScope.book.stock}"/></td>
                <td><input type="submit" value="提交"/></td>
            </tr>
        </table>
    </form>


</div>

<%@ include file="/pages/common/foot.jsp" %>>
</body>
</html>