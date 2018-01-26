package com.next.funshop.repository;

import com.next.funshop.domains.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
    /**
     * 根据状态获取商品信息
     * @Param productStatus 状态
     * @Return List<ProductInfo>
     * */
    List<ProductInfo> findByProductStatus(Integer productStatus);

    /**
     * 根据商品类目编码获取商品信息
     * @Param categoryType
     * @Param productStatus
     * @Return List<ProductInfo>
     * */
    List<ProductInfo> findByCategoryTypeAndProductStatus(Integer categoryType,Integer productStatus);

    /**
     * 更具商品类目编码获取可用商品信息
     * @Param categoryType
     * @Return List<ProductInfo>
     * */
    List<ProductInfo> findByCategoryType(Integer categoryType);
}
