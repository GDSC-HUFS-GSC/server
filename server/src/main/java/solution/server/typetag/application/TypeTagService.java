package solution.server.typetag.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solution.server.tag.application.TagService;
import solution.server.type.application.TypeService;
import solution.server.typetag.model.TypeTag;
import solution.server.typetag.repository.TypeTagRepository;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class TypeTagService {
    private final TypeTagRepository typeTagRepository;
    private final TypeService typeService;
    private final TagService tagService;

    public List<TypeTag> getAllTypeTags(){ return typeTagRepository.findAll();}
    public TypeTag getTypeTagById(Long typeTagId){
        return typeTagRepository.findById(typeTagId).orElseThrow(()->new IllegalStateException("[Error]"));
    }

    public TypeTag addNewTypeTag(TypeTag typeTag,String typeName, String tagName){
        var type = typeService.getTypeByName(typeName);
        var tag = tagService.getTagByName(tagName);
        typeTag.addInfo(type, tag);
        return typeTag;
    }

    public List<TypeTag> getTypeTagsByTypeId(Long typeId){
        return typeTagRepository.findAllByTypeId(typeId);
    }

    public List<TypeTag> getTypeTagsByTagId(Long tagId){
        return typeTagRepository.findAllByTagId(tagId);
    }
}
