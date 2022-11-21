package com.example.demo.architecture.order.application.port.out;

import com.example.demo.architecture.order.domain.member.Member;
import com.example.demo.architecture.order.domain.order.Order;
import com.example.demo.architecture.order.domain.order.Order.OrderId;
import java.time.LocalDateTime;
import java.util.List;

public interface AddMemberPort {

    Member save(Member member);

    List<Member> saveAll(List<Member> members);
}
