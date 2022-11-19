package com.example.demo.architecture.order.domain.member;

import com.example.demo.architecture.order.domain.order.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Embedded;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {

    private MemberId id;

    private String name;

    @Embedded
    private Address address;

    private List<Order> orders = new ArrayList<>();

    @Value
    public static class MemberId {
        private Long value;
    }
}
