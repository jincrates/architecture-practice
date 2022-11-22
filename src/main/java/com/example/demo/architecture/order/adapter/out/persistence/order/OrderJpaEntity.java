package com.example.demo.architecture.order.adapter.out.persistence.order;

import com.example.demo.architecture.order.adapter.out.persistence.member.MemberJpaEntity;
import com.example.demo.architecture.order.domain.member.Member;
import com.example.demo.architecture.order.domain.order.Order;
import com.example.demo.architecture.order.domain.order.OrderItem;
import com.example.demo.architecture.order.domain.order.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@NoArgsConstructor
@Builder
public class OrderJpaEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberJpaEntity member;

    private LocalDateTime orderDate;  //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status;  //주문상태 [ORDER, CANCEL]

    @Builder.Default   //Builder를 통해 생성한 인스턴스의 기본 값을 세팅할 수 있다.
    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemJpaEntity> orderItems = new ArrayList<>();

    public OrderJpaEntity(Long id, MemberJpaEntity member, LocalDateTime orderDate, OrderStatus status, List<OrderItemJpaEntity> orderItems) {
        this.id = id;
        this.member = member;
        this.orderDate = orderDate;
        this.status = status;
        this.orderItems = orderItems;  //빌더패턴 사용시 인스턴스를 만들때 초기값으로 지정한 것이 null로 된다. .
    }

    public OrderJpaEntity(Order domain) {
        this.id = domain.getId().isEmpty() ? null : domain.getId().get().getValue();
        this.member = new MemberJpaEntity(domain.getMember());
        this.orderDate = domain.getOrderDate();
        this.status = domain.getStatus();
        this.orderItems = domain.getOrderItems().stream()
            .map(orderItem -> new OrderItemJpaEntity(orderItem))
            .collect(Collectors.toList());
    }

    public static OrderJpaEntity createOrder(MemberJpaEntity member, OrderItemJpaEntity... orderItems) {
        OrderJpaEntity order = OrderJpaEntity.builder()
            .member(member)
            .status(OrderStatus.ORDER)
            .orderDate(LocalDateTime.now())
            .build();

        for (OrderItemJpaEntity orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }

        return order;
    }

    public void addOrderItem(OrderItemJpaEntity orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }


}
