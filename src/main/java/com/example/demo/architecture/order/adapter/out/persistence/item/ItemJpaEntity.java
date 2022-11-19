package com.example.demo.architecture.order.adapter.out.persistence.item;

import com.example.demo.architecture.order.domain.item.ItemSellStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "item")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(name = "item_name", nullable = false, length = 50)
    private String itemName; //상품명

    @Column(name = "price", nullable = false)
    private int price;  //가격

    @Column(name = "stock_quantity", nullable = false)
    private int stockQuantity;  //재고수량

    @Lob
    @Column(name = "item_detail", nullable = false)
    private String itemDetail;  //상품 상세설명

    @Enumerated(EnumType.STRING)
    @Column(name = "item_sell_status")
    private ItemSellStatus itemSellStatus;  //상품 판매 상태

    @Builder
    public ItemJpaEntity(Long id, String itemName, int price, int stockQuantity, String itemDetail, ItemSellStatus itemSellStatus) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.itemDetail = itemDetail;
        this.itemSellStatus = itemSellStatus;
    }
}
