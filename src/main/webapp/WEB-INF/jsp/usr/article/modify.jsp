<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="게시물 수정"/>

<%@ include file="../common/head.jspf" %>
<section class="mx-auto max-w-2xl">
	<div>
		<form class="flex flex-col mt-40" action="/usr/article/doModify">
			<input type="text" name="title" class="input input-accent input-bordered mb-5" value="${rd.data1.title}"/>
			<textarea class="textarea h-24 textarea-bordered textarea-accent mb-5 h-60" name="body">${rd.data1.body}</textarea>
			<input type="hidden" name="id" value="${rd.data1.id}" />
			<button class="btn btn-ghost w-28">수정 완료</button>
		</form>
	</div>
</section>
<%@ include file="../common/foot.jspf" %>