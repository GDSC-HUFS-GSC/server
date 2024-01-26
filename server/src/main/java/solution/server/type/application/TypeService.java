package solution.server.type.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solution.server.category.application.CategoryService;
import solution.server.category.model.Category;

import solution.server.recycle.application.RecycleService;
import solution.server.recycle.model.Recycle;
import solution.server.type.dto.TypeDtos.TypeResponseDto;
import solution.server.type.model.Type;
import solution.server.type.repository.TypeRepository;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class TypeService {

    private final TypeRepository typeRepository;
    private final RecycleService recycleService;
    private final CategoryService categoryService;

    public String getCategoryNameByName(String typeName) {
        Type type = typeRepository.findByName(typeName);
        Category category = type.getCategory();
        return category.getName();
    }

    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }

    public List<TypeResponseDto> getAllTypeResponse() {
        return getAllTypes().stream().map(TypeResponseDto::new).toList();
    }

    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    public TypeResponseDto getTypeResponseById(Long id) {
        return new TypeResponseDto(getTypeById(id));
    }

    public TypeResponseDto getTypeResponseByName(String name) {
        return new TypeResponseDto(getTypeByName(name));
    }

    public Type getTypeById(Long id) {
        return typeRepository.findById(id).orElseThrow(() -> new IllegalStateException("[ERROR]"));
    }

    public Type addNewType(Type type, String recycleName, String categoryName) {
        var recycle = recycleService.getRecycleByName(recycleName);
        var category = categoryService.getCategoryByName(categoryName);
        type.addInfo(recycle, category);
        typeRepository.save(type);
        return type;
    }

    public TypeResponseDto updateHowByName(String typeName, String how) {
        Type type = getTypeByName(typeName);
        type.updateHow(how);
        return new TypeResponseDto(type);
    }

    public TypeResponseDto updateImageByName(String typeName, String imgUrl) {
        Type type = getTypeByName(typeName);
        type.updateImageUrl(imgUrl);
        return new TypeResponseDto(type);
    }

    public TypeResponseDto updateTypeName(String typeName, String newName) {
        Type type = getTypeByName(typeName);
        type.updateName(newName);
        return new TypeResponseDto(type);
    }

    public void deleteType(String typeName) {
        Type type = getTypeByName(typeName);
        typeRepository.delete(type);
    }

    public TypeResponseDto updateCategoryByName(String typeName, String categoryName) {
        Type type = getTypeByName(typeName);
        Category category = categoryService.getCategoryByName(categoryName);
        type.updateCategory(category);
        return new TypeResponseDto(type);
    }

    public TypeResponseDto updateRecycleByName(String typeName, String recycleName) {
        Type type = getTypeByName(typeName);
        Recycle recycle = recycleService.getRecycleByName(recycleName);
        type.updateRecycle(recycle);
        return new TypeResponseDto(type);
    }

    public List<TypeResponseDto> getTypeListByCategoryName(String name) {
        Category category = categoryService.getCategoryByName(name);
        return typeRepository.findAByCategory(category).stream().map(TypeResponseDto::new).toList();
    }
}