<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Cart</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="home.do">My Shoe Store</a>
		</div>
	</nav>

	<div class="container mt-5">
		<h1>Your Cart</h1>

		<!-- Check if cart is null or has no inventoryItems -->
		<c:if test="${empty cart or empty cart.inventoryItems}">
			<div class="alert alert-info" role="alert">Hey,
				${customer.firstName}, there's no item in your cart!</div>
			<a href="home.do" class="btn btn-primary">Go to Homepage</a>
		</c:if>

		<c:if test="${not empty cart and not empty cart.inventoryItems}">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Kind</th>
						<th>Brand</th>
						<th>Type</th>
						<th>Price</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${cart.inventoryItems}">
						<tr>
							<td>${item.shoeId.kind.name}</td>
							<td>${item.shoeId.brand.name}</td>
							<td>${item.shoeId.type}</td>
							<td>${item.shoeId.price}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>