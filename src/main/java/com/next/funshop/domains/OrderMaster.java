package com.next.funshop.domains;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class OrderMaster {
/**
 *
 * create table `order_master` (
 `order_id` varchar(32) not null,
 `buyer_name` varchar(32) not null comment '买家名字',
 `buyer_phone` varchar(32) not null comment '买家电话',
 `buyer_address` varchar(128) not null comment '买家地址',
 `buyer_openid` varchar(64) not null comment '买家微信openid',
 `order_amount` decimal(8,2) not null comment '订单总金额',
 `order_status` tinyint(3) not null default '0' comment '订单状态, 默认为新下单',
 `pay_status` tinyint(3) not null default '0' comment '支付状态, 默认未支付',
 `create_time` timestamp not null default current_timestamp comment '创建时间',
 `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
 primary key (`order_id`),
 key `idx_buyer_openid` (`buyer_openid`)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 *
 * */
    @Id
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

}
