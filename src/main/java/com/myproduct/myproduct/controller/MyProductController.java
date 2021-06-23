package com.myproduct.myproduct.controller;

import com.myproduct.myproduct.model.PricesDTO;
import com.myproduct.myproduct.model.ProductTypeDTO;
import com.myproduct.myproduct.model.ProductsDTO;
import com.myproduct.myproduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyProductController {

    @Autowired
    private ProductService productService;

    @CrossOrigin
    @GetMapping("/products")
    public ResponseEntity<List<ProductsDTO>> getProducts() {
            return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/products/{productId}/list/{limit}")
    public ResponseEntity<List<PricesDTO>> getPriceList(@PathVariable int limit, @PathVariable Long productId) {
        return new ResponseEntity<>(productService.getPriceList(productId, limit), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/products/{productId}/price")
    public ResponseEntity<PricesDTO> getPrice(@PathVariable Long productId,
                                              @RequestParam(value = "type") ProductTypeDTO productType,
                                              @RequestParam int count) {
        return new ResponseEntity<>(productService.getPrice(productId, count, productType), HttpStatus.OK);
    }
}
