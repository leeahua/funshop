package com.next.funshop.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 * view 层商品信息.
 * User: lyh
 * Date: 2017/10/7
 * Time: 17:15
 */
public class ProductInfoVO {
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductIcon() {
        return productIcon;
    }

    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }

    /**商品id*/
    @JsonProperty("id")
    private String productId;
    /**商品名称*/
    @JsonProperty("name")
    private String productName;
    /**商品价格*/
    @JsonProperty("price")
    private BigDecimal productPrice;
    /**商品描述*/
    @JsonProperty("description")
    private String productDescription;
    /**商品图片*/
    @JsonProperty("icon")
    private String productIcon;
}
