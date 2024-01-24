package solution.server.category.application;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solution.server.category.model.Category;
import solution.server.category.repository.CategoryRepository;

import java.util.List;
import solution.server.type.application.TypeService;

@Transactional
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final TypeService typeService;

    public Category addNewCategory(Category category){
        categoryRepository.save(category);
        return category;
    }

    public List<Category> getAllCategories() { return categoryRepository.findAll();}

    public Category getCategoryByName(String categoryName) { return categoryRepository.findByName(categoryName);}

    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(()->new IllegalArgumentException("[Error]"));
    }

    public Category updateImageUrl(Long categoryId, String imageUrl){
        Category category = getCategoryById(categoryId);
        category.updateImageUrl(imageUrl);
        return category;
    }

    public Category updateCategoryName(Long categoryId, String newName){
        Category category = getCategoryById(categoryId);
        category.updateName(newName);
        return category;
    }

    public void deleteCategory(String name){
        Category category = getCategoryByName(name);
        categoryRepository.delete(category);
    }

    public Category getItemListByCategoryName(String name) {
        Category category =  getCategoryByName(name);
        List<Objects> var = typeService.getSearchResultByCategoryId(category.getId());
        return var;
    }
}
