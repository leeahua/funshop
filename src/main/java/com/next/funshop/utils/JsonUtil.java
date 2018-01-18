package com.next.funshop.utils;


import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

    public static String toJsonStirng(Object object){
        List<String> list = new ArrayList<>();
        return JSON.toJSONString(object);
    }

    public static int add(int a,int b){
        return  a+b;
    }

}
