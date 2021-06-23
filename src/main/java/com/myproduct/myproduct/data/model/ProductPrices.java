package com.myproduct.myproduct.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Setter
@Getter
public class ProductPrices {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Products products;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

}
