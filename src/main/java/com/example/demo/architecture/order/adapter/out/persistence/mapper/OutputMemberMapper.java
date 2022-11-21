package com.example.demo.architecture.order.adapter.out.persistence.mapper;

import com.example.demo.architecture.global.common.Mapper;
import com.example.demo.architecture.order.adapter.out.persistence.member.MemberJpaEntity;
import com.example.demo.architecture.order.domain.member.Member;
import com.example.demo.architecture.order.domain.member.Member.MemberId;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public class OutputMemberMapper {

    public Member toDomain(MemberJpaEntity entity) {
        return Member.builder()
            .id(new MemberId(entity.getId()))
            .name(entity.getName())
            .address(entity.getAddress())
            .build();
    }

    public MemberJpaEntity toEntity(Member domain) {
        return MemberJpaEntity.builder()
            .id(domain.getId().isEmpty() ? null : domain.getId().get().getValue())
            .name(domain.getName())
            .address(domain.getAddress())
            .build();
    }

    public List<Member> toDomain(List<MemberJpaEntity> entites) {
        return entites.stream()
            .map(e -> toDomain(e))
            .collect(Collectors.toList());
    }

    public List<MemberJpaEntity> toEntity(List<Member> domains) {
        return domains.stream()
            .map(d -> toEntity(d))
            .collect(Collectors.toList());
    }
}
