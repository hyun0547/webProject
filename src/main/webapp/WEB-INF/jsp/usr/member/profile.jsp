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
    			<img src="${member.profileDir}">
  			</div>
	  			<span class="text-xl">${member.name}</span>
		</div>
		<div class="w-2/4 mx-auto flex justify-between">
			<div></div>
		 	<a class="hover:text-gray-400 duration-200" href="/usr/member/showModifyProfile">수정하기</a>
		</div>
		<div class="w-2/4 mx-auto bg-gray-200 text-gray-600 flex flex-col text-center py-5">
			<span class="mb-2">내 회원 등급 : ${member.authLevel}</span>
  			<span class="mb-2">이메일 : ${member.email}</span>
  			<span class="mb-2">닉네임 : ${member.nickname}</span>
  			<span class="mb-2">전화번호 : ${member.cellphoneNo}</span>
		</div> 
	</section>
<%@ include file="../common/foot.jspf" %>