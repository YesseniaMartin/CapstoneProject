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
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="container mb-4">
				<div class="row justify-content-center">
					<div class="col-md-6">

						<form action="findShoeByKeyword.do" method="GET" class="d-flex">
							<input type="text" name="keyword" class="form-control me-2"
								placeholder="Search shoes..." />
							<button type="submit" class="btn btn-primary">Search</button>
						</form>
					</div>
				</div>
			</div>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ms-auto">
					<li class="nav-item"><a class="nav-link" href="logout.do">Logout</a></li>
					<li class="nav-item"><a class="nav-link" href="home.do">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="cart.do">
							Cart <span class="badge bg-secondary">${cartCount}</span>
					</a></li>
				</ul>
			</div>
		</div>
    </nav>

    <div class="container mt-5">
        <h1>Your Shopping Cart</h1>

        <!-- Display Success or Error Messages -->
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                ${successMessage}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                ${errorMessage}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>

        <!-- Check if cart is empty -->
        <c:if test="${empty cart or empty cart.inventoryItems}">
            <div class="alert alert-info" role="alert">
                Your cart is empty. <a href="home.do" class="alert-link">Go Shopping</a>
            </div>
        </c:if>

        <!-- Display Cart Items -->
        <c:if test="${not empty cart and not empty cart.inventoryItems}">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Brand</th>
                        <th>Type</th>
                        <th>Price</th>
                        <th>Total</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${cart.inventoryItems}">
                        <tr>
                            <td>${item.shoeId.brand.name}</td>
                            <td>${item.shoeId.type.name}</td>
                            <td>$${item.shoeId.price}</td>
                            <td>$${item.shoeId.price}</td>
                            <td>
                                <!-- Remove Item Form -->
                                <form action="removeFromCart.do" method="post" style="display:inline;">
                                    <input type="hidden" name="inventoryItemId" value="${item.id}">
                                    <button type="submit" class="btn btn-danger btn-sm">Remove</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Checkout and Clear Cart Buttons -->
            <div class="d-flex justify-content-between">
                <form action="checkout.do" method="post">
                    <button type="submit" class="btn btn-success">Checkout</button>
                </form>
                <form action="deleteCart.do" method="post">
                    <button type="submit" class="btn btn-warning">Clear Cart</button>
                </form>
            </div>
        </c:if>
    </div>

    <!-- Bootstrap JS -->
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>