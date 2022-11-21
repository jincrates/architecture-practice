package com.example.demo.architecture.order.adapter.in.web;

import com.example.demo.architecture.global.common.WebAdapter;
import com.example.demo.architecture.order.adapter.in.web.mapper.InputMemberMapper;
import com.example.demo.architecture.order.adapter.in.web.dto.MemberCreateRequestDto;
import com.example.demo.architecture.order.application.port.in.CreateMemberUseCase;
import com.example.demo.architecture.order.adapter.in.web.dto.MemberResponseDto;
import com.example.demo.architecture.order.application.port.in.LoadMemberUseCase;
import com.example.demo.architecture.order.domain.member.Member;
import com.example.demo.architecture.order.domain.member.Member.MemberId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final InputMemberMapper inputMemberMapper;
    private final CreateMemberUseCase createMemberUseCase;
    private final LoadMemberUseCase loadMemberUseCase;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    MemberResponseDto createMember(@RequestBody MemberCreateRequestDto request) {
        Member savedMember = createMemberUseCase.createMember(inputMemberMapper.toDomain(request));
        return inputMemberMapper.toDto(savedMember);
    }

    @GetMapping("/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    MemberResponseDto loadMember(@PathVariable("memberId") MemberId memberId) {
        Member member = loadMemberUseCase.findById(memberId);
        return inputMemberMapper.toDto(member);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<MemberResponseDto> loadAllMembers() {
        List<Member> members = loadMemberUseCase.findAllMembers();
        return inputMemberMapper.toDto(members);
    }
}
