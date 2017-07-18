<%@ include file="/includes.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Hello ${name}
	<br>
	<sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('USER')">
 	This content will only be visible to users who have the "ROLE_ADMIN" or "ROLE_USER" authority.
	</sec:authorize>
</body>
</html>