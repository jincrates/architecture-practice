package com.example.demo.architecture.order.domain.order;

import com.example.demo.architecture.order.adapter.out.persistence.order.OrderItemJpaEntity;
import com.example.demo.architecture.order.domain.item.Item;
import com.example.demo.architecture.order.domain.order.Order.OrderId;
import java.util.Optional;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
public class OrderItem {

    private OrderItemId id;

    private Item item;

    private Order order;

    private int orderPrice;

    private int count;

    public OrderItem(OrderItemJpaEntity entity) {
        this.id = new OrderItemId(entity.getId());
        this.item = new Item(entity.getItem());
        this.order = new Order(entity.getOrder());
        this.orderPrice = entity.getOrderPrice();
        this.count = entity.getCount();
    }

    /**
     * 주문상품 생성
     */
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = OrderItem.builder()
            .item(item)
            .orderPrice(orderPrice)
            .count(count)
            .build();

        item.removeStock(count);
        return orderItem;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void cancel() {
        getItem().addStock(count);
    }

    /**
     * 주문상품 전체 가격 조회
     */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }


    @Value
    public static class OrderItemId {
        private Long value;
    }

    public Optional<OrderItemId> getId() {
        return Optional.ofNullable(this.id);
    }
}
