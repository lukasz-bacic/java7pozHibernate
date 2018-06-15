<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="sda.pl.repository.ProductRepository" %>
<%@ page import="java.util.List" %>
<%@ page import="sda.pl.domain.Product" %>
<%@ page import="java.util.Optional" %>
<%--
  Created by IntelliJ IDEA.
  User: lbacic_adm
  Date: 14.06.2018
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%


    Long productId = Long.valueOf(request.getParameter("productId"));
    Optional<Product> product = ProductRepository.findProduct(productId);
    if(product.isPresent()) {
        pageContext.setAttribute("product", product.get());
    }else{
        //TODO dodac przekierowanie
    }

%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Shop Homepage - Start Bootstrap Template</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/shop-homepage.css" rel="stylesheet">

</head>

<body>

<%@include file="header.jsp"%>

<!-- Page Content -->
<div class="container">

    <div class="row">

        <div class="col-lg-3">

           <%@include file="leftMenu.jsp"%>

        </div>
        <!-- /.col-lg-3 -->

        <div class="col-lg-9">

            <div class="row">
                <div class="col-lg-12 col-md-12 mb-12">
                    <div class="card h-100">
                        <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
                        <div class="card-body">
                            <h4 class="card-title">
                                <a href="#">${product.name}</a>
                            </h4>
                            <h5>${product.price.priceGross} ${product.price.priceNet}</h5>
                            <p class="card-text">${product.color}</p>
                        </div>
                        <div class="card-footer">
                            <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
                        </div>
                        <div>
                            <form action="/addProductToCart?productId=${product.id}" method="post">
                                <label for="productAmount" >Liczba produktów: </label>
                                <input name="productAmount" id="productAmount" value="1" type="number">
                                <button type="submit">Dodaj do koszyka</button>
                            </form>

                        </div>
                    </div>
                </div>



            </div>
            <!-- /.row -->

        </div>
        <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->

</div>
<!-- /.container -->

<%@include file="footer.jsp"%>

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
