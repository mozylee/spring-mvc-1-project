package practice.itemservice.domain.item;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class itemRepository {

    private static final ConcurrentMap<Long, Item> store = new ConcurrentHashMap<>();

    private static AtomicLong sequence = new AtomicLong(0L);

    private static final long DELTA = 1L;

    public Item save(Item item) {
        Long id = sequence.addAndGet(DELTA);

        Item persistedItem = Item.forPersist(id, item);
        store.put(id, persistedItem);

        return persistedItem;
    }

}
