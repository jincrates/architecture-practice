package com.example.demo.architecture.order.adapter.out.persistence.mapper;

import com.example.demo.architecture.global.common.Mapper;
import com.example.demo.architecture.order.adapter.out.persistence.member.MemberJpaEntity;
import com.example.demo.architecture.order.adapter.out.persistence.order.OrderItemJpaEntity;
import com.example.demo.architecture.order.adapter.out.persistence.order.OrderJpaEntity;
import com.example.demo.architecture.order.domain.member.Member;
import com.example.demo.architecture.order.domain.order.Order;
import com.example.demo.architecture.order.domain.order.Order.OrderId;
import com.example.demo.architecture.order.domain.order.OrderItem;
import java.util.stream.Collectors;

@Mapper
public class OutputOrderItemMapper {

    public Order toDomain(OrderJpaEntity entity) {
        return Order.builder()
            .id(new OrderId(entity.getId()))
            .member(new Member(entity.getMember()))
            .orderDate(entity.getOrderDate())
            .orderItems(entity.getOrderItems().stream()
                .map(orderItem -> new OrderItem(orderItem))
                .collect(Collectors.toList()))
            .build();
    }

    public OrderJpaEntity toEntity(Order domain) {
        return OrderJpaEntity.builder()
            .id(domain.getId().isEmpty() ? null : domain.getId().get().getValue())
            .member(new MemberJpaEntity(domain.getMember()))
            .orderDate(domain.getOrderDate())
            .status(domain.getStatus())
            .orderItems(domain.getOrderItems().stream()
                .map(orderItem -> new OrderItemJpaEntity(orderItem))
                .collect(Collectors.toList()))
            .build();
    }
}
