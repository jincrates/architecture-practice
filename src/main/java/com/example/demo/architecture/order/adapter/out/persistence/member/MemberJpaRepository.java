package com.example.demo.architecture.order.adapter.out.persistence.member;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberJpaRepository extends JpaRepository<MemberJpaEntity, Long> {
}
