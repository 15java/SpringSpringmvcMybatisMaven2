<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
    <script type="text/javascript">

    </script>
    <title>Add</title>
</head>
<body>

<!-- modelAtribute 是request 里面传输过来的属性 默认叫command  multipart/form-data 加上这个才能上传二进制文件-->
<%--@elvariable id="user" type=""--%>
<form:form action="${pageContext.request.contextPath}/add" modelAttribute="user" method="post"
           enctype="multipart/form-data">
    <c:if test="${user.path != null}">
        <c:set value="${user.path}" var="path" scope="request"/>
        <img width="75" height="75"
             src="<%=request.getContextPath()%>/ResponseEntityHead?path=<%=java.net.URLEncoder.encode((String) request.getAttribute("path"))%>"/><br/></c:if>
    <fmt:message key="index.head"/>:<input type="file" name="head"/><br/>

    <!-- 如果有ID 就表示修改，修改是put请求 而且名字是不能修改的-->
    <c:if test="${user.id == 0}"><fmt:message key="index.name"/>:<form:input path="name"/><font color="red"><form:errors
            path="name"></form:errors></font><br/></c:if>

    <c:if test="${user.id != 0}"><form:hidden path="id"/><input type="hidden" name="_method" value="PUT"/></c:if>


    <fmt:message key="index.password"/>:<form:input path="password"/><font color="red"><form:errors
        path="password"></form:errors></font><br/>
    <fmt:message key="index.sex"/>:<form:radiobuttons path="sex" items="${sex}"/><font color="red"><form:errors
        path="sex"></form:errors></font><br/>
    <fmt:message key="index.age"/>:<form:input path="age"/><font color="red"><form:errors
        path="age"></form:errors></font><br/>
    <fmt:message key="index.city"/>:<form:select path="addr.id" items="${addr}" itemValue="id" itemLabel="city"/><br/>
    <fmt:message key="index.birth"/>:<form:input path="birth"/><font color="red"><form:errors
        path="birth"></form:errors></font><br/>
    <fmt:message key="index.salary"/>:<form:input path="salary"/><font color="red"><form:errors
        path="salary"></form:errors></font><br/>
    <fmt:message key="index.Email"/>:<form:input path="email"/><font color="red"><form:errors
        path="email"></form:errors></font><br/>
    <input type="submit" value="提交"/>
</form:form>


</body>
</html>
