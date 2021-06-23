package com.myproduct.myproduct.data.dao;

import com.myproduct.myproduct.data.model.Products;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Products, Long> {
    List<Products> findAll();

    Optional<Products> findById(Long id);
}
