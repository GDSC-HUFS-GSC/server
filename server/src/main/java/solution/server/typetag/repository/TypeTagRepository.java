package solution.server.typetag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solution.server.typetag.model.TypeTag;

public interface TypeTagRepository extends JpaRepository<TypeTag, Long> {
}
