package com.example.demo.architecture.order.adapter.in.web.dto;

import com.example.demo.architecture.order.domain.member.Member;
import com.example.demo.architecture.order.domain.member.Member.MemberId;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class MemberCreateRequestDto {
    @NotNull
    private final String name;

    @NotNull
    private final String address;

    @Builder
    public MemberCreateRequestDto(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
