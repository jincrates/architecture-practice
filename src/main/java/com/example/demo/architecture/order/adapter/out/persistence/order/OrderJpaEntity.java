package com.example.demo.architecture.order.adapter.out.persistence.order;

import com.example.demo.architecture.order.adapter.out.persistence.member.MemberJpaEntity;
import com.example.demo.architecture.order.domain.order.Order;
import com.example.demo.architecture.order.domain.order.OrderStatus;
import java.util.stream.Collectors;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "orders")
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(toBuilder = true)
public
class OrderJpaEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberJpaEntity member;

    private LocalDateTime orderDate;  //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status;  //주문상태 [ORDER, CANCEL]

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemJpaEntity> orderItems = new ArrayList<>();

    public OrderJpaEntity(Order domain) {
        this.id = domain.getId().isEmpty() ? null : domain.getId().get().getValue();
        this.member = new MemberJpaEntity(domain.getMember());
        this.orderDate = domain.getOrderDate();
        this.status = domain.getStatus();
        this.orderItems = domain.getOrderItems().stream()
            .map(orderItem -> new OrderItemJpaEntity(orderItem))
            .collect(Collectors.toList());
    }
}
