package solution.server.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solution.server.item.model.Item;

public interface ItemRepository extends JpaRepository<Item,Long> {
}
