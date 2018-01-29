package com.next.funshop.service;

import com.next.funshop.domains.OrderDetail;
import com.next.funshop.dto.OrderDto;
import com.next.funshop.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void create() {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("张三");
        orderDto.setBuyerPhone("18868822111");
        orderDto.setBuyerAddress("北京");
        orderDto.setBuyerOpenid("ew3euwhd7sjw9diwkq");
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("546f8271-07f");
        orderDetail.setProductQuantity(1);
        List<OrderDetail> orderDetailList = new ArrayList<>(1);
        orderDetailList.add(orderDetail);
        orderDto.setOrderDetailList(orderDetailList);
        orderService.create(orderDto);
    }

    @Test
    public void findone(){
        String orderId = "1516956689155354982";
        OrderDto orderDto = orderService.findOne(orderId);
        Assert.assertNotNull(orderDto);
        log.info(JsonUtil.toJson(orderDto));
    }


}