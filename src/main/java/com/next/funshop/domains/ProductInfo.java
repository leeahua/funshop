package com.next.funshop.domains;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * Created on IDEA.
 * User: lyh
 * Date: 2017/10/6
 * Time: 21:34
 */
@Entity
@UpdateTimestamp
@Data
public class ProductInfo {

    /**商品id*/
    @Id
    private String productId;
    /**商品名称*/
    private String productName;
    /**商品价格*/
    private BigDecimal productPrice;
    /**商品库存*/
    private Integer productStock;
    /**商品描述*/
    private String productDescription;
    /**商品图片*/
    private String productIcon;
    /**商品状态  0正常 1 下架*/
    private Integer productStatus;
    /**商品类目编号*/
    private Integer categoryType;
    /**创建时间*/
    private Date createTime;
    /**更新时间*/
    private Date updateTime;
}
