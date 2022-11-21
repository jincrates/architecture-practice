package com.example.demo.architecture.order.application.port.in;

import com.example.demo.architecture.order.domain.member.Member;
import java.util.List;

public interface CreateMemberUseCase {

    Member createMember(Member domain);

    List<Member> createAllMembers(List<Member> domains);
}
