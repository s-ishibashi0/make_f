<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	.header {
		background-color: #ADD8E6;
	}
</style>
</head>
<body>
<div class = header>
	<h1>得点管理システム</h1>
	<%
    	String loggedInUser = (String) session.getAttribute("userName");
	%>

	<span>
    	<%= loggedInUser != null ? loggedInUser +"様" : "" %>
	</span>
	<%if (loggedInUser != null) { %>
		<a href="git/scoremanager/main/logout.jsp">ログアウト</a>
	<% } %>
</div>
</body>
</html>