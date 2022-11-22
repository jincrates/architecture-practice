package com.example.demo.architecture.order.adapter.in.web.dto;

import com.example.demo.architecture.global.common.SelfValidating;
import com.example.demo.architecture.order.domain.item.Item;
import com.example.demo.architecture.order.domain.item.Item.ItemId;
import com.example.demo.architecture.order.domain.member.Member;
import com.example.demo.architecture.order.domain.member.Member.MemberId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class ItemResponseDto extends SelfValidating<ItemResponseDto> {

    private final ItemId itemId;

    private final String name;

    private final int price;

    private final int stockQuantity;

    @Builder
    public ItemResponseDto(ItemId itemId, String name, int price, int stockQuantity) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public ItemResponseDto(Item domain) {
        this.itemId = domain.getId().isEmpty() ? null : domain.getId().get();
        this.name = domain.getName();
        this.price = domain.getPrice();
        this.stockQuantity = domain.getStockQuantity();
    }
}
