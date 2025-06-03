<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../../base.jsp" />

<html>
<head>
<title>科目管理</title>
</head>
<body>
    <h2>科目管理</h2>
    <a href="subjectForm.jsp">新規登録</a>
    <br><br>

    <table border="1">
        <tr>
            <th>科目コード</th>
            <th>科目名</th>

        </tr>
        <c:forEach var="subject" items="${subjectList}">

            <tr>
                <td>${subject.cd}</td>
                <td>${subject.name}</td>
                <td>
                    <a href="subjectForm.jsp?code=${subject.cd}">変更</a>
                    <a href="deleteSubject?code=${subject.cd}">削除</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
