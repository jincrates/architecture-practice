package com.example.demo.architecture.order.application.service;

import com.example.demo.architecture.global.common.UseCase;
import com.example.demo.architecture.order.application.port.in.LoadOrderUseCase;
import com.example.demo.architecture.order.application.port.out.LoadOrderPort;
import com.example.demo.architecture.order.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoadOrderService implements LoadOrderUseCase {

    private final LoadOrderPort loadOrderPort;

    @Override
    public Order findById(Order.OrderId orderId) {
        return loadOrderPort.findById(orderId);
    }

    @Override
    public List<Order> findAllOrders() {
        return loadOrderPort.findAll();
    }
}
