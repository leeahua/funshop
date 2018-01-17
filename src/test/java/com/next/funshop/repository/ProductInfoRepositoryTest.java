package com.next.funshop.repository;

import com.alibaba.fastjson.JSON;
import com.next.funshop.domains.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;


    @Test
    @Transactional
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setCategoryType(1);
        productInfo.setProductDescription("第三个商品");
        productInfo.setProductIcon("www.baidu.com/1.jpg");
        productInfo.setProductName("燕麦片");
        productInfo.setProductPrice(new BigDecimal(11.50).setScale(2,BigDecimal.ROUND_CEILING));
        productInfo.setProductStatus(0);
        productInfo.setProductStock(50);
        productInfo.setProductId(UUID.randomUUID().toString().substring(0,12));
        productInfoRepository.save(productInfo);
        Assert.assertNotNull(productInfo.getProductId());
    }

    @Test
    public void findAllTest(){
        List<ProductInfo> productInfo = productInfoRepository.findAll();
        Assert.assertNotNull(productInfo);
        print(productInfo);
    }

    @Test
    public void findOneTest(){
        ProductInfo productInfo = productInfoRepository.findOne("546f8271-07f");
        Assert.assertEquals("546f8271-07f",productInfo.getProductId());
    }

    @Test
    public void updateTest(){
        ProductInfo productInfo = productInfoRepository.findOne("df9d8429-3d7");
        productInfo.setProductName("沙发");
        productInfo.setProductDescription("高端、大气");
        productInfo.setProductPrice(new BigDecimal(2222.80).setScale(2,BigDecimal.ROUND_CEILING));
        productInfo.setUpdateTime(new Date());
        productInfoRepository.save(productInfo);
        Assert.assertNotNull(productInfo.getProductId());
    }

    @Test
    @Transactional
    public void deleteTest(){
        productInfoRepository.delete("df9d8429-3d7");
        Assert.assertNull(productInfoRepository.findOne("df9d8429-3d7"));
    }

    @Test
    public void findByProductStatus(){
        List<ProductInfo> productInfos = productInfoRepository.findByProductStatus(0);
        Assert.assertEquals(2,productInfos.size());
    }

    @Test
    public void findByCategoryType(){
        List<ProductInfo> productInfos = productInfoRepository.findByCategoryType(1);
        Assert.assertEquals(2,productInfos.size());
    }


    private void print(Object obj){
        System.out.println(JSON.toJSONString(obj));
    }


}