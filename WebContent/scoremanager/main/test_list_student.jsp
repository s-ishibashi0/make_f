<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<section class="me-4">
	<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績参照(学生)</h2>
	<!-- 検索条件を保持 -->
	<input type="hidden" name="f1" value="${f1}" /> <input type="hidden"
		name="f2" value="${f2}" /> <input type="hidden" name="f3"
		value="${f3}" /> <input type="hidden" name="f4" value="${f4}" />

	<div class="px-3">検索結果：${fn:length(tests)}件</div>


	<table class="table">
		<thead>
			<tr>
				<th>科目名</th>
				<th>科目コード</th>
				<th>回数</th>
				<th>点数</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="test" items="${tests}">
				<tr>
					<td>${test.subjectName}</td>
					<td>${test.subjectCd}</td>
					<td>${test.num}</td>
					<td>${test.point}</td>
				</tr>
			</c:forEach>
		</tbody>

	</table>




</section>
