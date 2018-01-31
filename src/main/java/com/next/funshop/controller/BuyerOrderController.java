package com.next.funshop.controller;

import com.next.funshop.dto.OrderDto;
import com.next.funshop.enums.ResultEnum;
import com.next.funshop.exeption.SellException;
import com.next.funshop.form.OrderForm;
import com.next.funshop.service.OrderService;
import com.next.funshop.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

/**
 * 描述：〈买家订单处理〉
 *
 * @author liyaohua
 * @create 2018/1/31
 * @since 1.0.0
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * @Description: <br>
     *  创建订单
     * @return
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/1/31 下午5:06
     */
    @PostMapping("/create")
    public Object create(@Valid OrderForm orderForm, BindingResult bindingResult){
        log.info("【创建订单】请求信息:{}",orderForm);

        if(bindingResult.hasErrors()){
            log.error("【创建订单】,参数错误！orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        if(StringUtils.isBlank(orderForm.getItems())){
            log.error("【创建订单】，购物车不能为空！orderForm={}",orderForm);
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setOrderDetailList(JsonUtil.parseJsonArray(orderForm.getItems()));
        OrderDto result = orderService.create(orderDto);
        return result;
    }
}
