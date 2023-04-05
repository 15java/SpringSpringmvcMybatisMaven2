<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>

    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
    <script type="text/javascript">

        $(function () {
            $(".Delete").click(function () {
                var href = $(this).attr("href");//获取属性
                $("#Delete").attr("action", href).submit();//设置属性
                return false;//阻止原请求
            });


            $("#lang").change(function () {
                window.location.href = "query?locale=" + $(this).val();
            });


            $("#pageSize").change(function () {
                window.location.href = "query?pageNo=${page.pageNo -1}&pageSize=" + $(this).val();
            });
        });
    </script>
</head>
<body>
&nbsp;
<select id="lang">
    <option value="zh_CN"
            <c:if test="${requestScope.locale == 'zh_CN'}">selected</c:if> >简体中文
    </option>
    <option value="en_US"
            <c:if test="${requestScope.locale == 'en_US'}">selected</c:if> >English
    </option>
</select>

&nbsp;


<form action="" id="Delete" method="post">
    <input type="hidden" name="_method" value="Delete"/>
</form>


<table border="1" cellpadding="10" cellspacing="0">
    <tr>
        <td>ID</td>
        <td><fmt:message key="index.head"/></td>
        <td><fmt:message key="index.name"/></td>
        <td><fmt:message key="index.password"/></td>
        <td><fmt:message key="index.sex"/></td>
        <td><fmt:message key="index.age"/></td>
        <td><fmt:message key="index.salary"/></td>
        <td><fmt:message key="index.Email"/></td>
        <td><fmt:message key="index.city"/></td>
        <td><fmt:message key="index.group"/></td>
        <td><fmt:message key="index.Edit"/></td>
        <td><fmt:message key="index.Delete"/></td>
    </tr>

    <c:forEach items="${requestScope.page.list}" var="list">
        <tr>
            <td>${list.id}</td>
            <c:set value="${list.path}" var="path" scope="request"/>

            <td><c:if test="${list.path != null}"><img width="50" height="50" src="<%=request.getContextPath()%>/path?group=${list.group}&head=<%=java.net.URLEncoder.encode((String) request.getAttribute("path"))%>"/></c:if>

            <td>${list.name}</td>
            <td>${list.password}</td>
            <td>${list.sex == 0 ? '女':'男'}</td>
            <td>${list.age}</td>
            <td>${list.salary}</td>
            <td>${list.email}</td>
            <td>${list.addr.city}</td>
            <td>${list.group}</td>
            <td><a href="Update/${list.id}">Edit</a></td>
            <td><a class="Delete" href="Delete/${list.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<!-- 在表达式里面 requestScope 可以默认不写 -->
<a href="<%=request.getContextPath()%>/query?pageNo=1&pageSize=${page.pageSize}"><fmt:message key="index.shouye"/></a>&nbsp;
<c:if test="${page.pageNo -1!= 0}">
    <a href="<%=request.getContextPath()%>/query?pageNo=${page.pageNo -1}&pageSize=${page.pageSize}"><fmt:message
            key="index.shang"/></a>&nbsp;

</c:if>
<c:if test="${page.pageNo +1<= page.pageCount}">
    <a href="<%=request.getContextPath()%>/query?pageNo=${page.pageNo +1}&pageSize=${page.pageSize}"><fmt:message
            key="index.xia"/></a>&nbsp;
</c:if>
<a href="<%=request.getContextPath()%>/query?pageNo=${page.pageCount}&pageSize=${page.pageSize}"><fmt:message
        key="index.wei"/></a>&nbsp;
&nbsp;
<select id="pageSize">
    <option value="2"
            <c:if test="${page.pageSize == 2}">selected</c:if> >2
    </option>
    <option value="5"
            <c:if test="${page.pageSize == 5}">selected</c:if> >5
    </option>
    <option value="8"
            <c:if test="${page.pageSize == 8}">selected</c:if> >8
    </option>
</select>
&nbsp;

<a href="GetAdd"><fmt:message key="index.Add"/></a>

</body>
</html>