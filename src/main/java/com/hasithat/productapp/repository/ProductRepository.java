package com.hasithat.productapp.repository;

import com.hasithat.productapp.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity , Integer> {

    public ProductEntity findByName(String name);


}
