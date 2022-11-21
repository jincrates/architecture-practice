package com.example.demo.architecture.order.application.port.in;

import com.example.demo.architecture.global.common.UseCase;

public interface CancelOrderUseCase {

    boolean cancelOrder(CancelOrderCommand command);
}
