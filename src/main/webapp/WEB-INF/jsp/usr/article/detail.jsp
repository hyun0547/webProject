<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="pageTitle" value="게시물"/>

<%@ include file="../common/head.jspf" %>
<c:set var="article" value="${articleRd.data1}"/>
<c:set var="replies" value="${replyRd.data1}"/>
	<section class="container mx-auto mt-5 px-3">
		<div class="flex justify-between items-center">
			<h1 class="text-2xl">${article.title}</h1>
			<div class="flex items-center">
				<c:if test="${article.extra__actorAuth}">
					<a class="mr-3 btn-text-link" href="/usr/article/showModify?id=${article.id}">수정</a>
				</c:if>
				<c:if test="${article.extra__actorAuth}">
					<a class="btn-text-link" onclick="if(confirm('삭제 하시겠습니까?') == false){return false;}" href="/usr/article/doDelete?id=${article.id}&afterDeleteUri=${afterDeleteUri}">삭제</a>
				</c:if>
				<a class="hover:text-gray-500 mx-2" href="#">${article.member.nickname}</a> 
				<div class="badge badge-accent badge-outline">${article.member.authLevel}</div>
			</div>
		</div>
		<div class="h-1 bg-green-600 mt-5"></div>
		<div class="mt-10 px-10">
			${article.body}
			<c:if test="${attachFileUrl != null }">
				<div class="flex w-1/2">
					<img class="w-full" src="${attachFileUrl}" alt="" />
				</div>
			</c:if>
		</div>
		<div class="h-1 bg-green-600 mt-5"></div>
	</section>
	<section class="container mx-auto mt-5 px-3">
		<div class="reply">
			<span>댓글 ${replies.size()}</span>
			<c:forEach var="reply" items="${replies}">
				<div class="reply-box">
					<div class="flex items-center mt-2">
						<a class="hover:text-gray-500 mx-1 flex items-center" href="#">
							<img class="w-8 h-8 rounded-full mr-1" alt="logo" src="${reply.writer.profileDir}">
							${reply.writer.nickname}
						</a>
						<div class="badge badge-accent badge-outline">${reply.writer.authLevel}</div>
					</div>
					<p class="p-3">${reply.body}</p>
				</div>
			</c:forEach>
		</div>
		<c:if test="${rq.isLogined()}">
			<div class="my-3">
				<form action="/usr/article/doWriteReply">
					<input  class="input input-bordered w-1/2" type="text" name="body" placeholder="댓글을 작성해 보세요"/>
					<input type="hidden" name="relArticleId" value="${article.id}" />
					<button class="btn btn-ghost">작성</button>
				</form>
			</div>
		</c:if>
		<div class="mt-5 flex justify-between">
			<button class="btn-text-link" onclick="history.back()">뒤로가기</button>
		</div>
	</section>
<%@ include file="../common/foot.jspf" %>