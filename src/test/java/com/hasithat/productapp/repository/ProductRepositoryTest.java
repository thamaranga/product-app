package com.hasithat.productapp.repository;

import com.hasithat.productapp.entity.ProductEntity;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
/*If you want to configure real database as your test db(instead of H2) then below annotaion
 need to add. So, it will point to the database  in application.properties*/
@AutoConfigureTestDatabase(replace= Replace.NONE)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    /*By default all the test transactions will be rollbacked after executing the test case.
    * So, if you want to kep the data without rollback then we can use @Rollback(false)*/
    @Rollback(false)
    public void testCreateProduct(){
        ProductEntity product= new ProductEntity(0,"iphone SE",150000);
        ProductEntity savedProduct=productRepository.save(product);
        assertNotNull(savedProduct);
    }

    @Test
    public void testProductByNameExist(){
        String pName="iphone SE";
        ProductEntity product=productRepository.findByName(pName);
        assertThat(product.getName()).isEqualTo(pName);
        //assertNotNull(product);
    }

    @Test
    public void testProductByNameNotExist(){
        String pName="iphone 12";
        ProductEntity product=productRepository.findByName(pName);
        assertThat(product).isNull();
        //assertNotNull(product);
    }

    @Test
    public void testUpdateProduct(){
        String pName="iphone SE";
        ProductEntity product=productRepository.findByName(pName);
        product.setPrice(200000);
        ProductEntity updatedProduct=productRepository.save(product);
        assertThat(updatedProduct.getPrice()).isEqualTo(200000);
    }

    @Test
    public void testListProducts(){
        List<ProductEntity> productEntityList=productRepository.findAll();
        assertThat(productEntityList.size()).isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDeleteProduct(){
        String pName="iphone SE";
        ProductEntity existingProduct=productRepository.findByName(pName);
        assertThat(existingProduct).isNotNull();
        productRepository.deleteById(existingProduct.getId());
        ProductEntity existingProductNew=productRepository.findByName(pName);
        assertThat(existingProductNew).isNull();
    }

}