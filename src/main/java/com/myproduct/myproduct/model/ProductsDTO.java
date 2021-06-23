package com.myproduct.myproduct.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ProductsDTO {

    private Long productId;
    private String productName;

}
