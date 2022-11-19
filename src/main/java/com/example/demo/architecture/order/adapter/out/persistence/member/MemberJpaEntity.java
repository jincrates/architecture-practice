package com.example.demo.architecture.order.adapter.out.persistence.member;

import com.example.demo.architecture.order.adapter.out.persistence.order.OrderJpaEntity;
import com.example.demo.architecture.order.domain.member.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Data
public class MemberJpaEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<OrderJpaEntity> orders = new ArrayList<>();
}
