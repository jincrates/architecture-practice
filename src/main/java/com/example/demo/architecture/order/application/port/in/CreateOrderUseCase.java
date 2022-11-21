package com.example.demo.architecture.order.application.port.in;

import com.example.demo.architecture.order.domain.item.Item.ItemId;
import com.example.demo.architecture.order.domain.member.Member.MemberId;
import com.example.demo.architecture.order.domain.order.Order;

public interface CreateOrderUseCase {

    Order createOrder(MemberId memberId, ItemId itemId, int count);
}
