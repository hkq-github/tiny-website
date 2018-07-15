<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>搜索用户界面</title>
    <%@ include file="/WEB-INF/style.jsp" %>
</head>

<%@include file="/WEB-INF/admin/admin_header.jsp" %>

<div>
    <form action="${pageContext.request.contextPath}/admin/SearchController" method="get">
        <input type="text" name="searchText" />
        <input type="submit" value="筛选" />
        <input type="checkbox" name=fuzzySearch value="every_thing_is_ok" />模糊搜索
        <br/>
        <input type="radio" name="searchBy" value="id" checked="checked" />用户名 
        <input type="radio" name="searchBy" value="email" />邮箱 
        <input type="radio" name="searchBy" value="name" />昵称
    </form>
</div>

<%@include file="/WEB-INF/admin/admin_footer.jsp" %>
</html>