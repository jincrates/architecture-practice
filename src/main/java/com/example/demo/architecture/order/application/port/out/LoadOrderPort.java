package com.example.demo.architecture.order.application.port.out;

import com.example.demo.architecture.order.domain.order.Order;
import com.example.demo.architecture.order.domain.order.Order.OrderId;

import java.time.LocalDateTime;

public interface LoadOrderPort {

    Order loadOrder(OrderId orderId, LocalDateTime baselineDate);
}
