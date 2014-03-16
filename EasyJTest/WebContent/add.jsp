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
	<a href="index.jsp">返回首页</a><br>
	<form action="<%=path%>/org/insertOrg">
		ID:<input type="text" name="id"/>
		NAME:<input type="text" name="name"/>
		ORDERNUM:<input type="text" name="orderNum"/>
		PARENTID:<input type="text" name="pid"/>
		<input type="submit" value="新增"/>
	</form>
</div>
</body>
</html>