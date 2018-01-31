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
    /**
     * 订单id
     * */
    private String orderId;//订单id
    /**
     * 买家名称
     * */
    private String buyerName;//买家名称
    /**
     * 买家手机号
     * */
    private String buyerPhone;//买家手机号
    /**
     * 买家地址
     * */
    private String buyerAddress;//买家地址
    /**
     * openid
     * */
    private String buyerOpenid;//买家openid
    /**
     * 订单金额
     * */
    private BigDecimal orderAmount;//订单金额
    /**
     *订单状态
     * */
    private Integer orderStatus;//订单状态
    /**
     *支付状态
     * */
    private Integer payStatus;//支付状态
    /**
     *创建时间
     * */
    private Date createTime;//创建时间
    /**
     *更新时间
     * */
    private Date updateTime;//更新时间
    /**
     * 购物车信息
     * */
    @JsonProperty("items")
    private List<OrderDetail> orderDetailList;
}
