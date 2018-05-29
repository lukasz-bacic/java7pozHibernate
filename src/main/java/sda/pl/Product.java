package sda.pl;

import lombok.*;
import sda.pl.domain.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"orderDetailSet", "cartDetailSet",
        "productImage", "productRatingSet", "stockSet"})
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String name;
    @Embedded
    Price price;
    @Enumerated(EnumType.STRING)
    Color color;

    @OneToOne(mappedBy = "product")
    ProductImage productImage;

    @OneToMany(mappedBy = "product")
    Set<OrderDetail> orderDetailSet;

    @OneToMany(mappedBy = "product")
    Set<CartDetail> cartDetailSet;

    @OneToMany(mappedBy = "product")
    Set<ProductRating> productRatingSet;

    @OneToMany(mappedBy = "product")
    Set<Stock> stockSet;

    public void addStock(WarehouseName name, BigDecimal amount) {
        Stock stock = new Stock();
        stock.setProduct(this);
        stock.setWarehouseName(name);
        stock.setAmount(amount);
        if (stockSet == null) {
            stockSet = new HashSet<>();
            stockSet.add(stock);
        }else{
            Optional<Stock> stockExist = stockSet.stream().filter(s -> s.getWarehouseName().equals(name)).findFirst();
            stockExist.ifPresent(s -> {
                s.setAmount(s.getAmount().add(amount));
            });

        }

    }
}
