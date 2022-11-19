package com.example.demo.architecture.order.adapter.out.persistence.order;

import com.example.demo.architecture.order.adapter.out.persistence.member.MemberJpaEntity;
import com.example.demo.architecture.order.domain.order.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public
class OrderJpaEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberJpaEntity member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemJapEntity> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private DeliveryJpaEntity deliveryJpaEntity;

    private LocalDateTime orderDate;  //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status;  //주문상태 [ORDER, CANCEL]

    @Builder
    public OrderJpaEntity(Long id, MemberJpaEntity member, List<OrderItemJapEntity> orderItems, DeliveryJpaEntity deliveryJpaEntity, LocalDateTime orderDate, OrderStatus status) {
        this.id = id;
        this.member = member;
        this.orderItems = orderItems;
        this.deliveryJpaEntity = deliveryJpaEntity;
        this.orderDate = orderDate;
        this.status = status;
    }
}
