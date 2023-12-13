package solution.server.global.file.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import solution.server.global.file.domain.model.ImageFile;

@Repository
public interface GlobalImageRepository extends JpaRepository<ImageFile,Long> {

}
