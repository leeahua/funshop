package com.next.funshop.utils;


import com.alibaba.fastjson.JSON;

/**
 * @Description: 工具类
 * @since  1.0.0
 * @author liyaohua
 * Create On 2018/1/23 下午5:54
 */
public class JsonUtil {


    public static String toJsonStirng(Object object){
        return JSON.toJSONString(object);
    }
    /**
     * @Description: <br>
     * @return int
     * @since  1.0.0
     * @author liyaohua
     * Create On 2018/1/23 下午5:54
     */
    public static int add(int a,int b){
        return  a+b;
    }

}
