package com.example.demo.architecture.order.adapter.out.persistence.mapper;

import com.example.demo.architecture.global.common.Mapper;
import com.example.demo.architecture.order.adapter.out.persistence.item.ItemJpaEntity;
import com.example.demo.architecture.order.adapter.out.persistence.member.MemberJpaEntity;
import com.example.demo.architecture.order.adapter.out.persistence.order.OrderItemJpaEntity;
import com.example.demo.architecture.order.adapter.out.persistence.order.OrderJpaEntity;
import com.example.demo.architecture.order.domain.item.Item;
import com.example.demo.architecture.order.domain.member.Member;
import com.example.demo.architecture.order.domain.order.Order;
import com.example.demo.architecture.order.domain.order.Order.OrderId;
import com.example.demo.architecture.order.domain.order.OrderItem;
import com.example.demo.architecture.order.domain.order.OrderItem.OrderItemId;
import java.util.stream.Collectors;

@Mapper
public class OutputOrderMapper {

    public OrderItem toDomain(OrderItemJpaEntity entity) {
        return OrderItem.builder()
            .id(new OrderItemId(entity.getId()))
            .item(new Item(entity.getItem()))
            .order(new Order(entity.getOrder()))
            .orderPrice(entity.getOrderPrice())
            .build();
    }

    public OrderItemJpaEntity toEntity(OrderItem domain) {
        return OrderItemJpaEntity.builder()
            .id(domain.getId().isEmpty() ? null : domain.getId().get().getValue())
            .item(new ItemJpaEntity(domain.getItem()))
            .order(new OrderJpaEntity(domain.getOrder()))
            .count(domain.getCount())
            .build();
    }
}
