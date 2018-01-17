package com.next.funshop.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class JsonUtilTest {

    @Test
    public void toStringTest(){
        Map<String,String> map = new HashMap();
        map.put("aa","11");
        map.put("bb","22");
        String result = JsonUtil.toJsonStirng(map);
        System.out.println(result);
    }

    @Test
    public void addTest(){
        int c = JsonUtil.add(1,2);
        Assert.assertEquals(3,c);
    }

}