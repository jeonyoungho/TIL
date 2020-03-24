<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.6">
    <title>eStore Home Page</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.4/examples/carousel/">

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <!-- Favicons -->
    <link rel="icon" href="<c:url value="/resources/favicon.ico"/>">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
    <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/css/carousel.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
  </head>
  <body>
    <header>
  <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <a class="navbar-brand" href="#">Carousel</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
          <a class="nav-link" href="<c:url value="/"/>">Home <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="<c:url value="/products"/>">Products</a> 
        </li>
        <li class="nav-item">
          <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
        </li>
      </ul>
      <form class="form-inline mt-2 mt-md-0">
        <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
      </form>
    </div>
  </nav>
</header>

<!-- container wrapper -->
<div class="container-wrapper">
<div class="container">
  <h2>All Products</h2>
  <p>착한 가격으로 상품을 살펴보세요.</p>
  <table class="table table-striped">
    <thead>
      <tr class="bg-success">
        <th>Name</th>
        <th>Category</th>
        <th>Price</th>
        <th>Menufacturer</th>
        <th>UnitInStock</th>
        <th>Description</th>
      </tr>
    </thead>
    <tbody>
     
     <c:forEach var="product" items="${products}">
     	<tr>
     	
     	<td> ${product.name}</td>
     	<td> ${product.category}</td>
     	<td> ${product.price}</td>
     	<td> ${product.manufacturer}</td>
     	<td> ${product.unitInStock}></td>
     	<td> ${product.description}</td>
     	
     	</tr>
     </c:forEach>
      
    </tbody>
  </table>
</div>

</div>

  <!-- FOOTER -->
  <footer class="container">
    <p class="float-right"><a href="#">Back to top</a></p>
    <p>&copy; 2020 Hansung Company, Inc. &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a></p>
  </footer>
</main>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="<c:url value="/resources/js/bootstrap.bundle.min.js"/>" integrity="sha384-6khuMg9gaYr5AxOqhkVIODVIvm9ynTT5J4V1cfthmT+emCG6yVmEZsRHdxlotUnm" crossorigin="anonymous"></script></body>
</html>

