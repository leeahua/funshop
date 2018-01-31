package com.next.funshop.exeption;

import com.next.funshop.enums.ResultEnum;

/**
 * 描述：〈异常处理〉
 *
 * @author liyaohua
 * @create 2018/1/26
 * @since 1.0.0
 */
public class SellException  extends RuntimeException{

    private String code;

    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(String code,String message){
        super(message);
        this.code = code;

    }
}
