package com.myproduct.myproduct.data.dao;

import com.myproduct.myproduct.data.model.Discounts;
import com.myproduct.myproduct.data.model.ProductType;
import org.springframework.data.repository.CrudRepository;


public interface DiscountRepository extends CrudRepository<Discounts, Long> {
    Discounts findDiscountsByProductsIdAndActiveAndProductType(Long productId, boolean active, ProductType productType);
}
