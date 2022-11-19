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
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {

    private OrderId id;

    private Member member;

    private List<OrderItem> orderItems = new ArrayList<>();

    Delivery delivery;

    LocalDateTime orderDate;

    private OrderStatus status;

    @Builder
    public Order(OrderId id, Member member, List<OrderItem> orderItems, Delivery delivery, LocalDateTime orderDate, OrderStatus status) {
        this.id = id;
        this.member = member;
        this.orderItems = orderItems;
        this.delivery = delivery;
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

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = Order.builder()
                .member(member)
                .status(OrderStatus.ORDER)
                .delivery(delivery)
                .orderDate(LocalDateTime.now())
                .build();

        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }

        return order;
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
        if (DeliveryStatus.COMP.equals(delivery.getStatus())) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }

        if (this.status.equals(OrderStatus.CANCEL)) {
            log.info("이미 취소된 상품입니다. order ID: {}", this.id);
            return false;
        }

        this.status = OrderStatus.CANCEL;

        return true;
    }

    /**
     * 전체 주문 가격 조회
     * @return
     */
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }


    @Value
    public static class OrderId {
        private Long value;
    }
}
