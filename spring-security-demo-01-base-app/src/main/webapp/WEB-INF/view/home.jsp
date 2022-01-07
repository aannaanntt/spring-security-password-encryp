<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Hello Peter

	<!-- -add logout button -->

	<!-- -display name and role -->

	<p>
		USER :
		<security:authentication property="principal.username" />
		<br>
		<br> ROLE(s):
		<security:authentication property="principal.authorities" />
</p>
<security:authorize access="hasRole('Manager')">
	<!-- Add a link to point to /leaders  this is for managers-->
	<p>
		<a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>
		(Only for Manager peeps )
	</p>
	</security:authorize>
	
	<security:authorize access = "hasRole('Admin')">
	<p>
		<a href="${pageContext.request.contextPath}/systems">System Meeting</a>
		(Only for Admin peeps )
	</p>
	</security:authorize>
	
	<form:form action="${pageContext.request.contextPath }/logout"
		method="POST">


		<input type="submit" value="logout" />

	</form:form>



</body>
</html>