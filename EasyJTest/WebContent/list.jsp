<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% String path = request.getContextPath(); %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>EasyJ test case</title>
</head>
<body>
<style>
table{border-left:1px solid #ccc;border-bottom:1px solid #ccc;}
td{border-top:1px solid #ccc;border-right:1px solid #ccc;}
</style>
<div align="center">
<a href="<%=path %>/index.jsp">返回首页</a><br>
	<table border="0" cellpadding="0" cellspacing="0" style="width: 400px;">
		<tr><td>ID</td><td>NAME</td><td>ORDERNUM</td><td>PARAENTID</td><td>DELETE</td></tr>
		<c:forEach var="org" items="${requestScope.list }"> 
			<tr><td>${org.id }</td><td>${org.name }</td><td>${org.orderNum }</td><td>${org.pid }</td><td><a href="<%=path%>/org/deleteOrg?id=${org.id }">删除</a></td></tr>
		</c:forEach>
	</table>
	
</div>
</body>
</html>