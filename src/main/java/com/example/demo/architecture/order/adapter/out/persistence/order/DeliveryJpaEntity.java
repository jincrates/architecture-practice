package com.example.demo.architecture.order.adapter.out.persistence.order;

import com.example.demo.architecture.order.domain.member.Address;
import com.example.demo.architecture.order.domain.order.DeliveryStatus;
import com.example.demo.architecture.order.domain.order.Order;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "delivery")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private OrderJpaEntity order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)  //em은 무조건 STRING으로
    private DeliveryStatus status; //READY, COMP
}
