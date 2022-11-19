package com.example.demo.architecture.order.adapter.in.web;

import com.example.demo.architecture.global.common.WebAdapter;
import com.example.demo.architecture.order.application.port.in.CancelOrderCommand;
import com.example.demo.architecture.order.application.port.in.CancelOrderUseCase;
import com.example.demo.architecture.order.domain.order.Order.OrderId;
import com.example.demo.architecture.order.domain.order.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final CancelOrderUseCase cancelOrderUseCase;

    @PostMapping(path = "/api/v1/orders/cancel/{orderId}")
    void cancelOrder(@PathVariable("orderId") Long orderId) {
        CancelOrderCommand command = new CancelOrderCommand(
                new OrderId(orderId),
                OrderStatus.CANCEL
        );

        cancelOrderUseCase.cancelOrder(command);
    }
}
