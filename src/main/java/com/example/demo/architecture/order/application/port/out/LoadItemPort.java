package com.example.demo.architecture.order.application.port.out;

import com.example.demo.architecture.order.domain.item.Item;
import com.example.demo.architecture.order.domain.item.Item.ItemId;
import com.example.demo.architecture.order.domain.member.Member;
import com.example.demo.architecture.order.domain.member.Member.MemberId;
import java.util.List;

public interface LoadItemPort {

    Item findById(ItemId itemId);

    List<Item> findAll();
}
