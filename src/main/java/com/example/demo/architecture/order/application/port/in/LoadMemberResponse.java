package com.example.demo.architecture.order.application.port.in;

import static com.example.demo.architecture.order.domain.order.Order.OrderId;

import com.example.demo.architecture.global.common.SelfValidating;
import com.example.demo.architecture.order.domain.member.Member;
import com.example.demo.architecture.order.domain.member.Member.MemberId;
import com.example.demo.architecture.order.domain.order.OrderStatus;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class LoadMemberResponse extends SelfValidating<LoadMemberResponse> {

    private final Member.MemberId memberId;

    private final String name;

    private final String address;

    @Builder
    public LoadMemberResponse(MemberId memberId, String name, String address) {
        this.memberId = memberId;
        this.name = name;
        this.address = address;
    }
}
