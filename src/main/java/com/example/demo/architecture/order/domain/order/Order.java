package com.example.demo.architecture.order.domain.order;

import com.example.demo.architecture.order.domain.member.Member;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Getter
@Builder(toBuilder = true)
public class Order {

    private OrderId id;

    private Member member;

    LocalDateTime orderDate;

    private OrderStatus status;

    @Builder
    public Order(OrderId id, Member member, LocalDateTime orderDate, OrderStatus status) {
        this.id = id;
        this.member = member;
        this.orderDate = orderDate;
        this.status = status;
    }

    public Order(OrderId orderId, OrderStatus status) {
        this.id = orderId;
        this.status = status;
    }

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public static Order withId(OrderId orderId, OrderStatus status) {
        return new Order(orderId, status);
    }

    public Optional<OrderId> getId() {
        return Optional.ofNullable(this.id);
    }

    /**
     * 주문 취소
     */
    public boolean cancel() {
        if (this.status.equals(OrderStatus.CANCEL)) {
            log.info("이미 취소된 상품입니다. order ID: {}", this.id);
            return false;
        }

        this.status = OrderStatus.CANCEL;

        return true;
    }

    @Value
    public static class OrderId {
        private Long value;
    }
}
