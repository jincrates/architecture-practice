package com.example.demo.architecture.order.application.port.in;

import com.example.demo.architecture.order.domain.member.Member;
import com.example.demo.architecture.order.domain.member.Member.MemberId;
import java.util.Collection;
import java.util.List;

public interface LoadMemberUseCase {

    Member findById(MemberId memberId);

    List<Member> findAllMembers();
}
