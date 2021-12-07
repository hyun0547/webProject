<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="게시물 작성"/>

<%@ include file="../common/head.jspf" %>

<%@ include file="../../common/toastUI.jspf" %>

<section class="mx-auto container px-10 mb-10">
	<div>
		<form class="flex flex-col mt-20" onsubmit="if(articleAdd__chk(this) == false){return false;}" action="/usr/article/doWrite" method="post" enctype="multipart/form-data">
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
			<input type="hidden" name=body />
			<div class="toast-ui-editor mb-4">
			</div>
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