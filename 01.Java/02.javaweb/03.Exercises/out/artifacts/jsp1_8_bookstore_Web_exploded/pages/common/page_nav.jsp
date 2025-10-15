<%--
  Created by IntelliJ IDEA.
  User: iceri
  Date: 2022/7/7
  Time: 8:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <%--        1. 处于第一页时,页面不显示首页和第一页--%>
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>
    <%--                2. 页码输出显示【假设页面上只显示5个可点击的页码】--%>
    <%--
                    <c:choose>
            &lt;%&ndash;        2.1 总页码少于5时,可点击页面为:页码数-1&ndash;%&gt;
                        <c:when test="${requestScope.page.pageTotal <= 5}">
                            <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
                                <c:if test="${i==requestScope.page.pageNo}">
                                    【${i}】
                                </c:if>
                                <c:if test="${i!=requestScope.page.pageNo}">
                                    <a href="manage/bookServlet?action=page&pageNo=${i}">${i}</a>
                                </c:if>
                            </c:forEach>
                        </c:when>

            &lt;%&ndash;        2.2 总页码大于5时&ndash;%&gt;
                        <c:when test="${requestScope.page.pageTotal >5}">
            &lt;%&ndash;                2.2.1&ndash;%&gt;
                            <c:choose>
                                <c:when test="${requestScope.page.pageNo <= 3}">
                                    <c:forEach begin="1" end="5" var="i">
                                        <c:if test="${i==requestScope.page.pageNo}">
                                            【${i}】
                                        </c:if>
                                        <c:if test="${i!=requestScope.page.pageNo}">
                                            <a href="manage/bookServlet?action=page&pageNo=${i}">${i}</a>
                                        </c:if>
                                    </c:forEach>
                                </c:when>

            &lt;%&ndash;                2.2.2&ndash;%&gt;
                                <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
                                    <c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}" var="i">
                                        <c:if test="${i==requestScope.page.pageNo}">
                                            【${i}】
                                        </c:if>
                                        <c:if test="${i!=requestScope.page.pageNo}">
                                            <a href="manage/bookServlet?action=page&pageNo=${i}">${i}</a>
                                        </c:if>
                                    </c:forEach>
                                </c:when>

                &lt;%&ndash;                2.2.3&ndash;%&gt;
                                <c:otherwise>
                                    <c:forEach begin="${requestScope.page.pageNo-2}" end="${requestScope.page.pageNo+2}" var="i">
                                        <c:if test="${i==requestScope.page.pageNo}">
                                            【${i}】
                                        </c:if>
                                        <c:if test="${i!=requestScope.page.pageNo}">
                                            <a href="manage/bookServlet?action=page&pageNo=${i}">${i}</a>
                                        </c:if>
                                    </c:forEach>
                                </c:otherwise>

                            </c:choose>
                        </c:when>
                    </c:choose>
    --%>

    <%--        ====优化=====     --%>
    <c:choose>
        <%--        2.1 总页码少于5时,可点击页面为:页码数-1--%>
        <c:when test="${requestScope.page.pageTotal <= 5}">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${requestScope.page.pageTotal}"/>
        </c:when>

        <%--        2.2 总页码大于5时--%>
        <c:when test="${requestScope.page.pageTotal >5}">
            <%--                2.2.1--%>
            <c:choose>
                <c:when test="${requestScope.page.pageNo <= 3}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>

                <%--                2.2.2--%>
                <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
                    <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                    <c:set var="end" value="${requestScope.page.pageTotal}"/>
                </c:when>

                <%--                2.2.3--%>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo-2}"/>
                    <c:set var="end" value="${requestScope.page.pageNo+2}"/>
                </c:otherwise>

            </c:choose>
        </c:when>
    </c:choose>

    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i==requestScope.page.pageNo}">
            【${i}】
        </c:if>
        <c:if test="${i!=requestScope.page.pageNo}">
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>

    <%--        3. 处于最后一页时，末页和最后一页不显示--%>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页<%-- ${param.pageNo}是获取地址栏的值，写到input框里--%>
    <input id="searchPageButton" type="button" value="确定">

        <script>
            $(function () {
                //2.跳转到指定页功能
                $("#searchPageButton").click(function () {
                    var pageNo = $("#pn_input").val();
                    if (pageNo < 1){
                        pageNo = 1;
                    } else if( pageNo >${requestScope.page.pageTotal}) {
                        pageNo = ${requestScope.page.pageTotal};
                    }
                    location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;
                })
            })
        </script>
</div>
