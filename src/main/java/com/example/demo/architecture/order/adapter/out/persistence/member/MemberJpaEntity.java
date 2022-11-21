package com.example.demo.architecture.order.adapter.out.persistence.member;

import com.example.demo.architecture.order.adapter.out.persistence.order.OrderJpaEntity;
import com.example.demo.architecture.order.domain.member.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "member")
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(toBuilder = true)
public class MemberJpaEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String address;

    @OneToMany(mappedBy = "member")
    private List<OrderJpaEntity> orders = new ArrayList<>();

    public MemberJpaEntity(Member domain) {
        this.id = domain.getId().isEmpty() ? null : domain.getId().get().getValue();
        this.name = domain.getName();
        this.address = domain.getAddress();
    }
}
