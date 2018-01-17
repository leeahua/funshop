package com.next.funshop.domains;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 商品类目.
 * User: lyh
 * Date: 2017/10/6
 * Time: 20:12
 */
@Entity
@Data
public class ProductCategory {
    /**类目id*/
    @Id
    @GeneratedValue
    private Integer categoryId;
    /**类目名称*/
    private String categoryName;
    /**类目编码*/
    private Integer categoryType;
    /**创建时间*/
    private Date createTime;
    /**更新时间*/
    private Date updateTime;



    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    public ProductCategory() {
    }
}
