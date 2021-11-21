<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Record Your Day"/>

<link rel="stylesheet" href="/resource/main/style/style.css"/>

<%@ include file="../common/head.jspf" %>

    <div class="today absolute flex flex-col">
      <div class="hello">hello</div>
      <div class="date">00 00 일요일</div>
      <div class="clock clock-font">00:00</div>
    </div>

    <div class="form-wrap flex flex-col absolute mt-3">
      <form class="search-form">
        <input class="search input input input-bordered" type="text" placeholder="Google" required />
      </form>
      <form class="todo-form my-0 ">
        <input
          class="todo-input input input-bordered"
          type="text"
          placeholder="Add your to-dos"
          required
        />
      </form>
    </div>

    <ul class="todo-list absolute"></ul>

    <script src="/resource/main/script/clock.js"></script>
    <script src="/resource/main/script/hello.js"></script>
    <script src="/resource/main/script/todo.js"></script>
    <script src="/resource/main/script/search.js"></script>
    <script src="/resource/main/script/weather.js"></script>
    <script src="/resource/main/script/background.js"></script>

<%@ include file="../common/foot.jspf" %>
