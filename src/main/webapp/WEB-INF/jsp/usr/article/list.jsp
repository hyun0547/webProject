<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../common/head.jspf" %>
	<table border=1>
		<tr>
			<th>No</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
		<c:forEach var="article" items="${rd.data1}">
			<tr>
				<td>${article.id}</td>
				<td>${article.title}</td>
				<td>${article.memberLoginId}</td>
				<td>
					<fmt:parseDate var="parsedDate" value="${article.regDate}" pattern="yyyy-mm-dd HH:mm:ss"/>
					<fmt:formatDate var="newFormattedDateString" value="${parsedDate}" pattern="yyyy/MM/dd"/> 
					${newFormattedDateString}
				</td>
			</tr>
		</c:forEach>
	</table>
<%@ include file="../common/foot.jspf" %>