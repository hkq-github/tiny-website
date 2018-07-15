<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>修改个人信息</title>
    <%@ include file="/WEB-INF/style.jsp" %>
</head>
<%@ include file="/WEB-INF/client/header.jsp" %>
    <h3>修改个人信息</h3>
    <form action="${pageContext.request.contextPath}/ChangeInfoController" method="Post">
        <div class="elem">
            用户名：<input type="text" value="${sessionScope.self.id }" readonly="readonly"/>
        </div>
        <div class="elem">
            昵称：<input type="text" value="${sessionScope.self.name }" name="name" />
        </div>
        <div class="elem">
            邮箱：<input type="text" value="${sessionScope.self.email }" name="email" />
        </div>
        <div class="elem">
	        简介：<br/>
	        <textarea name="intro" rows="5" cols="35">${sessionScope.self.intro}</textarea>
        </div>
        <div class="elem">
            <a href="${pageContext.request.contextPath}/home"> <input type="button" value="返回" /> </a>
	        <input type="submit" value="修改" />
        </div>
    </form>
    <div class="red_12size_text">* 用户名：1到10个字符，由汉字、数字、英文字母、连字符构成</div>
    
<%@ include file="/WEB-INF/client/footer.jsp" %>
</html>