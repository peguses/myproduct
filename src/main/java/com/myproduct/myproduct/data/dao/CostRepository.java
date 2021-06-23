package com.myproduct.myproduct.data.dao;

import com.myproduct.myproduct.data.model.CostType;
import com.myproduct.myproduct.data.model.Costs;
import com.myproduct.myproduct.data.model.ProductType;
import org.springframework.data.repository.CrudRepository;

public interface CostRepository extends CrudRepository<Costs, Long> {
    Costs findCostsByProductsIdAndCostTypeAndProductType(Long productId, CostType costType, ProductType productType);
}
