<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% String path = request.getContextPath(); %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>EasyJ test case</title>
</head>
<body>
<div align="center">
	<a href="pagedlist.jsp">分页查询页面</a><br>
	<a href="add.jsp">新增页面</a><br>
	<a href="<%=path %>/org/orgByList">集合查询页面</a><br>
</div>
</body>
</html>