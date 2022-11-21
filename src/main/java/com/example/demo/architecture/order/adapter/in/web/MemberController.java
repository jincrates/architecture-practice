package com.example.demo.architecture.order.adapter.in.web;

import com.example.demo.architecture.global.common.WebAdapter;
import com.example.demo.architecture.order.adapter.in.web.mapper.InputMemberMapper;
import com.example.demo.architecture.order.application.port.in.AddMemberRequest;
import com.example.demo.architecture.order.application.port.in.AddMemberUseCase;
import com.example.demo.architecture.order.application.port.in.LoadMemberResponse;
import com.example.demo.architecture.order.application.port.in.LoadMemberUseCase;
import com.example.demo.architecture.order.domain.member.Member;
import com.example.demo.architecture.order.domain.member.Member.MemberId;
import java.util.Collection;
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

    private final AddMemberUseCase addMemberUseCase;
    private final LoadMemberUseCase loadMemberUseCase;

    private final InputMemberMapper inputMemberMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    LoadMemberResponse addMember(@RequestBody AddMemberRequest request) {
        Member savedMember = addMemberUseCase.addMember(inputMemberMapper.toDomain(request));
        return inputMemberMapper.toDto(savedMember);
    }

    @GetMapping("/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    LoadMemberResponse loadMember(@PathVariable("memberId") MemberId memberId) {
        Member member = loadMemberUseCase.findById(memberId);
        return inputMemberMapper.toDto(member);
    }

}
