package com.example.demo.architecture.order.domain.order;

import com.example.demo.architecture.order.domain.member.Address;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Delivery {

    private DeliveryId id;

    private Order order;

    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //READY, COMP

    public void setOrder(Order order) {
        this.order = order;
    }

    @Value
    public static class DeliveryId {
        private Long value;
    }
}
