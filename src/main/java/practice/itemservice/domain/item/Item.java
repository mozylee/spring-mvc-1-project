package practice.itemservice.domain.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Item {

    private Long id;

    private String itemName;

    private Integer price;

    private Integer quantity;

    @Builder
    private Item(Long id, String itemName, Integer price, Integer quantity) {
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

}
