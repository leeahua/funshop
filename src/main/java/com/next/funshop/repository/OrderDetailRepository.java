package com.next.funshop.repository;

import com.next.funshop.domains.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
    
    /**
     * @Description: <br>
     *  根据订单号获取订单详情
     * @return 
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/1/26 下午5:01
     */
    List<OrderDetail> findByOrderId(String orderId);
}
