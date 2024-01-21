package solution.server.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solution.server.tag.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Tag findByName(String name);
}
