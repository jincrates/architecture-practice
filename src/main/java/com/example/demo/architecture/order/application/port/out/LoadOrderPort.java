package com.example.demo.architecture.order.application.port.out;

import com.example.demo.architecture.order.domain.order.Order;
import com.example.demo.architecture.order.domain.order.Order.OrderId;

import java.util.List;

public interface LoadOrderPort {

    Order findById(OrderId orderId);

    List<Order> findAll();
}
