<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="pageTitle" value="게시물"/>

<%@ include file="../common/head.jspf" %>
<c:set var="article" value="${rd.data1}"/>
	<section class="container mx-auto mt-5 px-3">
		<div class="flex justify-between items-center">
			<h1 class="text-2xl">${article.title}</h1>
			<div class="flex items-center">
				<a class="hover:text-gray-500 mx-2" href="#">${article.member.nickname}</a> 
				<div class="badge badge-accent badge-outline">${article.member.authLevel}</div>
			</div>
		</div>
		<div class="h-1 bg-gray-200 mt-5"></div>
		<div class="mt-10 px-10">
			${article.body}
		</div>
		<div class="h-1 bg-gray-200 mt-5"></div>
		<div class="mt-5 flex justify-between">
			<button class="btn-text-link" onclick="history.back()">뒤로가기</button>
			<div class="flex">
				<c:if test="${article.extra__actorAuth}">
					<a class="mr-3 btn-text-link" href="/usr/article/showModify?id=${article.id}">수정</a>
				</c:if>
				<c:if test="${article.extra__actorAuth}">
					<a class="btn-text-link" onclick="if(confirm('삭제 하시겠습니까?') == false){return false;}" href="/usr/article/doDelete?id=${article.id}&afterDeleteUri">삭제</a>
				</c:if>
			</div>
		</div>
	</section>
<%@ include file="../common/foot.jspf" %>