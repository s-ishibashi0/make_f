<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>科目削除確認</title>
</head>
<body>
<h2>科目削除確認</h2>
<p>「${subject.name}（${subject.cd}）」を削除してよろしいですか？</p>

<form action="${pageContext.request.contextPath}/scoremanager/main/SubjectDeleteExecute.action" method="post">
    <input type="hidden" name="code" value="${subject.cd}" />
    <input type="submit" value="削除" />
</form>

<a href="${pageContext.request.contextPath}/scoremanager/main/SubjectList.action">戻る</a>
</body>
</html>
