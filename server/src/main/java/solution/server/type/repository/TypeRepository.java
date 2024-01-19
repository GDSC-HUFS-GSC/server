package solution.server.type.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solution.server.type.model.Type;

public interface TypeRepository extends JpaRepository<Type, Long> {

    Type findByName(String name);
}