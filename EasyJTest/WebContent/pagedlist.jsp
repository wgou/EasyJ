<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% String path = request.getContextPath(); %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>EasyJ test case</title>
</head>

<style>
table{border-left:1px solid #ccc;border-bottom:1px solid #ccc;}
td{border-top:1px solid #ccc;border-right:1px solid #ccc;}
</style>

<body>
<div align="center">

<a href="<%=path %>/index.jsp">返回首页</a><br>
<c:if test="${requestScope.pagedList != null }">

	当前页数:<input type="text" value="${requestScope.pagedList.nowPage }"><br>
	每页条数:<input type="text" value="${requestScope.pagedList.pageSize }"><br>
	总条数:<input type="text" value="${requestScope.pagedList.totalRowCount }"><br>
	总页数:<input type="text" value="${requestScope.pagedList.totalPage }"><br>
	<a href="<%=path%>/org/pagedList?pageSize=${requestScope.pagedList.pageSize }&pageNum=${requestScope.pagedList.nowPage+1 }">下一页</a>
	<table border="0" tyle="width:400px;" cellpadding="0" cellspacing="0">
		<tr><td>ID</td><td>NAME</td><td>ORDERNUM</td><td>PARAENTID</td></tr>
		<c:forEach var="org" items="${requestScope.pagedList.dataList }"> 
			<tr><td>${org.id }</td><td>${org.name }</td><td>${org.orderNum }</td><td>${org.pid }</td></tr>
		</c:forEach>
	</table>

</c:if>
<c:if test="${requestScope.pagedList == null }">
	<form action="<%=path%>/org/pagedList" method="post">
		请输入每页条数<input type="text" name="pageSize" value="10"/>
		请输入当前页数:<input type="text" name="pageNum" value="1"/>
		<input type="submit" value="查询"/>
	</form>
</c:if>
	
	
</div>
</body>
</html>