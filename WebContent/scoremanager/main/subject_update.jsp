<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>科目変更</title>
</head>
<body>
    <h2>科目変更</h2>

    <form action="${pageContext.request.contextPath}/scoremanager/main/SubjectUpdateExecute.action" method="post">
        <label for="cd">科目コード</label>
        <input type="text" id="cd" name="cd" value="${subject.cd}" readonly /><br><br>

        <label for="name">科目名</label>
        <input type="text" id="name" name="name" value="${subject.name}" maxlength="20" required /><br><br>

        <input type="submit" value="変更" />
    </form>

    <br>
    <a href="${pageContext.request.contextPath}/scoremanager/main/SubjectList.action">戻る</a>
</body>
</html>
