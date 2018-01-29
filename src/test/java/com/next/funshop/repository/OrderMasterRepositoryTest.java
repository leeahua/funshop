package com.next.funshop.repository;

import com.next.funshop.domains.OrderMaster;
import com.next.funshop.enums.OrderStatusEnum;
import com.next.funshop.enums.PayStatusEnum;
import groovy.util.logging.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    @Transactional
    public void save(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerAddress("北京");
        orderMaster.setBuyerName("张三");
        orderMaster.setBuyerOpenid("qawsedrf");
        orderMaster.setBuyerPhone("13260170808");
        orderMaster.setOrderAmount(new BigDecimal(20.5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.UN_PAY.getCode());
        orderMasterRepository.save(orderMaster);
    }
    @Test
    public void find(){
        OrderMaster orderMaster = orderMasterRepository.findOne("123456");
        Assert.assertNotNull(orderMaster);
        orderMaster.setBuyerPhone("13139077878");
        orderMasterRepository.save(orderMaster);
    }


}