package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Order;
import org.example.coffeeshopwebsite.model.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> getAllOrderDetail();

    void saveOrderDetail(OrderDetail orderDetail);

    List<OrderDetail> findByOrderId(int orderId);
}
