package solution.server.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solution.server.category.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);
}
