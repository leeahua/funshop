package com.next.funshop.vo;


/**
 * Http请求返回的最外层对象.
 * User: lyh
 * Date: 2017/10/7
 * Time: 17:06
 */

public class ResultVO<T> {

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**错误码*/
    private Integer code;
    /**提示信息*/
    private String msg;
    /**具体信息*/
    private T data;
}
