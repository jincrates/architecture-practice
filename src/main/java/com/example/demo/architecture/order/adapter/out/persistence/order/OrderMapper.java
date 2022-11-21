package com.example.demo.architecture.order.adapter.out.persistence.order;

import com.example.demo.architecture.global.common.Mapper;
import com.example.demo.architecture.order.adapter.out.persistence.member.MemberJpaEntity;
import com.example.demo.architecture.order.adapter.out.persistence.member.MemberMapper;
import com.example.demo.architecture.order.domain.member.Member;
import com.example.demo.architecture.order.domain.order.Order;
import com.example.demo.architecture.order.domain.order.Order.OrderId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Mapper
class OrderMapper {

    OrderMapper() {
        super();
    }

    public static OrderJpaEntity toEntity(Order domain) {
        return OrderJpaEntity.builder()
            .id(domain.getId().isEmpty() ? null : domain.getId().get().getValue())
            .orderDate(domain.getOrderDate())
            .status(domain.getStatus())
            .build();
    }

    public static Order toDomain(OrderJpaEntity entity) {
//        Member member = MemberMapper.toDomain(entity.getMember());

        return Order.builder()
            .id(new OrderId(entity.getId()))
            .orderDate(entity.getOrderDate())
            .member(null)
            .status(entity.getStatus())
            .build();
    }
}
