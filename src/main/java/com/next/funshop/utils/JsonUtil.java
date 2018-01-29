package com.next.funshop.utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Description: 工具类
 * @since  1.0.0
 * @author liyaohua
 * Create On 2018/1/23 下午5:54
 */
public class JsonUtil {


    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.setDateFormat("yyyy-HH-dd HH:mm:ss");
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
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
