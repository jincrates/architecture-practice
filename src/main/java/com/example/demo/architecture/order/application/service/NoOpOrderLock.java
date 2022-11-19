package com.example.demo.architecture.order.application.service;

import com.example.demo.architecture.order.application.port.out.OrderLock;
import com.example.demo.architecture.order.domain.order.Order;
import org.springframework.stereotype.Component;

@Component
public class NoOpOrderLock implements OrderLock {
    @Override
    public void lockOrder(Order.OrderId orderId) {
        // do noting
    }

    @Override
    public void unlockOrder(Order.OrderId orderId) {
            // do noting
    }
}
