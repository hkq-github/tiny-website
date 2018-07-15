<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>管理员登录界面</title>
    <%@ include file="/WEB-INF/style.jsp" %>
</head>
<body id="admin_login_body">
    <h3>管理员登录</h3>
    
    <%-- 包含一个显示信息的request范围参数mess --%>
    <c:if test="${not empty requestScope.mess}">
       <div class="red_12size_text">${requestScope.mess}</div>
    </c:if>
    
    <form action="${pageContext.request.contextPath}/admin/LoginController" method="POST">
        <div class="elem">
	        登录用户名<br/> <input type="text" name="id" />
        </div>
        <div class="elem">
	        密码<br/> <input type="password" name="pass" />
        </div>
        <div class="elem">
            <input type="submit" value="登录" />
        </div>
    </form>
    <br/>
    <div>用户登录？<a href="${pageContext.request.contextPath}/login" target="_self">Click here!</a></div>
    <hr />
    
    <span class="red_12size_text">用户名：admin 密码：admin888<br/>已禁用删除用户功能<br/>重置密码：123456</span>
</body>
</html>