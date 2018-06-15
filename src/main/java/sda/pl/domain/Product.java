package sda.pl.domain;

import lombok.*;
import sda.pl.Price;

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

    @Enumerated
    ProductType productType;

    @Embedded
    Price price;
    @Enumerated(EnumType.STRING)
    OrderComplaint.Color color;

    @OneToOne(mappedBy = "product")
    ProductImage productImage;

    @OneToMany(mappedBy = "product")
    Set<OrderDetail> orderDetailSet;

    @OneToMany(mappedBy = "product")
    Set<CartDetail> cartDetailSet;

    @OneToMany(mappedBy = "product")
    Set<ProductRating> productRatingSet;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<Stock> stockSet;

    public void addStock(WarehouseName name, BigDecimal amount) {
        if (stockSet == null) {
            stockSet = new HashSet<>();
        }
        Optional<Stock> stockExist = stockSet.stream().filter(s -> s.getWarehouseName().equals(name))
                .findFirst();
        if (!stockExist.isPresent()) {
            Stock stock = new Stock();
            stock.setProduct(this);
            stock.setWarehouseName(name);
            stock.setAmount(amount);
            stockSet.add(stock);
        } else {
            stockExist.ifPresent(s -> {
                s.setAmount(s.getAmount().add(amount));
            });

        }

    }


    public long getSumStockForSale() {
        return getStockSet().stream().filter(stock -> !stock.getWarehouseName().equals(WarehouseName.COMPLAINT))
                .mapToLong(s -> s.getAmount().longValue()).sum();
    }

    public void addProductRating(ProductRating productRating){
        if(productRatingSet == null){
            productRatingSet = new HashSet<>();
        }
        productRating.setProduct(this);
        productRatingSet.add(productRating);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public OrderComplaint.Color getColor() {
        return color;
    }

    public void setColor(OrderComplaint.Color color) {
        this.color = color;
    }

    public ProductImage getProductImage() {
        return productImage;
    }

    public void setProductImage(ProductImage productImage) {
        this.productImage = productImage;
    }

    public Set<OrderDetail> getOrderDetailSet() {
        return orderDetailSet;
    }

    public void setOrderDetailSet(Set<OrderDetail> orderDetailSet) {
        this.orderDetailSet = orderDetailSet;
    }

    public Set<CartDetail> getCartDetailSet() {
        return cartDetailSet;
    }

    public void setCartDetailSet(Set<CartDetail> cartDetailSet) {
        this.cartDetailSet = cartDetailSet;
    }

    public Set<ProductRating> getProductRatingSet() {
        return productRatingSet;
    }

    public void setProductRatingSet(Set<ProductRating> productRatingSet) {
        this.productRatingSet = productRatingSet;
    }

    public Set<Stock> getStockSet() {
        return stockSet;
    }

    public void setStockSet(Set<Stock> stockSet) {
        this.stockSet = stockSet;
    }
}
