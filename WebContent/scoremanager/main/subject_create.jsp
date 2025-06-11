<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>得点管理システム</title>
</head>
<body>
  <h2>科目登録</h2>

  <!-- エラーメッセージ表示（空でなければ表示） -->
  <c:if test="${not empty errorMsg}">
    <p style="color:red">${errorMsg}</p>
  </c:if>

  <form action="${pageContext.request.contextPath}/scoremanager/main/SubjectCreateExecute.action" method="post">

    <label for="cd">科目コード</label><br>
    <input
      type="text"
      id="cd"
      name="cd"
      maxlength="3"
      required
      placeholder="科目コードを入力してください"
      value="${cd != null ? cd : ''}"
      autocomplete="off"
      pattern=".{3}"
      title="科目コードは3文字で入力してください"
    >
    <br><br>

    <label for="name">科目名</label><br>
    <input
      type="text"
      id="name"
      name="name"
      maxlength="20"
      required
      placeholder="科目名を入力してください"
      value="${name != null ? name : ''}"
      autocomplete="off"
    >
    <br><br>

    <input type="submit" value="登録">
  </form>

  <br>
  <a href="${pageContext.request.contextPath}/scoremanager/main/SubjectList.action">戻る</a>
</body>
</html>
