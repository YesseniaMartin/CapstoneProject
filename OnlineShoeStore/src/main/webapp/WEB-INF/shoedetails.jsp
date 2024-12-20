<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shoe Details</title>
</head>
<body>
<c:forEach items="${shoes }" var="shoe">
	<p>Price:</p>
	${shoe.price }
	<p>Type:</p>
	${shoe.type }
	<p>Brand:</p>
	${shoe.brand }
</c:forEach>	
<form action="addToCart.do">
	<input type="submit" value="Add to Cart">
	</form><br>
	<br>	 
</body>
</html>