package com.next.funshop.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {
    UN_PAY(0,"未支付"),
    PAY_FINISH(1,"支付完成");

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
