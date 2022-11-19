package com.example.demo.architecture.order.domain.order;

import com.example.demo.architecture.order.domain.item.Item;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderItem {

    private OrderItemId id;

    private Item item;

    private Order order;

    private int orderPrice;

    private int count;  //주문 수량

    @Builder
    public OrderItem(OrderItemId id, Item item, Order order, int orderPrice, int count) {
        this.id = id;
        this.item = item;
        this.order = order;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public static OrderItem createOrderItem(Item item, int count) {
        OrderItem orderItem = OrderItem.builder()
                .item(item)
                .count(count)
                .orderPrice(item.getPrice())
                .build();
        item.removeStock(count);

        return orderItem;
    }

    public int getTotalPrice() {
        return orderPrice * count;
    }

    public void cancel() {
        this.getItem().addStock(count);
    }


    @Value
    public static class OrderItemId {
        private Long value;
    }
}
