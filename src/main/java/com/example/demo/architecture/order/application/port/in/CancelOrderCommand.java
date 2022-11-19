package com.example.demo.architecture.order.application.port.in;

import com.example.demo.architecture.global.common.SelfValidating;
import com.example.demo.architecture.order.domain.order.OrderStatus;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import static com.example.demo.architecture.order.domain.order.Order.*;

@Value
@EqualsAndHashCode(callSuper = false)
public class CancelOrderCommand extends SelfValidating<CancelOrderCommand> {

    @NotNull
    private final OrderId orderId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private final OrderStatus orderStatus;

    public CancelOrderCommand(OrderId orderId, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.validateSelf();
    }
}
