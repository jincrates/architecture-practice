package com.example.demo.architecture.order.adapter.out.persistence.order;

import com.example.demo.architecture.order.adapter.out.persistence.item.ItemJpaEntity;
import com.example.demo.architecture.order.domain.item.Item;
import com.example.demo.architecture.order.domain.order.Order;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemJapEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemJpaEntity item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderJpaEntity order;

    private int orderPrice;

    private int count;

    @Builder
    public OrderItemJapEntity(Long id, ItemJpaEntity item, OrderJpaEntity order, int orderPrice, int count) {
        this.id = id;
        this.item = item;
        this.order = order;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public void setOrder(OrderJpaEntity order) {
        this.order = order;
    }
}
