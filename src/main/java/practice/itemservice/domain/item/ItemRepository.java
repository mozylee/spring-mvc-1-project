package practice.itemservice.domain.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {

    private static final ConcurrentMap<Long, Item> STORE = new ConcurrentHashMap<>();

    private static final AtomicLong SEQUENCE = new AtomicLong(0L);

    private static final long DELTA = 1L;

    public Item save(Item item) {
        Long id = SEQUENCE.addAndGet(DELTA);

        Item persistedItem = Item.forPersist(id, item);
        STORE.put(id, persistedItem);

        return persistedItem;
    }

    public List<Item> saveAll(Collection<Item> items) {
        return items.stream()
                    .map(this::save)
                    .toList();
    }

    public Item findById(Long id) {
        return STORE.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(STORE.values());
    }

    public Item update(Long itemId, Item item) {
        Item findItem = findById(itemId);
        findItem.updateValues(item);

        return findItem;
    }

    public void clear() {
        STORE.clear();
    }

}
