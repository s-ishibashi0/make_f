<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:import url="../../base.jsp" />

<html>
<head>
<title>科目管理</title>
</head>
<body>
    <h2>科目管理</h2>
    <a href="${pageContext.request.contextPath}/scoremanager/main/SubjectCreate.action">新規登録</a>
    <br><br>

    <!-- 件数チェック表示 -->
    <c:if test="${empty subjectList}">
        <p>科目リストは空です</p>
    </c:if>
    <c:if test="${not empty subjectList}">
        <p>科目リスト件数: ${fn:length(subjectList)}</p>
    </c:if>

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
                    <a href="${pageContext.request.contextPath}/scoremanager/main/subjectForm.jsp?code=${subject.cd}">変更</a>
                    <a href="${pageContext.request.contextPath}/scoremanager/main/deleteSubject?code=${subject.cd}">削除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>