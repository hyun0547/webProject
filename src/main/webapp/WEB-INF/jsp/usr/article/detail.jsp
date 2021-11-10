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


<%@ include file="../common/head.jspf" %>
<c:set var="detail" value="${rd.data1}"/>
	<section class="container mx-auto mt-5 px-3">
		<div class="flex justify-between items-center">
			<h1 class="text-2xl">${detail.title}</h1>
			<div class="flex items-center">
				<a class="hover:text-gray-500 mx-2" href="#">${detail.nickname}</a> 
				<div class="bg-gray-500 color text-white rounded-xl px-2">${detail.authLevel}</div>
			</div>
		</div>
		<div class="h-1 bg-gray-200 mt-5"></div>
		<div class="mt-10 px-10">
			${detail.body}
		</div>
		<div class="h-1 bg-gray-200 mt-5"></div>
		<div class="mt-5 flex justify-between">
			<button onclick="history.back()">뒤로가기</button>
			<a onclick="if(confirm('삭제 하시겠습니까?') == false){return false;}" href="/usr/article/doDelete">삭제</a>
		</div>
	</section>
<%@ include file="../common/foot.jspf" %>