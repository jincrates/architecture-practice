package com.example.demo.architecture.order.adapter.out.persistence.order;

import com.example.demo.architecture.global.common.PersistenceAdapter;
import com.example.demo.architecture.order.adapter.out.persistence.mapper.OutputOrderMapper;
import com.example.demo.architecture.order.application.port.out.CreateOrderPort;
import com.example.demo.architecture.order.application.port.out.LoadOrderPort;
import com.example.demo.architecture.order.application.port.out.UpdateOrderStatusPort;
import com.example.demo.architecture.order.domain.order.Order;
import com.example.demo.architecture.order.domain.order.Order.OrderId;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@PersistenceAdapter
class OrderPersistenceAdapter implements
        LoadOrderPort,
        CreateOrderPort,
        UpdateOrderStatusPort {

    private final OrderJpaRepository orderRepository;
    private final OutputOrderMapper outputOrderMapper;

    @Override
    public Order loadOrder(OrderId orderId, LocalDateTime baselineDate) {

        OrderJpaEntity order =
                orderRepository.findById(orderId.getValue())
                        .orElseThrow(EntityNotFoundException::new);

        return outputOrderMapper.toDomain(order);
    }

    @Override
    public void updateStatus(Order order) {
        orderRepository.save(outputOrderMapper.toEntity(order));
    }

    @Override
    public Order save(Order order) {
        OrderJpaEntity savedOrder = orderRepository.save(outputOrderMapper.toEntity(order));
        return outputOrderMapper.toDomain(savedOrder);
    }
}
