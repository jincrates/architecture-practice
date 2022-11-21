package com.example.demo.architecture.order.adapter.out.persistence.member;

import com.example.demo.architecture.global.common.PersistenceAdapter;
import com.example.demo.architecture.order.application.port.out.AddMemberPort;
import com.example.demo.architecture.order.application.port.out.LoadMemberPort;
import com.example.demo.architecture.order.domain.member.Member;
import com.example.demo.architecture.order.domain.member.Member.MemberId;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
class MemberPersistenceAdapter implements
    LoadMemberPort,
    AddMemberPort {

    private final MemberJpaRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    public Member save(Member member) {
        MemberJpaEntity savedMember = memberRepository.save(memberMapper.toEntity(member));
        return memberMapper.toDomain(savedMember);
    }

    @Override
    public List<Member> saveAll(List<Member> members) {
        List<MemberJpaEntity> memberEntities = memberRepository.saveAll(memberMapper.toEntity(members));
        return memberMapper.toDomain(memberEntities);
    }

    @Override
    public Member findById(MemberId memberId) {
        MemberJpaEntity member =
            memberRepository.findById(memberId.getValue())
                .orElseThrow(EntityNotFoundException::new);
        return memberMapper.toDomain(member);
    }

    @Override
    public List<Member> findAll() {
        List<MemberJpaEntity> memberEntities = memberRepository.findAll();
        return memberMapper.toDomain(memberEntities);
    }
}
