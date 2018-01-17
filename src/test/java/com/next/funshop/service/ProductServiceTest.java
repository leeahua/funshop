package com.next.funshop.service;

import com.next.funshop.domains.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void findByProductStatusTest(){
        List<ProductInfo> productInfos = productService.findByProductStatus(0);
        Assert.assertEquals(2,productInfos.size());
    }

    @Test
    public void findByCategoryTypeAndProductStatus(){
        List<ProductInfo> productInfos = productService.findByCategoryTypeAndProductStatus(1,0);
        Assert.assertEquals(2,productInfos.size());
    }

    @Test
    public void findByCategoryType(){
        List<ProductInfo> productInfos = productService.findByCategoryType(1);
        Assert.assertEquals(2,productInfos.size());
    }

}