package com.hasithat.productapp.service;

import com.hasithat.productapp.entity.ProductEntity;
import com.hasithat.productapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductEntity saveProduct(ProductEntity product){
        return productRepository.save(product);
    }

    public List<ProductEntity> listAllProducts(){
        List<ProductEntity> allProducts=productRepository.findAll();
        return allProducts;
    }



    public ProductEntity getProductByName(String name){
        return productRepository.findByName(name);
    }

    public ProductEntity getProductById(int id){
        Optional<ProductEntity> productEntityOptional=productRepository.findById(id);
        ProductEntity product=null;
        if(productEntityOptional.isPresent()){
            product= productEntityOptional.get();
        }
        return product;
    }

    public boolean deleteProductById(int id){
        boolean isDeleted=false;
        productRepository.deleteById(id);
        isDeleted=true;
        return isDeleted;
    }

    public ProductEntity updateProduct(ProductEntity productEntity){
        return productRepository.save(productEntity);
    }






}
