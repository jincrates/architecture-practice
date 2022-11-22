package com.example.demo.architecture.order.adapter.in.web;

import com.example.demo.architecture.global.common.WebAdapter;
import com.example.demo.architecture.order.adapter.in.web.dto.ItemResponseDto;
import com.example.demo.architecture.order.adapter.in.web.dto.MemberCreateRequestDto;
import com.example.demo.architecture.order.adapter.in.web.dto.MemberResponseDto;
import com.example.demo.architecture.order.adapter.in.web.mapper.InputMemberMapper;
import com.example.demo.architecture.order.application.port.in.CreateMemberUseCase;
import com.example.demo.architecture.order.application.port.in.LoadItemUseCase;
import com.example.demo.architecture.order.application.port.in.LoadMemberUseCase;
import com.example.demo.architecture.order.domain.item.Item;
import com.example.demo.architecture.order.domain.item.Item.ItemId;
import com.example.demo.architecture.order.domain.member.Member;
import com.example.demo.architecture.order.domain.member.Member.MemberId;
import java.util.List;
import java.util.stream.Collectors;
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
@RequestMapping("/api/v1/items")
public class ItemController {

    private final LoadItemUseCase loadItemUseCase;

    @GetMapping("/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    ItemResponseDto loadItem(@PathVariable("itemId") Long itemId) {
        Item item = loadItemUseCase.findById(new ItemId(itemId));
        return new ItemResponseDto(item);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<ItemResponseDto> loadAllItems() {
        List<Item> items = loadItemUseCase.findAllItems();
        return items.stream()
            .map(i -> new ItemResponseDto(i))
            .collect(Collectors.toList());
    }
}
