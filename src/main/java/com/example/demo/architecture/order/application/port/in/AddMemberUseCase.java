package com.example.demo.architecture.order.application.port.in;

import com.example.demo.architecture.order.domain.member.Member;
import java.util.List;

public interface AddMemberUseCase {

    Member addMember(Member domain);

    List<Member> addAllMembers(List<Member> domains);
}
