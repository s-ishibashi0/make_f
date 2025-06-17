<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:import url="../../base.jsp" />

<section class="me-4">
    <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

    <!-- 検索フォーム -->
    <form method="get" action="TestRegist.action">
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
                    <c:forEach var="subject" items="${subjectList}">
                        <option value="${subject.cd}" <c:if test="${subject.cd == f3}">selected</c:if>>${subject.name}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="col-2">
                <label class="form-label" for="student-f4-select">テスト回数</label>
                <select class="form-select" id="student-f4-select" name="f4">
                    <option value="0">--------</option>
                    <c:forEach var="num" items="${test_no_list}">
                        <option value="${num}" <c:if test="${f4 == num}">selected</c:if>>${num}</option>
                    </c:forEach>
                </select>
                <c:if test="${errors.f4 != null}">
                    <span class="text-danger">${errors.f4}</span>
                </c:if>
            </div>

            <div class="col-1 text-center">
                <button type="submit" class="btn btn-secondary mt-4">検索</button>
            </div>

            <div class="mt-2 text-warning">${errors.f1}</div>
        </div>
    </form>

    <!-- 成績入力フォーム（検索結果がある場合） -->
    <c:if test="${not empty students}">
        <form method="post" action="TestRegist.action">
            <!-- 検索条件を保持 -->
            <input type="hidden" name="f1" value="${f1}" />
            <input type="hidden" name="f2" value="${f2}" />
            <input type="hidden" name="f3" value="${f3}" />
            <input type="hidden" name="f4" value="${f4}" />

            <div class="px-3">検索結果：${fn:length(students)}件</div>

            <table class="table table-hover mx-3">
                <thead>
                    <tr>
                        <th>入学年度</th>
                        <th>学生番号</th>
                        <th>氏名</th>
                        <th>クラス</th>
                        <th>得点</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="student" items="${students}">
                        <tr>
                            <td>${student.entYear}</td>
                            <td>${student.no}</td>
                            <td>${student.name}</td>
                            <td>${student.classNum}</td>
                            <td>
                                <input type="number" class="form-control"
                                    name="scores[${student.no}]" min="0" max="100"
                                    placeholder="0～100" />
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <div class="text-end mx-3">
                <button type="submit" class="btn btn-primary">成績登録</button>
            </div>
        </form>
    </c:if>

    <!-- 検索結果がない場合 -->
    <c:if test="${empty students}">
        <div class="px-3">該当する学生は存在しません</div>
    </c:if>
</section>
