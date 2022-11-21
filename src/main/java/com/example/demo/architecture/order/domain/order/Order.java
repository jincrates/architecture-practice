package com.example.demo.architecture.order.domain.order;

import com.example.demo.architecture.order.adapter.out.persistence.order.OrderJpaEntity;
import com.example.demo.architecture.order.domain.member.Member;
import java.util.Arrays;
import java.util.stream.Collectors;
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

    private List<OrderItem> orderItems = new ArrayList<>();

    LocalDateTime orderDate;

    private OrderStatus status;

    public Order(OrderJpaEntity entity) {
        this.id = new OrderId(entity.getId());
        this.member = new Member(entity.getMember());
        this.orderItems = entity.getOrderItems().stream()
            .map(orderItem -> new OrderItem(orderItem))
            .collect(Collectors.toList());
        this.orderDate = entity.getOrderDate();
        this.status = entity.getStatus();
    }

    public static Order createOrder(Member member, OrderItem... orderItems) {
        Order order = Order.builder()
            .member(member)
            .status(OrderStatus.ORDER)
            .orderDate(LocalDateTime.now())
            .build();

        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }

        return order;
    }

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
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

    public Optional<OrderId> getId() {
        return Optional.ofNullable(this.id);
    }

}
