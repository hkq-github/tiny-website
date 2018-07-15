<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>管理员主页</title>
	<%@ include file="/WEB-INF/style.jsp" %>
</head>

<%@include file="/WEB-INF/admin/admin_header.jsp" %>

<div>
	<a href="${pageContext.request.contextPath}/admin/AllUserController">所有用户</a>&nbsp;&nbsp;|
	<a href="${pageContext.request.contextPath}/admin/search">高级搜索</a>&nbsp;&nbsp;|
	<a href="#">可以添加功能</a>
</div>

<%@include file="/WEB-INF/admin/admin_footer.jsp" %>
</html>