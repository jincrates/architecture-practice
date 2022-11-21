package com.example.demo.architecture.order.adapter.in.web;

import static org.junit.jupiter.api.Assertions.*;

import antlr.collections.impl.IntRange;
import com.example.demo.architecture.infra.MockMvcTest;
import com.example.demo.architecture.order.application.port.in.AddMemberRequest;
import com.example.demo.architecture.order.application.port.in.AddMemberUseCase;
import com.example.demo.architecture.order.application.port.in.LoadMemberUseCase;
import com.example.demo.architecture.order.domain.member.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockMvcTest
class MemberControllerTest {

    @Autowired MockMvc mockmvc;
    @Autowired ObjectMapper objectMapper;
    @Autowired AddMemberUseCase addMemberUseCase;
    @Autowired LoadMemberUseCase loadMemberUseCase;


    @Test
    @DisplayName("사용자 등록 테스트")
    void saveMemberTest() throws Exception {
        //given
        var givenRequest = AddMemberRequest.builder()
            .name("user01")
            .address("서울시 강남구")
            .build();

        //when & then
        String content = objectMapper.writeValueAsString(givenRequest);
        mockmvc.perform(
                post("/api/v1/members")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(content))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name").value(givenRequest.getName()))
            .andExpect(jsonPath("$.address").value(givenRequest.getAddress()));
    }

    @Test
    @DisplayName("사용자 조회 테스트")
    void findMemberTest() throws Exception {
        //given
        var givenMember = Member.builder()
            .name("user01")
            .address("서울시 강남구")
            .build();

        //when
        Member savedMember = addMemberUseCase.addMember(givenMember);
        Member findMember = loadMemberUseCase.findById(savedMember.getId());

        // then
        assertNotNull(findMember);
        assertEquals(findMember.getId(), savedMember.getId());
        assertEquals(findMember.getName(), savedMember.getName());
        assertEquals(findMember.getAddress(), savedMember.getAddress());
    }

    @Test
    @DisplayName("사용자 리스트 조회 테스트")
    void findAllMembersTest() throws Exception {
        //given
        int expectedListSize = 10;
        List<Member> members = new ArrayList<>();
        IntStream.rangeClosed(1, expectedListSize).forEach(i -> {
            Member givenMember = Member.builder()
                .name("user" + i)
                .address("서울시 강남구" + i)
                .build();

            members.add(givenMember);
        });

        //when
        List<Member> savedMembers = addMemberUseCase.addAllMembers(members);
        List<Member> findAllMembers = loadMemberUseCase.findAllMembers();

        // then
        assertNotNull(findAllMembers);
        assertEquals(expectedListSize, findAllMembers.size(), savedMembers.size());
    }
}