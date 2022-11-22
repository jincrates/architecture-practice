package com.example.demo.architecture.order.application.port.in;

import com.example.demo.architecture.order.domain.order.Order;
import com.example.demo.architecture.order.domain.order.Order.OrderId;
import java.util.List;

public interface LoadOrderUseCase {

    Order findById(OrderId orderId);

    List<Order> findAllOrders();
}
