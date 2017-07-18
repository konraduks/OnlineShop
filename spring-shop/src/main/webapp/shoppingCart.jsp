<%@ include file="/includes.jsp"%>
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
			<h1>Shopping cart list</h1>
			<table>
				<thead>
					<th>Id</th>
					<th>Name</th>
					<th>Price</th>
				</thead>
				<c:forEach var="product" items="${cart}">
					<form:form method="post">
						<tr>
							<td>${product.id}</td>
							<td>${product.product.name}</td>
							<td>${product.quantity}
							<input type="hidden" name="id" value="${product.product.id}" />
							<input type="submit" value="Delete product" /></td>
						</tr>
					</form:form>
				</c:forEach>
			</table>			
			<form:form method="post" action="submitOrder">
				<input type="submit" value="Make an order" />
			</form:form>			
		</div>
		<hr />
		<a href="<c:url value='/'/>">main page</a>
	</div>
</body>
</html>