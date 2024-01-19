package solution.server.category.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solution.server.category.model.Category;
import solution.server.category.repository.CategoryRepository;

import java.util.List;

@Service
@Transactional
public class CategoryService {


    private CategoryRepository categoryRepository;

    // 카테고리 등록
    public Long addNewCategory(String name){
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
        return category.getId();
    }

    // 모든 카테고리 조회
    public List<Category> getAllCategories(){ return categoryRepository.findAll();}

    // 카테고리 수정
    public Long changeCategory(String nameOriginal, String nameNew){
        Category category = categoryRepository.findByName(nameOriginal);
        category.setName(nameNew);
        categoryRepository.save(category);
        return category.getId();
    }

    // 카테고리 삭제
    public Long deleteCategory(String name){
        Category category = categoryRepository.findByName(name);
        categoryRepository.delete(category);
        return category.getId();
    }
}
