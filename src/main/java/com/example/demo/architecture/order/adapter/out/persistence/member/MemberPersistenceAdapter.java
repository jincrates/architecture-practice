package com.example.demo.architecture.order.adapter.out.persistence.member;

import com.example.demo.architecture.global.common.PersistenceAdapter;
import com.example.demo.architecture.order.adapter.out.persistence.mapper.OutputMemberMapper;
import com.example.demo.architecture.order.application.port.out.CreateMemberPort;
import com.example.demo.architecture.order.application.port.out.LoadMemberPort;
import com.example.demo.architecture.order.domain.member.Member;
import com.example.demo.architecture.order.domain.member.Member.MemberId;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
class MemberPersistenceAdapter implements
    LoadMemberPort,
    CreateMemberPort {

    private final MemberJpaRepository memberRepository;
    private final OutputMemberMapper outputMemberMapper;

    @Override
    public Member save(Member member) {
        MemberJpaEntity savedMember = memberRepository.save(outputMemberMapper.toEntity(member));
        return outputMemberMapper.toDomain(savedMember);
    }

    @Override
    public List<Member> saveAll(List<Member> members) {
        List<MemberJpaEntity> memberEntities = memberRepository.saveAll(outputMemberMapper.toEntity(members));
        return outputMemberMapper.toDomain(memberEntities);
    }

    @Override
    public Member findById(MemberId memberId) {
        MemberJpaEntity member =
            memberRepository.findById(memberId.getValue())
                .orElseThrow(EntityNotFoundException::new);
        return outputMemberMapper.toDomain(member);
    }

    @Override
    public List<Member> findAll() {
        List<MemberJpaEntity> memberEntities = memberRepository.findAll();
        return outputMemberMapper.toDomain(memberEntities);
    }
}
