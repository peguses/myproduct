package com.myproduct.myproduct.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
public class Products {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String productName;

    @OneToOne(mappedBy = "products")
    private Cartons carton;

    @OneToMany(mappedBy="products")
    private List<ProductPrices> productPrice;

    @OneToMany(mappedBy = "products")
    private List<Discounts> discount;

    @OneToMany(mappedBy = "products")
    private List<Costs> costs;

}
