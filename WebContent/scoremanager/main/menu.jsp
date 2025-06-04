<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../../base.jsp" />
<h2>メニュー</h2>
<a href="main/StudentList.action">学生管理</a>
<div>
	<p>成績管理</p>
	<a href="TestRegist.action">成績登録</a>
	<a href="test_list_student.jsp">成績参照</a>
	<a href="main/SubjectList.action">科目管理</a>
	<a href="Logout.action">ログアウト</a>
</div>