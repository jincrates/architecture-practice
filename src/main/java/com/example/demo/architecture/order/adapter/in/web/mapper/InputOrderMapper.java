package com.example.demo.architecture.order.adapter.in.web.mapper;

import com.example.demo.architecture.global.common.Mapper;
import com.example.demo.architecture.order.adapter.in.web.dto.MemberCreateRequestDto;
import com.example.demo.architecture.order.adapter.in.web.dto.MemberResponseDto;
import com.example.demo.architecture.order.domain.member.Member;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public class InputOrderMapper {

    public MemberResponseDto toDto(Member domain) {
        return MemberResponseDto.builder()
            .memberId(domain.getId())
            .name(domain.getName())
            .address(domain.getAddress())
            .build();
    }

    public Member toDomain(MemberCreateRequestDto dto) {
        return Member.builder()
            .name(dto.getName())
            .address(dto.getAddress())
            .build();
    }

    public List<MemberResponseDto> toDto(List<Member> allMembers) {
        return allMembers.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }
}
