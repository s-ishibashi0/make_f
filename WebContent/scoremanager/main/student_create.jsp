<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- ページ固有コンテンツ -->
<div id="content">

  <!-- 画面タイトル -->
  <h2>学生情報登録画面</h2>

  <!-- 登録フォーム -->
<form action="StudentCreateExecute.action" method="post">
    <label for="ent_year">入学年度</label><br/>
    <select name="ent_year" id="ent_year" required>
      <c:forEach var="year" items="${entYearList}">
        <option value="${year}">${year}年</option>
      </c:forEach>
    </select>
    <br/><br/>

    <label for="no">学生番号</label><br/>
    <input type="text" name="no" id="no" maxlength="10" required
           value="${no}"
           placeholder="学生番号を入力してください" />
    <br/><br/>

    <label for="name">氏名</label><br/>
    <input type="text" name="name" id="name" maxlength="30" required
           value="${name}"
           placeholder="氏名を入力してください" />
    <br/><br/>

    <label for="class_num">クラス</label><br/>
    <select name="class_num" id="class_num" required>
      <c:forEach var="classNum" items="${classNumList}">
        <option value="${classNum}">${classNum}</option>
      </c:forEach>
    </select>
    <br/><br/>
    <button type="submit" name="end">登録して終了</button>
  </form>
  <br/>
  <a href="/Team_F/scoremanager/main/student_list.jsp">学生管理一覧へ戻る</a>

</div>
