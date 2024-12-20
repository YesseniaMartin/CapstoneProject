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
	<p>You can enter a search term here, to look up details for any shoes in our inventory</p><br>
	<form action="findShoeByKeyword.do">
	<input type= "text" name="Keyword" placeholder="input your search term here">
	<input type="sumbit" value="Search">
	</form><br>
	<br> 
</body>
</html>