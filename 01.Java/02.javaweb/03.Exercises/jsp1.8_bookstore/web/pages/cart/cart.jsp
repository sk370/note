<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <%@ include file="/pages/common/head.jsp" %>
    <style>
        input.change_count{
            width: 60px;
            text-align: center;
        }
    </style>
    <script>
        $(function (){

            $("a.delete_item").click(function (){
                return confirm("确定删除" + $(this).parent().parent().find("td:first").text() + "吗？");
            })
            $("a.clear_cart").click(function (){
                return confirm("确认清空购物车？");
            })
            $("input.change_count").change(function (){
                var name = $(this).parent().parent().find("td:first").text();
                var value = $(this).val();
                var bookId = $(this).attr("itemid")
                var stock = parseInt($(this).parent().parent().find("td.stock").text());
                // var stock = $(this).parent().parent().find("td:last").text();
                // console.log(stock);
                // console.log(typeof stock);
                if(confirm("确认要将 " + name + " 的数量修改为" + value + "吗？")){
                    if(this.value > stock){
                        alert("库存不足！库存数量为：" + stock);
                        this.value = this.defaultValue;
                    }else{
                        location.href = "${pageContext.request.contextPath}/cartServlet?action=updateItem&id=" + bookId + "&count=" + value;
                    }
                }else{
                    //defaultValue是表单项默认的值，即原始值【input标签value属性的值】
                    // this.value = this.defaultValue;//这一句可以不用写，因为change事件文本框内容未变化就不会改变
                }
            })
        })
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">购物车</span>
    <%@ include file="/pages/common/login_success_menu.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>

        <c:if test="${empty sessionScope.cart.items}">
            <%--如果购物车空的情况--%>
            <tr>
                <td colspan="5"><a href="index.jsp">亲，当前购物车为空！快跟小伙伴们去浏览商品吧！！！</a> </td>
            </tr>
        </c:if>
        <c:if test="${not empty sessionScope.cart.items}">
            <c:forEach items="${sessionScope.cart.items}" var="entry">
                <tr>
                    <td>${entry.value.name}</td>  <%--     实际应为entry.getValue()，但由于EL表达式中，访问属性名，默认调用的是getter，所以也能获取         --%>
                    <td>
                        <input itemid="${entry.value.id}" class="change_count" type="text" value="${entry.value.count}">
                    </td>
                    <td>${entry.value.price}</td>
                    <td>${entry.value.totalPrice}</td>
                    <td class="stock" style="display: none;">${entry.value.stock}</td>
                    <td><a class="delete_item" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>

    <c:if test="${not empty sessionScope.cart.items}">
        <div class="cart_info">
            <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
            <span class="cart_span"><a class="clear_cart" href="cartServlet?action=clear">清空购物车</a></span>
            <span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
        </div>
    </c:if>
</div>

<%@ include file="/pages/common/foot.jsp" %>
</body>
</html>