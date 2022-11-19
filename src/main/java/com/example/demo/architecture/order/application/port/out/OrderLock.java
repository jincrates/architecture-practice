package com.example.demo.architecture.order.application.port.out;

import com.example.demo.architecture.order.domain.order.Order;

public interface OrderLock {

    void lockOrder(Order.OrderId orderId);

    void unlockOrder(Order.OrderId orderId);
}
