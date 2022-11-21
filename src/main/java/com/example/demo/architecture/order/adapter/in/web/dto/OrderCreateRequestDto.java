package com.example.demo.architecture.order.adapter.in.web.dto;

import com.example.demo.architecture.order.domain.member.Member;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class OrderCreateRequestDto {

    @NotNull
    private final Member member;

    @Builder
    public OrderCreateRequestDto(Member member) {
        this.member = member;
    }
}
