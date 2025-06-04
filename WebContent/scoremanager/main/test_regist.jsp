<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../../base.jsp" />

<section class="me-4">
	<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
	<form method="get">
		<div class="row border mx-3 mb-3 py-2 align-items-center rounded"
			id="filter">
			<div class="col-4">
				<label class="form-label" for="student-f1-select">入学年度</label> <select
					class="form-select" id="student-f1-select" name="f1">
					<option value="0">--------</option>
					<c:forEach var="year" items="${ent_year_set}">
						<option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
					</c:forEach>
				</select>
			</div>

			<div class="col-4">
				<label class="form-label" for="student-f2-select">クラス</label> <select
					class="form-select" id="student-f2-select" name="f2">
					<option value="0">--------</option>
					<c:forEach var="num" items="${class_num_set}">
						<option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</form>
</section>

