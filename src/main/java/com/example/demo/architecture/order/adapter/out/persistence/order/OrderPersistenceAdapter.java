package com.example.demo.architecture.order.adapter.out.persistence.order;

import com.example.demo.architecture.global.common.PersistenceAdapter;
import com.example.demo.architecture.order.application.port.out.CreateOrderPort;
import com.example.demo.architecture.order.application.port.out.LoadOrderPort;
import com.example.demo.architecture.order.application.port.out.UpdateOrderStatusPort;
import com.example.demo.architecture.order.domain.order.Order;
import com.example.demo.architecture.order.domain.order.Order.OrderId;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@PersistenceAdapter
class OrderPersistenceAdapter implements
        LoadOrderPort,
        CreateOrderPort,
        UpdateOrderStatusPort {

    private final OrderJpaRepository orderRepository;

    @Override
    public Order findById(OrderId orderId) {
        OrderJpaEntity order = orderRepository.findById(orderId.getValue())
                        .orElseThrow(EntityNotFoundException::new);

        return new Order(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll().stream()
                .map(order -> new Order(order))
                .collect(Collectors.toList());
    }

    @Override
    public void updateStatus(Order order) {
        orderRepository.save(new OrderJpaEntity(order));
    }

    @Override
    public Order save(Order order) {
        OrderJpaEntity savedOrder = orderRepository.save(new OrderJpaEntity(order));
        return new Order(savedOrder);
    }
}
