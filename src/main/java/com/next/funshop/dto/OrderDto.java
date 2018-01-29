package com.next.funshop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.next.funshop.domains.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 描述：订单展现类
 *
 * @author liyaohua
 * @create 2018/1/26
 * @since 1.0.0
 */
@Data
public class OrderDto {
    private String orderId;//订单id
    private String buyerName;//买家名称
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus;
    private Integer payStatus;
    private Date createTime;
    private Date updateTime;
    @JsonProperty("items")
    private List<OrderDetail> orderDetailList;
}
