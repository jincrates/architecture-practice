package com.example.demo.architecture.order.application.port.in;

public interface CancelOrderUseCase {

    boolean cancelOrder(CancelOrderCommand command);
}
