package com.next.funshop.service;


import com.next.funshop.domains.ProductInfo;
import com.next.funshop.dto.CartDto;
import com.next.funshop.enums.ResultEnum;
import com.next.funshop.exeption.SellException;
import com.next.funshop.repository.ProductInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 处理商品的业务信息
 * @author liyaohua
 * @Date 20180116
 * */
@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;



    /**
     * @Description: <br>
     *  库存加
     * @return
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/1/26 下午3:34
     */
    @Transactional
    public void increaseStock(List<CartDto> cartDtoList){
        for(CartDto cartDto: cartDtoList){
            ProductInfo productInfo = productInfoRepository.findOne(cartDto.getProductId());
            if(productInfo==null){
                log.error("商品信息不存在！productId:{}",cartDto.getProductId());
                throw new SellException(ResultEnum.PRODUCT_NOT_EXISTS);
            }
            productInfo.setProductStock(productInfo.getProductStock()+cartDto.getProductQuantity());
            productInfoRepository.save(productInfo);
        }

    }

    /**
     * @Description: <br>
     *  库存减
     * @return
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/1/26 下午3:34
     */
    @Transactional
    public void decreaseStock(List<CartDto> cartDtoList){
        for(CartDto cartDto: cartDtoList){
            ProductInfo productInfo = productInfoRepository.findOne(cartDto.getProductId());
            if(productInfo==null){
                log.error("商品信息不存在！productId:{}",cartDto.getProductId());
                throw new SellException(ResultEnum.PRODUCT_NOT_EXISTS);
            }
            if (productInfo.getProductStock()<cartDto.getProductQuantity()){
                log.error("商品库存不足！request：{}",cartDto);
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(productInfo.getProductStock()-cartDto.getProductQuantity());
            productInfoRepository.save(productInfo);
        }
    }

    /**
     * 根据状态获取商品信息
     * @Param productStatus 状态
     * @Return List<ProductInfo>
     * */
    public List<ProductInfo> findByProductStatus(Integer productStatus){
        return productInfoRepository.findByProductStatus(productStatus);
    }

    /**
     *
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
