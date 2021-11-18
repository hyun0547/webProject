<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/head.jspf" %>
<section class="mx-auto max-w-2xl">
	<div>
		<form class="flex flex-col mt-40" action="/usr/article/doWrite">
			<input type="text" name="title" class="input input-accent input-bordered mb-5" value=""/>
			<textarea class="textarea h-24 textarea-bordered textarea-accent mb-5 h-60" name="body"></textarea>
			<button class="btn btn-ghost w-32">작성</button>
		</form>
	</div>
</section>
<%@ include file="../common/foot.jspf" %>