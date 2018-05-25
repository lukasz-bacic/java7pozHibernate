package sda.pl;

import org.hibernate.Session;
import sda.pl.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class App {

    public static void main(String[] args) {

        Product maslo = Product.builder()
                .name("Maslo")
                .color(Color.WHITE)
                .price(Price.builder().
                        priceGross(new BigDecimal("7.50"))
                        .priceNet(new BigDecimal("5.25"))
                        .priceSymbol("PLN").build()).build();

        ProductRepository.saveProduct(maslo);


        Optional<Product> product = ProductRepository.findProduct(2L);
        product.ifPresent(p -> System.out.println(p.getName()));

        ProductRepository.findAll().forEach(p -> System.out.println(p.getId() +" "+p.getName()));





    }
}