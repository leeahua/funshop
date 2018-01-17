package com.next.funshop.repository;

import com.next.funshop.domains.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    @Transactional
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("食品");
        productCategory.setCategoryType(1);
        productCategoryRepository.save(productCategory);
        Assert.assertNotNull(productCategory.getCategoryId());
    }
    @Test
    public void findOneTest(){
        ProductCategory productCategory = productCategoryRepository.findOne(1);
        Assert.assertNotNull(productCategory);
    }
    @Test
    @Transactional
    public void deleteTest(){
        productCategoryRepository.delete(1);
        Assert.assertNull(productCategoryRepository.findOne(1));

    }
}
