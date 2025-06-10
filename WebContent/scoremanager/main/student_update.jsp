<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../../base.jsp" />
<html>
<head>
<meta charset="UTF-8" />
<title>得点管理システム</title>
</head>
<body>

<h2>学生情報登録</h2>

<form method="post" action="${pageContext.request.contextPath}/scoremanager/main/StudentUpdateExecute.action">

  <label for="ent_year">入学年度</label><br />
  <input type="text" id="ent_year" name="ent_year" value="${ent_year}" readonly /><br /><br />

  <label for="no">学生番号</label><br />
  <input type="text" id="no" name="no" value="${no}" readonly /><br /><br />

  <label for="name">氏名</label><br />
  <input type="text" id="name" name="name" value="${name}" maxlength="30" required /><br /><br />

  <label for="class_num">クラス</label><br />
<label for="class_num">クラス</label><br />
	<select id="class_num" name="class_num">
	  <c:forEach var="cls" items="${class_list}">
	    <option value="${cls}" <c:if test="${cls == class_num}">selected</c:if>>クラス${cls}</option>
	  </c:forEach>
	</select><br /><br />


  <label for="is_attend">在学中</label>
  <input type="checkbox" id="is_attend" name="is_attend" /><br /><br />

  <input type="submit" name="login" value="変更" />

  <a href="/student/list" style="margin-left:10px;">戻る</a>

</form>

</body>
</html>
