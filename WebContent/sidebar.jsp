<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
	.sidebar ul {
		list-style:none;
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<% String loggedInUser = (String) session.getAttribute("userName"); %>
<div class = sidebar>
	<%if (loggedInUser != null) { %>
	<ul class = nav>
		<li><a href = git/scoremanager/main/menu.jsp>メニュー</a></li>
		<li><a href = git/scoremanager/main/student_list.jsp>学生管理</a></li>
		<li>成績管理
		<ul>
			<li><a href = git/scoremanager/main/test_regist.jsp>成績登録</a></li>
			<li><a href = git/scoremanager/main/test_list.jsp>成績参照</a></li>
		</ul>
		</li>
		<li><a href = git/scoremanager/main/subject_list.jsp>科目管理</a></li>
	</ul>
	<% } %>
</div>
</body>
</html>
