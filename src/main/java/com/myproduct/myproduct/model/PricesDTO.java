package com.myproduct.myproduct.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
public class PricesDTO {

    private Long productId;
    private BigDecimal price;
    private int unitCount;
}
