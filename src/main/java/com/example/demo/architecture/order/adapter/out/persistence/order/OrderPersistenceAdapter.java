package com.example.demo.architecture.order.adapter.out.persistence.order;

import com.example.demo.architecture.global.common.PersistenceAdapter;
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
        UpdateOrderStatusPort {

    private final OrderJpaRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public Order loadOrder(OrderId orderId, LocalDateTime baselineDate) {

        OrderJpaEntity order =
                orderRepository.findById(orderId.getValue())
                        .orElseThrow(EntityNotFoundException::new);

        return orderMapper.toDomain(order);
    }

    @Override
    public void updateStatus(Order order) {
        orderRepository.save(orderMapper.toEntity(order));
    }
}
