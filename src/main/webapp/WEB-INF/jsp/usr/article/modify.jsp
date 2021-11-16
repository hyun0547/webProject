<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 제이쿼리 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" ></script>

<!-- 테일윈드 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.19/tailwind.min.css"/>

<!-- 폰트 어썸 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>    

<%@ include file="../common/head.jspf" %>
<section>
	<div>
		<form action="/usr/article/doModify">
			<input type="text" name="title" value="${rd.data1.title}"/>
			<textarea name="body">${rd.data1.body}</textarea>
			<input type="hidden" name="id" value="${rd.data1.id}" />
			<button>수정</button>
		</form>
	</div>
</section>
<%@ include file="../common/foot.jspf" %>