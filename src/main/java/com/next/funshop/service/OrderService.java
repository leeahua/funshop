package com.next.funshop.service;

import com.next.funshop.domains.OrderDetail;
import com.next.funshop.domains.OrderMaster;
import com.next.funshop.domains.ProductInfo;
import com.next.funshop.dto.CartDto;
import com.next.funshop.dto.OrderDto;
import com.next.funshop.enums.OrderStatusEnum;
import com.next.funshop.enums.PayStatusEnum;
import com.next.funshop.enums.ResultEnum;
import com.next.funshop.exeption.SellException;
import com.next.funshop.repository.OrderDetailRepository;
import com.next.funshop.repository.OrderMasterRepository;
import com.next.funshop.repository.ProductInfoRepository;
import com.next.funshop.utils.KeyUtil;
import com.next.funshop.utils.OrderMaster2OrderDTOConverter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ManyToAny;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述：〈订单业务逻辑处理〉
 *
 * @author liyaohua
 * @create 2018/1/26
 * @since 1.0.0
 */
@Service
@Slf4j
public class OrderService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductService productService;

    /**
     * @Description: <br>
     *  创建订单
     * @return
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/1/26 上午11:21
     */
    @Transactional
    public OrderDto create(OrderDto orderDto){
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderId(KeyUtil.getUniqueKey());
        BigDecimal sum = BigDecimal.ZERO;
        for(OrderDetail orderDetail:orderDto.getOrderDetailList()){
            ProductInfo productInfo = productInfoRepository.findOne(orderDetail.getProductId());
            if(productInfo==null){
                log.error("商品信息不存在！request：{}",orderDto);
                throw new SellException(ResultEnum.PRODUCT_NOT_EXISTS);
            }else{
                if (productInfo.getProductStock()<orderDetail.getProductQuantity()){
                    //TODO 在这加上逻辑是不是更好
                    log.error("商品库存不足！request：{}",orderDetail);
                    throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
                }else{
                    orderDetail.setProductIcon(productInfo.getProductIcon());
                    orderDetail.setProductPrice(productInfo.getProductPrice());
                    orderDetail.setProductName(productInfo.getProductName());
                    orderDetail.setOrderId(orderMaster.getOrderId());
                    orderDetail.setDetailId(KeyUtil.getUniqueKey());
                    sum = sum.add(productInfo.getProductPrice().divide(new BigDecimal(orderDetail.getProductQuantity())));
                }
            }
        }
        //保存订单信息
        orderMaster.setOrderAmount(sum);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        for(OrderDetail orderDetail:orderDto.getOrderDetailList()) {
            orderDetailRepository.save(orderDetail);
        }
        //扣除库存
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream()
                .map(element->new CartDto(element.getProductId(),element.getProductQuantity()))
                .collect(Collectors.toList());
        productService.decreaseStock(cartDtoList);
        //TODO 发送消息通知订单完成
        return orderDto;
    }

    /**
     * @Description: <br>
     * 根据订单号查询订单
     * @return OrderDto
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/1/26 上午11:21
     */
    public OrderDto findOne(String orderId){
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if(orderMaster==null){
            throw new SellException(ResultEnum.PRODUCT_ORDER_NOT_EXISTS);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXISTS);
        }
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }

    /**
     * @Description: <br>
     * 根据openid获取订单列表
     * @param openId
     * @param pageable
     * @return Page<OrderDto>
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/1/29 上午10:26
     */
    public Page<OrderDto> findList(String openId, Pageable pageable){
        Page<OrderMaster> orderMasterList = orderMasterRepository.findByBuyerOpenid(openId,pageable);
        List<OrderDto> orderDtoList = OrderMaster2OrderDTOConverter.covent(orderMasterList.getContent());
        return new PageImpl<>(orderDtoList,pageable,orderMasterList.getTotalElements());
    }
    /**
     * @Description: <br>
     *  取消订单
     * @param orderDto 订单基本信息
     * @return OrderDto
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/1/29 上午10:27
     */
    @Transactional
    public OrderDto cancle(OrderDto orderDto){
        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态 新订单
        if(orderDto.getOrderStatus()!=OrderStatusEnum.NEW.getCode()){
            log.error("[取消订单]订单状态不正确，orderId：{},orderStatus:{}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.PRODUCT_ORDER_STATUS_ERROR);
        }
        orderDto.setOrderStatus(OrderStatusEnum.CANCLE.getCode());
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(updateResult==null){
            log.error("[取消订单] 订单更新失败，orderMaster:{}",orderMaster);
            throw new SellException(ResultEnum.PRODUCT_ORDER_UPDATE_ERROR);
        }
        //返回库存
        if(CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            log.error("[取消订单] 订单商品详情不存在，orderDto:{}",orderDto);
            throw new SellException(ResultEnum.PRODUCT_ORDER_DETAIL_NOT_EXISTS);
        }
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream()
                .map(orderDetail -> new CartDto(orderDetail.getProductId(),orderDetail.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDtoList);
        //TODO 如果订单已经支付，需要退款


        return orderDto;
    }

    /**
     * @Description: <br>
     *  完成订单
     * @param  orderDto
     * @return OrderDto
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/1/26 上午11:22
     */
    @Transactional
    public OrderDto finish(OrderDto orderDto){
        if(orderDto.getOrderStatus()!=OrderStatusEnum.NEW.getCode()){
            log.error("【完结订单】订单状态不对正确！oorderId:{},orderStatus:{}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.PRODUCT_ORDER_STATUS_ERROR);
        }
        orderDto.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(updateResult == null){
            log.error("【完结订单】更新订单失败,orderDto:{}",orderDto);
            throw new SellException(ResultEnum.PRODUCT_ORDER_UPDATE_ERROR);
        }
        return orderDto;
    }
    /**
     * @Description: <br>
     * 支付订单
     * @return
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/1/26 上午11:23
     */
    @Transactional
    public OrderDto paid(OrderDto orderDto){
        if(orderDto.getOrderStatus()!=OrderStatusEnum.NEW.getCode()){
            log.error("【支付订单】订单状态不对正确！oorderId:{},orderStatus:{}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.PRODUCT_ORDER_STATUS_ERROR);
        }
        if(orderDto.getPayStatus() != PayStatusEnum.WAIT.getCode()){
            log.error("【支付订单】订单支付状态不正确！orderId:{},orderPatStatus:{}",orderDto.getOrderId(),orderDto.getPayStatus());
            throw new SellException(ResultEnum.PRODUCT_ORDER_PAY_STATUS_ERROR);
        }
        orderDto.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(updateResult == null){
            log.error("【支付订单】更新订单失败,orderDto:{}",orderDto);
            throw new SellException(ResultEnum.PRODUCT_ORDER_UPDATE_ERROR);
        }
        return orderDto;
    }

}
