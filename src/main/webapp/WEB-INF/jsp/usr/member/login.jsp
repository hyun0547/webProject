<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jspf" %>

<section class="container mx-auto mt-56">
	<div class="mx-auto bg-green-50 flex py-20 w-2/3 rounded-xl">
		<form class="flex flex-col mx-auto w-1/2" action="/usr/member/doLogin">
			<input class="h-10 mb-5 text-center input input-bordered" type="text" placeholder="ID" name="loginId"/>
			<input class="h-10 mb-5 text-center input input-bordered" type="password" placeholder="PW" name="loginPw"/>
			<button class="bg-black text-white h-10 hover:bg-gray-400 duration-300">로그인</button>
		</form>
	</div>
</section>
<%@ include file="../common/foot.jspf" %>