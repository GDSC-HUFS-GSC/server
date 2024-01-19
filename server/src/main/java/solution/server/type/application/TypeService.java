package solution.server.type.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solution.server.category.model.Category;
import solution.server.category.repository.CategoryRepository;
import solution.server.recycle.model.Recycle;
import solution.server.recycle.repository.RecycleRepository;
import solution.server.type.model.Type;
import solution.server.type.repository.TypeRepository;

import java.util.List;

@Service
@Transactional
public class TypeService {


    private TypeRepository typeRepository;
    private CategoryRepository categoryRepository;
    private RecycleRepository recycleRepository;

    // 종류 등록
    public Long addNewType(String name) {
        Type type = new Type();
        type.setName(name);
        typeRepository.save(type);
        return type.getId();
    }

    // 종류 이름으로 분리수거방법 조회
    public String getHowByName(String typeName){
        Type type = typeRepository.findByName(typeName);
        return type.getHow();
    }

    // 종류 이름으로 분리수거방법 설정
    public Long setHowByName(String typeName, String how){
        Type type = typeRepository.findByName(typeName);
        type.setHow(how);
        typeRepository.save(type);
        return type.getId();
    }

    // 종류 이름으로 이미지 설정
    public Long setImageByName(String typeName, String img){
        Type type = typeRepository.findByName(typeName);
        type.setImg(img);
        typeRepository.save(type);
        return type.getId();
    }

    // 모든 종류 조회
    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }

    // 종류 이름 수정
    public Long changeTypeName(String typeNameOriginal, String typeNameNew){
        Type type = typeRepository.findByName(typeNameOriginal);
        type.setName(typeNameNew);
        typeRepository.save(type);
        return type.getId();
    }

    // 종류 삭제
    public Long deleteType(String typeName){
        Type type = typeRepository.findByName(typeName);
        typeRepository.delete(type);
        return type.getId();
    }

    // 종류 이름과 새 카테고리 이름으로 카테고리 설정
    public Long setCategoryByName(String typeName, String categoryName){
        Type type = typeRepository.findByName(typeName);
        Category category = categoryRepository.findByName(categoryName);
        type.setCategory(category);
        typeRepository.save(type);
        return type.getId();
    }

    // 종류 이름으로 카테고리 이름 조회
    public String getCategoryByName(String typeName){
        Type type = typeRepository.findByName(typeName);
        Category category = type.getCategory();
        return category.getName();
    }

    // 종류 이름과 새 분류 이름으로 분류 설정
    public Long setRecycleByName(String typeName, String recycleName){
        Type type = typeRepository.findByName(typeName);
        Recycle recycle = recycleRepository.findByName(recycleName);
        type.setRecycle(recycle);
        typeRepository.save(type);
        return type.getId();
    }

    // 종류 이름으로 분류 이름 조회
    public String getRecycleByName(String typeName){
        Type type = typeRepository.findByName(typeName);
        Recycle recycle = type.getRecycle();
        return recycle.getName();
    }
}