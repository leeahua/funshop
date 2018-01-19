package com.next.funshop.utils;


import com.alibaba.fastjson.JSON;


public class JsonUtil {


    public static String toJsonStirng(Object object){
        return JSON.toJSONString(object);
    }

    public static int add(int a,int b){
        return  a+b;
    }

}
