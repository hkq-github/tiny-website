<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户登录界面</title>
    <%@ include file="/WEB-INF/style.jsp" %>
</head>
<%@ include file="/WEB-INF/client/header.jsp" %>
    <h3>用户登录</h3>
    
    <form action="${pageContext.request.contextPath}/LoginController" method="post">
        <div class="elem">
	        登录用户名：<br/><input type="text" name="id" ><br/>
	    </div>
	    <div class="elem">
	        密码：<br/><input type="password" name="pass"><br/>
	    </div>
	    <div class="elem">
            <input type="checkbox" name="recordPass" value="everything_is_ok"/>下次自动登录
        </div>
        <div class="elem">
            <input type="submit" value="登录" />
        </div>
    </form>
    
    <div>还没有帐号？<a href="${pageContext.request.contextPath}/signup" target="_self">注册一个吧</a></div>
    <div>管理员登录？<a href="${pageContext.request.contextPath}/admin/login" target="_self">Click here!</a></div>

<%@ include file="/WEB-INF/client/footer.jsp" %>

</html>