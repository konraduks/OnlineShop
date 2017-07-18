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
<h1>
		Add product
	</h1>
	<div class="span-12 last">	
		<form:form modelAttribute="product" method="post">
		  	<fieldset>		
				<legend>Product fields</legend>			
				<p>
					<form:label	for="name" path="name" cssErrorClass="error">Name</form:label><br/>
					<form:input path="name" /> <form:errors path="name" />			
				</p>
				<p>	
					<form:label for="price" path="price" cssErrorClass="error">Price</form:label><br/>
					<form:input path="price" /> <form:errors path="price" />
				</p>		
				<p>
					<select name="catProd">
						<c:forEach var="category" items="${categories}">
							<option>${category.name}</option>
					</c:forEach>
					<select>
				</p>	
				<p>	
					<input type="submit" value="Save"/>
				</p>
			</fieldset>
			
				
		</form:form>
		
	</div>
	<hr/>
	<a href="<c:url value='/'/>">main page</a>	
	</div>
	</div>	
</body>
</html>