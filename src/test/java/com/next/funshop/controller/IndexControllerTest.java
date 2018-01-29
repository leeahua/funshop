package com.next.funshop.controller;

import com.next.funshop.utils.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class IndexControllerTest {

    @Autowired
    private IndexController indexController;

    @Test
    public void data() {
        Map<String,String> data = new HashMap<>();
        data.put("aaa","1234");
        data.put("bbb","5678");
        Assert.assertEquals(JsonUtil.toJson(data),JsonUtil.toJson(indexController.data()));
    }
}