<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body id="admin_body">
    <div>
        <div class="web_logo">TinyWebsite管理系统</div>
        欢迎你：管理员&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/admin/home" target="_self">我的主页</a>&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/admin/LogoutController" target="_self">退出</a>  
    </div>
    <hr />
    <div>
    <%-- 包含一个显示信息的request范围参数mess --%>
    <c:if test="${not empty requestScope.mess}">
       <div class="red_12size_text">${requestScope.mess}</div>
    </c:if>