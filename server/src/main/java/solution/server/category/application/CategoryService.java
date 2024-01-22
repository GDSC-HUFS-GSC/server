package solution.server.category.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solution.server.category.model.Category;
import solution.server.category.repository.CategoryRepository;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    // 카테고리 등록
    public Category addNewCategory(Category category){
        categoryRepository.save(category);
        return category;
    }

    // 모든 카테고리 조회
    public List<Category> getAllCategories() { return categoryRepository.findAll();}

    // 이름으로 카테고리 조회
    public Category getCategoryByName(String name) { return categoryRepository.findByName(name);}

    // 아이디로 카테고리 조회
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()->new IllegalArgumentException("[Error]"));
    }

    // 카테고리 이미지 변경
    public Category updateImageUrl(Long categoryId, String imageUrl){
        Category category = getCategoryById(categoryId);
        category.updateImageUrl(imageUrl);
        //categoryRepository.save(category);
        return category;
    }

    // 카테고리 이름 변경
    public Category updateCategoryName(Long categoryId, String newName){
        Category category = getCategoryById(categoryId);
        category.updateName(newName);
        categoryRepository.save(category);
        return category;
    }

    public void deleteCategory(String name){
        Category category = getCategoryByName(name);
        categoryRepository.delete(category);
    }
}
