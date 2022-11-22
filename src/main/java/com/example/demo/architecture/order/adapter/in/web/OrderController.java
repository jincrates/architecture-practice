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
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final InputOrderMapper inputOrderMapper;
    private final CreateOrderUseCase createOrderUseCase;
    private final LoadOrderUseCase loadOrderUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;

    @PostMapping(path = "/api/v1/orders/cancel/{orderId}")
    void cancelOrder(@PathVariable("orderId") Long orderId) {
        CancelOrderCommand command = new CancelOrderCommand(
                new OrderId(orderId),
                OrderStatus.CANCEL
        );

        cancelOrderUseCase.cancelOrder(command);
    }

    @PostMapping("/api/v1/orders")
    @ResponseStatus(HttpStatus.CREATED)
    OrderResponseDto createOrder(
        @RequestParam("memberId") Long memberId,
        @RequestParam("itemId") Long itemId,
        @RequestParam("count") int count) {

        Order savedOrder = createOrderUseCase.createOrder(
            new MemberId(memberId),
            new ItemId(itemId),
            count);
        return new OrderResponseDto(savedOrder);
    }

    @GetMapping("/api/v1/orders")
    @ResponseStatus(HttpStatus.OK)
    List<OrderResponseDto> findAllOrders() {
        List<Order> orders = loadOrderUseCase.findAllOrders();
        return orders.stream()
            .map(o -> new OrderResponseDto(o))
            .collect(Collectors.toList());
    }

    @GetMapping("/api/v1/orders/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    OrderResponseDto findOrder(@PathVariable("orderId") Long orderId) {
        Order loadOrder = loadOrderUseCase.findById(new OrderId(orderId));
        return new OrderResponseDto(loadOrder);
    }
}
