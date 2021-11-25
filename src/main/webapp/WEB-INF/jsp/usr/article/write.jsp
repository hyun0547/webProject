<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="게시물 작성"/>

<%@ include file="../common/head.jspf" %>
<script>
	function articleAdd__chk(form){
		var maxSizeMb = 50;
		var maxSize = maxSizeMb * 1024 * 1024; 
		var file = form.file__article__0__common__attachment;
		
		if(file.value){
			if(file.files[0].size > maxSize){
				alert(maxSizeMb + "MB이하의 파일만 업로드 할 수 있습니다.");
				return false;
			}
		}
	}
</script>
<section class="mx-auto max-w-2xl">
	<div>
		<form class="flex flex-col mt-40" onsubmit="if(articleAdd__chk(this) == false){return false;}" action="/usr/article/doWrite" method="post" enctype="multipart/form-data">
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
			<div class="flex justify-between items-center">
				<label
				  class="
				    w-40
				    flex flex-col
				    items-center
				    px-4
				    py-4
				    bg-white
				    rounded-md
				    shadow-md
				    tracking-wide
				    uppercase
				    border border-blue
				    cursor-pointer
				    hover:bg-purple-600 hover:text-white
				    text-purple-600
				    ease-linear
				    transition-all
				    duration-150
				  "
				>
				  <i class="fas fa-cloud-upload-alt fa-2x"></i>
				  <span class="mt-2 text-base leading-normal">첨부 이미지</span>
				  <input class="hidden" type="file" name="file__article__0__common__attachment"/>
				</label>
				<button class="btn btn-ghost w-28">작성</button>
			</div>
		</form>
	</div>
</section>
<%@ include file="../common/foot.jspf" %>