package com.example.demo.architecture.order.adapter.in.web.mapper;

import com.example.demo.architecture.global.common.Mapper;
import com.example.demo.architecture.order.adapter.in.web.dto.MemberCreateRequestDto;
import com.example.demo.architecture.order.adapter.in.web.dto.MemberResponseDto;
import com.example.demo.architecture.order.adapter.in.web.dto.OrderCreateRequestDto;
import com.example.demo.architecture.order.adapter.in.web.dto.OrderResponseDto;
import com.example.demo.architecture.order.domain.member.Member;
import com.example.demo.architecture.order.domain.order.Order;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public class InputOrderMapper {

//    public OrderResponseDto toDto(Order domain) {
//        return OrderResponseDto.builder().build();
//    }
//
////    public Order toDomain(OrderCreateRequestDto dto) {
////        return Member.builder()
////            .name(dto.getName())
////            .address(dto.getAddress())
////            .build();
////    }
//
//    public List<OrderResponseDto> toDto(List<Member> allMembers) {
//        return allMembers.stream()
//            .map(this::toDto)
//            .collect(Collectors.toList());
//    }
}
