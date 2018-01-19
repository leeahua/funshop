package com.next.funshop.vo;


import lombok.Data;

/**
 * Http请求返回的最外层对象.
 * User: lyh
 * Date: 2017/10/7
 * Time: 17:06
 */
@Data
public class ResultVO<T> {
    /**错误码*/
    private Integer code;
    /**提示信息*/
    private String msg;
    /**具体信息*/
    private T data;

}
