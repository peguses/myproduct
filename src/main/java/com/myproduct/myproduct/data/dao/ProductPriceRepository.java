package com.myproduct.myproduct.data.dao;

import com.myproduct.myproduct.data.model.ProductPrices;
import com.myproduct.myproduct.data.model.ProductType;
import org.springframework.data.repository.CrudRepository;

public interface ProductPriceRepository  extends CrudRepository<ProductPrices, Long> {
    ProductPrices findProductPricesByProductsIdAndProductType(Long id, ProductType productType);
}
