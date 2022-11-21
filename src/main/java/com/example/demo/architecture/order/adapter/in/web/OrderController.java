package com.example.demo.architecture.order.adapter.in.web;

import com.example.demo.architecture.global.common.WebAdapter;
import com.example.demo.architecture.order.adapter.in.web.dto.CancelOrderCommand;
import com.example.demo.architecture.order.adapter.in.web.dto.OrderResponseDto;
import com.example.demo.architecture.order.adapter.in.web.mapper.InputOrderMapper;
import com.example.demo.architecture.order.application.port.in.CreateOrderUseCase;
import com.example.demo.architecture.order.application.port.in.CancelOrderUseCase;
import com.example.demo.architecture.order.application.port.in.LoadOrderUseCase;
import com.example.demo.architecture.order.domain.item.Item.ItemId;
import com.example.demo.architecture.order.domain.member.Member.MemberId;
import com.example.demo.architecture.order.domain.order.Order;
import com.example.demo.architecture.order.domain.order.Order.OrderId;
import com.example.demo.architecture.order.domain.order.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final InputOrderMapper inputOrderMapper;
    private final CreateOrderUseCase createOrderUseCase;
    private final LoadOrderUseCase loadOrderUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;

    @PostMapping(path = "/cancel/{orderId}")
    void cancelOrder(@PathVariable("orderId") Long orderId) {
        CancelOrderCommand command = new CancelOrderCommand(
                new OrderId(orderId),
                OrderStatus.CANCEL
        );

        cancelOrderUseCase.cancelOrder(command);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    OrderResponseDto createOrder(
        @RequestParam("memberId") MemberId memberId,
        @RequestParam("itemId") ItemId itemId,
        @RequestParam("count") int count) {

        Order savedOrder = createOrderUseCase.createOrder(memberId, itemId, count);
        return new OrderResponseDto(savedOrder);
    }
}
