package practice.itemservice.domain.item;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clear();
    }

    @Test
    @DisplayName("상품 정보를 저장한다.")
    void save() throws Exception {
        // given
        Item givenItem = createItem("TEST", 10000, 10);

        // when
        Item savedItem = itemRepository.save(givenItem);

        // then
        System.out.println("savedItem = " + savedItem);
        assertThat(savedItem.getId()).isNotNull();
        assertThat(savedItem).extracting("itemName", "price", "quantity")
                             .containsExactlyInAnyOrder(givenItem.getItemName(), givenItem.getPrice(), givenItem.getQuantity());
    }

    @Test
    @DisplayName("저장된 상품 전체를 조회한다.")
    void findAll() throws Exception {
        // given
        List<Item> givenItems = List.of(createItem("TEST1", 10001, 11),
                                        createItem("TEST2", 10002, 12));
        itemRepository.saveAll(givenItems);

        // when
        List<Item> items = itemRepository.findAll();

        // then
        assertThat(items).hasSize(2)
                         .extracting("itemName", "price", "quantity")
                         .containsExactlyInAnyOrderElementsOf(givenItems.stream()
                                                                        .map(item -> tuple(item.getItemName(),
                                                                                           item.getPrice(),
                                                                                           item.getQuantity()))
                                                                        .toList());
    }

    private static Item createItem(String itemName, int price, int quantity) {
        return Item.builder()
                   .itemName(itemName)
                   .price(price)
                   .quantity(quantity)
                   .build();
    }

}