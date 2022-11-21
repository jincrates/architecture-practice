package com.example.demo.architecture.order.application.service;

import com.example.demo.architecture.global.common.UseCase;
import com.example.demo.architecture.order.application.port.in.AddMemberUseCase;
import com.example.demo.architecture.order.application.port.out.AddMemberPort;
import com.example.demo.architecture.order.domain.member.Member;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class AddMemberService implements AddMemberUseCase {

    private final AddMemberPort addMemberPort;
    @Override
    public Member addMember(Member member) {
        return addMemberPort.save(member);
    }

    @Override
    public List<Member> addAllMembers(List<Member> domains) {
        return addMemberPort.saveAll(domains);
    }
}
