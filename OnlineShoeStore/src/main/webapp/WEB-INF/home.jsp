<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOME</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<style>
/* Optional custom styling */
.hero {
  background: url('path/to/your/hero-image.jpg') center center/cover no-repeat;
  height: 60vh;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #fff;
}
.hero h1 {
  font-size: 3rem;
  text-shadow: 2px 2px 4px rgba(0,0,0,0.6);
}
</style>
</head>

<body class="bg-light">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="home.do">My Shoe Store</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" 
       aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
       <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item"><a class="nav-link" href="login.do">Add Shoe</a></li>
        <li class="nav-item"><a class="nav-link" href="home.do">Home</a></li>
        <li class="nav-item"><a class="nav-link" href="deleteShoe.do">Delete Shoe</a></li>
        <li class="nav-item"><a class="nav-link" href="update.do">Edit Shoe</a></li>
        <!-- add -->
      </ul>
    </div>
  </div>
</nav>

<section class="hero text-center mb-5">
  <h1>Step into Style</h1>
</section>

<div class="container">
  <div class="row mb-4">
    <div class="col text-center">
      <h2 class="display-6">Our Latest Shoes</h2>
      <p class="lead">Explore the newest additions to our collection.</p>
    </div>
  </div>

  <div class="row">
    <!-- Assuming `shoes` is a list of Shoe objects with shoe.img, brand name, and description -->
    <c:forEach var="shoe" items="${shoes}">
      <div class="col-md-4 col-sm-6 mb-4">
        <div class="card shadow-sm">
          <img src="shoe.img" class="card-img-top" alt="${shoe.brand.name}" style="height: 200px; object-fit: cover;">
          <div class="card-body">
            <h5 class="card-title">${shoe.brand.name} ${shoe.type.name}</h5>
            <p class="card-text">${shoe.description}</p>
            <a href="getShoeDetails.do?shoeId=${shoe.id}" class="btn btn-primary">View Details</a>
          </div>
        </div>
      </div>
    </c:forEach>

    <c:if test="${empty shoes}">
      <div class="col-12">
        <div class="alert alert-warning text-center">No shoes available at the moment!</div>
      </div>
    </c:if>
  </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>