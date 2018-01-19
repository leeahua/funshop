package com.next.funshop.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JsonUtilTest {

    @Test
    public void toJsonStirng() {
        Map<String,String> map = new HashMap<>();
        map.put("test","test");
        JsonUtil.toJsonStirng((Object) map);

    }



    @Test
    public void add() {
        JsonUtil.add(1,2);
    }

    @Test
    public void test(){
        JsonUtil jsonUtil = new JsonUtil();
        jsonUtil.add(2,3);
    }
}