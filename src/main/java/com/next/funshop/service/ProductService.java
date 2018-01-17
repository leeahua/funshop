package com.next.funshop.service;


import com.next.funshop.domains.ProductInfo;
import com.next.funshop.repository.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 处理商品的业务信息
 * @author liyaohua
 * @Date 20180116
 * */
@Service
public class ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    /**
     * 根据状态获取商品信息
     * @Param productStatus 状态
     * @Return List<ProductInfo>
     * */
    public List<ProductInfo> findByProductStatus(Integer productStatus){
        return productInfoRepository.findByProductStatus(productStatus);
    }

    /**
     * 更具商品类目编码获取商品信息
     * @Param categoryType
     * @Param productStatus
     * @Return List<ProductInfo>
     * */
    List<ProductInfo> findByCategoryTypeAndProductStatus(Integer categoryType,Integer productStatus){
        return productInfoRepository.findByCategoryTypeAndProductStatus(categoryType,productStatus);
    }

    /**
     * 更具商品类目编码获取可用商品信息
     * @Param categoryType
     * @Return List<ProductInfo>
     * */
    List<ProductInfo> findByCategoryType(Integer categoryType){
        return productInfoRepository.findByCategoryType(categoryType);
    }



}
