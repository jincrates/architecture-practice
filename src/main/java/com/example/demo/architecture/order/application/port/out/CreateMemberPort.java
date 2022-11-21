package com.example.demo.architecture.order.application.port.out;

import com.example.demo.architecture.order.domain.member.Member;
import java.util.List;

public interface CreateMemberPort {

    Member save(Member member);

    List<Member> saveAll(List<Member> members);
}
