package solution.server.item.application;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solution.server.item.model.Item;
import solution.server.item.repository.ItemRepository;

@RequiredArgsConstructor
@Transactional
@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public Item addNewItem(Item entity) {
        return null;
    }

    public Item changeImageUrl(Long itemId, String imageUrl) {
        return null;
    }

    public Item getItemDetailById(Long itemId, String token) {
        return null;
    }

    public List<Item> getAllItems() {
        return null;
    }
}
