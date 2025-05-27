<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	function togglePasswordVisibility() {
		const passwordInput = document.getElementById("password");
		const checkbox = document.getElementById("showPasswordCheckbox");
		if (checkbox.checked) {
			passwordInput.type = "text";
		} else {
			passwordInput.type = "password";
		}
	}
</script>

<c:import url="../base.jsp" />

<h2>ログイン</h2>

<form action="${pageContext.request.contextPath}/scoremanager/LoginExecute.action" method="post">

	<label for="id">ログインID</label><br />
	<input type="text"
		id="id"
		name="login"
		value="${login}"
		placeholder="半角でご入力ください"
		style="ime-mode: disabled;"
		required maxlength="10" /><br /><br />

	<label for="password">パスワード</label><br />
	<input type="password"
		id="password"
		name="password"
		placeholder="30文字以内の半角英数字でご入力ください"
		maxlength="30"
		style="ime-mode: disabled;"
		required /><br />

	<input
		type="checkbox"
		id="showPasswordCheckbox"
		name="chk_d_ps"
		onclick="togglePasswordVisibility()" />
	<label for="showPasswordCheckbox">パスワードを表示</label><br /><br />

	<input
		type="submit"
		name="login"
		value="ログイン" />
</form>

<c:if test="${not empty error}">
	<p style="color:red;">${error}</p>
</c:if>