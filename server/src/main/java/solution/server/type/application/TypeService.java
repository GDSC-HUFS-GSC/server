package solution.server.type.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solution.server.category.model.Category;

import solution.server.recycle.application.RecycleService;
import solution.server.recycle.model.Recycle;
import solution.server.recycle.repository.RecycleRepository;
import solution.server.type.model.Type;
import solution.server.type.repository.TypeRepository;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class TypeService {

    private final TypeRepository typeRepository;
    private final RecycleService recycleService;
    private final RecycleRepository recycleRepository;

    public Long addNewType(String name) {
        Type type = new Type();
        type.setName(name);
        typeRepository.save(type);
        return type.getId();
    }

    public String getHowByName(String typeName){
        Type type = typeRepository.findByName(typeName);
        return type.getHow();
    }

    public Long setHowByName(String typeName, String how){
        Type type = typeRepository.findByName(typeName);
        type.setHow(how);
        typeRepository.save(type);
        return type.getId();
    }

    public Long setImageByName(String typeName, String img){
        Type type = typeRepository.findByName(typeName);
        type.setImg(img);
        typeRepository.save(type);
        return type.getId();
    }

    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }

    public Long changeTypeName(String typeNameOriginal, String typeNameNew){
        Type type = typeRepository.findByName(typeNameOriginal);
        type.setName(typeNameNew);
        typeRepository.save(type);
        return type.getId();
    }

    public Long deleteType(String typeName){
        Type type = typeRepository.findByName(typeName);
        typeRepository.delete(type);
        return type.getId();
    }

    public Long setCategoryByName(String typeName, String categoryName){
        Type type = typeRepository.findByName(typeName);
        Category category = categoryRepository.findByName(categoryName);
        type.setCategory(category);
        typeRepository.save(type);
        return type.getId();
    }
    public String getCategoryByName(String typeName){
        Type type = typeRepository.findByName(typeName);
        Category category = type.getCategory();
        return category.getName();
    }

    public Long setRecycleByName(String typeName, String recycleName){
        Type type = typeRepository.findByName(typeName);
        Recycle recycle = recycleRepository.findByName(recycleName);
        type.setRecycle(recycle);
        typeRepository.save(type);
        return type.getId();
    }

     public String getRecycleByName(String typeName){
        Type type = typeRepository.findByName(typeName);
        Recycle recycle = type.getRecycle();
        return recycle.getName();
    }
}