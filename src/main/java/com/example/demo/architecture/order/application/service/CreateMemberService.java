package com.example.demo.architecture.order.application.service;

import com.example.demo.architecture.global.common.UseCase;
import com.example.demo.architecture.order.application.port.in.CreateMemberUseCase;
import com.example.demo.architecture.order.application.port.out.CreateMemberPort;
import com.example.demo.architecture.order.domain.member.Member;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class CreateMemberService implements CreateMemberUseCase {

    private final CreateMemberPort createMemberPort;
    @Override
    public Member createMember(Member member) {
        return createMemberPort.save(member);
    }

    @Override
    public List<Member> createAllMembers(List<Member> domains) {
        return createMemberPort.saveAll(domains);
    }
}
