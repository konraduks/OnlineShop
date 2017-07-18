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
<h1>
		Add category
	</h1>
	<div class="span-12 last">	
		<form:form modelAttribute="category" method="post">
		  	<fieldset>		
				<legend>Category</legend>			
				<p>
					<form:label	for="name" path="name" cssErrorClass="error">Name</form:label><br/>
					<form:input path="name" /> <form:errors path="name" />			
				</p>							
				<p>	
					<input type="submit" value="Add"/>
				</p>
			</fieldset>
		</form:form>
	</div>
	<hr/>
<h1>Category list</h1>
<table>
				<thead>
					<th>Id</th>
					<th>Name</th>					
				</thead>
	<c:forEach var="category" items="${categories}">
					
						<tr>
							<td>${category.id}</td>
							<td>${category.name}</td>
							<td>
							<form:form method="post" action="deleteCategory">
										<input type="hidden" name="catId"	value="${category.id}" /> 
										<input type="submit" value="Delete category" class="btn btn-danger" />
									</form:form>
							</td>
							</tr>
					
				</c:forEach>
				</table>
<!-- products without cat -->
<h1>Products without category</h1>
<table>
				<thead>
					<th>Id</th>
					<th>Name</th>					
				</thead>
	<c:forEach var="products" items="${productsWithoutCat}">
					
						<tr>
							<td>${products.id}</td>
							<td>${products.name}</td>
							<td>
							<form:form method="post" action="setCategory">
										<input type="hidden" name="catId"	value="${products.id}" /> 
										<select name="choosenCat">
											<c:forEach var="category" items="${categories}">
											<option>${category.name}</option>
											</c:forEach>
										</select>
										<input type="submit" value="Update category" class="btn btn-primary" />
									</form:form>
							</td>
							</tr>
					
				</c:forEach>
				</table>

<!-- end product wihout cat	 -->	

<!-- all products -->	
<h1>All Products</h1>
<table>
				<thead>
					<th>Id</th>
					<th>Name</th>					
				</thead>
	<c:forEach var="products" items="${allproductswithCat}">
					
						<tr>
							<td>${products.id}</td>
							<td>${products.name}	</td>
							<td>|	${products.category.name}</td>
							<td>
							<form:form method="post" action="setCategory">
										<input type="hidden" name="catId"	value="${products.id}" /> 
										<select name="choosenCat">
											<c:forEach var="category" items="${categories}">
											<option>${category.name}</option>
											</c:forEach>
										</select>
										<input type="submit" value="Update category" class="btn btn-warning" />
									</form:form>
							</td>
							</tr>
					
				</c:forEach>
				</table>

<a href="<c:url value='/'/>">main page</a>
<!-- end all products -->
</div>
	</div>				
</body>
</html>