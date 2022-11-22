package com.example.demo.architecture.order.adapter.out.persistence.order;

import com.example.demo.architecture.order.adapter.out.persistence.item.ItemJpaEntity;
import com.example.demo.architecture.order.domain.item.Item;
import com.example.demo.architecture.order.domain.order.Order;
import com.example.demo.architecture.order.domain.order.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "order_items")
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(toBuilder = true)
public class OrderItemJpaEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemJpaEntity item;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderJpaEntity order;

    private int orderPrice;

    private int count;

    public OrderItemJpaEntity(OrderItem domain) {
        this.id = domain.getId().isEmpty() ? null : domain.getId().get().getValue();
        this.item = new ItemJpaEntity(domain.getItem());
        this.orderPrice = domain.getOrderPrice();
        this.count = domain.getCount();
    }

    public static OrderItemJpaEntity createOrderItem(ItemJpaEntity item, int orderPrice, int count) {
        OrderItemJpaEntity orderItem = OrderItemJpaEntity.builder()
            .item(item)
            .orderPrice(orderPrice)
            .count(count)
            .build();

        item.removeStock(count);
        return orderItem;
    }

    public void setOrder(OrderJpaEntity order) {
        this.order = order;
    }
}
