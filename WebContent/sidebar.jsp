<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.Teacher" %>
<%
    Teacher loggedInTeacher = (Teacher) session.getAttribute("teacher");
    if (loggedInTeacher != null) {
%>
<div class="sidebar">
    <ul class="nav">
        <li><a href="${pageContext.request.contextPath}/scoremanager/main/menu.jsp">メニュー</a></li>
        <li><a href="main/StudentList.action">学生管理</a></li>
        <li>成績管理
            <ul>
                <li><a href="TestRegist.action">成績登録</a></li>
                <li><a href="TestList.action">成績参照</a></li>
            </ul>
        </li>
        <li><a href="main/SubjectList.action">科目管理</a></li>
    </ul>
</div>
<% } %>
