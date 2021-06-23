package com.myproduct.myproduct.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Costs {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Products products;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Enumerated(EnumType.STRING)
    CostType costType;

    String costCalculateFactor;

}
