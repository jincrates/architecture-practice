package com.example.demo.architecture.order.domain.item;

import com.example.demo.architecture.global.exception.NotEnoughStockException;
import com.example.demo.architecture.order.adapter.out.persistence.item.ItemJpaEntity;
import com.example.demo.architecture.order.domain.order.OrderItem.OrderItemId;
import java.util.Optional;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
public class Item {

    private final ItemId id;

    private final String name;

    private final int price;

    private int stockQuantity;

    public Item(ItemJpaEntity entity) {
        this.id = new ItemId(entity.getId());
        this.name = entity.getItemName();
        this.price = entity.getPrice();
        this.stockQuantity = entity.getStockQuantity();
    }

    /**
     * stock 증가
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity -= quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }

        this.stockQuantity = restStock;
    }

    @Value
    public static class ItemId {
        private Long value;
    }

    public Optional<ItemId> getId() {
        return Optional.ofNullable(this.id);
    }
}
