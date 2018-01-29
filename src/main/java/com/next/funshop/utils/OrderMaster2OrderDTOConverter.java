package com.next.funshop.utils;

import com.next.funshop.domains.OrderMaster;
import com.next.funshop.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述：〈orderMaster转换为OrderDto〉
 *
 * @author liyaohua
 * @create 2018/1/29
 * @since 1.0.0
 */
public class OrderMaster2OrderDTOConverter {

    public static List<OrderDto> covent(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(orderMaster -> coventsignal(orderMaster)).collect(Collectors.toList());
    }

    private static OrderDto coventsignal(OrderMaster orderMaster) {
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        return orderDto;
    }
}
