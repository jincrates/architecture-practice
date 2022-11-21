package com.example.demo.architecture.order.application.service;

import com.example.demo.architecture.global.common.UseCase;
import com.example.demo.architecture.order.application.port.in.CreateMemberUseCase;
import com.example.demo.architecture.order.application.port.in.CreateOrderUseCase;
import com.example.demo.architecture.order.application.port.out.CreateMemberPort;
import com.example.demo.architecture.order.application.port.out.CreateOrderPort;
import com.example.demo.architecture.order.application.port.out.LoadItemPort;
import com.example.demo.architecture.order.application.port.out.LoadMemberPort;
import com.example.demo.architecture.order.domain.item.Item;
import com.example.demo.architecture.order.domain.item.Item.ItemId;
import com.example.demo.architecture.order.domain.member.Member;
import com.example.demo.architecture.order.domain.member.Member.MemberId;
import com.example.demo.architecture.order.domain.order.Order;
import com.example.demo.architecture.order.domain.order.OrderItem;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class CreateOrderService implements CreateOrderUseCase {

    private final LoadMemberPort loadMemberPort;
    private final LoadItemPort loadItemPort;
    private final CreateOrderPort createOrderPort;

    @Override
    public Order createOrder(MemberId memberId, ItemId itemId, int count) {

        //도메인 객체 조회
        Member member = loadMemberPort.findById(memberId);
        Item item = loadItemPort.findById(itemId);

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, orderItem);

        //주문 저장
        return createOrderPort.save(order);
    }
}
