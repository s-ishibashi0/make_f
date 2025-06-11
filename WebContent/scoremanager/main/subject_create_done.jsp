<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../../base.jsp" />

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>得点管理システム</title>
</head>
<body>

  <!-- 画面タイトル -->
  <h2>科目登録完了</h2>

  <!-- 完了メッセージ -->
  <p>科目の登録が完了しました。</p>

  <br>

  <!-- 戻るリンク（再登録） -->
  <a href="${pageContext.request.contextPath}/scoremanager/main/SubjectCreate.action">戻る</a><br>

  <!-- 科目一覧リンク -->
  <a href="${pageContext.request.contextPath}/scoremanager/main/SubjectList.action">科目一覧</a>

</body>
</html>
