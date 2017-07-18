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
<h2>Orders status: 1 = przyjeto do realizacji, 2 - w trakcie realizacji, 3 - zrealizowano</h2>
<h1>Orders with status == 1</h1>
<table>
				<thead>
					<th>Id</th>
					<th>User</th>	
					<th>Content</th>					
				</thead>
	<c:forEach var="order" items="${orders1}">
					
						<tr>
							<td>${order.id}</td>
							<td>${order.user.username}</td>
							<td>
							<c:forEach var="product" items="${order.products}">
								${product.product.name} ${product.quantity} | 
							</c:forEach>
							</td>
							<td>
							<form:form method="post">
										<input type="hidden" name="id"	value="${order.id}" /> 	
										<input type="hidden" name="status"	value="2" />										
										<input type="submit" value="Update status to 2" class="btn btn-primary" />
									</form:form>
							</td>
							</tr>					
				</c:forEach>
				</table>
				
<h1>Orders with status == 2</h1>
<table>
				<thead>
					<th>Id</th>
					<th>User</th>	
					<th>Content</th>					
				</thead>
	<c:forEach var="order" items="${orders2}">
					
						<tr>
							<td>${order.id}</td>
							<td>${order.user.username}</td>
							<td>
							<c:forEach var="product" items="${order.products}">
								${product.product.name} ${product.quantity} | 
							</c:forEach>
							</td>
							<td>
							<form:form method="post">
										<input type="hidden" name="id"	value="${order.id}" />	
										<input type="hidden" name="status"	value="3" />		
										<input type="submit" value="Update status to 3" class="btn btn-primary" />
									</form:form>
							</td>
							</tr>					
				</c:forEach>
				</table>
				
<h1>Orders with status == 3(Finished)</h1>
<table>
				<thead>
					<th>Id</th>
					<th>User</th>	
					<th>Content</th>					
				</thead>
	<c:forEach var="order" items="${orders3}">
					
						<tr>
							<td>${order.id}</td>
							<td>${order.user.username}</td>
							<td>
							<c:forEach var="product" items="${order.products}">
								${product.product.name} ${product.quantity} | 
							</c:forEach>
							</td>							
							</tr>					
				</c:forEach>
				</table>
<a href="<c:url value='/'/>">main page</a>	
	</div>
	</div>	
</body>
</html>