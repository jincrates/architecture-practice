package com.example.demo.architecture.order.application.service;

import com.example.demo.architecture.global.common.UseCase;
import com.example.demo.architecture.order.application.port.in.LoadMemberUseCase;
import com.example.demo.architecture.order.application.port.out.LoadMemberPort;
import com.example.demo.architecture.order.domain.member.Member;
import com.example.demo.architecture.order.domain.member.Member.MemberId;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoadMemberService implements LoadMemberUseCase {

    private final LoadMemberPort loadMemberPort;

    @Override
    public Member findById(MemberId memberId) {
        return loadMemberPort.findById(memberId);
    }

    @Override
    public List<Member> findAllMembers() {
        return loadMemberPort.findAll();
    }
}
