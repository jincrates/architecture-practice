package com.example.demo.architecture.order.adapter.out.persistence.item;

import com.example.demo.architecture.global.exception.NotEnoughStockException;
import com.example.demo.architecture.order.domain.item.Item;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "item")
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(toBuilder = true)
public class ItemJpaEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(name = "item_name", nullable = false, length = 50)
    private String itemName; //상품명

    @Column(name = "price", nullable = false)
    private int price;  //가격

    @Column(name = "stock_quantity", nullable = false)
    private int stockQuantity;  //재고수량

    public ItemJpaEntity(Item domain) {
        this.id = domain.getId().isEmpty() ? null : domain.getId().get().getValue();
        this.itemName = domain.getName();
        this.price = domain.getPrice();
        this.stockQuantity = domain.getStockQuantity();
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
}
