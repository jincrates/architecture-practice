package com.example.demo.architecture.order.application.service;

import com.example.demo.architecture.global.common.UseCase;
import com.example.demo.architecture.order.application.port.in.LoadItemUseCase;
import com.example.demo.architecture.order.application.port.out.LoadItemPort;
import com.example.demo.architecture.order.domain.item.Item;
import com.example.demo.architecture.order.domain.item.Item.ItemId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoadItemService implements LoadItemUseCase {

    private final LoadItemPort loadItemPort;

    @Override
    public Item findById(ItemId itemId) {
        return loadItemPort.findById(itemId);
    }

    @Override
    public List<Item> findAllItems() {
        return loadItemPort.findAll();
    }
}