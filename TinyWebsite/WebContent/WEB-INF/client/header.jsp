<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body id="client_body">
	<div>
	    <div class="web_logo">TinyWebsite</div>
		
		<c:choose>
			<c:when test="${not empty sessionScope.self }">
			    欢迎你：${sessionScope.self.name}&nbsp;&nbsp;
			    在线人数：<c:out value="${applicationScope.onlineCount}" default="" /> &nbsp;&nbsp;
			    <a href="${pageContext.request.contextPath}/home" target="_self">我的主页</a>&nbsp;&nbsp;
	            <a href="${pageContext.request.contextPath}/LogoutController" target="_self">退出</a>
			</c:when>
			<c:otherwise>
			    欢迎你：游客
		    </c:otherwise>
		</c:choose>
		
	</div>
	<hr />
	<div>
	<%-- 包含一个显示信息的request范围参数mess --%>
	<c:if test="${not empty requestScope.mess}">
	   <div class="red_12size_text">${requestScope.mess}</div>
	</c:if>