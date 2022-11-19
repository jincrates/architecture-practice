package com.example.demo.architecture.order.application.service;

import com.example.demo.architecture.global.common.UseCase;
import com.example.demo.architecture.order.application.port.in.CancelOrderCommand;
import com.example.demo.architecture.order.application.port.in.CancelOrderUseCase;
import com.example.demo.architecture.order.application.port.out.LoadOrderPort;
import com.example.demo.architecture.order.application.port.out.OrderLock;
import com.example.demo.architecture.order.application.port.out.UpdateOrderStatusPort;
import com.example.demo.architecture.order.domain.order.Order;
import com.example.demo.architecture.order.domain.order.Order.OrderId;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@UseCase
@Transactional
@RequiredArgsConstructor
public class CancelOrderService implements CancelOrderUseCase {

    private final LoadOrderPort loadOrderPort;
    private final OrderLock orderLock;
    private final UpdateOrderStatusPort updateOrderStatusPort;

    @Override
    public boolean cancelOrder(CancelOrderCommand command) {

        LocalDateTime since = LocalDateTime.now().minusDays(10);

        Order order = loadOrderPort.loadOrder(command.getOrderId(), since);

        OrderId orderId = order.getId()
                .orElseThrow(() -> new IllegalStateException("expected order ID not to be empty"));

        orderLock.lockOrder(orderId);

        if (!order.cancel()) {
            orderLock.unlockOrder(orderId);
            return false;
        }

        updateOrderStatusPort.updateStatus(order);

        orderLock.unlockOrder(orderId);
        return true;
    }
}
