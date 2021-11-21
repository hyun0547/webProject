<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="게시물 작성"/>

<%@ include file="../common/head.jspf" %>
<section class="mx-auto max-w-2xl">
	<div>
		<form class="flex flex-col mt-40" action="/usr/article/doWrite">
			<div class="flex justify-between items-end mb-3">
				<span>제목</span>
				<select class="select select-bordered select-sm select-accent w-full max-w-xs" name="typeId">
					<c:forEach var="type" items="${typeRd.data1}">
  						<option value="${type.id}">${type.typeName}</option> 
					</c:forEach>
				</select>
			</div>
			<input type="text" name="title" class="input input-accent input-bordered mb-5" value=""/>
			<span class="mb-2">내용</span>
			<textarea class="textarea h-24 textarea-bordered textarea-accent mb-5 h-60" name="body"></textarea>
			<button class="btn btn-ghost w-28">작성</button>
		</form>
	</div>
</section>
<%@ include file="../common/foot.jspf" %>