package com.myproduct.myproduct.service;


import com.myproduct.myproduct.data.dao.CostRepository;
import com.myproduct.myproduct.data.dao.DiscountRepository;
import com.myproduct.myproduct.data.dao.ProductPriceRepository;
import com.myproduct.myproduct.data.dao.ProductRepository;
import com.myproduct.myproduct.data.model.*;
import com.myproduct.myproduct.exceptions.ResourceNotFoundException;
import com.myproduct.myproduct.model.PricesDTO;
import com.myproduct.myproduct.model.ProductTypeDTO;
import com.myproduct.myproduct.model.ProductsDTO;
import com.myproduct.myproduct.service.calculation.CartonPriceCalculator;
import com.myproduct.myproduct.service.calculation.PriceCalculator;
import com.myproduct.myproduct.service.calculation.UnitPriceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final PriceCalculator cartonPriceCalculator;
    private final PriceCalculator unitPriceCalculator;
    private final ProductPriceRepository productPriceRepository;
    private final DiscountRepository discountRepository;
    private final CostRepository costRepository;

    @Autowired
    public  ProductService(ProductRepository productRepository,
                           CartonPriceCalculator cartonPriceCalculator,
                           UnitPriceCalculator unitPriceCalculator,
                           ProductPriceRepository productPriceRepository,
                           DiscountRepository discountRepository,
                           CostRepository costRepository) {
        this.productRepository = productRepository;
        this.cartonPriceCalculator = cartonPriceCalculator;
        this.unitPriceCalculator = unitPriceCalculator;
        this.productPriceRepository = productPriceRepository;
        this.discountRepository = discountRepository;
        this.costRepository = costRepository;
    }

    public List<ProductsDTO> getProducts() {
       return  productRepository.findAll().stream()
               .map(product-> new ProductsDTO(product.getId(), product.getProductName())).collect(Collectors.toList());
    }

    public  List<PricesDTO> getPriceList(Long productId, int limit) {
       var product =  getProduct(productId);
       var cartonPrices = productPriceRepository.findProductPricesByProductsIdAndProductType(productId, ProductType.CARTON);
       var discount = discountRepository.findDiscountsByProductsIdAndActiveAndProductType(productId, true, ProductType.CARTON);
       var cost = costRepository.findCostsByProductsIdAndCostTypeAndProductType(productId, CostType.UNIT_PREP_LABOUR_COST, ProductType.UNIT);
       var carton =  product.getCarton();
       return IntStream.range(1, (limit+1))
               .mapToObj(count -> getPrice(productId, count, cartonPrices, discount,cost, carton))
               .collect(Collectors.toList());
    }

    private PricesDTO getPrice(Long productId, int unitCount, ProductPrices cartonPrices, Discounts discount, Costs cost,  Cartons carton) {

        if(unitCount % carton.getUnitCount() == 0) {
            int cartonCount = unitCount/carton.getUnitCount();
            return  new PricesDTO(productId, cartonPriceCalculator.calculate(cartonPrices, discount, cartonCount), unitCount);

        } else {
            int quotient = unitCount / carton.getUnitCount();
            int remainder = unitCount % carton.getUnitCount();
            BigDecimal quotientPrice = cartonPriceCalculator.calculate(cartonPrices, discount, quotient)
                    .add(unitPriceCalculator.calculate(cartonPrices, discount,cost, carton, remainder));
            return new PricesDTO(productId, quotientPrice, unitCount);
        }

    }

    public PricesDTO getPrice(Long productId, int unitCount, ProductTypeDTO productType) {
        var product =  getProduct(productId);
        var cartonPrices = productPriceRepository.findProductPricesByProductsIdAndProductType(productId, ProductType.CARTON);
        var discount = discountRepository.findDiscountsByProductsIdAndActiveAndProductType(productId, true, ProductType.CARTON);
        var cost = costRepository.findCostsByProductsIdAndCostTypeAndProductType(productId, CostType.UNIT_PREP_LABOUR_COST, ProductType.UNIT);

        if(productType == ProductTypeDTO.UNIT) {
            int quotient = unitCount / product.getCarton().getUnitCount();
            int remainder = unitCount % product.getCarton().getUnitCount();
            BigDecimal quotientPrice = cartonPriceCalculator.calculate(cartonPrices, discount, quotient)
                    .add(unitPriceCalculator.calculate(cartonPrices, discount,cost, product.getCarton(), remainder));
            return  new PricesDTO(productId, quotientPrice, unitCount);

        } else {
            return  new PricesDTO(productId, cartonPriceCalculator.calculate(cartonPrices, discount, unitCount), unitCount);
        }

    }

    public Products getProduct(Long productId) {
       return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));
    }



}
