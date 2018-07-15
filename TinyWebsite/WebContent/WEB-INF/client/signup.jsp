<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户注册界面</title>
    <%@ include file="/WEB-INF/style.jsp" %>
</head>

<%@ include file="/WEB-INF/client/header.jsp" %>
    
    <h3>用户注册</h3>
    <form action="${pageContext.request.contextPath}/SignupController" method="POST">
        <div class="elem">
	        用户名：<input type="text" name="id" /><br/>
	        <div class="red_12size_text">1到10个字符，由数字、英文字母、连字符构成</div>
	    </div>
	    <div class="elem">
	        密码：<br>
	        <input type="password" name="pass1" placeholder="登录密码"/><br/>
	        <input type="password" name="pass2" placeholder="确认密码"/><br/>
	        <div class="red_12size_text">8到16位密码；至少包含一个字母、一个数字、一个特殊符号</div>
	    </div>
	    <div class="elem">
	        昵称：<input type="text" name="name" /><br/>
	        <div class="red_12size_text">1到10个字符，由汉字、数字、英文字母、连字符构成</div>
	    </div>
	    <div class="elem">
	        邮箱：<input type="text" name="email" /><br/>
	    </div>
	    <div class="elem">
	        性别： 
	        <input type="radio" name="sex" value="male" />男
	        <input type="radio" name="sex" value="female" />女
	        <input type="radio" name="sex" value="secret" checked="checked"/>保密
	    </div>
        <div class="elem">
	        简介：<br/>
	        <textarea name="intro" rows="5" cols="50" placeholder="介绍下自己吧!!!(256个字符之内)"></textarea><br/><br/>
	    </div>
	    <div class="elem">
		    <input type="reset" value="重置" /> <input type="submit" value="注册" />
		</div>
    </form>
    <div>已有帐号？<a href="${pageContext.request.contextPath}/login" target="_self">点击登录</a></div>

<%@ include file="/WEB-INF/client/footer.jsp" %>

</html>