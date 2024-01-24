package solution.server.typetag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solution.server.typetag.model.TypeTag;

import java.util.List;

public interface TypeTagRepository extends JpaRepository<TypeTag, Long> {

    List<TypeTag> findAllByTypeId(Long typeId);
    List<TypeTag> findAllByTagId(Long TagId);
}
