<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>修改密码</title>
    <%@ include file="/WEB-INF/style.jsp" %>
</head>
<%@ include file="/WEB-INF/client/header.jsp" %>

    <h3>修改个人密码</h3>
    <form action="${pageContext.request.contextPath}/ChangePassController" method="post">
        <div class="elem">
            当前密码：<input type="password" name="oldPass" />
        </div>
        <div class="elem">
            新密码：<input type="password" name="newPass1" />
        </div>
        <div class="elem">
            重新输入：<input type="password" name="newPass2" />
        </div>
        <div class="elem">
            <a href="${pageContext.request.contextPath}/home"> <input type="button" value="返回" /> </a>
	        <input type="submit" value="修改" name="" /><br/>
	    </div>
    </form>
    <br/>
    <div class="red_12size_text">*密码：8到16位密码；至少包含一个字母、一个数字、一个特殊符号</div>

<%@ include file="/WEB-INF/client/footer.jsp" %>
</html>