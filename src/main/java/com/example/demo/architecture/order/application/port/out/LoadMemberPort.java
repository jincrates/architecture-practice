package com.example.demo.architecture.order.application.port.out;

import com.example.demo.architecture.order.domain.member.Member;
import com.example.demo.architecture.order.domain.member.Member.MemberId;
import java.util.Collection;
import java.util.List;

public interface LoadMemberPort {

    Member findById(MemberId memberId);

    List<Member> findAll();
}
