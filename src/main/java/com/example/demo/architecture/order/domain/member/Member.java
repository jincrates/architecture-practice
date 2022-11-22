package com.example.demo.architecture.order.domain.member;

import com.example.demo.architecture.order.adapter.out.persistence.member.MemberJpaEntity;
import com.example.demo.architecture.order.domain.order.Order;
import com.example.demo.architecture.order.domain.order.Order.OrderId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Embedded;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Member {

    private MemberId id;

    private String name;

    private String address;

    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Member(MemberId id, String name, String address, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.orders = orders;
    }

    public Member(MemberJpaEntity entity) {
        this.id = new MemberId(entity.getId());
        this.name = entity.getName();
        this.address = entity.getAddress();
    }

    @Value
    public static class MemberId {
        private Long value;
    }

    public Optional<MemberId> getId() {
        return Optional.ofNullable(this.id);
    }
}
