<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 제이쿼리 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" ></script>

<!-- 테일윈드 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.19/tailwind.min.css"/>

<!-- 폰트 어썸 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="pageTitle" value="게시판"/>

<%@ include file="../common/head.jspf" %>
	<section class="mt-5">
		<div class="container mx-auto px-3 table-box1">
			<table class="w-full">
				<tr>
					<th>No</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
				<c:forEach var="article" items="${rd.data1}">
					<tr>
						<td>${article.id}</td>
						<td>${article.title}</td>
						<td>${article.memberLoginId}</td>
						<td>
							<fmt:parseDate var="parsedDate" value="${article.regDate}" pattern="yyyy-mm-dd HH:mm:ss"/>
							<fmt:formatDate var="newFormattedDateString" value="${parsedDate}" pattern="yyyy/MM/dd"/> 
							${newFormattedDateString}
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</section>
	
<%@ include file="../common/foot.jspf" %>