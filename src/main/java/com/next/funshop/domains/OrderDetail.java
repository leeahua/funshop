package com.next.funshop.domains;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.beans.BeanInfo;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class OrderDetail {

    /**
     *
     * create table `order_detail` (
     `detail_id` varchar(32) not null,
     `order_id` varchar(32) not null,
     `product_id` varchar(32) not null,
     `product_name` varchar(64) not null comment '商品名称',
     `product_price` decimal(8,2) not null comment '当前价格,单位分',
     `product_quantity` int not null comment '数量',
     `product_icon` varchar(512) comment '小图',
     `create_time` timestamp not null default current_timestamp comment '创建时间',
     `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
     primary key (`detail_id`),
     key `idx_order_id` (`order_id`),
     foreign key(`order_id`) REFERENCES order_master(`order_id`)
     )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
     *
     * */
    @Id
    private String detailId;
    private String orderId;
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productQuantity;
    private String productIcon;
    private Date createTime;
    private Date updateTime;
}
