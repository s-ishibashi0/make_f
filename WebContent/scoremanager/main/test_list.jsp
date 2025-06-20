<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:import url="../../base.jsp" />

<section class="me-4">
    <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>

    <!-- 検索フォーム -->
    <form method="get" action="TestList.action">
        <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
            <div class="col-3">
                <label class="form-label" for="student-f1-select">入学年度</label>
                <select class="form-select" id="student-f1-select" name="f1">
                    <option value="0">--------</option>
                    <c:forEach var="year" items="${ent_year_set}">
                        <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="col-3">
                <label class="form-label" for="student-f2-select">クラス</label>
                <select class="form-select" id="student-f2-select" name="f2">
                    <option value="0">--------</option>
                    <c:forEach var="num" items="${class_num_set}">
                        <option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="col-3">
                <label class="form-label" for="student-f3-select">科目</label>
                <select class="form-select" id="student-f3-select" name="f3">
                    <option value="0">--------</option>
                    <c:forEach var="subject" items="${subjects}">
                        <option value="${subject.cd}" <c:if test="${subject.cd == f3}">selected</c:if>>${subject.name}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="col-1 text-center">
                <button type="submit" class="btn btn-secondary mt-4">検索</button>
            </div>

            <div class="row">
                <div class="col-3">
                    <label class="form-label" for="student-no">学生番号</label>
                    <input type="text" class="form-control" id="student-no" name="f4" value="${f4}" />
                </div>
                <div class="col-2 align-self-end">
                    <button type="submit" class="btn btn-primary">検索</button>
                </div>
            </div>
        </div>
    </form>

    <!-- 成績参照（科目） -->
    <c:if test="${not empty tests && mode == 'subject'}">
        <form method="post" action="TestSubjectExecute.action">
            <input type="hidden" name="f1" value="${f1}" />
            <input type="hidden" name="f2" value="${f2}" />
            <input type="hidden" name="f3" value="${f3}" />
            <input type="hidden" name="f4" value="${f4}" />
        </form>
        <jsp:include page="test_list_subject.jsp" />
    </c:if>

    <!-- 成績参照（学生） -->
    <c:if test="${not empty tests && mode == 'student'}">
        <form method="post" action="TestStudentExecute.action">
            <input type="hidden" name="f1" value="${f1}" />
            <input type="hidden" name="f2" value="${f2}" />
            <input type="hidden" name="f3" value="${f3}" />
            <input type="hidden" name="f4" value="${f4}" />
        </form>
        <jsp:include page="test_list_student.jsp" />
    </c:if>
</section>
