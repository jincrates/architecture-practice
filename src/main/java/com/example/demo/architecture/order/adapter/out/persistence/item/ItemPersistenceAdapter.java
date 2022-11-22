package com.example.demo.architecture.order.adapter.out.persistence.item;

import com.example.demo.architecture.global.common.PersistenceAdapter;
import com.example.demo.architecture.order.adapter.out.persistence.order.OrderJpaEntity;
import com.example.demo.architecture.order.adapter.out.persistence.order.OrderJpaRepository;
import com.example.demo.architecture.order.application.port.out.CreateOrderPort;
import com.example.demo.architecture.order.application.port.out.LoadItemPort;
import com.example.demo.architecture.order.application.port.out.LoadOrderPort;
import com.example.demo.architecture.order.application.port.out.UpdateOrderStatusPort;
import com.example.demo.architecture.order.domain.item.Item;
import com.example.demo.architecture.order.domain.item.Item.ItemId;
import com.example.demo.architecture.order.domain.order.Order;
import com.example.demo.architecture.order.domain.order.Order.OrderId;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@PersistenceAdapter
class ItemPersistenceAdapter implements
        LoadItemPort {

    private final ItemJpaRepository itemJpaRepository;

    @Override
    public Item findById(ItemId itemId) {
        ItemJpaEntity item = itemJpaRepository.findById(itemId.getValue())
            .orElseThrow(EntityNotFoundException::new);
        return new Item(item);

    }

    @Override
    public List<Item> findAll() {
        return itemJpaRepository.findAll().stream()
            .map(i -> new Item(i))
            .collect(Collectors.toList());
    }
}
