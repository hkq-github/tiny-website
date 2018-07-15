<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户主页</title>
    <%@ include file="/WEB-INF/style.jsp" %>
</head>

<%@ include file="/WEB-INF/client/header.jsp" %>

<h3>用户主页</h3>

<div>
	<div>用户名：${sessionScope.self.id} </div>
	<div>昵称： ${sessionScope.self.name}</div>
	<div>邮箱： ${sessionScope.self.email}</div>
	<div>性别： ${sessionScope.self.sex}</div>
	<div>
	   简介： <br/>
	   <textarea rows="5" cols="50" readonly="readonly">${sessionScope.self.intro}</textarea>
    </div>
</div>	

<div>
	<a href="${pageContext.request.contextPath}/change_info" target="_self">>修改个人信息</a><br/>
	<a href="${pageContext.request.contextPath}/change_pass" target="_self">>修改密码</a>
</div>

<%@ include file="/WEB-INF/client/footer.jsp" %>
</html>