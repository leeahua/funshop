package com.next.funshop.controller;

import com.next.funshop.utils.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyerProductControllerTest {

    @Autowired
    private BuyerProductController productController;

    @Test
    public void list() {
        Object respond = productController.list();
        System.out.println(JsonUtil.toJson(respond));
    }
}