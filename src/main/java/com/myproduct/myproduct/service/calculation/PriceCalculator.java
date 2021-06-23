package com.myproduct.myproduct.service.calculation;

import com.myproduct.myproduct.data.model.Cartons;
import com.myproduct.myproduct.data.model.Costs;
import com.myproduct.myproduct.data.model.Discounts;
import com.myproduct.myproduct.data.model.ProductPrices;
import com.myproduct.myproduct.exceptions.CalculationNotSupportedException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public abstract class  PriceCalculator {
    public BigDecimal calculate(ProductPrices prices, Discounts discounts, int unitCount) {
        throw new CalculationNotSupportedException("Price calculation not supported yet");
    }
    public BigDecimal calculate(ProductPrices prices, Discounts discounts, Costs cost, Cartons carton, int unitCount) {
        throw new CalculationNotSupportedException("Price calculation not supported yet");
    }
}
