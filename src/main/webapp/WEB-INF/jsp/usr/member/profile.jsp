<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="pageTitle" value="Profile"/>

<%@ include file="../common/head.jspf" %>
<c:set var="member" value="${rq.loginedMember}"/>
	<section class="container mx-auto mt-5 px-3">
		<div class="avatar flex flex-col items-center left-1/2 transform -translate-x-1/2 mb-5">
  			<div class="mb-6 rounded-full w-40 h-40">
    			<img src="http://t1.daumcdn.net/friends/prod/editor/dc8b3d02-a15a-4afa-a88b-989cf2a50476.jpg">
  			</div>
	  			<span class="text-xl">${member.name}</span>
		</div>
		<div class="w-2/4 mx-auto bg-gray-200 text-gray-600 flex flex-col text-center">
			<span>내 회원 등급 : ${member.authLevel}</span>
  			<span>이메일 : ${member.email}</span>
  			<span>닉네임 : ${member.nickname}</span>
  			<span>전화번호 : ${member.cellphoneNo}</span>
		</div> 
	</section>
<%@ include file="../common/foot.jspf" %>