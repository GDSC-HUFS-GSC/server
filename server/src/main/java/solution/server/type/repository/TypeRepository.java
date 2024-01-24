package solution.server.type.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import solution.server.category.model.Category;
import solution.server.type.model.Type;

public interface TypeRepository extends JpaRepository<Type, Long> {

    Type findByName(String name);
//    @Query("SELECT t FROM Type t JOIN FETCH t.category c WHERE c.id = :categoryId")
//    List<Type> findAllByCategoryId(@Param("categoryId") Long categoryId);
    List<Type> findAByCategory(Category category);

}