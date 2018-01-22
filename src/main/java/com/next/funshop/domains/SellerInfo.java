package com.next.funshop.domains;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class SellerInfo {
    /**
     *
     *
     create table `seller_info` (
         `id` varchar(32) not null,
         `username` varchar(32) not null,
         `password` varchar(32) not null,
         `openid` varchar(64) not null comment '微信openid',
         `create_time` timestamp not null default current_timestamp comment '创建时间',
         `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
         primary key (`id`)
     ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
     * */
    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String password;
    private String openid;
    private Date createTime;
    private Date updateTime;
}
