package com.example.demo.architecture.order.application.port.out;

import com.example.demo.architecture.order.domain.member.Member;
import com.example.demo.architecture.order.domain.order.Order;
import java.util.List;

public interface CreateOrderPort {

    Order save(Order order);
}
