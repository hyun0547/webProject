<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko" class="light" data-theme="light">
<head>
<meta charset="UTF-8">
<title>회원가입</title>
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
	<main>
		<section class="max-w-2xl mx-auto mt-10 bg-green-50 p-5">
			<div class="mx-auto w-1/2">
				<form class="mx-auto w-full" action="/usr/member/doJoin">
  						<input type="text" placeholder="아이디" class="input input-bordered w-full mb-8" name="loginId" required>
  						<input type="password" placeholder="비밀번호" class="input input-bordered w-full mb-2" name="loginPw" required>
  						<input type="password" placeholder="비밀번호 확인" class="input input-bordered w-full mb-8" required>
  						<input type="text" placeholder="이름" class="input input-bordered w-full mb-4" name="name" required>
  						<input type="text" placeholder="닉네임" class="input input-bordered w-full mb-4" name="nickname" required>
  						<input type="email" placeholder="이메일" class="input input-bordered w-full mb-4" name="email" required>
  						<input type="tel" placeholder="전화번호 '-' 를 제외하고 입력" class="input input-bordered w-full mb-4" name="cellphoneNo" pattern="[0-9]{3}[0-9]{4}[0-9]{4}" required>
  						<div class="flex justify-between">
  							<a href="/" class="btn btn-ghost w-28">취소</a>
  							<button class="btn btn-ghost w-28">회원 가입</button>
  						</div>
				</form>
			</div>
		</section>
	</main>
</body>
</html>