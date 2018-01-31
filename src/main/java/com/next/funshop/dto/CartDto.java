package com.next.funshop.dto;

import lombok.Data;

/**
 * 描述：〈购物车〉
 *
 * @author liyaohua
 * @create 2018/1/26
 * @since 1.0.0
 */
@Data
public class CartDto {
    /**
     * 商品id
     * */
    private String productId;
    /**
     * 商品数量
     * */
    private Integer productQuantity;

    public CartDto(String productId,Integer productQuantity){
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
