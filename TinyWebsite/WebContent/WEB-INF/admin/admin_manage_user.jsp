<%@page import="java.net.URLEncoder"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
    <title>管理用户</title>
   <%@ include file="/WEB-INF/style.jsp" %>
</head>

<%@include file="/WEB-INF/admin/admin_header.jsp" %>

<div>
    <c:choose>
        <c:when test="${not empty requestScope.page and requestScope.page.hasData}">
             <table>
                <%-- 表头 --%>
                <tr>
                    <th>用户名</th> <th>昵称</th> <th>邮箱</th> <th>性别</th>
                    <th>重置密码</th> <th>冻结/恢复</th> <th>删除</th>
                </tr>
                
                <%-- 表体 --%>
                <c:forEach var="row" items="${requestScope.page.data}">
                <tr>
                    <%-- 用户名、昵称、电子邮件、性别 --%>
	                <td>${row.id}</td> <td>${row.name}</td> <td>${row.email}</td> <td>${row.sex}</td> 
	                
	                <%-- 重置密码、冻结/恢复、删除用户 --%>
	                <td>
	                    <a href="<c:url value="/admin/UpdateUserController?type=resetcode"> <c:param name="id" value="${row.id}"/> </c:url>">重置密码</a>
	                </td>
	                <c:choose>
	                    <c:when test="${row.freeze}">
	                        <td><a href="<c:url value="/admin/UpdateUserController?type=recover"> <c:param name="id" value="${row.id}"/> </c:url>"> <input type="button" value="恢复" /></a></td>
	                    </c:when>
	                    <c:otherwise>
	                        <td><a href="<c:url value="/admin/UpdateUserController?type=freeze"> <c:param name="id" value="${row.id}"/> </c:url>"> <input type="button" value="冻结" /></a></td>
	                    </c:otherwise>
	                </c:choose>
	                <td><a href="<c:url value="/admin/UpdateUserController?type=delete"> <c:param name="id" value="${row.id}"/> </c:url>">删除用户</a></td> 
               </tr>
               </c:forEach>  
            </table>
            <div>共找到${requestScope.page.totalRecord}条记录</div>
        </c:when>
        <c:otherwise>
            <div>没有找到任何数据</div>
        </c:otherwise>
    </c:choose>
</div>
       
<br/>
       
<%-- 分页器 --%>
<%-- 若只能分0或1页，则不输出分页器 --%>
<c:if test="${not empty requestScope.page and requestScope.page.totalPage > 1}">
    <div>
        <c:if test="${requestScope.page.pageNum != 1}">
            <a href="<c:url value="${requestScope.uri}"> <c:param name="pageNum" value="${requestScope.page.pageNum - 1}" /> </c:url>">&lt;&nbsp;</a>
        </c:if>
    
        <c:forEach var="i" begin="1" end="${requestScope.page.totalPage}">
            <c:if test="${i == requestScope.page.pageNum}">${i}&nbsp;</c:if>
            <c:if test="${i != requestScope.page.pageNum}">
                <a href="<c:url value="${requestScope.uri}"> <c:param name="pageNum" value="${i}" /> </c:url>"> ${i}</a>&nbsp;
            </c:if>
        </c:forEach>
    
        <c:if test="${requestScope.page.pageNum != requestScope.page.totalPage}">
            <a href="<c:url value="${requestScope.uri}"> <c:param name="pageNum" value="${requestScope.page.pageNum + 1}" /> </c:url>">&lt;&nbsp;</a>
        </c:if>
        
        <form action="<c:url value="${requestScope.uri}"></c:url>" method="POST">
	        <input type="text" name="pageNum" size="1" /> /共${requestScope.page.totalPage}页 
	        <input type="submit" value="跳转" />
        </form>
    </div>
</c:if>
      
<%@include file="/WEB-INF/admin/admin_footer.jsp" %>
</html>