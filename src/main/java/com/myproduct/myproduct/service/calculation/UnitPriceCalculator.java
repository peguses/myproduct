package com.myproduct.myproduct.service.calculation;

import com.myproduct.myproduct.data.model.Cartons;
import com.myproduct.myproduct.data.model.Costs;
import com.myproduct.myproduct.data.model.Discounts;
import com.myproduct.myproduct.data.model.ProductPrices;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UnitPriceCalculator extends  PriceCalculator {
    @Override
    public BigDecimal calculate(ProductPrices prices, Discounts discount, Costs cost, Cartons carton, int unitCount) {
        BigDecimal increasedPrice = prices.getPrice().multiply(BigDecimal.valueOf(Double.valueOf(cost.getCostCalculateFactor())));
        return increasedPrice.divide(BigDecimal.valueOf(carton.getUnitCount())).multiply(BigDecimal.valueOf(unitCount));
    }
}
