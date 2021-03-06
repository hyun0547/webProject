<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko" class="light" data-theme="light">
<head>
<meta charset="UTF-8">
<title>로그인</title>
<!-- 제이쿼리 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" ></script>

<!-- 테일윈드 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.19/tailwind.min.css"/>

<!-- 폰트 어썸 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>

<!-- 데이지 UI -->
<link href="https://cdn.jsdelivr.net/npm/daisyui@1.16.2/dist/full.css" rel="stylesheet" type="text/css" />    

<link rel="stylesheet" href="/resource/common.css"/>
<link rel="shortcut icon" href="/favicon.ico">
</head>
<body>
	<section class="container mx-auto mt-32 flex items-center flex-col">
		<div class="w-60">
	    	<a href="/" class="flex items-center px-3">
	    		<img alt="logo" src="/RYD-logo.png">
	    	</a>
	    </div>
		<div class="mx-auto bg-green-50 flex py-20 w-2/3 rounded-xl">
			<form class="flex flex-col mx-auto w-1/2" action="/usr/member/doLogin">
				<input class="h-10 mb-5 text-center input input-bordered" type="text" placeholder="ID" name="loginId"/>
				<input class="h-10 mb-5 text-center input input-bordered" type="password" placeholder="PW" name="loginPw"/>
				<input type="hidden" value="${afterLoginUri}" name="afterLoginUri"/>
				<button class="bg-black text-white py-3 box-border hover:bg-gray-400 duration-300 mb-2 mt-3">로그인</button>
				<a href="/usr/member/showJoin" class="bg-black text-white py-3 box-border hover:bg-gray-400 duration-300 text-center">회원가입</a>
			</form>
		</div>
	</section>
<%@ include file="../common/foot.jspf" %>