<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="v" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOME</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<style>
/* Optional custom styling */
.hero {
	background: url('path/to/your/hero-image.jpg') center center/cover
		no-repeat;
	height: 50vh;
	display: flex;
	justify-content: center;
	align-items: flex-start;
	color: #fff;
	background-color: #21D4FD;
	background-image: linear-gradient(19deg, #21D4FD 0%, #B721FF 100%);
	padding-top: 2rem;
	background-color: #FF9A8B;
}

.hero h1 {
	font-size: 4rem;
	text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.6);
}
</style>
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
					<li class="nav-item"><a class="nav-link" href="cart.do">Cart</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<section class="hero text-center mb-1">
		<h1>Shoe Details</h1>
	</section>

	<div class="container">
		<div class="row mb-4">
			<div class="col text-center">
				<h2 class="display-6">Options for Everyone</h2>
				<p class="lead">Shoe collection.</p>
			</div>
		</div>

		<div class="row">
			<div class="col-md-4 col-sm-6 mb-4">
				<div class="card shadow-sm">
					<img
						src="https://www.basspro.com/shop/en/merrell-morphlite-trail-running-shoes-for-men?hvarAID=shopping_googleproductextensions&amp;ds_e=GOOGLE&amp;ds_c=BPS%7CShopping%7CPMax%7CFootwear%7CGeneral%7CNAud%7CNVol%7CNMT&gad_source=1&gclid=CjwKCAiAyJS7BhBiEiwAyS9uNTLWHEf8fhWI4RSYly3EDQJ1w7X-lD4e4oTsZMVKtEtfi0i6Nu2IMRoCCOQQAvD_BwE&gclsrc=aw.ds"
						alt="${shoe.brand.name} - ${shoe.type.name}" class="img-thumbnail"
						style="height: 200px; object-fit: cover;">
					<div class="card-body">
						<h2>${shoe.brand.name}-${shoe.type.name}</h2>
						<p>
							<strong>Price:</strong> ${shoe.price}
						</p>
						<p>
							<strong>Type:</strong> ${shoe.type.name}
						</p>
						<form action="addToCart.do" method="post" class="mt-3">
							<input type="hidden" name="shoeId" value="${shoe.id}">
							<button type="submit" class="btn btn-primary">Add to
								Cart</button>
						</form>
					</div>
				</div>
				<br> <br>
			</div>
		</div>

	</div>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>