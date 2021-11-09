<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border=1>
		<c:forEach var="article" items="${rd.data1}">
			<tr>
				<td>${article.id}</td>
				<td>${article.title}</td>
				<td>
					<fmt:parseDate var="parsedDate" value="${article.regDate}" pattern="yyyy-mm-dd HH:mm:ss"/>
					<fmt:formatDate var="newFormattedDateString" value="${parsedDate}" pattern="yyyy/MM/dd"/> 
					${newFormattedDateString}
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>