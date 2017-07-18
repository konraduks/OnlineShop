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
			<h1>Product list</h1>
			<table>
				<thead>
					<th>Id</th>
					<th>Name</th>
					<th>Price</th>
					<th>Category</th>
				</thead>
				<c:forEach var="product" items="${products}">
					
						<tr>
							<td>${product.id}</td>
							<td>${product.name}</td>
							<td>${product.price}</td>
							<td>${product.category.name}</td>
							<td>						
							<sec:authorize access="hasRole('USER')">
								<form:form method="post">
									<input type="hidden" name="id"	value="${product.id}" />
									<input type="number" step="1" name="quantity" value="" />
									<input type="submit" value="Add to cart" />
								</form:form>
							</sec:authorize>
							<sec:authorize access="hasRole('ROLE_ADMIN')">
									<form:form method="get" action="editProduct">
										<input type="hidden" name="id"	value="${product.id}" /> 
										<input type="submit" value="Edit product" class="btn btn-info" />
									</form:form>
									</td><td>
									<form:form method="post" action="deleteProduct">
										<input type="hidden" name="id"	value="${product.id}" /> 
										<input type="submit" value="Delete product"	class="btn btn-danger" />
									</form:form>
								</sec:authorize>
							</td>
						</tr>
					
				</c:forEach>
			</table>
		</div>
		<hr />
		<a href="<c:url value='/'/>">main page</a>
	</div>
</body>
</html>