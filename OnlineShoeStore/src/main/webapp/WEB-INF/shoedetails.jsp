<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shoe Details</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<style>
/* Optional custom styling */
.hero {
	background: url('path/to/your/hero-image.jpg') center center/cover
		no-repeat;
	height: 20vh;
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
<body>
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
			<c:forEach items="${shoes }" var="shoe">
				<div class="col-md-4 col-sm-6 mb-4">

					<div class="card shadow-sm">
						<a
							href="https://www.basspro.com/shop/en/merrell-morphlite-trail-running-shoes-for-men?hvarAI[…]EDQJ1w7X-lD4e4oTsZMVKtEtfi0i6Nu2IMRoCCOQQAvD_BwE&gclsrc=aw.ds">
							<img
							src="https://assets.basspro.com/image/upload/c_limit,dpr_1.0,f_auto,h_881,q_auto,w_1500/c_limit,h_881,w_1500/v1/ProductImages/500/stone_101432442_main?pgw=1"
							alt="${shoe.brand.name} - ${shoe.type.name}"
							class="img-thumbnail" style="height: 200px; object-fit: cover;">
						</a>

						<div class="card-body">
							<h2>${shoe.brand.name}-${shoe.type.name}</h2>
							<p>
								<strong>Price:&nbsp;</strong>${shoe.price }</p>
							<p>
								<strong>Type:&nbsp;</strong>${shoe.type.name }</p>
							<p>
								<strong>Brand:&nbsp;</strong>${shoe.brand.name }</p>

							<form action="addToCart.do" method="post" class="mt-3">
								<input type="hidden" name="shoeId" value="${shoe.id}">
								<button type="submit" class="btn btn-primary">Add to
									Cart</button>
							</form>
						</div>

					</div>
				</div>
			</c:forEach>
			<br> <br>
		</div>

	</div>
</body>
</html>