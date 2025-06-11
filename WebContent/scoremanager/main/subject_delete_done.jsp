<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../../base.jsp" />

<html>
<head>
<title>得点管理システム</title>
</head>
<body>
	<h2>科目情報削除</h2>

	<p>削除が完了しました。</p>

	<a
		href="${pageContext.request.contextPath}/scoremanager/main/SubjectList.action">科目一覧</a>
</body>
</html>
