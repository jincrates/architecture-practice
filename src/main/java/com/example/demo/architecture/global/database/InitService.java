package com.example.demo.architecture.global.database;

import com.example.demo.architecture.order.adapter.out.persistence.item.ItemJpaEntity;
import com.example.demo.architecture.order.adapter.out.persistence.member.MemberJpaEntity;
import com.example.demo.architecture.order.adapter.out.persistence.order.OrderItemJpaEntity;
import com.example.demo.architecture.order.adapter.out.persistence.order.OrderJpaEntity;
import com.example.demo.architecture.order.domain.item.Item;
import com.example.demo.architecture.order.domain.member.Member;
import com.example.demo.architecture.order.domain.order.Order;
import com.example.demo.architecture.order.domain.order.OrderItem;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


@Service
@Transactional
@RequiredArgsConstructor
public class InitService {

    private final EntityManager em;

    public void dbInit1() {
        MemberJpaEntity member = createMember("userA", "서울");
        em.persist(member);

        ItemJpaEntity item1 = createItem("상품1", 10000, 100);
        em.persist(item1);

        ItemJpaEntity item2 = createItem("상품2", 20000, 200);
        em.persist(item2);

        OrderItem orderItem1 = OrderItem.createOrderItem(new Item(item1), 10000, 1);
        OrderItem orderItem2 = OrderItem.createOrderItem(new Item(item2), 20000, 2);

        Order order = Order.createOrder(new Member(member), orderItem1, orderItem2);
        em.persist(new OrderJpaEntity(order));
    }

    public void dbInit2() {
        MemberJpaEntity member = createMember("userB", "경기");
        em.persist(member);

        ItemJpaEntity item1 = createItem("상품3", 30000, 300);
        em.persist(item1);

        ItemJpaEntity item2 = createItem("상품4", 40000, 400);
        em.persist(item2);

        OrderItem orderItem1 = OrderItem.createOrderItem(new Item(item1), 30000, 3);
        OrderItem orderItem2 = OrderItem.createOrderItem(new Item(item2), 40000, 4);

        Order order = Order.createOrder(new Member(member), orderItem1, orderItem2);
        em.persist(new OrderJpaEntity(order));
    }

    private ItemJpaEntity createItem(String name, int price, int stockQuantity) {
        return ItemJpaEntity.builder()
                .itemName(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .build();
    }

    private MemberJpaEntity createMember(String name, String address) {
        return MemberJpaEntity.builder()
                .name(name)
                .address(address)
                .build();
    }
}
