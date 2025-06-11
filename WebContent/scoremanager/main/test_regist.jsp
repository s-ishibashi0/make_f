<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:import url="../../base.jsp" />

<section class="me-4">
	<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
	<form method="get" action="TestRegist.action">
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

			<div class="col-4">
				<label class="form-label" for="student-f3-select">科目</label> <select
					class="form-select" id="student-f3-select" name="f3">
					<option value="0">--------</option>
					<c:forEach var="subject" items="${subjectList}">
						<option value="${subject.cd}"
							<c:if test="${subject.cd == f3}">selected</c:if>>${subject.name}</option>
					</c:forEach>
				</select>
			</div>


			<div class="col-4">
				<label class="form-label" for="student-f4-select">テスト回数</label> <select
					class="form-select" id="student-f4-select" name="f4">
					<option value="0">--------</option>
					<c:forEach var="num" items="${test_no_list}">
						<option value="${num}" <c:if test="${f4 == num}">selected</c:if>>${num}</option>
					</c:forEach>
				</select>
				<c:if test="${errors.f4 != null}">
					<span class="text-danger">${errors.f4}</span>
				</c:if>
			</div>

			<div class="col-2 text-center">
				<button type="submit" class="btn btn-secondary" id="filter-button">検索</button>
			</div>

			<div class="mt-2 text-warning">${errors.f1}</div>
		</div>
	</form>

	<!-- デバッグ表示（必要に応じて削除） -->
	<p>students size: ${fn:length(students)}</p>

	<c:choose>
		<c:when test="${not empty students}">
			<div>検索結果：${students.size()}件</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>入学年度</th>
						<th>クラス</th>
						<th>学生番号</th>
						<th>氏名</th>
						<th class="text-center">出席</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="student" items="${students}">
						<tr>
							<td>${student.entYear}</td>
							<td>${student.classNum}</td>
							<td>${student.no}</td>
							<td>${student.name}</td>
							<td class="text-center"><c:choose>
									<c:when test="${student.isAttend()}">○</c:when>
									<c:otherwise>✕</c:otherwise>
								</c:choose></td>
							<td><a href="StudentUpdate.action?no=${student.no}">変更</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<div>学生情報が存在しませんでした</div>
		</c:otherwise>
	</c:choose>
</section>
