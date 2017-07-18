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
<div class="container">
		<div class="span-12 last">
<h1>All orders</h1>
	<table>
		<thead>
			<th>Content</th>
			<th>Status</th>					
		</thead>
	<c:forEach var="order" items="${orders}">
		<tr>
			<%-- <td>${order.products}</td> --%>
			<td>
			<c:forEach var="product" items="${order.products}">
			${product.product.name} ${product.quantity} | 
			</c:forEach>
			</td>
			<%-- <td>${order.status}</td> --%>
			<td>
				<c:if test="${order.status == 1}">
					przyjeto do realizacji
				</c:if>
				<c:if test="${order.status == 2}">
					w trakcie realizacji
				</c:if>
				<c:if test="${order.status == 3}">
					zrealizowano
				</c:if>
			</td>
		</tr>
	</c:forEach>
	</table>
	</div>
	</div>	
</body>
</html>