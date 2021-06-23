package com.myproduct.myproduct.service.calculation;

import com.myproduct.myproduct.data.model.Discounts;
import com.myproduct.myproduct.data.model.ProductPrices;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class CartonPriceCalculator extends PriceCalculator{
    @Override
    public BigDecimal calculate(ProductPrices prices, Discounts discount, int unitCount) {
        BigDecimal cartonPrice = prices.getPrice();
        if (Objects.nonNull(discount) && Double.valueOf(unitCount) >= Double.valueOf(discount.getDiscountCondition())) {
            cartonPrice = cartonPrice.subtract(discount.getDiscount().divide(BigDecimal.valueOf(100)).multiply(cartonPrice));
        }
        return cartonPrice.multiply(BigDecimal.valueOf(unitCount));
    }
}
