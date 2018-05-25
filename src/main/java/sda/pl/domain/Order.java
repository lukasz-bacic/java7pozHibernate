package sda.pl.domain;

import lombok.Data;
import sda.pl.Price;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    LocalDateTime date;
    Price totalPrice;
    String email;

    @Column(name = "adres_wysylki")
    String cityName;
    boolean RODO;

    @OneToMany(mappedBy = "order")
    Set<OrderDetail> orderDetailSet;

}
