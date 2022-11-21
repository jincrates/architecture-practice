package com.example.demo.architecture.order.adapter.out.persistence.member;

import com.example.demo.architecture.global.common.Mapper;
import com.example.demo.architecture.order.adapter.out.persistence.order.OrderJpaEntity;
import com.example.demo.architecture.order.domain.member.Member;
import com.example.demo.architecture.order.domain.member.Member.MemberId;
import com.example.demo.architecture.order.domain.order.Order;
import com.example.demo.architecture.order.domain.order.Order.OrderId;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Mapper
public
class MemberMapper {

    Member toDomain(MemberJpaEntity entity) {
        return Member.builder()
            .id(new MemberId(entity.getId()))
            .name(entity.getName())
            .address(entity.getAddress())
            .build();
    }

    MemberJpaEntity toEntity(Member domain) {
        return MemberJpaEntity.builder()
            //.id(domain.getId().getValue())
            .name(domain.getName())
            .address(domain.getAddress())
            .build();
    }

    List<Member> toDomain(List<MemberJpaEntity> entites) {
        return entites.stream()
            .map(e -> toDomain(e))
            .collect(Collectors.toList());
    }

    List<MemberJpaEntity> toEntity(List<Member> domains) {
        return domains.stream()
            .map(d -> toEntity(d))
            .collect(Collectors.toList());
    }
}
