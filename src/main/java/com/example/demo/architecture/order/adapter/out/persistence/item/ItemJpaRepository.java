package com.example.demo.architecture.order.adapter.out.persistence.item;

import com.example.demo.architecture.order.adapter.out.persistence.order.OrderJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemJpaRepository extends JpaRepository<ItemJpaEntity, Long> {

    Optional<ItemJpaEntity> findById(Long orderId);
}
