<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<title>Dayal's Bookstore</title>
<link href="webjars/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="card">
		<c:import url="myheader.jsp" />
	</div>
	<div class="card">
		<div class="card-body">
			<c:import url="mymenu.jsp" />
			<div class="container" align="center">
				<h2>
					<a href='<c:url value="viewBooks"/>' class="btn btn-success btn-lg">
						View Books</a> 
					<sec:authorize access="hasRole('ADMIN') or hasRole('ROLE_STOREKEEPER')">
					<br /><br /> 
					<a href='<c:url value="addBook"/>' class="btn btn-success btn-lg"> 
						Add Book </a> 
					<br /><br /> 
					<a href='<c:url value="editBook"/>' class="btn btn-success btn-lg"> 
						Edit Book </a> 
					</sec:authorize>
					<sec:authorize access="hasRole('ADMIN')">
					<br /><br /> 
					<a href='<c:url value="deleteBook"/>' class="btn btn-success btn-lg"> 
						Delete Book </a> 
					</sec:authorize>
					<sec:authorize access="hasRole('ROLE_CUSTOMER')">
					<br /><br /> 
					<a href='<c:url value="placeOrder"/>' class="btn btn-success btn-lg"> 
						Place Order </a>
					</sec:authorize>
				</h2>
			</div>
		</div>
	</div>
	<c:import url="myfooter.jsp" />
</body>
</html>