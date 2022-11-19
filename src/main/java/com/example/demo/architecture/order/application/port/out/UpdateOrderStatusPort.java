package com.example.demo.architecture.order.application.port.out;

import com.example.demo.architecture.order.domain.order.Order;

public interface UpdateOrderStatusPort {

    void updateStatus(Order order);
}
