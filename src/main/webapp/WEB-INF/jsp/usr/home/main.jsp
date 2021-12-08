<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="/resource/main/style/style.css"/>

<c:set var="pageTitle" value="당신의 하루를 남겨보세요"/>

<%@ include file="../common/head.jspf" %>
<section>
    <div class="today absolute flex flex-col">
      <div class="date">00 00 일요일</div>
      <div class="hello text-2xl my-3"></div>
      <div class="clock clock-font">00:00</div>
    </div>

    <div class="form-wrap flex flex-col absolute">
      <form class="search-form mb-2">
        <input class="search input input input-bordered" type="text" placeholder="Google" required />
      </form>
      <form class="todo-form">
        <input
          class="todo-input input input-bordered"
          type="text"
          placeholder="Add your to-dos"
          required
        />
      </form>
    </div>
</section>
    <ul class="todo-list absolute"></ul>

<script src="/resource/main/script/hello.js"></script>
<script src="/resource/main/script/clock.js"></script>
<script src="/resource/main/script/todo.js"></script>
<script src="/resource/main/script/search.js"></script>
<%@ include file="../common/foot.jspf" %>

