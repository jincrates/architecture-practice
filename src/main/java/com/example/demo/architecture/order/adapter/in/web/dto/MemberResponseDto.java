package com.example.demo.architecture.order.adapter.in.web.dto;

import com.example.demo.architecture.global.common.SelfValidating;
import com.example.demo.architecture.order.domain.member.Member;
import com.example.demo.architecture.order.domain.member.Member.MemberId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class MemberResponseDto extends SelfValidating<MemberResponseDto> {

    private final Member.MemberId memberId;

    private final String name;

    private final String address;

    @Builder
    public MemberResponseDto(MemberId memberId, String name, String address) {
        this.memberId = memberId;
        this.name = name;
        this.address = address;
    }

    public MemberResponseDto(Member domain) {
        this.memberId = domain.getId().isEmpty() ? null : domain.getId().get();
        this.name = domain.getName();
        this.address = domain.getAddress();
    }
}
