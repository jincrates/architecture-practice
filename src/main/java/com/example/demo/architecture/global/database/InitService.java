package com.example.demo.architecture.global.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


@Service
@Transactional
@RequiredArgsConstructor
public class InitService {

    private final EntityManager em;
    //private final OrderMapper orderMapper;

    public void dbInit() {
        //Order order = Order.withId(new OrderId(1L), OrderStatus.ORDER);
        //em.persist(orderMapper);
        //OrderJpaEntity order = new OrderJpaEntity(1L, OrderStatus.ORDER);
        //em.persist(order);
    }
}
