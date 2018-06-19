package sda.pl.web;


import sda.pl.domain.Product;
import sda.pl.repository.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "ProductImageServlet", urlPatterns = "/productImage")
public class ProductImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        Long productId = Long.valueOf(req.getParameter("productId"));
        Optional<Product> product = ProductRepository.findProduct(productId);

        if(product.isPresent()){
            //response.setHeader("Content-Type", contentType);
            response.setHeader("Content-Length", String.valueOf(product.get().getProductImage().getImage().length));
            response.setHeader("Content-Disposition", "inline; filename=\"" + product.get().getName() + "\"");

            // Write image data to Response.
            response.getOutputStream().write(product.get().getProductImage().getImage());
        }

    }

}
