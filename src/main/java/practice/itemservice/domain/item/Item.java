package practice.itemservice.domain.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class Item {

    private Long id;

    private String itemName;

    private Integer price;

    private Integer quantity;

    @Builder
    private Item(Long id, String itemName, Integer price, Integer quantity) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public static Item forPersist(Long id, Item item) {
        return Item.builder()
                   .id(id)
                   .itemName(item.getItemName())
                   .price(item.getPrice())
                   .quantity(item.getQuantity())
                   .build();
    }

    public Item updateValues(Item item) {
        this.itemName = item.itemName;
        this.price = item.price;
        this.quantity = item.quantity;

        return this;
    }

}
