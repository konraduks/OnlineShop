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
	Register user
	</h1>
	<div class="span-12 last">
		<form:form modelAttribute="user" method="post">
			<fieldset>
				<legend>Create user fields</legend>
				<p>
					<form:label for="username" path="username" cssErrorClass="error">username</form:label>
					<br />
					<form:input path="username" />
					<form:errors path="username" />
				</p>
				<p>
					<form:label for="password" path="password" cssErrorClass="error">password</form:label>
					<br />
					<form:input type="password" path="password" />
					<form:errors path="password" />
				</p>
				<%-- <p>
					<label>confirm password</label><br /> <input id="conpassword" />
					<c:choose>
						<c:when test="${password != conpassword}">
							<label class="bg-danger">passwords are not the same</label>
						</c:when>
						<c:otherwise>
							<label class="bg-success">passwords are the same</label>
						</c:otherwise>
					</c:choose>
				</p> --%>
				<p>
					<%-- <c:when test="${password == conpassword}">
					<input type="submit" value="Save"/>
				</c:when> --%>
					<input type="submit" value="Save" />
				</p>
			</fieldset>
		</form:form>
	</div>
	</div>
	</div>		
</body>
</html>