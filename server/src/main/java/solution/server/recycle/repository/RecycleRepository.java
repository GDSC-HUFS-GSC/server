package solution.server.recycle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solution.server.recycle.model.Recycle;

public interface RecycleRepository extends JpaRepository<Recycle, Long> {

    Recycle findByName(String name);
}
