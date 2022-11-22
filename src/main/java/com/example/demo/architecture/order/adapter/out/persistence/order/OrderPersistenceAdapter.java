package com.example.demo.architecture.order.adapter.out.persistence.order;

import com.example.demo.architecture.global.common.PersistenceAdapter;
import com.example.demo.architecture.order.application.port.out.CreateOrderPort;
import com.example.demo.architecture.order.application.port.out.LoadOrderPort;
import com.example.demo.architecture.order.application.port.out.UpdateOrderStatusPort;
import com.example.demo.architecture.order.domain.order.Order;
import com.example.demo.architecture.order.domain.order.Order.OrderId;
import com.example.demo.architecture.order.domain.order.OrderItem;
import java.util.Map;
import java.util.Optional;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@PersistenceAdapter
class OrderPersistenceAdapter implements
        LoadOrderPort,
        CreateOrderPort,
        UpdateOrderStatusPort {

    private final EntityManager em;
    private final OrderJpaRepository orderRepository;

    @Override
    public Order findById(OrderId orderId) {
        OrderJpaEntity order = orderRepository.findById(orderId.getValue())
                        .orElseThrow(EntityNotFoundException::new);

        return new Order(order);
    }

    @Override
    public List<Order> findAll() {
        /*
        List<Order> result = findOrders();  // query 1번 -> N
        result.forEach(o -> {
            List<OrderItem> orderItems = findOrderItems(o.getId().orElseThrow(EntityNotFoundException::new));
        });
        */
        return orderRepository.findAll().stream()
            .map(o -> new Order(o))
            .collect(Collectors.toList());
    }

    @Override
    public void updateStatus(Order order) {
        orderRepository.save(new OrderJpaEntity(order));
    }

    @Override
    public Order save(Order order) {
        OrderJpaEntity savedOrder = orderRepository.save(new OrderJpaEntity(order));
        return new Order(savedOrder);
    }

    private List<OrderItem> findOrderItems(OrderId orderId) {
        return em.createQuery(
                "select new com.example.demo.architecture.order.domain.order.OrderItem(oi.id. i.itemName. oi.orderPrice, oi.count)"
                    + " from OrderItemJpaEntity oi "
                    + " join oi.item i "
                    + " where oi.order.id in :orderId", OrderItem.class)
            .setParameter("orderId", orderId)
            .getResultList();
    }

    private List<Order> findOrders() {
        return em.createQuery(
                "select new com.example.demo.architecture.order.domain.order.Order(o.id, m.name, o.orderDate, o.status) "
                    + " from OrderJpaEntity o "
                    + " join o.member m ", Order.class)
            .getResultList();
    }

}
