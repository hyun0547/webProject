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

<c:set var="pageTitle" value="${typeRd.data1.typeName}"/>

<%@ include file="../common/head.jspf" %>
	<section class="mt-5">
		<div class="container mx-auto px-3 table-box1">
			<div class="flex justify-between mr-2 items-center">
				<div>
					<c:if test="${searchKeyword != null}">
						<span>
							'${searchKeyword}' (으)로 검색한 결과 입니다.
						</span>
					</c:if>
				</div>
				<form class="flex items-center" action="/usr/article/list">
					<input type="hidden" name="typeId" value="${typeRd.data1.id}" />
					<input class="input input-bordered mr-2" type="text" name="searchKeyword"/>
					<button><i class="fas fa-search fa-lg opacity-70"></i></button>
				</form>
			</div>
			<table class="w-full">
				<colgroup>
					<col width="3%"/>
					<col width="67%"/>
					<col width="15%"/>
					<col width="15%"/>
				</colgroup>
				<tr>
					<th>No</th>
					<th class="text-center">제목</th>
					<th class="text-right">작성자</th>
					<th class="text-right">작성일</th>
				</tr>
				<c:forEach var="article" items="${articleRd.data1}">
					<tr>
						<td class="text-center">${article.id}</td>
						<td class="text-center btn-text-link"><a href="/usr/article/detail?id=${article.id}&searchKeyword=${searchKeyword}">${article.title}</a></td>
						<td class="text-right">${article.memberLoginId}</td>
						<td class="text-right">
							<fmt:parseDate var="parsedDate" value="${article.regDate}" pattern="yyyy-mm-dd HH:mm:ss"/>
							<fmt:formatDate var="newFormattedDateString" value="${parsedDate}" pattern="yyyy/MM/dd"/> 
							${newFormattedDateString}
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="text-center mt-2">
			<a onclick="if(${curPage <= 1}){return false;}" href="/usr/article/list?typeId=${typeRd.data1.id}&curPage=${curPage-1}">&#60</a>
			<span class="mx-3">${curPage}/${allPages}</span>
			<a onclick="if(${curPage >= allPages}){return false;}" href="/usr/article/list?typeId=${typeRd.data1.id}&curPage=${curPage+1}">&#62</a>
		</div>
	</section>
	
<%@ include file="../common/foot.jspf" %>