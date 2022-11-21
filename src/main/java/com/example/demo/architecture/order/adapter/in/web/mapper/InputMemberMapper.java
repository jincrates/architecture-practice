package com.example.demo.architecture.order.adapter.in.web.mapper;

import com.example.demo.architecture.global.common.Mapper;
import com.example.demo.architecture.order.application.port.in.AddMemberRequest;
import com.example.demo.architecture.order.application.port.in.LoadMemberResponse;
import com.example.demo.architecture.order.domain.member.Member;
import com.example.demo.architecture.order.domain.member.Member.MemberId;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public class InputMemberMapper {

    public LoadMemberResponse toDto(Member domain) {
        return LoadMemberResponse.builder()
            .memberId(domain.getId())
            .name(domain.getName())
            .address(domain.getAddress())
            .build();
    }

    public Member toDomain(AddMemberRequest dto) {
        return Member.builder()
            .name(dto.getName())
            .address(dto.getAddress())
            .build();
    }

    public List<LoadMemberResponse> toDto(List<Member> allMembers) {
        return allMembers.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }
}
