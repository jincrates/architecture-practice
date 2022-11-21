package com.example.demo.architecture.order.application.port.in;

import com.example.demo.architecture.order.adapter.in.web.dto.CancelOrderCommand;

public interface CancelOrderUseCase {

    boolean cancelOrder(CancelOrderCommand command);
}
