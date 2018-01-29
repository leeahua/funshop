package com.next.funshop.repository;

import com.next.funshop.domains.OrderDetail;
import com.next.funshop.domains.ProductInfo;
import com.next.funshop.utils.KeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    @Transactional
    public void save(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId(KeyUtil.getUniqueKey());
        orderDetail.setOrderId("123456");
        ProductInfo productInfo = productInfoRepository.findOne("df9d8429-3d7");
        orderDetail.setProductIcon(productInfo.getProductIcon());
        orderDetail.setProductName(productInfo.getProductName());
        orderDetail.setProductPrice(productInfo.getProductPrice());
        orderDetail.setProductQuantity(2);
        orderDetail.setProductId(productInfo.getProductId());
        orderDetailRepository.save(orderDetail);
    }
}