<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Login</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<style>
/* Optional custom styling */
.hero {

	height: 40vh;
	display: flex;
	justify-content: center;
	align-items: flex-start;
	color: #fff;
	padding: 1rem 6rem;
	margin-top: 1rem;
	background-color: #FF9A8B;
	background-image: linear-gradient(19deg, #21D4FD 0%, #B721FF 100%);
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
			<a class="navbar-brand" href="login.do">Login </a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav"></div>
		</div>
	</nav>
	<section class="hero text-center mb-1">
  <div class="heading-container" style="display:inline-block; padding:1rem 2rem;">
    <h1 class="display-6">Welcome Back</h1>
  </div>
</section>
	
	<div class="container">
		<div class="row mb-6">
			<div class="col text-center">
				<h2 class="display-6">Please Sign In</h2>
				<p class="lead">Enter your credentials to access your account.</p>
			</div>
		</div>

		<div class="row justify-content-center">
			<div class="col-md-4">
				<div class="card shadow-sm p-4">
					<form action="login.do" method="POST">
						<div class="mb-3">
							<label for="username" class="form-label">Username</label> <input
								type="text" class="form-control" id="username" name="username"
								required>
						</div>

						<div class="mb-3">
							<label for="password" class="form-label">Password</label> <input
								type="password" class="form-control" id="password"
								name="password" required>
						</div>

						<button type="submit" class="btn btn-primary w-100">Login</button>
					</form>

					<c:if test="${not empty errorMessage}">
						<div class="alert alert-danger mt-3 text-center">${errorMessage}</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>