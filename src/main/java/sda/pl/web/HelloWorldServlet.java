package sda.pl.web;

import sda.pl.domain.Product;
import sda.pl.repository.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HelloWorldServlet", urlPatterns = "/hello")
public class HelloWorldServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstName = request.getParameter("firstName");

        List<Product> all = new ArrayList<>();
        Product product = new Product();
        product.setName("Maslo");
        all.add(product);

        PrintWriter writer = response.getWriter();
        writer.write("<html><head></head><body><h1>Hello World !"+firstName
                +" !!!</h1>");

        writer.write("<table><tr><td>Nazwa</td><td>Id</td></tr>");
        for(Product p : all){
            writer.write("<tr><td>"+p.getName()+"</td><td>a</td></tr>");
        }
        writer.write("</table>");
        writer.write("</body></html>");

    }
}
