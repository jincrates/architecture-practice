package com.example.demo.architecture.order.domain.item;

import com.example.demo.architecture.global.exception.OutOfStockException;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Item {

    private ItemId id;

    private String itemName;

    private int price;

    private int stockNumber;  //재고 수량

    private String itemDetail;

    private ItemSellStatus itemSellStatus;  //상품 판매 상태

    @Builder
    public Item(ItemId id, String itemName, int price, int stockNumber, String itemDetail, ItemSellStatus itemSellStatus) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.stockNumber = stockNumber;
        this.itemDetail = itemDetail;
        this.itemSellStatus = itemSellStatus;
    }

    public void removeStock(int stockNumber) {
        int restStock = this.stockNumber - stockNumber;
        if (restStock < 0) {
            throw new OutOfStockException("상품의 재고가 부족합니다.(현재 재고 수량: " + this.stockNumber + ")");
        }
        this.stockNumber = restStock;
    }

    public void addStock(int stockNumber) {
        this.stockNumber += stockNumber;
    }

    @Value
    public static class ItemId {
        private Long value;
    }
}
