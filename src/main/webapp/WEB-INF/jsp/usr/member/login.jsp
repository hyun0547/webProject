<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 제이쿼리 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" ></script>

<!-- 테일윈드 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.19/tailwind.min.css"/>

<!-- 폰트 어썸 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>    

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../common/head.jspf" %>

<section class="container mx-auto mt-60">
	<div class="mx-auto bg-gray-200 flex py-20 w-2/3 rounded-xl">
		<form class="flex flex-col mx-auto w-1/2" action="/usr/member/doLogin">
			<input class="h-10 mb-5 text-center" type="text" placeholder="ID" name="loginId"/>
			<input class="h-10 mb-5 text-center" type="password" placeholder="PW" name="loginPw"/>
			<button class="bg-black text-white h-10 hover:bg-gray-400 duration-300">로그인</button>
		</form>
	</div>
</section>

<%@ include file="../common/foot.jspf" %>